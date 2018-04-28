/*     */ package vc.thinker.colours.client;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.squareup.okhttp.Interceptor;
/*     */ import com.squareup.okhttp.Interceptor.Chain;
/*     */ import com.squareup.okhttp.OkHttpClient;
/*     */ import com.squareup.okhttp.Request;
/*     */ import com.squareup.okhttp.Request.Builder;
/*     */ import com.squareup.okhttp.Response;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder;
/*     */ import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
/*     */ import org.apache.oltu.oauth2.common.message.types.GrantType;
/*     */ import retrofit.Retrofit;
/*     */ import retrofit.RxJavaCallAdapterFactory;
/*     */ import vc.thinker.colours.client.auth.ApiKeyAuth;
/*     */ import vc.thinker.colours.client.auth.HttpBasicAuth;
/*     */ import vc.thinker.colours.client.auth.OAuth;
/*     */ import vc.thinker.colours.client.auth.OAuth.AccessTokenListener;
/*     */ import vc.thinker.colours.client.auth.OAuthFlow;
/*     */
/*     */
/*     */
/*     */
/*     */
public class ApiClient {
    public static String apiVersion = "1.0";
    public static String tokenUrl = "http://192.168.1.250:14050/oauth/token";
    public static String baseUrl = "http://192.168.1.250:14060/";
    public static String userAgent = "";
    private Map<String, Interceptor> apiAuthorizations;
    private OkHttpClient okClient;
    private Retrofit.Builder adapterBuilder;
    JsonSerializer<Date> ser;
    JsonDeserializer<Date> deser;

    public ApiClient() {
        this.ser = new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null?null:new JsonPrimitive(Long.valueOf(src.getTime()));
            }
        };
        this.deser = new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return json == null?null:new Date(json.getAsLong());
            }
        };
        this.apiAuthorizations = new LinkedHashMap();
        this.createDefaultAdapter();
    }

    public ApiClient(String[] authNames) {
        this();
        String[] var2 = authNames;
        int var3 = authNames.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String authName = var2[var4];
            OAuth auth;
            if(authName == "oauth2-clientcredentials") {
                auth = new OAuth(OAuthFlow.application, "", tokenUrl);
            } else if(authName == "oauth2-password") {
                auth = new OAuth(OAuthFlow.password, "", tokenUrl);
            } else {
                if(authName != "refresh-token") {
                    throw new RuntimeException("auth name \"" + authName + "\" not found in available auth names");
                }

                auth = new OAuth(OAuthFlow.refreshToken, "", tokenUrl);
            }

            this.addAuthorization(authName, auth);
        }

    }

    public ApiClient(String authName) {
        this(new String[]{authName});
    }

    public ApiClient(String authName, String apiKey) {
        this(authName);
        this.setApiKey(apiKey);
    }

    public ApiClient(String authName, String username, String password) {
        this(authName);
        this.setClientCredentials(username, password);
    }

    public ApiClient(String authName, String clientId, String secret, String username, String password) {
        this(authName);
        this.getTokenEndPoint().setClientId(clientId).setClientSecret(secret).setUsername(username).setPassword(password);
    }

    public void createDefaultAdapter() {
        Gson gson = (new GsonBuilder()).registerTypeAdapter(Date.class, this.ser).registerTypeAdapter(Date.class, this.deser).create();
        this.okClient = new OkHttpClient();
        if(!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("api-agent", ApiClient.userAgent).addHeader("api-version", ApiClient.apiVersion).build();
                return chain.proceed(newRequest);
            }
        };
        this.okClient.interceptors().add(interceptor);
        this.adapterBuilder = (new Retrofit.Builder()).baseUrl(baseUrl).client(this.okClient).addConverterFactory(GsonCustomConverterFactory.create(gson)).addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    public <S> S createService(Class<S> serviceClass) {
        return this.adapterBuilder.build().create(serviceClass);
    }

    private void setApiKey(String apiKey) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var2.hasNext()) {
                return;
            }

            apiAuthorization = (Interceptor)var2.next();
            if(apiAuthorization instanceof ApiKeyAuth) {
                ApiKeyAuth keyAuth = (ApiKeyAuth)apiAuthorization;
                keyAuth.setApiKey(apiKey);
                return;
            }
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.getTokenRequestBuilder().setRefreshToken(apiKey);
    }

    public void setClientCredentials(String clientid, String secret) {
        Iterator var3 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var3.hasNext()) {
                return;
            }

            apiAuthorization = (Interceptor)var3.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.getTokenRequestBuilder().setClientSecret(secret).setClientId(clientid).setGrantType(GrantType.CLIENT_CREDENTIALS);
    }

    private void setCredentials(String username, String password) {
        Iterator var3 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var3.hasNext()) {
                return;
            }

            apiAuthorization = (Interceptor)var3.next();
            if(apiAuthorization instanceof HttpBasicAuth) {
                HttpBasicAuth basicAuth = (HttpBasicAuth)apiAuthorization;
                basicAuth.setCredentials(username, password);
                return;
            }
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.getTokenRequestBuilder().setClientSecret(password).setClientId(username).setUsername(username).setPassword(password);
    }

    public TokenRequestBuilder getTokenEndPoint() {
        Iterator var1 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var1.hasNext()) {
                return null;
            }

            apiAuthorization = (Interceptor)var1.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        return oauth.getTokenRequestBuilder();
    }

    public AuthenticationRequestBuilder getAuthorizationEndPoint() {
        Iterator var1 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var1.hasNext()) {
                return null;
            }

            apiAuthorization = (Interceptor)var1.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        return oauth.getAuthenticationRequestBuilder();
    }

    public void setAccessToken(String accessToken) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var2.hasNext()) {
                return;
            }

            apiAuthorization = (Interceptor)var2.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.setAccessToken(accessToken);
    }

    public void setRefreshToken(String refreshToken) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var2.hasNext()) {
                return;
            }

            apiAuthorization = (Interceptor)var2.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.setRefreshToken(refreshToken);
    }

    public void configureAuthorizationFlow(String clientId, String clientSecret, String redirectURI) {
        Iterator var4 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var4.hasNext()) {
                return;
            }

            apiAuthorization = (Interceptor)var4.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.getTokenRequestBuilder().setClientId(clientId).setClientSecret(clientSecret).setRedirectURI(redirectURI);
        oauth.getAuthenticationRequestBuilder().setClientId(clientId).setRedirectURI(redirectURI);
    }

    public void registerAccessTokenListener(AccessTokenListener accessTokenListener) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if(!var2.hasNext()) {
                return;
            }

            apiAuthorization = (Interceptor)var2.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.registerAccessTokenListener(accessTokenListener);
    }

    public void addAuthorization(String authName, Interceptor authorization) {
        if(this.apiAuthorizations.containsKey(authName)) {
            throw new RuntimeException("auth name \"" + authName + "\" already in api authorizations");
        } else {
            this.apiAuthorizations.put(authName, authorization);
            this.okClient.interceptors().add(authorization);
        }
    }

    public Map<String, Interceptor> getApiAuthorizations() {
        return this.apiAuthorizations;
    }

    public void setApiAuthorizations(Map<String, Interceptor> apiAuthorizations) {
        this.apiAuthorizations = apiAuthorizations;
    }

    public Retrofit.Builder getAdapterBuilder() {
        return this.adapterBuilder;
    }

    public void setAdapterBuilder(Retrofit.Builder adapterBuilder) {
        this.adapterBuilder = adapterBuilder;
    }

    public OkHttpClient getOkClient() {
        return this.okClient;
    }

    public void addAuthsToOkClient(OkHttpClient okClient) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        while(var2.hasNext()) {
            Interceptor apiAuthorization = (Interceptor)var2.next();
            okClient.interceptors().add(apiAuthorization);
        }

    }

    public void configureFromOkclient(OkHttpClient okClient) {
        OkHttpClient clone = okClient.clone();
        this.addAuthsToOkClient(clone);
        this.adapterBuilder.client(clone);
    }
}


/* Location:              D:\projecttest\trunk\BikeMan\app\libs\android-sdk-client-1.0.jar!\vc\thinker\colours\client\ApiClient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */