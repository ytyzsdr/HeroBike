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
/*     */ public class ReserveBO
/*     */ {
/*     */   @SerializedName("bicycleId")
/*  17 */   private Long bicycleId = null;
/*     */   
/*     */   @SerializedName("createTime")
/*  20 */   private Date createTime = null;
/*     */   
/*     */   @SerializedName("expireTime")
/*  23 */   private Date expireTime = null;
/*     */   
/*     */   @SerializedName("id")
/*  26 */   private Long id = null;
/*     */   
/*     */   @SerializedName("location")
/*  29 */   private Point location = null;
/*     */   
/*     */   @SerializedName("locationDetails")
/*  32 */   private String locationDetails = null;
/*     */   
/*     */   @SerializedName("reserveTime")
/*  35 */   private Date reserveTime = null;
/*     */   
/*     */   @SerializedName("status")
/*  38 */   private Integer status = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  41 */   private String sysCode = null;
/*     */   
/*     */   @SerializedName("uid")
/*  44 */   private Long uid = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getBicycleId()
/*     */   {
/*  53 */     return this.bicycleId;
/*     */   }
/*     */   
/*  56 */   public void setBicycleId(Long bicycleId) { this.bicycleId = bicycleId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getCreateTime()
/*     */   {
/*  64 */     return this.createTime;
/*     */   }
/*     */   
/*  67 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getExpireTime()
/*     */   {
/*  75 */     return this.expireTime;
/*     */   }
/*     */   
/*  78 */   public void setExpireTime(Date expireTime) { this.expireTime = expireTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  86 */     return this.id;
/*     */   }
/*     */   
/*  89 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Point getLocation()
/*     */   {
/*  97 */     return this.location;
/*     */   }
/*     */   
/* 100 */   public void setLocation(Point location) { this.location = location; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getLocationDetails()
/*     */   {
/* 108 */     return this.locationDetails;
/*     */   }
/*     */   
/* 111 */   public void setLocationDetails(String locationDetails) { this.locationDetails = locationDetails; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getReserveTime()
/*     */   {
/* 119 */     return this.reserveTime;
/*     */   }
/*     */   
/* 122 */   public void setReserveTime(Date reserveTime) { this.reserveTime = reserveTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("1:预约中 2:已取消")
/*     */   public Integer getStatus()
/*     */   {
/* 131 */     return this.status;
/*     */   }
/*     */   
/* 134 */   public void setStatus(Integer status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSysCode()
/*     */   {
/* 142 */     return this.sysCode;
/*     */   }
/*     */   
/* 145 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getUid()
/*     */   {
/* 153 */     return this.uid;
/*     */   }
/*     */   
/* 156 */   public void setUid(Long uid) { this.uid = uid; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 163 */     if (this == o) {
/* 164 */       return true;
/*     */     }
/* 166 */     if ((o == null) || (getClass() != o.getClass())) {
/* 167 */       return false;
/*     */     }
/* 169 */     ReserveBO reserveBO = (ReserveBO)o;
/* 170 */     return (Objects.equals(this.bicycleId, reserveBO.bicycleId)) && 
/* 171 */       (Objects.equals(this.createTime, reserveBO.createTime)) && 
/* 172 */       (Objects.equals(this.expireTime, reserveBO.expireTime)) && 
/* 173 */       (Objects.equals(this.id, reserveBO.id)) && 
/* 174 */       (Objects.equals(this.location, reserveBO.location)) && 
/* 175 */       (Objects.equals(this.locationDetails, reserveBO.locationDetails)) && 
/* 176 */       (Objects.equals(this.reserveTime, reserveBO.reserveTime)) && 
/* 177 */       (Objects.equals(this.status, reserveBO.status)) && 
/* 178 */       (Objects.equals(this.sysCode, reserveBO.sysCode)) && 
/* 179 */       (Objects.equals(this.uid, reserveBO.uid));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 184 */     return Objects.hash(new Object[] { this.bicycleId, this.createTime, this.expireTime, this.id, this.location, this.locationDetails, this.reserveTime, this.status, this.sysCode, this.uid });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 189 */     StringBuilder sb = new StringBuilder();
/* 190 */     sb.append("class ReserveBO {\n");
/*     */     
/* 192 */     sb.append("    bicycleId: ").append(toIndentedString(this.bicycleId)).append("\n");
/* 193 */     sb.append("    createTime: ").append(toIndentedString(this.createTime)).append("\n");
/* 194 */     sb.append("    expireTime: ").append(toIndentedString(this.expireTime)).append("\n");
/* 195 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 196 */     sb.append("    location: ").append(toIndentedString(this.location)).append("\n");
/* 197 */     sb.append("    locationDetails: ").append(toIndentedString(this.locationDetails)).append("\n");
/* 198 */     sb.append("    reserveTime: ").append(toIndentedString(this.reserveTime)).append("\n");
/* 199 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 200 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 201 */     sb.append("    uid: ").append(toIndentedString(this.uid)).append("\n");
/* 202 */     sb.append("}");
/* 203 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 211 */     if (o == null) {
/* 212 */       return "null";
/*     */     }
/* 214 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ReserveBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */