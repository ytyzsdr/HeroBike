/*     */ package vc.thinker.colours.client.auth;
/*     */ 
/*     */ import com.squareup.okhttp.Interceptor;
/*     */ import com.squareup.okhttp.Interceptor.Chain;
/*     */ import com.squareup.okhttp.OkHttpClient;
/*     */ import com.squareup.okhttp.Request;
/*     */ import com.squareup.okhttp.Request.Builder;
/*     */ import com.squareup.okhttp.Response;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.apache.oltu.oauth2.client.OAuthClient;
/*     */ import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
/*     */ import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
/*     */ import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder;
/*     */ import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
/*     */ import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
/*     */ import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
/*     */ import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
/*     */ import org.apache.oltu.oauth2.common.message.types.GrantType;
/*     */ import org.apache.oltu.oauth2.common.token.BasicOAuthToken;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OAuth
/*     */   implements Interceptor
/*     */ {
    /*     */   private volatile String accessToken;
    /*     */   private volatile String refreshToken;
    /*     */   private OAuthClient oauthClient;
    /*     */   private TokenRequestBuilder tokenRequestBuilder;
    /*     */   private AuthenticationRequestBuilder authenticationRequestBuilder;
    /*     */   private AccessTokenListener accessTokenListener;
    /*     */
/*     */   public OAuth(OkHttpClient client, TokenRequestBuilder requestBuilder)
/*     */   {
/*  41 */     this.oauthClient = new OAuthClient(new OAuthOkHttpClient(client));
/*  42 */     this.tokenRequestBuilder = requestBuilder;
/*     */   }
    /*     */
/*     */   public OAuth(TokenRequestBuilder requestBuilder) {
/*  46 */     this(new OkHttpClient(), requestBuilder);
/*     */   }
    /*     */
/*     */   public OAuth(OAuthFlow flow, String authorizationUrl, String tokenUrl, String scopes) {
/*  50 */     this(OAuthClientRequest.tokenLocation(tokenUrl).setScope(scopes));
/*  51 */     setFlow(flow);
/*  52 */     this.authenticationRequestBuilder = OAuthClientRequest.authorizationLocation(authorizationUrl);
/*     */   }
    /*     */
/*  55 */   public OAuth(OAuthFlow flow, String authorizationUrl, String tokenUrl) { this(OAuthClientRequest.tokenLocation(tokenUrl));
/*  56 */     setFlow(flow);
/*  57 */     this.authenticationRequestBuilder = OAuthClientRequest.authorizationLocation(authorizationUrl);
/*     */   }
    /*     */
/*     */   public void setFlow(OAuthFlow flow) {
/*  61 */     switch (flow) {
/*     */     case accessCode: 
/*     */     case implicit: 
/*  64 */       this.tokenRequestBuilder.setGrantType(GrantType.AUTHORIZATION_CODE);
/*  65 */       break;
/*     */     case password: 
/*  67 */       this.tokenRequestBuilder.setGrantType(GrantType.PASSWORD);
/*  68 */       break;
/*     */     case application: 
/*  70 */       this.tokenRequestBuilder.setGrantType(GrantType.CLIENT_CREDENTIALS);
/*  71 */       break;
/*     */     case refreshToken: 
/*  73 */       this.tokenRequestBuilder.setGrantType(GrantType.REFRESH_TOKEN);
/*     */     }
/*     */     
/*     */   }
    /*     */
/*     */ 
/*     */ 
/*     */   @Override
    public Response intercept(Chain chain)
/*     */     throws IOException
/*     */   {
/*  83 */     Request request = chain.request();
/*     */
/*     */
/*  86 */     if (request.header("Authorization") != null) {
/*  87 */       return chain.proceed(request);
/*     */     }
/*     */
/*     */
/*     */
/*  92 */     if ((getAccessToken() == null) || (getRefreshToken() != null)) {
/*  93 */       updateAccessToken(null);
/*     */     }
/*     */
/*     */
/*  97 */     Builder rb = request.newBuilder();
/*     */
/*  99 */     String requestAccessToken = new String(getAccessToken());
/*     */
        OAuthClientRequest oAuthRequest;
/*     */     try
/*     */     {
/* 103 */       oAuthRequest = new OAuthBearerClientRequest(request.urlString()).setAccessToken(requestAccessToken).buildHeaderMessage();
/*     */     }
/*     */     catch (OAuthSystemException e) {
/*     */
/* 107 */       throw new IOException(e);
/*     */     }
/*     */
/* 110 */     for (Entry<String, String> header : oAuthRequest.getHeaders().entrySet()) {
/* 111 */       rb.addHeader((String)header.getKey(), (String)header.getValue());
/*     */     }
/* 113 */     rb.url(oAuthRequest.getLocationUri());
/*     */
/*     */
/* 116 */     Response response = chain.proceed(rb.build());
/*     */
/*     */
/*     */
/* 120 */     if (response.code() == 401) {
/* 121 */       updateAccessToken(requestAccessToken);
/* 122 */       return intercept(chain);
/*     */     }
/* 124 */     return response;
/*     */   }
    /*     */
/*     */   public synchronized void updateAccessToken(String requestAccessToken) throws IOException {
/* 128 */     if ((getAccessToken() == null) || (getAccessToken().equals(requestAccessToken)) || (getRefreshToken() != null)) {
/*     */       try
/*     */       {
/* 131 */         OAuthJSONAccessTokenResponse accessTokenResponse = this.oauthClient.accessToken(this.tokenRequestBuilder.buildBodyMessage());
/* 132 */         setAccessToken(accessTokenResponse.getAccessToken());
/* 133 */         if (this.accessTokenListener != null) {
/* 134 */           this.accessTokenListener.notify((BasicOAuthToken)accessTokenResponse.getOAuthToken());
/*     */         }
/*     */       } catch (OAuthSystemException e) {
/* 137 */         throw new IOException(e);
/*     */       } catch (OAuthProblemException e) {
/* 139 */         throw new IOException(e);
/*     */       }
/*     */     }
/*     */   }
    /*     */
/*     */   public void registerAccessTokenListener(AccessTokenListener accessTokenListener) {
/* 145 */     this.accessTokenListener = accessTokenListener;
/*     */   }
    /*     */
/*     */   public synchronized String getAccessToken() {
/* 149 */     return this.accessToken;
/*     */   }
    /*     */
/*     */   public synchronized void setAccessToken(String accessToken) {
/* 153 */     this.accessToken = accessToken;
/*     */   }
    /*     */
/*     */   public synchronized String getRefreshToken()
/*     */   {
/* 158 */     return this.refreshToken;
/*     */   }
    /*     */
/*     */   public synchronized void setRefreshToken(String refreshToken) {
/* 162 */     this.refreshToken = refreshToken;
/*     */   }
    /*     */
/*     */   public TokenRequestBuilder getTokenRequestBuilder() {
/* 166 */     return this.tokenRequestBuilder;
/*     */   }
    /*     */
/*     */   public void setTokenRequestBuilder(TokenRequestBuilder tokenRequestBuilder) {
/* 170 */     this.tokenRequestBuilder = tokenRequestBuilder;
/*     */   }
    /*     */
/*     */   public AuthenticationRequestBuilder getAuthenticationRequestBuilder() {
/* 174 */     return this.authenticationRequestBuilder;
/*     */   }
    /*     */
/*     */   public void setAuthenticationRequestBuilder(AuthenticationRequestBuilder authenticationRequestBuilder) {
/* 178 */     this.authenticationRequestBuilder = authenticationRequestBuilder;
/*     */   }
    /*     */
/*     */   public static abstract interface AccessTokenListener
/*     */   {
        /*     */     public abstract void notify(BasicOAuthToken paramBasicOAuthToken);
/*     */   }
/*     */ }


/* Location:              D:\projecttest\trunk\BikeMan\app\libs\android-sdk-client-1.0.jar!\vc\thinker\colours\client\auth\OAuth.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */