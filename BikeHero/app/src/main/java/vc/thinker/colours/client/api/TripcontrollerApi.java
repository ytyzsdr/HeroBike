package vc.thinker.colours.client.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.ListResponseOfUserCouponBO;
import vc.thinker.colours.client.model.PageResponseOfTripBO;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfAPIShareObject;
import vc.thinker.colours.client.model.SingleResponseOfBicycleBO;
import vc.thinker.colours.client.model.SingleResponseOfOngoingInfoBO;
import vc.thinker.colours.client.model.SingleResponseOfPayDetailsBO;
import vc.thinker.colours.client.model.SingleResponseOfTripBO;
import vc.thinker.colours.client.model.TripPointBo;

public abstract interface TripcontrollerApi
{
  @POST("api/trip/beep")
  public abstract Observable<SimpleResponse> beepUsingPOST();
  
  @POST("api/trip/cancel_reserve")
  public abstract Observable<SimpleResponse> cancelReserveUsingPOST();
  
  @POST("api/trip/confirm_unlock")
  public abstract Observable<SimpleResponse> confirmUnlockUsingPOST(@Query("sysCode") String paramString, @Query("showPwd") Boolean paramBoolean);
  
  @POST("api/trip/coupon_list")
  public abstract Observable<ListResponseOfUserCouponBO> findCouponListUsingPOST();
  
  @POST("api/trip/end_trip")
  public abstract Observable<SingleResponseOfTripBO> endTripUsingPOST(@Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("endType") String paramString1, @Query("pointType") String paramString2);
  
  @GET("api/trip/have_in_hand")
  public abstract Observable<SingleResponseOfTripBO> findHaveInHandUsingGET();

  @POST("api/trip/list")
  public abstract Observable<PageResponseOfTripBO> findTripListUsingPOST(@Query("ltTime") Long paramLong);
  
  @POST("api/trip/location")
  public abstract Observable<SimpleResponse> locationUsingPOST();
  
  @GET("api/trip/not_pay_trip")
  public abstract Observable<SingleResponseOfTripBO> findNotPayTripUsingGET();

  @GET("api/trip/ongoing_info")
  Observable<SingleResponseOfOngoingInfoBO> findOngoingInfoUsingGET(@Query("pointType") String var1);
  
  @POST("api/trip/paymet")
  public abstract Observable<SingleResponseOfPayDetailsBO> paymetUsingPOST(@Query("tid") Long paramLong1, @Query("cid") Long paramLong2, @Query("paymentMark") String paramString);

  @POST("api/trip/paymet")
  Observable<SingleResponseOfPayDetailsBO> paymetUsingPOSTNo(@Query("tid") Long var1, @Query("paymentMark") String var3);

  @GET("api/trip/profile")
  public abstract Observable<SingleResponseOfTripBO> profileUsingGET(@Query("tid") Long paramLong, @Query("pointType") String paramString);
  
  @POST("api/trip/reserve")
  public abstract Observable<SimpleResponse> reserveUsingPOST(@Query("sysCode") String paramString);
  
  @POST("api/trip/trip_unlock")
  public abstract Observable<SingleResponseOfBicycleBO> tripUnlockUsingPOST();
  
  @POST("api/trip/unlock")
  public abstract Observable<SingleResponseOfAPIShareObject> unlockUsingPOST(@Query("sysCode") String paramString1, @Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("pointType") String paramString2);
  
  @POST("api/trip/upload_track_loc")
  public abstract Observable<SimpleResponse> uploadTrackLocUsingPOST(@Body TripPointBo paramTripPointBo);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\TripcontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */