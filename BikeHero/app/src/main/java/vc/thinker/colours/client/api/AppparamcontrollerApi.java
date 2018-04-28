package vc.thinker.colours.client.api;

import retrofit.http.GET;
import rx.Observable;
import vc.thinker.colours.client.model.SingleResponseOfImgBo;
import vc.thinker.colours.client.model.SingleResponseOfInitImgBO;
import vc.thinker.colours.client.model.SingleResponseOfShareSetBO;
import vc.thinker.colours.client.model.SingleResponseOfSysSettingBO;

public abstract interface AppparamcontrollerApi
{
  @GET("api/param/query_ad_img")
  public abstract Observable<SingleResponseOfInitImgBO> queryAdImgUsingGET();
  
  @GET("api/param/query_app_img")
  public abstract Observable<SingleResponseOfImgBo> queryInitImgUsingGET();
  
  @GET("api/param/query_app_share")
  public abstract Observable<SingleResponseOfShareSetBO> queryAppShareUsingGET();
  
  @GET("api/param/query_sys_set")
  public abstract Observable<SingleResponseOfSysSettingBO> querySysSetUsingGET();
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\AppparamcontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */