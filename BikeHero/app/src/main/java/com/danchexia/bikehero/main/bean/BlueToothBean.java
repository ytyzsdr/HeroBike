package com.danchexia.bikehero.main.bean;

import com.google.gson.annotations.SerializedName;
import com.danchexia.bikehero.api.BaseBean;

/**
 * Created by farley on 17/7/27.
 * description:
 */

public class BlueToothBean extends BaseBean {
    @SerializedName("bluetoothCode")
    private String bluetoothCode = null;//蓝牙协议版本 必加锁:bijiasuo-01
    @SerializedName("lockMacAddress")
    private String lockMacAddress = null;//锁的mac地址
    @SerializedName("openType")
    private Integer openType = null;//打开方式 1：GPRS，2：蓝牙，3：GPRS 和 蓝牙
    @SerializedName("shareType")
    private String shareType = null;//共享类型，bicycle,battery
    @SerializedName("sysCode")
    private String sysCode = null;

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    @SerializedName("macPwd")
    private String macPwd = null;
    @SerializedName("macSecretKey")
    private String macSecretKey = null;

    public String getMacPwd() {
        return macPwd;
    }

    public void setMacPwd(String macPwd) {
        this.macPwd = macPwd;
    }

    public String getMacSecretKey() {
        return macSecretKey;
    }

    public void setMacSecretKey(String macSecretKey) {
        this.macSecretKey = macSecretKey;
    }

    public String getBluetoothCode() {
        return bluetoothCode;
    }

    public void setBluetoothCode(String bluetoothCode) {
        this.bluetoothCode = bluetoothCode;
    }

    public String getLockMacAddress() {
        return lockMacAddress;
    }

    public void setLockMacAddress(String lockMacAddress) {
        this.lockMacAddress = lockMacAddress;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }
}
