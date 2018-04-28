package com.danchexia.bikehero.main.rideover;

import android.widget.Toast;

import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.api_destribut.MoneyController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.bean.UserAmount;
import com.danchexia.bikehero.pay.OnPayListener;
import com.danchexia.bikehero.pay.PayAgent;
import com.danchexia.bikehero.pay.bean.PayDetails;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.main.recharge.IRechargeView;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.colours.client.model.WeiXinPaymetBO;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.ShowToast;

import static com.danchexia.bikehero.app.MyApplication.wxApi;

/**
 * Created by farley on 17/5/18.
 * description:
 */

public class RideOverPresenter extends BasePresenter<IRechargeView> {
    private RideOverActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    TripController tripController = APIControllerFactory.ridePay();
    private MoneyController moneyController = APIControllerFactory.getMoneyController();
    public RideOverPresenter(RideOverActivity rideOverActivity) {
        super();
        activity = rideOverActivity;
    }

    /**
     * 骑行结束支付
     */
    public void ridePayNo(Long tid,final String paymentMark) {
        addSubscription(tripController.ridePayNo(tid,paymentMark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PayDetails>() {
                    @Override
                    public void call(PayDetails payDetails) {
                        if (payDetails != null && payDetails.getError_code() == 0) {
                            if (MyUtils.PAY_TYPE_ALIPAY.contentEquals(paymentMark)) {
                                PayAgent.getInstance().onAliPay(activity, payDetails.getAlipaPpaySignature(),
                                        new OnPayListener() {
                                            @Override
                                            public void onPaySuccess() {
                                                getNotPayTrip();
                                                activity.stopService();
                                            }

                                            @Override
                                            public void onPayFail(String code, String msg) {
                                                LogUtils.d("code=" + code + ";msg=" + msg);
                                                getNotPayTrip();
                                            }
                                        });
                            } else if (MyUtils.PAY_TYPE_WXPAY.contentEquals(paymentMark)) {

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
                                                getNotPayTrip();
                                                activity.stopService();
                                            }

                                            @Override
                                            public void onPayFail(String code, String msg) {
                                                LogUtils.d("code=" + code + ";msg=" + msg);
                                                getNotPayTrip();
                                            }
                                        });
                            } else {
                                LogUtils.d("代金券支付成功");
                                ShowToast.show(activity, activity.getString(R.string.toast_7));
                                activity.stopService();
                                activity.finish();
                            }

                        }else if (payDetails == null){
                            ShowToast.show(activity, activity.getString(R.string.toast_7));
                            activity.stopService();
                            activity.finish();
                        }else{
                            showErrorNone(payDetails,activity);
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }

    /**
     * 骑行结束支付
     */
    public void ridePay(Long tid, Long cid, final String paymentMark) {
        addSubscription(tripController.ridePay(tid, cid, paymentMark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PayDetails>() {
                    @Override
                    public void call(PayDetails payDetails) {
                        if (payDetails != null && payDetails.getError_code() == 0) {
                            if (MyUtils.PAY_TYPE_ALIPAY.contentEquals(paymentMark)) {
                                PayAgent.getInstance().onAliPay(activity, payDetails.getAlipaPpaySignature(),
                                        new OnPayListener() {
                                            @Override
                                            public void onPaySuccess() {
                                                getNotPayTrip();
                                                activity.stopService();
                                            }

                                            @Override
                                            public void onPayFail(String code, String msg) {
                                                LogUtils.d("code=" + code + ";msg=" + msg);
                                                getNotPayTrip();
                                            }
                                        });
                            } else if (MyUtils.PAY_TYPE_WXPAY.contentEquals(paymentMark)) {

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
                                                getNotPayTrip();
                                                activity.stopService();
                                            }

                                            @Override
                                            public void onPayFail(String code, String msg) {
                                                LogUtils.d("code=" + code + ";msg=" + msg);
                                                getNotPayTrip();
                                            }
                                        });
                            } else {
                                LogUtils.d("代金券支付成功");
                                ShowToast.show(activity, activity.getString(R.string.toast_7));
                                activity.stopService();
                                activity.finish();
                            }

                        }else if (payDetails == null){
                            ShowToast.show(activity, activity.getString(R.string.toast_7));
                            activity.stopService();
                            activity.finish();
                        }else{
                            showErrorNone(payDetails,activity);
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }

    /**
     * 获取未支付的行程
     */
    public void getNotPayTrip() {
        addSubscription(tripController.getNotPayTrip()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OnGoing_TripBO>() {
                    @Override
                    public void call(OnGoing_TripBO notPayRideBean) {
                        if (notPayRideBean != null) {
                            if (notPayRideBean.getError_code() == 0) {
                                activity.setData(notPayRideBean);
                            } else {
                                showErrorNone(notPayRideBean, activity);
                            }
                        } else {
                            Long myTripId = PreferencesUtils.getLong(MyApplication.appContext, Config.USERTRIPID, 0);
                            profileUsing(myTripId);
//                            activity.finish();
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }

    /**
     * 支付结果
     */
    private void resultPay() {
        StanderdDialog dialog = new StanderdDialog(activity, activity.getString(R.string.toast_10),
                activity.getString(R.string.toast_11), activity.getString(R.string.toast_12),
                activity.getString(R.string.toast_13)
                , new StanderdDialog.OnDialogClickListener() {
            @Override
            public void doAnyClick() {
                resultPay();
            }

            @Override
            public void doMainClick() {
                resultPay();
            }
        });
        dialog.show();
    }
    //获取行程详情tid
    public void profileUsing(Long tid){
        addSubscription(tripController.profileUsing(tid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OnGoing_TripBO>() {
                    @Override
                    public void call(OnGoing_TripBO onGoing_tripBO) {
                        if (onGoing_tripBO.getError_code() == 0){
                            activity.getTripDetail(onGoing_tripBO);
                        }else{
                            showErrorNone(onGoing_tripBO, activity);
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }
    //获取余额
    public void getMyAmount(){
        addSubscription(
                moneyController.getMoney()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<UserAmount>() {
                            @Override
                            public void call(UserAmount bean) {
                                if (bean.getError_code() == 0){
                                    activity.setMoneyData(bean);
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
     * 获取个人资料
     */
    public void getMyData() {
        addSubscription(userController.getPersonalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PersonalBean>() {
                    @Override
                    public void call(PersonalBean personalBean) {
                        if (personalBean.getError_code() == 0) {
                            MyUtils.savaPesonData(personalBean);
                            activity.openMemberTop();
                        } else {
                            showErrorNone(personalBean, activity);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        if (throwable != null) {
                            if (throwable.getMessage().contains("OAuthProblemException")) {
                                PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_IS_BIND", "");
                                PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA", "");
                            }
                        }
                    }
                }));
    }
}
