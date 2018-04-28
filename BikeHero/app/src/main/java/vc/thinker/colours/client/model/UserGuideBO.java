/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class UserGuideBO
/*     */ {
/*     */   @SerializedName("content")
/*  15 */   private String content = null;
/*     */   
/*     */   @SerializedName("id")
/*  18 */   private Long id = null;
/*     */   
/*     */   @SerializedName("title")
/*  21 */   private String title = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("内容")
/*     */   public String getContent()
/*     */   {
/*  31 */     return this.content;
/*     */   }
/*     */   
/*  34 */   public void setContent(String content) { this.content = content; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("id")
/*     */   public Long getId()
/*     */   {
/*  43 */     return this.id;
/*     */   }
/*     */   
/*  46 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("标题")
/*     */   public String getTitle()
/*     */   {
/*  55 */     return this.title;
/*     */   }
/*     */   
/*  58 */   public void setTitle(String title) { this.title = title; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  65 */     if (this == o) {
/*  66 */       return true;
/*     */     }
/*  68 */     if ((o == null) || (getClass() != o.getClass())) {
/*  69 */       return false;
/*     */     }
/*  71 */     UserGuideBO userGuideBO = (UserGuideBO)o;
/*  72 */     return (Objects.equals(this.content, userGuideBO.content)) && 
/*  73 */       (Objects.equals(this.id, userGuideBO.id)) && 
/*  74 */       (Objects.equals(this.title, userGuideBO.title));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  79 */     return Objects.hash(new Object[] { this.content, this.id, this.title });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  84 */     StringBuilder sb = new StringBuilder();
/*  85 */     sb.append("class UserGuideBO {\n");
/*     */     
/*  87 */     sb.append("    content: ").append(toIndentedString(this.content)).append("\n");
/*  88 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/*  89 */     sb.append("    title: ").append(toIndentedString(this.title)).append("\n");
/*  90 */     sb.append("}");
/*  91 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/*  99 */     if (o == null) {
/* 100 */       return "null";
/*     */     }
/* 102 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\UserGuideBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */