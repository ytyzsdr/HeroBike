package com.danchexia.bikehero.batterymain;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.AppParamController;
import com.danchexia.bikehero.api.api_destribut.BatteryController;
import com.danchexia.bikehero.api.api_destribut.BicycleController;
import com.danchexia.bikehero.api.api_destribut.MessageCenterController;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.main.advnotify.AdvNotifyDialog;
import com.danchexia.bikehero.main.bean.BatteryListBean;
import com.danchexia.bikehero.main.bean.InvateAndShateBean;
import com.danchexia.bikehero.main.bean.MessageHomeBean;
import com.danchexia.bikehero.main.bean.NearByPark;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/9/5.
 * description:
 */

public class BatteryMainPresenter extends BasePresenter<BatteryMainView> {
    private BatteryMainActivity activity;
    BatteryController batteryController = APIControllerFactory.getAllBattery();
    TripController tripController = APIControllerFactory.reserve();
    BicycleController bicycleControllerForPark = APIControllerFactory.getALLPark();
    AppParamController appParamController = APIControllerFactory.getSystemParams();
    MessageCenterController messageCenterController = APIControllerFactory.getMessag();
    public BatteryMainPresenter(BatteryMainActivity activity) {
        this.activity = activity;
    }
    //获取所有电池
    public void getAllBattery(Double lon, Double lat, Integer distance){
        addSubscription(batteryController.getAllbattery(lon,lat,distance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BatteryListBean>() {
                    @Override
                    public void call(BatteryListBean batteryListBean) {
                        if (batteryListBean.getError_code() == 0) {
                            activity.setBatteryMarks(batteryListBean);
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
     * 用户当前进行中的状态
     */
    public void onGiongInfo() {
        addSubscription(tripController.onGoingInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OnGoingInfoBean>() {
                    @Override
                    public void call(OnGoingInfoBean onGoingInfoBean) {
                        if (onGoingInfoBean.getError_code() == 0) {
                            activity.setMyStatus(onGoingInfoBean);
                        } else {
                            showErrorNone(onGoingInfoBean, activity);
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }
    //获取附近的停车点
    public void getNearByPark(){
        addSubscription(
                bicycleControllerForPark.getLatePark(MyApplication.myLongtitude,MyApplication.myLatitude)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<NearByPark>() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
                            @Override
                            public void call(NearByPark nearByPark) {
//                                activity.setNearParkStatus(nearByPark);
                            }
                        },getErrorAction(new OnHttpListener() {
                            @Override
                            public void onResult(BaseBean bean) {
                                showErrorNone(bean, activity);
                            }
                        }))
        );
    }
    public void endTrip(){
        addSubscription(
                tripController.endTrip(MyApplication.myLongtitude,MyApplication.myLatitude,"gprs")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<OnGoing_TripBO>() {
                            @Override
                            public void call(OnGoing_TripBO bean) {
                                if (bean.getError_code() == 0) {
                                    activity.setEndTripSuccess(bean);
                                } else {
//                                    if (isShow && bean.getError_code() != 407) {
//                                        showErrorNone(bean, activity);
//                                    }
                                    if (bean.getError_code() == 407){
//                                        PreferencesUtils.putBoolean(activity,"INPARKLOCATION_BLUE",false);
//                                        activity.notInParkContent();
                                    }
//                                    activity.setEndTripFailed();
                                }
                            }
                        }, getErrorAction(new OnHttpListener() {
                            @Override
                            public void onResult(BaseBean bean) {
                                showErrorNone(bean, activity);
                            }
                        }))
        );
    }
    /**
     * 获取邀请和分享的参数
     */
    public void getInvateAndShareParams() {
        addSubscription(appParamController.getInvateAndShareParams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<InvateAndShateBean>() {
                    @Override
                    public void call(InvateAndShateBean invateAndShateBean) {
                        if (invateAndShateBean.getError_code() == 0) {
                            MyUtils.savaInvateAndShareData(invateAndShateBean);
                        } else {
                            showErrorNone(invateAndShateBean, activity);
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }
    public void getHomeMessage(){
        Long timeTamp = PreferencesUtils.getLong(MyApplication.appContext,"ADV_TIME_TANMP");
        addSubscription(
                messageCenterController.findHomeMessage(timeTamp)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<MessageHomeBean>() {
                            @Override
                            public void call(MessageHomeBean messageHomeBean) {
                                if (messageHomeBean != null && messageHomeBean.getError_code() ==0 &&
                                        !TextUtils.isEmpty(messageHomeBean.getAdCover())) {
                                    PreferencesUtils.putLong(MyApplication.appContext,"ADV_TIME_TANMP",messageHomeBean.getSendTime().getTime());
                                    AdvNotifyDialog dialog = new AdvNotifyDialog(activity, messageHomeBean);
                                    dialog.show();
                                }else {

                                }
                            }
                        },getErrorAction(new OnHttpListener() {
                            @Override
                            public void onResult(BaseBean bean) {
//                        showErrorNone(bean, activity);
                            }
                        }))
        );
    }
}
