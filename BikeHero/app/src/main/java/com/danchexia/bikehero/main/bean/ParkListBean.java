package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.BaseBean;

import java.util.List;

/**
 * Created by farley on 17/8/10.
 * description:
 */

public class ParkListBean extends BaseBean {
    private List<ParkDetailBean> dataList;

    public ParkListBean(List<ParkDetailBean> dataList) {
        this.dataList = dataList;
    }

    public List<ParkDetailBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<ParkDetailBean> dataList) {
        this.dataList = dataList;
    }
}
