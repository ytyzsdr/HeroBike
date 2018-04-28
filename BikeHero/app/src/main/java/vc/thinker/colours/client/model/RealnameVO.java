/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */
/*     */ public class RealnameVO
/*     */ {
/*     */   @SerializedName("credentialsImages")
/*  16 */   private List<String> credentialsImages = new ArrayList();
/*     */   
/*     */   @SerializedName("idcard")
/*  19 */   private String idcard = null;
/*     */   
/*     */   @SerializedName("name")
/*  22 */   private String name = null;
/*     */   
/*     */   @SerializedName("schoolName")
/*  25 */   private String schoolName = null;
/*     */   
/*     */   @SerializedName("studentId")
/*  28 */   private String studentId = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public List<String> getCredentialsImages()
/*     */   {
/*  37 */     return this.credentialsImages;
/*     */   }
/*     */   
/*  40 */   public void setCredentialsImages(List<String> credentialsImages) { this.credentialsImages = credentialsImages; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getIdcard()
/*     */   {
/*  48 */     return this.idcard;
/*     */   }
/*     */   
/*  51 */   public void setIdcard(String idcard) { this.idcard = idcard; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getName()
/*     */   {
/*  59 */     return this.name;
/*     */   }
/*     */   
/*  62 */   public void setName(String name) { this.name = name; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getSchoolName()
/*     */   {
/*  70 */     return this.schoolName;
/*     */   }
/*     */   
/*  73 */   public void setSchoolName(String schoolName) { this.schoolName = schoolName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getStudentId()
/*     */   {
/*  81 */     return this.studentId;
/*     */   }
/*     */   
/*  84 */   public void setStudentId(String studentId) { this.studentId = studentId; }
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
/*  97 */     RealnameVO realnameVO = (RealnameVO)o;
/*  98 */     return (Objects.equals(this.credentialsImages, realnameVO.credentialsImages)) && 
/*  99 */       (Objects.equals(this.idcard, realnameVO.idcard)) && 
/* 100 */       (Objects.equals(this.name, realnameVO.name)) && 
/* 101 */       (Objects.equals(this.schoolName, realnameVO.schoolName)) && 
/* 102 */       (Objects.equals(this.studentId, realnameVO.studentId));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 107 */     return Objects.hash(new Object[] { this.credentialsImages, this.idcard, this.name, this.schoolName, this.studentId });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 112 */     StringBuilder sb = new StringBuilder();
/* 113 */     sb.append("class RealnameVO {\n");
/*     */     
/* 115 */     sb.append("    credentialsImages: ").append(toIndentedString(this.credentialsImages)).append("\n");
/* 116 */     sb.append("    idcard: ").append(toIndentedString(this.idcard)).append("\n");
/* 117 */     sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
/* 118 */     sb.append("    schoolName: ").append(toIndentedString(this.schoolName)).append("\n");
/* 119 */     sb.append("    studentId: ").append(toIndentedString(this.studentId)).append("\n");
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


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\RealnameVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */