package com.danchexia.bikehero.main.myoffer.bean;

import com.danchexia.bikehero.api.ApiProperty;

import java.util.Date;

/**
 * Created by farley on 17/5/22.
 * description:
 */

public class MyOfferData {
    @ApiProperty("source")
    private String offerType;//邀请券类型
    @ApiProperty("expireDate")
    private Date effectiveDate;//有效期
    @ApiProperty("amount")
    private Double money;
    @ApiProperty("cityId")
    private String cityId = null;
    @ApiProperty("cityName")
    private String cityName = null;
    @ApiProperty("id")
    private Long id = null;

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
