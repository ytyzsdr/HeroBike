package com.danchexia.bikehero.main;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.R;

import java.util.HashMap;
import java.util.Map;

import vc.thinker.colours.client.ApiClient;
import vc.thinker.mvp.util.XlStatusBarUtil;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;


public class WebViewActivity extends Activity implements View.OnClickListener {

    private WebView mWebView;
    private String title="详情";
    private String viewUrl;
    private ImageView head_left,head_right;
    private TextView head_title;
    private Boolean needToken = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        XlStatusBarUtil.StatusBarLightMode(this, XlStatusBarUtil.StatusBarLightMode(this));
        Intent intent = getIntent();
        title=getString(R.string.toast_29);
        title = intent.getStringExtra("TITLE");
        viewUrl = intent.getStringExtra("VIEWURL");
        needToken = intent.getBooleanExtra("NEEDTOKEN",true);
        mWebView = (WebView) findViewById(R.id.sys_webView);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title = (TextView) findViewById(R.id.head_title);
        head_right.setVisibility(View.GONE);
        head_title.setText(title);
        head_left.setOnClickListener(this);
        mWebView.getSettings().setJavaScriptEnabled(true);

        Map<String, String> extraHeaders = new HashMap<String, String>();
        if (needToken) {
            extraHeaders.put("Authorization", "Bearer " + PreferencesUtils.getString(this, "RADISHSAAS_IS_BIND"));
        }else{
            extraHeaders.put("Authorization", "Bearer oauth2-password");
        }
        LogUtils.d("url===="+ApiClient.baseUrl + viewUrl);
        mWebView.loadUrl(ApiClient.baseUrl + viewUrl, extraHeaders);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale!=1){
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_left:
                finish();
                break;
            default:
                break;
        }
    }
}
