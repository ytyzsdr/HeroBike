/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class LockTypeBO
/*     */ {
/*     */   @SerializedName("bluetoothCode")
/*  15 */   private String bluetoothCode = null;
/*     */   
/*     */   @SerializedName("codeName")
/*  18 */   private String codeName = null;
/*     */   
/*     */   @SerializedName("defaultPwd")
/*  21 */   private String defaultPwd = null;
/*     */   
/*     */   @SerializedName("defaultSecretKey")
/*  24 */   private String defaultSecretKey = null;
/*     */   
/*     */   @SerializedName("id")
/*  27 */   private Long id = null;
/*     */   
/*     */   @SerializedName("openType")
/*  30 */   private Integer openType = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙协议版本")
/*     */   public String getBluetoothCode()
/*     */   {
/*  40 */     return this.bluetoothCode;
/*     */   }
/*     */   
/*  43 */   public void setBluetoothCode(String bluetoothCode) { this.bluetoothCode = bluetoothCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getCodeName()
/*     */   {
/*  51 */     return this.codeName;
/*     */   }
/*     */   
/*  54 */   public void setCodeName(String codeName) { this.codeName = codeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙默认密码")
/*     */   public String getDefaultPwd()
/*     */   {
/*  63 */     return this.defaultPwd;
/*     */   }
/*     */   
/*  66 */   public void setDefaultPwd(String defaultPwd) { this.defaultPwd = defaultPwd; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙默认密钥")
/*     */   public String getDefaultSecretKey()
/*     */   {
/*  75 */     return this.defaultSecretKey;
/*     */   }
/*     */   
/*  78 */   public void setDefaultSecretKey(String defaultSecretKey) { this.defaultSecretKey = defaultSecretKey; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  86 */     return this.id;
/*     */   }
/*     */   
/*  89 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("打开方式 1：GPRS，2：蓝牙，3：GPRS 和 蓝牙")
/*     */   public Integer getOpenType()
/*     */   {
/*  98 */     return this.openType;
/*     */   }
/*     */   
/* 101 */   public void setOpenType(Integer openType) { this.openType = openType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 108 */     if (this == o) {
/* 109 */       return true;
/*     */     }
/* 111 */     if ((o == null) || (getClass() != o.getClass())) {
/* 112 */       return false;
/*     */     }
/* 114 */     LockTypeBO lockTypeBO = (LockTypeBO)o;
/* 115 */     return (Objects.equals(this.bluetoothCode, lockTypeBO.bluetoothCode)) && 
/* 116 */       (Objects.equals(this.codeName, lockTypeBO.codeName)) && 
/* 117 */       (Objects.equals(this.defaultPwd, lockTypeBO.defaultPwd)) && 
/* 118 */       (Objects.equals(this.defaultSecretKey, lockTypeBO.defaultSecretKey)) && 
/* 119 */       (Objects.equals(this.id, lockTypeBO.id)) && 
/* 120 */       (Objects.equals(this.openType, lockTypeBO.openType));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 125 */     return Objects.hash(new Object[] { this.bluetoothCode, this.codeName, this.defaultPwd, this.defaultSecretKey, this.id, this.openType });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 130 */     StringBuilder sb = new StringBuilder();
/* 131 */     sb.append("class LockTypeBO {\n");
/*     */     
/* 133 */     sb.append("    bluetoothCode: ").append(toIndentedString(this.bluetoothCode)).append("\n");
/* 134 */     sb.append("    codeName: ").append(toIndentedString(this.codeName)).append("\n");
/* 135 */     sb.append("    defaultPwd: ").append(toIndentedString(this.defaultPwd)).append("\n");
/* 136 */     sb.append("    defaultSecretKey: ").append(toIndentedString(this.defaultSecretKey)).append("\n");
/* 137 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 138 */     sb.append("    openType: ").append(toIndentedString(this.openType)).append("\n");
/* 139 */     sb.append("}");
/* 140 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 148 */     if (o == null) {
/* 149 */       return "null";
/*     */     }
/* 151 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\LockTypeBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */