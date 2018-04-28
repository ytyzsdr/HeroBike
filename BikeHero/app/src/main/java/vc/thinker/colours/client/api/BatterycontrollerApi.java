package vc.thinker.colours.client.api;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.ListResponseOfAPIBatteryBO;

public abstract interface BatterycontrollerApi
{
  @GET("api/battery/find_by_location")
  public abstract Observable<ListResponseOfAPIBatteryBO> findByLocationAndDistanceUsingGET(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("distance") Integer paramInteger, @Query("pointType") String paramString);
  
  @GET("api/battery/find_location")
  public abstract Observable<ListResponseOfAPIBatteryBO> findByLocationUsingGET(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("distance") Integer paramInteger);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\BatterycontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */