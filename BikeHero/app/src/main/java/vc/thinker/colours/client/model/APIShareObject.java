/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIShareObject
/*     */ {
/*     */   @SerializedName("bluetoothCode")
/*  15 */   private String bluetoothCode = null;
/*     */   
/*     */   @SerializedName("lockMacAddress")
/*  18 */   private String lockMacAddress = null;
/*     */   
/*     */   @SerializedName("macPwd")
/*  21 */   private String macPwd = null;
/*     */   
/*     */   @SerializedName("macSecretKey")
/*  24 */   private String macSecretKey = null;
/*     */   
/*     */   @SerializedName("openType")
/*  27 */   private Integer openType = null;
/*     */   
/*     */   @SerializedName("shareType")
/*  30 */   private String shareType = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  33 */   private String sysCode = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙协议版本")
/*     */   public String getBluetoothCode()
/*     */   {
/*  43 */     return this.bluetoothCode;
/*     */   }
/*     */   
/*  46 */   public void setBluetoothCode(String bluetoothCode) { this.bluetoothCode = bluetoothCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("锁的mac地址")
/*     */   public String getLockMacAddress()
/*     */   {
/*  55 */     return this.lockMacAddress;
/*     */   }
/*     */   
/*  58 */   public void setLockMacAddress(String lockMacAddress) { this.lockMacAddress = lockMacAddress; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getMacPwd()
/*     */   {
/*  66 */     return this.macPwd;
/*     */   }
/*     */   
/*  69 */   public void setMacPwd(String macPwd) { this.macPwd = macPwd; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getMacSecretKey()
/*     */   {
/*  77 */     return this.macSecretKey;
/*     */   }
/*     */   
/*  80 */   public void setMacSecretKey(String macSecretKey) { this.macSecretKey = macSecretKey; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("打开方式 1：GPRS，2：蓝牙，3：GPRS 和 蓝牙")
/*     */   public Integer getOpenType()
/*     */   {
/*  89 */     return this.openType;
/*     */   }
/*     */   
/*  92 */   public void setOpenType(Integer openType) { this.openType = openType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("共享类型，bicycle,battery")
/*     */   public String getShareType()
/*     */   {
/* 101 */     return this.shareType;
/*     */   }
/*     */   
/* 104 */   public void setShareType(String shareType) { this.shareType = shareType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSysCode()
/*     */   {
/* 112 */     return this.sysCode;
/*     */   }
/*     */   
/* 115 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 122 */     if (this == o) {
/* 123 */       return true;
/*     */     }
/* 125 */     if ((o == null) || (getClass() != o.getClass())) {
/* 126 */       return false;
/*     */     }
/* 128 */     APIShareObject aPIShareObject = (APIShareObject)o;
/* 129 */     return (Objects.equals(this.bluetoothCode, aPIShareObject.bluetoothCode)) && 
/* 130 */       (Objects.equals(this.lockMacAddress, aPIShareObject.lockMacAddress)) && 
/* 131 */       (Objects.equals(this.macPwd, aPIShareObject.macPwd)) && 
/* 132 */       (Objects.equals(this.macSecretKey, aPIShareObject.macSecretKey)) && 
/* 133 */       (Objects.equals(this.openType, aPIShareObject.openType)) && 
/* 134 */       (Objects.equals(this.shareType, aPIShareObject.shareType)) && 
/* 135 */       (Objects.equals(this.sysCode, aPIShareObject.sysCode));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 140 */     return Objects.hash(new Object[] { this.bluetoothCode, this.lockMacAddress, this.macPwd, this.macSecretKey, this.openType, this.shareType, this.sysCode });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 145 */     StringBuilder sb = new StringBuilder();
/* 146 */     sb.append("class APIShareObject {\n");
/*     */     
/* 148 */     sb.append("    bluetoothCode: ").append(toIndentedString(this.bluetoothCode)).append("\n");
/* 149 */     sb.append("    lockMacAddress: ").append(toIndentedString(this.lockMacAddress)).append("\n");
/* 150 */     sb.append("    macPwd: ").append(toIndentedString(this.macPwd)).append("\n");
/* 151 */     sb.append("    macSecretKey: ").append(toIndentedString(this.macSecretKey)).append("\n");
/* 152 */     sb.append("    openType: ").append(toIndentedString(this.openType)).append("\n");
/* 153 */     sb.append("    shareType: ").append(toIndentedString(this.shareType)).append("\n");
/* 154 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 155 */     sb.append("}");
/* 156 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 164 */     if (o == null) {
/* 165 */       return "null";
/*     */     }
/* 167 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIShareObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */