package com.danchexia.bikehero.main.openloack;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.utils.MyUtils;

import vc.thinker.tools.utils.LightUtils;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/15.
 * description:编码开锁
 */

public class CodeOpenLockActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView title_left_img;
    private TextView title_right;
    private TextView sure;//确认
    private TextView row1,row2,row3,row4,row5,row6,row7,row8;//九个数
    private EditText edt_code;
    private ImageView light;//手电筒
    private boolean isOpen = false;
    private Camera camera;
    private TextView scan_desc;
    private TextView mLight_text;
    private RelativeLayout mShadow_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            camera = Camera.open();
        }catch (RuntimeException e){
            LogUtils.d("相机打开失败");
        }
        setContentView(R.layout.activity_code_open_lock);
        initView();
    }

    private void initView() {
        String hintText = "输入车上的编号解锁用车";
        SpannableString ss =  new SpannableString(hintText);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(16, true);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        mShadow_layout = (RelativeLayout) findViewById(R.id.shadow_layout_id);
        mLight_text = (TextView) findViewById(R.id.light_text);
        title_left_img = (ImageView) findViewById(R.id.title_left_img);
        light = (ImageView) findViewById(R.id.light);
        title_right = (TextView) findViewById(R.id.title_right);
        scan_desc = (TextView) findViewById(R.id.scan_desc);
        row1 = (TextView) findViewById(R.id.row1);
        row2 = (TextView) findViewById(R.id.row2);
        row3 = (TextView) findViewById(R.id.row3);
        row4 = (TextView) findViewById(R.id.row4);
        row5 = (TextView) findViewById(R.id.row5);
        row6 = (TextView) findViewById(R.id.row6);
        row7 = (TextView) findViewById(R.id.row7);
        row8 = (TextView) findViewById(R.id.row8);
        sure = (TextView) findViewById(R.id.sure);
        edt_code = (EditText) findViewById(R.id.edt_code);
        edt_code.setHint(new SpannedString(ss));
        title_left_img.setOnClickListener(this);
        title_right.setOnClickListener(this);
        sure.setOnClickListener(this);
        light.setOnClickListener(this);
        if (!hasFlash()){
            light.setVisibility(View.GONE);
            mLight_text.setVisibility(View.GONE);
        }
        edt_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char[] arrayStr = s.toString().toCharArray();
                if (arrayStr.length > 0){
                    row1.setText(Utils.object2String(arrayStr[0]));
                }else{
                    row1.setText("");
                }
                if (arrayStr.length > 1){
                    row2.setText(Utils.object2String(arrayStr[1]));
                }else{
                    row2.setText("");
                }
                if (arrayStr.length > 2){
                    row3.setText(Utils.object2String(arrayStr[2]));
                }else{
                    row3.setText("");
                }
                if (arrayStr.length > 3){
                    row4.setText(Utils.object2String(arrayStr[3]));
                }else{
                    row4.setText("");
                }
                if (arrayStr.length > 4){
                    row5.setText(Utils.object2String(arrayStr[4]));
                }else{
                    row5.setText("");
                }
                if (arrayStr.length > 5){
                    row6.setText(Utils.object2String(arrayStr[5]));
                }else{
                    row6.setText("");
                }
                if (arrayStr.length > 6){
                    row7.setText(Utils.object2String(arrayStr[6]));
                }else{
                    row7.setText("");
                }
                if (arrayStr.length > 7){
                    row8.setText(Utils.object2String(arrayStr[7]));
                }else{
                    row8.setText("");
                }

                if (s.toString().length() == 8 ){
                    sure.setEnabled(true);
                    mShadow_layout.setBackgroundResource(R.drawable.bottom_shadow_bg);
                }else {
                    sure.setEnabled(false);
                    mShadow_layout.setBackgroundResource(R.drawable.bottom_shadow_bg_nomal);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null){
            if (systemParamsBean.getOpenBattery()){
                scan_desc.setText(Utils.object2String(getString(R.string.zxing_code_tip_battery)));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_left_img:
                finish();
                break;
            case R.id.light:
                if (isOpen){
                    isOpen = false;
                    /**
                     * 关闭闪光灯
                     */
                    LightUtils.turnLightOff(camera);
//                    LightUtils.CloseLightOff(camera);
                    light.setSelected(false);
                }else {
                    isOpen = true;
                    /**
                     * 打开闪光灯
                     */
//                    LightUtils.turnLightOn(camera);
                    LightUtils.OpenLightOn(camera);
                    light.setSelected(true);
                }
                break;
            case R.id.sure:
                String codeStr = edt_code.getText().toString().trim();
                if (!TextUtils.isEmpty(codeStr) && codeStr.length() == 8){
                    String code = edt_code.getText().toString().trim();
                    ActivityController.startLockOpenning(this,code);
                    finish();
                }else{
                    ShowToast.show(this,getString(R.string.my_toast3));
                }
                break;
            default:
                break;
        }
    }
    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }


    @Override
    protected void onDestroy() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        super.onDestroy();
    }
}
