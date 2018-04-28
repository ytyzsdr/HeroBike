/*    */ package vc.thinker.colours.client;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CollectionFormats
/*    */ {
/*    */   public static class CSVParams
/*    */   {
/*    */     protected List<String> params;
/*    */     
/*    */     public CSVParams() {}
/*    */     
/*    */     public CSVParams(List<String> params)
/*    */     {
/* 16 */       this.params = params;
/*    */     }
/*    */     
/*    */     public CSVParams(String... params) {
/* 20 */       this.params = Arrays.asList(params);
/*    */     }
/*    */     
/*    */     public List<String> getParams() {
/* 24 */       return this.params;
/*    */     }
/*    */     
/*    */     public void setParams(List<String> params) {
/* 28 */       this.params = params;
/*    */     }
/*    */     
/*    */     public String toString()
/*    */     {
/* 33 */       return StringUtil.join((String[])this.params.toArray(new String[0]), ",");
/*    */     }
/*    */   }
/*    */   
/*    */   public static class SSVParams
/*    */     extends CSVParams
/*    */   {
/*    */     public SSVParams() {}
/*    */     
/*    */     public SSVParams(List<String> params)
/*    */     {
/* 44 */       super();
/*    */     }
/*    */     
/*    */     public SSVParams(String... params) {
/* 48 */       super();
/*    */     }
/*    */     
/*    */     public String toString()
/*    */     {
/* 53 */       return StringUtil.join((String[])this.params.toArray(new String[0]), " ");
/*    */     }
/*    */   }
/*    */   
/*    */   public static class TSVParams extends CSVParams
/*    */   {
/*    */     public TSVParams() {}
/*    */     
/*    */     public TSVParams(List<String> params)
/*    */     {
/* 63 */       super();
/*    */     }
/*    */     
/*    */     public TSVParams(String... params) {
/* 67 */       super();
/*    */     }
/*    */     
/*    */     public String toString()
/*    */     {
/* 72 */       return StringUtil.join((String[])this.params.toArray(new String[0]), "\t");
/*    */     }
/*    */   }
/*    */   
/*    */   public static class PIPESParams extends CSVParams
/*    */   {
/*    */     public PIPESParams() {}
/*    */     
/*    */     public PIPESParams(List<String> params)
/*    */     {
/* 82 */       super();
/*    */     }
/*    */     
/*    */     public PIPESParams(String... params) {
/* 86 */       super();
/*    */     }
/*    */     
/*    */     public String toString()
/*    */     {
/* 91 */       return StringUtil.join((String[])this.params.toArray(new String[0]), "|");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\CollectionFormats.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */