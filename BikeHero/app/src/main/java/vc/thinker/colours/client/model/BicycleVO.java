/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class BicycleVO
/*     */ {
/*     */   @SerializedName("bicycleTypeId")
/*  15 */   private Long bicycleTypeId = null;
/*     */   
/*     */   @SerializedName("lcode")
/*  18 */   private String lcode = null;
/*     */   
/*     */   @SerializedName("lockTypeId")
/*  21 */   private Long lockTypeId = null;
/*     */   
/*     */   @SerializedName("macBluetooth")
/*  24 */   private String macBluetooth = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  27 */   private String sysCode = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getBicycleTypeId()
/*     */   {
/*  36 */     return this.bicycleTypeId;
/*     */   }
/*     */   
/*  39 */   public void setBicycleTypeId(Long bicycleTypeId) { this.bicycleTypeId = bicycleTypeId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getLcode()
/*     */   {
/*  47 */     return this.lcode;
/*     */   }
/*     */   
/*  50 */   public void setLcode(String lcode) { this.lcode = lcode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getLockTypeId()
/*     */   {
/*  58 */     return this.lockTypeId;
/*     */   }
/*     */   
/*  61 */   public void setLockTypeId(Long lockTypeId) { this.lockTypeId = lockTypeId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getMacBluetooth()
/*     */   {
/*  69 */     return this.macBluetooth;
/*     */   }
/*     */   
/*  72 */   public void setMacBluetooth(String macBluetooth) { this.macBluetooth = macBluetooth; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSysCode()
/*     */   {
/*  80 */     return this.sysCode;
/*     */   }
/*     */   
/*  83 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  90 */     if (this == o) {
/*  91 */       return true;
/*     */     }
/*  93 */     if ((o == null) || (getClass() != o.getClass())) {
/*  94 */       return false;
/*     */     }
/*  96 */     BicycleVO bicycleVO = (BicycleVO)o;
/*  97 */     return (Objects.equals(this.bicycleTypeId, bicycleVO.bicycleTypeId)) && 
/*  98 */       (Objects.equals(this.lcode, bicycleVO.lcode)) && 
/*  99 */       (Objects.equals(this.lockTypeId, bicycleVO.lockTypeId)) && 
/* 100 */       (Objects.equals(this.macBluetooth, bicycleVO.macBluetooth)) && 
/* 101 */       (Objects.equals(this.sysCode, bicycleVO.sysCode));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 106 */     return Objects.hash(new Object[] { this.bicycleTypeId, this.lcode, this.lockTypeId, this.macBluetooth, this.sysCode });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 111 */     StringBuilder sb = new StringBuilder();
/* 112 */     sb.append("class BicycleVO {\n");
/*     */     
/* 114 */     sb.append("    bicycleTypeId: ").append(toIndentedString(this.bicycleTypeId)).append("\n");
/* 115 */     sb.append("    lcode: ").append(toIndentedString(this.lcode)).append("\n");
/* 116 */     sb.append("    lockTypeId: ").append(toIndentedString(this.lockTypeId)).append("\n");
/* 117 */     sb.append("    macBluetooth: ").append(toIndentedString(this.macBluetooth)).append("\n");
/* 118 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 119 */     sb.append("}");
/* 120 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 128 */     if (o == null) {
/* 129 */       return "null";
/*     */     }
/* 131 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\BicycleVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */