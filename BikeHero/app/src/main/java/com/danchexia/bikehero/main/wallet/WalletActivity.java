package com.danchexia.bikehero.main.wallet;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.main.recharge.bean.ChergeBean;
import com.danchexia.bikehero.main.wallet.adapter.ItemWalletAdapter;
import com.danchexia.bikehero.main.wallet.bean.WalletBean;
import com.danchexia.bikehero.main.wallet.bean.WalletItemData;
import com.danchexia.bikehero.main.wallet.dialog.DepositDialog;
import com.danchexia.bikehero.main.wallet.dialog.RechargeDialog;
import com.danchexia.bikehero.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/18.
 * description:我的钱包
 */

public class WalletActivity extends MvpActivity<WalletPresenter, IWalletView> implements IWalletView, View.OnClickListener {
    private WalletPresenter presenter;
    private ImageView head_left, head_right;
    private TextView head_title;
    private ListView listview;
    private List<WalletItemData> dataList = new ArrayList<>();
    private ItemWalletAdapter adapter;
    private Button btn_deposit;
    private TextView charge;
    private boolean isRecharge;//是否充值了押金
    private SystemParamsBean systemBean;

    @Override
    protected WalletPresenter CreatePresenter() {
        return presenter = new WalletPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        initView();
        systemBean = MyUtils.getSystemData();
    }

    public void initData(PersonalBean bean) {
        if (bean != null) {
            charge.setText(getString(R.string.wallet_charge) + bean.getDeposit() + getString(R.string.wallet_charge_unit));
            if (bean.getDeposit() != null && bean.getDeposit() > 0) {
                btn_deposit.setText(getString(R.string.wallet_dialog_btn));
                isRecharge = true;
            } else {
                charge.setText(getString(R.string.wallet_charge) + "0" + getString(R.string.wallet_charge_unit));
                btn_deposit.setText(getString(R.string.wallet_rechrge));
                isRecharge = false;
            }
        } else {
            charge.setText(getString(R.string.wallet_charge) + "0" + getString(R.string.wallet_charge_unit));
            btn_deposit.setText(getString(R.string.wallet_rechrge));
            isRecharge = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getMyWallet(null);
        presenter.getMyData();
        presenter.getCherge();
    }
    private Double myCherge = 0d;
    public void setCherge(final ChergeBean chergeBean) {
        myCherge = chergeBean.getCherge();
    }
    private void initView() {
        btn_deposit = (Button) findViewById(R.id.btn_deposit);
        charge = (TextView) findViewById(R.id.charge);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        listview = (ListView) findViewById(R.id.listview);
        head_title.setText(Utils.object2String(getString(R.string.wallet_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        btn_deposit.setOnClickListener(this);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (dataList.size() > 9) {
                    presenter.getMyWallet(dataList.get(dataList.size() - 1).getCreateTime().getTime());
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left:
                finish();
                break;
            case R.id.btn_deposit:
                if (isRecharge) {//已经充值了 走退押金流程
                    showRefoundDialog();
                } else {//未充值，走充值流程
                    if (systemBean.getPayWay().contentEquals("1")) {
                        presenter.recharge(MyUtils.PAY_TYPE_WXPAY);
                    } else if (systemBean.getPayWay().contentEquals("2")) {
                        presenter.recharge(MyUtils.PAY_TYPE_ALIPAY);
                    } else if (systemBean.getPayWay().contentEquals("1,2")) {
                        showRechargeDialog();
                    }
                }

                break;
            default:
                break;
        }
    }

    public void loadMore(final WalletBean bean, final Long page) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (page == null) {
                            dataList.clear();
                        }
                        dataList.addAll(bean.getDatas());
                        if (adapter == null) {
                            adapter = new ItemWalletAdapter(WalletActivity.this, dataList);
                            listview.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }.start();
    }

    /**
     * 退押金
     */
    private void showRefoundDialog() {
        DepositDialog dialog = new DepositDialog(WalletActivity.this, new DepositDialog.OnDialogClickListener() {

            @Override
            public void onClick() {
                presenter.refundDeposit();
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
                presenter.recharge(MyUtils.PAY_TYPE_WXPAY);
            } else if (systemParamsBean.getPayWay().contentEquals("2")) {
                presenter.recharge(MyUtils.PAY_TYPE_ALIPAY);
            } else if (systemParamsBean.getPayWay().contentEquals("1,2")) {


                RechargeDialog dialog = new RechargeDialog(myCherge,WalletActivity.this, new RechargeDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(int type) {
                        if (type == 0) {
                            presenter.recharge(MyUtils.PAY_TYPE_WXPAY);
                        } else if (type == 1) {
                            presenter.recharge(MyUtils.PAY_TYPE_ALIPAY);
                        }
                    }
                });
                dialog.show();
            }
        }
    }
}
