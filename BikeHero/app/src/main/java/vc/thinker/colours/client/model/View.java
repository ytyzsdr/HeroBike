/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class View
/*    */ {
/*    */   @SerializedName("contentType")
/* 15 */   private String contentType = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getContentType()
/*    */   {
/* 24 */     return this.contentType;
/*    */   }
/*    */   
/* 27 */   public void setContentType(String contentType) { this.contentType = contentType; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 34 */     if (this == o) {
/* 35 */       return true;
/*    */     }
/* 37 */     if ((o == null) || (getClass() != o.getClass())) {
/* 38 */       return false;
/*    */     }
/* 40 */     View view = (View)o;
/* 41 */     return Objects.equals(this.contentType, view.contentType);
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 46 */     return Objects.hash(new Object[] { this.contentType });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 51 */     StringBuilder sb = new StringBuilder();
/* 52 */     sb.append("class View {\n");
/*    */     
/* 54 */     sb.append("    contentType: ").append(toIndentedString(this.contentType)).append("\n");
/* 55 */     sb.append("}");
/* 56 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 64 */     if (o == null) {
/* 65 */       return "null";
/*    */     }
/* 67 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\View.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */