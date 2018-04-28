package com.danchexia.bikehero.main.wallet;

import android.widget.Toast;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.main.recharge.bean.ChergeBean;
import com.danchexia.bikehero.pay.OnPayListener;
import com.danchexia.bikehero.pay.PayAgent;
import com.danchexia.bikehero.pay.bean.PayDetails;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.main.wallet.bean.WalletBean;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.colours.client.model.WeiXinPaymetBO;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.ShowToast;

import static com.danchexia.bikehero.app.MyApplication.wxApi;

/**
 * Created by farley on 17/5/18.
 * description:
 */

public class WalletPresenter extends BasePresenter<IWalletView> {
    private WalletActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    public WalletPresenter(WalletActivity walletActivity) {
        super();
        activity = walletActivity;
    }

    /**
     * 获取我的钱包记录
     * @param time
     */
    public void getMyWallet(final Long time){
        addSubscription(userController.getMyWallet(time)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<WalletBean>() {
            @Override
            public void call(WalletBean walletBean) {
                if (walletBean.getError_code() == 0){
                   activity.loadMore(walletBean,time);
                }else{
                    showErrorNone(walletBean,activity);
                }
            }
        },getErrorAction(new OnHttpListener() {
            @Override
            public void onResult(BaseBean bean) {
                activity.hideLoading();
                showErrorNone(bean,activity);
            }
        })));
    }
    /**
     * 获取需要交纳的押金金额
     */
    public void getCherge(){
        addSubscription(userController.getCherge()
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<ChergeBean>() {
                    @Override
                    public void call(ChergeBean chergeBean) {
                        if (chergeBean.getError_code() == 0){
                            activity.setCherge(chergeBean);
                        }else{
                            showErrorNone(chergeBean,activity);
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean,activity);
                    }
                })));
    }
    /**
     * 退押金
     */
    public void refundDeposit(){
        addSubscription(userController.repounDisposit()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<BaseBean>() {
            @Override
            public void call(BaseBean bean) {
                if (bean.getError_code() == 0){
                    ShowToast.show(activity,activity.getString(R.string.toast_20));
                    getMyData();
                    getMyWallet(null);
                }else{
                    showErrorNone(bean,activity);
                }
            }
        },getErrorAction(new OnHttpListener() {
            @Override
            public void onResult(BaseBean bean) {
                showErrorNone(bean,activity);
            }
        })));
    }
    /**
     * 获取个人资料
     */
    public void getMyData(){
        addSubscription(userController.getPersonalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PersonalBean>() {
                    @Override
                    public void call(PersonalBean personalBean) {
                        if (personalBean.getError_code() == 0){
                            MyUtils.savaPesonData(personalBean);
                            activity.initData(personalBean);
                        }else{
                            showErrorNone(personalBean,activity);
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean,activity);
                    }
                })));
    }
    /**
     * 充值
     *
     */
    public void recharge(final String payTpye){
        addSubscription(userController.recharge(payTpye)
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
                                            getMyData();
                                            getMyWallet(null);
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
                                            getMyData();
                                            getMyWallet(null);
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
