package vc.thinker.colours.client.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.CompleteOrderVO;
import vc.thinker.colours.client.model.ListResponseOfAPIFaultTypeBO;
import vc.thinker.colours.client.model.PageResponseOfAPIOrderWorkBO;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfAPIOrderWorkBO;

public abstract interface OrderworkcontrollerApi
{
  @POST("api/order_work/begin_doing")
  public abstract Observable<SimpleResponse> beginDoingUsingPOST(@Query("orderCode") String paramString1, @Query("x") Double paramDouble1, @Query("y") Double paramDouble2, @Query("pointType") String paramString2);
  
  @POST("api/order_work/complete")
  public abstract Observable<SimpleResponse> completeUsingPOST(@Body CompleteOrderVO paramCompleteOrderVO);
  
  @GET("api/order_work/fault_type_list")
  public abstract Observable<ListResponseOfAPIFaultTypeBO> findFaultTypeListUsingGET();
  
  @GET("api/order_work/profile")
  public abstract Observable<SingleResponseOfAPIOrderWorkBO> profileUsingGET(@Query("orderCode") String paramString);
  
  @POST("api/order_work/reject")
  public abstract Observable<SimpleResponse> rejectUsingPOST(@Query("orderCode") String paramString);
  
  @GET("api/order_work/repairer_order_list")
  public abstract Observable<PageResponseOfAPIOrderWorkBO> findRepairerOrderListUsingGET(@Query("repairerStatus") String paramString1, @Query("completeStatus") String paramString2, @Query("orderFaultType") String paramString3, @Query("ltTime") Long paramLong);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\OrderworkcontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */