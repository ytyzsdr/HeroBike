/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class Point
/*    */ {
/*    */   @SerializedName("x")
/* 15 */   private Double x = null;
/*    */   
/*    */   @SerializedName("y")
/* 18 */   private Double y = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public Double getX()
/*    */   {
/* 27 */     return this.x;
/*    */   }
/*    */   
/* 30 */   public void setX(Double x) { this.x = x; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public Double getY()
/*    */   {
/* 38 */     return this.y;
/*    */   }
/*    */   
/* 41 */   public void setY(Double y) { this.y = y; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 48 */     if (this == o) {
/* 49 */       return true;
/*    */     }
/* 51 */     if ((o == null) || (getClass() != o.getClass())) {
/* 52 */       return false;
/*    */     }
/* 54 */     Point point = (Point)o;
/* 55 */     return (Objects.equals(this.x, point.x)) && 
/* 56 */       (Objects.equals(this.y, point.y));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 61 */     return Objects.hash(new Object[] { this.x, this.y });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 66 */     StringBuilder sb = new StringBuilder();
/* 67 */     sb.append("class Point {\n");
/*    */     
/* 69 */     sb.append("    x: ").append(toIndentedString(this.x)).append("\n");
/* 70 */     sb.append("    y: ").append(toIndentedString(this.y)).append("\n");
/* 71 */     sb.append("}");
/* 72 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 80 */     if (o == null) {
/* 81 */       return "null";
/*    */     }
/* 83 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\Point.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */