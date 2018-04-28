/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class ImgBo
/*     */ {
/*     */   @SerializedName("imgList")
/*  17 */   private List<InitImgBO> imgList = new ArrayList();
/*     */   
/*     */   @SerializedName("md5")
/*  20 */   private String md5 = null;
/*     */   
/*     */   @SerializedName("time")
/*  23 */   private Integer time = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public List<InitImgBO> getImgList()
/*     */   {
/*  32 */     return this.imgList;
/*     */   }
/*     */   
/*  35 */   public void setImgList(List<InitImgBO> imgList) { this.imgList = imgList; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getMd5()
/*     */   {
/*  43 */     return this.md5;
/*     */   }
/*     */   
/*  46 */   public void setMd5(String md5) { this.md5 = md5; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getTime()
/*     */   {
/*  54 */     return this.time;
/*     */   }
/*     */   
/*  57 */   public void setTime(Integer time) { this.time = time; }
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
/*  70 */     ImgBo imgBo = (ImgBo)o;
/*  71 */     return (Objects.equals(this.imgList, imgBo.imgList)) && 
/*  72 */       (Objects.equals(this.md5, imgBo.md5)) && 
/*  73 */       (Objects.equals(this.time, imgBo.time));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  78 */     return Objects.hash(new Object[] { this.imgList, this.md5, this.time });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  83 */     StringBuilder sb = new StringBuilder();
/*  84 */     sb.append("class ImgBo {\n");
/*     */     
/*  86 */     sb.append("    imgList: ").append(toIndentedString(this.imgList)).append("\n");
/*  87 */     sb.append("    md5: ").append(toIndentedString(this.md5)).append("\n");
/*  88 */     sb.append("    time: ").append(toIndentedString(this.time)).append("\n");
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


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ImgBo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */