package com.danchexia.bikehero.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.MainActivity;
import com.danchexia.bikehero.main.MapUtils.BaiduMapHelper;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.toplayout.UseingFragment;
import com.danchexia.bikehero.service.bean.MyPoint;
import com.danchexia.bikehero.service.bean.UpPointData;
import com.danchexia.bikehero.utils.MyUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/5/27.
 * description:骑行状态的轮询
 */

public class RideStatusService extends Service implements BDLocationListener {
    TripController tripController = APIControllerFactory.reserve();
    private MyThread myThread;
    private MyBinder mBinder = new MyBinder();
    private boolean flag = false;
    private Integer time = 5000;
    private LocationClient mLocClient;// 定位相关
    List<UpPointData> beenList = new ArrayList<>();
    private PowerManager.WakeLock wakeLock;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        beenList.clear();
        //定位
        initLocation();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, RideStatusService.class.getName());
        wakeLock.acquire();
    }

    @Override
    public void onDestroy() {
        LogUtils.d("状态服务被干掉");
        flag = false;
        if (myThread != null) {
            myThread = null;
        }
        if (mLocClient != null) {
            mLocClient.stop();
            mLocClient = null;
        }
        if (beenList != null) {
            beenList = null;
        }
        if (wakeLock != null) {
            wakeLock.release();
            wakeLock = null;
        }
        super.onDestroy();
    }

    private void initLocation() {
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(this);
        LocationClientOption optiona = new LocationClientOption();
        optiona.setOpenGps(true); // 打开gps
        optiona.setCoorType("bd09ll"); // 设置坐标类型
        optiona.setScanSpan(5000);
        optiona.setIsNeedAddress(true);
        optiona.setLocationNotify(true);
        optiona.setOpenGps(true);
        optiona.setNeedDeviceDirect(true);
        mLocClient.setLocOption(optiona);
        mLocClient.start();
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null) {
            return;
        }
        Double distance = BaiduMapHelper.getMinDistance(MyApplication.myLongtitude,MyApplication.myLatitude,
                location.getLongitude(),location.getLatitude());
        if (distance > 15) {//如果移动距离大于15的时候 再去上报位置
            MyApplication.myLatitude = location.getLatitude();//每次定位都保存下自己的位置
            MyApplication.myLongtitude = location.getLongitude();
            MyPoint myPoint = new MyPoint(MyApplication.myLongtitude, MyApplication.myLatitude);
            UpPointData data = new UpPointData(new Date(), myPoint);
            beenList.add(data);
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    public class MyBinder extends Binder {
        /**
         * 获取当前Service的实例
         *
         * @return
         */
        public RideStatusService getService() {
            return RideStatusService.this;
        }

        public void startCheckStatus() {
            LogUtils.d("==============flag========"+flag);
            if (!flag) {
                myThread = new MyThread();
                if (!myThread.isAlive()) {
                    flag = true;
                    myThread.start();
                }
            }
        }

        public void stopCheckStatus() {
            if (myThread != null) {
                flag = false;
                LogUtils.d("终止进程");
                myThread.interrupt();
            }
        }

    }
    private boolean vipToTripRideover = false;//如果是会员 在问题反馈之后 后台给结束了以后 就进入支付界面
    private class MyThread extends Thread {
        @Override
        public void run() {
            while (flag) {
                tripController.onGoingInfo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<OnGoingInfoBean>() {
                            @Override
                            public void call(OnGoingInfoBean onGoingInfoBean) {
                                if (onGoingInfoBean.getError_code() == 0 && onGoingInfoBean.getOnGoing_tripBO() !=null) {
                                    if (tripChangeListener != null) {
                                        OnGoing_TripBO data = onGoingInfoBean.getOnGoing_tripBO();
                                        MyUtils.savaTripData(data);
                                        if (data.getBicycle() != null) {//如果是蓝牙锁 那么去保存下mac地址
                                            PreferencesUtils.putString(MyApplication.appContext, Config.TRIPINGBLUETOOTHMACADDRESS, data.getBicycle().getLockMacAddress());
                                        }else{
                                            PreferencesUtils.putString(MyApplication.appContext, Config.TRIPINGBLUETOOTHMACADDRESS, "");
                                        }
                                        tripChangeListener.onChange(onGoingInfoBean);//给监控对象

                                    }
                                    if (onGoingInfoBean.getOnGoing_tripBO() != null && onGoingInfoBean.getOnGoing_tripBO().getStatus() == MyUtils.RIDE_STATUS_4) {
                                        LogUtils.d("status==service===40");
                                        Intent it = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
                                        it.putExtra("NEEDTOPAY", true);
                                        it.putExtra("SHOWORHIDEASKFRAGMENT", false);
                                        getApplicationContext().sendBroadcast(it);
//                                        Intent bindPhone = new Intent(getApplicationContext(), RideOverActivity.class);
//                                        bindPhone.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        getApplicationContext().startActivity(bindPhone);
                                        MyApplication.toPayStroke = false;
//                                        interrupt();
                                    } else if (onGoingInfoBean.getOnGoing_tripBO() != null &&
                                            onGoingInfoBean.getOnGoing_tripBO().getStatus() == MyUtils.RIDE_STATUS_3) {//奇行种
                                        LogUtils.d("status==service===30");

                                        tripController.upload_track_loc(beenList)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Action1<BaseBean>() {
                                                    @Override
                                                    public void call(BaseBean bean) {
                                                        LogUtils.d("上传位置成功");
                                                        beenList.clear();
                                                    }
                                                }, getErrorAction(new OnHttpListener() {
                                                    @Override
                                                    public void onResult(BaseBean bean) {
//                                                        showErrorNone(bean, getApplicationContext());
                                                    }
                                                }));

                                        OnGoing_TripBO data = onGoingInfoBean.getOnGoing_tripBO();
//                                        MyUtils.savaTripData(data);
                                        if (data.getDoingFeedbacks() != null && data.getDoingFeedbacks().size() > 0) {
                                            vipToTripRideover = true;
                                            return;
                                        }else{
                                            vipToTripRideover = false;
                                        }

                                        PreferencesUtils.putLong(MyApplication.appContext, Config.USERTRIPID, data.getId());

                                        if (data.getStopType().contains("fixed_point")) {
                                            if (data.getLockOnoff()) {
                                                if (!UseingFragment.isFrount) {
                                                    if (data.getBicycle() != null && data.getBicycle().getOpenType() ==2){

                                                    }else {
                                                        Intent it = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
                                                        it.putExtra("LOCKISOPENED", true);
                                                        getApplicationContext().sendBroadcast(it);
                                                    }
                                                }
                                            } else {
                                                if (isParkLocation) {//控制是否走定点停车的逻辑
                                                    if (!isRefreshFragment) {
                                                        isParkLocation = false;
                                                    }
                                                    if (data.getBicycle().getOpenType() == 2 || data.getBicycle().getOpenType() == 4){
                                                        return;
                                                    }
                                                    if (data.getAutoEnd()) {
                                                        int turnTime = data.getLockLocationAutoEndEffectiveTime() - (int) ((System.currentTimeMillis() - data.getLastLockTime().getTime()) / 1000);
                                                        LogUtils.d("turnTime=" + turnTime);
                                                        Intent it = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
                                                        it.putExtra("LockIsClosed", true);
                                                        it.putExtra("TURNTIME", turnTime);
                                                        it.putExtra("TRIPSTATUS", data.getStatus());
                                                        getApplicationContext().sendBroadcast(it);
                                                    } else {//超过时间以后 直接跳转停车失败界面
                                                        Double myPrice = data.getPrice();
                                                        String sysCode = data.getSysCode();
                                                        Long refreshTime = 0L;
                                                        if (data.getLastLockLocationTime() != null) {
                                                            refreshTime = data.getLastLockLocationTime().getTime();
                                                        }

                                                        boolean isInPark = data.getInTheParkingLot();
                                                        Intent it = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
                                                        it.putExtra("PARKFAILED", true);
                                                        it.putExtra("ISINPARK", isInPark);
                                                        it.putExtra("myPrice", myPrice);
                                                        it.putExtra("sysCode", sysCode);
                                                        it.putExtra("refreshTime", refreshTime);
                                                        it.putExtra("OpenType", data.getBicycle().getOpenType());
                                                        getApplicationContext().sendBroadcast(it);
                                                    }
                                                }
                                            }
                                        }
                                        if (UseingFragment.isFrount) {
                                            Intent using = new Intent(UseingFragment.MY_MESSAGE_RECEIVED_ACTION);
                                            using.putExtra("DISTANCE", onGoingInfoBean.getOnGoing_tripBO().getDistance());
                                            LogUtils.d("shichang======="+onGoingInfoBean.getOnGoing_tripBO().getBeginTime().getTime());
                                            using.putExtra("TIME", onGoingInfoBean.getOnGoing_tripBO().getBeginTime().getTime());
                                            using.putExtra("KCAL", onGoingInfoBean.getOnGoing_tripBO().getCalorie());
                                            using.putExtra("VIPMONEY",onGoingInfoBean.getOnGoing_tripBO().getVipPrice());
                                            using.putExtra("MONEY", onGoingInfoBean.getOnGoing_tripBO().getPrice());
                                            using.putExtra("OPENTYPE", data.getBicycle().getOpenType());
                                            LogUtils.d("发送广播");
                                            getApplicationContext().sendBroadcast(using);
                                        }

                                    }


                                    if (onGoingInfoBean.getOnGoing_tripBO() == null) {
                                        LogUtils.d("行程关闭");
//                                        if (!Config.isNeadToPay) {
                                        flag = false;
                                        Intent use = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
                                        use.putExtra("NOTRIP", true);
                                        use.putExtra("SHOWORHIDEASKFRAGMENT", false);
                                        getApplicationContext().sendBroadcast(use);
                                        Intent it = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);

                                    }
                                } else {
                                    flag = false;
//                                    if (vipToTripRideover){
                                        Intent use = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
                                        use.putExtra("NOTRIP", true);
                                        use.putExtra("SHOWORHIDEASKFRAGMENT", false);
                                        getApplicationContext().sendBroadcast(use);
                                        vipToTripRideover = false;
//                                    }
                                }
                            }
                        }, getErrorAction(new OnHttpListener() {
                            @Override
                            public void onResult(BaseBean bean) {
//                                showErrorNone(bean, getApplicationContext());
                            }
                        }));
                try {
                    // 每个10秒向服务器发送一次请求
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Boolean isParkLocation = true;
    private Boolean isRefreshFragment = false;

    public void setMyParkBoolean(boolean isOrNo) {
        isParkLocation = isOrNo;
        isRefreshFragment = isOrNo;
    }

    public Action1<Throwable> getErrorAction(final OnHttpListener onHttpListener) {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                LogUtils.d("================throwable================service=" + throwable);
                BaseBean bean = new BaseBean("系统错误", -1);
                if (throwable != null && throwable.getMessage() != null) {
                    if (throwable.getMessage().contains("OAuthProblemException")) {
                        bean = new BaseBean("登录失效，请重新登录", -10);//登录失效
                    } else if (throwable.getMessage().contains("isConnected failed")) {
                        bean = new BaseBean("服务器连接失败", -20);//服务器链接失败
                    } else if (throwable.getMessage().contains("failed to connect to")) {
                        bean = new BaseBean("网络连接超时", -30);//服务器链接失败
                    } else if (throwable.getMessage().contains("danchexia.vc")) {
                        bean = new BaseBean("网络连接超时", -30);//服务器链接失败
                    } else if (throwable.getMessage().contains("Bad credentials")) {
                        bean = new BaseBean("验证码错误", -35);//验证码错误
                    } else if (throwable.getMessage().contains("HTTP 500 Server Error")) {
                        bean = new BaseBean("服务器出错", -36);
                    } else {
                        bean = new BaseBean(throwable.getMessage(), -1);
                    }
                    onHttpListener.onResult(bean);
                }
            }
        };
    }

    /**
     * 什么都不需要做的
     *
     * @param bean
     * @param activity
     */
    public void showErrorNone(final BaseBean bean, final Context activity) {
//        final StanderdDialog dialog = new StanderdDialog(activity, bean.getResult(), "知道了",
//                new StanderdDialog.OnDialogClickListener() {
//                    @Override
//                    public void doAnyClick() {
//                    }
//
//                    @Override
//                    public void doMainClick() {
//                        if (bean.getError_code() == -10) {
//                            PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_IS_BIND", "");//退出登录要清空token 不能传null
//                            PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA", "");//退出登录要清空个人资料不能传null
//                            ActivityController.startBindPhone(activity);
//                            ControllerActivity.finishAll();
//                        }
//                    }
//                });
//        dialog.show();
//        dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
//        dialog.setCanceledOnTouchOutside(false);
    }
    private TripChangeListener tripChangeListener;
    public void setTripChangeListener(TripChangeListener tripChangeListener){
        this.tripChangeListener = tripChangeListener;
    }
}
