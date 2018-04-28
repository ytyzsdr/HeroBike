/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class FeedbackTypeBO
/*     */ {
/*     */   @SerializedName("id")
/*  15 */   private Long id = null;
/*     */   
/*     */   @SerializedName("isCheckLock")
/*  18 */   private Boolean isCheckLock = null;
/*     */   
/*     */   @SerializedName("type")
/*  21 */   private String type = null;
/*     */   
/*     */   @SerializedName("typeDesc")
/*  24 */   private String typeDesc = null;
/*     */   
/*     */   @SerializedName("typeName")
/*  27 */   private String typeName = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  36 */     return this.id;
/*     */   }
/*     */   
/*  39 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getIsCheckLock()
/*     */   {
/*  47 */     return this.isCheckLock;
/*     */   }
/*     */   
/*  50 */   public void setIsCheckLock(Boolean isCheckLock) { this.isCheckLock = isCheckLock; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getType()
/*     */   {
/*  58 */     return this.type;
/*     */   }
/*     */   
/*  61 */   public void setType(String type) { this.type = type; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTypeDesc()
/*     */   {
/*  69 */     return this.typeDesc;
/*     */   }
/*     */   
/*  72 */   public void setTypeDesc(String typeDesc) { this.typeDesc = typeDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTypeName()
/*     */   {
/*  80 */     return this.typeName;
/*     */   }
/*     */   
/*  83 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  90 */     if (this == o) {
/*  91 */       return true;
/*     */     }
/*  93 */     if ((o == null) || (getClass() != o.getClass())) {
/*  94 */       return false;
/*     */     }
/*  96 */     FeedbackTypeBO feedbackTypeBO = (FeedbackTypeBO)o;
/*  97 */     return (Objects.equals(this.id, feedbackTypeBO.id)) && 
/*  98 */       (Objects.equals(this.isCheckLock, feedbackTypeBO.isCheckLock)) && 
/*  99 */       (Objects.equals(this.type, feedbackTypeBO.type)) && 
/* 100 */       (Objects.equals(this.typeDesc, feedbackTypeBO.typeDesc)) && 
/* 101 */       (Objects.equals(this.typeName, feedbackTypeBO.typeName));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 106 */     return Objects.hash(new Object[] { this.id, this.isCheckLock, this.type, this.typeDesc, this.typeName });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 111 */     StringBuilder sb = new StringBuilder();
/* 112 */     sb.append("class FeedbackTypeBO {\n");
/*     */     
/* 114 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 115 */     sb.append("    isCheckLock: ").append(toIndentedString(this.isCheckLock)).append("\n");
/* 116 */     sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
/* 117 */     sb.append("    typeDesc: ").append(toIndentedString(this.typeDesc)).append("\n");
/* 118 */     sb.append("    typeName: ").append(toIndentedString(this.typeName)).append("\n");
/* 119 */     sb.append("}");
/* 120 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 128 */     if (o == null) {
/* 129 */       return "null";
/*     */     }
/* 131 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\FeedbackTypeBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */