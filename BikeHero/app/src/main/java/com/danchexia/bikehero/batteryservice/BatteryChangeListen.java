package com.danchexia.bikehero.batteryservice;

import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.service.TripChangeListener;

import java.util.Observable;

/**
 * Created by farley on 17/9/6.
 * description:
 */

public class BatteryChangeListen extends Observable implements TripChangeListener {
    private OnGoingInfoBean onGoingInfoBean;
    public BatteryChangeListen(BatteryService rideStatusService) {
        rideStatusService.setTripChangeListener(this);
    }
    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }
    @Override
    public void onChange(OnGoingInfoBean onGoingInfoBean) {
        this.onGoingInfoBean = onGoingInfoBean;
        measurementsChanged();
    }

    public OnGoingInfoBean getOnGoingInfoBean() {
        return onGoingInfoBean;
    }
}
