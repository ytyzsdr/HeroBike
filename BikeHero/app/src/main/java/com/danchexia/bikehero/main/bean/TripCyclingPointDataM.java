package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.ApiProperty;

import java.util.Date;

/**
 * Created by farley on 17/5/27.
 * description:
 */

public class TripCyclingPointDataM {
    @ApiProperty("createTime")
    private Date createTime = null;
    @ApiProperty("pointLat")
    private Double pointLat = null;
    @ApiProperty("pointLon")
    private Double pointLon = null;

    public Double getPointLon() {
        return pointLon;
    }

    public void setPointLon(Double pointLon) {
        this.pointLon = pointLon;
    }

    public Double getPointLat() {
        return pointLat;
    }

    public void setPointLat(Double pointLat) {
        this.pointLat = pointLat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
