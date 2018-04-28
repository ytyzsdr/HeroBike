/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class ElectrombileStatusVO
/*    */ {
/*    */   @SerializedName("sysCode")
/* 15 */   private String sysCode = null;
/*    */   
/*    */   @SerializedName("voltage")
/* 18 */   private Float voltage = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getSysCode()
/*    */   {
/* 27 */     return this.sysCode;
/*    */   }
/*    */   
/* 30 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("电压")
/*    */   public Float getVoltage()
/*    */   {
/* 39 */     return this.voltage;
/*    */   }
/*    */   
/* 42 */   public void setVoltage(Float voltage) { this.voltage = voltage; }
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
/* 55 */     ElectrombileStatusVO electrombileStatusVO = (ElectrombileStatusVO)o;
/* 56 */     return (Objects.equals(this.sysCode, electrombileStatusVO.sysCode)) && 
/* 57 */       (Objects.equals(this.voltage, electrombileStatusVO.voltage));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 62 */     return Objects.hash(new Object[] { this.sysCode, this.voltage });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 67 */     StringBuilder sb = new StringBuilder();
/* 68 */     sb.append("class ElectrombileStatusVO {\n");
/*    */     
/* 70 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 71 */     sb.append("    voltage: ").append(toIndentedString(this.voltage)).append("\n");
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


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ElectrombileStatusVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */