package com.danchexia.bikehero.main;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.AppParamController;
import com.danchexia.bikehero.api.api_destribut.BicycleController;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.api.api_destribut.MessageCenterController;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.bluetooth.BluetoothUtils;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.advnotify.AdvNotifyDialog;
import com.danchexia.bikehero.main.bean.AdvImages;
import com.danchexia.bikehero.main.bean.BicycleBean;
import com.danchexia.bikehero.main.bean.BicycleData;
import com.danchexia.bikehero.main.bean.ImagesBean;
import com.danchexia.bikehero.main.bean.InvateAndShateBean;
import com.danchexia.bikehero.main.bean.MessageHomeBean;
import com.danchexia.bikehero.main.bean.NearByPark;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.bean.ParkListBean;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;
import com.danchexia.bikehero.utils.SaveImgeUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.ShowToast;

/**
 * Created by farley on 17/5/15.
 * description:首页peresenter
 */

public class MainPresenter extends BasePresenter<IMainView> {
    private MainActivity activity;

    public MainPresenter(MainActivity mainActivity) {
        super();
        activity = mainActivity;
    }

    BicycleController bicycleController = APIControllerFactory.getAllBikeData();
    BicycleController bicycleControllerForPark = APIControllerFactory.getALLPark();

    AppParamController appParamController = APIControllerFactory.getSystemParams();

    TripController tripController = APIControllerFactory.reserve();

    MemberController userController = APIControllerFactory.getMemberApi();

    MessageCenterController messageCenterController = APIControllerFactory.getMessag();

    /**
     * 获取所有车辆
     *
     * @param var1
     * @param var2
     * @param str
     */
    public void getAllBicycleData(Double var1, Double var2, Integer str) {
        addSubscription(bicycleController.getAllBike(var1, var2, str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BicycleBean>() {
                    @Override
                    public void call(BicycleBean bicycleBean) {
                        if (bicycleBean.getError_code() == 0) {
                            activity.addMarkers(bicycleBean);//给地图添加车辆图标
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }

    public void getAllParkList(Double lon, Double lat, Integer distance) {
        addSubscription(bicycleControllerForPark.getParkList(lon, lat, distance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ParkListBean>() {
                    @Override
                    public void call(ParkListBean bean) {
                        if (bean.getError_code() == 0) {
                            LogUtils.d("结果=" + bean);
                            activity.addParkMarkers(bean);
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
     * 获取系统配置参数
     */
    public void getSystemParams() {
        addSubscription(appParamController.getSystemParams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SystemParamsBean>() {
                    @Override
                    public void call(SystemParamsBean systemParamsBean) {
                        if (systemParamsBean.getError_code() == 0) {
                            MyUtils.savaSystemData(systemParamsBean);
                            activity.setSystemData(systemParamsBean);
                            if (systemParamsBean.getOpenAd()){
                                getAdvImages();
                            }else{
                                MyUtils.deleteMyFiles();
                            }
                        } else {
                            showErrorNone(systemParamsBean, activity);
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

    /**
     * 预约
     *
     * @param code
     */
    public void reserve(String code) {
        addSubscription(tripController.reserve(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean bean) {
                        if (bean.getError_code() == 0) {
                            activity.setRecervationGone();
                            ShowToast.show(activity, activity.getString(R.string.toast_27));
                            onGiongInfo();
                        } else {
                            showErrorNone(bean, activity);
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
    /**
     * 用户当前进行中的状态-----行程中开锁获取状态
     */
    public void onGiongInfoForTriping() {
        addSubscription(tripController.onGoingInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OnGoingInfoBean>() {
                    @Override
                    public void call(OnGoingInfoBean onGoingInfoBean) {
                        if (onGoingInfoBean.getError_code() == 0) {
                            activity.setForTripingMyStatus(onGoingInfoBean);
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
    /**
     * 寻车龄
     */
    public void ring() {
        addSubscription(tripController.ring()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean bean) {
                        if (bean.getError_code() == 0) {
                            ShowToast.show(activity, activity.getString(R.string.ring));
                        } else {
                            showErrorNone(bean, activity);
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
     * 取消预约
     */
    public void canselRecervation() {
        addSubscription(tripController.canselRecervation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean bean) {
                        if (bean.getError_code() == 0) {
                            ShowToast.show(activity, activity.getString(R.string.toast_28));
                            onGiongInfo();
                        } else {
                            showErrorNone(bean, activity);
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
                            if (personalBean != null && !personalBean.getAuthStatus().contentEquals("2")) {
                                switch (personalBean.getAuthStep()) {
                                    case 2:
                                        MyApplication.setIsIdenty(2);
                                        break;
                                    case 3:
                                        MyApplication.setIsIdenty(3);
                                        break;
                                    default:
                                        break;
                                }
                            }
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
    public void tripUnlock(){
        addSubscription(
                tripController.tripUnlock()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<BicycleData>() {
                            @Override
                            public void call(BicycleData bean) {
                                if (bean == null){
                                    activity.setUnLocakSuccess();
                                }else {
                                    activity.setUnlockFailed();
                                    showErrorNone(bean, activity);
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
    public void refreshLocation(){
        addSubscription(
                tripController.refreshLocation()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<BaseBean>() {
                            @Override
                            public void call(BaseBean bean) {
                                if (bean.getError_code() == 0) {
                                    activity.setStartRefreshLocation();
                                } else {
                                    showErrorNone(bean, activity);
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
    public void endTrip(final boolean isShow,String  endType){
        addSubscription(
                tripController.endTrip(MyApplication.myLongtitude,MyApplication.myLatitude,endType)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<OnGoing_TripBO>() {
                            @Override
                            public void call(OnGoing_TripBO bean) {
                                if (bean.getError_code() == 0) {
                                    Config.HAVETRIPING = false;
                                    activity.setEndTripSuccess(bean);
                                } else {
                                    if (isShow && bean.getError_code() != 407) {
                                    showErrorNone(bean, activity);
                                    }
                                    if (bean.getError_code() == 407){
//                                        PreferencesUtils.putBoolean(activity,"INPARKLOCATION_BLUE",false);
//                                        activity.notInParkContent();
                                    }
                                    activity.setEndTripFailed();
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
    public void endTripForBlue(final boolean isShow,String  endType){
        addSubscription(
                tripController.endTrip(MyApplication.myLongtitude,MyApplication.myLatitude,endType)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<OnGoing_TripBO>() {
                            @Override
                            public void call(OnGoing_TripBO bean) {
                                if (bean.getError_code() == 0) {
                                    Config.HAVETRIPING = false;
                                    PreferencesUtils.putBoolean(activity,"INPARKLOCATION_BLUE",true);
                                    activity.setEndTripBlueSuccess(bean);
                                    if (BluetoothUtils.mBluetoothLeService != null) {
                                        //断开蓝牙连接
                                        BluetoothUtils.mBluetoothLeService.disconnect();
                                        BluetoothUtils.mBluetoothLeService.close();
                                        activity.unbindService(BluetoothUtils.mServiceConnection);
                                        BluetoothUtils.mBluetoothLeService = null;
                                    }
                                } else {
                                    if (isShow && bean.getError_code() != 407) {
                                        showErrorNone(bean, activity);
                                    }
                                    if (bean.getError_code() == 407){
                                        PreferencesUtils.putBoolean(activity,"INPARKLOCATION_BLUE",false);
                                        activity.notInParkContent();
                                    }
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
    private void getAdvImages(){
        addSubscription(
                appParamController.getAdvImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AdvImages>() {
                    @Override
                    public void call(AdvImages advImages) {
                        if (advImages.getError_code() == 0){
                            if (!Config.ADV_IMAGE_MD5.contains(advImages.getMd5()) || !MyUtils.fileIsHave().exists()){
                                MyUtils.deleteMyFiles();
                                MyUtils.savaAdvanceData(advImages);
                                Config.setAdvImageMd5(advImages.getMd5());
                                for (ImagesBean image:advImages.getImgList()) {
                                    LogUtils.d("下载图片");
                                    new Thread(new SaveImgeUtils(activity, image.getInitImg())).start();
                                }
                            }
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
    public void getNearByPark(){
        addSubscription(
                bicycleControllerForPark.getLatePark(MyApplication.myLongtitude,MyApplication.myLatitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NearByPark>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void call(NearByPark nearByPark) {
                        activity.setNearParkStatus(nearByPark);
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                }))
        );
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
