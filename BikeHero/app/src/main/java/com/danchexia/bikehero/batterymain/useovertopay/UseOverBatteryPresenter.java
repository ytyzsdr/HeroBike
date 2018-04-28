package com.danchexia.bikehero.batterymain.useovertopay;

import com.danchexia.bikehero.api.BasePresenter;

import vc.thinker.mvp.MvpView;

/**
 * Created by farley on 17/9/6.
 * description:
 */

public class UseOverBatteryPresenter extends BasePresenter<MvpView> {
    private UseOverBatteryActivity activity;

    public UseOverBatteryPresenter(UseOverBatteryActivity activity) {
        this.activity = activity;
    }

}
