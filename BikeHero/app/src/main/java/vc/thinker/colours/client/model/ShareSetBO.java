/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */
/*     */ public class ShareSetBO
/*     */ {
/*     */   @SerializedName("inviteContent")
/*  15 */   private String inviteContent = null;
/*     */   
/*     */   @SerializedName("inviteTitle")
/*  18 */   private String inviteTitle = null;
/*     */   
/*     */   @SerializedName("inviteWay")
/*  21 */   private String inviteWay = null;
/*     */   
/*     */   @SerializedName("isAllowInvite")
/*  24 */   private Boolean isAllowInvite = null;
/*     */   
/*     */   @SerializedName("isAllowShare")
/*  27 */   private Boolean isAllowShare = null;
/*     */   
/*     */   @SerializedName("shareAppImg")
/*  30 */   private String shareAppImg = null;
/*     */   
/*     */   @SerializedName("shareContent")
/*  33 */   private String shareContent = null;
/*     */   
/*     */   @SerializedName("shareTitle")
/*  36 */   private String shareTitle = null;
/*     */   
/*     */   @SerializedName("shareWay")
/*  39 */   private String shareWay = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("邀请好友内容")
/*     */   public String getInviteContent()
/*     */   {
/*  49 */     return this.inviteContent;
/*     */   }
/*     */   
/*  52 */   public void setInviteContent(String inviteContent) { this.inviteContent = inviteContent; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("邀请好友标题")
/*     */   public String getInviteTitle()
/*     */   {
/*  61 */     return this.inviteTitle;
/*     */   }
/*     */   
/*  64 */   public void setInviteTitle(String inviteTitle) { this.inviteTitle = inviteTitle; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("邀请好友途径 1，微信，2朋友圈，3QQ,4QQ空间")
/*     */   public String getInviteWay()
/*     */   {
/*  73 */     return this.inviteWay;
/*     */   }
/*     */   
/*  76 */   public void setInviteWay(String inviteWay) { this.inviteWay = inviteWay; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否允许邀请")
/*     */   public Boolean getIsAllowInvite()
/*     */   {
/*  85 */     return this.isAllowInvite;
/*     */   }
/*     */   
/*  88 */   public void setIsAllowInvite(Boolean isAllowInvite) { this.isAllowInvite = isAllowInvite; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否允许分享")
/*     */   public Boolean getIsAllowShare()
/*     */   {
/*  97 */     return this.isAllowShare;
/*     */   }
/*     */   
/* 100 */   public void setIsAllowShare(Boolean isAllowShare) { this.isAllowShare = isAllowShare; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("分享的图标")
/*     */   public String getShareAppImg()
/*     */   {
/* 109 */     return this.shareAppImg;
/*     */   }
/*     */   
/* 112 */   public void setShareAppImg(String shareAppImg) { this.shareAppImg = shareAppImg; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("行程分享内容")
/*     */   public String getShareContent()
/*     */   {
/* 121 */     return this.shareContent;
/*     */   }
/*     */   
/* 124 */   public void setShareContent(String shareContent) { this.shareContent = shareContent; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("行程分享标题")
/*     */   public String getShareTitle()
/*     */   {
/* 133 */     return this.shareTitle;
/*     */   }
/*     */   
/* 136 */   public void setShareTitle(String shareTitle) { this.shareTitle = shareTitle; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("行程分享途径 1，微信，2朋友圈，3QQ,4QQ空间")
/*     */   public String getShareWay()
/*     */   {
/* 145 */     return this.shareWay;
/*     */   }
/*     */   
/* 148 */   public void setShareWay(String shareWay) { this.shareWay = shareWay; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 155 */     if (this == o) {
/* 156 */       return true;
/*     */     }
/* 158 */     if ((o == null) || (getClass() != o.getClass())) {
/* 159 */       return false;
/*     */     }
/* 161 */     ShareSetBO shareSetBO = (ShareSetBO)o;
/* 162 */     return (Objects.equals(this.inviteContent, shareSetBO.inviteContent)) && 
/* 163 */       (Objects.equals(this.inviteTitle, shareSetBO.inviteTitle)) && 
/* 164 */       (Objects.equals(this.inviteWay, shareSetBO.inviteWay)) && 
/* 165 */       (Objects.equals(this.isAllowInvite, shareSetBO.isAllowInvite)) && 
/* 166 */       (Objects.equals(this.isAllowShare, shareSetBO.isAllowShare)) && 
/* 167 */       (Objects.equals(this.shareAppImg, shareSetBO.shareAppImg)) && 
/* 168 */       (Objects.equals(this.shareContent, shareSetBO.shareContent)) && 
/* 169 */       (Objects.equals(this.shareTitle, shareSetBO.shareTitle)) && 
/* 170 */       (Objects.equals(this.shareWay, shareSetBO.shareWay));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 175 */     return Objects.hash(new Object[] { this.inviteContent, this.inviteTitle, this.inviteWay, this.isAllowInvite, this.isAllowShare, this.shareAppImg, this.shareContent, this.shareTitle, this.shareWay });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 180 */     StringBuilder sb = new StringBuilder();
/* 181 */     sb.append("class ShareSetBO {\n");
/*     */     
/* 183 */     sb.append("    inviteContent: ").append(toIndentedString(this.inviteContent)).append("\n");
/* 184 */     sb.append("    inviteTitle: ").append(toIndentedString(this.inviteTitle)).append("\n");
/* 185 */     sb.append("    inviteWay: ").append(toIndentedString(this.inviteWay)).append("\n");
/* 186 */     sb.append("    isAllowInvite: ").append(toIndentedString(this.isAllowInvite)).append("\n");
/* 187 */     sb.append("    isAllowShare: ").append(toIndentedString(this.isAllowShare)).append("\n");
/* 188 */     sb.append("    shareAppImg: ").append(toIndentedString(this.shareAppImg)).append("\n");
/* 189 */     sb.append("    shareContent: ").append(toIndentedString(this.shareContent)).append("\n");
/* 190 */     sb.append("    shareTitle: ").append(toIndentedString(this.shareTitle)).append("\n");
/* 191 */     sb.append("    shareWay: ").append(toIndentedString(this.shareWay)).append("\n");
/* 192 */     sb.append("}");
/* 193 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 201 */     if (o == null) {
/* 202 */       return "null";
/*     */     }
/* 204 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ShareSetBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */