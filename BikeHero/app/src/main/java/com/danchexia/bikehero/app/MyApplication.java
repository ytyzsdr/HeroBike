package com.danchexia.bikehero.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.baidu.mapapi.SDKInitializer;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.jpush.myobserveable.JiPushGetData;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/5/15.
 * description:application
 */

public class MyApplication extends Application implements BootstrapNotifier {
    public static Context appContext;
    public static Boolean isBindPhone = false;
    public static Boolean toPayStroke = true;//第一次进来去支付行程
    public static int isIdenty = 0;
    public static Double myLatitude = 0d;//我的纬度
    public static Double myLongtitude = 0d;//我的经度
    public static String DATA_MESSAGE = "开锁失败";//开锁提示 标记
    public static IWXAPI wxApi;
    private static JiPushGetData jiPushGetData;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        checkBind();
        //API初始化
        Config.init(getApplicationContext());
        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        //sharesdk初始化
        ShareSDK.initSDK(this);
        //注册微信
        wxApi = WXAPIFactory.createWXAPI(this, Config.wxAppId);
        wxApi.registerApp(Config.wxAppId);
//        CrashHandler.getInstance().init(this);
        initJiguang();
        jiPushGetData = new JiPushGetData();
        beaconCreate();
        PgyCrashManager.register(this,Config.APPID);
        initLogger();
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("farley")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    private RegionBootstrap regionBootstrap;
    private BackgroundPowerSaver backgroundPowerSaver;
    private void beaconCreate() {
        BeaconManager beaconManager = org.altbeacon.beacon.BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().clear();
        /**
         * 由于数据位没有数据，所以使用以下传入参数，没有数据位d:25-25
         * */
//        beaconManager.getBeaconParsers().add(new BeaconParser()
//                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
//        Region region = new Region("all-region-beacon", null, null, null);
//        regionBootstrap = new RegionBootstrap(this, region);
//        backgroundPowerSaver = new BackgroundPowerSaver(this);
    }

    public static JiPushGetData getJiPushGetData() {
        return jiPushGetData;
    }

    /**
     * 极光推送的初始化
     */
    private void initJiguang() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
    /**
     * 检测是否绑定了手机
     */
    private void checkBind() {
        if (TextUtils.isEmpty(PreferencesUtils.getString(this,"RADISHSAAS_IS_BIND"))) {
            isBindPhone = false;
        }else {
            isBindPhone = true;
        }
        PersonalBean bean = MyUtils.getPersonData();
        if (bean != null){
            MyApplication.isIdenty = bean.getAuthStep();
        }
    }

    public static Boolean getIsBindPhone() {
        return isBindPhone;
    }

    public static void setIsBindPhone(Boolean isBindPhone) {
        MyApplication.isBindPhone = isBindPhone;
    }

    public static int getIsIdenty() {
        return isIdenty;
    }

    public static void setIsIdenty(int isIdenty) {
        MyApplication.isIdenty = isIdenty;
    }

    @Override
    public void didEnterRegion(Region region) {

    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }
}
