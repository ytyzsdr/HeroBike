package com.danchexia.bikehero.main.personal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.R;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/31.
 * description:
 */

public class FixUserNameActivity extends MvpActivity<FixUserNamePresenter,IPersonalView> implements IPersonalView {
    private ImageView head_left;
    //private ImageView cancel;
    private TextView head_title;
    private EditText nickName;
    private TextView save;
    private String nickname_str;
    private FixUserNamePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_username);
        nickname_str = getIntent().getStringExtra("NICKNAME");
        initView();

    }

    @Override
    protected FixUserNamePresenter CreatePresenter() {
        return presenter = new FixUserNamePresenter(this);
    }

    private void initView() {
        nickName = (EditText) findViewById(R.id.nickName);
        save = (TextView) findViewById(R.id.save);
        head_title = (TextView) findViewById(R.id.head_title);
        //cancel = (ImageView) findViewById(R.id.cancel);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_title.setText(Utils.object2String(getString(R.string.fix_username)));

        nickName.setText(Utils.object2String(nickname_str));
        //光标置于最后
        nickName.setSelection(nickName.getText().length());
        nickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //hint字体大小为15sp并隐藏光标
                if(s.length()==0) {
                    nickName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    nickName.setCursorVisible(false);
                }else{
                    nickName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    nickName.setCursorVisible(true);
                }
            }
        });

        head_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nickName.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    presenter.upNicName(name);
                }else{
                    ShowToast.show(FixUserNameActivity.this,getString(R.string.toast_4));
                }
            }
        });
        /*cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickName.setText("");
            }
        });*/
    }
    public void setSuccessDo(){
        ShowToast.show(FixUserNameActivity.this,getString(R.string.toast_5));
        String name = nickName.getText().toString().trim();
        Intent it = new Intent();
        it.putExtra("NICKNAME",name);
        setResult(RESULT_OK,it);
        finish();
    }
}
