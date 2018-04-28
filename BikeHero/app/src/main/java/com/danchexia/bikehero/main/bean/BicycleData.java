package com.danchexia.bikehero.main.bean;

import com.google.gson.annotations.SerializedName;
import com.danchexia.bikehero.api.BaseBean;

import java.util.Date;

import vc.thinker.colours.client.model.APIOrderWorkBO;
import vc.thinker.colours.client.model.Point;

/**
 * Created by farley on 17/5/16.
 * description:
 */

public class BicycleData extends BaseBean{
    @SerializedName("bluetoothCode")
    private String bluetoothCode = null;
    @SerializedName("doingOrderWork")
    private APIOrderWorkBO doingOrderWork = null;
    @SerializedName("electricity")
    private Integer electricity = null;
    @SerializedName("lastHeartbeat")
    private Date lastHeartbeat = null;
    @SerializedName("lastLocationTime")
    private Date lastLocationTime = null;
    @SerializedName("lockMacAddress")
    private String lockMacAddress = null;
    @SerializedName("openType")
    private Integer openType = null;//2纯蓝牙 3 混合 1gps
    @SerializedName("distance")
    private Double distance = null;//距离
    @SerializedName("locationDetails")
    private String locationDetails = null;//位置详情
    @SerializedName("point")
    private Point point = null;//位置
    @SerializedName("price")
    private Double price = null;//费用，单位元(0.5)
    @SerializedName("priceMinute")
    private Integer priceMinute = null;//费用分钟(30分)
    @SerializedName("status")
    private Integer status = null;//'1 正常  2 故障  3 禁用  4 报废',
    @SerializedName("sysCode")
    private String sysCode = null;//车辆的编码
    @SerializedName("walkTime")
    private Integer walkTime = null;//步行用时，秒
    @SerializedName("macPwd")
    private String macPwd = null;
    @SerializedName("macSecretKey")
    private String macSecretKey = null;

    public String getBluetoothCode() {
        return bluetoothCode;
    }

    public void setBluetoothCode(String bluetoothCode) {
        this.bluetoothCode = bluetoothCode;
    }

    public String getMacPwd() {
        return macPwd;
    }

    public void setMacPwd(String macPwd) {
        this.macPwd = macPwd;
    }

    public String getMacSecretKey() {
        return macSecretKey;
    }

    public void setMacSecretKey(String macSecretKey) {
        this.macSecretKey = macSecretKey;
    }

    public APIOrderWorkBO getDoingOrderWork() {
        return doingOrderWork;
    }

    public void setDoingOrderWork(APIOrderWorkBO doingOrderWork) {
        this.doingOrderWork = doingOrderWork;
    }

    public Integer getElectricity() {
        return electricity;
    }

    public void setElectricity(Integer electricity) {
        this.electricity = electricity;
    }

    public Date getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(Date lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public Date getLastLocationTime() {
        return lastLocationTime;
    }

    public void setLastLocationTime(Date lastLocationTime) {
        this.lastLocationTime = lastLocationTime;
    }

    public String getLockMacAddress() {
        return lockMacAddress;
    }

    public void setLockMacAddress(String lockMacAddress) {
        this.lockMacAddress = lockMacAddress;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(String locationDetails) {
        this.locationDetails = locationDetails;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPriceMinute() {
        return priceMinute;
    }

    public void setPriceMinute(Integer priceMinute) {
        this.priceMinute = priceMinute;
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

    public Integer getWalkTime() {
        return walkTime;
    }

    public void setWalkTime(Integer walkTime) {
        this.walkTime = walkTime;
    }
}
