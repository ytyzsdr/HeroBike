/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIMessageBO
/*     */ {
/*     */   @SerializedName("adCover")
/*  16 */   private String adCover = null;
/*     */   
/*     */   @SerializedName("bizId")
/*  19 */   private String bizId = null;
/*     */   
/*     */   @SerializedName("bizType")
/*  22 */   private Integer bizType = null;
/*     */   
/*     */   @SerializedName("content")
/*  25 */   private String content = null;
/*     */   
/*     */   @SerializedName("cover")
/*  28 */   private String cover = null;
/*     */   
/*     */   @SerializedName("id")
/*  31 */   private Long id = null;
/*     */   
/*     */   @SerializedName("imageTextId")
/*  34 */   private Long imageTextId = null;
/*     */   
/*     */   @SerializedName("isImageText")
/*  37 */   private Boolean isImageText = null;
/*     */   
/*     */   @SerializedName("remark")
/*  40 */   private String remark = null;
/*     */   
/*     */   @SerializedName("sendTime")
/*  43 */   private Date sendTime = null;
/*     */   
/*     */   @SerializedName("startDate")
/*  46 */   private Date startDate = null;
/*     */   
/*     */   @SerializedName("title")
/*  49 */   private String title = null;
/*     */   
/*     */   @SerializedName("toUserType")
/*  52 */   private String toUserType = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("首页广告图片或者其它广告图片")
/*     */   public String getAdCover()
/*     */   {
/*  62 */     return this.adCover;
/*     */   }
/*     */   
/*  65 */   public void setAdCover(String adCover) { this.adCover = adCover; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("业务ID")
/*     */   public String getBizId()
/*     */   {
/*  74 */     return this.bizId;
/*     */   }
/*     */   
/*  77 */   public void setBizId(String bizId) { this.bizId = bizId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("31:工单消息")
/*     */   public Integer getBizType()
/*     */   {
/*  86 */     return this.bizType;
/*     */   }
/*     */   
/*  89 */   public void setBizType(Integer bizType) { this.bizType = bizType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getContent()
/*     */   {
/*  97 */     return this.content;
/*     */   }
/*     */   
/* 100 */   public void setContent(String content) { this.content = content; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("封面")
/*     */   public String getCover()
/*     */   {
/* 109 */     return this.cover;
/*     */   }
/*     */   
/* 112 */   public void setCover(String cover) { this.cover = cover; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/* 120 */     return this.id;
/*     */   }
/*     */   
/* 123 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("图文消息ID")
/*     */   public Long getImageTextId()
/*     */   {
/* 132 */     return this.imageTextId;
/*     */   }
/*     */   
/* 135 */   public void setImageTextId(Long imageTextId) { this.imageTextId = imageTextId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否图文")
/*     */   public Boolean getIsImageText()
/*     */   {
/* 144 */     return this.isImageText;
/*     */   }
/*     */   
/* 147 */   public void setIsImageText(Boolean isImageText) { this.isImageText = isImageText; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("摘要")
/*     */   public String getRemark()
/*     */   {
/* 156 */     return this.remark;
/*     */   }
/*     */   
/* 159 */   public void setRemark(String remark) { this.remark = remark; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getSendTime()
/*     */   {
/* 167 */     return this.sendTime;
/*     */   }
/*     */   
/* 170 */   public void setSendTime(Date sendTime) { this.sendTime = sendTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("时间生效时间，该字段用做首页广告时间戳")
/*     */   public Date getStartDate()
/*     */   {
/* 179 */     return this.startDate;
/*     */   }
/*     */   
/* 182 */   public void setStartDate(Date startDate) { this.startDate = startDate; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("标题")
/*     */   public String getTitle()
/*     */   {
/* 191 */     return this.title;
/*     */   }
/*     */   
/* 194 */   public void setTitle(String title) { this.title = title; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("接收用户的类型(3:维保)")
/*     */   public String getToUserType()
/*     */   {
/* 203 */     return this.toUserType;
/*     */   }
/*     */   
/* 206 */   public void setToUserType(String toUserType) { this.toUserType = toUserType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 213 */     if (this == o) {
/* 214 */       return true;
/*     */     }
/* 216 */     if ((o == null) || (getClass() != o.getClass())) {
/* 217 */       return false;
/*     */     }
/* 219 */     APIMessageBO aPIMessageBO = (APIMessageBO)o;
/* 220 */     return (Objects.equals(this.adCover, aPIMessageBO.adCover)) && 
/* 221 */       (Objects.equals(this.bizId, aPIMessageBO.bizId)) && 
/* 222 */       (Objects.equals(this.bizType, aPIMessageBO.bizType)) && 
/* 223 */       (Objects.equals(this.content, aPIMessageBO.content)) && 
/* 224 */       (Objects.equals(this.cover, aPIMessageBO.cover)) && 
/* 225 */       (Objects.equals(this.id, aPIMessageBO.id)) && 
/* 226 */       (Objects.equals(this.imageTextId, aPIMessageBO.imageTextId)) && 
/* 227 */       (Objects.equals(this.isImageText, aPIMessageBO.isImageText)) && 
/* 228 */       (Objects.equals(this.remark, aPIMessageBO.remark)) && 
/* 229 */       (Objects.equals(this.sendTime, aPIMessageBO.sendTime)) && 
/* 230 */       (Objects.equals(this.startDate, aPIMessageBO.startDate)) && 
/* 231 */       (Objects.equals(this.title, aPIMessageBO.title)) && 
/* 232 */       (Objects.equals(this.toUserType, aPIMessageBO.toUserType));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 237 */     return Objects.hash(new Object[] { this.adCover, this.bizId, this.bizType, this.content, this.cover, this.id, this.imageTextId, this.isImageText, this.remark, this.sendTime, this.startDate, this.title, this.toUserType });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 242 */     StringBuilder sb = new StringBuilder();
/* 243 */     sb.append("class APIMessageBO {\n");
/*     */     
/* 245 */     sb.append("    adCover: ").append(toIndentedString(this.adCover)).append("\n");
/* 246 */     sb.append("    bizId: ").append(toIndentedString(this.bizId)).append("\n");
/* 247 */     sb.append("    bizType: ").append(toIndentedString(this.bizType)).append("\n");
/* 248 */     sb.append("    content: ").append(toIndentedString(this.content)).append("\n");
/* 249 */     sb.append("    cover: ").append(toIndentedString(this.cover)).append("\n");
/* 250 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 251 */     sb.append("    imageTextId: ").append(toIndentedString(this.imageTextId)).append("\n");
/* 252 */     sb.append("    isImageText: ").append(toIndentedString(this.isImageText)).append("\n");
/* 253 */     sb.append("    remark: ").append(toIndentedString(this.remark)).append("\n");
/* 254 */     sb.append("    sendTime: ").append(toIndentedString(this.sendTime)).append("\n");
/* 255 */     sb.append("    startDate: ").append(toIndentedString(this.startDate)).append("\n");
/* 256 */     sb.append("    title: ").append(toIndentedString(this.title)).append("\n");
/* 257 */     sb.append("    toUserType: ").append(toIndentedString(this.toUserType)).append("\n");
/* 258 */     sb.append("}");
/* 259 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 267 */     if (o == null) {
/* 268 */       return "null";
/*     */     }
/* 270 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIMessageBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */