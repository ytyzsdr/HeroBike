package com.danchexia.bikehero.main.mystroke.bean;

import com.danchexia.bikehero.api.ApiProperty;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by farley on 17/5/17.
 * description:
 */

public class ItemStrokeBean extends BaseBean {
    private List<OnGoing_TripBO> content = new ArrayList();
    @ApiProperty("error")
    private String error = null;
    @ApiProperty("error_description")
    private String errorDescription = null;
    @ApiProperty("first")
    private Boolean first = null;
    @ApiProperty("last")
    private Boolean last = null;
    @ApiProperty("number")
    private Integer number = null;
    @ApiProperty("searchDate")
    private Date searchDate = null;
    @ApiProperty("size")
    private Integer size = null;
    @ApiProperty("success")
    private Boolean success = null;
    @ApiProperty("totalElements")
    private Long totalElements = null;
    @ApiProperty("totalPages")
    private Integer totalPages = null;

    public List<OnGoing_TripBO> getContent() {
        return content;
    }

    public void setContent(List<OnGoing_TripBO> content) {
        this.content = content;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
