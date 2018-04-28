package com.danchexia.bikehero.main.newwallet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.bean.UserAmount;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.main.recharge.bean.ChergeBean;
import com.danchexia.bikehero.main.wallet.dialog.DepositDialog;
import com.danchexia.bikehero.main.wallet.dialog.RechargeDialog;
import com.danchexia.bikehero.utils.MyUtils;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.mvp.MvpView;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/8/21.
 * description:
 */

public class MyWalletActivity extends MvpActivity<MyWalletPresenter, MvpView> implements MvpView, View.OnClickListener {
    private MyWalletPresenter myPresenter;

    @Override
    protected MyWalletPresenter CreatePresenter() {
        return myPresenter = new MyWalletPresenter(this);
    }

    private ImageView head_left;
    private TextView head_right;
    private TextView rechart;
    private TextView amount;
    private TextView alreadyAmount;
    private TextView btn_deposit;
    private boolean isRecharge;//是否充值了押金
    private SystemParamsBean systemBean;
    private Double myCherge = 0d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        initView();
        systemBean = MyUtils.getSystemData();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        myPresenter.getMyAmount();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myPresenter.getMyData();
        myPresenter.getCherge();
    }

    private void initView() {
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (TextView) findViewById(R.id.head_right);
        rechart = (TextView) findViewById(R.id.rechart);
        amount = (TextView) findViewById(R.id.amount);
        alreadyAmount = (TextView) findViewById(R.id.alreadyAmount);
        btn_deposit = (TextView) findViewById(R.id.btn_deposit);
        head_left.setOnClickListener(this);
        head_right.setOnClickListener(this);
        rechart.setOnClickListener(this);
        btn_deposit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left:
                finish();
                break;
            case R.id.head_right:
                ActivityController.startRechartHistory(this);
                break;
            case R.id.rechart:
                ActivityController.startToRecharge(this);
                break;
            case R.id.btn_deposit:
                if (isRecharge) {//已经充值了 走退押金流程
                    showRefoundDialog();
                } else {//未充值，走充值流程
                    if (systemBean.getPayWay().contentEquals("1")) {
                        myPresenter.recharge(MyUtils.PAY_TYPE_WXPAY);
                    } else if (systemBean.getPayWay().contentEquals("2")) {
                        myPresenter.recharge(MyUtils.PAY_TYPE_ALIPAY);
                    } else if (systemBean.getPayWay().contentEquals("1,2")) {
                        showRechargeDialog();
                    }
                }

                break;
            default:
                break;
        }
    }
    /**
     * 退押金
     */
    private void showRefoundDialog() {
        DepositDialog dialog = new DepositDialog(MyWalletActivity.this, new DepositDialog.OnDialogClickListener() {

            @Override
            public void onClick() {
                myPresenter.refundDeposit();
            }
        });
        dialog.show();
    }
    /**
     * 充值押金
     */
    private void showRechargeDialog() {
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null) {
            if (systemParamsBean.getPayWay().contentEquals("1")) {
                myPresenter.recharge(MyUtils.PAY_TYPE_WXPAY);
            } else if (systemParamsBean.getPayWay().contentEquals("2")) {
                myPresenter.recharge(MyUtils.PAY_TYPE_ALIPAY);
            } else if (systemParamsBean.getPayWay().contentEquals("1,2")) {


                RechargeDialog dialog = new RechargeDialog(myCherge,MyWalletActivity.this, new RechargeDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(int type) {
                        if (type == 0) {
                            myPresenter.recharge(MyUtils.PAY_TYPE_WXPAY);
                        } else if (type == 1) {
                            myPresenter.recharge(MyUtils.PAY_TYPE_ALIPAY);
                        }
                    }
                });
                dialog.show();
            }
        }
    }
    public void setData(UserAmount bean) {
        amount.setText(Utils.object2String(bean.getAmount()));
    }
    //获取到了要充值的押金金额
    public void setCherge(ChergeBean chergeBean) {
        if (chergeBean.getCherge() != null) {
            myCherge = chergeBean.getCherge();
        }
    }
    //获取个人资料
    public void initData(PersonalBean bean) {
        if (bean != null) {
            amount.setText(Utils.object2String(bean.getBalance()));
            alreadyAmount.setText(Utils.object2String(bean.getDeposit()));
            if (bean.getDeposit() != null && bean.getDeposit() > 0) {
                btn_deposit.setText(getString(R.string.wallet_dialog_btn));
                isRecharge = true;
            } else {
                alreadyAmount.setText("0");
                btn_deposit.setText(getString(R.string.wallet_rechrge));
                isRecharge = false;
            }
        } else {
            alreadyAmount.setText("0");
            btn_deposit.setText(getString(R.string.wallet_rechrge));
            isRecharge = false;
        }
    }
}
