package com.danchexia.bikehero.main.bean;


import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by farley on 17/5/16.
 * description:单车
 */

public class BicycleBean extends BaseBean {
    private List<BicycleData> listBike  = new ArrayList<>();
    public BicycleBean() {
    }
    public BicycleBean(List<BicycleData> listBike) {
        this.listBike = listBike;
    }

    public List<BicycleData> getListBike() {
        return listBike;
    }

    public void setListBike(List<BicycleData> listBike) {
        this.listBike = listBike;
    }
}
