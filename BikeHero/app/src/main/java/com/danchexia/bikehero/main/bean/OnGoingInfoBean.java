package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.ApiProperty;
import com.danchexia.bikehero.api.BaseBean;

/**
 * Created by farley on 17/5/26.
 * description:
 */

public class OnGoingInfoBean extends BaseBean {
    @ApiProperty("reserve")
    private OnGoing_ReserveBO onGoing_reserveBO;
    @ApiProperty("trip")
    private OnGoing_TripBO onGoing_tripBO;

    public OnGoingInfoBean(OnGoing_ReserveBO onGoing_reserveBO, OnGoing_TripBO onGoing_tripBO) {
        this.onGoing_reserveBO = onGoing_reserveBO;
        this.onGoing_tripBO = onGoing_tripBO;
    }

    public OnGoing_ReserveBO getOnGoing_reserveBO() {
        return onGoing_reserveBO;
    }

    public void setOnGoing_reserveBO(OnGoing_ReserveBO onGoing_reserveBO) {
        this.onGoing_reserveBO = onGoing_reserveBO;
    }

    public OnGoing_TripBO getOnGoing_tripBO() {
        return onGoing_tripBO;
    }

    public void setOnGoing_tripBO(OnGoing_TripBO onGoing_tripBO) {
        this.onGoing_tripBO = onGoing_tripBO;
    }
}
