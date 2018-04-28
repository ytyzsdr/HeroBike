package com.danchexia.bikehero.main.bean;

import com.google.gson.annotations.SerializedName;
import com.danchexia.bikehero.api.BaseBean;

import java.util.Date;

import vc.thinker.colours.client.model.Point;

/**
 * Created by farley on 17/8/26.
 * description:
 */

public class BatteryBean extends BaseBean {
    @SerializedName("batteryCode")
    private String batteryCode = null;//电池编码
    @SerializedName("distance")
    private Double distance = null;
    @SerializedName("electricity")
    private Integer electricity = null;
    @SerializedName("lastHeartbeat")
    private Date lastHeartbeat = null;
    @SerializedName("lastLocationTime")
    private Date lastLocationTime = null;
    @SerializedName("locationDetails")
    private String locationDetails = null;
    @SerializedName("point")
    private Point point = null;
    @SerializedName("price")
    private Double price = null;//费用，单位元
    @SerializedName("priceMinute")
    private Integer priceMinute = null;//费用分钟
    @SerializedName("rechargeMileage")
    private Double rechargeMileage = null;//续航里程，单位小时
    @SerializedName("sysCode")
    private String sysCode = null;
    @SerializedName("typeDesc")
    private String typeDesc = null;//规格型号描述
    @SerializedName("typeName")
    private String typeName = null;

    public String getBatteryCode() {
        return batteryCode;
    }

    public void setBatteryCode(String batteryCode) {
        this.batteryCode = batteryCode;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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

    public Double getRechargeMileage() {
        return rechargeMileage;
    }

    public void setRechargeMileage(Double rechargeMileage) {
        this.rechargeMileage = rechargeMileage;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
