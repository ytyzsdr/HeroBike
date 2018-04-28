package com.danchexia.bikehero.main.bindphone;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.MyHandler;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/15.
 * description:
 */

public class BindPhoneActivity extends MvpActivity<BindPhonePresenter,IBindPhoneView> implements IBindPhoneView, View.OnClickListener {
    private BindPhonePresenter presenter;
    private ImageView head_left,head_right;//标题栏
    private TextView head_title;//标题
    private EditText edt_phone,edt_auth;//手机号 验证码
    private TextView get_auth,rules;//获取 规则
    private TextView complete;
    private MyHandler myHandler;
    Long downCount = 60L;
    private RelativeLayout mButton_layout;
    @Override
    protected BindPhonePresenter CreatePresenter() {
        return presenter = new BindPhonePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        initView();
    }
    private void initView() {
        mButton_layout = (RelativeLayout) findViewById(R.id.button_layout);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title = (TextView) findViewById(R.id.head_title);
        get_auth = (TextView) findViewById(R.id.get_auth);
        rules = (TextView) findViewById(R.id.rules);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_auth = (EditText) findViewById(R.id.edt_auth);
        complete = (TextView) findViewById(R.id.complete);
        head_title.setText(getString(R.string.bind_phone));//设置标题
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        get_auth.setOnClickListener(this);
        rules.setOnClickListener(this);
        complete.setOnClickListener(this);
        edt_auth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5){
                    complete.setEnabled(true);
                    mButton_layout.setBackgroundResource(R.drawable.bottom_shadow_bg);
                }else {
                    complete.setEnabled(false);
                    mButton_layout.setBackgroundResource(R.drawable.bottom_shadow_bg_nomal);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        String phoneNum = PreferencesUtils.getString(BindPhoneActivity.this,"BING_PHONE_NUMBER");
        if (!TextUtils.isEmpty(phoneNum)){
            edt_phone.setText(phoneNum);
        }
        Long nowTime = System.currentTimeMillis()/1000;
        Long tempTime = PreferencesUtils.getLong(BindPhoneActivity.this,"DOWNCOUNT",nowTime);
        String nowCount = PreferencesUtils.getString(BindPhoneActivity.this,"NOWCOUNT");
        Long temp = -1L;
        if (!TextUtils.isEmpty(nowCount) && nowTime != tempTime) {
            temp = Long.parseLong(nowCount) - (nowTime - tempTime);
        }
        if (temp > 0 && temp < 60){
            downCount = temp;
            get_auth.setEnabled(false);
            setCountDown();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_left:
                finish();
                break;
            case R.id.get_auth://获取验证码
                String phoneStr = edt_phone.getText().toString().trim();
                if (TextUtils.isEmpty(phoneStr)){
                    ShowToast.show(this,getString(R.string.bind_toast1));
                }else if(Utils.isMobile(phoneStr)){
                    get_auth.setEnabled(false);
                    presenter.getAuth(phoneStr);
                    downCount = 60L;
                    edt_auth.setFocusable(true);
                    edt_auth.requestFocus();

                }else{
                    ShowToast.show(this,getString(R.string.bind_toast2));
                }

                break;
            case R.id.rules://规则
                ActivityController.startWebView(this,getString(R.string.bind_rules_title),"share/use_car_rules",false);
                break;
            case R.id.complete://完成
                String phone = edt_phone.getText().toString().trim();
                String code = edt_auth.getText().toString().trim();
                PreferencesUtils.putString(BindPhoneActivity.this,"BING_PHONE_NUMBER",phone);
                PreferencesUtils.putLong(BindPhoneActivity.this,"DOWNCOUNT",System.currentTimeMillis()/1000);
                presenter.loginService(phone,code);
                break;
            default:
                break;
        }
    }
    private void setCountDown(){
        final Message msg = new Message();
        msg.what = 2;

        myHandler = new MyHandler(downCount);
        myHandler.setOnChangeLisener( new MyHandler.onCountChange() {
            @Override
            public void currentCount(String count) {
                PreferencesUtils.putString(BindPhoneActivity.this,"NOWCOUNT",count);
                LogUtils.d("count="+count);
                get_auth.setText(getString(R.string.bind_toast3)+count+")");
                get_auth.setTextColor(getResources().getColor(R.color.color_deep_gray));
                Message msg1 = new Message();
                msg1.what = 2;
                myHandler.sendMessageDelayed(msg1,1000);
                LogUtils.d("count="+count);
                if (count.contentEquals("0")){
                    myHandler.removeMessages(2);
                    get_auth.setText(getString(R.string.bind_toast4));
                    get_auth.setTextColor(getResources().getColor(R.color.color_theme));
                    get_auth.setEnabled(true);
                }
            }
        });
        myHandler.sendMessageDelayed(msg,1000);
    }
    public void setCountClose(){
        if (myHandler != null){
            myHandler.removeMessages(2);
            get_auth.setText(getString(R.string.bind_toast4));
            get_auth.setTextColor(getResources().getColor(R.color.color_theme));
            get_auth.setEnabled(true);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("System.currentTimeMillis()/1000="+System.currentTimeMillis()/1000);
        PreferencesUtils.putLong(BindPhoneActivity.this,"DOWNCOUNT",System.currentTimeMillis()/1000);
        if (myHandler != null){
            myHandler.removeMessages(2);
        }
    }

    public void getAuthSuccess() {
        setCountDown();
    }
}
