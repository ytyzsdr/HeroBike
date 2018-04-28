/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class APIFaultTypeBO
/*    */ {
/*    */   @SerializedName("code")
/* 15 */   private String code = null;
/*    */   
/*    */   @SerializedName("name")
/* 18 */   private String name = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getCode()
/*    */   {
/* 27 */     return this.code;
/*    */   }
/*    */   
/* 30 */   public void setCode(String code) { this.code = code; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getName()
/*    */   {
/* 38 */     return this.name;
/*    */   }
/*    */   
/* 41 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 48 */     if (this == o) {
/* 49 */       return true;
/*    */     }
/* 51 */     if ((o == null) || (getClass() != o.getClass())) {
/* 52 */       return false;
/*    */     }
/* 54 */     APIFaultTypeBO aPIFaultTypeBO = (APIFaultTypeBO)o;
/* 55 */     return (Objects.equals(this.code, aPIFaultTypeBO.code)) && 
/* 56 */       (Objects.equals(this.name, aPIFaultTypeBO.name));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 61 */     return Objects.hash(new Object[] { this.code, this.name });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 66 */     StringBuilder sb = new StringBuilder();
/* 67 */     sb.append("class APIFaultTypeBO {\n");
/*    */     
/* 69 */     sb.append("    code: ").append(toIndentedString(this.code)).append("\n");
/* 70 */     sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
/* 71 */     sb.append("}");
/* 72 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 80 */     if (o == null) {
/* 81 */       return "null";
/*    */     }
/* 83 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIFaultTypeBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */