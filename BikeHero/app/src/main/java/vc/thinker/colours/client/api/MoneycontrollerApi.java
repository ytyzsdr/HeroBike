package vc.thinker.colours.client.api;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.ListResponseOfAPIPayAmountBO;
import vc.thinker.colours.client.model.PageResponseOfAPIUserMoneyLogBO;
import vc.thinker.colours.client.model.SingleResponseOfPayDetailsBO;
import vc.thinker.colours.client.model.SingleResponseOfboolean;
import vc.thinker.colours.client.model.SingleResponseOfdouble;

public abstract interface MoneycontrollerApi
{
  @GET("api/money/balance_pay_result")
  public abstract Observable<SingleResponseOfboolean> isCompleteBalancePayUsingGET(@Query("sn") String paramString);
  
  @GET("api/money/money_log_list")
  public abstract Observable<PageResponseOfAPIUserMoneyLogBO> findMoneyLogListUsingGET(@Query("ltTime") Long paramLong);
  
  @GET("api/money/pay_amount_list")
  public abstract Observable<ListResponseOfAPIPayAmountBO> findPayAmountListUsingGET();
  
  @POST("api/money/paymet_balance")
  public abstract Observable<SingleResponseOfPayDetailsBO> paymetBalanceUsingPOST(@Query("payAmountId") Long paramLong, @Query("paymentMark") String paramString);
  
  @GET("api/money/user_balance")
  public abstract Observable<SingleResponseOfdouble> getMyBalanceUsingGET();
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\MoneycontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */