/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Date;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIUserMoneyLogBO
/*     */ {
/*     */   @SerializedName("createTime")
/*  16 */   private Date createTime = null;
/*     */   
/*     */   @SerializedName("isDeleted")
/*  19 */   private Boolean isDeleted = null;
/*     */   
/*     */   @SerializedName("logAmount")
/*  22 */   private Double logAmount = null;
/*     */   
/*     */   @SerializedName("logInfo")
/*  25 */   private String logInfo = null;
/*     */   
/*     */   @SerializedName("logSourceId")
/*  28 */   private Long logSourceId = null;
/*     */   
/*     */   @SerializedName("logType")
/*  31 */   private String logType = null;
/*     */   
/*     */   @SerializedName("logUserId")
/*  34 */   private Long logUserId = null;
/*     */   
/*     */   @SerializedName("outOrderId")
/*  37 */   private String outOrderId = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getCreateTime()
/*     */   {
/*  46 */     return this.createTime;
/*     */   }
/*     */   
/*  49 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getIsDeleted()
/*     */   {
/*  57 */     return this.isDeleted;
/*     */   }
/*     */   
/*  60 */   public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("日志操作金额")
/*     */   public Double getLogAmount()
/*     */   {
/*  69 */     return this.logAmount;
/*     */   }
/*     */   
/*  72 */   public void setLogAmount(Double logAmount) { this.logAmount = logAmount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("日志信息")
/*     */   public String getLogInfo()
/*     */   {
/*  81 */     return this.logInfo;
/*     */   }
/*     */   
/*  84 */   public void setLogInfo(String logInfo) { this.logInfo = logInfo; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getLogSourceId()
/*     */   {
/*  92 */     return this.logSourceId;
/*     */   }
/*     */   
/*  95 */   public void setLogSourceId(Long logSourceId) { this.logSourceId = logSourceId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("操作类型，分为充值、提现、消费、兑换金币、人工操作")
/*     */   public String getLogType()
/*     */   {
/* 104 */     return this.logType;
/*     */   }
/*     */   
/* 107 */   public void setLogType(String logType) { this.logType = logType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getLogUserId()
/*     */   {
/* 115 */     return this.logUserId;
/*     */   }
/*     */   
/* 118 */   public void setLogUserId(Long logUserId) { this.logUserId = logUserId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getOutOrderId()
/*     */   {
/* 126 */     return this.outOrderId;
/*     */   }
/*     */   
/* 129 */   public void setOutOrderId(String outOrderId) { this.outOrderId = outOrderId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 136 */     if (this == o) {
/* 137 */       return true;
/*     */     }
/* 139 */     if ((o == null) || (getClass() != o.getClass())) {
/* 140 */       return false;
/*     */     }
/* 142 */     APIUserMoneyLogBO aPIUserMoneyLogBO = (APIUserMoneyLogBO)o;
/* 143 */     return (Objects.equals(this.createTime, aPIUserMoneyLogBO.createTime)) && 
/* 144 */       (Objects.equals(this.isDeleted, aPIUserMoneyLogBO.isDeleted)) && 
/* 145 */       (Objects.equals(this.logAmount, aPIUserMoneyLogBO.logAmount)) && 
/* 146 */       (Objects.equals(this.logInfo, aPIUserMoneyLogBO.logInfo)) && 
/* 147 */       (Objects.equals(this.logSourceId, aPIUserMoneyLogBO.logSourceId)) && 
/* 148 */       (Objects.equals(this.logType, aPIUserMoneyLogBO.logType)) && 
/* 149 */       (Objects.equals(this.logUserId, aPIUserMoneyLogBO.logUserId)) && 
/* 150 */       (Objects.equals(this.outOrderId, aPIUserMoneyLogBO.outOrderId));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 155 */     return Objects.hash(new Object[] { this.createTime, this.isDeleted, this.logAmount, this.logInfo, this.logSourceId, this.logType, this.logUserId, this.outOrderId });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 160 */     StringBuilder sb = new StringBuilder();
/* 161 */     sb.append("class APIUserMoneyLogBO {\n");
/*     */     
/* 163 */     sb.append("    createTime: ").append(toIndentedString(this.createTime)).append("\n");
/* 164 */     sb.append("    isDeleted: ").append(toIndentedString(this.isDeleted)).append("\n");
/* 165 */     sb.append("    logAmount: ").append(toIndentedString(this.logAmount)).append("\n");
/* 166 */     sb.append("    logInfo: ").append(toIndentedString(this.logInfo)).append("\n");
/* 167 */     sb.append("    logSourceId: ").append(toIndentedString(this.logSourceId)).append("\n");
/* 168 */     sb.append("    logType: ").append(toIndentedString(this.logType)).append("\n");
/* 169 */     sb.append("    logUserId: ").append(toIndentedString(this.logUserId)).append("\n");
/* 170 */     sb.append("    outOrderId: ").append(toIndentedString(this.outOrderId)).append("\n");
/* 171 */     sb.append("}");
/* 172 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 180 */     if (o == null) {
/* 181 */       return "null";
/*     */     }
/* 183 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIUserMoneyLogBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */