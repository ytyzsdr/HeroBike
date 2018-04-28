package com.danchexia.bikehero.service;

import com.danchexia.bikehero.main.bean.OnGoingInfoBean;

import java.util.Observable;

import vc.thinker.tools.utils.LogUtils;

/**
 * Created by farley on 17/8/30.
 * description:
 */

public class ServiceBase extends Observable implements TripChangeListener{
    private OnGoingInfoBean onGoingInfoBean;
    public ServiceBase(RideStatusService rideStatusService) {
        rideStatusService.setTripChangeListener(this);
    }
    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }
    @Override
    public void onChange(OnGoingInfoBean onGoingInfoBean) {
        LogUtils.d("====================获取到数据==================");
        this.onGoingInfoBean = onGoingInfoBean;
        measurementsChanged();
    }

    public OnGoingInfoBean getOnGoingInfoBean() {
        return onGoingInfoBean;
    }
}
