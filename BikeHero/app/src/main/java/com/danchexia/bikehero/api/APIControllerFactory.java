package com.danchexia.bikehero.api;


import android.content.Context;
import android.text.TextUtils;

import com.danchexia.bikehero.api.api_destribut.AppParamController;
import com.danchexia.bikehero.api.api_destribut.BatteryController;
import com.danchexia.bikehero.api.api_destribut.BicycleController;
import com.danchexia.bikehero.api.api_destribut.FileController;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.api.api_destribut.MessageCenterController;
import com.danchexia.bikehero.api.api_destribut.MoneyController;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.api.api_destribut.UserController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.Config;

import vc.thinker.colours.client.ApiClient;
import vc.thinker.colours.client.api.AppparamcontrollerApi;
import vc.thinker.colours.client.api.BatterycontrollerApi;
import vc.thinker.colours.client.api.BicyclecontrollerApi;
import vc.thinker.colours.client.api.FileuploadcontrollerApi;
import vc.thinker.colours.client.api.MembercontrollerApi;
import vc.thinker.colours.client.api.MessagecontrollerApi;
import vc.thinker.colours.client.api.MoneycontrollerApi;
import vc.thinker.colours.client.api.TripcontrollerApi;
import vc.thinker.colours.client.api.UsercontrollerApi;
import vc.thinker.tools.utils.PreferencesUtils;

public class APIControllerFactory {

    private static ApiClient mOauth2ApiClient = new ApiClient("oauth2-password");

    private static ApiClient mClientApiClient = new ApiClient("oauth2-clientcredentials", Config.appClientId, Config.appClientSecret);

    private static Context mContext = MyApplication.appContext;

    private APIControllerFactory() {
    }

    ;

    /**
     * 获取所有车辆
     *
     * @return
     */
    public static BicycleController getAllBikeData() {
        return new BicycleController(mClientApiClient.createService(BicyclecontrollerApi.class));
    }

    public static BatteryController getAllBattery() {
        String token = PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND");
        if (TextUtils.isEmpty(token)) {
            return new BatteryController(mClientApiClient.createService(BatterycontrollerApi.class));
        }else{
            mOauth2ApiClient.setAccessToken(token);
            return new BatteryController(mOauth2ApiClient.createService(BatterycontrollerApi.class));
        }
    }

    /**
     * 开锁
     */
    public static BicycleController getALLPark() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new BicycleController(mOauth2ApiClient.createService(BicyclecontrollerApi.class));
    }


    /**
     * 开锁
     */
    public static TripController openLock() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new TripController(mOauth2ApiClient.createService(TripcontrollerApi.class));
    }


    /**
     * 获取系统配置
     */
    public static AppParamController getSystemParams() {

        return new AppParamController(mClientApiClient.createService(AppparamcontrollerApi.class));
    }

    public static MemberController getMemberApiNoToken() {
        return new MemberController(mClientApiClient.createService(MembercontrollerApi.class));
    }

    public static MemberController getMemberApi() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new MemberController(mOauth2ApiClient.createService(MembercontrollerApi.class));
    }

    public static UserController getUserApi() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new UserController(mOauth2ApiClient.createService(UsercontrollerApi.class));
    }

    /**
     * 预约
     */
    public static TripController reserve() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new TripController(mOauth2ApiClient.createService(TripcontrollerApi.class));
    }

    /**
     * 骑行结束支付
     */
    public static TripController ridePay() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new TripController(mOauth2ApiClient.createService(TripcontrollerApi.class));
    }

    public static MoneyController getMoneyController() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new MoneyController(mOauth2ApiClient.createService(MoneycontrollerApi.class));
    }

    /**
     * 获取所有的行程信息
     */
    public static TripController getAllStroke() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new TripController(mOauth2ApiClient.createService(TripcontrollerApi.class));
    }

    public static FileController getClientFileController() {
        return new FileController(mClientApiClient.createService(FileuploadcontrollerApi.class));
    }

    public static MessageCenterController getMessag() {
        mOauth2ApiClient.setAccessToken(PreferencesUtils.getString(mContext, "RADISHSAAS_IS_BIND"));
        return new MessageCenterController(mOauth2ApiClient.createService(MessagecontrollerApi.class));
    }
}
