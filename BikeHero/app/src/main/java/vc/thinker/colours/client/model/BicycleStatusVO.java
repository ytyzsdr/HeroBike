/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class BicycleStatusVO
/*    */ {
/*    */   @SerializedName("electricity")
/* 15 */   private Integer electricity = null;
/*    */   
/*    */   @SerializedName("sysCode")
/* 18 */   private String sysCode = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("电量")
/*    */   public Integer getElectricity()
/*    */   {
/* 28 */     return this.electricity;
/*    */   }
/*    */   
/* 31 */   public void setElectricity(Integer electricity) { this.electricity = electricity; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getSysCode()
/*    */   {
/* 39 */     return this.sysCode;
/*    */   }
/*    */   
/* 42 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 49 */     if (this == o) {
/* 50 */       return true;
/*    */     }
/* 52 */     if ((o == null) || (getClass() != o.getClass())) {
/* 53 */       return false;
/*    */     }
/* 55 */     BicycleStatusVO bicycleStatusVO = (BicycleStatusVO)o;
/* 56 */     return (Objects.equals(this.electricity, bicycleStatusVO.electricity)) && 
/* 57 */       (Objects.equals(this.sysCode, bicycleStatusVO.sysCode));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 62 */     return Objects.hash(new Object[] { this.electricity, this.sysCode });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 67 */     StringBuilder sb = new StringBuilder();
/* 68 */     sb.append("class BicycleStatusVO {\n");
/*    */     
/* 70 */     sb.append("    electricity: ").append(toIndentedString(this.electricity)).append("\n");
/* 71 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 72 */     sb.append("}");
/* 73 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 81 */     if (o == null) {
/* 82 */       return "null";
/*    */     }
/* 84 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\BicycleStatusVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */