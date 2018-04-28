package com.danchexia.bikehero.main.bean;

import com.google.gson.annotations.SerializedName;
import com.danchexia.bikehero.api.BaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vc.thinker.colours.client.model.APIFeedbackBO;

/**
 * Created by farley on 17/5/26.
 * description:
 */

public class OnGoing_TripBO extends BaseBean{
    @SerializedName("inTheParkingLot")
    private Boolean inTheParkingLot = null;//在停车点内
    //是否可以自动结束    true  可以   ----> 倒计时    结束以后  去 位置弹窗   false 不可以 -----> （直接位置弹位置窗口） 第三步
    @SerializedName("isAutoEnd")
    private Boolean isAutoEnd = null;
    @SerializedName("kmMark")
    private String kmMark = null;
    @SerializedName("lastLockTime")
    private Date lastLockTime = null;//最后关锁时间
    @SerializedName("lastUnlockTime")
    private Date lastUnlockTime = null;//最后开锁时间
    @SerializedName("lockLocationAutoEndEffectiveTime")
    private Integer lockLocationAutoEndEffectiveTime = null;//定点停车位置结束行程的有效时间（秒）
    @SerializedName("lockOnoff")
    private Boolean lockOnoff = null;//锁开关  //true 开  false 管   ----》判断是否是可以自动结束    第二步
    @SerializedName("payType")
    private String payType = null;//支付类型：免费：free  现金： cash
    @SerializedName("stopType")
    private String stopType = null;//停车方式：任意：arbitrarily  定点停车 fixed_point       第一步
    @SerializedName("lastLockLocationTime")
    private Date lastLockLocationTime = null;//锁的最后位置时间
    @SerializedName("beginTime")
    private Date beginTime = null;
    @SerializedName("bicycleId")
    private Long bicycleId = null;
    @SerializedName("calorie")
    private Integer calorie = null;//卡路里
    @SerializedName("createTime")
    private Date createTime = null;
    private List<TripCyclingPointDataM> cyclingPoints = new ArrayList();
    @SerializedName("distance")
    private Integer distance = null;//骑行距离/米
    @SerializedName("carbon")
    private Integer carbon = null;//碳排放量
    @SerializedName("finishTime")
    private Date finishTime = null;
    private UserCouponDataM fitCoupon = null;//适合的优惠劵
    @SerializedName("id")
    private Long id = null;
    @SerializedName("isDeleted")
    private Boolean isDeleted = null;
    @SerializedName("payTime")
    private Date payTime = null;
    @SerializedName("paymentMark")
    private String paymentMark = null;
    @SerializedName("price")
    private Double price = null;
    @SerializedName("vipPrice")
    private Double vipPrice;//VIP价格
    @SerializedName("discountAmount")
    private Double discountAmount;//优惠价格
    @SerializedName("payAmout")
    private Double payAmout;//最终实际支付价格
    @SerializedName("rideTime")
    private Integer rideTime = null;//骑行时间/分钟
    @SerializedName("status")
    private Integer status = null;//状态  10:开锁中 20: 开锁失败 30:行驶中 40:未支付 50:已支付
    @SerializedName("sysCode")
    private String sysCode = null;
    private Long refreshTime = 0L;
    @SerializedName("doingFeedbacks")
    private List<APIFeedbackBO> doingFeedbacks = new ArrayList();
    @SerializedName("bicycle")
    private BicycleData bicycle = null;
    @SerializedName("battery")
    private BatteryBean battery = null;
    @SerializedName("tripType")
    private String tripType = null;//bicycle,battery
    @SerializedName("beginLocationDetails")
    private String beginLocationDetails = null;
    @SerializedName("endLocationDetails")
    private String endLocationDetails = null;
    @SerializedName("beginLcationLat")
    private Double beginLcationLat = null;
    @SerializedName("beginLcationLon")
    private Double beginLcationLon = null;
    @SerializedName("endLcationLat")
    private Double endLcationLat = null;
    @SerializedName("endLcationLon")
    private Double endLcationLon = null;
    @SerializedName("showPwd")
    private Boolean showPwd = false;//是否显示密码
    @SerializedName("unlockPwd")
    private String unlockPwd = null;//密码

    public Double getPayAmout() {
        return payAmout;
    }

    public void setPayAmout(Double payAmout) {
        this.payAmout = payAmout;
    }

    public Double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }
    public Boolean getShowPwd() {
        return showPwd;
    }

    public void setShowPwd(Boolean showPwd) {
        this.showPwd = showPwd;
    }

    public String getUnlockPwd() {
        return unlockPwd;
    }

    public void setUnlockPwd(String unlockPwd) {
        this.unlockPwd = unlockPwd;
    }

    public Double getEndLcationLat() {
        return endLcationLat;
    }

    public void setEndLcationLat(Double endLcationLat) {
        this.endLcationLat = endLcationLat;
    }

    public Double getEndLcationLon() {
        return endLcationLon;
    }

    public void setEndLcationLon(Double endLcationLon) {
        this.endLcationLon = endLcationLon;
    }

    public Double getBeginLcationLat() {
        return beginLcationLat;
    }

    public void setBeginLcationLat(Double beginLcationLat) {
        this.beginLcationLat = beginLcationLat;
    }

    public Double getBeginLcationLon() {
        return beginLcationLon;
    }

    public void setBeginLcationLon(Double beginLcationLon) {
        this.beginLcationLon = beginLcationLon;
    }

    public String getEndLocationDetails() {
        return endLocationDetails;
    }

    public void setEndLocationDetails(String endLocationDetails) {
        this.endLocationDetails = endLocationDetails;
    }

    public String getBeginLocationDetails() {
        return beginLocationDetails;
    }

    public void setBeginLocationDetails(String beginLocationDetails) {
        this.beginLocationDetails = beginLocationDetails;
    }

    public BatteryBean getBattery() {
        return battery;
    }

    public void setBattery(BatteryBean battery) {
        this.battery = battery;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public BatteryBean getBatteryBO() {
        return battery;
    }

    public void setBatteryBO(BatteryBean batteryBO) {
        this.battery = batteryBO;
    }

    public BicycleData getBicycle() {
        return bicycle;
    }

    public void setBicycle(BicycleData bicycle) {
        this.bicycle = bicycle;
    }

    public List<APIFeedbackBO> getDoingFeedbacks() {
        return doingFeedbacks;
    }

    public void setDoingFeedbacks(List<APIFeedbackBO> doingFeedbacks) {
        this.doingFeedbacks = doingFeedbacks;
    }

    public Date getLastLockLocationTime() {
        return lastLockLocationTime;
    }

    public void setLastLockLocationTime(Date lastLockLocationTime) {
        this.lastLockLocationTime = lastLockLocationTime;
    }

    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public Boolean getInTheParkingLot() {
        return inTheParkingLot;
    }

    public void setInTheParkingLot(Boolean inTheParkingLot) {
        this.inTheParkingLot = inTheParkingLot;
    }

    public Boolean getAutoEnd() {
        return isAutoEnd;
    }

    public void setAutoEnd(Boolean autoEnd) {
        isAutoEnd = autoEnd;
    }

    public String getKmMark() {
        return kmMark;
    }

    public void setKmMark(String kmMark) {
        this.kmMark = kmMark;
    }

    public Date getLastLockTime() {
        return lastLockTime;
    }

    public void setLastLockTime(Date lastLockTime) {
        this.lastLockTime = lastLockTime;
    }

    public Date getLastUnlockTime() {
        return lastUnlockTime;
    }

    public void setLastUnlockTime(Date lastUnlockTime) {
        this.lastUnlockTime = lastUnlockTime;
    }

    public Integer getLockLocationAutoEndEffectiveTime() {
        return lockLocationAutoEndEffectiveTime;
    }

    public void setLockLocationAutoEndEffectiveTime(Integer lockLocationAutoEndEffectiveTime) {
        this.lockLocationAutoEndEffectiveTime = lockLocationAutoEndEffectiveTime;
    }

    public Boolean getLockOnoff() {
        return lockOnoff;
    }

    public void setLockOnoff(Boolean lockOnoff) {
        this.lockOnoff = lockOnoff;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public Integer getCarbon() {
        return carbon;
    }

    public void setCarbon(Integer carbon) {
        this.carbon = carbon;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

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

    public List<TripCyclingPointDataM> getCyclingPoints() {
        return cyclingPoints;
    }

    public void setCyclingPoints(List<TripCyclingPointDataM> cyclingPoints) {
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

    public UserCouponDataM getFitCoupon() {
        return fitCoupon;
    }

    public void setFitCoupon(UserCouponDataM fitCoupon) {
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
