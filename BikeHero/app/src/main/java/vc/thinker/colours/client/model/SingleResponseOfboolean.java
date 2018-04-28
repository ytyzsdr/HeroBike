/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class SingleResponseOfboolean
/*     */ {
/*     */   @SerializedName("error")
/*  15 */   private String error = null;
/*     */   
/*     */   @SerializedName("error_description")
/*  18 */   private String errorDescription = null;
/*     */   
/*     */   @SerializedName("item")
/*  21 */   private Boolean item = null;
/*     */   
/*     */   @SerializedName("success")
/*  24 */   private Boolean success = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getError()
/*     */   {
/*  33 */     return this.error;
/*     */   }
/*     */   
/*  36 */   public void setError(String error) { this.error = error; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getErrorDescription()
/*     */   {
/*  44 */     return this.errorDescription;
/*     */   }
/*     */   
/*  47 */   public void setErrorDescription(String errorDescription) { this.errorDescription = errorDescription; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getItem()
/*     */   {
/*  55 */     return this.item;
/*     */   }
/*     */   
/*  58 */   public void setItem(Boolean item) { this.item = item; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getSuccess()
/*     */   {
/*  66 */     return this.success;
/*     */   }
/*     */   
/*  69 */   public void setSuccess(Boolean success) { this.success = success; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  76 */     if (this == o) {
/*  77 */       return true;
/*     */     }
/*  79 */     if ((o == null) || (getClass() != o.getClass())) {
/*  80 */       return false;
/*     */     }
/*  82 */     SingleResponseOfboolean singleResponseOfboolean = (SingleResponseOfboolean)o;
/*  83 */     return (Objects.equals(this.error, singleResponseOfboolean.error)) && 
/*  84 */       (Objects.equals(this.errorDescription, singleResponseOfboolean.errorDescription)) && 
/*  85 */       (Objects.equals(this.item, singleResponseOfboolean.item)) && 
/*  86 */       (Objects.equals(this.success, singleResponseOfboolean.success));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  91 */     return Objects.hash(new Object[] { this.error, this.errorDescription, this.item, this.success });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  96 */     StringBuilder sb = new StringBuilder();
/*  97 */     sb.append("class SingleResponseOfboolean {\n");
/*     */     
/*  99 */     sb.append("    error: ").append(toIndentedString(this.error)).append("\n");
/* 100 */     sb.append("    errorDescription: ").append(toIndentedString(this.errorDescription)).append("\n");
/* 101 */     sb.append("    item: ").append(toIndentedString(this.item)).append("\n");
/* 102 */     sb.append("    success: ").append(toIndentedString(this.success)).append("\n");
/* 103 */     sb.append("}");
/* 104 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 112 */     if (o == null) {
/* 113 */       return "null";
/*     */     }
/* 115 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\SingleResponseOfboolean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */