/*    */ package vc.thinker.colours.client.model;
/*    */
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class MultipartFile
/*    */ {
/*    */   public boolean equals(Object o)
/*    */   {
/* 20 */     if (this == o) {
/* 21 */       return true;
/*    */     }
/* 23 */     if ((o == null) || (getClass() != o.getClass())) {
/* 24 */       return false;
/*    */     }
/* 26 */     MultipartFile multipartFile = (MultipartFile)o;
/* 27 */     return true;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 32 */     return Objects.hash(new Object[0]);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 37 */     StringBuilder sb = new StringBuilder();
/* 38 */     sb.append("class MultipartFile {\n");
/*    */     
/* 40 */     sb.append("}");
/* 41 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 49 */     if (o == null) {
/* 50 */       return "null";
/*    */     }
/* 52 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\MultipartFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */