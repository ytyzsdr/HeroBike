package vc.thinker.colours.client.api;

import com.squareup.okhttp.RequestBody;
import java.io.File;
import java.util.List;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import rx.Observable;
import vc.thinker.colours.client.model.ListResponseOfRequestResult;
import vc.thinker.colours.client.model.SingleResponseOfRequestResult;

public abstract interface FileuploadcontrollerApi
{
  @Multipart
  @POST("api/fs/")
  public abstract Observable<String> uploadFileUsingPOST(@Part("fileType") String paramString, @Part("file\"; filename=\"file\"") RequestBody paramRequestBody);
  
  @POST("api/fs/image")
  public abstract Observable<SingleResponseOfRequestResult> uploadWebFileUsingPOST();
  
  @Multipart
  @POST("api/fs/upload")
  public abstract Observable<SingleResponseOfRequestResult> uploadFileV2UsingPOST(@Part("fileType") String paramString, @Part("file\"; filename=\"file\"") RequestBody paramRequestBody);
  
  @Multipart
  @POST("api/fs/upload_batch")
  public abstract Observable<ListResponseOfRequestResult> uploadFileBatchV2UsingPOST(@Part("fileType") String paramString, @Part("files") List<File> paramList);
}


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\api\FileuploadcontrollerApi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */