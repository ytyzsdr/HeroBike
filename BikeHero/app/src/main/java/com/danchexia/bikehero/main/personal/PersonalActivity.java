package com.danchexia.bikehero.main.personal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.car.qijia.thinker.photo.view.ImageSelectorActivity;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.fixpower.FixPowerActivity;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/17.
 * description:个人信息
 */

public class PersonalActivity extends MvpActivity<PersonalPresenter,IPersonalView> implements IPersonalView, View.OnClickListener {
    private PersonalPresenter presenter;
    private ImageView head_left, head_right;
    private TextView head_title;
    private CircleImageView icon_my;
    private TextView nickName;
    private TextView phone;
    private TextView name;
    private TextView idetent;
    private TextView idetent_text;
    private RelativeLayout person_icon,person_nickName;
    private String headImgUrl = "";
    private String myNickName = "";
    private LinearLayout power;
    private TextView powerType;
    private LinearLayout ident_view;//认证界面
    private LinearLayout students;//学校
    private TextView  schoolname;
    @Override
    protected PersonalPresenter CreatePresenter() {
        return presenter = new PersonalPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initView();
        initData();
    }

    private void initView() {
        powerType = (TextView) findViewById(R.id.powerType);
        students = (LinearLayout) findViewById(R.id.students);
        power = (LinearLayout) findViewById(R.id.power);
        schoolname = (TextView) findViewById(R.id.schoolname);
        ident_view = (LinearLayout) findViewById(R.id.ident_view);
        person_icon = (RelativeLayout) findViewById(R.id.person_icon);
        person_nickName = (RelativeLayout) findViewById(R.id.person_nickName);
        icon_my = (CircleImageView) findViewById(R.id.icon_my);
        idetent = (TextView) findViewById(R.id.idetent);
        idetent_text = (TextView) findViewById(R.id.idetent_text);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        nickName = (TextView) findViewById(R.id.nickName);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title.setText(Utils.object2String(getString(R.string.person_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        person_icon.setOnClickListener(this);
        person_nickName.setOnClickListener(this);
        power.setOnClickListener(this);
        idetent.setOnClickListener(this);
        if (!Config.isNeadToRegrist){
            idetent_text.setText("工号");
        }
        if (Config.isNeedIdent){
            ident_view.setVisibility(View.VISIBLE);
        }else{
            ident_view.setVisibility(View.GONE);
        }
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null){
            if ("student_card".equals(systemParamsBean.getAuthenType())){
                students.setVisibility(View.VISIBLE);
            }
        }
    }
    private void initData() {
        PersonalBean bean = MyUtils.getPersonData();
        if (bean != null){
            if (bean.getAuthStatus().contentEquals(MyUtils.PERSONAL_AUTH_STATUS_ITEM2)){
                idetent.setText(getString(R.string.person_indented));
                idetent.setTextColor(getResources().getColor(R.color.color_deep_gray));
            }else{
                idetent.setText(getString(R.string.person_unindent));
                idetent.setTextColor(getResources().getColor(R.color.color_theme));
            }
            if (!Config.isNeadToRegrist){
                idetent.setText(Utils.object2String(bean.getJobNumber()));
            }
            if (!TextUtils.isEmpty(bean.getHeadImgPath())){
                Glide.with(this).load(bean.getHeadImgPath()).into(icon_my);
            }
            myNickName = bean.getNickname();
            if (!TextUtils.isEmpty(myNickName)){
                nickName.setText(Utils.object2String(myNickName));
            }
            phone.setText(Utils.object2String(bean.getMobile()));
            if (!TextUtils.isEmpty(bean.getName())){
                name.setText(Utils.object2String(bean.getName()));
            }
            if (bean.getMotorPower() != null){
                powerType.setText(Utils.object2String(bean.getMotorPower())+"W");
            }else{
                powerType.setText(getString(R.string.person_set_power_click));
            }
            motorPower = bean.getMotorPower();
        }
        if (Config.isOpenBattery){
            power.setVisibility(View.VISIBLE);
        }else{
            power.setVisibility(View.GONE);
        }
        if (Config.isNeedIdent){
            schoolname.setText(Utils.object2String(bean.getSchoolName()));
        }
    }
    private Integer motorPower;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.power:
                Intent fixpower = new Intent(PersonalActivity.this,FixPowerActivity.class);
                if (null != motorPower){
                    fixpower.putExtra("MOTORPOWER",motorPower);
                }
                startActivityForResult(fixpower,104);
                break;
            case R.id.head_left:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.idetent:
                PersonalBean bean = MyUtils.getPersonData();
                switch (bean.getAuthStep()) {
                    case 2:
                        ActivityController.startRecharge(this);
                        break;
                    case 3:
                        ActivityController.startIdentid(this);
                        break;
                    default:
                        break;
                }
                break;
            case R.id.person_icon:
                ImageSelectorActivity activty = new ImageSelectorActivity();
                activty.start(PersonalActivity.this, 1, ImageSelectorActivity.MODE_SINGLE, true,true,true);
                break;
            case R.id.person_nickName:
                Intent fix = new Intent(PersonalActivity.this,FixUserNameActivity.class);
                fix.putExtra("NICKNAME",myNickName);
                startActivityForResult(fix,103);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE){
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
            if (images.size()>0){
                LogUtils.d("images="+images.get(0));
                headImgUrl = images.get(0);
                Glide.with(this).load(headImgUrl).into(icon_my);
                Bitmap avatarBitMap= BitmapFactory.decodeFile(headImgUrl);
                presenter.upIcon(avatarBitMap);
            }
        }
        if(resultCode == RESULT_OK && requestCode == 103){
            if (data != null){
                String name = data.getStringExtra("NICKNAME");
                myNickName = name;
                nickName.setText(Utils.object2String(name));
            }
        }
        if(resultCode == RESULT_OK && requestCode == 104){
            if (data != null){
                String name = data.getStringExtra("POWERTYPE");
                powerType.setText(Utils.object2String(name)+"W");
            }
        }

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
