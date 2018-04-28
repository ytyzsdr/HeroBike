package com.danchexia.bikehero.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.AdvImages;
import com.danchexia.bikehero.main.bean.BatteryBean;
import com.danchexia.bikehero.main.bean.InvateAndShateBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.myviews.AddressEntity;
import com.danchexia.bikehero.utils.autoupdata.PyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import cn.sharesdk.wechat.utils.WechatHelper;
import vc.thinker.tools.dialog.ShareDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/5/23.
 * description:
 */

public class MyUtils {
    //"认证状态：0未认证，1认证中，2认证成功，3认证失败"
    public static String PERSONAL_AUTH_STATUS_ITEM0 = "0";
    public static String PERSONAL_AUTH_STATUS_ITEM1 = "1";
    public static String PERSONAL_AUTH_STATUS_ITEM2 = "2";
    public static String PERSONAL_AUTH_STATUS_ITEM3 = "3";
    //支付模式
    public static String PAY_TYPE_ALIPAY = "alipay_app";
    public static String PAY_TYPE_WXPAY = "wx_app";

    //行程状态/** 状态  10:开锁中 20: 开锁失败 30:行驶中 40:未支付 50:已支付 */
    public static Integer RIDE_STATUS_1 = 10;
    public static Integer RIDE_STATUS_2 = 20;
    public static Integer RIDE_STATUS_3 = 30;
    public static Integer RIDE_STATUS_4 = 40;
    public static Integer RIDE_STATUS_5 = 50;


    /**
     * 检测token
     */
    public static Boolean checkToken(final Activity activity) {
        if (!MyApplication.getIsBindPhone()) {
            DialogUtils.standerdDialog1(activity, "提示", "请先绑定手机号", new DialogUtils.OnMainClickListener() {
                @Override
                public void onClick() {
                    ActivityController.startBindPhone(activity);
                }
            });
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保存个人信息
     */
    public static void savaPesonData(PersonalBean bean) {
        MyApplication.setIsIdenty(bean.getAuthStep());
        Gson gson = new Gson();
        String personalData = gson.toJson(bean);
        PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA", personalData);
    }

    /**
     * 获取个人资料
     *
     * @return
     */
    public static PersonalBean getPersonData() {
        Gson gson = new Gson();
        PersonalBean bean = new PersonalBean();
        String personStr = PreferencesUtils.getString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA");
        if (!TextUtils.isEmpty(personStr)) {
            bean = gson.fromJson(personStr, PersonalBean.class);
            return bean;
        } else {
            return null;
        }
    }

    /**
     * 保存系统配置
     *
     * @param systemParamsBean
     */
    public static void savaSystemData(SystemParamsBean systemParamsBean) {
        Gson gson = new Gson();
        String systemData = gson.toJson(systemParamsBean);
        PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_SYSTEM_DATA", systemData);
        Config.isNeadToPay = systemParamsBean.getPayTrip();//默认需要支付
        Config.isNeadToRegrist = systemParamsBean.getUserRegister();
        Config.isOpenVip = systemParamsBean.getOpenMemberCard();
        Config.isNeedOpenBalance = systemParamsBean.getOpenBalance();
        Config.MyAppName = "come on baby";
        if (systemParamsBean.getOpenBattery() != null){
            Config.isOpenBattery = systemParamsBean.getOpenBattery();
        }
        Config.isNeedIdent = systemParamsBean.getNeedAuthen();
    }
    /**
     * 保存电池信息
     *
     * @param batteryBean
     */
    public static void savaBatteryData(BatteryBean batteryBean) {
        Gson gson = new Gson();
        String batteryData = gson.toJson(batteryBean);
        PreferencesUtils.putString(MyApplication.appContext, "BATTERY", batteryData);
    }
    /**
     * 获取电池信息
     *
     * @return
     */
    public static BatteryBean getBatteryData() {
        Gson gson = new Gson();
        BatteryBean bean = new BatteryBean();
        String personStr = PreferencesUtils.getString(MyApplication.appContext, "BATTERY");
        if (!TextUtils.isEmpty(personStr)) {
            bean = gson.fromJson(personStr, BatteryBean.class);
            return bean;
        } else {
            return null;
        }

    }
    /**
     * 获取系统配置
     *
     * @return
     */
    public static SystemParamsBean getSystemData() {
        Gson gson = new Gson();
        SystemParamsBean bean = new SystemParamsBean();
        String personStr = PreferencesUtils.getString(MyApplication.appContext, "RADISHSAAS_SYSTEM_DATA");
        if (!TextUtils.isEmpty(personStr)) {
            bean = gson.fromJson(personStr, SystemParamsBean.class);
            return bean;
        } else {
            return null;
        }
    }

    /**
     * 保存邀请和分享的配置参数
     *
     * @param invateAndShateBean
     */
    public static void savaInvateAndShareData(InvateAndShateBean invateAndShateBean) {
        Gson gson = new Gson();
        String systemData = gson.toJson(invateAndShateBean);
        PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_INVATEANDSHARE_DATA", systemData);
        Config.isNeadToShare = invateAndShateBean.getAllowShare();
        Config.isNeadToInvate = invateAndShateBean.getAllowInvite();
        if (invateAndShateBean.getInviteWay() == null){
        }else{
            Config.getInviteType(invateAndShateBean.getInviteWay());
        }
        if (invateAndShateBean.getShareWay() == null){
        }else{
            Config.getShareType(invateAndShateBean.getShareWay());
        }
    }

    /**
     * 获取邀请和分享的配置参数
     *
     * @return
     */
    public static InvateAndShateBean getInvateAndShareData() {
        Gson gson = new Gson();
        InvateAndShateBean bean = new InvateAndShateBean();
        String personStr = PreferencesUtils.getString(MyApplication.appContext, "RADISHSAAS_INVATEANDSHARE_DATA");
        if (!TextUtils.isEmpty(personStr)) {
            bean = gson.fromJson(personStr, InvateAndShateBean.class);
            return bean;
        } else {
            return null;
        }
    }

    //分享demo
    public static void showShare(Activity activity) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(activity);
    }

    /**
     * 分享QQ
     *
     * @param activity
     * @param title
     * @param shareUrl
     * @param shareContent
     */
    public static void shareQQ(Activity activity, String title, String shareUrl, String shareContent) {

        OnekeyShare oks = new OnekeyShare();
        Platform plat = ShareSDK.getPlatform(QQ.NAME);
        String platform = plat.getName();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(shareUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareContent);

        Resources res = activity.getResources();
        BitmapDrawable d = (BitmapDrawable) res.getDrawable(R.mipmap.ic_launcher);
        Bitmap img = d.getBitmap();

        String fn = "image_test.png";
        String path = activity.getFilesDir() + File.separator + fn;
        try {
            OutputStream os = new FileOutputStream(path);
            img.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.close();
        } catch (Exception e) {
            Log.e("TAG", "", e);
        }
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(path);//确保SDcard下面存在此张图片
        // 启动分享GUI
        oks.show(activity);
    }

    public static void shareQzone(Activity activity, String title, String shareUrl, String shareContent) {

        OnekeyShare oks = new OnekeyShare();
        Platform plat = ShareSDK.getPlatform(QZone.NAME);
        String platform = plat.getName();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(shareUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareContent);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        Resources res = activity.getResources();
        BitmapDrawable d = (BitmapDrawable) res.getDrawable(R.mipmap.ic_launcher);
        Bitmap img = d.getBitmap();

        String fn = "image_test.png";
        String path = activity.getFilesDir() + File.separator + fn;
        try {
            OutputStream os = new FileOutputStream(path);
            img.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.close();
        } catch (Exception e) {
            Log.e("TAG", "", e);
        }

        oks.setImagePath(path);//确保SDcard下面存在此张图片
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null) {
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(systemParamsBean.getAppName());
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl("");
        }
        // 启动分享GUI
        oks.show(activity);


    }

    public static void showWechat(Activity activity, String title, String shareUrl, String shareContent) {
        /*OnekeyShare oks = new OnekeyShare();
        Platform plat = ShareSDK.getPlatform(Wechat.NAME);
        String platform = plat.getName();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareContent);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        *//*if (!TextUtils.isEmpty(shareImg)) {
            oks.setImagePath(shareImg);//确保SDcard下面存在此张图片
        }*//*
        oks.setImageUrl();
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareUrl);

        // 启动分享GUI
        oks.show(activity);*/
        WechatHelper.ShareParams shareParams = null;
        shareParams = new Wechat.ShareParams();
        shareParams.title = title;
        shareParams.imageData = ((BitmapDrawable) activity.getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        shareParams.text = shareContent;
        shareParams.url = shareUrl;
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        Platform plat = null;
        plat = ShareSDK.getPlatform(Wechat.NAME);
        plat.share(shareParams);
    }

    public static void showMoment(Activity activity, View view) {
       /* OnekeyShare oks = new OnekeyShare();
        WechatMoments.ShareParams params = new WechatMoments.ShareParams();
        params.setShareType(Platform.SHARE_IMAGE);
        Platform plat = ShareSDK.getPlatform(WechatMoments.NAME);
        plat.share(params);
        String platform = plat.getName();

        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareContent);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        if (!TextUtils.isEmpty(shareImg)) {
            oks.setImagePath(shareImg);//确保SDcard下面存在此张图片
        }
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareUrl);


        // 启动分享GUI
        oks.show(activity);*/
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setSilent(false);
        oks.setPlatform(WechatMoments.NAME);
        oks.setViewToShare(view);
        oks.show(activity);

    }

    public static void myShareDialog(boolean type,Activity activity,final ShareCancelListener shareCancelListener) {
        ShareDialog dialog = new ShareDialog(activity, new ShareDialog.OnDialogClickListener() {
            @Override
            public void choose(int i) {
                shareCancelListener.mySelect(i);

            }

            @Override
            public void cancel() {
                shareCancelListener.cancel();
            }
        });

        dialog.show();
        if (type) {
            dialog.setShareType(Config.isNeadToWeChatShare, Config.isNeadToWeChatMomentsShare
                    , Config.isNeadToQQShare, Config.isNeadToQZoneShare);
        }else{
            dialog.setShareType(Config.isNeadToWeChatInvite, Config.isNeadToWeChatMomentsInvite
                    , Config.isNeadToQQInvite, Config.isNeadToQZoneInvite);
        }
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.dialogWindowAnim);
        if (dialog != null && window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.width = ViewGroup.LayoutParams.MATCH_PARENT;
                attr.gravity = Gravity.BOTTOM;//设置dialog 在布局中的位置
                window.setAttributes(attr);
            }
        }
    }

    public interface ShareCancelListener {
        void cancel();
        void mySelect(int pos);
    }

    /**
     * 保存list数据
     *
     * @param context
     * @param key
     * @param datalist
     * @param <T>
     */
    public static String PREFERENCE_NAME = "RadishSaas";//自己命名

    public static <T> void putSeachHistory(Context context, String key, List<T> datalist) {
        /*if (null == datalist || datalist.size() <= 0)
            return;*/

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, strJson);
        editor.commit();
    }

    /**
     * 获取list数据
     *
     * @param context
     * @param tag
     * @return
     */
    public static List<AddressEntity> getSeachHistory(Context context, String tag) {
        List<AddressEntity> datalist = new ArrayList<AddressEntity>();
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        String strJson = settings.getString(tag, null);
        LogUtils.d("strJson=" + strJson);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<AddressEntity>>() {
        }.getType());
        return datalist;
    }

    /**
     * 旋转动画
     */
    public static void setAnimotion(final View view) {
        AnimationSet animationSet = new AnimationSet(true);
        final RotateAnimation rote = new RotateAnimation(0, 360 * 20, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rote.setDuration(400 * 20);
        animationSet.addAnimation(rote);
        view.startAnimation(animationSet);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.clearAnimation();
                view.setVisibility(View.GONE);
            }
        }, 2000);
    }

    /**
     * 检查用户当前的认证状态
     */
    public static Boolean checkStep(Activity activity) {
        int step = MyApplication.getIsIdenty();
        switch (step) {
            case 1:
                return false;
            case 2:
                ActivityController.startRecharge(activity);
                return false;
            case 3:
                ActivityController.startIdentid(activity);
                return false;
            case 4:
                return true;
            default:
                ActivityController.startBindPhone(activity);
                return false;
        }
    }
    private static String ADVFILENAME = "radish";
    public static File fileIsHave(){
        // 首先保存图片
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String filesName = ADVFILENAME;
        File appDir = new File(file ,filesName);

        LogUtils.d("imegaddress="+appDir.getAbsolutePath());
        return appDir;
    }
    public static void deleteMyFiles(){
        File myFile = fileIsHave();
        if (myFile.exists()) {
            deleteAllFilesOfDir(myFile);
        }
    }
    //删除文件夹
    public static void deleteAllFilesOfDir(File path) {
        if (!path.exists())
            return;
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAllFilesOfDir(files[i]);
        }
        path.delete();
    }
    /**
     * 保存行程
     *
     */
    public static void savaTripData(OnGoing_TripBO advImages) {
        Gson gson = new Gson();
        String systemData = gson.toJson(advImages);
        PreferencesUtils.putString(MyApplication.appContext, Config.TIPINGDATA, systemData);
    }
    public static OnGoing_TripBO getTripData() {
        Gson gson = new Gson();
        OnGoing_TripBO bean = new OnGoing_TripBO();
        String personStr = PreferencesUtils.getString(MyApplication.appContext, Config.TIPINGDATA);
        if (!TextUtils.isEmpty(personStr)) {
            bean = gson.fromJson(personStr, OnGoing_TripBO.class);
            return bean;
        } else {
            return null;
        }
    }
    /**
     * 保存广告配置
     *
     */
    public static void savaAdvanceData(AdvImages advImages) {
        Gson gson = new Gson();
        String systemData = gson.toJson(advImages);
        PreferencesUtils.putString(MyApplication.appContext, Config.ADV_IMAGE_DATA, systemData);
    }
    /**
     * 获取广告配置
     *
     * @return
     */
    public static AdvImages getAdvanceData() {
        Gson gson = new Gson();
        AdvImages bean = new AdvImages();
        String personStr = PreferencesUtils.getString(MyApplication.appContext, Config.ADV_IMAGE_DATA);
        if (!TextUtils.isEmpty(personStr)) {
            bean = gson.fromJson(personStr, AdvImages.class);
            return bean;
        } else {
            return null;
        }
    }
    public static long toToday(Long tDate){
        long today = new Date().getTime();
        return (tDate - today)/1000/60/60/24;
    }
    //自动更新
    public static void canUpdata(final Activity activity, final boolean isForce) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final PyUtils pyUtils = new PyUtils(activity);
                pyUtils.setCheckListener(new PyUtils.CheckListener() {
                    @Override
                    public void checkSuccess() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog dialog = new AlertDialog.Builder(activity)
                                        .setTitle(activity.getString(vc.thinker.tools.R.string.update_title))
                                        .setMessage(activity.getString(vc.thinker.tools.R.string.update_desc))
                                        .setNegativeButton(activity.getString(vc.thinker.tools.R.string.update_sure), new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                pyUtils.downUrl();
                                            }
                                        }).show();
                                if (isForce){
                                    dialog.setCanceledOnTouchOutside(false);
                                    dialog.setCancelable(false);
                                }else {
                                    dialog.setCanceledOnTouchOutside(true);
                                    dialog.setCancelable(true);
                                }

                            }
                        });
                    }
                });
                pyUtils.check();
            }
        }, 1000);
    }
    public static LogBean getAppMsgForBean(Context context) {
        String appVersion = "";
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            appVersion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String myPackageName = context.getPackageName();
        LogBean logBean = new LogBean(myPackageName,"Android",appVersion,Build.MODEL,Build.VERSION.RELEASE);
        String model = "Android/" + Build.MODEL + "/"
                + Build.VERSION.SDK_INT + "/"
                + Build.VERSION.RELEASE + "/" + appVersion;
        ;
        return logBean;
    }
    //打印对象
    public static void d(Object obj){
        Gson gson = new Gson();
        String result = gson.toJson(obj);
        Logger.d(result);
    }
}
