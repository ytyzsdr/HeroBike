/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class ElectrombileVO
/*     */ {
/*     */   @SerializedName("bluetoothIdentifier")
/*  15 */   private String bluetoothIdentifier = null;
/*     */   
/*     */   @SerializedName("electrombileCode")
/*  18 */   private String electrombileCode = null;
/*     */   
/*     */   @SerializedName("positionerCode")
/*  21 */   private String positionerCode = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  24 */   private String sysCode = null;
/*     */   
/*     */   @SerializedName("typeId")
/*  27 */   private Long typeId = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getBluetoothIdentifier()
/*     */   {
/*  36 */     return this.bluetoothIdentifier;
/*     */   }
/*     */   
/*  39 */   public void setBluetoothIdentifier(String bluetoothIdentifier) { this.bluetoothIdentifier = bluetoothIdentifier; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getElectrombileCode()
/*     */   {
/*  47 */     return this.electrombileCode;
/*     */   }
/*     */   
/*  50 */   public void setElectrombileCode(String electrombileCode) { this.electrombileCode = electrombileCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getPositionerCode()
/*     */   {
/*  58 */     return this.positionerCode;
/*     */   }
/*     */   
/*  61 */   public void setPositionerCode(String positionerCode) { this.positionerCode = positionerCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSysCode()
/*     */   {
/*  69 */     return this.sysCode;
/*     */   }
/*     */   
/*  72 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getTypeId()
/*     */   {
/*  80 */     return this.typeId;
/*     */   }
/*     */   
/*  83 */   public void setTypeId(Long typeId) { this.typeId = typeId; }
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
/*  96 */     ElectrombileVO electrombileVO = (ElectrombileVO)o;
/*  97 */     return (Objects.equals(this.bluetoothIdentifier, electrombileVO.bluetoothIdentifier)) && 
/*  98 */       (Objects.equals(this.electrombileCode, electrombileVO.electrombileCode)) && 
/*  99 */       (Objects.equals(this.positionerCode, electrombileVO.positionerCode)) && 
/* 100 */       (Objects.equals(this.sysCode, electrombileVO.sysCode)) && 
/* 101 */       (Objects.equals(this.typeId, electrombileVO.typeId));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 106 */     return Objects.hash(new Object[] { this.bluetoothIdentifier, this.electrombileCode, this.positionerCode, this.sysCode, this.typeId });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 111 */     StringBuilder sb = new StringBuilder();
/* 112 */     sb.append("class ElectrombileVO {\n");
/*     */     
/* 114 */     sb.append("    bluetoothIdentifier: ").append(toIndentedString(this.bluetoothIdentifier)).append("\n");
/* 115 */     sb.append("    electrombileCode: ").append(toIndentedString(this.electrombileCode)).append("\n");
/* 116 */     sb.append("    positionerCode: ").append(toIndentedString(this.positionerCode)).append("\n");
/* 117 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 118 */     sb.append("    typeId: ").append(toIndentedString(this.typeId)).append("\n");
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


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ElectrombileVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */