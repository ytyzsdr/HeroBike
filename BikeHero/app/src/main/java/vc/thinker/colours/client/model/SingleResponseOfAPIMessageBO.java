/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class SingleResponseOfAPIMessageBO
/*     */ {
/*     */   @SerializedName("error")
/*  16 */   private String error = null;
/*     */   
/*     */   @SerializedName("error_description")
/*  19 */   private String errorDescription = null;
/*     */   
/*     */   @SerializedName("item")
/*  22 */   private APIMessageBO item = null;
/*     */   
/*     */   @SerializedName("success")
/*  25 */   private Boolean success = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getError()
/*     */   {
/*  34 */     return this.error;
/*     */   }
/*     */   
/*  37 */   public void setError(String error) { this.error = error; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getErrorDescription()
/*     */   {
/*  45 */     return this.errorDescription;
/*     */   }
/*     */   
/*  48 */   public void setErrorDescription(String errorDescription) { this.errorDescription = errorDescription; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public APIMessageBO getItem()
/*     */   {
/*  56 */     return this.item;
/*     */   }
/*     */   
/*  59 */   public void setItem(APIMessageBO item) { this.item = item; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getSuccess()
/*     */   {
/*  67 */     return this.success;
/*     */   }
/*     */   
/*  70 */   public void setSuccess(Boolean success) { this.success = success; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  77 */     if (this == o) {
/*  78 */       return true;
/*     */     }
/*  80 */     if ((o == null) || (getClass() != o.getClass())) {
/*  81 */       return false;
/*     */     }
/*  83 */     SingleResponseOfAPIMessageBO singleResponseOfAPIMessageBO = (SingleResponseOfAPIMessageBO)o;
/*  84 */     return (Objects.equals(this.error, singleResponseOfAPIMessageBO.error)) && 
/*  85 */       (Objects.equals(this.errorDescription, singleResponseOfAPIMessageBO.errorDescription)) && 
/*  86 */       (Objects.equals(this.item, singleResponseOfAPIMessageBO.item)) && 
/*  87 */       (Objects.equals(this.success, singleResponseOfAPIMessageBO.success));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  92 */     return Objects.hash(new Object[] { this.error, this.errorDescription, this.item, this.success });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  97 */     StringBuilder sb = new StringBuilder();
/*  98 */     sb.append("class SingleResponseOfAPIMessageBO {\n");
/*     */     
/* 100 */     sb.append("    error: ").append(toIndentedString(this.error)).append("\n");
/* 101 */     sb.append("    errorDescription: ").append(toIndentedString(this.errorDescription)).append("\n");
/* 102 */     sb.append("    item: ").append(toIndentedString(this.item)).append("\n");
/* 103 */     sb.append("    success: ").append(toIndentedString(this.success)).append("\n");
/* 104 */     sb.append("}");
/* 105 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 113 */     if (o == null) {
/* 114 */       return "null";
/*     */     }
/* 116 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\SingleResponseOfAPIMessageBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */