/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class SingleResponseOfTripBO
/*     */ {
    @SerializedName("error")
    private String error = null;
    @SerializedName("error_description")
    private String errorDescription = null;
    @SerializedName("item")
    private TripBO item = null;
    @SerializedName("success")
    private Boolean success = null;

    public SingleResponseOfTripBO() {
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public TripBO getItem() {
        return this.item;
    }

    public void setItem(TripBO item) {
        this.item = item;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\SingleResponseOfTripBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */