/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class PageResponseOfAPIVipPayLogBO
/*     */ {
/*     */   @SerializedName("content")
/*  18 */   private List<APIVipPayLogBO> content = new ArrayList();
/*     */   
/*     */   @SerializedName("error")
/*  21 */   private String error = null;
/*     */   
/*     */   @SerializedName("error_description")
/*  24 */   private String errorDescription = null;
/*     */   
/*     */   @SerializedName("first")
/*  27 */   private Boolean first = null;
/*     */   
/*     */   @SerializedName("last")
/*  30 */   private Boolean last = null;
/*     */   
/*     */   @SerializedName("number")
/*  33 */   private Integer number = null;
/*     */   
/*     */   @SerializedName("searchDate")
/*  36 */   private Date searchDate = null;
/*     */   
/*     */   @SerializedName("size")
/*  39 */   private Integer size = null;
/*     */   
/*     */   @SerializedName("success")
/*  42 */   private Boolean success = null;
/*     */   
/*     */   @SerializedName("totalElements")
/*  45 */   private Long totalElements = null;
/*     */   
/*     */   @SerializedName("totalPages")
/*  48 */   private Integer totalPages = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public List<APIVipPayLogBO> getContent()
/*     */   {
/*  57 */     return this.content;
/*     */   }
/*     */   
/*  60 */   public void setContent(List<APIVipPayLogBO> content) { this.content = content; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getError()
/*     */   {
/*  68 */     return this.error;
/*     */   }
/*     */   
/*  71 */   public void setError(String error) { this.error = error; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getErrorDescription()
/*     */   {
/*  79 */     return this.errorDescription;
/*     */   }
/*     */   
/*  82 */   public void setErrorDescription(String errorDescription) { this.errorDescription = errorDescription; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getFirst()
/*     */   {
/*  90 */     return this.first;
/*     */   }
/*     */   
/*  93 */   public void setFirst(Boolean first) { this.first = first; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getLast()
/*     */   {
/* 101 */     return this.last;
/*     */   }
/*     */   
/* 104 */   public void setLast(Boolean last) { this.last = last; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getNumber()
/*     */   {
/* 112 */     return this.number;
/*     */   }
/*     */   
/* 115 */   public void setNumber(Integer number) { this.number = number; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getSearchDate()
/*     */   {
/* 123 */     return this.searchDate;
/*     */   }
/*     */   
/* 126 */   public void setSearchDate(Date searchDate) { this.searchDate = searchDate; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getSize()
/*     */   {
/* 134 */     return this.size;
/*     */   }
/*     */   
/* 137 */   public void setSize(Integer size) { this.size = size; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getSuccess()
/*     */   {
/* 145 */     return this.success;
/*     */   }
/*     */   
/* 148 */   public void setSuccess(Boolean success) { this.success = success; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getTotalElements()
/*     */   {
/* 156 */     return this.totalElements;
/*     */   }
/*     */   
/* 159 */   public void setTotalElements(Long totalElements) { this.totalElements = totalElements; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getTotalPages()
/*     */   {
/* 167 */     return this.totalPages;
/*     */   }
/*     */   
/* 170 */   public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 177 */     if (this == o) {
/* 178 */       return true;
/*     */     }
/* 180 */     if ((o == null) || (getClass() != o.getClass())) {
/* 181 */       return false;
/*     */     }
/* 183 */     PageResponseOfAPIVipPayLogBO pageResponseOfAPIVipPayLogBO = (PageResponseOfAPIVipPayLogBO)o;
/* 184 */     return (Objects.equals(this.content, pageResponseOfAPIVipPayLogBO.content)) && 
/* 185 */       (Objects.equals(this.error, pageResponseOfAPIVipPayLogBO.error)) && 
/* 186 */       (Objects.equals(this.errorDescription, pageResponseOfAPIVipPayLogBO.errorDescription)) && 
/* 187 */       (Objects.equals(this.first, pageResponseOfAPIVipPayLogBO.first)) && 
/* 188 */       (Objects.equals(this.last, pageResponseOfAPIVipPayLogBO.last)) && 
/* 189 */       (Objects.equals(this.number, pageResponseOfAPIVipPayLogBO.number)) && 
/* 190 */       (Objects.equals(this.searchDate, pageResponseOfAPIVipPayLogBO.searchDate)) && 
/* 191 */       (Objects.equals(this.size, pageResponseOfAPIVipPayLogBO.size)) && 
/* 192 */       (Objects.equals(this.success, pageResponseOfAPIVipPayLogBO.success)) && 
/* 193 */       (Objects.equals(this.totalElements, pageResponseOfAPIVipPayLogBO.totalElements)) && 
/* 194 */       (Objects.equals(this.totalPages, pageResponseOfAPIVipPayLogBO.totalPages));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 199 */     return Objects.hash(new Object[] { this.content, this.error, this.errorDescription, this.first, this.last, this.number, this.searchDate, this.size, this.success, this.totalElements, this.totalPages });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 204 */     StringBuilder sb = new StringBuilder();
/* 205 */     sb.append("class PageResponseOfAPIVipPayLogBO {\n");
/*     */     
/* 207 */     sb.append("    content: ").append(toIndentedString(this.content)).append("\n");
/* 208 */     sb.append("    error: ").append(toIndentedString(this.error)).append("\n");
/* 209 */     sb.append("    errorDescription: ").append(toIndentedString(this.errorDescription)).append("\n");
/* 210 */     sb.append("    first: ").append(toIndentedString(this.first)).append("\n");
/* 211 */     sb.append("    last: ").append(toIndentedString(this.last)).append("\n");
/* 212 */     sb.append("    number: ").append(toIndentedString(this.number)).append("\n");
/* 213 */     sb.append("    searchDate: ").append(toIndentedString(this.searchDate)).append("\n");
/* 214 */     sb.append("    size: ").append(toIndentedString(this.size)).append("\n");
/* 215 */     sb.append("    success: ").append(toIndentedString(this.success)).append("\n");
/* 216 */     sb.append("    totalElements: ").append(toIndentedString(this.totalElements)).append("\n");
/* 217 */     sb.append("    totalPages: ").append(toIndentedString(this.totalPages)).append("\n");
/* 218 */     sb.append("}");
/* 219 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 227 */     if (o == null) {
/* 228 */       return "null";
/*     */     }
/* 230 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\PageResponseOfAPIVipPayLogBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */