package com.danchexia.bikehero.main;

import vc.thinker.mvp.MvpView;

/**
 * Created by farley on 17/5/15.
 * description:
 */

public interface IMainView extends MvpView {
    void restartLocation();//重新定位
    void recervationBike();//预约
    void refreshAll();//刷新
    void ring();//点击寻车铃
    void canselRecervation();//取消预约
    void OnGoingStatus();//获取状态
    void helpFeedback();//获取状态
    void refreshLocation();
    void setStartRefreshLocation();
    void trip_unlock();
    void trip_unlock_forBluetooth();
    void endMyTrip();
    void endMyTripForBlue();
    void startToPerson();
    void go_find();
    void openMemberTop();//开通会员的提示条
}
