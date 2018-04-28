/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIBatteryTypeBO
/*     */ {
/*     */   @SerializedName("electricity")
/*  15 */   private Integer electricity = null;
/*     */   
/*     */   @SerializedName("id")
/*  18 */   private Long id = null;
/*     */   
/*     */   @SerializedName("price")
/*  21 */   private Double price = null;
/*     */   
/*     */   @SerializedName("priceMinute")
/*  24 */   private Integer priceMinute = null;
/*     */   
/*     */   @SerializedName("typeDesc")
/*  27 */   private String typeDesc = null;
/*     */   
/*     */   @SerializedName("typeName")
/*  30 */   private String typeName = null;
/*     */   
/*     */   @SerializedName("voltage")
/*  33 */   private Integer voltage = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getElectricity()
/*     */   {
/*  42 */     return this.electricity;
/*     */   }
/*     */   
/*  45 */   public void setElectricity(Integer electricity) { this.electricity = electricity; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  53 */     return this.id;
/*     */   }
/*     */   
/*  56 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getPrice()
/*     */   {
/*  64 */     return this.price;
/*     */   }
/*     */   
/*  67 */   public void setPrice(Double price) { this.price = price; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getPriceMinute()
/*     */   {
/*  75 */     return this.priceMinute;
/*     */   }
/*     */   
/*  78 */   public void setPriceMinute(Integer priceMinute) { this.priceMinute = priceMinute; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTypeDesc()
/*     */   {
/*  86 */     return this.typeDesc;
/*     */   }
/*     */   
/*  89 */   public void setTypeDesc(String typeDesc) { this.typeDesc = typeDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTypeName()
/*     */   {
/*  97 */     return this.typeName;
/*     */   }
/*     */   
/* 100 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getVoltage()
/*     */   {
/* 108 */     return this.voltage;
/*     */   }
/*     */   
/* 111 */   public void setVoltage(Integer voltage) { this.voltage = voltage; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 118 */     if (this == o) {
/* 119 */       return true;
/*     */     }
/* 121 */     if ((o == null) || (getClass() != o.getClass())) {
/* 122 */       return false;
/*     */     }
/* 124 */     APIBatteryTypeBO aPIBatteryTypeBO = (APIBatteryTypeBO)o;
/* 125 */     return (Objects.equals(this.electricity, aPIBatteryTypeBO.electricity)) && 
/* 126 */       (Objects.equals(this.id, aPIBatteryTypeBO.id)) && 
/* 127 */       (Objects.equals(this.price, aPIBatteryTypeBO.price)) && 
/* 128 */       (Objects.equals(this.priceMinute, aPIBatteryTypeBO.priceMinute)) && 
/* 129 */       (Objects.equals(this.typeDesc, aPIBatteryTypeBO.typeDesc)) && 
/* 130 */       (Objects.equals(this.typeName, aPIBatteryTypeBO.typeName)) && 
/* 131 */       (Objects.equals(this.voltage, aPIBatteryTypeBO.voltage));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 136 */     return Objects.hash(new Object[] { this.electricity, this.id, this.price, this.priceMinute, this.typeDesc, this.typeName, this.voltage });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 141 */     StringBuilder sb = new StringBuilder();
/* 142 */     sb.append("class APIBatteryTypeBO {\n");
/*     */     
/* 144 */     sb.append("    electricity: ").append(toIndentedString(this.electricity)).append("\n");
/* 145 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 146 */     sb.append("    price: ").append(toIndentedString(this.price)).append("\n");
/* 147 */     sb.append("    priceMinute: ").append(toIndentedString(this.priceMinute)).append("\n");
/* 148 */     sb.append("    typeDesc: ").append(toIndentedString(this.typeDesc)).append("\n");
/* 149 */     sb.append("    typeName: ").append(toIndentedString(this.typeName)).append("\n");
/* 150 */     sb.append("    voltage: ").append(toIndentedString(this.voltage)).append("\n");
/* 151 */     sb.append("}");
/* 152 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 160 */     if (o == null) {
/* 161 */       return "null";
/*     */     }
/* 163 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIBatteryTypeBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */