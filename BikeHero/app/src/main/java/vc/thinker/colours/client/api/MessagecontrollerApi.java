package vc.thinker.colours.client.api;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.PageResponseOfAPIMessageBO;
import vc.thinker.colours.client.model.SingleResponseOfAPIMessageBO;

public abstract interface MessagecontrollerApi
{
  @GET("api/message/home_message")
  public abstract Observable<SingleResponseOfAPIMessageBO> findHomeMessageUsingGET(@Query("timestamp") Long paramLong);
  
  @GET("api/message/sys_list")
  public abstract Observable<PageResponseOfAPIMessageBO> sysListUsingGET(@Query("userType") String paramString, @Query("ltTime") Long paramLong);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\MessagecontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */