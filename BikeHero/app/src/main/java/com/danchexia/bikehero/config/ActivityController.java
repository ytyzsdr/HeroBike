package com.danchexia.bikehero.config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import com.danchexia.bikehero.batterymain.BatteryMainActivity;
import com.danchexia.bikehero.batteryservice.BatteryService;
import com.danchexia.bikehero.main.MainActivity;
import com.danchexia.bikehero.main.WebViewActivity;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.bindphone.BindPhoneActivity;
import com.danchexia.bikehero.main.certifacation.CertifacationActivity;
import com.danchexia.bikehero.main.childseach.SeachActivity;
import com.danchexia.bikehero.main.feedback.FeedBackActivity;
import com.danchexia.bikehero.main.feedback.FeedBackScanActivity;
import com.danchexia.bikehero.main.invate.InvateActivity;
import com.danchexia.bikehero.main.member.RechartMemberActivity;
import com.danchexia.bikehero.main.messagecenter.MessageCenterActivity;
import com.danchexia.bikehero.main.my.MyActivity;
import com.danchexia.bikehero.main.myinvate.MyInvateActivity;
import com.danchexia.bikehero.main.myoffer.MyOfferActivity;
import com.danchexia.bikehero.main.mystroke.MyStrokeActivity;
import com.danchexia.bikehero.main.newwallet.DetailRechartActivity;
import com.danchexia.bikehero.main.newwallet.MyWalletActivity;
import com.danchexia.bikehero.main.newwallet.ToRechartWalletActivity;
import com.danchexia.bikehero.main.openloack.CodeOpenLockActivity;
import com.danchexia.bikehero.main.openloack.LockOpenningActivity;
import com.danchexia.bikehero.main.openloack.ScanQrcodeActivity;
import com.danchexia.bikehero.main.openloack.ScanReturnDataActivity;
import com.danchexia.bikehero.main.personal.PersonalActivity;
import com.danchexia.bikehero.main.recharge.RechargeActivity;
import com.danchexia.bikehero.main.rideover.RideOverActivity;
import com.danchexia.bikehero.main.set.SetActivity;
import com.danchexia.bikehero.main.strokedetail.StrokeDetailActivity;
import com.danchexia.bikehero.main.userguide.UserGuideActivity;
import com.danchexia.bikehero.main.wallet.WalletActivity;
import com.danchexia.bikehero.service.OpenLockService;
import com.danchexia.bikehero.service.RideStatusService;
import com.danchexia.bikehero.utils.MyUtils;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by farley on 17/5/15.
 * description:
 */

public class ActivityController {
    /**
     * 绑定手机号
     * @param context
     */
    public static void startBindPhone(Context context){
        Intent bindPhone = new Intent(context, BindPhoneActivity.class);
        context.startActivity(bindPhone);
    }

    /**
     * 编码开锁
     * @param context
     */
    public static void startCodeOpenLock(Context context){
        Intent code = new Intent(context, CodeOpenLockActivity.class);
        context.startActivity(code);
    }

    /**
     * 开锁中
     * @param context
     */
    public static void startLockOpenning(Context context,String code){
        Intent open = new Intent(context, LockOpenningActivity.class);
        open.putExtra("LOCK_CODE",code);//传过去code
        context.startActivity(open);
    }
    /**
     * 押金充值
     * @param context
     */
    public static void startRecharge(Context context){
        Intent recharge = new Intent(context, RechargeActivity.class);
        context.startActivity(recharge);
    }
    public static void startToRecharge(Context context){
        Intent recharge = new Intent(context, ToRechartWalletActivity.class);
        context.startActivity(recharge);
    }
    public static void startRechartHistory(Context context){
        Intent recharge = new Intent(context, DetailRechartActivity.class);
        context.startActivity(recharge);
    }
    /**
     * 身份证认证
     * @param context
     */
    public static void startIdentid(Context context){
        if (Config.isNeedIdent) {
            Intent certifacationActivity = new Intent(context, CertifacationActivity.class);
            context.startActivity(certifacationActivity);
        }else{
            Intent invate = new Intent(context, InvateActivity.class);
            context.startActivity(invate);
        }
    }

    /**
     * 个人中心
     */
    public static void startMy(Context context){
        Intent my = new Intent(context, MyActivity.class);
        context.startActivity(my);
    }

    /**
     * 修改个人信息
     * @param context
     */
    public static void startModify(Context context){
        Intent modify = new Intent(context, PersonalActivity.class);
        context.startActivity(modify);
    }

    /**
     * 设置
     * @param context
     */
    public static void startSet(Context context){
        Intent set = new Intent(context, SetActivity.class);
        context.startActivity(set);
    }
    /**
     * 填写好友的邀请码
     * @param context
     */
    public static void startInvate(Context context){
        Intent invate = new Intent(context, InvateActivity.class);
        context.startActivity(invate);
    }
    /**
     * 邀请好友
     * @param context
     */
    public static void startMyInvate(Context context){
        Intent invate = new Intent(context, MyInvateActivity.class);
        context.startActivity(invate);
    }
    /**
     * 启动WEB VIEW详情
     */
    public static void startWebView(Context context,String title, String url,Boolean needToken){
        Intent webview = new Intent(context, WebViewActivity.class);
        webview.putExtra("TITLE", title);
        webview.putExtra("VIEWURL", url);
        webview.putExtra("NEEDTOKEN",needToken);
        context.startActivity(webview);
    }
    /**
     * 用户指南
     */
    public static void startUserGuide(Context context){
        Intent guide = new Intent(context, UserGuideActivity.class);
        context.startActivity(guide);
    }
    /**
     * 我的会员
     */
    public static void getMyVip(Context context,boolean isvip,long remainDate){
        Intent guide = new Intent(context, RechartMemberActivity.class);
        guide.putExtra("ISVIP",isvip);
        guide.putExtra("REMAINDATE",remainDate);
        context.startActivity(guide);
    }
    public static void startMessage(Context context) {
        Intent main = new Intent(context, MessageCenterActivity.class);
        context.startActivity(main);
    }
    public static void startMain(Context context) {
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        Intent main;
        if (systemParamsBean.getOpenBattery() != null && systemParamsBean.getOpenBattery()){
            main = new Intent(context,BatteryMainActivity.class);
        }else{
            main = new Intent(context,MainActivity.class);
        }
        context.startActivity(main);
    }
    /**
     * 我的优惠
     */
    public static void startMyOffer(Context context,Boolean type){
        Intent offer = new Intent(context, MyOfferActivity.class);
        offer.putExtra("TYPE",type);
        context.startActivity(offer);
    }
    /**
     * 我的钱包
     */
    public static void startMywallet(Context context){
        Intent offer = new Intent(context, WalletActivity.class);
        context.startActivity(offer);
    }
    public static void startMyNewWallet(Context context){
        Intent offer = new Intent(context, MyWalletActivity.class);
        context.startActivity(offer);
    }
    /**
     * 我的行程
     */
    public static void startMyStroke(Context context){
        Intent stroke = new Intent(context, MyStrokeActivity.class);
        context.startActivity(stroke);
    }
    /**
     * 填写邀请码
     */
    public static void startFriendsInavate(Context context){
        Intent invate = new Intent(context, InvateActivity.class);
        context.startActivity(invate);
    }

    /**
     * 搜索
     * @param context
     */
    public static void startSeach(Activity context){
        Intent seach = new Intent(context, SeachActivity.class);
        context.startActivityForResult(seach, 1001);
    }
    /**
     * 扫面二维码
     * @param context
     */
    public static void startScan(Activity context){
        Intent bindPhone = new Intent(context, ScanQrcodeActivity.class);
        context.startActivityForResult(bindPhone,5);
    }
    /**
     * 扫面二维码返回数据
     * @param context
     */
    public static void startScanReturnData(Activity context){
        Intent bindPhone = new Intent(context, ScanReturnDataActivity.class);
        context.startActivityForResult(bindPhone,5);
    }
    /**
     * 扫面二维码返回数据
     * @param context
     */
    public static void startFeedBackScanReturnData(Activity context){
        Intent bindPhone = new Intent(context, FeedBackScanActivity.class);
        context.startActivityForResult(bindPhone,5);
    }
    /**
     * 问题反馈
     * @param context
     * 1 一般问题 2 行程中 3 已完成用户
     * @param id
     */
    public static void startFeedBack(Activity context, String type, String code,Long id){
        Intent question = new Intent(context, FeedBackActivity.class);
        question.putExtra("TYPE",type);
        question.putExtra("SYSCODE",code);
        question.putExtra("TRIPID",id);
        context.startActivity(question);
    }
    /**
     * 骑行结束
     * @param context
     */
    public static void startRideOver(Activity context,Long tripId){
        Intent bindPhone = new Intent(context, RideOverActivity.class);
        bindPhone.putExtra("USERTRIPID",tripId);
        context.startActivityForResult(bindPhone,303);
    }

    /**
     * 启动骑行状态轮训
     * @param context
     */
    public static void startRideStatusService(Context context){
        Intent startIntent = new Intent(context, RideStatusService.class);
        context.startService(startIntent);
    }
    /**
     * 启动开锁轮询
     * @param context
     */
    public static void startOpenService(Context context){
        Intent startIntent = new Intent(context, OpenLockService.class);
        context.startService(startIntent);
    }
    /**
     * 启动开锁轮询
     * @param context
     */
    public static void startOpenServiceForBattery(Context context){
        Intent startIntent = new Intent(context, BatteryService.class);
        context.startService(startIntent);
    }
    /**
     * 终止骑行状态轮训
     * @param context
     */
    public static void stopRideStatusService(Context context){
        Intent stopIntent = new Intent(context, RideStatusService.class);
        context.stopService(stopIntent);
    }
    /**
     * 终止开锁
     * @param context
     */
    public static void stopOpenService(Context context){
        Intent stopIntent = new Intent(context, OpenLockService.class);
        context.stopService(stopIntent);
    }
    public static void stopOpenServiceForBattery(Context context){
        Intent stopIntent = new Intent(context, BatteryService.class);
        context.stopService(stopIntent);
    }
    /**
     * 绑定骑行状态轮训
     * @param context
     */
    public static boolean bindRideStatusService(Context context, ServiceConnection connection){
        Intent bindIntent = new Intent(context, RideStatusService.class);
        return context.bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }
    /**
     * 绑定开锁轮训
     * @param context
     */
    public static void bindOpenService(Context context,ServiceConnection connection){
        Intent bindIntent = new Intent(context, OpenLockService.class);
        context.bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }
    /**
     * 绑定开锁轮训
     * @param context
     */
    public static void bindOpenServiceForBattery(Context context,ServiceConnection connection){
        Intent bindIntent = new Intent(context, BatteryService.class);
        context.bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }
    /**
     * 取消绑定骑行状态轮训
     * @param context
     */
    public static void unbindRideStatusService(Context context,ServiceConnection connection){
        context.unbindService(connection);
    }
    /**
     * 取消开锁轮训
     * @param context
     */
    public static void unbindOpenService(Context context,ServiceConnection connection){
        context.unbindService(connection);
    }
    /**
     * 行程详情
     * @param context
     * @param id
     */
    public static void startStrokeDetail(Context context, Long id){
        Intent detail = new Intent(context, StrokeDetailActivity.class);
        detail.putExtra("TID",id);
        context.startActivity(detail);
    }
}
