/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;

/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class SimpleResponse
/*    */ {
/*    */   @SerializedName("error")
/* 15 */   private String error = null;
/*    */   
/*    */   @SerializedName("error_description")
/* 18 */   private String errorDescription = null;
/*    */   
/*    */   @SerializedName("success")
/* 21 */   private Boolean success = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getError()
/*    */   {
/* 30 */     return this.error;
/*    */   }
/*    */   
/* 33 */   public void setError(String error) { this.error = error; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getErrorDescription()
/*    */   {
/* 41 */     return this.errorDescription;
/*    */   }
/*    */   
/* 44 */   public void setErrorDescription(String errorDescription) { this.errorDescription = errorDescription; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public Boolean getSuccess()
/*    */   {
/* 52 */     return this.success;
/*    */   }
/*    */   
/* 55 */   public void setSuccess(Boolean success) { this.success = success; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 62 */     if (this == o) {
/* 63 */       return true;
/*    */     }
/* 65 */     if ((o == null) || (getClass() != o.getClass())) {
/* 66 */       return false;
/*    */     }
/* 68 */     SimpleResponse simpleResponse = (SimpleResponse)o;
/* 69 */     return (Objects.equals(this.error, simpleResponse.error)) && 
/* 70 */       (Objects.equals(this.errorDescription, simpleResponse.errorDescription)) && 
/* 71 */       (Objects.equals(this.success, simpleResponse.success));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 76 */     return Objects.hash(new Object[] { this.error, this.errorDescription, this.success });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 81 */     StringBuilder sb = new StringBuilder();
/* 82 */     sb.append("class SimpleResponse {\n");
/*    */     
/* 84 */     sb.append("    error: ").append(toIndentedString(this.error)).append("\n");
/* 85 */     sb.append("    errorDescription: ").append(toIndentedString(this.errorDescription)).append("\n");
/* 86 */     sb.append("    success: ").append(toIndentedString(this.success)).append("\n");
/* 87 */     sb.append("}");
/* 88 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 96 */     if (o == null) {
/* 97 */       return "null";
/*    */     }
/* 99 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\SimpleResponse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */