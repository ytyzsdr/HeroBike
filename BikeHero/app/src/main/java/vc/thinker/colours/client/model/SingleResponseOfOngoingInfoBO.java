/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class SingleResponseOfOngoingInfoBO
/*     */ {
    @SerializedName("error")
    private String error = null;
    @SerializedName("error_description")
    private String errorDescription = null;
    @SerializedName("item")
    private OngoingInfoBO item = null;
    @SerializedName("success")
    private Boolean success = null;

    public SingleResponseOfOngoingInfoBO() {
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

    public OngoingInfoBO getItem() {
        return this.item;
    }

    public void setItem(OngoingInfoBO item) {
        this.item = item;
    }


    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            SingleResponseOfOngoingInfoBO singleResponseOfOngoingInfoBO = (SingleResponseOfOngoingInfoBO)o;
            return Objects.equals(this.error, singleResponseOfOngoingInfoBO.error) && Objects.equals(this.errorDescription, singleResponseOfOngoingInfoBO.errorDescription) && Objects.equals(this.item, singleResponseOfOngoingInfoBO.item) && Objects.equals(this.success, singleResponseOfOngoingInfoBO.success);
        } else {
            return false;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.error, this.errorDescription, this.item, this.success});
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SingleResponseOfOngoingInfoBO {\n");
        sb.append("    error: ").append(this.toIndentedString(this.error)).append("\n");
        sb.append("    errorDescription: ").append(this.toIndentedString(this.errorDescription)).append("\n");
        sb.append("    item: ").append(this.toIndentedString(this.item)).append("\n");
        sb.append("    success: ").append(this.toIndentedString(this.success)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null?"null":o.toString().replace("\n", "\n    ");
    }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\SingleResponseOfOngoingInfoBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */