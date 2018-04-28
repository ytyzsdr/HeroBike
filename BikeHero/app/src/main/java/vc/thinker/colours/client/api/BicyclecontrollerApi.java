package vc.thinker.colours.client.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.BicycleStatusVO;
import vc.thinker.colours.client.model.ListResponseOfAPISpotParkingBO;
import vc.thinker.colours.client.model.ListResponseOfBicycleBO;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfAPISpotParkingBO;
import vc.thinker.colours.client.model.SingleResponseOfBicycleBO;

public abstract interface BicyclecontrollerApi
{
  @GET("api/bicycle/find_by_location")
  public abstract Observable<ListResponseOfBicycleBO> findByLocationAndDistanceUsingGET(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("distance") Integer paramInteger, @Query("pointType") String paramString);
  
  @GET("api/bicycle/find_location")
  public abstract Observable<ListResponseOfBicycleBO> findByLocationUsingGET(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("distance") Integer paramInteger);
  
  @GET("api/bicycle/get_bicycle")
  public abstract Observable<SingleResponseOfBicycleBO> getBicycleUsingGET(@Query("sysCode") String paramString1, @Query("pointType") String paramString2);
  
  @GET("api/bicycle/lately_spot_parking")
  public abstract Observable<SingleResponseOfAPISpotParkingBO> findLatelySpotParkingUsingGET(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("pointType") String paramString);
  
  @GET("api/bicycle/spot_parking_list")
  public abstract Observable<ListResponseOfAPISpotParkingBO> findSpotParkingUsingGET(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("distance") Integer paramInteger, @Query("pointType") String paramString);
  
  @POST("api/bicycle/upload_status")
  public abstract Observable<SimpleResponse> uploadStatusUsingPOST(@Body BicycleStatusVO paramBicycleStatusVO);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\BicyclecontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */