/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APITripChargeRuleBO
/*     */ {
/*     */   @SerializedName("chargeRuleDesc")
/*  15 */   private String chargeRuleDesc = null;
/*     */   
/*     */   @SerializedName("id")
/*  18 */   private Long id = null;
/*     */   
/*     */   @SerializedName("unitMinute")
/*  21 */   private Integer unitMinute = null;
/*     */   
/*     */   @SerializedName("unitPrice")
/*  24 */   private Double unitPrice = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("收费详细")
/*     */   public String getChargeRuleDesc()
/*     */   {
/*  34 */     return this.chargeRuleDesc;
/*     */   }
/*     */   
/*  37 */   public void setChargeRuleDesc(String chargeRuleDesc) { this.chargeRuleDesc = chargeRuleDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  45 */     return this.id;
/*     */   }
/*     */   
/*  48 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getUnitMinute()
/*     */   {
/*  56 */     return this.unitMinute;
/*     */   }
/*     */   
/*  59 */   public void setUnitMinute(Integer unitMinute) { this.unitMinute = unitMinute; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getUnitPrice()
/*     */   {
/*  67 */     return this.unitPrice;
/*     */   }
/*     */   
/*  70 */   public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  77 */     if (this == o) {
/*  78 */       return true;
/*     */     }
/*  80 */     if ((o == null) || (getClass() != o.getClass())) {
/*  81 */       return false;
/*     */     }
/*  83 */     APITripChargeRuleBO aPITripChargeRuleBO = (APITripChargeRuleBO)o;
/*  84 */     return (Objects.equals(this.chargeRuleDesc, aPITripChargeRuleBO.chargeRuleDesc)) && 
/*  85 */       (Objects.equals(this.id, aPITripChargeRuleBO.id)) && 
/*  86 */       (Objects.equals(this.unitMinute, aPITripChargeRuleBO.unitMinute)) && 
/*  87 */       (Objects.equals(this.unitPrice, aPITripChargeRuleBO.unitPrice));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  92 */     return Objects.hash(new Object[] { this.chargeRuleDesc, this.id, this.unitMinute, this.unitPrice });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  97 */     StringBuilder sb = new StringBuilder();
/*  98 */     sb.append("class APITripChargeRuleBO {\n");
/*     */     
/* 100 */     sb.append("    chargeRuleDesc: ").append(toIndentedString(this.chargeRuleDesc)).append("\n");
/* 101 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 102 */     sb.append("    unitMinute: ").append(toIndentedString(this.unitMinute)).append("\n");
/* 103 */     sb.append("    unitPrice: ").append(toIndentedString(this.unitPrice)).append("\n");
/* 104 */     sb.append("}");
/* 105 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 113 */     if (o == null) {
/* 114 */       return "null";
/*     */     }
/* 116 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APITripChargeRuleBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */