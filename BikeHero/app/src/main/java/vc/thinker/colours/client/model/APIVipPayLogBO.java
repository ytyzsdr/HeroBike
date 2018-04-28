/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIVipPayLogBO
/*     */ {
/*     */   @SerializedName("amount")
/*  16 */   private Double amount = null;
/*     */   
/*     */   @SerializedName("createTime")
/*  19 */   private Date createTime = null;
/*     */   
/*     */   @SerializedName("payTime")
/*  22 */   private Date payTime = null;
/*     */   
/*     */   @SerializedName("paymentMark")
/*  25 */   private String paymentMark = null;
/*     */   
/*     */   @SerializedName("paymentMarkName")
/*  28 */   private String paymentMarkName = null;
/*     */   
/*     */   @SerializedName("sn")
/*  31 */   private String sn = null;
/*     */   
/*     */   @SerializedName("status")
/*  34 */   private Integer status = null;
/*     */   
/*     */   @SerializedName("vipCardName")
/*  37 */   private String vipCardName = null;
/*     */   
/*     */   @SerializedName("vipDay")
/*  40 */   private Integer vipDay = null;
/*     */   
/*     */   @SerializedName("vipDiscount")
/*  43 */   private Double vipDiscount = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("金额")
/*     */   public Double getAmount()
/*     */   {
/*  53 */     return this.amount;
/*     */   }
/*     */   
/*  56 */   public void setAmount(Double amount) { this.amount = amount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getCreateTime()
/*     */   {
/*  64 */     return this.createTime;
/*     */   }
/*     */   
/*  67 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getPayTime()
/*     */   {
/*  75 */     return this.payTime;
/*     */   }
/*     */   
/*  78 */   public void setPayTime(Date payTime) { this.payTime = payTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("支付方式")
/*     */   public String getPaymentMark()
/*     */   {
/*  87 */     return this.paymentMark;
/*     */   }
/*     */   
/*  90 */   public void setPaymentMark(String paymentMark) { this.paymentMark = paymentMark; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("支付方式名称")
/*     */   public String getPaymentMarkName()
/*     */   {
/*  99 */     return this.paymentMarkName;
/*     */   }
/*     */   
/* 102 */   public void setPaymentMarkName(String paymentMarkName) { this.paymentMarkName = paymentMarkName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSn()
/*     */   {
/* 110 */     return this.sn;
/*     */   }
/*     */   
/* 113 */   public void setSn(String sn) { this.sn = sn; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("1:支付中 2:支付成功")
/*     */   public Integer getStatus()
/*     */   {
/* 122 */     return this.status;
/*     */   }
/*     */   
/* 125 */   public void setStatus(Integer status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("卡名称")
/*     */   public String getVipCardName()
/*     */   {
/* 134 */     return this.vipCardName;
/*     */   }
/*     */   
/* 137 */   public void setVipCardName(String vipCardName) { this.vipCardName = vipCardName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("vip天数")
/*     */   public Integer getVipDay()
/*     */   {
/* 146 */     return this.vipDay;
/*     */   }
/*     */   
/* 149 */   public void setVipDay(Integer vipDay) { this.vipDay = vipDay; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("vip 折扣")
/*     */   public Double getVipDiscount()
/*     */   {
/* 158 */     return this.vipDiscount;
/*     */   }
/*     */   
/* 161 */   public void setVipDiscount(Double vipDiscount) { this.vipDiscount = vipDiscount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 168 */     if (this == o) {
/* 169 */       return true;
/*     */     }
/* 171 */     if ((o == null) || (getClass() != o.getClass())) {
/* 172 */       return false;
/*     */     }
/* 174 */     APIVipPayLogBO aPIVipPayLogBO = (APIVipPayLogBO)o;
/* 175 */     return (Objects.equals(this.amount, aPIVipPayLogBO.amount)) && 
/* 176 */       (Objects.equals(this.createTime, aPIVipPayLogBO.createTime)) && 
/* 177 */       (Objects.equals(this.payTime, aPIVipPayLogBO.payTime)) && 
/* 178 */       (Objects.equals(this.paymentMark, aPIVipPayLogBO.paymentMark)) && 
/* 179 */       (Objects.equals(this.paymentMarkName, aPIVipPayLogBO.paymentMarkName)) && 
/* 180 */       (Objects.equals(this.sn, aPIVipPayLogBO.sn)) && 
/* 181 */       (Objects.equals(this.status, aPIVipPayLogBO.status)) && 
/* 182 */       (Objects.equals(this.vipCardName, aPIVipPayLogBO.vipCardName)) && 
/* 183 */       (Objects.equals(this.vipDay, aPIVipPayLogBO.vipDay)) && 
/* 184 */       (Objects.equals(this.vipDiscount, aPIVipPayLogBO.vipDiscount));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 189 */     return Objects.hash(new Object[] { this.amount, this.createTime, this.payTime, this.paymentMark, this.paymentMarkName, this.sn, this.status, this.vipCardName, this.vipDay, this.vipDiscount });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 194 */     StringBuilder sb = new StringBuilder();
/* 195 */     sb.append("class APIVipPayLogBO {\n");
/*     */     
/* 197 */     sb.append("    amount: ").append(toIndentedString(this.amount)).append("\n");
/* 198 */     sb.append("    createTime: ").append(toIndentedString(this.createTime)).append("\n");
/* 199 */     sb.append("    payTime: ").append(toIndentedString(this.payTime)).append("\n");
/* 200 */     sb.append("    paymentMark: ").append(toIndentedString(this.paymentMark)).append("\n");
/* 201 */     sb.append("    paymentMarkName: ").append(toIndentedString(this.paymentMarkName)).append("\n");
/* 202 */     sb.append("    sn: ").append(toIndentedString(this.sn)).append("\n");
/* 203 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 204 */     sb.append("    vipCardName: ").append(toIndentedString(this.vipCardName)).append("\n");
/* 205 */     sb.append("    vipDay: ").append(toIndentedString(this.vipDay)).append("\n");
/* 206 */     sb.append("    vipDiscount: ").append(toIndentedString(this.vipDiscount)).append("\n");
/* 207 */     sb.append("}");
/* 208 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 216 */     if (o == null) {
/* 217 */       return "null";
/*     */     }
/* 219 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIVipPayLogBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */