package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farley on 17/8/17.
 * description:
 */

public class VipListBean extends BaseBean {
    private List<MemberVipCardBean> dataList = new ArrayList<>();

    public VipListBean(List<MemberVipCardBean> dataList) {
        this.dataList = dataList;
    }

    public List<MemberVipCardBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<MemberVipCardBean> dataList) {
        this.dataList = dataList;
    }
}
