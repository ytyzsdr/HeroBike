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
/*     */ public class PageResponseOfTripBO
/*     */ {
    @SerializedName("content")
    private List<TripBO> content = new ArrayList();
    @SerializedName("error")
    private String error = null;
    @SerializedName("error_description")
    private String errorDescription = null;
    @SerializedName("first")
    private Boolean first = null;
    @SerializedName("last")
    private Boolean last = null;
    @SerializedName("number")
    private Integer number = null;
    @SerializedName("searchDate")
    private Date searchDate = null;
    @SerializedName("size")
    private Integer size = null;
    @SerializedName("success")
    private Boolean success = null;
    @SerializedName("totalElements")
    private Long totalElements = null;
    @SerializedName("totalPages")
    private Integer totalPages = null;

    public PageResponseOfTripBO() {
    }

    public List<TripBO> getContent() {
        return this.content;
    }

    public void setContent(List<TripBO> content) {
        this.content = content;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Boolean getFirst() {
        return this.first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return this.last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getSearchDate() {
        return this.searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\PageResponseOfTripBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */