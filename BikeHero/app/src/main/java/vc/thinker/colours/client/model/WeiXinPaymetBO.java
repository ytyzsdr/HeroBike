/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class WeiXinPaymetBO
/*     */ {
/*     */   @SerializedName("appId")
/*  15 */   private String appId = null;
/*     */   
/*     */   @SerializedName("nonceStr")
/*  18 */   private String nonceStr = null;
/*     */   
/*     */   @SerializedName("package1")
/*  21 */   private String package1 = null;
/*     */   
/*     */   @SerializedName("partnerId")
/*  24 */   private String partnerId = null;
/*     */   
/*     */   @SerializedName("prepayId")
/*  27 */   private String prepayId = null;
/*     */   
/*     */   @SerializedName("sign")
/*  30 */   private String sign = null;
/*     */   
/*     */   @SerializedName("timeStamp")
/*  33 */   private String timeStamp = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getAppId()
/*     */   {
/*  42 */     return this.appId;
/*     */   }
/*     */   
/*  45 */   public void setAppId(String appId) { this.appId = appId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getNonceStr()
/*     */   {
/*  53 */     return this.nonceStr;
/*     */   }
/*     */   
/*  56 */   public void setNonceStr(String nonceStr) { this.nonceStr = nonceStr; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getPackage1()
/*     */   {
/*  64 */     return this.package1;
/*     */   }
/*     */   
/*  67 */   public void setPackage1(String package1) { this.package1 = package1; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getPartnerId()
/*     */   {
/*  75 */     return this.partnerId;
/*     */   }
/*     */   
/*  78 */   public void setPartnerId(String partnerId) { this.partnerId = partnerId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getPrepayId()
/*     */   {
/*  86 */     return this.prepayId;
/*     */   }
/*     */   
/*  89 */   public void setPrepayId(String prepayId) { this.prepayId = prepayId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSign()
/*     */   {
/*  97 */     return this.sign;
/*     */   }
/*     */   
/* 100 */   public void setSign(String sign) { this.sign = sign; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getTimeStamp()
/*     */   {
/* 108 */     return this.timeStamp;
/*     */   }
/*     */   
/* 111 */   public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }
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
/* 124 */     WeiXinPaymetBO weiXinPaymetBO = (WeiXinPaymetBO)o;
/* 125 */     return (Objects.equals(this.appId, weiXinPaymetBO.appId)) && 
/* 126 */       (Objects.equals(this.nonceStr, weiXinPaymetBO.nonceStr)) && 
/* 127 */       (Objects.equals(this.package1, weiXinPaymetBO.package1)) && 
/* 128 */       (Objects.equals(this.partnerId, weiXinPaymetBO.partnerId)) && 
/* 129 */       (Objects.equals(this.prepayId, weiXinPaymetBO.prepayId)) && 
/* 130 */       (Objects.equals(this.sign, weiXinPaymetBO.sign)) && 
/* 131 */       (Objects.equals(this.timeStamp, weiXinPaymetBO.timeStamp));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 136 */     return Objects.hash(new Object[] { this.appId, this.nonceStr, this.package1, this.partnerId, this.prepayId, this.sign, this.timeStamp });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 141 */     StringBuilder sb = new StringBuilder();
/* 142 */     sb.append("class WeiXinPaymetBO {\n");
/*     */     
/* 144 */     sb.append("    appId: ").append(toIndentedString(this.appId)).append("\n");
/* 145 */     sb.append("    nonceStr: ").append(toIndentedString(this.nonceStr)).append("\n");
/* 146 */     sb.append("    package1: ").append(toIndentedString(this.package1)).append("\n");
/* 147 */     sb.append("    partnerId: ").append(toIndentedString(this.partnerId)).append("\n");
/* 148 */     sb.append("    prepayId: ").append(toIndentedString(this.prepayId)).append("\n");
/* 149 */     sb.append("    sign: ").append(toIndentedString(this.sign)).append("\n");
/* 150 */     sb.append("    timeStamp: ").append(toIndentedString(this.timeStamp)).append("\n");
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


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\WeiXinPaymetBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */