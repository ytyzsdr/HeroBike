package com.danchexia.bikehero.batteryservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.service.RideStatusService;
import com.danchexia.bikehero.service.TripChangeListener;
import com.danchexia.bikehero.service.bean.MyPoint;
import com.danchexia.bikehero.service.bean.UpPointData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.tools.utils.LogUtils;

/**
 * Created by farley on 17/9/6.
 * description:
 */

public class BatteryService extends Service implements BDLocationListener {
    private MyBinder mBinder = new MyBinder();
    private MyThread myThread;
    private boolean flag = true;
    private Integer time = 5000;

    public class MyBinder extends Binder {
        public BatteryService getService() {
            return BatteryService.this;
        }
        public void startCheckStatus() {
            myThread = new MyThread();
            if (!myThread.isAlive()) {
                myThread.start();
                flag = true;
            }
        }

        public void stopCheckStatus() {
            if (myThread != null) {
                flag = false;
            }
        }

    }
    TripController tripController = APIControllerFactory.reserve();
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
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
                                LogUtils.d("收到，开始轮询");
                                tripChangeListener.onChange(onGoingInfoBean);//给监控对象
                            }} , getErrorAction(new OnHttpListener() {
                                @Override
                                public void onResult(BaseBean bean) {
//                                showErrorNone(bean, getApplicationContext());
                                }
                            }));
                try {
                    // 每个time秒向服务器发送一次请求
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private PowerManager.WakeLock wakeLock;
    private LocationClient mLocClient;// 定位相关
    List<UpPointData> beenList = new ArrayList<>();
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
    private void initLocation() {
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(this);
        LocationClientOption optiona = new LocationClientOption();
        optiona.setOpenGps(true); // 打开gps
        optiona.setCoorType("bd09ll"); // 设置坐标类型
        optiona.setScanSpan(time);
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
        MyApplication.myLatitude = location.getLatitude();//每次定位都保存下自己的位置
        MyApplication.myLongtitude = location.getLongitude();
        MyPoint myPoint = new MyPoint(MyApplication.myLongtitude, MyApplication.myLatitude);
        UpPointData data = new UpPointData(new Date(), myPoint);
        beenList.add(data);
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @Override
    public void onDestroy() {
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
    public Action1<Throwable> getErrorAction(final OnHttpListener onHttpListener) {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                LogUtils.d("================throwable================" + throwable);
                BaseBean bean = new BaseBean("系统错误", -1);
                if (throwable != null) {
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
    private TripChangeListener tripChangeListener;
    public void setTripChangeListener(TripChangeListener tripChangeListener){
        this.tripChangeListener = tripChangeListener;
    }
}
