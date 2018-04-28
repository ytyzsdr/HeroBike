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
/*     */
/*     */ public class APIElectrombileBO
/*     */ {
/*     */   @SerializedName("bluetoothIdentifier")
/*  18 */   private String bluetoothIdentifier = null;
/*     */   
/*     */   @SerializedName("distance")
/*  21 */   private Double distance = null;
/*     */   
/*     */   @SerializedName("doingOrderWork")
/*  24 */   private APIOrderWorkBO doingOrderWork = null;
/*     */   
/*     */   @SerializedName("electricity")
/*  27 */   private Integer electricity = null;
/*     */   
/*     */   @SerializedName("electrombileCode")
/*  30 */   private String electrombileCode = null;
/*     */   
/*     */   @SerializedName("lastLocationTime")
/*  33 */   private Date lastLocationTime = null;
/*     */   
/*     */   @SerializedName("locationDetails")
/*  36 */   private String locationDetails = null;
/*     */   
/*     */   @SerializedName("motorPower")
/*  39 */   private String motorPower = null;
/*     */   
/*     */   @SerializedName("point")
/*  42 */   private Point point = null;
/*     */   
/*     */   @SerializedName("rechargeMileage")
/*  45 */   private Double rechargeMileage = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  48 */   private String sysCode = null;
/*     */   
/*     */   @SerializedName("typeId")
/*  51 */   private Long typeId = null;
/*     */   
/*     */   @SerializedName("typeName")
/*  54 */   private String typeName = null;
/*     */   
/*     */   @SerializedName("voltage")
/*  57 */   private Integer voltage = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙唯一标识")
/*     */   public String getBluetoothIdentifier()
/*     */   {
/*  67 */     return this.bluetoothIdentifier;
/*     */   }
/*     */   
/*  70 */   public void setBluetoothIdentifier(String bluetoothIdentifier) { this.bluetoothIdentifier = bluetoothIdentifier; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getDistance()
/*     */   {
/*  78 */     return this.distance;
/*     */   }
/*     */   
/*  81 */   public void setDistance(Double distance) { this.distance = distance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("进行中工单")
/*     */   public APIOrderWorkBO getDoingOrderWork()
/*     */   {
/*  90 */     return this.doingOrderWork;
/*     */   }
/*     */   
/*  93 */   public void setDoingOrderWork(APIOrderWorkBO doingOrderWork) { this.doingOrderWork = doingOrderWork; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getElectricity()
/*     */   {
/* 101 */     return this.electricity;
/*     */   }
/*     */   
/* 104 */   public void setElectricity(Integer electricity) { this.electricity = electricity; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("电动车编码")
/*     */   public String getElectrombileCode()
/*     */   {
/* 113 */     return this.electrombileCode;
/*     */   }
/*     */   
/* 116 */   public void setElectrombileCode(String electrombileCode) { this.electrombileCode = electrombileCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getLastLocationTime()
/*     */   {
/* 124 */     return this.lastLocationTime;
/*     */   }
/*     */   
/* 127 */   public void setLastLocationTime(Date lastLocationTime) { this.lastLocationTime = lastLocationTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getLocationDetails()
/*     */   {
/* 135 */     return this.locationDetails;
/*     */   }
/*     */   
/* 138 */   public void setLocationDetails(String locationDetails) { this.locationDetails = locationDetails; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getMotorPower()
/*     */   {
/* 146 */     return this.motorPower;
/*     */   }
/*     */   
/* 149 */   public void setMotorPower(String motorPower) { this.motorPower = motorPower; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Point getPoint()
/*     */   {
/* 157 */     return this.point;
/*     */   }
/*     */   
/* 160 */   public void setPoint(Point point) { this.point = point; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("续航里程，单位公里")
/*     */   public Double getRechargeMileage()
/*     */   {
/* 169 */     return this.rechargeMileage;
/*     */   }
/*     */   
/* 172 */   public void setRechargeMileage(Double rechargeMileage) { this.rechargeMileage = rechargeMileage; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSysCode()
/*     */   {
/* 180 */     return this.sysCode;
/*     */   }
/*     */   
/* 183 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getTypeId()
/*     */   {
/* 191 */     return this.typeId;
/*     */   }
/*     */   
/* 194 */   public void setTypeId(Long typeId) { this.typeId = typeId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTypeName()
/*     */   {
/* 202 */     return this.typeName;
/*     */   }
/*     */   
/* 205 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getVoltage()
/*     */   {
/* 213 */     return this.voltage;
/*     */   }
/*     */   
/* 216 */   public void setVoltage(Integer voltage) { this.voltage = voltage; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 223 */     if (this == o) {
/* 224 */       return true;
/*     */     }
/* 226 */     if ((o == null) || (getClass() != o.getClass())) {
/* 227 */       return false;
/*     */     }
/* 229 */     APIElectrombileBO aPIElectrombileBO = (APIElectrombileBO)o;
/* 230 */     return (Objects.equals(this.bluetoothIdentifier, aPIElectrombileBO.bluetoothIdentifier)) && 
/* 231 */       (Objects.equals(this.distance, aPIElectrombileBO.distance)) && 
/* 232 */       (Objects.equals(this.doingOrderWork, aPIElectrombileBO.doingOrderWork)) && 
/* 233 */       (Objects.equals(this.electricity, aPIElectrombileBO.electricity)) && 
/* 234 */       (Objects.equals(this.electrombileCode, aPIElectrombileBO.electrombileCode)) && 
/* 235 */       (Objects.equals(this.lastLocationTime, aPIElectrombileBO.lastLocationTime)) && 
/* 236 */       (Objects.equals(this.locationDetails, aPIElectrombileBO.locationDetails)) && 
/* 237 */       (Objects.equals(this.motorPower, aPIElectrombileBO.motorPower)) && 
/* 238 */       (Objects.equals(this.point, aPIElectrombileBO.point)) && 
/* 239 */       (Objects.equals(this.rechargeMileage, aPIElectrombileBO.rechargeMileage)) && 
/* 240 */       (Objects.equals(this.sysCode, aPIElectrombileBO.sysCode)) && 
/* 241 */       (Objects.equals(this.typeId, aPIElectrombileBO.typeId)) && 
/* 242 */       (Objects.equals(this.typeName, aPIElectrombileBO.typeName)) && 
/* 243 */       (Objects.equals(this.voltage, aPIElectrombileBO.voltage));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 248 */     return Objects.hash(new Object[] { this.bluetoothIdentifier, this.distance, this.doingOrderWork, this.electricity, this.electrombileCode, this.lastLocationTime, this.locationDetails, this.motorPower, this.point, this.rechargeMileage, this.sysCode, this.typeId, this.typeName, this.voltage });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 253 */     StringBuilder sb = new StringBuilder();
/* 254 */     sb.append("class APIElectrombileBO {\n");
/*     */     
/* 256 */     sb.append("    bluetoothIdentifier: ").append(toIndentedString(this.bluetoothIdentifier)).append("\n");
/* 257 */     sb.append("    distance: ").append(toIndentedString(this.distance)).append("\n");
/* 258 */     sb.append("    doingOrderWork: ").append(toIndentedString(this.doingOrderWork)).append("\n");
/* 259 */     sb.append("    electricity: ").append(toIndentedString(this.electricity)).append("\n");
/* 260 */     sb.append("    electrombileCode: ").append(toIndentedString(this.electrombileCode)).append("\n");
/* 261 */     sb.append("    lastLocationTime: ").append(toIndentedString(this.lastLocationTime)).append("\n");
/* 262 */     sb.append("    locationDetails: ").append(toIndentedString(this.locationDetails)).append("\n");
/* 263 */     sb.append("    motorPower: ").append(toIndentedString(this.motorPower)).append("\n");
/* 264 */     sb.append("    point: ").append(toIndentedString(this.point)).append("\n");
/* 265 */     sb.append("    rechargeMileage: ").append(toIndentedString(this.rechargeMileage)).append("\n");
/* 266 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 267 */     sb.append("    typeId: ").append(toIndentedString(this.typeId)).append("\n");
/* 268 */     sb.append("    typeName: ").append(toIndentedString(this.typeName)).append("\n");
/* 269 */     sb.append("    voltage: ").append(toIndentedString(this.voltage)).append("\n");
/* 270 */     sb.append("}");
/* 271 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 279 */     if (o == null) {
/* 280 */       return "null";
/*     */     }
/* 282 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIElectrombileBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */