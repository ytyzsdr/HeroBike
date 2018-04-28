package vc.thinker.colours.client.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.FeedbackVO;
import vc.thinker.colours.client.model.ListResponseOfFeedbackTypeBO;
import vc.thinker.colours.client.model.ListResponseOfUserGuideBO;
import vc.thinker.colours.client.model.PageResponseOfUserCouponBO;
import vc.thinker.colours.client.model.PageResponseOfUserDepositLogBO;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfMemberBO;
import vc.thinker.colours.client.model.SingleResponseOfPayDetailsBO;
import vc.thinker.colours.client.model.SingleResponseOfThirdPartyBoundModileBO;
import vc.thinker.colours.client.model.SingleResponseOfUserGuideBO;
import vc.thinker.colours.client.model.SingleResponseOfboolean;
import vc.thinker.colours.client.model.SingleResponseOfdouble;

public abstract interface UsercontrollerApi
{
  @POST("api/bound_invite_code")
  public abstract Observable<SimpleResponse> boundInviteCodeUsingPOST(@Query("inviteCode") String paramString);
  
  @POST("api/bound_mobile")
  public abstract Observable<SingleResponseOfThirdPartyBoundModileBO> boundModileUsingPOST(@Query("mobile") String paramString1, @Query("idcode") String paramString2);
  
  @GET("api/bound_modile_verifycode")
  public abstract Observable<SimpleResponse> boundModileVerifycodeUsingGET(@Query("phone_number") String paramString);
  
  @GET("api/depositLog")
  public abstract Observable<PageResponseOfUserDepositLogBO> depositLogUsingGET(@Query("ltTime") Long paramLong);
  
  @POST("api/feedback")
  public abstract Observable<SimpleResponse> feedbackUsingPOST(@Body FeedbackVO paramFeedbackVO);
  
  @GET("api/feedback_type_list")
  public abstract Observable<ListResponseOfFeedbackTypeBO> findFeedbackTypeListUsingGET(@Query("type") String paramString);
  
  @GET("api/get_deposit")
  public abstract Observable<SingleResponseOfdouble> getDepositUsingGET();
  
  @GET("api/guide_detail")
  public abstract Observable<SingleResponseOfUserGuideBO> userGuideDetailUsingGET(@Query("id") Long paramLong);
  
  @GET("api/guide_list")
  public abstract Observable<ListResponseOfUserGuideBO> userGuideListUsingGET(@Query("type") Integer paramInteger);
  
  @POST("api/listCoupon")
  public abstract Observable<PageResponseOfUserCouponBO> findTripListUsingPOST();
  
  @GET("api/logout")
  public abstract Observable<SingleResponseOfboolean> logoutUsingGET();
  
  @GET("api/modify_user_head")
  public abstract Observable<SimpleResponse> modifyUserHeadUsingGET(@Query("headImgUrl") String paramString);
  
  @GET("api/modify_user_nickname")
  public abstract Observable<SimpleResponse> modifyUserNicknameUsingGET(@Query("nickname") String paramString);
  
  @POST("api/paymet")
  public abstract Observable<SingleResponseOfPayDetailsBO> paymetUsingPOST(@Query("paymentMark") String paramString);
  
  @GET("api/query_invite_amount")
  public abstract Observable<SingleResponseOfdouble> queryInviteAmountUsingGET();
  
  @GET("api/real_name")
  public abstract Observable<SimpleResponse> realnameUsingGET(@Query("name") String paramString1, @Query("idcard") String paramString2);
  
  @POST("api/refundDeposit")
  public abstract Observable<SimpleResponse> refundDepositUsingPOST();
  
  @GET("api/save_user_info")
  public abstract Observable<SimpleResponse> saveUserInfoUsingGET(@Query("nickname") String paramString1, @Query("headImgUrl") String paramString2);
  
  @GET("api/userprofile")
  public abstract Observable<SingleResponseOfMemberBO> getMyProfileUsingGET();
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\UsercontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */