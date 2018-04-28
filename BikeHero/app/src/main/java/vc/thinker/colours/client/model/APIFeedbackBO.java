/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIFeedbackBO
/*     */ {
/*     */   @SerializedName("dealType")
/*  15 */   private String dealType = null;
/*     */   
/*     */   @SerializedName("feedDesc")
/*  18 */   private String feedDesc = null;
/*     */   
/*     */   @SerializedName("feedType")
/*  21 */   private String feedType = null;
/*     */   
/*     */   @SerializedName("id")
/*  24 */   private Long id = null;
/*     */   
/*     */   @SerializedName("imgUrl1")
/*  27 */   private String imgUrl1 = null;
/*     */   
/*     */   @SerializedName("imgUrl2")
/*  30 */   private String imgUrl2 = null;
/*     */   
/*     */   @SerializedName("imgUrl3")
/*  33 */   private String imgUrl3 = null;
/*     */   
/*     */   @SerializedName("imgUrl4")
/*  36 */   private String imgUrl4 = null;
/*     */   
/*     */   @SerializedName("remark")
/*  39 */   private String remark = null;
/*     */   
/*     */   @SerializedName("status")
/*  42 */   private Integer status = null;
/*     */   
/*     */   @SerializedName("tripId")
/*  45 */   private Long tripId = null;
/*     */   
/*     */   @SerializedName("typeId")
/*  48 */   private Long typeId = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getDealType()
/*     */   {
/*  57 */     return this.dealType;
/*     */   }
/*     */   
/*  60 */   public void setDealType(String dealType) { this.dealType = dealType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("问题表述")
/*     */   public String getFeedDesc()
/*     */   {
/*  69 */     return this.feedDesc;
/*     */   }
/*     */   
/*  72 */   public void setFeedDesc(String feedDesc) { this.feedDesc = feedDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("类型 1：首页，2：行程中，3：已完成")
/*     */   public String getFeedType()
/*     */   {
/*  81 */     return this.feedType;
/*     */   }
/*     */   
/*  84 */   public void setFeedType(String feedType) { this.feedType = feedType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  92 */     return this.id;
/*     */   }
/*     */   
/*  95 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getImgUrl1()
/*     */   {
/* 103 */     return this.imgUrl1;
/*     */   }
/*     */   
/* 106 */   public void setImgUrl1(String imgUrl1) { this.imgUrl1 = imgUrl1; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getImgUrl2()
/*     */   {
/* 114 */     return this.imgUrl2;
/*     */   }
/*     */   
/* 117 */   public void setImgUrl2(String imgUrl2) { this.imgUrl2 = imgUrl2; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getImgUrl3()
/*     */   {
/* 125 */     return this.imgUrl3;
/*     */   }
/*     */   
/* 128 */   public void setImgUrl3(String imgUrl3) { this.imgUrl3 = imgUrl3; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getImgUrl4()
/*     */   {
/* 136 */     return this.imgUrl4;
/*     */   }
/*     */   
/* 139 */   public void setImgUrl4(String imgUrl4) { this.imgUrl4 = imgUrl4; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getRemark()
/*     */   {
/* 147 */     return this.remark;
/*     */   }
/*     */   
/* 150 */   public void setRemark(String remark) { this.remark = remark; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("0:已处理，1：未处理，2：无需处理")
/*     */   public Integer getStatus()
/*     */   {
/* 159 */     return this.status;
/*     */   }
/*     */   
/* 162 */   public void setStatus(Integer status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getTripId()
/*     */   {
/* 170 */     return this.tripId;
/*     */   }
/*     */   
/* 173 */   public void setTripId(Long tripId) { this.tripId = tripId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("问题类型id")
/*     */   public Long getTypeId()
/*     */   {
/* 182 */     return this.typeId;
/*     */   }
/*     */   
/* 185 */   public void setTypeId(Long typeId) { this.typeId = typeId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 192 */     if (this == o) {
/* 193 */       return true;
/*     */     }
/* 195 */     if ((o == null) || (getClass() != o.getClass())) {
/* 196 */       return false;
/*     */     }
/* 198 */     APIFeedbackBO aPIFeedbackBO = (APIFeedbackBO)o;
/* 199 */     return (Objects.equals(this.dealType, aPIFeedbackBO.dealType)) && 
/* 200 */       (Objects.equals(this.feedDesc, aPIFeedbackBO.feedDesc)) && 
/* 201 */       (Objects.equals(this.feedType, aPIFeedbackBO.feedType)) && 
/* 202 */       (Objects.equals(this.id, aPIFeedbackBO.id)) && 
/* 203 */       (Objects.equals(this.imgUrl1, aPIFeedbackBO.imgUrl1)) && 
/* 204 */       (Objects.equals(this.imgUrl2, aPIFeedbackBO.imgUrl2)) && 
/* 205 */       (Objects.equals(this.imgUrl3, aPIFeedbackBO.imgUrl3)) && 
/* 206 */       (Objects.equals(this.imgUrl4, aPIFeedbackBO.imgUrl4)) && 
/* 207 */       (Objects.equals(this.remark, aPIFeedbackBO.remark)) && 
/* 208 */       (Objects.equals(this.status, aPIFeedbackBO.status)) && 
/* 209 */       (Objects.equals(this.tripId, aPIFeedbackBO.tripId)) && 
/* 210 */       (Objects.equals(this.typeId, aPIFeedbackBO.typeId));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 215 */     return Objects.hash(new Object[] { this.dealType, this.feedDesc, this.feedType, this.id, this.imgUrl1, this.imgUrl2, this.imgUrl3, this.imgUrl4, this.remark, this.status, this.tripId, this.typeId });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 220 */     StringBuilder sb = new StringBuilder();
/* 221 */     sb.append("class APIFeedbackBO {\n");
/*     */     
/* 223 */     sb.append("    dealType: ").append(toIndentedString(this.dealType)).append("\n");
/* 224 */     sb.append("    feedDesc: ").append(toIndentedString(this.feedDesc)).append("\n");
/* 225 */     sb.append("    feedType: ").append(toIndentedString(this.feedType)).append("\n");
/* 226 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 227 */     sb.append("    imgUrl1: ").append(toIndentedString(this.imgUrl1)).append("\n");
/* 228 */     sb.append("    imgUrl2: ").append(toIndentedString(this.imgUrl2)).append("\n");
/* 229 */     sb.append("    imgUrl3: ").append(toIndentedString(this.imgUrl3)).append("\n");
/* 230 */     sb.append("    imgUrl4: ").append(toIndentedString(this.imgUrl4)).append("\n");
/* 231 */     sb.append("    remark: ").append(toIndentedString(this.remark)).append("\n");
/* 232 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 233 */     sb.append("    tripId: ").append(toIndentedString(this.tripId)).append("\n");
/* 234 */     sb.append("    typeId: ").append(toIndentedString(this.typeId)).append("\n");
/* 235 */     sb.append("}");
/* 236 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 244 */     if (o == null) {
/* 245 */       return "null";
/*     */     }
/* 247 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIFeedbackBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */