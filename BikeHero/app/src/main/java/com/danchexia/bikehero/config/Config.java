package com.danchexia.bikehero.config;

import android.content.Context;
import android.text.TextUtils;


import com.danchexia.bikehero.app.MyApplication;

import vc.thinker.colours.client.ApiClient;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/3/13.
 * description:后台api的配置
 */

public class Config {
    public static String MyAppName = "单车侠";//默认应用被App的名字
    public static Boolean isNeadToRegrist = true;//默认需要注册
    public static Boolean isNeadToPay = true;//默认需要支付
    public static Boolean isNeadToShare = true;//默认需要分享
    public static Boolean isNeadToInvate = true;//默认需要邀请
    public static Boolean isOpenVip = false;//默认不需要会员体系
    public static Boolean isNeadToWeChatShare = false;//默认不需要微信分享
    public static Boolean isNeadToWeChatMomentsShare = false;//默认不需要微信朋友圈分享
    public static Boolean isNeadToQQShare = false;//默认不需要QQ分享
    public static Boolean isNeadToQZoneShare = false;//默认不需要QZone分享
    public static Boolean isNeadToWeChatInvite = false;//默认不需要微信分享邀请
    public static Boolean isNeadToWeChatMomentsInvite = false;//默认不需要微信朋友圈分享邀请
    public static Boolean isNeadToQQInvite = false;//默认不需要QQ分享邀请
    public static Boolean isNeadToQZoneInvite = false;//默认不需要QZone分享邀请
    public static Boolean isNeedOpenBalance = true;//默认需要余额功能
    public static Boolean isOpenBattery = false;//是否有蓄电池
    public static Boolean isNeedIdent = true;//默认需要认证
    public static boolean toRefreshData = false;//my是不是要刷新
    public static boolean HAVETRIPING = false;//默认没有进行中的行程
    public static String ADV_IMAGE_MD5 = "START";
    public static String ADV_IMAGE_DATA = "ADV_IMAGE_DATA";//广告配置
    public static String TIPINGDATA = "TIPINGDATA";//当前存在的行程
    public static String USERTRIPID = "USERTRIPID";
    public static String TRIPINGBLUETOOTHMACADDRESS = "TRIPINGBLUETOOTHMACADDRESS";
    public static final String wxAppId = "wx65ede7aeb95b5822";
    public static final String wxAppSecret = "893cb1c32de33d828cbef6e91e4bd069";
    public static final String APPID="d6db17296dcb3bceffbad4d7f3c9ba47";// 蒲公英上传应用appId
    public static final String _api_key="683e51e43b701e28f759a576ae8e2f26";// 蒲公英上传应用appId apikey
    public static final  String appClientId="3000001";
    public static final  String appClientSecret="92aa5479e9b3fc62c4f7faff7d415310";


    enum Mode{devMode,testMode,releaseMode}
    private static final String TOKEN_URL = "http://oauth.bikeman.fun/oauth/token";
    //    private static final String TOKEN_URL = "http://oauth.nextsystem.cn/oauth/token";
    private static final String TOKEN_URL_TEST = "http://colours.oauth.danchexia.vc/oauth/token";
    private static final String TOKEN_URL_DEV = "http://192.168.1.250:13010/oauth/token";

    private static final String API_URL = "http://api.bikeman.fun/";
    //    private static final String API_URL = "http://api.nextsystem.cn/";
    private static final String API_URL_TEST = "http://colours.api.danchexia.vc/";
    private static final String API_URL_DEV = "http://192.168.1.250:13020/";
    private static Mode myMode = Mode.releaseMode;//当前模式
    public static void init(Context context){
        ApiClient.userAgent = Utils.getAppMsg(context);
        switch (myMode){
            case devMode:
                ApiClient.tokenUrl = TOKEN_URL_DEV;
                ApiClient.baseUrl = API_URL_DEV;
                break;
            case testMode:
                ApiClient.tokenUrl = TOKEN_URL_TEST;
                ApiClient.baseUrl = API_URL_TEST;
                break;
            case releaseMode:
                ApiClient.tokenUrl = TOKEN_URL;
                ApiClient.baseUrl = API_URL;
                break;
            default:
                break;

        }
        getAdvImageMd5();
    }

    public static String getAdvImageMd5() {
        ADV_IMAGE_MD5 = PreferencesUtils.getString(MyApplication.appContext,"ADV_IMAGE_MD5");
        if (TextUtils.isEmpty(ADV_IMAGE_MD5)){
            ADV_IMAGE_MD5 = "1";
        }
        return ADV_IMAGE_MD5;
    }

    public static void setAdvImageMd5(String advImageMd5) {
        ADV_IMAGE_MD5 = advImageMd5;
        PreferencesUtils.putString(MyApplication.appContext,"ADV_IMAGE_MD5",ADV_IMAGE_MD5);
    }

    //行程分享途径 1，微信，2朋友圈，3QQ,4QQ空间
    public  static void getShareType(String type){
        if (type.contains("1")){
            isNeadToWeChatShare = true;
        }
        if(type.contains("2")){
            isNeadToWeChatMomentsShare = true;
        }
        if (type.contains("3")){
            isNeadToQQShare = true;
        }
        if (type.contains("4")){
            isNeadToQZoneShare = true;
        }
    }
    //邀请好友途径 1，微信，2朋友圈，3QQ,4QQ空间
    public static void getInviteType(String type){
        if (type.contains("1")){
            isNeadToWeChatInvite = true;
        }
        if(type.contains("2")){
            isNeadToWeChatMomentsInvite = true;
        }
        if (type.contains("3")){
            isNeadToQQInvite = true;
        }
        if (type.contains("4")){
            isNeadToQZoneInvite = true;
        }

    }
}
