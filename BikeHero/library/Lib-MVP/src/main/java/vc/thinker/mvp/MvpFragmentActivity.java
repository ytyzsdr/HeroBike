package vc.thinker.mvp;


import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.umeng.analytics.MobclickAgent;

import vc.thinker.mvp.util.XlStatusBarUtil;


/**
 * Created by farley on 2017/2/23.
 * description:实现了ActivityMvpDelegateCallback，然后创建一个BasemvpDelegateCallback:
 */

public abstract class MvpFragmentActivity<P extends Mvppresenter, V extends MvpView> extends FragmentActivity implements
        ActivityMvpDelegateCallback<P, V>, MvpView {
    private ActyvityDelegate mActyvityDelegate;
    private P mPresenter;
    public MvpFragmentActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XlStatusBarUtil.StatusBarLightMode(this, XlStatusBarUtil.StatusBarLightMode(this));
        mActyvityDelegate = new ActivityDelegateImp<P, V>(this);
        mActyvityDelegate.onCreate();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale!=1){
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        mActyvityDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        mActyvityDelegate.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActyvityDelegate.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActyvityDelegate.onDestroy();
    }

    @Override
    public void setPresenter() {

    }

    protected abstract P CreatePresenter();//暴露一个创建的方法用于创建presenter

    @Override
    public P createPresenter()//这个方法由MvpInternalDelegate 调用BasemvpDelegateCallback 来创建presenter

    {
        mPresenter=CreatePresenter();
        return mPresenter;
    }

    @Override
    public P getPresenter() {//
        return mPresenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }


}
