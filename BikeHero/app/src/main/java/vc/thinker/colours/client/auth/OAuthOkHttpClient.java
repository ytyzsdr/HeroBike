/*    */ package vc.thinker.colours.client.auth;
/*    */ 
/*    */ import com.squareup.okhttp.Call;
/*    */ import com.squareup.okhttp.MediaType;
/*    */ import com.squareup.okhttp.OkHttpClient;
/*    */ import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
/*    */ import com.squareup.okhttp.RequestBody;
/*    */ import com.squareup.okhttp.Response;
/*    */ import com.squareup.okhttp.ResponseBody;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import org.apache.oltu.oauth2.client.HttpClient;
/*    */ import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
/*    */ import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
/*    */ import org.apache.oltu.oauth2.client.response.OAuthClientResponseFactory;
/*    */ import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
/*    */ import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
/*    */ 
/*    */ public class OAuthOkHttpClient implements HttpClient
/*    */ {
/*    */   private OkHttpClient client;
/*    */   
/*    */   public OAuthOkHttpClient()
/*    */   {
/* 26 */     this.client = new OkHttpClient();
/*    */   }
/*    */   
/*    */   public OAuthOkHttpClient(OkHttpClient client) {
/* 30 */     this.client = client;
/*    */   }
/*    */   
/*    */ 
/*    */   public <T extends OAuthClientResponse> T execute(OAuthClientRequest request, Map<String, String> headers, String requestMethod, Class<T> responseClass)
/*    */     throws OAuthSystemException, OAuthProblemException
/*    */   {
/* 37 */     MediaType mediaType = MediaType.parse("application/json");
/* 38 */     Request.Builder requestBuilder = new Request.Builder().url(request.getLocationUri());
/*    */     
/* 40 */     if (headers != null) {
/* 41 */       for (Entry<String, String> entry : headers.entrySet()) {
/* 42 */         if (((String)entry.getKey()).equalsIgnoreCase("Content-Type")) {
/* 43 */           mediaType = MediaType.parse((String)entry.getValue());
/*    */         } else {
/* 45 */           requestBuilder.addHeader((String)entry.getKey(), (String)entry.getValue());
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 50 */     RequestBody body = request.getBody() != null ? RequestBody.create(mediaType, request.getBody()) : null;
/* 51 */     requestBuilder.method(requestMethod, body);
/*    */     try
/*    */     {
/* 54 */       Response response = this.client.newCall(requestBuilder.build()).execute();
/* 55 */       return OAuthClientResponseFactory.createCustomResponse(response
/* 56 */         .body().string(), response
/* 57 */         .body().contentType().toString(), response
/* 58 */         .code(), responseClass);
/*    */     }
/*    */     catch (IOException e) {
/* 61 */       throw new OAuthSystemException(e);
/*    */     }
/*    */   }
/*    */   
/*    */   public void shutdown() {}
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\auth\OAuthOkHttpClient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */