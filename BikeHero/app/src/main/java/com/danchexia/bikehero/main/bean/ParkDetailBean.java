package com.danchexia.bikehero.main.bean;

import com.google.gson.annotations.SerializedName;
import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.colours.client.model.Point;

/**
 * Created by farley on 17/8/10.
 * description:
 */

public class ParkDetailBean extends BaseBean {
    @SerializedName("distance")
    private Integer distance = null;//"定点半径
    @SerializedName("id")
    private Long id = null;
    @SerializedName("locationAddress")
    private String locationAddress = null;
    @SerializedName("locationDesc")
    private String locationDesc = null;//详细地址描述
    @SerializedName("locationLat")
    private Double locationLat = null;
    @SerializedName("locationLon")
    private Double locationLon = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("points")
    private List<Point> points = new ArrayList();//多边形的点
    @SerializedName("status")
    private Integer status = null;
    @SerializedName("type")
    private String type = null;//定点类型 circular：圆，polygon：多边形

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Double getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(Double locationLon) {
        this.locationLon = locationLon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
