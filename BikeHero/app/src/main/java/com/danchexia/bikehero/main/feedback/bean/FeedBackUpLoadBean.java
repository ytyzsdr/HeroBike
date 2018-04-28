package com.danchexia.bikehero.main.feedback.bean;

/**
 * Created by farley on 17/5/30.
 * description:
 */

public class FeedBackUpLoadBean {
    private String feedDesc = null;
    private String imgUrl1 = null;
    private String imgUrl2 = null;
    private String imgUrl3 = null;
    private String imgUrl4 = null;
    private String sysCode = null;
    private Long tripId = null;
    private String typeId = null;
    private Integer bluetoothConnection = null;//蓝牙连接状态 1:正常 2:异常  3:超时
    private Boolean lockOnoff = null;

    public Integer getBluetoothConnection() {
        return bluetoothConnection;
    }

    public void setBluetoothConnection(Integer bluetoothConnection) {
        this.bluetoothConnection = bluetoothConnection;
    }

    public Boolean getLockOnoff() {
        return lockOnoff;
    }

    public void setLockOnoff(Boolean lockOnoff) {
        this.lockOnoff = lockOnoff;
    }

    public String getFeedDesc() {
        return feedDesc;
    }

    public void setFeedDesc(String feedDesc) {
        this.feedDesc = feedDesc;
    }

    public String getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(String imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    public String getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    public String getImgUrl3() {
        return imgUrl3;
    }

    public void setImgUrl3(String imgUrl3) {
        this.imgUrl3 = imgUrl3;
    }

    public String getImgUrl4() {
        return imgUrl4;
    }

    public void setImgUrl4(String imgUrl4) {
        this.imgUrl4 = imgUrl4;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
