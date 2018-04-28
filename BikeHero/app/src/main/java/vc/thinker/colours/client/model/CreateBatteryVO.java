/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class CreateBatteryVO
/*    */ {
/*    */   @SerializedName("batteryCode")
/* 15 */   private String batteryCode = null;
/*    */   
/*    */   @SerializedName("sysCode")
/* 18 */   private String sysCode = null;
/*    */   
/*    */   @SerializedName("typeId")
/* 21 */   private Long typeId = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getBatteryCode()
/*    */   {
/* 30 */     return this.batteryCode;
/*    */   }
/*    */   
/* 33 */   public void setBatteryCode(String batteryCode) { this.batteryCode = batteryCode; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getSysCode()
/*    */   {
/* 41 */     return this.sysCode;
/*    */   }
/*    */   
/* 44 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public Long getTypeId()
/*    */   {
/* 52 */     return this.typeId;
/*    */   }
/*    */   
/* 55 */   public void setTypeId(Long typeId) { this.typeId = typeId; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 62 */     if (this == o) {
/* 63 */       return true;
/*    */     }
/* 65 */     if ((o == null) || (getClass() != o.getClass())) {
/* 66 */       return false;
/*    */     }
/* 68 */     CreateBatteryVO createBatteryVO = (CreateBatteryVO)o;
/* 69 */     return (Objects.equals(this.batteryCode, createBatteryVO.batteryCode)) && 
/* 70 */       (Objects.equals(this.sysCode, createBatteryVO.sysCode)) && 
/* 71 */       (Objects.equals(this.typeId, createBatteryVO.typeId));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 76 */     return Objects.hash(new Object[] { this.batteryCode, this.sysCode, this.typeId });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 81 */     StringBuilder sb = new StringBuilder();
/* 82 */     sb.append("class CreateBatteryVO {\n");
/*    */     
/* 84 */     sb.append("    batteryCode: ").append(toIndentedString(this.batteryCode)).append("\n");
/* 85 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 86 */     sb.append("    typeId: ").append(toIndentedString(this.typeId)).append("\n");
/* 87 */     sb.append("}");
/* 88 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 96 */     if (o == null) {
/* 97 */       return "null";
/*    */     }
/* 99 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\CreateBatteryVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */