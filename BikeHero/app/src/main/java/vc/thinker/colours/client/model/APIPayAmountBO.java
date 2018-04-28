/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIPayAmountBO
/*     */ {
/*     */   @SerializedName("id")
/*  15 */   private Long id = null;
/*     */   
/*     */   @SerializedName("payAmount")
/*  18 */   private Double payAmount = null;
/*     */   
/*     */   @SerializedName("remark")
/*  21 */   private String remark = null;
/*     */   
/*     */   @SerializedName("sendAmount")
/*  24 */   private Double sendAmount = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  33 */     return this.id;
/*     */   }
/*     */   
/*  36 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("用户实际支付金额（单位元）")
/*     */   public Double getPayAmount()
/*     */   {
/*  45 */     return this.payAmount;
/*     */   }
/*     */   
/*  48 */   public void setPayAmount(Double payAmount) { this.payAmount = payAmount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("优惠说明")
/*     */   public String getRemark()
/*     */   {
/*  57 */     return this.remark;
/*     */   }
/*     */   
/*  60 */   public void setRemark(String remark) { this.remark = remark; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("赠送金额（单位元）")
/*     */   public Double getSendAmount()
/*     */   {
/*  69 */     return this.sendAmount;
/*     */   }
/*     */   
/*  72 */   public void setSendAmount(Double sendAmount) { this.sendAmount = sendAmount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  79 */     if (this == o) {
/*  80 */       return true;
/*     */     }
/*  82 */     if ((o == null) || (getClass() != o.getClass())) {
/*  83 */       return false;
/*     */     }
/*  85 */     APIPayAmountBO aPIPayAmountBO = (APIPayAmountBO)o;
/*  86 */     return (Objects.equals(this.id, aPIPayAmountBO.id)) && 
/*  87 */       (Objects.equals(this.payAmount, aPIPayAmountBO.payAmount)) && 
/*  88 */       (Objects.equals(this.remark, aPIPayAmountBO.remark)) && 
/*  89 */       (Objects.equals(this.sendAmount, aPIPayAmountBO.sendAmount));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  94 */     return Objects.hash(new Object[] { this.id, this.payAmount, this.remark, this.sendAmount });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  99 */     StringBuilder sb = new StringBuilder();
/* 100 */     sb.append("class APIPayAmountBO {\n");
/*     */     
/* 102 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 103 */     sb.append("    payAmount: ").append(toIndentedString(this.payAmount)).append("\n");
/* 104 */     sb.append("    remark: ").append(toIndentedString(this.remark)).append("\n");
/* 105 */     sb.append("    sendAmount: ").append(toIndentedString(this.sendAmount)).append("\n");
/* 106 */     sb.append("}");
/* 107 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 115 */     if (o == null) {
/* 116 */       return "null";
/*     */     }
/* 118 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIPayAmountBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */