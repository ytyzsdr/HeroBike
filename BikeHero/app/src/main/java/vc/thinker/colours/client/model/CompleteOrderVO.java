/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */
/*     */ public class CompleteOrderVO
/*     */ {
/*     */   @SerializedName("completeStatus")
/*  16 */   private String completeStatus = null;
/*     */   
/*     */   @SerializedName("dealImgs")
/*  19 */   private List<String> dealImgs = new ArrayList();
/*     */   
/*     */   @SerializedName("dealRemark")
/*  22 */   private String dealRemark = null;
/*     */   
/*     */   @SerializedName("orderCode")
/*  25 */   private String orderCode = null;
/*     */   
/*     */   @SerializedName("pointType")
/*  28 */   private String pointType = null;
/*     */   
/*     */   @SerializedName("x")
/*  31 */   private Double x = null;
/*     */   
/*     */   @SerializedName("y")
/*  34 */   private Double y = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("1,正常完成，2 异常完成")
/*     */   public String getCompleteStatus()
/*     */   {
/*  44 */     return this.completeStatus;
/*     */   }
/*     */   
/*  47 */   public void setCompleteStatus(String completeStatus) { this.completeStatus = completeStatus; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public List<String> getDealImgs()
/*     */   {
/*  55 */     return this.dealImgs;
/*     */   }
/*     */   
/*  58 */   public void setDealImgs(List<String> dealImgs) { this.dealImgs = dealImgs; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getDealRemark()
/*     */   {
/*  66 */     return this.dealRemark;
/*     */   }
/*     */   
/*  69 */   public void setDealRemark(String dealRemark) { this.dealRemark = dealRemark; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getOrderCode()
/*     */   {
/*  77 */     return this.orderCode;
/*     */   }
/*     */   
/*  80 */   public void setOrderCode(String orderCode) { this.orderCode = orderCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getPointType()
/*     */   {
/*  88 */     return this.pointType;
/*     */   }
/*     */   
/*  91 */   public void setPointType(String pointType) { this.pointType = pointType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getX()
/*     */   {
/*  99 */     return this.x;
/*     */   }
/*     */   
/* 102 */   public void setX(Double x) { this.x = x; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getY()
/*     */   {
/* 110 */     return this.y;
/*     */   }
/*     */   
/* 113 */   public void setY(Double y) { this.y = y; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 120 */     if (this == o) {
/* 121 */       return true;
/*     */     }
/* 123 */     if ((o == null) || (getClass() != o.getClass())) {
/* 124 */       return false;
/*     */     }
/* 126 */     CompleteOrderVO completeOrderVO = (CompleteOrderVO)o;
/* 127 */     return (Objects.equals(this.completeStatus, completeOrderVO.completeStatus)) && 
/* 128 */       (Objects.equals(this.dealImgs, completeOrderVO.dealImgs)) && 
/* 129 */       (Objects.equals(this.dealRemark, completeOrderVO.dealRemark)) && 
/* 130 */       (Objects.equals(this.orderCode, completeOrderVO.orderCode)) && 
/* 131 */       (Objects.equals(this.pointType, completeOrderVO.pointType)) && 
/* 132 */       (Objects.equals(this.x, completeOrderVO.x)) && 
/* 133 */       (Objects.equals(this.y, completeOrderVO.y));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 138 */     return Objects.hash(new Object[] { this.completeStatus, this.dealImgs, this.dealRemark, this.orderCode, this.pointType, this.x, this.y });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 143 */     StringBuilder sb = new StringBuilder();
/* 144 */     sb.append("class CompleteOrderVO {\n");
/*     */     
/* 146 */     sb.append("    completeStatus: ").append(toIndentedString(this.completeStatus)).append("\n");
/* 147 */     sb.append("    dealImgs: ").append(toIndentedString(this.dealImgs)).append("\n");
/* 148 */     sb.append("    dealRemark: ").append(toIndentedString(this.dealRemark)).append("\n");
/* 149 */     sb.append("    orderCode: ").append(toIndentedString(this.orderCode)).append("\n");
/* 150 */     sb.append("    pointType: ").append(toIndentedString(this.pointType)).append("\n");
/* 151 */     sb.append("    x: ").append(toIndentedString(this.x)).append("\n");
/* 152 */     sb.append("    y: ").append(toIndentedString(this.y)).append("\n");
/* 153 */     sb.append("}");
/* 154 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 162 */     if (o == null) {
/* 163 */       return "null";
/*     */     }
/* 165 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\CompleteOrderVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */