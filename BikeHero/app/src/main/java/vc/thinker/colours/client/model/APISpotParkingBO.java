/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APISpotParkingBO
/*     */ {
/*     */   @SerializedName("distance")
/*  17 */   private Integer distance = null;
/*     */   
/*     */   @SerializedName("id")
/*  20 */   private Long id = null;
/*     */   
/*     */   @SerializedName("locationAddress")
/*  23 */   private String locationAddress = null;
/*     */   
/*     */   @SerializedName("locationDesc")
/*  26 */   private String locationDesc = null;
/*     */   
/*     */   @SerializedName("locationLat")
/*  29 */   private Double locationLat = null;
/*     */   
/*     */   @SerializedName("locationLon")
/*  32 */   private Double locationLon = null;
/*     */   
/*     */   @SerializedName("major")
/*  35 */   private String major = null;
/*     */   
/*     */   @SerializedName("minor")
/*  38 */   private String minor = null;
/*     */   
/*     */   @SerializedName("name")
/*  41 */   private String name = null;
/*     */   
/*     */   @SerializedName("points")
/*  44 */   private List<Point> points = new ArrayList();
/*     */   
/*     */   @SerializedName("spotType")
/*  47 */   private String spotType = null;
/*     */   
/*     */   @SerializedName("status")
/*  50 */   private Integer status = null;
/*     */   
/*     */   @SerializedName("type")
/*  53 */   private String type = null;
/*     */   
/*     */   @SerializedName("uuid")
/*  56 */   private String uuid = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("定点半径")
/*     */   public Integer getDistance()
/*     */   {
/*  66 */     return this.distance;
/*     */   }
/*     */   
/*  69 */   public void setDistance(Integer distance) { this.distance = distance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  77 */     return this.id;
/*     */   }
/*     */   
/*  80 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getLocationAddress()
/*     */   {
/*  88 */     return this.locationAddress;
/*     */   }
/*     */   
/*  91 */   public void setLocationAddress(String locationAddress) { this.locationAddress = locationAddress; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("详细地址描述")
/*     */   public String getLocationDesc()
/*     */   {
/* 100 */     return this.locationDesc;
/*     */   }
/*     */   
/* 103 */   public void setLocationDesc(String locationDesc) { this.locationDesc = locationDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getLocationLat()
/*     */   {
/* 111 */     return this.locationLat;
/*     */   }
/*     */   
/* 114 */   public void setLocationLat(Double locationLat) { this.locationLat = locationLat; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getLocationLon()
/*     */   {
/* 122 */     return this.locationLon;
/*     */   }
/*     */   
/* 125 */   public void setLocationLon(Double locationLon) { this.locationLon = locationLon; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙停车桩参数")
/*     */   public String getMajor()
/*     */   {
/* 134 */     return this.major;
/*     */   }
/*     */   
/* 137 */   public void setMajor(String major) { this.major = major; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙停车桩参数")
/*     */   public String getMinor()
/*     */   {
/* 146 */     return this.minor;
/*     */   }
/*     */   
/* 149 */   public void setMinor(String minor) { this.minor = minor; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getName()
/*     */   {
/* 157 */     return this.name;
/*     */   }
/*     */   
/* 160 */   public void setName(String name) { this.name = name; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("多边形的点")
/*     */   public List<Point> getPoints()
/*     */   {
/* 169 */     return this.points;
/*     */   }
/*     */   
/* 172 */   public void setPoints(List<Point> points) { this.points = points; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("定点类型：  GPRS :gprs  , 蓝牙：bluetooth")
/*     */   public String getSpotType()
/*     */   {
/* 181 */     return this.spotType;
/*     */   }
/*     */   
/* 184 */   public void setSpotType(String spotType) { this.spotType = spotType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getStatus()
/*     */   {
/* 192 */     return this.status;
/*     */   }
/*     */   
/* 195 */   public void setStatus(Integer status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("定点类型 circular：圆，polygon：多边形")
/*     */   public String getType()
/*     */   {
/* 204 */     return this.type;
/*     */   }
/*     */   
/* 207 */   public void setType(String type) { this.type = type; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙停车桩参数")
/*     */   public String getUuid()
/*     */   {
/* 216 */     return this.uuid;
/*     */   }
/*     */   
/* 219 */   public void setUuid(String uuid) { this.uuid = uuid; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 226 */     if (this == o) {
/* 227 */       return true;
/*     */     }
/* 229 */     if ((o == null) || (getClass() != o.getClass())) {
/* 230 */       return false;
/*     */     }
/* 232 */     APISpotParkingBO aPISpotParkingBO = (APISpotParkingBO)o;
/* 233 */     return (Objects.equals(this.distance, aPISpotParkingBO.distance)) && 
/* 234 */       (Objects.equals(this.id, aPISpotParkingBO.id)) && 
/* 235 */       (Objects.equals(this.locationAddress, aPISpotParkingBO.locationAddress)) && 
/* 236 */       (Objects.equals(this.locationDesc, aPISpotParkingBO.locationDesc)) && 
/* 237 */       (Objects.equals(this.locationLat, aPISpotParkingBO.locationLat)) && 
/* 238 */       (Objects.equals(this.locationLon, aPISpotParkingBO.locationLon)) && 
/* 239 */       (Objects.equals(this.major, aPISpotParkingBO.major)) && 
/* 240 */       (Objects.equals(this.minor, aPISpotParkingBO.minor)) && 
/* 241 */       (Objects.equals(this.name, aPISpotParkingBO.name)) && 
/* 242 */       (Objects.equals(this.points, aPISpotParkingBO.points)) && 
/* 243 */       (Objects.equals(this.spotType, aPISpotParkingBO.spotType)) && 
/* 244 */       (Objects.equals(this.status, aPISpotParkingBO.status)) && 
/* 245 */       (Objects.equals(this.type, aPISpotParkingBO.type)) && 
/* 246 */       (Objects.equals(this.uuid, aPISpotParkingBO.uuid));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 251 */     return Objects.hash(new Object[] { this.distance, this.id, this.locationAddress, this.locationDesc, this.locationLat, this.locationLon, this.major, this.minor, this.name, this.points, this.spotType, this.status, this.type, this.uuid });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 256 */     StringBuilder sb = new StringBuilder();
/* 257 */     sb.append("class APISpotParkingBO {\n");
/*     */     
/* 259 */     sb.append("    distance: ").append(toIndentedString(this.distance)).append("\n");
/* 260 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 261 */     sb.append("    locationAddress: ").append(toIndentedString(this.locationAddress)).append("\n");
/* 262 */     sb.append("    locationDesc: ").append(toIndentedString(this.locationDesc)).append("\n");
/* 263 */     sb.append("    locationLat: ").append(toIndentedString(this.locationLat)).append("\n");
/* 264 */     sb.append("    locationLon: ").append(toIndentedString(this.locationLon)).append("\n");
/* 265 */     sb.append("    major: ").append(toIndentedString(this.major)).append("\n");
/* 266 */     sb.append("    minor: ").append(toIndentedString(this.minor)).append("\n");
/* 267 */     sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
/* 268 */     sb.append("    points: ").append(toIndentedString(this.points)).append("\n");
/* 269 */     sb.append("    spotType: ").append(toIndentedString(this.spotType)).append("\n");
/* 270 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 271 */     sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
/* 272 */     sb.append("    uuid: ").append(toIndentedString(this.uuid)).append("\n");
/* 273 */     sb.append("}");
/* 274 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 282 */     if (o == null) {
/* 283 */       return "null";
/*     */     }
/* 285 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APISpotParkingBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */