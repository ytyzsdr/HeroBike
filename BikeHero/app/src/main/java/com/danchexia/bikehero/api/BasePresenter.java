package com.danchexia.bikehero.api;

import android.app.Activity;

import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.ActivityController;

import java.lang.ref.WeakReference;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import vc.thinker.mvp.ControllerActivity;
import vc.thinker.mvp.MvpView;
import vc.thinker.mvp.Mvppresenter;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/5/22.
 * description:
 */

public class BasePresenter <V extends MvpView> implements Mvppresenter<V> {
    private WeakReference<V> reference;
    @Override
    public void attachView(V view) {
        if(view==null)
            throw new NullPointerException("view can not be null when in attachview() in MvpBasepresenter");
        else
        {
            if(reference==null)
                reference=new WeakReference<V>(view);

        }

    }

    @Override
    public void detachView() {

        if(reference!=null)
            reference.clear();
    }

    public V getMvpView()
    {
        if(reference!=null&&reference.get()!=null)
            return reference.get();
        else throw new NullPointerException("have you ever called attachview() in MvpBasepresenter");
    }

    public Boolean isAttach()
    {
        return reference!=null&&reference.get()!=null;
    }


    private CompositeSubscription mCompositeSubscription;

    public Action1<Throwable> getErrorAction(final OnHttpListener onHttpListener) {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                LogUtils.d("================throwable================"+throwable);
                BaseBean bean = new BaseBean("系统错误", -1);
                if (throwable != null && throwable.getMessage() != null){
                    if (throwable.getMessage().contains("UnknownHostException")){
                        bean = new BaseBean("网络连接失败，请重试", -10);
                    }else if (throwable.getMessage().contains("OAuthProblemException")){
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
                        bean = new BaseBean("服务器出错了", -36);
                    }else if(throwable.getMessage().contains("HTTP 502 Bad Gateway")){
                        bean = new BaseBean("服务器正在升级，请稍后...", -37);
                    }else if(throwable.getMessage().contains("OAuthSystemException")){
                        bean = new BaseBean("请求超时,请重试", -38);
                    }else if(throwable.getMessage().contains("timeout")){
                        bean = new BaseBean("请求超时，请重试", -39);
                    }else{
                        bean = new BaseBean(throwable.getMessage(), -1);
                    }
                    onHttpListener.onResult(bean);
                }
            }
        };
    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }

        mCompositeSubscription.add(subscription);
    }

    /**
     * 什么都不需要做的
     * @param bean
     * @param activity
     */
    public void showErrorNone(final BaseBean bean, final Activity activity){
//        StanderdDialog.getInstatnce(activity, bean.getResult(), "知道了", new StanderdDialog.OnDialogClickListener() {
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
//        }).show();
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
        dialog.setCanceledOnTouchOutside(false);
    }
    /**
     * finish掉当前界面的
     * @param bean
     * @param activity
     */
    public void showErrorFinish(final BaseBean bean, final Activity activity){
//        StanderdDialog.getInstatnce(activity, bean.getResult(), "知道了", new StanderdDialog.OnDialogClickListener() {
//            @Override
//            public void doAnyClick() {
//
//            }
//
//            @Override
//            public void doMainClick() {
//                activity.finish();
//            }
//        }).show();
        StanderdDialog dialog = new StanderdDialog(activity, bean.getResult(), "知道了",
                new StanderdDialog.OnDialogClickListener() {
                    @Override
                    public void doAnyClick() {
                    }

                    @Override
                    public void doMainClick() {
                        activity.finish();
                    }
                });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }
    /**
     * 退出到登录界面的
     * @param bean
     * @param activity
     */
    public void showErrorLogin(final BaseBean bean, final Activity activity){
//        StanderdDialog.getInstatnce(activity, bean.getResult(), "知道了", new StanderdDialog.OnDialogClickListener() {
//            @Override
//            public void doAnyClick() {
//
//            }
//
//            @Override
//            public void doMainClick() {
//                PreferencesUtils.putString(MyApplication.appContext,"RADISHSAAS_IS_BIND","");//退出登录要清空token 不能传null
//                PreferencesUtils.putString(MyApplication.appContext,"RADISHSAAS_PERSONAL_DATA","");//退出登录要清空个人资料不能传null
//                ActivityController.startBindPhone(activity);
//                JPushInterface.setAlias(activity, "", new TagAliasCallback() {
//                    @Override
//                    public void gotResult(int i, String s, Set<String> set) {
//                        LogUtils.d("注销jpush======"+s);
//                    }
//                });
//                MyApplication.setIsIdenty(0);
//                MyApplication.toPayStroke = true;
//                ControllerActivity.finishAll();
//                activity.finish();
//            }
//        }).show();
        StanderdDialog dialog = new StanderdDialog(activity, bean.getResult(), "知道了",
                new StanderdDialog.OnDialogClickListener() {
                    @Override
                    public void doAnyClick() {
                    }

                    @Override
                    public void doMainClick() {
                        PreferencesUtils.putString(MyApplication.appContext,"RADISHSAAS_IS_BIND","");//退出登录要清空token 不能传null
                        PreferencesUtils.putString(MyApplication.appContext,"RADISHSAAS_PERSONAL_DATA","");//退出登录要清空个人资料不能传null
                        ActivityController.startBindPhone(activity);
                        JPushInterface.setAlias(activity, "", new TagAliasCallback() {
                            @Override
                            public void gotResult(int i, String s, Set<String> set) {
                                LogUtils.d("注销jpush======"+s);
                            }
                        });
                        MyApplication.setIsIdenty(0);
                        MyApplication.toPayStroke = true;
                        ControllerActivity.finishAll();
                        activity.finish();
                    }
                });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }
}
