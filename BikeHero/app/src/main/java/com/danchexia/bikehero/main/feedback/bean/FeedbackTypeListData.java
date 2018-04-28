package com.danchexia.bikehero.main.feedback.bean;

import com.danchexia.bikehero.api.ApiProperty;

/**
 * Created by farley on 17/5/30.
 * description:
 */

public class FeedbackTypeListData {
    @ApiProperty("id")
    private Long id = null;
    @ApiProperty("type")
    private String type = null;
    @ApiProperty("typeDesc")
    private String typeDesc = null;
    @ApiProperty("typeName")
    private String typeName = null;
    @ApiProperty("isCheckLock")
    private Boolean isCheckLock = null;

    public Boolean getCheckLock() {
        return isCheckLock;
    }

    public void setCheckLock(Boolean checkLock) {
        isCheckLock = checkLock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
