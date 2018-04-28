package com.danchexia.bikehero.batterymain;

import vc.thinker.mvp.MvpView;

/**
 * Created by farley on 17/9/5.
 * description:
 */

public interface BatteryMainView  extends MvpView{
    void reLocation();
    void refreshData();
    void helpFeedback();
    void endTripForBattery();
    void startToPerson();
    void setGetAllBatteryBol();
}
