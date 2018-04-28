/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIBatteryBO
/*     */ {
/*     */   @SerializedName("batteryCode")
/*  17 */   private String batteryCode = null;
/*     */   
/*     */   @SerializedName("distance")
/*  20 */   private Double distance = null;
/*     */   
/*     */   @SerializedName("electricity")
/*  23 */   private Integer electricity = null;
/*     */   
/*     */   @SerializedName("lastHeartbeat")
/*  26 */   private Date lastHeartbeat = null;
/*     */   
/*     */   @SerializedName("lastLocationTime")
/*  29 */   private Date lastLocationTime = null;
/*     */   
/*     */   @SerializedName("locationDetails")
/*  32 */   private String locationDetails = null;
/*     */   
/*     */   @SerializedName("point")
/*  35 */   private Point point = null;
/*     */   
/*     */   @SerializedName("price")
/*  38 */   private Double price = null;
/*     */   
/*     */   @SerializedName("priceMinute")
/*  41 */   private Integer priceMinute = null;
/*     */   
/*     */   @SerializedName("rechargeMileage")
/*  44 */   private Double rechargeMileage = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  47 */   private String sysCode = null;
/*     */   
/*     */   @SerializedName("typeDesc")
/*  50 */   private String typeDesc = null;
/*     */   
/*     */   @SerializedName("typeName")
/*  53 */   private String typeName = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("电池编码")
/*     */   public String getBatteryCode()
/*     */   {
/*  63 */     return this.batteryCode;
/*     */   }
/*     */   
/*  66 */   public void setBatteryCode(String batteryCode) { this.batteryCode = batteryCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getDistance()
/*     */   {
/*  74 */     return this.distance;
/*     */   }
/*     */   
/*  77 */   public void setDistance(Double distance) { this.distance = distance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getElectricity()
/*     */   {
/*  85 */     return this.electricity;
/*     */   }
/*     */   
/*  88 */   public void setElectricity(Integer electricity) { this.electricity = electricity; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getLastHeartbeat()
/*     */   {
/*  96 */     return this.lastHeartbeat;
/*     */   }
/*     */   
/*  99 */   public void setLastHeartbeat(Date lastHeartbeat) { this.lastHeartbeat = lastHeartbeat; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getLastLocationTime()
/*     */   {
/* 107 */     return this.lastLocationTime;
/*     */   }
/*     */   
/* 110 */   public void setLastLocationTime(Date lastLocationTime) { this.lastLocationTime = lastLocationTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getLocationDetails()
/*     */   {
/* 118 */     return this.locationDetails;
/*     */   }
/*     */   
/* 121 */   public void setLocationDetails(String locationDetails) { this.locationDetails = locationDetails; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Point getPoint()
/*     */   {
/* 129 */     return this.point;
/*     */   }
/*     */   
/* 132 */   public void setPoint(Point point) { this.point = point; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("费用，单位元")
/*     */   public Double getPrice()
/*     */   {
/* 141 */     return this.price;
/*     */   }
/*     */   
/* 144 */   public void setPrice(Double price) { this.price = price; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("费用分钟")
/*     */   public Integer getPriceMinute()
/*     */   {
/* 153 */     return this.priceMinute;
/*     */   }
/*     */   
/* 156 */   public void setPriceMinute(Integer priceMinute) { this.priceMinute = priceMinute; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("续航里程，单位公里")
/*     */   public Double getRechargeMileage()
/*     */   {
/* 165 */     return this.rechargeMileage;
/*     */   }
/*     */   
/* 168 */   public void setRechargeMileage(Double rechargeMileage) { this.rechargeMileage = rechargeMileage; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSysCode()
/*     */   {
/* 176 */     return this.sysCode;
/*     */   }
/*     */   
/* 179 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("规格型号描述")
/*     */   public String getTypeDesc()
/*     */   {
/* 188 */     return this.typeDesc;
/*     */   }
/*     */   
/* 191 */   public void setTypeDesc(String typeDesc) { this.typeDesc = typeDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTypeName()
/*     */   {
/* 199 */     return this.typeName;
/*     */   }
/*     */   
/* 202 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SuppressLint("NewApi")
public boolean equals(Object o)
/*     */   {
/* 209 */     if (this == o) {
/* 210 */       return true;
/*     */     }
/* 212 */     if ((o == null) || (getClass() != o.getClass())) {
/* 213 */       return false;
/*     */     }
/* 215 */     APIBatteryBO aPIBatteryBO = (APIBatteryBO)o;
/* 216 */     return (Objects.equals(this.batteryCode, aPIBatteryBO.batteryCode)) && 
/* 217 */       (Objects.equals(this.distance, aPIBatteryBO.distance)) && 
/* 218 */       (Objects.equals(this.electricity, aPIBatteryBO.electricity)) && 
/* 219 */       (Objects.equals(this.lastHeartbeat, aPIBatteryBO.lastHeartbeat)) && 
/* 220 */       (Objects.equals(this.lastLocationTime, aPIBatteryBO.lastLocationTime)) && 
/* 221 */       (Objects.equals(this.locationDetails, aPIBatteryBO.locationDetails)) && 
/* 222 */       (Objects.equals(this.point, aPIBatteryBO.point)) && 
/* 223 */       (Objects.equals(this.price, aPIBatteryBO.price)) && 
/* 224 */       (Objects.equals(this.priceMinute, aPIBatteryBO.priceMinute)) && 
/* 225 */       (Objects.equals(this.rechargeMileage, aPIBatteryBO.rechargeMileage)) && 
/* 226 */       (Objects.equals(this.sysCode, aPIBatteryBO.sysCode)) && 
/* 227 */       (Objects.equals(this.typeDesc, aPIBatteryBO.typeDesc)) && 
/* 228 */       (Objects.equals(this.typeName, aPIBatteryBO.typeName));
/*     */   }
/*     */   
/*     */   @SuppressLint("NewApi")
@Override
public int hashCode()
/*     */   {
/* 233 */     return Objects.hash(new Object[] { this.batteryCode, this.distance, this.electricity, this.lastHeartbeat, this.lastLocationTime, this.locationDetails, this.point, this.price, this.priceMinute, this.rechargeMileage, this.sysCode, this.typeDesc, this.typeName });
/*     */   }
/*     */   
/*     */   @Override
public String toString()
/*     */   {
/* 238 */     StringBuilder sb = new StringBuilder();
/* 239 */     sb.append("class APIBatteryBO {\n");
/*     */     
/* 241 */     sb.append("    batteryCode: ").append(toIndentedString(this.batteryCode)).append("\n");
/* 242 */     sb.append("    distance: ").append(toIndentedString(this.distance)).append("\n");
/* 243 */     sb.append("    electricity: ").append(toIndentedString(this.electricity)).append("\n");
/* 244 */     sb.append("    lastHeartbeat: ").append(toIndentedString(this.lastHeartbeat)).append("\n");
/* 245 */     sb.append("    lastLocationTime: ").append(toIndentedString(this.lastLocationTime)).append("\n");
/* 246 */     sb.append("    locationDetails: ").append(toIndentedString(this.locationDetails)).append("\n");
/* 247 */     sb.append("    point: ").append(toIndentedString(this.point)).append("\n");
/* 248 */     sb.append("    price: ").append(toIndentedString(this.price)).append("\n");
/* 249 */     sb.append("    priceMinute: ").append(toIndentedString(this.priceMinute)).append("\n");
/* 250 */     sb.append("    rechargeMileage: ").append(toIndentedString(this.rechargeMileage)).append("\n");
/* 251 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 252 */     sb.append("    typeDesc: ").append(toIndentedString(this.typeDesc)).append("\n");
/* 253 */     sb.append("    typeName: ").append(toIndentedString(this.typeName)).append("\n");
/* 254 */     sb.append("}");
/* 255 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 263 */     if (o == null) {
/* 264 */       return "null";
/*     */     }
/* 266 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIBatteryBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */