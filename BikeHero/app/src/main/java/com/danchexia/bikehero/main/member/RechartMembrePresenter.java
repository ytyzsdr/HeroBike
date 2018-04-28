package com.danchexia.bikehero.main.member;

import android.widget.Toast;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.main.bean.VipListBean;
import com.danchexia.bikehero.pay.OnPayListener;
import com.danchexia.bikehero.pay.PayAgent;
import com.danchexia.bikehero.pay.bean.PayDetails;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.colours.client.model.WeiXinPaymetBO;
import vc.thinker.mvp.MvpView;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.ShowToast;

import static android.app.Activity.RESULT_OK;
import static com.danchexia.bikehero.app.MyApplication.wxApi;

/**
 * Created by farley on 17/8/17.
 * description:
 */

public class RechartMembrePresenter extends BasePresenter<MvpView> {
    private RechartMemberActivity activity;
    private MemberController memberController = APIControllerFactory.getMemberApi();
    public RechartMembrePresenter(RechartMemberActivity activity) {
        this.activity = activity;
    }
    public void getMemberData(){
        addSubscription(
                memberController.getVipList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<VipListBean>() {
                    @Override
                    public void call(VipListBean vipListBean) {
                        if (vipListBean.getError_code() == 0){
                            activity.getDataListSuccess(vipListBean);
                        }else{
                            activity.getDataListFailed();
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
    public void recharge(long cardId,final String payTpye){
        addSubscription(memberController.buyVip(cardId,payTpye)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PayDetails>() {
                    @Override
                    public void call(PayDetails payDetails) {
                        if (MyUtils.PAY_TYPE_ALIPAY.contentEquals(payTpye)) {
                            PayAgent.getInstance().onAliPay(activity, payDetails.getAlipaPpaySignature(),
                                    new OnPayListener() {
                                        @Override
                                        public void onPaySuccess() {
                                            ShowToast.show(activity, activity.getString(R.string.toast_7));
                                            activity.setResult(RESULT_OK);
                                            activity.finish();
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
                                            activity.setResult(RESULT_OK);
                                            activity.finish();
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
