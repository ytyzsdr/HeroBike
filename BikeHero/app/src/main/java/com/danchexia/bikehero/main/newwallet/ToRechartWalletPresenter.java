package com.danchexia.bikehero.main.newwallet;

import android.widget.Toast;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MoneyController;
import com.danchexia.bikehero.main.bean.PayDetailBean;
import com.danchexia.bikehero.main.bean.RechartTypeListBean;
import com.danchexia.bikehero.pay.OnPayListener;
import com.danchexia.bikehero.pay.PayAgent;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.colours.client.model.WeiXinPaymetBO;
import vc.thinker.mvp.MvpView;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.ShowToast;

import static com.danchexia.bikehero.app.MyApplication.wxApi;

/**
 * Created by farley on 17/8/21.
 * description:
 */

public class ToRechartWalletPresenter extends BasePresenter<MvpView> {
    private ToRechartWalletActivity activity;
    private MoneyController moneyController = APIControllerFactory.getMoneyController();
    public ToRechartWalletPresenter(ToRechartWalletActivity activity) {
        this.activity = activity;
    }
    public void getRechartType(){
        addSubscription(
                moneyController.getRechartList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RechartTypeListBean>() {
                    @Override
                    public void call(RechartTypeListBean rechartTypeListBean) {
                        if (rechartTypeListBean.getError_code() == 0){
                            activity.initData(rechartTypeListBean);
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean,activity);
                    }
                }))

        );
    }
    /**
     * 充值
     *
     */
    public void recharge(Long money,final String payTpye){
        addSubscription(moneyController.getRechartResult(money,payTpye)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PayDetailBean>() {
                    @Override
                    public void call(PayDetailBean payDetails) {
                        if (MyUtils.PAY_TYPE_ALIPAY.contentEquals(payTpye)) {
                            PayAgent.getInstance().onAliPay(activity, payDetails.getAlipaPpaySignature(),
                                    new OnPayListener() {
                                        @Override
                                        public void onPaySuccess() {
                                            ShowToast.show(activity, activity.getString(R.string.toast_7));
                                        }

                                        @Override
                                        public void onPayFail(String code, String msg) {
                                            LogUtils.d("code="+code+";msg="+msg);
                                            ShowToast.show(activity, activity.getString(R.string.toast_8));
                                        }
                                    });
                        }else if (MyUtils.PAY_TYPE_WXPAY.contentEquals(payTpye)) {

                            if (wxApi == null || !wxApi.isWXAppInstalled()) {
                                Toast.makeText(activity, activity.getString(R.string.toast_9), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            PayReq req = new PayReq();
                            WeiXinPaymetBO wxPay = payDetails.getWeiXinPaymet();

                            req.appId = wxPay.getAppId();
                            req.partnerId = wxPay.getPartnerId();
                            req.prepayId = wxPay.getPrepayId();
                            req.packageValue = wxPay.getPackage1();
                            req.nonceStr = wxPay.getNonceStr();
                            req.timeStamp = wxPay.getTimeStamp();
                            req.sign = wxPay.getSign();

                            PayAgent.getInstance().onWxPay(activity, req,
                                    new OnPayListener() {
                                        @Override
                                        public void onPaySuccess() {
                                            ShowToast.show(activity, activity.getString(R.string.toast_7));
                                        }

                                        @Override
                                        public void onPayFail(String code, String msg) {
                                            LogUtils.d("code="+code+";msg="+msg);
                                            ShowToast.show(activity,activity.getString(R.string.toast_8));
                                        }
                                    });
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }
}
