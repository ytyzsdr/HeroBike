/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class ListResponseOfBicycleTypeBO
/*     */ {
/*     */   @SerializedName("error")
/*  17 */   private String error = null;
/*     */   
/*     */   @SerializedName("error_description")
/*  20 */   private String errorDescription = null;
/*     */   
/*     */   @SerializedName("items")
/*  23 */   private List<BicycleTypeBO> items = new ArrayList();
/*     */   
/*     */   @SerializedName("success")
/*  26 */   private Boolean success = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getError()
/*     */   {
/*  35 */     return this.error;
/*     */   }
/*     */   
/*  38 */   public void setError(String error) { this.error = error; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getErrorDescription()
/*     */   {
/*  46 */     return this.errorDescription;
/*     */   }
/*     */   
/*  49 */   public void setErrorDescription(String errorDescription) { this.errorDescription = errorDescription; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public List<BicycleTypeBO> getItems()
/*     */   {
/*  57 */     return this.items;
/*     */   }
/*     */   
/*  60 */   public void setItems(List<BicycleTypeBO> items) { this.items = items; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getSuccess()
/*     */   {
/*  68 */     return this.success;
/*     */   }
/*     */   
/*  71 */   public void setSuccess(Boolean success) { this.success = success; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  78 */     if (this == o) {
/*  79 */       return true;
/*     */     }
/*  81 */     if ((o == null) || (getClass() != o.getClass())) {
/*  82 */       return false;
/*     */     }
/*  84 */     ListResponseOfBicycleTypeBO listResponseOfBicycleTypeBO = (ListResponseOfBicycleTypeBO)o;
/*  85 */     return (Objects.equals(this.error, listResponseOfBicycleTypeBO.error)) && 
/*  86 */       (Objects.equals(this.errorDescription, listResponseOfBicycleTypeBO.errorDescription)) && 
/*  87 */       (Objects.equals(this.items, listResponseOfBicycleTypeBO.items)) && 
/*  88 */       (Objects.equals(this.success, listResponseOfBicycleTypeBO.success));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  93 */     return Objects.hash(new Object[] { this.error, this.errorDescription, this.items, this.success });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  98 */     StringBuilder sb = new StringBuilder();
/*  99 */     sb.append("class ListResponseOfBicycleTypeBO {\n");
/*     */     
/* 101 */     sb.append("    error: ").append(toIndentedString(this.error)).append("\n");
/* 102 */     sb.append("    errorDescription: ").append(toIndentedString(this.errorDescription)).append("\n");
/* 103 */     sb.append("    items: ").append(toIndentedString(this.items)).append("\n");
/* 104 */     sb.append("    success: ").append(toIndentedString(this.success)).append("\n");
/* 105 */     sb.append("}");
/* 106 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 114 */     if (o == null) {
/* 115 */       return "null";
/*     */     }
/* 117 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ListResponseOfBicycleTypeBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */