/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */
/*     */ public class TripPointBo
/*     */ {
/*     */   @SerializedName("clientType")
/*  17 */   private String clientType = null;
/*     */   
/*     */   @SerializedName("pointList")
/*  20 */   private List<TripPointVO> pointList = new ArrayList();
/*     */   
/*     */   @SerializedName("pointType")
/*  23 */   private String pointType = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("客户端类型  1:微信小程序，2:app")
/*     */   public String getClientType()
/*     */   {
/*  33 */     return this.clientType;
/*     */   }
/*     */   
/*  36 */   public void setClientType(String clientType) { this.clientType = clientType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public List<TripPointVO> getPointList()
/*     */   {
/*  44 */     return this.pointList;
/*     */   }
/*     */   
/*  47 */   public void setPointList(List<TripPointVO> pointList) { this.pointList = pointList; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("坐标系类型,默认百度,百度坐标（BD09）、国测局坐标（火星坐标，GCJ02）、和WGS84坐标系之间的转换的工具")
/*     */   public String getPointType()
/*     */   {
/*  56 */     return this.pointType;
/*     */   }
/*     */   
/*  59 */   public void setPointType(String pointType) { this.pointType = pointType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  66 */     if (this == o) {
/*  67 */       return true;
/*     */     }
/*  69 */     if ((o == null) || (getClass() != o.getClass())) {
/*  70 */       return false;
/*     */     }
/*  72 */     TripPointBo tripPointBo = (TripPointBo)o;
/*  73 */     return (Objects.equals(this.clientType, tripPointBo.clientType)) && 
/*  74 */       (Objects.equals(this.pointList, tripPointBo.pointList)) && 
/*  75 */       (Objects.equals(this.pointType, tripPointBo.pointType));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  80 */     return Objects.hash(new Object[] { this.clientType, this.pointList, this.pointType });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  85 */     StringBuilder sb = new StringBuilder();
/*  86 */     sb.append("class TripPointBo {\n");
/*     */     
/*  88 */     sb.append("    clientType: ").append(toIndentedString(this.clientType)).append("\n");
/*  89 */     sb.append("    pointList: ").append(toIndentedString(this.pointList)).append("\n");
/*  90 */     sb.append("    pointType: ").append(toIndentedString(this.pointType)).append("\n");
/*  91 */     sb.append("}");
/*  92 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 100 */     if (o == null) {
/* 101 */       return "null";
/*     */     }
/* 103 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\TripPointBo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */