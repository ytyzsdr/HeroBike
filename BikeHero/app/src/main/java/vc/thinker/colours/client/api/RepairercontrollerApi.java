package vc.thinker.colours.client.api;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfAPIRepairerBO;

public abstract interface RepairercontrollerApi
{
  @POST("api/repairer/beep")
  public abstract Observable<SimpleResponse> beepUsingPOST(@Query("sysCode") String paramString);
  
  @GET("api/repairer/detail")
  public abstract Observable<SingleResponseOfAPIRepairerBO> detailUsingGET();
  
  @POST("api/repairer/location")
  public abstract Observable<SimpleResponse> locationUsingPOST(@Query("sysCode") String paramString);
  
  @POST("api/repairer/unlock")
  public abstract Observable<SimpleResponse> unlockUsingPOST(@Query("sysCode") String paramString1, @Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("pointType") String paramString2, @Query("orderCode") String paramString3);
  
  @POST("api/repairer/update_head_img")
  public abstract Observable<SingleResponseOfAPIRepairerBO> updateHeadImgUsingPOST(@Query("headImg") String paramString);
  
  @POST("api/repairer/upload_point")
  public abstract Observable<SimpleResponse> uploadPointUsingPOST(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("pointType") String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\RepairercontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */