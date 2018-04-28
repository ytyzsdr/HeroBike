package com.danchexia.bikehero.main.recharge.bean;

import com.danchexia.bikehero.api.BaseBean;

/**
 * Created by farley on 17/5/23.
 * description:充值押金
 */

public class ChergeBean extends BaseBean {
    private Double cherge;//需要家哪的押金

    public ChergeBean(Double cherge) {
        this.cherge = cherge;
    }

    public Double getCherge() {
        return cherge;
    }

    public void setCherge(Double cherge) {
        this.cherge = cherge;
    }
}
