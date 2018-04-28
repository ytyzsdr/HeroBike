package com.danchexia.bikehero.main.wallet.bean;

import com.danchexia.bikehero.api.ApiProperty;

import java.util.Date;

/**
 * Created by farley on 17/5/18.
 * description:
 */

public class WalletItemData {
    @ApiProperty("amount")
    private Double amount = null;
    @ApiProperty("createTime")
    private Date createTime = null;
    @ApiProperty("type")
    private String type = null;//1充值2退款
    @ApiProperty("uid")
    private Long uid = null;
    @ApiProperty("updateTime")
    private Date updateTime = null;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
