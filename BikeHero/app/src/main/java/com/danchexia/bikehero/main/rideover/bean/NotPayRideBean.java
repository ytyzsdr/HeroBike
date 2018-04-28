package com.danchexia.bikehero.main.rideover.bean;

import com.danchexia.bikehero.api.ApiProperty;
import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by farley on 17/5/27.
 * description:
 */

public class NotPayRideBean extends BaseBean{
    @ApiProperty("beginTime")
    private Date beginTime = null;
    @ApiProperty("bicycleId")
    private Long bicycleId = null;
    @ApiProperty("createTime")
    private Date createTime = null;
    private List<TripCyclingPointData> cyclingPoints = new ArrayList();
    @ApiProperty("distance")
    private Integer distance = null;
    @ApiProperty("finishTime")
    private Date finishTime = null;
    private UserCouponData fitCoupon = null;
    @ApiProperty("id")
    private Long id = null;
    @ApiProperty("isDeleted")
    private Boolean isDeleted = null;
    @ApiProperty("payTime")
    private Date payTime = null;
    @ApiProperty("paymentMark")
    private String paymentMark = null;
    @ApiProperty("price")
    private Double price = null;
    @ApiProperty("rideTime")
    private Integer rideTime = null;
    @ApiProperty("status")
    private Integer status = null;
    @ApiProperty("sysCode")
    private String sysCode = null;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

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

    public List<TripCyclingPointData> getCyclingPoints() {
        return cyclingPoints;
    }

    public void setCyclingPoints(List<TripCyclingPointData> cyclingPoints) {
        this.cyclingPoints = cyclingPoints;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public UserCouponData getFitCoupon() {
        return fitCoupon;
    }

    public void setFitCoupon(UserCouponData fitCoupon) {
        this.fitCoupon = fitCoupon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPaymentMark() {
        return paymentMark;
    }

    public void setPaymentMark(String paymentMark) {
        this.paymentMark = paymentMark;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRideTime() {
        return rideTime;
    }

    public void setRideTime(Integer rideTime) {
        this.rideTime = rideTime;
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
}
