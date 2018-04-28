package com.danchexia.bikehero.main.newwallet;

import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.bean.RechartHistoryBean;
import com.danchexia.bikehero.main.bean.RechartHistoryListBean;
import com.danchexia.bikehero.main.newwallet.adapter.YaJinListAdapter;
import com.danchexia.bikehero.main.newwallet.adapter.YueListAdapter;
import com.danchexia.bikehero.main.wallet.bean.WalletBean;
import com.danchexia.bikehero.main.wallet.bean.WalletItemData;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.mvp.MvpView;

/**
 * Created by farley on 17/8/21.
 * description:
 */

public class DetailRechartActivity extends MvpActivity<DetailRechartPresenter, MvpView> implements MvpView, View.OnClickListener {
    private DetailRechartPresenter myPresenter;
    private LinearLayout empty_view;

    @Override
    protected DetailRechartPresenter CreatePresenter() {
        return myPresenter = new DetailRechartPresenter(this);
    }

    private ImageView head_left;
    private ListView listview;
    private YaJinListAdapter yajinListAdapter;
    private YueListAdapter yueListAdapter;
    private List<WalletItemData> dataList = new ArrayList<>();
    private List<RechartHistoryBean> dataListForAmount = new ArrayList<>();
    private RadioGroup group_radio;
    private boolean isleft = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechart_detail);
        initView();
        myPresenter.getYueList(null);
    }

    private void initView() {
        group_radio = (RadioGroup) findViewById(R.id.group_radio);
        listview = (ListView) findViewById(R.id.listview);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_left.setOnClickListener(this);
        empty_view = (LinearLayout) findViewById(R.id.empty_view);
        group_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.left:
                        if (yajinListAdapter != null){
                            dataList.clear();
                            yajinListAdapter.notifyDataSetChanged();
                            yajinListAdapter = null;
                        }
                        isleft = true;
                        myPresenter.getYueList(null);
                        break;
                    case R.id.right:
                        if (yueListAdapter != null){
                            dataListForAmount.clear();
                            yueListAdapter.notifyDataSetChanged();
                            yueListAdapter = null;
                        }
                        isleft = false;
                        myPresenter.getMyWallet(null);
                        break;
                }
            }
        });
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (dataList.size() > 9) {
                    if (isleft) {
                        myPresenter.getMyWallet(dataListForAmount.get(dataListForAmount.size() - 1).getCreateTime().getTime());
                    } else {
                        myPresenter.getMyWallet(dataList.get(dataList.size() - 1).getCreateTime().getTime());
                    }
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
            default:
                break;
        }
    }

    //押金记录
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
                        if (yajinListAdapter == null) {
                            yajinListAdapter = new YaJinListAdapter(DetailRechartActivity.this, dataList);
                            listview.setAdapter(yajinListAdapter);
                        } else {
                            yajinListAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }.start();
    }
    //月明细
    public void loadMoreAmountDetail(final RechartHistoryListBean bean, final Long page) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (page == null) {
                            dataListForAmount.clear();
                        }
                        /*if (bean.getDataList() == null) {
                            return;
                        }*/
                        if (bean.getDataList()!=null){
                            dataListForAmount.addAll(bean.getDataList());
                        }
                        if (yueListAdapter == null) {
                            yueListAdapter = new YueListAdapter(DetailRechartActivity.this, dataListForAmount);
                            listview.setAdapter(yueListAdapter);
                        } else {
                            yueListAdapter.notifyDataSetChanged();
                        }

                        if (dataListForAmount.size()>0){
                            empty_view.setVisibility(View.GONE);
                        }else {
                            empty_view.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        }.start();
    }
}
