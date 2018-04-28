/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class UserDepositLogBO
/*     */ {
/*     */   @SerializedName("amount")
/*  16 */   private Double amount = null;
/*     */   
/*     */   @SerializedName("createTime")
/*  19 */   private Date createTime = null;
/*     */   
/*     */   @SerializedName("type")
/*  22 */   private String type = null;
/*     */   
/*     */   @SerializedName("uid")
/*  25 */   private Long uid = null;
/*     */   
/*     */   @SerializedName("updateTime")
/*  28 */   private Date updateTime = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getAmount()
/*     */   {
/*  37 */     return this.amount;
/*     */   }
/*     */   
/*  40 */   public void setAmount(Double amount) { this.amount = amount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getCreateTime()
/*     */   {
/*  48 */     return this.createTime;
/*     */   }
/*     */   
/*  51 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getType()
/*     */   {
/*  59 */     return this.type;
/*     */   }
/*     */   
/*  62 */   public void setType(String type) { this.type = type; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getUid()
/*     */   {
/*  70 */     return this.uid;
/*     */   }
/*     */   
/*  73 */   public void setUid(Long uid) { this.uid = uid; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getUpdateTime()
/*     */   {
/*  81 */     return this.updateTime;
/*     */   }
/*     */   
/*  84 */   public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
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
/*  97 */     UserDepositLogBO userDepositLogBO = (UserDepositLogBO)o;
/*  98 */     return (Objects.equals(this.amount, userDepositLogBO.amount)) && 
/*  99 */       (Objects.equals(this.createTime, userDepositLogBO.createTime)) && 
/* 100 */       (Objects.equals(this.type, userDepositLogBO.type)) && 
/* 101 */       (Objects.equals(this.uid, userDepositLogBO.uid)) && 
/* 102 */       (Objects.equals(this.updateTime, userDepositLogBO.updateTime));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 107 */     return Objects.hash(new Object[] { this.amount, this.createTime, this.type, this.uid, this.updateTime });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 112 */     StringBuilder sb = new StringBuilder();
/* 113 */     sb.append("class UserDepositLogBO {\n");
/*     */     
/* 115 */     sb.append("    amount: ").append(toIndentedString(this.amount)).append("\n");
/* 116 */     sb.append("    createTime: ").append(toIndentedString(this.createTime)).append("\n");
/* 117 */     sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
/* 118 */     sb.append("    uid: ").append(toIndentedString(this.uid)).append("\n");
/* 119 */     sb.append("    updateTime: ").append(toIndentedString(this.updateTime)).append("\n");
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


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\UserDepositLogBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */