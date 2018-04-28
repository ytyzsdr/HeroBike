/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class UserCouponBO
/*     */ {
/*     */   @SerializedName("amount")
/*  16 */   private Double amount = null;
/*     */   
/*     */   @SerializedName("cityId")
/*  19 */   private String cityId = null;
/*     */   
/*     */   @SerializedName("cityName")
/*  22 */   private String cityName = null;
/*     */   
/*     */   @SerializedName("expireDate")
/*  25 */   private Date expireDate = null;
/*     */   
/*     */   @SerializedName("id")
/*  28 */   private Long id = null;
/*     */   
/*     */   @SerializedName("source")
/*  31 */   private String source = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getAmount()
/*     */   {
/*  40 */     return this.amount;
/*     */   }
/*     */   
/*  43 */   public void setAmount(Double amount) { this.amount = amount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getCityId()
/*     */   {
/*  51 */     return this.cityId;
/*     */   }
/*     */   
/*  54 */   public void setCityId(String cityId) { this.cityId = cityId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getCityName()
/*     */   {
/*  62 */     return this.cityName;
/*     */   }
/*     */   
/*  65 */   public void setCityName(String cityName) { this.cityName = cityName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("过期时间")
/*     */   public Date getExpireDate()
/*     */   {
/*  74 */     return this.expireDate;
/*     */   }
/*     */   
/*  77 */   public void setExpireDate(Date expireDate) { this.expireDate = expireDate; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getId()
/*     */   {
/*  85 */     return this.id;
/*     */   }
/*     */   
/*  88 */   public void setId(Long id) { this.id = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("来源")
/*     */   public String getSource()
/*     */   {
/*  97 */     return this.source;
/*     */   }
/*     */   
/* 100 */   public void setSource(String source) { this.source = source; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 107 */     if (this == o) {
/* 108 */       return true;
/*     */     }
/* 110 */     if ((o == null) || (getClass() != o.getClass())) {
/* 111 */       return false;
/*     */     }
/* 113 */     UserCouponBO userCouponBO = (UserCouponBO)o;
/* 114 */     return (Objects.equals(this.amount, userCouponBO.amount)) && 
/* 115 */       (Objects.equals(this.cityId, userCouponBO.cityId)) && 
/* 116 */       (Objects.equals(this.cityName, userCouponBO.cityName)) && 
/* 117 */       (Objects.equals(this.expireDate, userCouponBO.expireDate)) && 
/* 118 */       (Objects.equals(this.id, userCouponBO.id)) && 
/* 119 */       (Objects.equals(this.source, userCouponBO.source));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 124 */     return Objects.hash(new Object[] { this.amount, this.cityId, this.cityName, this.expireDate, this.id, this.source });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 129 */     StringBuilder sb = new StringBuilder();
/* 130 */     sb.append("class UserCouponBO {\n");
/*     */     
/* 132 */     sb.append("    amount: ").append(toIndentedString(this.amount)).append("\n");
/* 133 */     sb.append("    cityId: ").append(toIndentedString(this.cityId)).append("\n");
/* 134 */     sb.append("    cityName: ").append(toIndentedString(this.cityName)).append("\n");
/* 135 */     sb.append("    expireDate: ").append(toIndentedString(this.expireDate)).append("\n");
/* 136 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 137 */     sb.append("    source: ").append(toIndentedString(this.source)).append("\n");
/* 138 */     sb.append("}");
/* 139 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 147 */     if (o == null) {
/* 148 */       return "null";
/*     */     }
/* 150 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\UserCouponBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */