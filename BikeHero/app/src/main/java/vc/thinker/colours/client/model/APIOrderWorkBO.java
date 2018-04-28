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
/*     */ public class APIOrderWorkBO
/*     */ {
/*     */   @SerializedName("beginLat")
/*  17 */   private Double beginLat = null;
/*     */   
/*     */   @SerializedName("beginLon")
/*  20 */   private Double beginLon = null;
/*     */   
/*     */   @SerializedName("beginPlace")
/*  23 */   private String beginPlace = null;
/*     */   
/*     */   @SerializedName("beginTime")
/*  26 */   private Date beginTime = null;
/*     */   
/*     */   @SerializedName("bicycleLocationDetails")
/*  29 */   private String bicycleLocationDetails = null;
/*     */   
/*     */   @SerializedName("bicycleLocationLon")
/*  32 */   private Double bicycleLocationLon = null;
/*     */   
/*     */   @SerializedName("bicycleLocationlat")
/*  35 */   private Double bicycleLocationlat = null;
/*     */   
/*     */   @SerializedName("bluetoothCode")
/*  38 */   private String bluetoothCode = null;
/*     */   
/*     */   @SerializedName("completeStatus")
/*  41 */   private Integer completeStatus = null;
/*     */   
/*     */   @SerializedName("createTime")
/*  44 */   private Date createTime = null;
/*     */   
/*     */   @SerializedName("dealImgs")
/*  47 */   private List<String> dealImgs = new ArrayList();
/*     */   
/*     */   @SerializedName("dealRemark")
/*  50 */   private String dealRemark = null;
/*     */   
/*     */   @SerializedName("defaultMacPwd")
/*  53 */   private String defaultMacPwd = null;
/*     */   
/*     */   @SerializedName("defaultMacSecretKey")
/*  56 */   private String defaultMacSecretKey = null;
/*     */   
/*     */   @SerializedName("faultImgs")
/*  59 */   private List<String> faultImgs = new ArrayList();
/*     */   
/*     */   @SerializedName("finishLat")
/*  62 */   private Double finishLat = null;
/*     */   
/*     */   @SerializedName("finishLon")
/*  65 */   private Double finishLon = null;
/*     */   
/*     */   @SerializedName("finishPlace")
/*  68 */   private String finishPlace = null;
/*     */   
/*     */   @SerializedName("finishTime")
/*  71 */   private Date finishTime = null;
/*     */   
/*     */   @SerializedName("launchTime")
/*  74 */   private Date launchTime = null;
/*     */   
/*     */   @SerializedName("lockMacAddress")
/*  77 */   private String lockMacAddress = null;
/*     */   
/*     */   @SerializedName("macPwd")
/*  80 */   private String macPwd = null;
/*     */   
/*     */   @SerializedName("macSecretKey")
/*  83 */   private String macSecretKey = null;
/*     */   
/*     */   @SerializedName("openType")
/*  86 */   private Integer openType = null;
/*     */   
/*     */   @SerializedName("orderCode")
/*  89 */   private String orderCode = null;
/*     */   
/*     */   @SerializedName("rejectTime")
/*  92 */   private Date rejectTime = null;
/*     */   
/*     */   @SerializedName("repairerId")
/*  95 */   private Long repairerId = null;
/*     */   
/*     */   @SerializedName("repairerJoinTime")
/*  98 */   private Date repairerJoinTime = null;
/*     */   
/*     */   @SerializedName("repairerStatus")
/* 101 */   private Integer repairerStatus = null;
/*     */   
/*     */   @SerializedName("status")
/* 104 */   private Integer status = null;
/*     */   
/*     */   @SerializedName("sysCode")
/* 107 */   private String sysCode = null;
/*     */   
/*     */   @SerializedName("typeId")
/* 110 */   private String typeId = null;
/*     */   
/*     */   @SerializedName("typeName")
/* 113 */   private String typeName = null;
/*     */   
/*     */   @SerializedName("updateTime")
/* 116 */   private Date updateTime = null;
/*     */   
/*     */   @SerializedName("workDesc")
/* 119 */   private String workDesc = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Double getBeginLat()
/*     */   {
/* 128 */     return this.beginLat;
/*     */   }
/*     */   
/* 131 */   public void setBeginLat(Double beginLat) { this.beginLat = beginLat; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Double getBeginLon()
/*     */   {
/* 139 */     return this.beginLon;
/*     */   }
/*     */   
/* 142 */   public void setBeginLon(Double beginLon) { this.beginLon = beginLon; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("接单地点全路径")
/*     */   public String getBeginPlace()
/*     */   {
/* 151 */     return this.beginPlace;
/*     */   }
/*     */   
/* 154 */   public void setBeginPlace(String beginPlace) { this.beginPlace = beginPlace; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("接单时间")
/*     */   public Date getBeginTime()
/*     */   {
/* 163 */     return this.beginTime;
/*     */   }
/*     */   
/* 166 */   public void setBeginTime(Date beginTime) { this.beginTime = beginTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("车锁位置详情")
/*     */   public String getBicycleLocationDetails()
/*     */   {
/* 175 */     return this.bicycleLocationDetails;
/*     */   }
/*     */   
/* 178 */   public void setBicycleLocationDetails(String bicycleLocationDetails) { this.bicycleLocationDetails = bicycleLocationDetails; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Double getBicycleLocationLon()
/*     */   {
/* 186 */     return this.bicycleLocationLon;
/*     */   }
/*     */   
/* 189 */   public void setBicycleLocationLon(Double bicycleLocationLon) { this.bicycleLocationLon = bicycleLocationLon; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Double getBicycleLocationlat()
/*     */   {
/* 197 */     return this.bicycleLocationlat;
/*     */   }
/*     */   
/* 200 */   public void setBicycleLocationlat(Double bicycleLocationlat) { this.bicycleLocationlat = bicycleLocationlat; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("蓝牙协议版本")
/*     */   public String getBluetoothCode()
/*     */   {
/* 209 */     return this.bluetoothCode;
/*     */   }
/*     */   
/* 212 */   public void setBluetoothCode(String bluetoothCode) { this.bluetoothCode = bluetoothCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("工单完成状态 1,正常完成，2 异常完成")
/*     */   public Integer getCompleteStatus()
/*     */   {
/* 221 */     return this.completeStatus;
/*     */   }
/*     */   
/* 224 */   public void setCompleteStatus(Integer completeStatus) { this.completeStatus = completeStatus; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Date getCreateTime()
/*     */   {
/* 232 */     return this.createTime;
/*     */   }
/*     */   
/* 235 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public List<String> getDealImgs()
/*     */   {
/* 243 */     return this.dealImgs;
/*     */   }
/*     */   
/* 246 */   public void setDealImgs(List<String> dealImgs) { this.dealImgs = dealImgs; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getDealRemark()
/*     */   {
/* 254 */     return this.dealRemark;
/*     */   }
/*     */   
/* 257 */   public void setDealRemark(String dealRemark) { this.dealRemark = dealRemark; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getDefaultMacPwd()
/*     */   {
/* 265 */     return this.defaultMacPwd;
/*     */   }
/*     */   
/* 268 */   public void setDefaultMacPwd(String defaultMacPwd) { this.defaultMacPwd = defaultMacPwd; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getDefaultMacSecretKey()
/*     */   {
/* 276 */     return this.defaultMacSecretKey;
/*     */   }
/*     */   
/* 279 */   public void setDefaultMacSecretKey(String defaultMacSecretKey) { this.defaultMacSecretKey = defaultMacSecretKey; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("故障图片列表")
/*     */   public List<String> getFaultImgs()
/*     */   {
/* 288 */     return this.faultImgs;
/*     */   }
/*     */   
/* 291 */   public void setFaultImgs(List<String> faultImgs) { this.faultImgs = faultImgs; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Double getFinishLat()
/*     */   {
/* 299 */     return this.finishLat;
/*     */   }
/*     */   
/* 302 */   public void setFinishLat(Double finishLat) { this.finishLat = finishLat; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Double getFinishLon()
/*     */   {
/* 310 */     return this.finishLon;
/*     */   }
/*     */   
/* 313 */   public void setFinishLon(Double finishLon) { this.finishLon = finishLon; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getFinishPlace()
/*     */   {
/* 321 */     return this.finishPlace;
/*     */   }
/*     */   
/* 324 */   public void setFinishPlace(String finishPlace) { this.finishPlace = finishPlace; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("完成时间")
/*     */   public Date getFinishTime()
/*     */   {
/* 333 */     return this.finishTime;
/*     */   }
/*     */   
/* 336 */   public void setFinishTime(Date finishTime) { this.finishTime = finishTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("发起时间")
/*     */   public Date getLaunchTime()
/*     */   {
/* 345 */     return this.launchTime;
/*     */   }
/*     */   
/* 348 */   public void setLaunchTime(Date launchTime) { this.launchTime = launchTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("锁的mac地址")
/*     */   public String getLockMacAddress()
/*     */   {
/* 357 */     return this.lockMacAddress;
/*     */   }
/*     */   
/* 360 */   public void setLockMacAddress(String lockMacAddress) { this.lockMacAddress = lockMacAddress; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getMacPwd()
/*     */   {
/* 368 */     return this.macPwd;
/*     */   }
/*     */   
/* 371 */   public void setMacPwd(String macPwd) { this.macPwd = macPwd; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getMacSecretKey()
/*     */   {
/* 379 */     return this.macSecretKey;
/*     */   }
/*     */   
/* 382 */   public void setMacSecretKey(String macSecretKey) { this.macSecretKey = macSecretKey; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("打开方式 1：GPRS，2：蓝牙，3：GPRS 和 蓝牙")
/*     */   public Integer getOpenType()
/*     */   {
/* 391 */     return this.openType;
/*     */   }
/*     */   
/* 394 */   public void setOpenType(Integer openType) { this.openType = openType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getOrderCode()
/*     */   {
/* 402 */     return this.orderCode;
/*     */   }
/*     */   
/* 405 */   public void setOrderCode(String orderCode) { this.orderCode = orderCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("拒绝时间")
/*     */   public Date getRejectTime()
/*     */   {
/* 414 */     return this.rejectTime;
/*     */   }
/*     */   
/* 417 */   public void setRejectTime(Date rejectTime) { this.rejectTime = rejectTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Long getRepairerId()
/*     */   {
/* 425 */     return this.repairerId;
/*     */   }
/*     */   
/* 428 */   public void setRepairerId(Long repairerId) { this.repairerId = repairerId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("维保人加入时间")
/*     */   public Date getRepairerJoinTime()
/*     */   {
/* 437 */     return this.repairerJoinTime;
/*     */   }
/*     */   
/* 440 */   public void setRepairerJoinTime(Date repairerJoinTime) { this.repairerJoinTime = repairerJoinTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("维保状态,10 待处理，20 已拒绝, 30进行中，40已完成")
/*     */   public Integer getRepairerStatus()
/*     */   {
/* 449 */     return this.repairerStatus;
/*     */   }
/*     */   
/* 452 */   public void setRepairerStatus(Integer repairerStatus) { this.repairerStatus = repairerStatus; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("工单状态,10 待处理，30进行中，40已完成")
/*     */   public Integer getStatus()
/*     */   {
/* 461 */     return this.status;
/*     */   }
/*     */   
/* 464 */   public void setStatus(Integer status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("锁系统编号")
/*     */   public String getSysCode()
/*     */   {
/* 473 */     return this.sysCode;
/*     */   }
/*     */   
/* 476 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getTypeId()
/*     */   {
/* 484 */     return this.typeId;
/*     */   }
/*     */   
/* 487 */   public void setTypeId(String typeId) { this.typeId = typeId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("故障类似名称")
/*     */   public String getTypeName()
/*     */   {
/* 496 */     return this.typeName;
/*     */   }
/*     */   
/* 499 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Date getUpdateTime()
/*     */   {
/* 507 */     return this.updateTime;
/*     */   }
/*     */   
/* 510 */   public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("任务描述")
/*     */   public String getWorkDesc()
/*     */   {
/* 519 */     return this.workDesc;
/*     */   }
/*     */   
/* 522 */   public void setWorkDesc(String workDesc) { this.workDesc = workDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 529 */     if (this == o) {
/* 530 */       return true;
/*     */     }
/* 532 */     if ((o == null) || (getClass() != o.getClass())) {
/* 533 */       return false;
/*     */     }
/* 535 */     APIOrderWorkBO aPIOrderWorkBO = (APIOrderWorkBO)o;
/* 536 */     return (Objects.equals(this.beginLat, aPIOrderWorkBO.beginLat)) && 
/* 537 */       (Objects.equals(this.beginLon, aPIOrderWorkBO.beginLon)) && 
/* 538 */       (Objects.equals(this.beginPlace, aPIOrderWorkBO.beginPlace)) && 
/* 539 */       (Objects.equals(this.beginTime, aPIOrderWorkBO.beginTime)) && 
/* 540 */       (Objects.equals(this.bicycleLocationDetails, aPIOrderWorkBO.bicycleLocationDetails)) && 
/* 541 */       (Objects.equals(this.bicycleLocationLon, aPIOrderWorkBO.bicycleLocationLon)) && 
/* 542 */       (Objects.equals(this.bicycleLocationlat, aPIOrderWorkBO.bicycleLocationlat)) && 
/* 543 */       (Objects.equals(this.bluetoothCode, aPIOrderWorkBO.bluetoothCode)) && 
/* 544 */       (Objects.equals(this.completeStatus, aPIOrderWorkBO.completeStatus)) && 
/* 545 */       (Objects.equals(this.createTime, aPIOrderWorkBO.createTime)) && 
/* 546 */       (Objects.equals(this.dealImgs, aPIOrderWorkBO.dealImgs)) && 
/* 547 */       (Objects.equals(this.dealRemark, aPIOrderWorkBO.dealRemark)) && 
/* 548 */       (Objects.equals(this.defaultMacPwd, aPIOrderWorkBO.defaultMacPwd)) && 
/* 549 */       (Objects.equals(this.defaultMacSecretKey, aPIOrderWorkBO.defaultMacSecretKey)) && 
/* 550 */       (Objects.equals(this.faultImgs, aPIOrderWorkBO.faultImgs)) && 
/* 551 */       (Objects.equals(this.finishLat, aPIOrderWorkBO.finishLat)) && 
/* 552 */       (Objects.equals(this.finishLon, aPIOrderWorkBO.finishLon)) && 
/* 553 */       (Objects.equals(this.finishPlace, aPIOrderWorkBO.finishPlace)) && 
/* 554 */       (Objects.equals(this.finishTime, aPIOrderWorkBO.finishTime)) && 
/* 555 */       (Objects.equals(this.launchTime, aPIOrderWorkBO.launchTime)) && 
/* 556 */       (Objects.equals(this.lockMacAddress, aPIOrderWorkBO.lockMacAddress)) && 
/* 557 */       (Objects.equals(this.macPwd, aPIOrderWorkBO.macPwd)) && 
/* 558 */       (Objects.equals(this.macSecretKey, aPIOrderWorkBO.macSecretKey)) && 
/* 559 */       (Objects.equals(this.openType, aPIOrderWorkBO.openType)) && 
/* 560 */       (Objects.equals(this.orderCode, aPIOrderWorkBO.orderCode)) && 
/* 561 */       (Objects.equals(this.rejectTime, aPIOrderWorkBO.rejectTime)) && 
/* 562 */       (Objects.equals(this.repairerId, aPIOrderWorkBO.repairerId)) && 
/* 563 */       (Objects.equals(this.repairerJoinTime, aPIOrderWorkBO.repairerJoinTime)) && 
/* 564 */       (Objects.equals(this.repairerStatus, aPIOrderWorkBO.repairerStatus)) && 
/* 565 */       (Objects.equals(this.status, aPIOrderWorkBO.status)) && 
/* 566 */       (Objects.equals(this.sysCode, aPIOrderWorkBO.sysCode)) && 
/* 567 */       (Objects.equals(this.typeId, aPIOrderWorkBO.typeId)) && 
/* 568 */       (Objects.equals(this.typeName, aPIOrderWorkBO.typeName)) && 
/* 569 */       (Objects.equals(this.updateTime, aPIOrderWorkBO.updateTime)) && 
/* 570 */       (Objects.equals(this.workDesc, aPIOrderWorkBO.workDesc));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 575 */     return Objects.hash(new Object[] { this.beginLat, this.beginLon, this.beginPlace, this.beginTime, this.bicycleLocationDetails, this.bicycleLocationLon, this.bicycleLocationlat, this.bluetoothCode, this.completeStatus, this.createTime, this.dealImgs, this.dealRemark, this.defaultMacPwd, this.defaultMacSecretKey, this.faultImgs, this.finishLat, this.finishLon, this.finishPlace, this.finishTime, this.launchTime, this.lockMacAddress, this.macPwd, this.macSecretKey, this.openType, this.orderCode, this.rejectTime, this.repairerId, this.repairerJoinTime, this.repairerStatus, this.status, this.sysCode, this.typeId, this.typeName, this.updateTime, this.workDesc });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 580 */     StringBuilder sb = new StringBuilder();
/* 581 */     sb.append("class APIOrderWorkBO {\n");
/*     */     
/* 583 */     sb.append("    beginLat: ").append(toIndentedString(this.beginLat)).append("\n");
/* 584 */     sb.append("    beginLon: ").append(toIndentedString(this.beginLon)).append("\n");
/* 585 */     sb.append("    beginPlace: ").append(toIndentedString(this.beginPlace)).append("\n");
/* 586 */     sb.append("    beginTime: ").append(toIndentedString(this.beginTime)).append("\n");
/* 587 */     sb.append("    bicycleLocationDetails: ").append(toIndentedString(this.bicycleLocationDetails)).append("\n");
/* 588 */     sb.append("    bicycleLocationLon: ").append(toIndentedString(this.bicycleLocationLon)).append("\n");
/* 589 */     sb.append("    bicycleLocationlat: ").append(toIndentedString(this.bicycleLocationlat)).append("\n");
/* 590 */     sb.append("    bluetoothCode: ").append(toIndentedString(this.bluetoothCode)).append("\n");
/* 591 */     sb.append("    completeStatus: ").append(toIndentedString(this.completeStatus)).append("\n");
/* 592 */     sb.append("    createTime: ").append(toIndentedString(this.createTime)).append("\n");
/* 593 */     sb.append("    dealImgs: ").append(toIndentedString(this.dealImgs)).append("\n");
/* 594 */     sb.append("    dealRemark: ").append(toIndentedString(this.dealRemark)).append("\n");
/* 595 */     sb.append("    defaultMacPwd: ").append(toIndentedString(this.defaultMacPwd)).append("\n");
/* 596 */     sb.append("    defaultMacSecretKey: ").append(toIndentedString(this.defaultMacSecretKey)).append("\n");
/* 597 */     sb.append("    faultImgs: ").append(toIndentedString(this.faultImgs)).append("\n");
/* 598 */     sb.append("    finishLat: ").append(toIndentedString(this.finishLat)).append("\n");
/* 599 */     sb.append("    finishLon: ").append(toIndentedString(this.finishLon)).append("\n");
/* 600 */     sb.append("    finishPlace: ").append(toIndentedString(this.finishPlace)).append("\n");
/* 601 */     sb.append("    finishTime: ").append(toIndentedString(this.finishTime)).append("\n");
/* 602 */     sb.append("    launchTime: ").append(toIndentedString(this.launchTime)).append("\n");
/* 603 */     sb.append("    lockMacAddress: ").append(toIndentedString(this.lockMacAddress)).append("\n");
/* 604 */     sb.append("    macPwd: ").append(toIndentedString(this.macPwd)).append("\n");
/* 605 */     sb.append("    macSecretKey: ").append(toIndentedString(this.macSecretKey)).append("\n");
/* 606 */     sb.append("    openType: ").append(toIndentedString(this.openType)).append("\n");
/* 607 */     sb.append("    orderCode: ").append(toIndentedString(this.orderCode)).append("\n");
/* 608 */     sb.append("    rejectTime: ").append(toIndentedString(this.rejectTime)).append("\n");
/* 609 */     sb.append("    repairerId: ").append(toIndentedString(this.repairerId)).append("\n");
/* 610 */     sb.append("    repairerJoinTime: ").append(toIndentedString(this.repairerJoinTime)).append("\n");
/* 611 */     sb.append("    repairerStatus: ").append(toIndentedString(this.repairerStatus)).append("\n");
/* 612 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 613 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 614 */     sb.append("    typeId: ").append(toIndentedString(this.typeId)).append("\n");
/* 615 */     sb.append("    typeName: ").append(toIndentedString(this.typeName)).append("\n");
/* 616 */     sb.append("    updateTime: ").append(toIndentedString(this.updateTime)).append("\n");
/* 617 */     sb.append("    workDesc: ").append(toIndentedString(this.workDesc)).append("\n");
/* 618 */     sb.append("}");
/* 619 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 627 */     if (o == null) {
/* 628 */       return "null";
/*     */     }
/* 630 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIOrderWorkBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */