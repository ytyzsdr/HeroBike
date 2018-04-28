package com.danchexia.bikehero.main.my;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.member.RechartMemberActivity;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.main.my.fragment.CertifacationFragment;
import com.danchexia.bikehero.main.my.fragment.PersonalDataFragment;
import com.danchexia.bikehero.main.personal.PersonalActivity;
import com.danchexia.bikehero.utils.MyUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/16.
 * description:
 */

public class MyActivity extends MvpActivity<MyPresenter,IMyView> implements IMyView, View.OnClickListener {
    private MyPresenter presenter;
    private ImageView head_left,head_right;
    private TextView head_title;
    private TextView userName;
    private CircleImageView icon_my;
    private RelativeLayout head_modify;
    private RelativeLayout  my_stroke,my_box,my_offer,my_invate,my_guide,my_set,my_aboutus,my_vip,my_message;
    private PersonalDataFragment personalDataFragment;
    private CertifacationFragment certifacationFragment;
    private FragmentManager fragmentManager;
    private String serviceCenterPhone;//客服电话
    private View line_1,line_2,line_3,line_4,line_8;
    private LinearLayout vip_ll;
    private TextView vip_text;
    private boolean isVip = false;
    private String remainDate;
    private long remainDateLong;
    private LinearLayout useStatus;
    @Override
    protected MyPresenter CreatePresenter() {
        return presenter = new MyPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        PersonalBean personalBean = MyUtils.getPersonData();
        initData(personalBean);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Config.toRefreshData) {
            presenter.getMyData();
            Config.toRefreshData = false;
        }
    }

    public  void initData(PersonalBean bean) {
        if (bean != null){
            if (bean.getAuthStatus().contentEquals(MyUtils.PERSONAL_AUTH_STATUS_ITEM2)){
                initPersonalFragment();
            }else{
                Config.toRefreshData = true;
                initCertifacationFragment();
            }
            if (!TextUtils.isEmpty(bean.getHeadImgPath())){
                Glide.with(this).load(bean.getHeadImgPath()).into(icon_my);
            }
            userName.setText(vc.thinker.tools.utils.Utils.object2String(bean.getNickname()));
            if (bean.getVIP()){
                vip_text.setSelected(true);
                if (bean.getVipExpiresIn() != null) {
                    remainDate = bean.getVipDxpireDateDesc();
                    remainDateLong = bean.getVipExpiresIn().getTime();
                    vip_text.setText("会员有效期" + Utils.stampToDate3(bean.getVipExpiresIn().getTime()));
                    isVip = true;
                }
                vip_ll.setSelected(true);
            }else{
                vip_text.setSelected(false);
                vip_text.setText("加入会员");
                isVip = false;
                vip_ll.setSelected(false);
            }
        }
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null){
            serviceCenterPhone = systemParamsBean.getContactMobile();
            contectMobile = systemParamsBean.getContactMobile();
        }

    }
    private String contectMobile;
    private void initView() {
        icon_my = (CircleImageView) findViewById(R.id.icon_my);
        head_modify = (RelativeLayout) findViewById(R.id.head_modify);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title = (TextView) findViewById(R.id.head_title);
        userName = (TextView) findViewById(R.id.userName);
        vip_text = (TextView) findViewById(R.id.vip_text);
        my_stroke = (RelativeLayout) findViewById(R.id.my_stroke);
        my_box = (RelativeLayout) findViewById(R.id.my_box);
        my_offer = (RelativeLayout) findViewById(R.id.my_offer);
        my_invate = (RelativeLayout) findViewById(R.id.my_invate);
        my_guide = (RelativeLayout) findViewById(R.id.my_guide);
        my_set = (RelativeLayout) findViewById(R.id.my_set);
        my_aboutus = (RelativeLayout) findViewById(R.id.my_aboutus);
        my_message = (RelativeLayout) findViewById(R.id.my_message);
        vip_ll = (LinearLayout) findViewById(R.id.vip_ll);
        useStatus = (LinearLayout) findViewById(R.id.useStatus);
        my_vip = (RelativeLayout) findViewById(R.id.my_vip);
        line_1 = (View) findViewById(R.id.line_1);
        line_2 = (View) findViewById(R.id.line_2);
        line_3 = (View) findViewById(R.id.line_3);
        line_4 = (View) findViewById(R.id.line_4);
        line_8 = (View) findViewById(R.id.line_8);
        head_right.setVisibility(View.GONE);
        head_title.setText(getString(R.string.my_title));
        head_left.setOnClickListener(this);
        head_modify.setOnClickListener(this);
        my_stroke.setOnClickListener(this);
        my_box.setOnClickListener(this);
        my_offer.setOnClickListener(this);
        my_invate.setOnClickListener(this);
        my_guide.setOnClickListener(this);
        my_set.setOnClickListener(this);
        my_aboutus.setOnClickListener(this);
        my_vip.setOnClickListener(this);
        my_message.setOnClickListener(this);
        vip_ll.setOnClickListener(this);
        if (!Config.isNeadToPay){
            line_1.setVisibility(View.GONE);
            line_2.setVisibility(View.GONE);
            line_3.setVisibility(View.GONE);
            my_box.setVisibility(View.GONE);
            my_offer.setVisibility(View.GONE);
        }
        if (Config.isNeadToInvate){
            my_invate.setVisibility(View.VISIBLE);
        }else{
            my_invate.setVisibility(View.GONE);
            line_3.setVisibility(View.GONE);
            line_4.setVisibility(View.GONE);
        }
        if (Config.isOpenVip){
            vip_ll.setVisibility(View.VISIBLE);
            my_vip.setVisibility(View.VISIBLE);
            line_8.setVisibility(View.VISIBLE);
        }else{
            vip_ll.setVisibility(View.GONE);
            my_vip.setVisibility(View.GONE);
            line_8.setVisibility(View.GONE);
        }
        if (Config.isOpenBattery){
            useStatus.setVisibility(View.GONE);
        }
    }

    /**
     * 加载个人数据
     */
    public void initPersonalFragment(){
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        personalDataFragment = new PersonalDataFragment();
        transaction.replace(R.id.auth_data, personalDataFragment);
        // 事务提交
        transaction.commit();
    }
    /**
     * 加载认证流程
     */
    public void initCertifacationFragment(){
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        certifacationFragment = new CertifacationFragment(true);
        transaction.replace(R.id.auth_data, certifacationFragment);
        // 事务提交
        transaction.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_left:
                finish();
                break;
            case R.id.head_modify://头像
                Intent modify = new Intent(this, PersonalActivity.class);
                startActivityForResult(modify,203);
                break;
            case R.id.my_stroke://我的行程
                ActivityController.startMyStroke(this);
                break;
            case R.id.my_box://我的钱包
                if (Config.isNeedOpenBalance){
                    ActivityController.startMyNewWallet(this);
                }else{
                    ActivityController.startMywallet(this);
                }
                break;
            case R.id.my_offer://我的优惠
                ActivityController.startMyOffer(this,false);
                break;
            case R.id.my_invate://邀请好友
                PersonalBean bean = MyUtils.getPersonData();
                if (bean.getAuthStatus().contentEquals("2")) {
                    ActivityController.startMyInvate(this);
                }else{
                    showErrorNone();
                }
                break;
            case R.id.my_guide://指南
                ActivityController.startUserGuide(this);

                break;
            case R.id.my_set://设置
                ActivityController.startSet(this);
                break;
            case R.id.my_aboutus://联系我们
                if (TextUtils.isEmpty(contectMobile)){
                    ShowToast.show(this,"后台没有设置此联系号码");
                }else {
                    vc.thinker.tools.utils.Utils.callPhone(this, contectMobile);
                }
                break;
            case R.id.my_vip:
                Intent guide = new Intent(this, RechartMemberActivity.class);
                guide.putExtra("ISVIP",isVip);
                guide.putExtra("REMAINDATE",remainDate);
                guide.putExtra("REMAINDATELONG",remainDateLong);
                startActivityForResult(guide,204);
                break;
            case R.id.my_message:
                ActivityController.startMessage(this);
                break;
            case R.id.vip_ll:
                Intent vip = new Intent(this, RechartMemberActivity.class);
                vip.putExtra("ISVIP",isVip);
                vip.putExtra("REMAINDATE",remainDate);
                vip.putExtra("REMAINDATELONG",remainDateLong);
                startActivityForResult(vip,204);
                break;
            default:
                break;
        }
    }
    private void showErrorNone(){
//        StanderdDialog.getInstatnce(MyActivity.this, getString(R.string.my_toast1), getString(R.string.my_toast2), new StanderdDialog.OnDialogClickListener() {
//            @Override
//            public void doAnyClick() {
//
//            }
//
//            @Override
//            public void doMainClick() {
//            }
//        }).show();
        final StanderdDialog dialog = new StanderdDialog(MyActivity.this, getString(R.string.my_toast1), getString(R.string.my_toast2),
                new StanderdDialog.OnDialogClickListener() {
                    @Override
                    public void doAnyClick() {
                    }

                    @Override
                    public void doMainClick() {
                    }
                });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 203 || requestCode == 204) {
                presenter.getMyData();
            }
        }
    }
}