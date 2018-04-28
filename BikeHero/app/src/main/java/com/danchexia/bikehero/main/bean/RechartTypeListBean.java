package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farley on 17/8/22.
 * description:
 */

public class RechartTypeListBean extends BaseBean {
    private List<RechartTypeBean> dataList = new ArrayList<>();

    public RechartTypeListBean(List<RechartTypeBean> dataList) {
        this.dataList = dataList;
    }

    public List<RechartTypeBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<RechartTypeBean> dataList) {
        this.dataList = dataList;
    }
}
