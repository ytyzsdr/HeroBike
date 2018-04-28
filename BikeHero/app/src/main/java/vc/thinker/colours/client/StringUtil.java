/*    */ package vc.thinker.colours.client;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringUtil
/*    */ {
/*    */   public static boolean containsIgnoreCase(String[] array, String value)
/*    */   {
/* 13 */     for (String str : array) {
/* 14 */       if ((value == null) && (str == null)) return true;
/* 15 */       if ((value != null) && (value.equalsIgnoreCase(str))) return true;
/*    */     }
/* 17 */     return false;
/*    */   }
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
/*    */ 
/*    */   public static String join(String[] array, String separator)
/*    */   {
/* 32 */     int len = array.length;
/* 33 */     if (len == 0) { return "";
/*    */     }
/* 35 */     StringBuilder out = new StringBuilder();
/* 36 */     out.append(array[0]);
/* 37 */     for (int i = 1; i < len; i++) {
/* 38 */       out.append(separator).append(array[i]);
/*    */     }
/* 40 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\StringUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */