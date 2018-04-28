package com.danchexia.bikehero.main.recharge;

import android.widget.Toast;

import com.danchexia.bikehero.api.api_destribut.MoneyController;
import com.danchexia.bikehero.main.bean.PayDetailBean;
import com.danchexia.bikehero.main.bean.RechartTypeListBean;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.pay.OnPayListener;
import com.danchexia.bikehero.pay.PayAgent;
import com.danchexia.bikehero.pay.bean.PayDetails;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.main.recharge.bean.ChergeBean;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.colours.client.model.WeiXinPaymetBO;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.ShowToast;

import static com.danchexia.bikehero.app.MyApplication.wxApi;


/**
 * Created by farley on 17/5/17.
 * description:
 */

public class RechargePresenter extends BasePresenter<IRechargeView> {
    private RechargeActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    private MoneyController moneyController = APIControllerFactory.getMoneyController();
    public RechargePresenter(RechargeActivity rechargeActivity) {
        super();
        activity = rechargeActivity;
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
    public void recharge(Long money, final String payTpye){
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
                                            ActivityController.startIdentid(activity);
                                            if(Config.isNeedIdent) {//需要不需要认证
                                                MyApplication.setIsIdenty(3);
                                            }else{
                                                MyApplication.setIsIdenty(4);
                                            }
                                            activity.finish();
                                        }

                                        @Override
                                        public void onPayFail(String code, String msg) {
                                            LogUtils.d("code="+code+";msg="+msg);
                                            ShowToast.show(activity, activity.getString(R.string.toast_8));
                                            activity.setEnableSuccess();
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
                                            ActivityController.startIdentid(activity);
                                            if(Config.isNeedIdent) {//需要不需要认证
                                                MyApplication.setIsIdenty(3);
                                            }else{
                                                MyApplication.setIsIdenty(4);
                                            }
                                            activity.finish();
                                        }

                                        @Override
                                        public void onPayFail(String code, String msg) {
                                            LogUtils.d("code="+code+";msg="+msg);
                                            ShowToast.show(activity,activity.getString(R.string.toast_8));
                                            activity.setEnableSuccess();
                                        }
                                    });
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                        activity.setBtnClick();
                    }
                })));
    }


    /**
     * 获取需要交纳的押金金额
     */
/*    public void getCherge(){
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
    }*/

    /**
     * 充值
     *
     */
   /* public void recharge(final String payTpye){
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
                                            ActivityController.startIdentid(activity);
                                            if(Config.isNeedIdent) {//需要不需要认证
                                                MyApplication.setIsIdenty(3);
                                            }else{
                                                MyApplication.setIsIdenty(4);
                                            }
                                            activity.finish();
                                        }

                                        @Override
                                        public void onPayFail(String code, String msg) {
                                            LogUtils.d("code="+code+";msg="+msg);
                                            ShowToast.show(activity, activity.getString(R.string.toast_8));
                                            activity.setEnableSuccess();
                                        }
                                    });
                        }else if (MyUtils.PAY_TYPE_WXPAY.contentEquals(payTpye)) {

                            if (wxApi == null || !wxApi.isWXAppInstalled()) {
                                Toast.makeText(activity, activity.getString(R.string.toast_9), Toast.LENGTH_SHORT).show();
                                activity.setEnableSuccess();
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
                                            ActivityController.startIdentid(activity);
                                            if(Config.isNeedIdent) {//需要不需要认证
                                                MyApplication.setIsIdenty(3);
                                            }else{
                                                MyApplication.setIsIdenty(4);
                                            }
                                            activity.finish();
                                        }

                                        @Override
                                        public void onPayFail(String code, String msg) {
                                            LogUtils.d("code="+code+";msg="+msg);
                                            ShowToast.show(activity, activity.getString(R.string.toast_8));
                                            activity.setEnableSuccess();
                                        }
                                    });
                        }

                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                        activity.setBtnClick();
                    }
                })));
    }*/
}
