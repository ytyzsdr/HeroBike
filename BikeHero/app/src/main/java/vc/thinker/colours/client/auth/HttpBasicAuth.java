/*    */ package vc.thinker.colours.client.auth;
/*    */ 
/*    */ import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Interceptor.Chain;
/*    */ import com.squareup.okhttp.Request;
/*    */ import com.squareup.okhttp.Request.Builder;
/*    */ import com.squareup.okhttp.Response;
/*    */ import java.io.IOException;
/*    */
/*    */ public class HttpBasicAuth implements com.squareup.okhttp.Interceptor
/*    */ {
    /*    */   private String username;
    /*    */   private String password;
    /*    */
/*    */   public String getUsername()
/*    */   {
/* 16 */     return this.username;
/*    */   }
    /*    */
/*    */   public void setUsername(String username) {
/* 20 */     this.username = username;
/*    */   }
    /*    */
/*    */   public String getPassword() {
/* 24 */     return this.password;
/*    */   }
    /*    */
/*    */   public void setPassword(String password) {
/* 28 */     this.password = password;
/*    */   }
    /*    */
/*    */   public void setCredentials(String username, String password) {
/* 32 */     this.username = username;
/* 33 */     this.password = password;
/*    */   }
    /*    */
/*    */   @Override
    public Response intercept(Interceptor.Chain chain) throws IOException
/*    */   {
/* 38 */     Request request = chain.request();
/*    */
/*    */
/* 41 */     if (request.header("Authorization") == null) {
/* 42 */       String credentials = com.squareup.okhttp.Credentials.basic(this.username, this.password);
/*    */
/*    */
/* 45 */       request = request.newBuilder().addHeader("Authorization", credentials).build();
/*    */     }
/* 47 */     return chain.proceed(request);
/*    */   }
/*    */ }


/* Location:              D:\projecttest\trunk\BikeMan\app\libs\android-sdk-client-1.0.jar!\vc\thinker\colours\client\auth\HttpBasicAuth.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */