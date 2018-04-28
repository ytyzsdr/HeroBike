/*    */ package vc.thinker.colours.client.auth;
/*    */ 
/*    */ import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Interceptor.Chain;
/*    */ import com.squareup.okhttp.Request;
/*    */ import com.squareup.okhttp.Request.Builder;
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */
/*    */ public class ApiKeyAuth implements Interceptor
/*    */ {
    /*    */   private final String location;
    /*    */   private final String paramName;
    /*    */   private String apiKey;
    /*    */
/*    */   public ApiKeyAuth(String location, String paramName)
/*    */   {
/* 18 */     this.location = location;
/* 19 */     this.paramName = paramName;
/*    */   }
    /*    */
/*    */   public String getLocation() {
/* 23 */     return this.location;
/*    */   }
    /*    */
/*    */   public String getParamName() {
/* 27 */     return this.paramName;
/*    */   }
    /*    */
/*    */   public String getApiKey() {
/* 31 */     return this.apiKey;
/*    */   }
    /*    */
/*    */   public void setApiKey(String apiKey) {
/* 35 */     this.apiKey = apiKey;
/*    */   }
    /*    */
/*    */   @Override
    public com.squareup.okhttp.Response intercept(Interceptor.Chain chain)
/*    */     throws IOException
/*    */   {
/* 41 */     Request request = chain.request();
/*    */
/* 43 */     if (this.location == "query") {
/* 44 */       String newQuery = request.uri().getQuery();
/* 45 */       String paramValue = this.paramName + "=" + this.apiKey;
/* 46 */       if (newQuery == null) {
/* 47 */         newQuery = paramValue;
/*    */       } else {
/* 49 */         newQuery = newQuery + "&" + paramValue;
/*    */       }
/*    */
/*    */ URI newUri;
/*    */       try
/*    */       {
/* 55 */         newUri = new URI(request.uri().getScheme(), request.uri().getAuthority(), request.uri().getPath(), newQuery, request.uri().getFragment());
/*    */       } catch (URISyntaxException e) { throw new IOException(e);
/*    */       }
/*    */
/* 60 */       request = request.newBuilder().url(newUri.toURL()).build();
/* 61 */     } else if (this.location == "header")
/*    */     {
/*    */
/* 64 */       request = request.newBuilder().addHeader(this.paramName, this.apiKey).build();
/*    */     }
/* 66 */     return chain.proceed(request);
/*    */   }
/*    */ }


/* Location:              D:\projecttest\trunk\BikeMan\app\libs\android-sdk-client-1.0.jar!\vc\thinker\colours\client\auth\ApiKeyAuth.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */