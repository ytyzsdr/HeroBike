package vc.thinker.colours.client.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.ElectrombileStatusVO;
import vc.thinker.colours.client.model.ListResponseOfAPIElectrombileBO;
import vc.thinker.colours.client.model.ListResponseOfAPIElectrombileTypeBO;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfAPIElectrombileBO;
import vc.thinker.colours.client.model.SingleResponseOfboolean;

public abstract interface ElectrombilecontrollerApi
{
  @POST("api/electrombile/add_electrombile_saddle_lock_open")
  public abstract Observable<SingleResponseOfAPIElectrombileBO> addElectrombileSaddleLockOpenUsingPOST();
  
  @GET("api/electrombile/find_by_location")
  public abstract Observable<ListResponseOfAPIElectrombileBO> findByLocationAndDistanceUsingGET(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("distance") Integer paramInteger, @Query("pointType") String paramString);
  
  @GET("api/electrombile/find_electrombile_type")
  public abstract Observable<ListResponseOfAPIElectrombileTypeBO> findElectrombileTypeUsingGET();
  
  @GET("api/electrombile/find_location")
  public abstract Observable<ListResponseOfAPIElectrombileBO> findByLocationUsingGET(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("distance") Integer paramInteger);
  
  @GET("api/electrombile/is_open_electrombile_saddle_lock")
  public abstract Observable<SingleResponseOfboolean> isOpenElectrombileSaddleLockUsingGET();
  
  @GET("api/electrombile/profile")
  public abstract Observable<SingleResponseOfAPIElectrombileBO> profileUsingGET(@Query("sysCode") String paramString1, @Query("pointType") String paramString2);
  
  @POST("api/electrombile/upload_status")
  public abstract Observable<SimpleResponse> uploadStatusUsingPOST(@Body ElectrombileStatusVO paramElectrombileStatusVO);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\ElectrombilecontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */