package com.danchexia.bikehero.main.recharge;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.main.bean.RechartTypeListBean;
import com.danchexia.bikehero.main.newwallet.adapter.RechartViewAdapter;
import com.danchexia.bikehero.pay.OnPayListener;
import com.danchexia.bikehero.pay.PayAgent;
import com.danchexia.bikehero.pay.bean.PayDetails;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.my.fragment.CertifacationFragment;
import com.danchexia.bikehero.main.recharge.bean.ChergeBean;
import com.danchexia.bikehero.utils.MyUtils;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/17.
 * description:押金充值
 */

public class RechargeActivity extends MvpActivity<RechargePresenter, IRechargeView> implements IRechargeView, View.OnClickListener {
    private RechargePresenter presenter;
    private CertifacationFragment certifacationFragment;
    private FragmentManager fragmentManager;
    private ImageView head_left, head_right;
    private TextView head_title;
    private TextView wx_app, alipay_app;//支付方式
    private GridView mGridView;
    private RechartTypeListBean mBean;
    private Long payMoney;
    private RechartViewAdapter adapter;

    @Override
    protected RechargePresenter CreatePresenter() {
        return presenter = new RechargePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initView();
        initCertifacationFragment();
        initData();
        initListener();
    }

    private void initListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(adapter != null && mBean.getDataList().size() > 0){
                    adapter.setMySelected(position);
                    payMoney = mBean.getDataList().get(position).getId();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        presenter.getCherge();
        presenter.getRechartType();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEnableSuccess();
    }

    private void initView() {
        mGridView= (GridView) findViewById(R.id.myGridview);
        alipay_app = (TextView) findViewById(R.id.alipay_app);
        wx_app = (TextView) findViewById(R.id.wx_app);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title.setText(Utils.object2String(getString(R.string.charge_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        wx_app.setOnClickListener(this);
        alipay_app.setOnClickListener(this);
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null) {
            if (systemParamsBean.getPayWay().contentEquals("1")) {
                wx_app.setVisibility(View.VISIBLE);

            } else if (systemParamsBean.getPayWay().contentEquals("2")) {
                alipay_app.setVisibility(View.VISIBLE);
            } else if (systemParamsBean.getPayWay().contentEquals("1,2")) {
                wx_app.setVisibility(View.VISIBLE);
                alipay_app.setVisibility(View.VISIBLE);
            }
        }
    }

    public void initData(RechartTypeListBean rechartTypeListBean) {
        mBean = rechartTypeListBean;
        if (mBean.getDataList() != null) {
            adapter = new RechartViewAdapter(this, rechartTypeListBean.getDataList());
            mGridView.setAdapter(adapter);
            if (mBean.getDataList().size() > 0) {
                payMoney = mBean.getDataList().get(0).getId();
            }
        }
    }

    private void initCertifacationFragment() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        certifacationFragment = new CertifacationFragment(false);
        transaction.replace(R.id.certifacation, certifacationFragment);
        // 事务提交
        transaction.commit();
    }

    private void initData() {
        SystemParamsBean bean = MyUtils.getSystemData();
        if (bean != null) {

            if (bean.getPayWay().contentEquals("1")) {
                wx_app.setVisibility(View.VISIBLE);
                alipay_app.setVisibility(View.GONE);
            } else if (bean.getPayWay().contentEquals("2")) {
                wx_app.setVisibility(View.GONE);
                alipay_app.setVisibility(View.VISIBLE);
            } else if (bean.getPayWay().contentEquals("1,2")) {
                wx_app.setVisibility(View.VISIBLE);
                alipay_app.setVisibility(View.VISIBLE);
            }
        }
    }



    public void setBtnClick() {
        wx_app.setEnabled(true);
        alipay_app.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left:
                finish();
                break;
            case R.id.wx_app:
                presenter.recharge(payMoney,MyUtils.PAY_TYPE_WXPAY);
                wx_app.setEnabled(false);
                alipay_app.setEnabled(false);
                break;
            case R.id.alipay_app:
                presenter.recharge(payMoney,MyUtils.PAY_TYPE_ALIPAY);
                wx_app.setEnabled(false);
                alipay_app.setEnabled(false);
                break;
            default:
                break;
        }

    }
    public void setEnableSuccess(){
        wx_app.setEnabled(true);
        alipay_app.setEnabled(true);
    }
    public void doAlipay(final PayDetails payDetails) {
        PayAgent.getInstance().onAliPay(RechargeActivity.this, payDetails.getAlipaPpaySignature(),
                new OnPayListener() {
                    @Override
                    public void onPaySuccess() {
                        ShowToast.show(RechargeActivity.this, "支付成功");
                        ActivityController.startIdentid(RechargeActivity.this);
                        finish();
                    }

                    @Override
                    public void onPayFail(String code, String msg) {
                        ShowToast.show(RechargeActivity.this, "支付失败");
                    }
                });


    }
}
