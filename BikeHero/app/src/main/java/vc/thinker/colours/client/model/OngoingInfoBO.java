/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 

/*    */ public class OngoingInfoBO
/*    */ {
/*    */   @SerializedName("reserve")
private ReserveBO reserve = null;
    @SerializedName("trip")
    private TripBO trip = null;

    public OngoingInfoBO() {
    }


    public ReserveBO getReserve() {
        return this.reserve;
    }

    public void setReserve(ReserveBO reserve) {
        this.reserve = reserve;
    }


    public TripBO getTrip() {
        return this.trip;
    }

    public void setTrip(TripBO trip) {
        this.trip = trip;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            OngoingInfoBO ongoingInfoBO = (OngoingInfoBO)o;
            return Objects.equals(this.reserve, ongoingInfoBO.reserve) && Objects.equals(this.trip, ongoingInfoBO.trip);
        } else {
            return false;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.reserve, this.trip});
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OngoingInfoBO {\n");
        sb.append("    reserve: ").append(this.toIndentedString(this.reserve)).append("\n");
        sb.append("    trip: ").append(this.toIndentedString(this.trip)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null?"null":o.toString().replace("\n", "\n    ");
    }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\OngoingInfoBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */