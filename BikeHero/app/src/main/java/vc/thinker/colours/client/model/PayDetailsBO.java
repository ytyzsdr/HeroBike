/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class PayDetailsBO
/*     */ {
/*     */   @SerializedName("alipaPpaySignature")
/*  16 */   private String alipaPpaySignature = null;
/*     */   
/*     */   @SerializedName("sn")
/*  19 */   private String sn = null;
/*     */   
/*     */   @SerializedName("weiXinPaymet")
/*  22 */   private WeiXinPaymetBO weiXinPaymet = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getAlipaPpaySignature()
/*     */   {
/*  31 */     return this.alipaPpaySignature;
/*     */   }
/*     */   
/*  34 */   public void setAlipaPpaySignature(String alipaPpaySignature) { this.alipaPpaySignature = alipaPpaySignature; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("支付的流水号")
/*     */   public String getSn()
/*     */   {
/*  43 */     return this.sn;
/*     */   }
/*     */   
/*  46 */   public void setSn(String sn) { this.sn = sn; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public WeiXinPaymetBO getWeiXinPaymet()
/*     */   {
/*  54 */     return this.weiXinPaymet;
/*     */   }
/*     */   
/*  57 */   public void setWeiXinPaymet(WeiXinPaymetBO weiXinPaymet) { this.weiXinPaymet = weiXinPaymet; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  64 */     if (this == o) {
/*  65 */       return true;
/*     */     }
/*  67 */     if ((o == null) || (getClass() != o.getClass())) {
/*  68 */       return false;
/*     */     }
/*  70 */     PayDetailsBO payDetailsBO = (PayDetailsBO)o;
/*  71 */     return (Objects.equals(this.alipaPpaySignature, payDetailsBO.alipaPpaySignature)) && 
/*  72 */       (Objects.equals(this.sn, payDetailsBO.sn)) && 
/*  73 */       (Objects.equals(this.weiXinPaymet, payDetailsBO.weiXinPaymet));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  78 */     return Objects.hash(new Object[] { this.alipaPpaySignature, this.sn, this.weiXinPaymet });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  83 */     StringBuilder sb = new StringBuilder();
/*  84 */     sb.append("class PayDetailsBO {\n");
/*     */     
/*  86 */     sb.append("    alipaPpaySignature: ").append(toIndentedString(this.alipaPpaySignature)).append("\n");
/*  87 */     sb.append("    sn: ").append(toIndentedString(this.sn)).append("\n");
/*  88 */     sb.append("    weiXinPaymet: ").append(toIndentedString(this.weiXinPaymet)).append("\n");
/*  89 */     sb.append("}");
/*  90 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/*  98 */     if (o == null) {
/*  99 */       return "null";
/*     */     }
/* 101 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\PayDetailsBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */