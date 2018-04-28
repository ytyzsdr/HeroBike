package com.danchexia.bikehero.main.openloack;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import vc.thinker.mvp.util.XlStatusBarUtil;
import vc.thinker.tools.utils.ShowToast;

/**
 * Created by farley on 17/5/15.
 * description:
 */

public class ScanQrcodeActivity extends FragmentActivity {
    private ImageView title_left_img;//标题左边的图片
    private TextView openFlash;//打开闪光灯
    private TextView code_open_locak;//编码开锁
    private TextView title_right;
    private boolean isOpen = true;
    FragmentTransaction transaction;
    CaptureFragment captureFragment;
    private TextView scan_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        XlStatusBarUtil.StatusBarLightMode(this, XlStatusBarUtil.StatusBarLightMode(this));
         captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_zxing_container, captureFragment).commit();
        title_left_img = (ImageView) findViewById(com.uuzuche.lib_zxing.R.id.title_left_img);
        title_right = (TextView) findViewById(R.id.title_right);
        code_open_locak = (TextView) findViewById(R.id.code_open_locak);
        scan_desc = (TextView) findViewById(R.id.scan_desc);
        openFlash = (TextView) findViewById(com.uuzuche.lib_zxing.R.id.openFlash);
        title_left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_left_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        title_left_img.setSelected(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        title_left_img.setSelected(false);
                        finish();
                        break;
                }

                return true;
            }
        });
        if (!hasFlash()){
            openFlash.setVisibility(View.GONE);
        }
        openFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen){
                    Log.d("farley","close");
                    isOpen = false;
                    /**
                     * 关闭闪光灯
                     */
                    CodeUtils.isLightEnable(false);
                    openFlash.setSelected(false);
                }else {
                    Log.d("farley","open");
                    isOpen = true;
                    /**
                     * 打开闪光灯
                     */
                    CodeUtils.isLightEnable(true);
                    openFlash.setSelected(true);
                }
            }
        });
        code_open_locak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityController.startCodeOpenLock(ScanQrcodeActivity.this);
                finish();
            }
        });
        if (Config.isOpenBattery){
            scan_desc.setText("将二维码放入框内，即可自动扫描");
        }
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            /*Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();*/
            //https://colours.api.thinker.vc/wx?bike=00046032
            String codes="";
            if (result.length()>8){
                codes=result.substring(result.length()-8);
                if (isNumber(codes)){
                    ActivityController.startLockOpenning(ScanQrcodeActivity.this, codes);
                    if (captureFragment != null) {
                        transaction.remove(captureFragment);
                    }
                    ScanQrcodeActivity.this.finish();
                }else {
                    ShowToast.show(ScanQrcodeActivity.this,getString(R.string.toast_3));
                }
            }else {
                ShowToast.show(ScanQrcodeActivity.this,getString(R.string.toast_3));
            }
        }

        @Override
        public void onAnalyzeFailed() {
            /*Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();*/
            ShowToast.show(ScanQrcodeActivity.this,getString(R.string.toast_3));
        }
    };

    //    判断是否为数字的
    private boolean isNumber(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    protected void onDestroy() {
        if (captureFragment != null) {
            transaction.remove(captureFragment);
        }

        super.onDestroy();
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
}