package com.danchexia.bikehero.main.fixpower;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.R;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.mvp.MvpView;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/9/5.
 * description:
 */

public class FixPowerActivity extends MvpActivity<FixPowerPresenter,MvpView> implements MvpView {
    private FixPowerPresenter myPresenter;
    @Override
    protected FixPowerPresenter CreatePresenter() {
        return myPresenter = new FixPowerPresenter(this);
    }
    private EditText powerType;
    private TextView save;
    private ImageView cancel;
    private ImageView head_left;
    private Integer motorPower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_power);
        motorPower = getIntent().getIntExtra("MOTORPOWER",0);
        initView();
    }

    private void initView() {
        head_left = (ImageView) findViewById(R.id.head_left);
        powerType = (EditText) findViewById(R.id.powerType);
        save = (TextView) findViewById(R.id.save);
        cancel = (ImageView) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerType.setText("");
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
                String powerstr = powerType.getText().toString().trim();
                if(!TextUtils.isEmpty(powerstr)){
                    Integer po = Integer.parseInt(powerstr);
                    myPresenter.fixPower(po);
                }else{
                    ShowToast.show(FixPowerActivity.this,getString(R.string.toast_31));
                }
            }
        });
        powerType.setText(Utils.object2String(motorPower));
    }

    public void setSuccessDo() {
        ShowToast.show(FixPowerActivity.this,getString(R.string.toast_5));
        String name = powerType.getText().toString().trim();
        Intent it = new Intent();
        it.putExtra("POWERTYPE",name);
        setResult(RESULT_OK,it);
        finish();
    }
}
