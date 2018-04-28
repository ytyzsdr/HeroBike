package com.danchexia.bikehero.service.bean;

import com.danchexia.bikehero.api.BaseBean;

import java.util.List;

/**
 * Created by farley on 17/5/27.
 * description:
 */

public class UpPointBean extends BaseBean {
    private List<UpPointData> pointDatas;

    public List<UpPointData> getPointDatas() {
        return pointDatas;
    }

    public void setPointDatas(List<UpPointData> pointDatas) {
        this.pointDatas = pointDatas;
    }
}
