package com.danchexia.bikehero.main.bean;

import com.danchexia.bikehero.api.ApiProperty;

import java.util.Date;

/**
 * Created by farley on 17/5/27.
 * description:
 */

public class UserCouponDataM {
    @ApiProperty("amount")
    private Double amount = null;
    @ApiProperty("cityId")
    private String cityId = null;
    @ApiProperty("cityName")
    private String cityName = null;
    @ApiProperty("expireDate")
    private Date expireDate = null;
    @ApiProperty("id")
    private Long id = null;
    @ApiProperty("source")
    private String source = null;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
