/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class TripBO
/*     */ {
    @SerializedName("battery")
    private APIBatteryBO battery = null;
    @SerializedName("beginLcationLat")
    private Double beginLcationLat = null;
    @SerializedName("beginLcationLon")
    private Double beginLcationLon = null;
    @SerializedName("beginLocationDetails")
    private String beginLocationDetails = null;
    @SerializedName("beginTime")
    private Date beginTime = null;
    @SerializedName("bicycle")
    private BicycleBO bicycle = null;
    @SerializedName("bicycleId")
    private Long bicycleId = null;
    @SerializedName("calorie")
    private Integer calorie = null;
    @SerializedName("carbon")
    private Integer carbon = null;
    @SerializedName("createTime")
    private Date createTime = null;
    @SerializedName("cyclingPoints")
    private List<TripCyclingPointBO> cyclingPoints = new ArrayList();
    @SerializedName("distance")
    private Integer distance = null;
    @SerializedName("doingFeedbacks")
    private List<APIFeedbackBO> doingFeedbacks = new ArrayList();
    @SerializedName("endLcationLat")
    private Double endLcationLat = null;
    @SerializedName("endLcationLon")
    private Double endLcationLon = null;
    @SerializedName("endLocationDetails")
    private String endLocationDetails = null;
    @SerializedName("finishTime")
    private Date finishTime = null;
    @SerializedName("fitCoupon")
    private UserCouponBO fitCoupon = null;
    @SerializedName("id")
    private Long id = null;
    @SerializedName("inTheParkingLot")
    private Boolean inTheParkingLot = null;
    @SerializedName("isAutoEnd")
    private Boolean isAutoEnd = null;
    @SerializedName("isDeleted")
    private Boolean isDeleted = null;
    @SerializedName("kmMark")
    private String kmMark = null;
    @SerializedName("lastLockLocationTime")
    private Date lastLockLocationTime = null;
    @SerializedName("lastLockTime")
    private Date lastLockTime = null;
    @SerializedName("lastUnlockTime")
    private Date lastUnlockTime = null;
    @SerializedName("lockLocationAutoEndEffectiveTime")
    private Integer lockLocationAutoEndEffectiveTime = null;
    @SerializedName("lockOnoff")
    private Boolean lockOnoff = null;
    @SerializedName("payTime")
    private Date payTime = null;
    @SerializedName("payType")
    private String payType = null;
    @SerializedName("paymentMark")
    private String paymentMark = null;
    @SerializedName("price")
    private Double price = null;
    /** VIP价格 **/
    @SerializedName("vipPrice")
    private Double vipPrice;
    /** 优惠价格 **/
    @SerializedName("discountAmount")
    private Double discountAmount;
    @SerializedName("payAmout")
    private Double payAmout;//最终实际支付价格
    @SerializedName("rideTime")
    private Integer rideTime = null;
    @SerializedName("status")
    private Integer status = null;
    @SerializedName("stopType")
    private String stopType = null;
    @SerializedName("sysCode")
    private String sysCode = null;
    @SerializedName("tripType")
    private String tripType = null;

    public TripBO() {
    }

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

    //    @ApiModelProperty("电池详情")
    public APIBatteryBO getBattery() {
        return this.battery;
    }

    public void setBattery(APIBatteryBO battery) {
        this.battery = battery;
    }

    //    @ApiModelProperty("")
    public Double getBeginLcationLat() {
        return this.beginLcationLat;
    }

    public void setBeginLcationLat(Double beginLcationLat) {
        this.beginLcationLat = beginLcationLat;
    }

    //    @ApiModelProperty("")
    public Double getBeginLcationLon() {
        return this.beginLcationLon;
    }

    public void setBeginLcationLon(Double beginLcationLon) {
        this.beginLcationLon = beginLcationLon;
    }

    //    @ApiModelProperty("开始地址")
    public String getBeginLocationDetails() {
        return this.beginLocationDetails;
    }

    public void setBeginLocationDetails(String beginLocationDetails) {
        this.beginLocationDetails = beginLocationDetails;
    }

    //    @ApiModelProperty("")
    public Date getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    //    @ApiModelProperty("")
    public BicycleBO getBicycle() {
        return this.bicycle;
    }

    public void setBicycle(BicycleBO bicycle) {
        this.bicycle = bicycle;
    }

    //    @ApiModelProperty("")
    public Long getBicycleId() {
        return this.bicycleId;
    }

    public void setBicycleId(Long bicycleId) {
        this.bicycleId = bicycleId;
    }

    //    @ApiModelProperty("卡路里")
    public Integer getCalorie() {
        return this.calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    //    @ApiModelProperty("碳排放量")
    public Integer getCarbon() {
        return this.carbon;
    }

    public void setCarbon(Integer carbon) {
        this.carbon = carbon;
    }

    //    @ApiModelProperty("")
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //    @ApiModelProperty("")
    public List<TripCyclingPointBO> getCyclingPoints() {
        return this.cyclingPoints;
    }

    public void setCyclingPoints(List<TripCyclingPointBO> cyclingPoints) {
        this.cyclingPoints = cyclingPoints;
    }

    //    @ApiModelProperty("骑行距离/米")
    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    //    @ApiModelProperty("进行中的投诉")
    public List<APIFeedbackBO> getDoingFeedbacks() {
        return this.doingFeedbacks;
    }

    public void setDoingFeedbacks(List<APIFeedbackBO> doingFeedbacks) {
        this.doingFeedbacks = doingFeedbacks;
    }

    //    @ApiModelProperty("")
    public Double getEndLcationLat() {
        return this.endLcationLat;
    }

    public void setEndLcationLat(Double endLcationLat) {
        this.endLcationLat = endLcationLat;
    }

    //    @ApiModelProperty("")
    public Double getEndLcationLon() {
        return this.endLcationLon;
    }

    public void setEndLcationLon(Double endLcationLon) {
        this.endLcationLon = endLcationLon;
    }

    //    @ApiModelProperty("结束地址")
    public String getEndLocationDetails() {
        return this.endLocationDetails;
    }

    public void setEndLocationDetails(String endLocationDetails) {
        this.endLocationDetails = endLocationDetails;
    }

    //    @ApiModelProperty("")
    public Date getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    //    @ApiModelProperty("适合的优惠劵")
    public UserCouponBO getFitCoupon() {
        return this.fitCoupon;
    }

    public void setFitCoupon(UserCouponBO fitCoupon) {
        this.fitCoupon = fitCoupon;
    }

    //    @ApiModelProperty("")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //    @ApiModelProperty("在停车点内")
    public Boolean getInTheParkingLot() {
        return this.inTheParkingLot;
    }

    public void setInTheParkingLot(Boolean inTheParkingLot) {
        this.inTheParkingLot = inTheParkingLot;
    }

    //    @ApiModelProperty("是否可以自动结束")
    public Boolean getIsAutoEnd() {
        return this.isAutoEnd;
    }

    public void setIsAutoEnd(Boolean isAutoEnd) {
        this.isAutoEnd = isAutoEnd;
    }

    //    @ApiModelProperty("")
    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    //    @ApiModelProperty("")
    public String getKmMark() {
        return this.kmMark;
    }

    public void setKmMark(String kmMark) {
        this.kmMark = kmMark;
    }

    //    @ApiModelProperty("锁的最后位置时间")
    public Date getLastLockLocationTime() {
        return this.lastLockLocationTime;
    }

    public void setLastLockLocationTime(Date lastLockLocationTime) {
        this.lastLockLocationTime = lastLockLocationTime;
    }

    //    @ApiModelProperty("最后关锁时间")
    public Date getLastLockTime() {
        return this.lastLockTime;
    }

    public void setLastLockTime(Date lastLockTime) {
        this.lastLockTime = lastLockTime;
    }

    //    @ApiModelProperty("最后开锁时间")
    public Date getLastUnlockTime() {
        return this.lastUnlockTime;
    }

    public void setLastUnlockTime(Date lastUnlockTime) {
        this.lastUnlockTime = lastUnlockTime;
    }

    //    @ApiModelProperty("定点停车位置结束行程的有效时间（秒）")
    public Integer getLockLocationAutoEndEffectiveTime() {
        return this.lockLocationAutoEndEffectiveTime;
    }

    public void setLockLocationAutoEndEffectiveTime(Integer lockLocationAutoEndEffectiveTime) {
        this.lockLocationAutoEndEffectiveTime = lockLocationAutoEndEffectiveTime;
    }

    //    @ApiModelProperty("锁开关")
    public Boolean getLockOnoff() {
        return this.lockOnoff;
    }

    public void setLockOnoff(Boolean lockOnoff) {
        this.lockOnoff = lockOnoff;
    }

    //    @ApiModelProperty("")
    public Date getPayTime() {
        return this.payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    //    @ApiModelProperty("支付类型：免费：free  现金： cash  会员卡：vip  余额：balance")
    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    //    @ApiModelProperty("")
    public String getPaymentMark() {
        return this.paymentMark;
    }

    public void setPaymentMark(String paymentMark) {
        this.paymentMark = paymentMark;
    }

    //    @ApiModelProperty("")
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    //    @ApiModelProperty("骑行时间/分钟")
    public Integer getRideTime() {
        return this.rideTime;
    }

    public void setRideTime(Integer rideTime) {
        this.rideTime = rideTime;
    }

    //    @ApiModelProperty("状态  10:开锁中 20: 开锁失败 30:行驶中 40:未支付 50:已支付")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //    @ApiModelProperty("停车方式：任意：arbitrarily  定点停车 fixed_point")
    public String getStopType() {
        return this.stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    //    @ApiModelProperty("")
    public String getSysCode() {
        return this.sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    //    @ApiModelProperty("行程类型 bicycle,battery")
    public String getTripType() {
        return this.tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\TripBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */