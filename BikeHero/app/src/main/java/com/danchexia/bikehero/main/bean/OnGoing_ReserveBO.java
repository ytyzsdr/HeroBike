package com.danchexia.bikehero.main.bean;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

import vc.thinker.colours.client.model.Point;

/**
 * Created by farley on 17/5/26.
 * description:
 */

public class OnGoing_ReserveBO {
    @SerializedName("bicycleId")
    private Long bicycleId = null;
    @SerializedName("createTime")
    private Date createTime = null;//
    @SerializedName("expireTime")
    private Date expireTime = null;//过期时间
    @SerializedName("id")
    private Long id = null;
    @SerializedName("location")
    private Point location = null;
    @SerializedName("locationDetails")
    private String locationDetails = null;
    @SerializedName("reserveTime")
    private Date reserveTime = null;//预约时间
    @SerializedName("status")
    private Integer status = null; /** 1:预约中 2:已取消  */
    @SerializedName("sysCode")
    private String sysCode = null;
    @SerializedName("uid")
    private Long uid = null;

    public Long getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(Long bicycleId) {
        this.bicycleId = bicycleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(String locationDetails) {
        this.locationDetails = locationDetails;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
