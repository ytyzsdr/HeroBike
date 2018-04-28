/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Date;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class TripPointVO
/*    */ {
/*    */   @SerializedName("dateTime")
/* 17 */   private Date dateTime = null;
/*    */   
/*    */   @SerializedName("point")
/* 20 */   private Point point = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public Date getDateTime()
/*    */   {
/* 29 */     return this.dateTime;
/*    */   }
/*    */   
/* 32 */   public void setDateTime(Date dateTime) { this.dateTime = dateTime; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public Point getPoint()
/*    */   {
/* 40 */     return this.point;
/*    */   }
/*    */   
/* 43 */   public void setPoint(Point point) { this.point = point; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 50 */     if (this == o) {
/* 51 */       return true;
/*    */     }
/* 53 */     if ((o == null) || (getClass() != o.getClass())) {
/* 54 */       return false;
/*    */     }
/* 56 */     TripPointVO tripPointVO = (TripPointVO)o;
/* 57 */     return (Objects.equals(this.dateTime, tripPointVO.dateTime)) && 
/* 58 */       (Objects.equals(this.point, tripPointVO.point));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 63 */     return Objects.hash(new Object[] { this.dateTime, this.point });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 68 */     StringBuilder sb = new StringBuilder();
/* 69 */     sb.append("class TripPointVO {\n");
/*    */     
/* 71 */     sb.append("    dateTime: ").append(toIndentedString(this.dateTime)).append("\n");
/* 72 */     sb.append("    point: ").append(toIndentedString(this.point)).append("\n");
/* 73 */     sb.append("}");
/* 74 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 82 */     if (o == null) {
/* 83 */       return "null";
/*    */     }
/* 85 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\TripPointVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */