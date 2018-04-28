package com.danchexia.bikehero.main.rideover;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.bean.UserAmount;
import com.danchexia.bikehero.main.member.RechartMemberActivity;
import com.danchexia.bikehero.main.myoffer.MyOfferActivity;
import com.danchexia.bikehero.service.RideStatusService;
import com.danchexia.bikehero.utils.MyUtils;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/18.
 * description:骑行结束
 */

public class RideOverActivity extends MvpActivity<RideOverPresenter, IRideOverView> implements IRideOverView, View.OnClickListener {
    private RideOverPresenter presenter;
    private ImageView head_left, head_right;
    private TextView head_title;
    private TextView number;//车编号
    private TextView ride_money;//骑行费用
    private TextView ride_time;//骑行时间
    private TextView my_offer_detail;//我的优惠详情
    private RelativeLayout my_offer;//我的优惠
    private RelativeLayout ride_detail;//骑行详情
    private RelativeLayout wx_pay;//微信支付
    private RelativeLayout alipay_pay;//支付宝支付
    private ImageView imageView3;//微信对勾
    private ImageView alipay_fill;//支付宝对勾
    private TextView pay_money;//待支付
    //    private TextView offer_e;//优惠
    private LinearLayout pay;//支付
    private LinearLayout layoutOpenMember;
    private TextView tvOpenMember;
    private String payType = MyUtils.PAY_TYPE_WXPAY;//支付方式.默认微信
    private Long tid;
    private Long cid=-1L;
    private RideStatusService.MyBinder myBinder;
    private View view_line;
    private Double mPrice = 0d;
    private ImageView help;
    private TextView myAmount;
    private String sysCode;
    private RelativeLayout openVip;
    private RelativeLayout myWallet;
    private LinearLayout ll_myWallet;
    private Long myTripId;
    private Double needPayMoney = 0d;
    private LinearLayout pay_ll;
    private View pay_line_view;
    private View pay_line_view_1;
    private View walletLine;
    private View wallet_li;
    private boolean hasOffer=false;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (RideStatusService.MyBinder) service;
            myBinder.stopCheckStatus();
        }
    };

    @Override
    protected RideOverPresenter CreatePresenter() {
        return presenter = new RideOverPresenter(this);
    }
    private boolean isRegistService = false;//服务是否注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_over);
        Intent sst = new Intent();
        sst = getIntent();
        if (sst != null) {
            myTripId = sst.getLongExtra("USERTRIPID", 0);
        }
        initView();
        openMemberTop();
        if (isRegistService) {
            isRegistService = true;
            ActivityController.startRideStatusService(this);
            ActivityController.bindRideStatusService(this, connection);
        }
        if (myTripId > 0) {
            presenter.profileUsing(myTripId);
        } else {
            presenter.getNotPayTrip();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.isNeedOpenBalance) {
            presenter.getMyAmount();
        }
    }

    private void initView() {
        pay_ll = (LinearLayout) findViewById(R.id.pay_ll);
        wallet_li = (View) findViewById(R.id.wallet_li);
        pay_line_view_1 = (View) findViewById(R.id.pay_line_view_1);
        pay_line_view = (View) findViewById(R.id.pay_line_view);
        view_line = (View) findViewById(R.id.view_line);
        help = (ImageView) findViewById(R.id.help);
        myAmount = (TextView) findViewById(R.id.myAmount);
        pay = (LinearLayout) findViewById(R.id.pay);
        ll_myWallet = (LinearLayout) findViewById(R.id.ll_myWallet);
        ride_time = (TextView) findViewById(R.id.ride_time);
        my_offer_detail = (TextView) findViewById(R.id.my_offer_detail);
        pay_money = (TextView) findViewById(R.id.pay_money);
        alipay_fill = (ImageView) findViewById(R.id.alipay_fill);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        alipay_pay = (RelativeLayout) findViewById(R.id.alipay_pay);
        ride_detail = (RelativeLayout) findViewById(R.id.ride_detail);
        myWallet = (RelativeLayout) findViewById(R.id.myWallet);
        wx_pay = (RelativeLayout) findViewById(R.id.wx_pay);
        openVip = (RelativeLayout) findViewById(R.id.openVip);
        alipay_pay = (RelativeLayout) findViewById(R.id.alipay_pay);
        my_offer = (RelativeLayout) findViewById(R.id.my_offer);
        ride_money = (TextView) findViewById(R.id.ride_money);
        number = (TextView) findViewById(R.id.number);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        layoutOpenMember = (LinearLayout) findViewById(R.id.layout_open_member);
        tvOpenMember = (TextView) findViewById(R.id.tv_open_member);

        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        pay.setOnClickListener(this);
        wx_pay.setOnClickListener(this);
        alipay_pay.setOnClickListener(this);
        ride_detail.setOnClickListener(this);
        my_offer.setOnClickListener(this);
        help.setOnClickListener(this);
        myAmount.setOnClickListener(this);
        tvOpenMember.setOnClickListener(this);
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null){//如果是电池就去修改标题
            if (systemParamsBean.getOpenBattery()){
                head_title.setText(Utils.object2String(getString(R.string.ride_title_battery)));
            }else{
                head_title.setText(Utils.object2String(getString(R.string.ride_title)));
            }
        }
        if (systemParamsBean != null && Config.isNeadToPay) {
            if (systemParamsBean.getPayWay().contentEquals("1")) {
                wx_pay.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                alipay_fill.setVisibility(View.GONE);
                payType = MyUtils.PAY_TYPE_WXPAY;
            } else if (systemParamsBean.getPayWay().contentEquals("2")) {
                alipay_pay.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.GONE);
                alipay_fill.setVisibility(View.VISIBLE);
                payType = MyUtils.PAY_TYPE_ALIPAY;
            } else if (systemParamsBean.getPayWay().contentEquals("1,2")) {
                view_line.setVisibility(View.VISIBLE);
                wx_pay.setVisibility(View.VISIBLE);
                alipay_pay.setVisibility(View.VISIBLE);
            }
        }else {
            pay_line_view_1.setVisibility(View.GONE);
        }
//        20170922易晓梅决定 不隐藏了
//        if (Config.isOpenVip) {
//            help.setVisibility(View.GONE);
//        } else {
//            help.setVisibility(View.VISIBLE);
//        }
        if (!Config.isNeedOpenBalance) {
            ll_myWallet.setVisibility(View.GONE);
            walletLine.setVisibility(View.GONE);
            wallet_li.setVisibility(View.GONE);
        } else {
            pay_ll.setVisibility(View.GONE);//
            pay_line_view.setVisibility(View.GONE);//
            pay_line_view_1.setVisibility(View.GONE);//
            presenter.getMyAmount();
            payType = "balance";
        }
    }

    public void setData(OnGoing_TripBO notPayRideBean) {
        PreferencesUtils.putLong(MyApplication.appContext, Config.USERTRIPID, 0);
        sysCode = notPayRideBean.getSysCode();
        number.setText("NO." + notPayRideBean.getSysCode());
        if (MyUtils.getPersonData().getVIP()){
//            会员价格
            ride_money.setText(notPayRideBean.getVipPrice()+"");
        }else {
//            非会员价格
            ride_money.setText(notPayRideBean.getPrice()+"");
        }

        ride_time.setText(notPayRideBean.getRideTime() + "");
        if (notPayRideBean.getFitCoupon() == null) {
            hasOffer=false;
            //如果没有优惠券
            if (MyUtils.getPersonData().getVIP()){
                needPayMoney = notPayRideBean.getVipPrice();
                my_offer_detail.setText("会员优惠");
                pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) +"<font color=\"#ff3e00\">"+ " ￥"+needPayMoney));
            }else {
                needPayMoney = notPayRideBean.getPrice();
                my_offer_detail.setText(getString(R.string.ride_no_offer));
                pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+needPayMoney));
            }
        } else {
//            有优惠券的情况
            hasOffer=true;
            my_offer_detail.setText("-¥" + notPayRideBean.getFitCoupon().getAmount());
            my_offer_detail.setTextColor(getResources().getColor(R.color.color_tip_red));
            cid = notPayRideBean.getFitCoupon().getId();
//            mPrice = notPayRideBean.getPrice();
            if (MyUtils.getPersonData().getVIP()){
//                会员
                mPrice = notPayRideBean.getVipPrice();
                needPayMoney = mPrice - notPayRideBean.getFitCoupon().getAmount();
            }else {
//                非会员
                mPrice = notPayRideBean.getPrice();
                needPayMoney = mPrice - notPayRideBean.getFitCoupon().getAmount();
            }
            if (needPayMoney>0){
                pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+needPayMoney));
            }else {
                pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+0));
            }
        }
        tid = notPayRideBean.getId();
        int myStatus = notPayRideBean.getStatus();
        if (myStatus == 40) {
            openVip.setVisibility(View.VISIBLE);
        } else {
            openVip.setVisibility(View.GONE);
        }
        if (notPayRideBean.getPayType().equals("free")) {
            my_offer_detail.setText("免费");
            ll_myWallet.setVisibility(View.GONE);
            ride_money.setVisibility(View.INVISIBLE);
            walletLine.setVisibility(View.GONE);
            wallet_li.setVisibility(View.GONE);
        } else if (notPayRideBean.getPayType().equals("vip")) {
        }
        if (Config.isNeedOpenBalance) {
            presenter.getMyAmount();
        }
    }

    public void setMoneyData(UserAmount bean) {
        if (bean.getAmount() != null) {
            LogUtils.d("needPayMoney======"+needPayMoney);
            if (bean.getAmount() >= needPayMoney) {
                myAmount.setText("¥" + Utils.object2String(bean.getAmount()));
                myAmount.setTextColor(getResources().getColor(R.color.color_deep_gray));
                pay.setEnabled(true);
            } else {
                myAmount.setText(getString(R.string.rideover_money_empty));
                pay.setEnabled(false);//改成余额不足时可以直接使用支付宝或微信支付
                myAmount.setTextColor(getResources().getColor(R.color.color_tip_red));
            }
        }

    }

    public void getTripDetail(OnGoing_TripBO onGoing_tripBO) {
        PreferencesUtils.putLong(MyApplication.appContext, Config.USERTRIPID, 0);
        sysCode = onGoing_tripBO.getSysCode();
        number.setText("NO." + onGoing_tripBO.getSysCode());
        if (MyUtils.getPersonData().getVIP()){
            ride_money.setText(onGoing_tripBO.getVipPrice()+"");
        }else {
            ride_money.setText(onGoing_tripBO.getPrice()+"");
        }

        ride_time.setText(onGoing_tripBO.getRideTime()+"");
        if (onGoing_tripBO.getFitCoupon() == null) {
            //如果没有优惠券
            hasOffer=false;
            if (MyUtils.getPersonData().getVIP()){
//                会员
                needPayMoney = onGoing_tripBO.getVipPrice();
                my_offer_detail.setText("会员优惠");
                pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+needPayMoney));
            }else {
//                非会员
                needPayMoney = onGoing_tripBO.getPrice();
                my_offer_detail.setText(getString(R.string.ride_no_offer));
                pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+needPayMoney));
            }

        } else {
//            有优惠券的情况下
            hasOffer=true;
            my_offer_detail.setText("-¥" + onGoing_tripBO.getFitCoupon().getAmount());
            my_offer_detail.setTextColor(getResources().getColor(R.color.color_tip_red));
            cid = onGoing_tripBO.getFitCoupon().getId();

            if (MyUtils.getPersonData().getVIP()){
                mPrice = onGoing_tripBO.getVipPrice();
                needPayMoney = mPrice - onGoing_tripBO.getFitCoupon().getAmount();
            }else {
                mPrice = onGoing_tripBO.getPrice();
                needPayMoney = mPrice - onGoing_tripBO.getFitCoupon().getAmount();
            }
            if (needPayMoney>0){
                pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+needPayMoney));
            }else {
                pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+0));
            }
        }
        tid = onGoing_tripBO.getId();
        int myStatus = onGoing_tripBO.getStatus();
        if (myStatus == 40) {
            openVip.setVisibility(View.VISIBLE);
        } else {
            openVip.setVisibility(View.GONE);
        }
        if (onGoing_tripBO.getPayType().equals("free")) {
            my_offer_detail.setText("免费");
            ride_money.setVisibility(View.INVISIBLE);
            ll_myWallet.setVisibility(View.GONE);
            walletLine.setVisibility(View.GONE);
            wallet_li.setVisibility(View.GONE);
        } else if (onGoing_tripBO.getPayType().equals("vip")) {
        }
        if (Config.isNeedOpenBalance) {
            presenter.getMyAmount();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.help:
                ActivityController.startFeedBack(RideOverActivity.this, "3", sysCode, tid);
                break;
            case R.id.head_left:
                finish();
                break;
            case R.id.my_offer:
                if (hasOffer){
                    Intent offer = new Intent(RideOverActivity.this, MyOfferActivity.class);
                    offer.putExtra("TYPE", true);
                    startActivityForResult(offer, 104);
                }

                break;
            case R.id.ride_detail:
                ActivityController.startStrokeDetail(this, tid);
                break;
            case R.id.pay:
                if (cid==-1){
                    presenter.ridePayNo(tid, payType);
                }else {
                    presenter.ridePay(tid, cid, payType);
                }
                break;
            case R.id.wx_pay:
                imageView3.setVisibility(View.VISIBLE);
                alipay_fill.setVisibility(View.GONE);
                payType = MyUtils.PAY_TYPE_WXPAY;
                break;
            case R.id.alipay_pay:
                imageView3.setVisibility(View.GONE);
                alipay_fill.setVisibility(View.VISIBLE);
                payType = MyUtils.PAY_TYPE_ALIPAY;
                break;
            case R.id.myAmount:
                ActivityController.startMyNewWallet(this);
                break;
            case R.id.tv_open_member:
                Intent guide = new Intent(this, RechartMemberActivity.class);
                guide.putExtra("ISVIP",false);
                guide.putExtra("REMAINDATE","");
                guide.putExtra("REMAINDATELONG","");
                startActivityForResult(guide,204);
                break;
            default:
                break;
        }
    }

    /**
     * 支付成功之后 把轮询给干掉
     */
    public void stopService() {
        if (isRegistService) {
            ActivityController.unbindRideStatusService(this, connection);
            ActivityController.stopRideStatusService(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == 104) {
                if (data != null) {
                    cid = data.getLongExtra("CID", -1L);
                    Double pPrice = data.getDoubleExtra("MONEY", 0);
                    if (cid==-1){
                        my_offer_detail.setText("不使用");
                        my_offer_detail.setTextColor(getResources().getColor(R.color.color_a5a5a5));
                    }else {
                        my_offer_detail.setText("-¥" + pPrice);
                        my_offer_detail.setTextColor(getResources().getColor(R.color.color_tip_red));
                    }

//                    offer_e.setText(getString(R.string.ride_offer) + pPrice);
                    if (pPrice >= mPrice) {
                        needPayMoney = 0d;
                        pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+0));
                    } else {
                        needPayMoney = mPrice - pPrice;
                        pay_money.setText(Html.fromHtml(getString(R.string.ride_pay_money) + "<font color=\"#ff3e00\">"+ " ￥"+needPayMoney));
                    }
                    presenter.getMyAmount();
                }
            }else if (requestCode == 204){
                presenter.getMyData();
            }
        }
    }


    public void openMemberTop() {
        if (MyUtils.getPersonData() != null && MyUtils.getPersonData().getVIP()){
            layoutOpenMember.setVisibility(View.GONE);
        }else {
            layoutOpenMember.setVisibility(View.VISIBLE);
        }
    }


}
