/*    */ package vc.thinker.colours.client.model;
/*    */
/*    */ import android.annotation.SuppressLint;

import java.util.HashMap;
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
/*    */ public class ModelMap
/*    */   extends HashMap<String, Object>
/*    */ {
/*    */   @Override
public boolean equals(Object o)
/*    */   {
/* 22 */     if (this == o) {
/* 23 */       return true;
/*    */     }
/* 25 */     if ((o == null) || (getClass() != o.getClass())) {
/* 26 */       return false;
/*    */     }
/* 28 */     ModelMap modelMap = (ModelMap)o;
/* 29 */     return true;
/*    */   }
/*    */   
/*    */   @SuppressLint("NewApi")
@Override
public int hashCode()
/*    */   {
/* 34 */     return Objects.hash(new Object[0]);
/*    */   }
/*    */   
/*    */   @Override
public String toString()
/*    */   {
/* 39 */     StringBuilder sb = new StringBuilder();
/* 40 */     sb.append("class ModelMap {\n");
/* 41 */     sb.append("    ").append(toIndentedString(super.toString())).append("\n");
/* 42 */     sb.append("}");
/* 43 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 51 */     if (o == null) {
/* 52 */       return "null";
/*    */     }
/* 54 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ModelMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */