package com.danchexia.bikehero.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.main.openloack.LockOpenningActivity;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.mvp.ControllerActivity;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/6/6.
 * description:开锁轮询
 */

public class OpenLockService extends Service {
    private Integer time = 2000;
    private MyThread myThread;
    private MyBinder mBinder = new MyBinder();
    private boolean flag = true;
    TripController tripController = APIControllerFactory.reserve();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public class MyBinder extends Binder {

        public void startCheckStatus() {
            myThread = new MyThread();
            if (!myThread.isAlive()) {
                myThread.start();
                flag = true;
            }
        }
        public void stopCheckStatus(){
            if (myThread != null) {
                LogUtils.d("stopCheckStatus");
                flag = false;
                myThread.interrupt();
            }
        }

    }

    @Override
    public void onDestroy() {
        LogUtils.d("开锁轮询终止");
        super.onDestroy();

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
                                if (onGoingInfoBean.getError_code() == 0) {
                                    LogUtils.d("status==service===开锁监听");
                                    if (onGoingInfoBean.getOnGoing_tripBO() != null && onGoingInfoBean.getOnGoing_tripBO().getStatus() == MyUtils.RIDE_STATUS_4) {
                                        LogUtils.d("status==service===40");

                                    }else if (onGoingInfoBean.getOnGoing_tripBO() != null &&
                                            onGoingInfoBean.getOnGoing_tripBO().getStatus() == MyUtils.RIDE_STATUS_3){//奇行种
                                        LogUtils.d("status==service===30");
                                        Intent msgIntent = new Intent(LockOpenningActivity.MESSAGE_RECEIVED_ACTION);
                                        getApplicationContext().sendBroadcast(msgIntent);
                                        interrupt();
                                    }
                                } else {
//                                    showErrorNone(onGoingInfoBean, getApplicationContext());
                                }
                            }
                        }, getErrorAction(new OnHttpListener() {
                            @Override
                            public void onResult(BaseBean bean) {
                                showErrorNone(bean, getApplicationContext());
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
    public Action1<Throwable> getErrorAction(final OnHttpListener onHttpListener) {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                LogUtils.d("================throwable================"+throwable);
                BaseBean bean = new BaseBean("系统错误", -1);
                if (throwable != null){
                    if (throwable.getMessage().contains("OAuthProblemException")){
                        bean = new BaseBean("登录失效，请重新登录", -10);//登录失效
                    }else if(throwable.getMessage().contains("isConnected failed")){
                        bean = new BaseBean("服务器连接失败", -20);//服务器链接失败
                    }else if(throwable.getMessage().contains("failed to connect to")){
                        bean = new BaseBean("网络连接超时", -30);//服务器链接失败
                    }else if(throwable.getMessage().contains("danchexia.vc")){
                        bean = new BaseBean("网络连接超时", -30);//服务器链接失败
                    }else if(throwable.getMessage().contains("Bad credentials")){
                        bean = new BaseBean("验证码错误", -35);//验证码错误
                    }else if(throwable.getMessage().contains("HTTP 500 Server Error")){
                        bean = new BaseBean("服务器出错", -36);
                    }else{
                        bean = new BaseBean(throwable.getMessage(), -1);
                    }
                    onHttpListener.onResult(bean);
                }
            }
        };
    }
    /**
     * 什么都不需要做的
     * @param bean
     * @param activity
     */
    public void showErrorNone(final BaseBean bean, final Context activity){
        final StanderdDialog dialog = new StanderdDialog(activity, bean.getResult(), "知道了",
                new StanderdDialog.OnDialogClickListener() {
                    @Override
                    public void doAnyClick() {
                    }

                    @Override
                    public void doMainClick() {
                        if (bean.getError_code() == -10 ) {
                            PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_IS_BIND", "");//退出登录要清空token 不能传null
                            PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA", "");//退出登录要清空个人资料不能传null
                            ActivityController.startBindPhone(activity);
                            ControllerActivity.finishAll();
                        }
                    }
                });
        dialog.show();
        dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
        dialog.setCanceledOnTouchOutside(false);
//        StanderdDialog dialog = StanderdDialog.getInstatnce(activity, bean.getResult(), "知道了", new StanderdDialog.OnDialogClickListener() {
//            @Override
//            public void doAnyClick() {
//
//            }
//
//            @Override
//            public void doMainClick() {
//                if (bean.getError_code() == -10 ) {
//                    PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_IS_BIND", "");//退出登录要清空token 不能传null
//                    PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA", "");//退出登录要清空个人资料不能传null
//                    ActivityController.startBindPhone(activity);
//                    ControllerActivity.finishAll();
//                }
//            }
//        });
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
    }
}
