/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class RequestResult
/*     */ {
/*     */   @SerializedName("fid")
/*  15 */   private String fid = null;
/*     */   
/*     */   @SerializedName("fileName")
/*  18 */   private String fileName = null;
/*     */   
/*     */   @SerializedName("height")
/*  21 */   private Integer height = null;
/*     */   
/*     */   @SerializedName("mime")
/*  24 */   private String mime = null;
/*     */   
/*     */   @SerializedName("size")
/*  27 */   private Double size = null;
/*     */   
/*     */   @SerializedName("success")
/*  30 */   private Boolean success = null;
/*     */   
/*     */   @SerializedName("url")
/*  33 */   private String url = null;
/*     */   
/*     */   @SerializedName("width")
/*  36 */   private Integer width = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getFid()
/*     */   {
/*  45 */     return this.fid;
/*     */   }
/*     */   
/*  48 */   public void setFid(String fid) { this.fid = fid; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getFileName()
/*     */   {
/*  56 */     return this.fileName;
/*     */   }
/*     */   
/*  59 */   public void setFileName(String fileName) { this.fileName = fileName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getHeight()
/*     */   {
/*  67 */     return this.height;
/*     */   }
/*     */   
/*  70 */   public void setHeight(Integer height) { this.height = height; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getMime()
/*     */   {
/*  78 */     return this.mime;
/*     */   }
/*     */   
/*  81 */   public void setMime(String mime) { this.mime = mime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Double getSize()
/*     */   {
/*  89 */     return this.size;
/*     */   }
/*     */   
/*  92 */   public void setSize(Double size) { this.size = size; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getSuccess()
/*     */   {
/* 100 */     return this.success;
/*     */   }
/*     */   
/* 103 */   public void setSuccess(Boolean success) { this.success = success; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getUrl()
/*     */   {
/* 111 */     return this.url;
/*     */   }
/*     */   
/* 114 */   public void setUrl(String url) { this.url = url; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getWidth()
/*     */   {
/* 122 */     return this.width;
/*     */   }
/*     */   
/* 125 */   public void setWidth(Integer width) { this.width = width; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 132 */     if (this == o) {
/* 133 */       return true;
/*     */     }
/* 135 */     if ((o == null) || (getClass() != o.getClass())) {
/* 136 */       return false;
/*     */     }
/* 138 */     RequestResult requestResult = (RequestResult)o;
/* 139 */     return (Objects.equals(this.fid, requestResult.fid)) && 
/* 140 */       (Objects.equals(this.fileName, requestResult.fileName)) && 
/* 141 */       (Objects.equals(this.height, requestResult.height)) && 
/* 142 */       (Objects.equals(this.mime, requestResult.mime)) && 
/* 143 */       (Objects.equals(this.size, requestResult.size)) && 
/* 144 */       (Objects.equals(this.success, requestResult.success)) && 
/* 145 */       (Objects.equals(this.url, requestResult.url)) && 
/* 146 */       (Objects.equals(this.width, requestResult.width));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 151 */     return Objects.hash(new Object[] { this.fid, this.fileName, this.height, this.mime, this.size, this.success, this.url, this.width });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 156 */     StringBuilder sb = new StringBuilder();
/* 157 */     sb.append("class RequestResult {\n");
/*     */     
/* 159 */     sb.append("    fid: ").append(toIndentedString(this.fid)).append("\n");
/* 160 */     sb.append("    fileName: ").append(toIndentedString(this.fileName)).append("\n");
/* 161 */     sb.append("    height: ").append(toIndentedString(this.height)).append("\n");
/* 162 */     sb.append("    mime: ").append(toIndentedString(this.mime)).append("\n");
/* 163 */     sb.append("    size: ").append(toIndentedString(this.size)).append("\n");
/* 164 */     sb.append("    success: ").append(toIndentedString(this.success)).append("\n");
/* 165 */     sb.append("    url: ").append(toIndentedString(this.url)).append("\n");
/* 166 */     sb.append("    width: ").append(toIndentedString(this.width)).append("\n");
/* 167 */     sb.append("}");
/* 168 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 176 */     if (o == null) {
/* 177 */       return "null";
/*     */     }
/* 179 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\RequestResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */