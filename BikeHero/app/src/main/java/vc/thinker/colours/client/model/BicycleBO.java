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
/*     */ public class BicycleBO
/*     */ {
/*     */   @SerializedName("bluetoothCode")
/*  18 */   private String bluetoothCode = null;
/*     */   
/*     */   @SerializedName("defaultMacPwd")
/*  21 */   private String defaultMacPwd = null;
/*     */   
/*     */   @SerializedName("defaultMacSecretKey")
/*  24 */   private String defaultMacSecretKey = null;
/*     */   
/*     */   @SerializedName("distance")
/*  27 */   private Double distance = null;
/*     */   
/*     */   @SerializedName("doingOrderWork")
/*  30 */   private APIOrderWorkBO doingOrderWork = null;
/*     */   
/*     */   @SerializedName("electricity")
/*  33 */   private Integer electricity = null;
/*     */   
/*     */   @SerializedName("lastHeartbeat")
/*  36 */   private Date lastHeartbeat = null;
/*     */   
/*     */   @SerializedName("lastLocationTime")
/*  39 */   private Date lastLocationTime = null;
/*     */   
/*     */   @SerializedName("locationDetails")
/*  42 */   private String locationDetails = null;
/*     */   
/*     */   @SerializedName("lockMacAddress")
/*  45 */   private String lockMacAddress = null;
/*     */   
/*     */   @SerializedName("macPwd")
/*  48 */   private String macPwd = null;
/*     */   
/*     */   @SerializedName("macSecretKey")
/*  51 */   private String macSecretKey = null;
/*     */   
/*     */   @SerializedName("openType")
/*  54 */   private Integer openType = null;
/*     */   
/*     */   @SerializedName("point")
/*  57 */   private Point point = null;
/*     */   
/*     */   @SerializedName("price")
/*  60 */   private Double price = null;
/*     */   
/*     */   @SerializedName("priceMinute")
/*  63 */   private Integer priceMinute = null;
/*     */   
/*     */   @SerializedName("status")
/*  66 */   private Integer status = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  69 */   private String sysCode = null;
/*     */   
/*     */   @SerializedName("walkTime")
/*  72 */   private Integer walkTime = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙协议版本")
/*     */   public String getBluetoothCode()
/*     */   {
/*  82 */     return this.bluetoothCode;
/*     */   }
/*     */   
/*  85 */   public void setBluetoothCode(String bluetoothCode) { this.bluetoothCode = bluetoothCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getDefaultMacPwd()
/*     */   {
/*  93 */     return this.defaultMacPwd;
/*     */   }
/*     */   
/*  96 */   public void setDefaultMacPwd(String defaultMacPwd) { this.defaultMacPwd = defaultMacPwd; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getDefaultMacSecretKey()
/*     */   {
/* 104 */     return this.defaultMacSecretKey;
/*     */   }
/*     */   
/* 107 */   public void setDefaultMacSecretKey(String defaultMacSecretKey) { this.defaultMacSecretKey = defaultMacSecretKey; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("距离")
/*     */   public Double getDistance()
/*     */   {
/* 116 */     return this.distance;
/*     */   }
/*     */   
/* 119 */   public void setDistance(Double distance) { this.distance = distance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("进行中工单")
/*     */   public APIOrderWorkBO getDoingOrderWork()
/*     */   {
/* 128 */     return this.doingOrderWork;
/*     */   }
/*     */   
/* 131 */   public void setDoingOrderWork(APIOrderWorkBO doingOrderWork) { this.doingOrderWork = doingOrderWork; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("电量")
/*     */   public Integer getElectricity()
/*     */   {
/* 140 */     return this.electricity;
/*     */   }
/*     */   
/* 143 */   public void setElectricity(Integer electricity) { this.electricity = electricity; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("最后一次心跳时间")
/*     */   public Date getLastHeartbeat()
/*     */   {
/* 152 */     return this.lastHeartbeat;
/*     */   }
/*     */   
/* 155 */   public void setLastHeartbeat(Date lastHeartbeat) { this.lastHeartbeat = lastHeartbeat; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("最后一次位置时间")
/*     */   public Date getLastLocationTime()
/*     */   {
/* 164 */     return this.lastLocationTime;
/*     */   }
/*     */   
/* 167 */   public void setLastLocationTime(Date lastLocationTime) { this.lastLocationTime = lastLocationTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("位置详情")
/*     */   public String getLocationDetails()
/*     */   {
/* 176 */     return this.locationDetails;
/*     */   }
/*     */   
/* 179 */   public void setLocationDetails(String locationDetails) { this.locationDetails = locationDetails; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("锁的mac地址")
/*     */   public String getLockMacAddress()
/*     */   {
/* 188 */     return this.lockMacAddress;
/*     */   }
/*     */   
/* 191 */   public void setLockMacAddress(String lockMacAddress) { this.lockMacAddress = lockMacAddress; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙连接的密码")
/*     */   public String getMacPwd()
/*     */   {
/* 200 */     return this.macPwd;
/*     */   }
/*     */   
/* 203 */   public void setMacPwd(String macPwd) { this.macPwd = macPwd; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙连接的密钥")
/*     */   public String getMacSecretKey()
/*     */   {
/* 212 */     return this.macSecretKey;
/*     */   }
/*     */   
/* 215 */   public void setMacSecretKey(String macSecretKey) { this.macSecretKey = macSecretKey; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("打开方式 1：GPRS，2：蓝牙，3：GPRS 和 蓝牙")
/*     */   public Integer getOpenType()
/*     */   {
/* 224 */     return this.openType;
/*     */   }
/*     */   
/* 227 */   public void setOpenType(Integer openType) { this.openType = openType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("位置")
/*     */   public Point getPoint()
/*     */   {
/* 236 */     return this.point;
/*     */   }
/*     */   
/* 239 */   public void setPoint(Point point) { this.point = point; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("费用，单位元")
/*     */   public Double getPrice()
/*     */   {
/* 248 */     return this.price;
/*     */   }
/*     */   
/* 251 */   public void setPrice(Double price) { this.price = price; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("费用分钟")
/*     */   public Integer getPriceMinute()
/*     */   {
/* 260 */     return this.priceMinute;
/*     */   }
/*     */   
/* 263 */   public void setPriceMinute(Integer priceMinute) { this.priceMinute = priceMinute; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getStatus()
/*     */   {
/* 271 */     return this.status;
/*     */   }
/*     */   
/* 274 */   public void setStatus(Integer status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSysCode()
/*     */   {
/* 282 */     return this.sysCode;
/*     */   }
/*     */   
/* 285 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("步行用时，秒")
/*     */   public Integer getWalkTime()
/*     */   {
/* 294 */     return this.walkTime;
/*     */   }
/*     */   
/* 297 */   public void setWalkTime(Integer walkTime) { this.walkTime = walkTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 304 */     if (this == o) {
/* 305 */       return true;
/*     */     }
/* 307 */     if ((o == null) || (getClass() != o.getClass())) {
/* 308 */       return false;
/*     */     }
/* 310 */     BicycleBO bicycleBO = (BicycleBO)o;
/* 311 */     return (Objects.equals(this.bluetoothCode, bicycleBO.bluetoothCode)) && 
/* 312 */       (Objects.equals(this.defaultMacPwd, bicycleBO.defaultMacPwd)) && 
/* 313 */       (Objects.equals(this.defaultMacSecretKey, bicycleBO.defaultMacSecretKey)) && 
/* 314 */       (Objects.equals(this.distance, bicycleBO.distance)) && 
/* 315 */       (Objects.equals(this.doingOrderWork, bicycleBO.doingOrderWork)) && 
/* 316 */       (Objects.equals(this.electricity, bicycleBO.electricity)) && 
/* 317 */       (Objects.equals(this.lastHeartbeat, bicycleBO.lastHeartbeat)) && 
/* 318 */       (Objects.equals(this.lastLocationTime, bicycleBO.lastLocationTime)) && 
/* 319 */       (Objects.equals(this.locationDetails, bicycleBO.locationDetails)) && 
/* 320 */       (Objects.equals(this.lockMacAddress, bicycleBO.lockMacAddress)) && 
/* 321 */       (Objects.equals(this.macPwd, bicycleBO.macPwd)) && 
/* 322 */       (Objects.equals(this.macSecretKey, bicycleBO.macSecretKey)) && 
/* 323 */       (Objects.equals(this.openType, bicycleBO.openType)) && 
/* 324 */       (Objects.equals(this.point, bicycleBO.point)) && 
/* 325 */       (Objects.equals(this.price, bicycleBO.price)) && 
/* 326 */       (Objects.equals(this.priceMinute, bicycleBO.priceMinute)) && 
/* 327 */       (Objects.equals(this.status, bicycleBO.status)) && 
/* 328 */       (Objects.equals(this.sysCode, bicycleBO.sysCode)) && 
/* 329 */       (Objects.equals(this.walkTime, bicycleBO.walkTime));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 334 */     return Objects.hash(new Object[] { this.bluetoothCode, this.defaultMacPwd, this.defaultMacSecretKey, this.distance, this.doingOrderWork, this.electricity, this.lastHeartbeat, this.lastLocationTime, this.locationDetails, this.lockMacAddress, this.macPwd, this.macSecretKey, this.openType, this.point, this.price, this.priceMinute, this.status, this.sysCode, this.walkTime });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 339 */     StringBuilder sb = new StringBuilder();
/* 340 */     sb.append("class BicycleBO {\n");
/*     */     
/* 342 */     sb.append("    bluetoothCode: ").append(toIndentedString(this.bluetoothCode)).append("\n");
/* 343 */     sb.append("    defaultMacPwd: ").append(toIndentedString(this.defaultMacPwd)).append("\n");
/* 344 */     sb.append("    defaultMacSecretKey: ").append(toIndentedString(this.defaultMacSecretKey)).append("\n");
/* 345 */     sb.append("    distance: ").append(toIndentedString(this.distance)).append("\n");
/* 346 */     sb.append("    doingOrderWork: ").append(toIndentedString(this.doingOrderWork)).append("\n");
/* 347 */     sb.append("    electricity: ").append(toIndentedString(this.electricity)).append("\n");
/* 348 */     sb.append("    lastHeartbeat: ").append(toIndentedString(this.lastHeartbeat)).append("\n");
/* 349 */     sb.append("    lastLocationTime: ").append(toIndentedString(this.lastLocationTime)).append("\n");
/* 350 */     sb.append("    locationDetails: ").append(toIndentedString(this.locationDetails)).append("\n");
/* 351 */     sb.append("    lockMacAddress: ").append(toIndentedString(this.lockMacAddress)).append("\n");
/* 352 */     sb.append("    macPwd: ").append(toIndentedString(this.macPwd)).append("\n");
/* 353 */     sb.append("    macSecretKey: ").append(toIndentedString(this.macSecretKey)).append("\n");
/* 354 */     sb.append("    openType: ").append(toIndentedString(this.openType)).append("\n");
/* 355 */     sb.append("    point: ").append(toIndentedString(this.point)).append("\n");
/* 356 */     sb.append("    price: ").append(toIndentedString(this.price)).append("\n");
/* 357 */     sb.append("    priceMinute: ").append(toIndentedString(this.priceMinute)).append("\n");
/* 358 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 359 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 360 */     sb.append("    walkTime: ").append(toIndentedString(this.walkTime)).append("\n");
/* 361 */     sb.append("}");
/* 362 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 370 */     if (o == null) {
/* 371 */       return "null";
/*     */     }
/* 373 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\BicycleBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */