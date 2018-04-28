/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIMembershipCardBO
/*     */ {
/*     */   @SerializedName("cardAmount")
/*  15 */   private Double cardAmount = null;
/*     */   
/*     */   @SerializedName("cardDesc")
/*  18 */   private String cardDesc = null;
/*     */   
/*     */   @SerializedName("cardEffectiveTime")
/*  21 */   private Integer cardEffectiveTime = null;
/*     */   
/*     */   @SerializedName("cardName")
/*  24 */   private String cardName = null;
/*     */   
/*     */   @SerializedName("id")
/*  27 */   private Long id = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("购买金额单位元")
/*     */   public Double getCardAmount()
/*     */   {
/*  37 */     return this.cardAmount;
/*     */   }
/*     */   
/*  40 */   public void setCardAmount(Double cardAmount) { this.cardAmount = cardAmount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("会员卡说明")
/*     */   public String getCardDesc()
/*     */   {
/*  49 */     return this.cardDesc;
/*     */   }
/*     */   
/*  52 */   public void setCardDesc(String cardDesc) { this.cardDesc = cardDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("会员卡有的期限（单位天")
/*     */   public Integer getCardEffectiveTime()
/*     */   {
/*  61 */     return this.cardEffectiveTime;
/*     */   }
/*     */   
/*  64 */   public void setCardEffectiveTime(Integer cardEffectiveTime) { this.cardEffectiveTime = cardEffectiveTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getCardName()
/*     */   {
/*  72 */     return this.cardName;
/*     */   }
/*     */   
/*  75 */   public void setCardName(String cardName) { this.cardName = cardName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  83 */     return this.id;
/*     */   }
/*     */   
/*  86 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  93 */     if (this == o) {
/*  94 */       return true;
/*     */     }
/*  96 */     if ((o == null) || (getClass() != o.getClass())) {
/*  97 */       return false;
/*     */     }
/*  99 */     APIMembershipCardBO aPIMembershipCardBO = (APIMembershipCardBO)o;
/* 100 */     return (Objects.equals(this.cardAmount, aPIMembershipCardBO.cardAmount)) && 
/* 101 */       (Objects.equals(this.cardDesc, aPIMembershipCardBO.cardDesc)) && 
/* 102 */       (Objects.equals(this.cardEffectiveTime, aPIMembershipCardBO.cardEffectiveTime)) && 
/* 103 */       (Objects.equals(this.cardName, aPIMembershipCardBO.cardName)) && 
/* 104 */       (Objects.equals(this.id, aPIMembershipCardBO.id));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 109 */     return Objects.hash(new Object[] { this.cardAmount, this.cardDesc, this.cardEffectiveTime, this.cardName, this.id });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 114 */     StringBuilder sb = new StringBuilder();
/* 115 */     sb.append("class APIMembershipCardBO {\n");
/*     */     
/* 117 */     sb.append("    cardAmount: ").append(toIndentedString(this.cardAmount)).append("\n");
/* 118 */     sb.append("    cardDesc: ").append(toIndentedString(this.cardDesc)).append("\n");
/* 119 */     sb.append("    cardEffectiveTime: ").append(toIndentedString(this.cardEffectiveTime)).append("\n");
/* 120 */     sb.append("    cardName: ").append(toIndentedString(this.cardName)).append("\n");
/* 121 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 122 */     sb.append("}");
/* 123 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 131 */     if (o == null) {
/* 132 */       return "null";
/*     */     }
/* 134 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIMembershipCardBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */