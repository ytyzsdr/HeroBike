package vc.thinker.colours.client.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.FeedbackVO;
import vc.thinker.colours.client.model.ListResponseOfAPIMembershipCardBO;
import vc.thinker.colours.client.model.ListResponseOfFeedbackTypeBO;
import vc.thinker.colours.client.model.ListResponseOfUserGuideBO;
import vc.thinker.colours.client.model.PageResponseOfAPIVipPayLogBO;
import vc.thinker.colours.client.model.PageResponseOfUserCouponBO;
import vc.thinker.colours.client.model.PageResponseOfUserDepositLogBO;
import vc.thinker.colours.client.model.RealnameVO;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfMemberBO;
import vc.thinker.colours.client.model.SingleResponseOfPayDetailsBO;
import vc.thinker.colours.client.model.SingleResponseOfThirdPartyBoundModileBO;
import vc.thinker.colours.client.model.SingleResponseOfUserGuideBO;
import vc.thinker.colours.client.model.SingleResponseOfboolean;
import vc.thinker.colours.client.model.SingleResponseOfdouble;
import vc.thinker.colours.client.model.SingleResponseOfstring;

public abstract interface MembercontrollerApi
{
  @GET("api/member/Membership_card_list")
  public abstract Observable<ListResponseOfAPIMembershipCardBO> findMembershipCardListUsingGET();
  
  @POST("api/member/bound_invite_code")
  public abstract Observable<SimpleResponse> boundInviteCodeUsingPOST(@Query("inviteCode") String paramString);
  
  @POST("api/member/bound_mobile")
  public abstract Observable<SingleResponseOfThirdPartyBoundModileBO> boundModileUsingPOST(@Query("mobile") String paramString1, @Query("idcode") String paramString2);
  
  @GET("api/member/bound_modile_verifycode")
  public abstract Observable<SimpleResponse> boundModileVerifycodeUsingGET(@Query("phone_number") String paramString);
  
  @GET("api/member/depositLog")
  public abstract Observable<PageResponseOfUserDepositLogBO> depositLogUsingGET(@Query("ltTime") Long paramLong);
  
  @POST("api/member/feedback")
  public abstract Observable<SimpleResponse> feedbackUsingPOST(@Body FeedbackVO paramFeedbackVO);
  
  @GET("api/member/feedback_type_list")
  public abstract Observable<ListResponseOfFeedbackTypeBO> findFeedbackTypeListUsingGET(@Query("type") String paramString);
  
  @GET("api/member/get_deposit")
  public abstract Observable<SingleResponseOfdouble> getDepositUsingGET();
  
  @GET("api/member/guide_detail")
  public abstract Observable<SingleResponseOfUserGuideBO> userGuideDetailUsingGET(@Query("id") Long paramLong);
  
  @GET("api/member/guide_list")
  public abstract Observable<ListResponseOfUserGuideBO> userGuideListUsingGET(@Query("type") Integer paramInteger);
  
  @POST("api/member/listCoupon")
  public abstract Observable<PageResponseOfUserCouponBO> findTripListUsingPOST();
  
  @GET("api/member/modify_motor_power")
  public abstract Observable<SimpleResponse> modifyMotorPowerUsingGET(@Query("motorPower") Integer paramInteger);
  
  @GET("api/member/modify_user_head")
  public abstract Observable<SimpleResponse> modifyUserHeadUsingGET(@Query("headImgUrl") String paramString);
  
  @GET("api/member/modify_user_nickname")
  public abstract Observable<SimpleResponse> modifyUserNicknameUsingGET(@Query("nickname") String paramString);
  
  @POST("api/member/paymet")
  public abstract Observable<SingleResponseOfPayDetailsBO> paymetUsingPOST(@Query("paymentMark") String paramString);
  
  @POST("api/member/paymet_vip")
  public abstract Observable<SingleResponseOfPayDetailsBO> paymetVIPUsingPOST(@Query("cardId") Long paramLong, @Query("paymentMark") String paramString);
  
  @GET("api/member/query_invite_amount")
  public abstract Observable<SingleResponseOfdouble> queryInviteAmountUsingGET();
  
  @POST("api/member/real_name")
  public abstract Observable<SingleResponseOfMemberBO> realnameUsingPOST(@Body RealnameVO paramRealnameVO);
  
  @POST("api/member/refundDeposit")
  public abstract Observable<SimpleResponse> refundDepositUsingPOST();
  
  @GET("api/member/save_user_info")
  public abstract Observable<SimpleResponse> saveUserInfoUsingGET(@Query("nickname") String paramString1, @Query("headImgUrl") String paramString2);
  
  @POST("api/member/third_party_login")
  public abstract Observable<SingleResponseOfstring> thirdPartyLoginUsingPOST(@Query("openId") String paramString1, @Query("accessToken") String paramString2, @Query("third_party_type") String paramString3);
  
  @GET("api/member/user_balance")
  public abstract Observable<SingleResponseOfdouble> getMyBalanceUsingGET();
  
  @GET("api/member/userprofile")
  public abstract Observable<SingleResponseOfMemberBO> getMyProfileUsingGET();
  
  @GET("api/member/vip_pay_list")
  public abstract Observable<PageResponseOfAPIVipPayLogBO> findVipPayListUsingGET(@Query("ltTime") Long paramLong);
  
  @GET("api/member/vip_pay_result")
  public abstract Observable<SingleResponseOfboolean> isCompleteVIPPayUsingGET(@Query("sn") String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\MembercontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */