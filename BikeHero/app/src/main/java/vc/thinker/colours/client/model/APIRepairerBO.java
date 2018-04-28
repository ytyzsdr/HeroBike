/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class APIRepairerBO
/*     */ {
/*     */   @SerializedName("area")
/*  15 */   private String area = null;
/*     */   
/*     */   @SerializedName("headImgPath")
/*  18 */   private String headImgPath = null;
/*     */   
/*     */   @SerializedName("mobile")
/*  21 */   private String mobile = null;
/*     */   
/*     */   @SerializedName("name")
/*  24 */   private String name = null;
/*     */   
/*     */   @SerializedName("sex")
/*  27 */   private Integer sex = null;
/*     */   
/*     */   @SerializedName("status")
/*  30 */   private String status = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("所属区域")
/*     */   public String getArea()
/*     */   {
/*  40 */     return this.area;
/*     */   }
/*     */   
/*  43 */   public void setArea(String area) { this.area = area; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getHeadImgPath()
/*     */   {
/*  51 */     return this.headImgPath;
/*     */   }
/*     */   
/*  54 */   public void setHeadImgPath(String headImgPath) { this.headImgPath = headImgPath; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getMobile()
/*     */   {
/*  62 */     return this.mobile;
/*     */   }
/*     */   
/*  65 */   public void setMobile(String mobile) { this.mobile = mobile; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getName()
/*     */   {
/*  73 */     return this.name;
/*     */   }
/*     */   
/*  76 */   public void setName(String name) { this.name = name; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getSex()
/*     */   {
/*  84 */     return this.sex;
/*     */   }
/*     */   
/*  87 */   public void setSex(Integer sex) { this.sex = sex; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("状态 1:正常 2禁用")
/*     */   public String getStatus()
/*     */   {
/*  96 */     return this.status;
/*     */   }
/*     */   
/*  99 */   public void setStatus(String status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 106 */     if (this == o) {
/* 107 */       return true;
/*     */     }
/* 109 */     if ((o == null) || (getClass() != o.getClass())) {
/* 110 */       return false;
/*     */     }
/* 112 */     APIRepairerBO aPIRepairerBO = (APIRepairerBO)o;
/* 113 */     return (Objects.equals(this.area, aPIRepairerBO.area)) && 
/* 114 */       (Objects.equals(this.headImgPath, aPIRepairerBO.headImgPath)) && 
/* 115 */       (Objects.equals(this.mobile, aPIRepairerBO.mobile)) && 
/* 116 */       (Objects.equals(this.name, aPIRepairerBO.name)) && 
/* 117 */       (Objects.equals(this.sex, aPIRepairerBO.sex)) && 
/* 118 */       (Objects.equals(this.status, aPIRepairerBO.status));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 123 */     return Objects.hash(new Object[] { this.area, this.headImgPath, this.mobile, this.name, this.sex, this.status });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 128 */     StringBuilder sb = new StringBuilder();
/* 129 */     sb.append("class APIRepairerBO {\n");
/*     */     
/* 131 */     sb.append("    area: ").append(toIndentedString(this.area)).append("\n");
/* 132 */     sb.append("    headImgPath: ").append(toIndentedString(this.headImgPath)).append("\n");
/* 133 */     sb.append("    mobile: ").append(toIndentedString(this.mobile)).append("\n");
/* 134 */     sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
/* 135 */     sb.append("    sex: ").append(toIndentedString(this.sex)).append("\n");
/* 136 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 137 */     sb.append("}");
/* 138 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 146 */     if (o == null) {
/* 147 */       return "null";
/*     */     }
/* 149 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\APIRepairerBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */