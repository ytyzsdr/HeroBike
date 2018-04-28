/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class ElectrombileBO
/*     */ {
/*     */   @SerializedName("bluetoothIdentifier")
/*  17 */   private String bluetoothIdentifier = null;
/*     */   
/*     */   @SerializedName("centerControllerId")
/*  20 */   private Long centerControllerId = null;
/*     */   
/*     */   @SerializedName("cityId")
/*  23 */   private String cityId = null;
/*     */   
/*     */   @SerializedName("createBy")
/*  26 */   private String createBy = null;
/*     */   
/*     */   @SerializedName("createTime")
/*  29 */   private Date createTime = null;
/*     */   
/*     */   @SerializedName("disTime")
/*  32 */   private Date disTime = null;
/*     */   
/*     */   @SerializedName("distance")
/*  35 */   private Double distance = null;
/*     */   
/*     */   @SerializedName("electricity")
/*  38 */   private Integer electricity = null;
/*     */   
/*     */   @SerializedName("electricityAlarm")
/*  41 */   private Integer electricityAlarm = null;
/*     */   
/*     */   @SerializedName("electrombileCode")
/*  44 */   private String electrombileCode = null;
/*     */   
/*     */   @SerializedName("id")
/*  47 */   private Long id = null;
/*     */   
/*     */   @SerializedName("lastHeartbeat")
/*  50 */   private Date lastHeartbeat = null;
/*     */   
/*     */   @SerializedName("lastLocationDetails")
/*  53 */   private String lastLocationDetails = null;
/*     */   
/*     */   @SerializedName("lastLocationTime")
/*  56 */   private Date lastLocationTime = null;
/*     */   
/*     */   @SerializedName("locationDetails")
/*  59 */   private String locationDetails = null;
/*     */   
/*     */   @SerializedName("motorPower")
/*  62 */   private Integer motorPower = null;
/*     */   
/*     */   @SerializedName("officeId")
/*  65 */   private Long officeId = null;
/*     */   
/*     */   @SerializedName("officeName")
/*  68 */   private String officeName = null;
/*     */   
/*     */   @SerializedName("oldElectricity")
/*  71 */   private Integer oldElectricity = null;
/*     */   
/*     */   @SerializedName("point")
/*  74 */   private Point point = null;
/*     */   
/*     */   @SerializedName("positionerCode")
/*  77 */   private String positionerCode = null;
/*     */   
/*     */   @SerializedName("positionerId")
/*  80 */   private Long positionerId = null;
/*     */   
/*     */   @SerializedName("rechargeMileage")
/*  83 */   private Double rechargeMileage = null;
/*     */   
/*     */   @SerializedName("speed")
/*  86 */   private Integer speed = null;
/*     */   
/*     */   @SerializedName("status")
/*  89 */   private Integer status = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  92 */   private String sysCode = null;
/*     */   
/*     */   @SerializedName("totalIncome")
/*  95 */   private Double totalIncome = null;
/*     */   
/*     */   @SerializedName("totalRideDistance")
/*  98 */   private Double totalRideDistance = null;
/*     */   
/*     */   @SerializedName("totalUseTimes")
/* 101 */   private Integer totalUseTimes = null;
/*     */   
/*     */   @SerializedName("typeId")
/* 104 */   private Long typeId = null;
/*     */   
/*     */   @SerializedName("typeName")
/* 107 */   private String typeName = null;
/*     */   
/*     */   @SerializedName("updateBy")
/* 110 */   private String updateBy = null;
/*     */   
/*     */   @SerializedName("updateTime")
/* 113 */   private Date updateTime = null;
/*     */   
/*     */   @SerializedName("voltage")
/* 116 */   private Integer voltage = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getBluetoothIdentifier()
/*     */   {
/* 125 */     return this.bluetoothIdentifier;
/*     */   }
/*     */   
/* 128 */   public void setBluetoothIdentifier(String bluetoothIdentifier) { this.bluetoothIdentifier = bluetoothIdentifier; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getCenterControllerId()
/*     */   {
/* 136 */     return this.centerControllerId;
/*     */   }
/*     */   
/* 139 */   public void setCenterControllerId(Long centerControllerId) { this.centerControllerId = centerControllerId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getCityId()
/*     */   {
/* 147 */     return this.cityId;
/*     */   }
/*     */   
/* 150 */   public void setCityId(String cityId) { this.cityId = cityId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getCreateBy()
/*     */   {
/* 158 */     return this.createBy;
/*     */   }
/*     */   
/* 161 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getCreateTime()
/*     */   {
/* 169 */     return this.createTime;
/*     */   }
/*     */   
/* 172 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getDisTime()
/*     */   {
/* 180 */     return this.disTime;
/*     */   }
/*     */   
/* 183 */   public void setDisTime(Date disTime) { this.disTime = disTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getDistance()
/*     */   {
/* 191 */     return this.distance;
/*     */   }
/*     */   
/* 194 */   public void setDistance(Double distance) { this.distance = distance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getElectricity()
/*     */   {
/* 202 */     return this.electricity;
/*     */   }
/*     */   
/* 205 */   public void setElectricity(Integer electricity) { this.electricity = electricity; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getElectricityAlarm()
/*     */   {
/* 213 */     return this.electricityAlarm;
/*     */   }
/*     */   
/* 216 */   public void setElectricityAlarm(Integer electricityAlarm) { this.electricityAlarm = electricityAlarm; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getElectrombileCode()
/*     */   {
/* 224 */     return this.electrombileCode;
/*     */   }
/*     */   
/* 227 */   public void setElectrombileCode(String electrombileCode) { this.electrombileCode = electrombileCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/* 235 */     return this.id;
/*     */   }
/*     */   
/* 238 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getLastHeartbeat()
/*     */   {
/* 246 */     return this.lastHeartbeat;
/*     */   }
/*     */   
/* 249 */   public void setLastHeartbeat(Date lastHeartbeat) { this.lastHeartbeat = lastHeartbeat; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getLastLocationDetails()
/*     */   {
/* 257 */     return this.lastLocationDetails;
/*     */   }
/*     */   
/* 260 */   public void setLastLocationDetails(String lastLocationDetails) { this.lastLocationDetails = lastLocationDetails; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getLastLocationTime()
/*     */   {
/* 268 */     return this.lastLocationTime;
/*     */   }
/*     */   
/* 271 */   public void setLastLocationTime(Date lastLocationTime) { this.lastLocationTime = lastLocationTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getLocationDetails()
/*     */   {
/* 279 */     return this.locationDetails;
/*     */   }
/*     */   
/* 282 */   public void setLocationDetails(String locationDetails) { this.locationDetails = locationDetails; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getMotorPower()
/*     */   {
/* 290 */     return this.motorPower;
/*     */   }
/*     */   
/* 293 */   public void setMotorPower(Integer motorPower) { this.motorPower = motorPower; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getOfficeId()
/*     */   {
/* 301 */     return this.officeId;
/*     */   }
/*     */   
/* 304 */   public void setOfficeId(Long officeId) { this.officeId = officeId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getOfficeName()
/*     */   {
/* 312 */     return this.officeName;
/*     */   }
/*     */   
/* 315 */   public void setOfficeName(String officeName) { this.officeName = officeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getOldElectricity()
/*     */   {
/* 323 */     return this.oldElectricity;
/*     */   }
/*     */   
/* 326 */   public void setOldElectricity(Integer oldElectricity) { this.oldElectricity = oldElectricity; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Point getPoint()
/*     */   {
/* 334 */     return this.point;
/*     */   }
/*     */   
/* 337 */   public void setPoint(Point point) { this.point = point; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getPositionerCode()
/*     */   {
/* 345 */     return this.positionerCode;
/*     */   }
/*     */   
/* 348 */   public void setPositionerCode(String positionerCode) { this.positionerCode = positionerCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getPositionerId()
/*     */   {
/* 356 */     return this.positionerId;
/*     */   }
/*     */   
/* 359 */   public void setPositionerId(Long positionerId) { this.positionerId = positionerId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getRechargeMileage()
/*     */   {
/* 367 */     return this.rechargeMileage;
/*     */   }
/*     */   
/* 370 */   public void setRechargeMileage(Double rechargeMileage) { this.rechargeMileage = rechargeMileage; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getSpeed()
/*     */   {
/* 378 */     return this.speed;
/*     */   }
/*     */   
/* 381 */   public void setSpeed(Integer speed) { this.speed = speed; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getStatus()
/*     */   {
/* 389 */     return this.status;
/*     */   }
/*     */   
/* 392 */   public void setStatus(Integer status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSysCode()
/*     */   {
/* 400 */     return this.sysCode;
/*     */   }
/*     */   
/* 403 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getTotalIncome()
/*     */   {
/* 411 */     return this.totalIncome;
/*     */   }
/*     */   
/* 414 */   public void setTotalIncome(Double totalIncome) { this.totalIncome = totalIncome; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getTotalRideDistance()
/*     */   {
/* 422 */     return this.totalRideDistance;
/*     */   }
/*     */   
/* 425 */   public void setTotalRideDistance(Double totalRideDistance) { this.totalRideDistance = totalRideDistance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getTotalUseTimes()
/*     */   {
/* 433 */     return this.totalUseTimes;
/*     */   }
/*     */   
/* 436 */   public void setTotalUseTimes(Integer totalUseTimes) { this.totalUseTimes = totalUseTimes; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getTypeId()
/*     */   {
/* 444 */     return this.typeId;
/*     */   }
/*     */   
/* 447 */   public void setTypeId(Long typeId) { this.typeId = typeId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTypeName()
/*     */   {
/* 455 */     return this.typeName;
/*     */   }
/*     */   
/* 458 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getUpdateBy()
/*     */   {
/* 466 */     return this.updateBy;
/*     */   }
/*     */   
/* 469 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getUpdateTime()
/*     */   {
/* 477 */     return this.updateTime;
/*     */   }
/*     */   
/* 480 */   public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getVoltage()
/*     */   {
/* 488 */     return this.voltage;
/*     */   }
/*     */   
/* 491 */   public void setVoltage(Integer voltage) { this.voltage = voltage; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 498 */     if (this == o) {
/* 499 */       return true;
/*     */     }
/* 501 */     if ((o == null) || (getClass() != o.getClass())) {
/* 502 */       return false;
/*     */     }
/* 504 */     ElectrombileBO electrombileBO = (ElectrombileBO)o;
/* 505 */     return (Objects.equals(this.bluetoothIdentifier, electrombileBO.bluetoothIdentifier)) && 
/* 506 */       (Objects.equals(this.centerControllerId, electrombileBO.centerControllerId)) && 
/* 507 */       (Objects.equals(this.cityId, electrombileBO.cityId)) && 
/* 508 */       (Objects.equals(this.createBy, electrombileBO.createBy)) && 
/* 509 */       (Objects.equals(this.createTime, electrombileBO.createTime)) && 
/* 510 */       (Objects.equals(this.disTime, electrombileBO.disTime)) && 
/* 511 */       (Objects.equals(this.distance, electrombileBO.distance)) && 
/* 512 */       (Objects.equals(this.electricity, electrombileBO.electricity)) && 
/* 513 */       (Objects.equals(this.electricityAlarm, electrombileBO.electricityAlarm)) && 
/* 514 */       (Objects.equals(this.electrombileCode, electrombileBO.electrombileCode)) && 
/* 515 */       (Objects.equals(this.id, electrombileBO.id)) && 
/* 516 */       (Objects.equals(this.lastHeartbeat, electrombileBO.lastHeartbeat)) && 
/* 517 */       (Objects.equals(this.lastLocationDetails, electrombileBO.lastLocationDetails)) && 
/* 518 */       (Objects.equals(this.lastLocationTime, electrombileBO.lastLocationTime)) && 
/* 519 */       (Objects.equals(this.locationDetails, electrombileBO.locationDetails)) && 
/* 520 */       (Objects.equals(this.motorPower, electrombileBO.motorPower)) && 
/* 521 */       (Objects.equals(this.officeId, electrombileBO.officeId)) && 
/* 522 */       (Objects.equals(this.officeName, electrombileBO.officeName)) && 
/* 523 */       (Objects.equals(this.oldElectricity, electrombileBO.oldElectricity)) && 
/* 524 */       (Objects.equals(this.point, electrombileBO.point)) && 
/* 525 */       (Objects.equals(this.positionerCode, electrombileBO.positionerCode)) && 
/* 526 */       (Objects.equals(this.positionerId, electrombileBO.positionerId)) && 
/* 527 */       (Objects.equals(this.rechargeMileage, electrombileBO.rechargeMileage)) && 
/* 528 */       (Objects.equals(this.speed, electrombileBO.speed)) && 
/* 529 */       (Objects.equals(this.status, electrombileBO.status)) && 
/* 530 */       (Objects.equals(this.sysCode, electrombileBO.sysCode)) && 
/* 531 */       (Objects.equals(this.totalIncome, electrombileBO.totalIncome)) && 
/* 532 */       (Objects.equals(this.totalRideDistance, electrombileBO.totalRideDistance)) && 
/* 533 */       (Objects.equals(this.totalUseTimes, electrombileBO.totalUseTimes)) && 
/* 534 */       (Objects.equals(this.typeId, electrombileBO.typeId)) && 
/* 535 */       (Objects.equals(this.typeName, electrombileBO.typeName)) && 
/* 536 */       (Objects.equals(this.updateBy, electrombileBO.updateBy)) && 
/* 537 */       (Objects.equals(this.updateTime, electrombileBO.updateTime)) && 
/* 538 */       (Objects.equals(this.voltage, electrombileBO.voltage));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 543 */     return Objects.hash(new Object[] { this.bluetoothIdentifier, this.centerControllerId, this.cityId, this.createBy, this.createTime, this.disTime, this.distance, this.electricity, this.electricityAlarm, this.electrombileCode, this.id, this.lastHeartbeat, this.lastLocationDetails, this.lastLocationTime, this.locationDetails, this.motorPower, this.officeId, this.officeName, this.oldElectricity, this.point, this.positionerCode, this.positionerId, this.rechargeMileage, this.speed, this.status, this.sysCode, this.totalIncome, this.totalRideDistance, this.totalUseTimes, this.typeId, this.typeName, this.updateBy, this.updateTime, this.voltage });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 548 */     StringBuilder sb = new StringBuilder();
/* 549 */     sb.append("class ElectrombileBO {\n");
/*     */     
/* 551 */     sb.append("    bluetoothIdentifier: ").append(toIndentedString(this.bluetoothIdentifier)).append("\n");
/* 552 */     sb.append("    centerControllerId: ").append(toIndentedString(this.centerControllerId)).append("\n");
/* 553 */     sb.append("    cityId: ").append(toIndentedString(this.cityId)).append("\n");
/* 554 */     sb.append("    createBy: ").append(toIndentedString(this.createBy)).append("\n");
/* 555 */     sb.append("    createTime: ").append(toIndentedString(this.createTime)).append("\n");
/* 556 */     sb.append("    disTime: ").append(toIndentedString(this.disTime)).append("\n");
/* 557 */     sb.append("    distance: ").append(toIndentedString(this.distance)).append("\n");
/* 558 */     sb.append("    electricity: ").append(toIndentedString(this.electricity)).append("\n");
/* 559 */     sb.append("    electricityAlarm: ").append(toIndentedString(this.electricityAlarm)).append("\n");
/* 560 */     sb.append("    electrombileCode: ").append(toIndentedString(this.electrombileCode)).append("\n");
/* 561 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 562 */     sb.append("    lastHeartbeat: ").append(toIndentedString(this.lastHeartbeat)).append("\n");
/* 563 */     sb.append("    lastLocationDetails: ").append(toIndentedString(this.lastLocationDetails)).append("\n");
/* 564 */     sb.append("    lastLocationTime: ").append(toIndentedString(this.lastLocationTime)).append("\n");
/* 565 */     sb.append("    locationDetails: ").append(toIndentedString(this.locationDetails)).append("\n");
/* 566 */     sb.append("    motorPower: ").append(toIndentedString(this.motorPower)).append("\n");
/* 567 */     sb.append("    officeId: ").append(toIndentedString(this.officeId)).append("\n");
/* 568 */     sb.append("    officeName: ").append(toIndentedString(this.officeName)).append("\n");
/* 569 */     sb.append("    oldElectricity: ").append(toIndentedString(this.oldElectricity)).append("\n");
/* 570 */     sb.append("    point: ").append(toIndentedString(this.point)).append("\n");
/* 571 */     sb.append("    positionerCode: ").append(toIndentedString(this.positionerCode)).append("\n");
/* 572 */     sb.append("    positionerId: ").append(toIndentedString(this.positionerId)).append("\n");
/* 573 */     sb.append("    rechargeMileage: ").append(toIndentedString(this.rechargeMileage)).append("\n");
/* 574 */     sb.append("    speed: ").append(toIndentedString(this.speed)).append("\n");
/* 575 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 576 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 577 */     sb.append("    totalIncome: ").append(toIndentedString(this.totalIncome)).append("\n");
/* 578 */     sb.append("    totalRideDistance: ").append(toIndentedString(this.totalRideDistance)).append("\n");
/* 579 */     sb.append("    totalUseTimes: ").append(toIndentedString(this.totalUseTimes)).append("\n");
/* 580 */     sb.append("    typeId: ").append(toIndentedString(this.typeId)).append("\n");
/* 581 */     sb.append("    typeName: ").append(toIndentedString(this.typeName)).append("\n");
/* 582 */     sb.append("    updateBy: ").append(toIndentedString(this.updateBy)).append("\n");
/* 583 */     sb.append("    updateTime: ").append(toIndentedString(this.updateTime)).append("\n");
/* 584 */     sb.append("    voltage: ").append(toIndentedString(this.voltage)).append("\n");
/* 585 */     sb.append("}");
/* 586 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 594 */     if (o == null) {
/* 595 */       return "null";
/*     */     }
/* 597 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ElectrombileBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */