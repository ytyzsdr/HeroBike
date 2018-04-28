package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farley on 17/8/18.
 * description:
 */

public class VipListPayBean extends BaseBean {
    private List<VipPayBean> dataList = new ArrayList<>();

    public VipListPayBean(List<VipPayBean> dataList) {
        this.dataList = dataList;
    }

    public List<VipPayBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<VipPayBean> dataList) {
        this.dataList = dataList;
    }
}
