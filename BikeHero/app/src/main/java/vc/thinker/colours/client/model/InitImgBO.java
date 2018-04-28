/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */
/*     */ public class InitImgBO
/*     */ {
/*     */   @SerializedName("createTime")
/*  16 */   private Date createTime = null;
/*     */   
/*     */   @SerializedName("id")
/*  19 */   private Long id = null;
/*     */   
/*     */   @SerializedName("imgType")
/*  22 */   private Integer imgType = null;
/*     */   
/*     */   @SerializedName("initImg")
/*  25 */   private String initImg = null;
/*     */   
/*     */   @SerializedName("isDelete")
/*  28 */   private Boolean isDelete = null;
/*     */   
/*     */   @SerializedName("linkUrl")
/*  31 */   private String linkUrl = null;
/*     */   
/*     */   @SerializedName("name")
/*  34 */   private String name = null;
/*     */   
/*     */   @SerializedName("updateTime")
/*  37 */   private Date updateTime = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getCreateTime()
/*     */   {
/*  46 */     return this.createTime;
/*     */   }
/*     */   
/*  49 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  57 */     return this.id;
/*     */   }
/*     */   
/*  60 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getImgType()
/*     */   {
/*  68 */     return this.imgType;
/*     */   }
/*     */   
/*  71 */   public void setImgType(Integer imgType) { this.imgType = imgType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getInitImg()
/*     */   {
/*  79 */     return this.initImg;
/*     */   }
/*     */   
/*  82 */   public void setInitImg(String initImg) { this.initImg = initImg; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getIsDelete()
/*     */   {
/*  90 */     return this.isDelete;
/*     */   }
/*     */   
/*  93 */   public void setIsDelete(Boolean isDelete) { this.isDelete = isDelete; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getLinkUrl()
/*     */   {
/* 101 */     return this.linkUrl;
/*     */   }
/*     */   
/* 104 */   public void setLinkUrl(String linkUrl) { this.linkUrl = linkUrl; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getName()
/*     */   {
/* 112 */     return this.name;
/*     */   }
/*     */   
/* 115 */   public void setName(String name) { this.name = name; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getUpdateTime()
/*     */   {
/* 123 */     return this.updateTime;
/*     */   }
/*     */   
/* 126 */   public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 133 */     if (this == o) {
/* 134 */       return true;
/*     */     }
/* 136 */     if ((o == null) || (getClass() != o.getClass())) {
/* 137 */       return false;
/*     */     }
/* 139 */     InitImgBO initImgBO = (InitImgBO)o;
/* 140 */     return (Objects.equals(this.createTime, initImgBO.createTime)) && 
/* 141 */       (Objects.equals(this.id, initImgBO.id)) && 
/* 142 */       (Objects.equals(this.imgType, initImgBO.imgType)) && 
/* 143 */       (Objects.equals(this.initImg, initImgBO.initImg)) && 
/* 144 */       (Objects.equals(this.isDelete, initImgBO.isDelete)) && 
/* 145 */       (Objects.equals(this.linkUrl, initImgBO.linkUrl)) && 
/* 146 */       (Objects.equals(this.name, initImgBO.name)) && 
/* 147 */       (Objects.equals(this.updateTime, initImgBO.updateTime));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 152 */     return Objects.hash(new Object[] { this.createTime, this.id, this.imgType, this.initImg, this.isDelete, this.linkUrl, this.name, this.updateTime });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 157 */     StringBuilder sb = new StringBuilder();
/* 158 */     sb.append("class InitImgBO {\n");
/*     */     
/* 160 */     sb.append("    createTime: ").append(toIndentedString(this.createTime)).append("\n");
/* 161 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 162 */     sb.append("    imgType: ").append(toIndentedString(this.imgType)).append("\n");
/* 163 */     sb.append("    initImg: ").append(toIndentedString(this.initImg)).append("\n");
/* 164 */     sb.append("    isDelete: ").append(toIndentedString(this.isDelete)).append("\n");
/* 165 */     sb.append("    linkUrl: ").append(toIndentedString(this.linkUrl)).append("\n");
/* 166 */     sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
/* 167 */     sb.append("    updateTime: ").append(toIndentedString(this.updateTime)).append("\n");
/* 168 */     sb.append("}");
/* 169 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 177 */     if (o == null) {
/* 178 */       return "null";
/*     */     }
/* 180 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\InitImgBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */