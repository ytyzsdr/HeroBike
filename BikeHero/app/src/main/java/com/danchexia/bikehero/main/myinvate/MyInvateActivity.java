package com.danchexia.bikehero.main.myinvate;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.InvateAndShateBean;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import vc.thinker.colours.client.ApiClient;
import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/18.
 * description:
 */

public class MyInvateActivity extends MvpActivity<MyInvatePresenter, IMyInvateView> implements IMyInvateView, View.OnClickListener {
    private MyInvatePresenter presenter;
    private ImageView head_left, head_right;
    private TextView head_title;
    private TextView my_invate_code;
    private TextView invateMoney1, invateMoney2;
    private TextView share;
    private LinearLayout layout_share;
    private ImageView head_icon;
    private InvateAndShateBean invateAndShare;
    private String inviteCode;//邀请码
    private LinearLayout shareQrCode;
    private ImageView qrCode;
    private String shareTile = "我的行程";
    private String shareContent = "我的行程";
    private ScrollView sv_invate;
    private ImageView iv_invate_bg,iv_invate_qrcode;
    private LinearLayout ll_share;

    @Override
    protected MyInvatePresenter CreatePresenter() {
        return presenter = new MyInvatePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invate);
        initView();
        initData();
        invateAndShare = MyUtils.getInvateAndShareData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getInvateMoney();
        initQrCodeView();
    }
    public void initQrCodeView() {
        if (share != null && shareQrCode != null) {
            //显示时，分享按钮和背景显示，二维码和二维码背景隐藏
            ll_share.setVisibility(View.VISIBLE);
            iv_invate_bg.setVisibility(View.VISIBLE);
            iv_invate_qrcode.setVisibility(View.GONE);
            shareQrCode.setVisibility(View.GONE);
            isShow = true;
        }
    }

    private void initView() {
        shareQrCode = (LinearLayout) findViewById(R.id.shareQrCode);
        qrCode = (ImageView) findViewById(R.id.qrCode);
        layout_share = (LinearLayout) findViewById(R.id.layout_share);
        share = (TextView) findViewById(R.id.share);

        //新增控件
        ll_share = (LinearLayout) findViewById(R.id.ll_share);
        iv_invate_bg = (ImageView) findViewById(R.id.iv_invate_bg);//邀请页背景
        iv_invate_qrcode = (ImageView) findViewById(R.id.iv_invate_qrcode);//邀请页二维码背景

        //滚动条
        sv_invate = (ScrollView) findViewById(R.id.sv_invate);
        //head_icon = (ImageView) findViewById(R.id.head_icon);
        //invateMoney1 = (TextView) findViewById(R.id.invateMoney1);
        invateMoney2 = (TextView) findViewById(R.id.invateMoney2);
        my_invate_code = (TextView) findViewById(R.id.my_invate_code);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title.setText(Utils.object2String(getString(R.string.my_invate_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        share.setOnClickListener(this);
        share.setVisibility(View.VISIBLE);
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null && !TextUtils.isEmpty(systemParamsBean.getAppDownloadUrl())) {
            String imgUrl = systemParamsBean.getAppDownloadUrl();
            Bitmap bitLogo = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
            Bitmap bitmap = CodeUtils.createImage(imgUrl, DisplayUtil.dip2px(this,130.0f), DisplayUtil.dip2px(this,130.0f), bitLogo);
            qrCode.setImageBitmap(bitmap);
        }
        shareTile = getString(R.string.toast_6);
        shareContent = getString(R.string.toast_6);
        if (!Config.isNeadToInvate) {
            share.setVisibility(View.GONE);
        }
    }

    private void initData() {
        PersonalBean bean = MyUtils.getPersonData();
        if (bean != null) {
            inviteCode = bean.getInviteCode();
            my_invate_code.setText(inviteCode);
        }
    }

    public void setInvateMoney(BaseBean bean) {
        //invateMoney1.setText(getString(R.string.my_invate_text1));
        invateMoney2.setText(bean.getResult());
    }

    boolean isShow = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left:
                finish();
                break;
            case R.id.share:
                isShow = false;
                if (invateAndShare != null) {
                    shareTile = invateAndShare.getInviteTitle();
                    shareContent = invateAndShare.getInviteContent();
                }
                MyUtils.myShareDialog(false, this, new MyUtils.ShareCancelListener() {
                    @Override
                    public void cancel() {
                        share.setVisibility(View.VISIBLE);
                        shareQrCode.setVisibility(View.GONE);
                        isShow = true;
                    }

                    @Override
                    public void mySelect(int pos) {
                        if (pos == 0) {
                            MyUtils.shareQzone(MyInvateActivity.this, shareTile, ApiClient.baseUrl + "share/invite_share?inviteCode=" + inviteCode, shareContent);
                        } else if (pos == 1) {
                            MyUtils.shareQQ(MyInvateActivity.this, shareTile, ApiClient.baseUrl + "share/invite_share?inviteCode=" + inviteCode, shareContent);
                        } else if (pos == 2) {
                            //微信分享时，分享按钮和背景隐藏，二维码和二维码背景显示
                            ll_share.setVisibility(View.GONE);
                            iv_invate_bg.setVisibility(View.GONE);
                            shareQrCode.setVisibility(View.VISIBLE);
                            iv_invate_qrcode.setVisibility(View.VISIBLE);

                            sv_invate.post(new Runnable() {
                                @Override
                                public void run() {
                                    //使滚动条滚动到顶端
                                    sv_invate.fullScroll(ScrollView.FOCUS_UP);
                                }
                            });

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MyUtils.showMoment(MyInvateActivity.this, layout_share);
                                }
                            }, 1000);

                        } else if (pos == 3) {
                            MyUtils.showWechat(MyInvateActivity.this, shareTile, ApiClient.baseUrl + "share/invite_share?inviteCode=" + inviteCode, shareContent);
                        }
                        isShow = true;
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (isShow) {
            finish();
        }
    }
}
