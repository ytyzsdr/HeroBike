package vc.thinker.colours.client.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;
import vc.thinker.colours.client.model.BicycleVO;
import vc.thinker.colours.client.model.CreateBatteryVO;
import vc.thinker.colours.client.model.ListResponseOfAPIBatteryTypeBO;
import vc.thinker.colours.client.model.ListResponseOfBicycleTypeBO;
import vc.thinker.colours.client.model.ListResponseOfLockTypeBO;
import vc.thinker.colours.client.model.SingleResponseOfAPIBatteryBO;
import vc.thinker.colours.client.model.SingleResponseOfBicycleBO;
import vc.thinker.colours.client.model.SingleResponseOfboolean;

public abstract interface ScancontrollerApi
{
  @POST("api/scan/bind")
  public abstract Observable<SingleResponseOfBicycleBO> bindUsingPOST(@Body BicycleVO paramBicycleVO);
  
  @POST("api/scan/create_battery")
  public abstract Observable<SingleResponseOfAPIBatteryBO> createBatteryUsingPOST(@Body CreateBatteryVO paramCreateBatteryVO);
  
  @GET("api/scan/get_battery_type")
  public abstract Observable<ListResponseOfAPIBatteryTypeBO> getBatteryTypeUsingGET();
  
  @GET("api/scan/get_bicycle_type")
  public abstract Observable<ListResponseOfBicycleTypeBO> getBicycleTypeUsingGET();
  
  @GET("api/scan/get_lock_type")
  public abstract Observable<ListResponseOfLockTypeBO> getLockTypeUsingGET();
  
  @GET("api/scan/is_exit_mac_address")
  public abstract Observable<SingleResponseOfboolean> isExitMacAddressUsingGET(@Query("macAddress") String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\ScancontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */