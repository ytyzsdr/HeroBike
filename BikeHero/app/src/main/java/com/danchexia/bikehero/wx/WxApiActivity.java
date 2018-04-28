package com.danchexia.bikehero.wx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.danchexia.bikehero.pay.wxpay.WechatHelper;

import static com.danchexia.bikehero.app.MyApplication.wxApi;
import static com.danchexia.bikehero.config.Config.wxAppId;
import static com.danchexia.bikehero.config.Config.wxAppSecret;

public class WxApiActivity extends Activity implements IWXAPIEventHandler {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wxApi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        wxApi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX && resp.errCode == 0) {
            WechatHelper.handleOnResp(resp);
        }
        else if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            Bundle bundle = getIntent().getExtras();
            SendAuth.Resp respResult = new SendAuth.Resp(bundle);
            getAccessToken(respResult);
        }
        this.finish();
    }


    /**
     * 获取openid accessToken值用于后期操作
     */
    private void getAccessToken(final SendAuth.Resp respResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                Bundle bundle = new Bundle();
                if (respResult.errCode == 0) {
                    try {
                        final String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wxAppId + "&secret=" + wxAppSecret + "&code=" + respResult.code + "&grant_type=authorization_code";
//                        JSONObject jsonObject = JsonUtils.initSSLWithHttpClinet(path);// 请求https连接并得到json结果
//                        if (null != jsonObject) {
//                            String openid = jsonObject.getString("openid").toString().trim();
//                            String access_token = jsonObject.getString("access_token").toString().trim();
//                            bundle.putInt("errorCode", 0);
//                            bundle.putString("accessToken", access_token);
//                            bundle.putString("openId", openid);
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        bundle.putInt("errorCode", -1);
                    }
                }
                else if (respResult.errCode == -2) {
                    bundle.putInt("errorCode", -2);
                }
                else
                    bundle.putInt("errorCode", -1);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };

}