package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farley on 17/8/22.
 * description:
 */

public class RechartHistoryListBean extends BaseBean {
    private List<RechartHistoryBean> dataList = new ArrayList<>();

    public RechartHistoryListBean(List<RechartHistoryBean> dataList) {
        this.dataList = dataList;
    }

    public List<RechartHistoryBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<RechartHistoryBean> dataList) {
        this.dataList = dataList;
    }
}
