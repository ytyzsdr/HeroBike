package com.danchexia.bikehero.jpush;

/**
 * Created by farley on 17/6/24.
 * description:"accessToken":"866e46d4a14d449ba602c1b1de9aef09","msgType":"99999"
 */

public class ReceiverBean {
    private String accessToken;
    private String msgType;
    private boolean clicked = false;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
