/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class TripCyclingPointBO
/*     */ {
/*     */   @SerializedName("createTime")
/*  16 */   private Date createTime = null;
/*     */   
/*     */   @SerializedName("pointLat")
/*  19 */   private Double pointLat = null;
/*     */   
/*     */   @SerializedName("pointLon")
/*  22 */   private Double pointLon = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getCreateTime()
/*     */   {
/*  31 */     return this.createTime;
/*     */   }
/*     */   
/*  34 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getPointLat()
/*     */   {
/*  42 */     return this.pointLat;
/*     */   }
/*     */   
/*  45 */   public void setPointLat(Double pointLat) { this.pointLat = pointLat; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getPointLon()
/*     */   {
/*  53 */     return this.pointLon;
/*     */   }
/*     */   
/*  56 */   public void setPointLon(Double pointLon) { this.pointLon = pointLon; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  63 */     if (this == o) {
/*  64 */       return true;
/*     */     }
/*  66 */     if ((o == null) || (getClass() != o.getClass())) {
/*  67 */       return false;
/*     */     }
/*  69 */     TripCyclingPointBO tripCyclingPointBO = (TripCyclingPointBO)o;
/*  70 */     return (Objects.equals(this.createTime, tripCyclingPointBO.createTime)) && 
/*  71 */       (Objects.equals(this.pointLat, tripCyclingPointBO.pointLat)) && 
/*  72 */       (Objects.equals(this.pointLon, tripCyclingPointBO.pointLon));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  77 */     return Objects.hash(new Object[] { this.createTime, this.pointLat, this.pointLon });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  82 */     StringBuilder sb = new StringBuilder();
/*  83 */     sb.append("class TripCyclingPointBO {\n");
/*     */     
/*  85 */     sb.append("    createTime: ").append(toIndentedString(this.createTime)).append("\n");
/*  86 */     sb.append("    pointLat: ").append(toIndentedString(this.pointLat)).append("\n");
/*  87 */     sb.append("    pointLon: ").append(toIndentedString(this.pointLon)).append("\n");
/*  88 */     sb.append("}");
/*  89 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/*  97 */     if (o == null) {
/*  98 */       return "null";
/*     */     }
/* 100 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\TripCyclingPointBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */