package com.danchexia.bikehero.main.newwallet;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.bean.RechartTypeListBean;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.newwallet.adapter.RechartViewAdapter;
import com.danchexia.bikehero.utils.MyUtils;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.mvp.MvpView;
import vc.thinker.tools.utils.ShowToast;

/**
 * Created by farley on 17/8/21.
 * description:
 */

public class ToRechartWalletActivity extends MvpActivity<ToRechartWalletPresenter,MvpView> implements MvpView, View.OnClickListener {
    private ToRechartWalletPresenter myPresenter;
    @Override
    protected ToRechartWalletPresenter CreatePresenter() {
        return myPresenter = new ToRechartWalletPresenter(this);
    }
    private ImageView head_left;
    private TextView head_title;
    private GridView myGridview;
    private RechartViewAdapter adapter;
    private RelativeLayout wx_pay,ali_pay;
    private ImageView img_selected;
    private ImageView img_ali;
    private TextView toRechart;
    private SystemParamsBean systemBean;
    private Long payMoney;
    private String payType;
    private RechartTypeListBean mBean;
    private TextView rechartRules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_rechart);
        systemBean = MyUtils.getSystemData();
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        myPresenter.getRechartType();
    }

    private void initView() {
        wx_pay = (RelativeLayout) findViewById(R.id.wx_pay);
        ali_pay = (RelativeLayout) findViewById(R.id.ali_pay);
        img_ali = (ImageView) findViewById(R.id.img_ali);
        img_selected = (ImageView) findViewById(R.id.img_selected);
        head_left = (ImageView) findViewById(R.id.head_left);
        toRechart = (TextView) findViewById(R.id.toRechart);
        head_title = (TextView) findViewById(R.id.head_title);
        rechartRules = (TextView) findViewById(R.id.rechartRules);
        myGridview = (GridView) findViewById(R.id.myGridview);
        head_title.setText(getString(R.string.to_rechart_title));
        head_left.setOnClickListener(this);
        myGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(adapter != null && mBean.getDataList().size() > 0){
                    adapter.setMySelected(position);
                    payMoney = mBean.getDataList().get(position).getId();
                }
            }
        });
        wx_pay.setOnClickListener(this);
        ali_pay.setOnClickListener(this);
        toRechart.setOnClickListener(this);
        if (systemBean.getPayWay().contentEquals("1")) {
            img_selected.setSelected(true);
            payType = MyUtils.PAY_TYPE_WXPAY;
        } else if (systemBean.getPayWay().contentEquals("2")) {
            img_ali.setSelected(true);
            payType = MyUtils.PAY_TYPE_ALIPAY;
        } else if (systemBean.getPayWay().contentEquals("1,2")) {
            img_selected.setSelected(true);
            payType = MyUtils.PAY_TYPE_WXPAY;
        }
        rechartRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityController.startWebView(ToRechartWalletActivity.this,getString(R.string.to_rechart_rules),"api/guide_detail_app?id="+21,true);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_left:
                finish();
                break;
            case R.id.wx_pay:
                if (img_selected.isSelected()){
                }else{
                    payType = MyUtils.PAY_TYPE_WXPAY;
                    img_selected.setSelected(true);
                    img_ali.setSelected(false);
                }
                break;
            case R.id.ali_pay:
                if (img_ali.isSelected()){
                }else{
                    payType = MyUtils.PAY_TYPE_ALIPAY;
                    img_ali.setSelected(true);
                    img_selected.setSelected(false);
                }
                break;
            case R.id.toRechart:
                if (systemBean.getPayWay().contentEquals("1")) {
                    myPresenter.recharge(payMoney,MyUtils.PAY_TYPE_WXPAY);
                } else if (systemBean.getPayWay().contentEquals("2")) {
                    myPresenter.recharge(payMoney,MyUtils.PAY_TYPE_ALIPAY);
                } else if (systemBean.getPayWay().contentEquals("1,2")) {
                    if (img_selected.isSelected() || img_ali.isSelected()) {
                        myPresenter.recharge(payMoney,payType);
                    }else{
                        ShowToast.show(this,"请选择支付方式");
                    }
                }
                break;
            default:
                break;
        }
    }

    public void initData(RechartTypeListBean rechartTypeListBean) {
        mBean = rechartTypeListBean;
        if (mBean.getDataList() != null) {
            adapter = new RechartViewAdapter(this, rechartTypeListBean.getDataList());
            myGridview.setAdapter(adapter);
            if (mBean.getDataList().size() > 0) {
                payMoney = mBean.getDataList().get(0).getId();
            }
        }
    }
}
