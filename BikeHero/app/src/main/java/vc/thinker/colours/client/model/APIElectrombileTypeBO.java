/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIElectrombileTypeBO
/*     */ {
/*     */   @SerializedName("chargeRuleDesc")
/*  15 */   private String chargeRuleDesc = null;
/*     */   
/*     */   @SerializedName("electricity")
/*  18 */   private Integer electricity = null;
/*     */   
/*     */   @SerializedName("id")
/*  21 */   private Long id = null;
/*     */   
/*     */   @SerializedName("typeName")
/*  24 */   private String typeName = null;
/*     */   
/*     */   @SerializedName("voltage")
/*  27 */   private Integer voltage = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("收费规则描述")
/*     */   public String getChargeRuleDesc()
/*     */   {
/*  37 */     return this.chargeRuleDesc;
/*     */   }
/*     */   
/*  40 */   public void setChargeRuleDesc(String chargeRuleDesc) { this.chargeRuleDesc = chargeRuleDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getElectricity()
/*     */   {
/*  48 */     return this.electricity;
/*     */   }
/*     */   
/*  51 */   public void setElectricity(Integer electricity) { this.electricity = electricity; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  59 */     return this.id;
/*     */   }
/*     */   
/*  62 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTypeName()
/*     */   {
/*  70 */     return this.typeName;
/*     */   }
/*     */   
/*  73 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getVoltage()
/*     */   {
/*  81 */     return this.voltage;
/*     */   }
/*     */   
/*  84 */   public void setVoltage(Integer voltage) { this.voltage = voltage; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  91 */     if (this == o) {
/*  92 */       return true;
/*     */     }
/*  94 */     if ((o == null) || (getClass() != o.getClass())) {
/*  95 */       return false;
/*     */     }
/*  97 */     APIElectrombileTypeBO aPIElectrombileTypeBO = (APIElectrombileTypeBO)o;
/*  98 */     return (Objects.equals(this.chargeRuleDesc, aPIElectrombileTypeBO.chargeRuleDesc)) && 
/*  99 */       (Objects.equals(this.electricity, aPIElectrombileTypeBO.electricity)) && 
/* 100 */       (Objects.equals(this.id, aPIElectrombileTypeBO.id)) && 
/* 101 */       (Objects.equals(this.typeName, aPIElectrombileTypeBO.typeName)) && 
/* 102 */       (Objects.equals(this.voltage, aPIElectrombileTypeBO.voltage));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 107 */     return Objects.hash(new Object[] { this.chargeRuleDesc, this.electricity, this.id, this.typeName, this.voltage });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 112 */     StringBuilder sb = new StringBuilder();
/* 113 */     sb.append("class APIElectrombileTypeBO {\n");
/*     */     
/* 115 */     sb.append("    chargeRuleDesc: ").append(toIndentedString(this.chargeRuleDesc)).append("\n");
/* 116 */     sb.append("    electricity: ").append(toIndentedString(this.electricity)).append("\n");
/* 117 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 118 */     sb.append("    typeName: ").append(toIndentedString(this.typeName)).append("\n");
/* 119 */     sb.append("    voltage: ").append(toIndentedString(this.voltage)).append("\n");
/* 120 */     sb.append("}");
/* 121 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 129 */     if (o == null) {
/* 130 */       return "null";
/*     */     }
/* 132 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIElectrombileTypeBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */