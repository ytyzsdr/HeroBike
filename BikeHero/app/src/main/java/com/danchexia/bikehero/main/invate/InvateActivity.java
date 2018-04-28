package com.danchexia.bikehero.main.invate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.MainActivity;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/17.
 * description:填写好友的邀请码
 */

public class InvateActivity extends MvpActivity<InvatePresenter, IInvateView> implements IInvateView, View.OnClickListener {
    private InvatePresenter presenter;
    private ImageView head_left, head_right;
    private TextView head_title;
    private Button complete;
    private EditText invateCode;

    @Override
    protected InvatePresenter CreatePresenter() {
        return presenter = new InvatePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invate);
        initView();
    }

    private void initView() {
        invateCode = (EditText) findViewById(R.id.invateCode);
        complete = (Button) findViewById(R.id.complete);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title.setText(Utils.object2String(getString(R.string.invate_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        complete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.complete:
                String code = invateCode.getText().toString().trim();
                if (!TextUtils.isEmpty(code)){
                    presenter.boundInvate(code);
                }else {
                    Intent main = new Intent(InvateActivity.this,MainActivity.class);
                    startActivity(main);
                    finish();
                }
                break;
            case R.id.head_left:
                finish();
                break;
            default:
                break;
        }
    }
}
