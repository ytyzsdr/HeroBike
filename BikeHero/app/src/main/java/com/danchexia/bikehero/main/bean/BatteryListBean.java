package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farley on 17/8/26.
 * description:
 */

public class BatteryListBean extends BaseBean {
    private List<BatteryBean> dataList = new ArrayList<>();

    public BatteryListBean(List<BatteryBean> dataList) {
        this.dataList = dataList;
    }

    public List<BatteryBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<BatteryBean> dataList) {
        this.dataList = dataList;
    }
}
