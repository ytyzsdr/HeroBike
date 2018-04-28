package com.danchexia.bikehero.main.member;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.bean.VipListPayBean;
import com.danchexia.bikehero.main.bean.VipPayBean;
import com.danchexia.bikehero.main.member.adapter.MemberListAdapter;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.mvp.MvpView;

/**
 * Created by farley on 17/8/18.
 * description:
 */

public class MemberListActivity extends MvpActivity<MemberListPresenter,MvpView> implements MvpView {
    private MemberListPresenter myPresenter;
    private LinearLayout empty_view;
    private ImageView head_left;
    private TextView head_title;
    private MemberListAdapter adapter;
    private List<VipPayBean> dataList = new ArrayList<>();
    private ListView listView;
    @Override
    protected MemberListPresenter CreatePresenter() {
        return myPresenter = new MemberListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myPresenter.getVipList(null);
    }

    private void initView() {
        empty_view = (LinearLayout) findViewById(R.id.empty_view);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_title = (TextView) findViewById(R.id.head_title);
        listView = (ListView) findViewById(R.id.listView);
        head_title.setText(getString(R.string.member_list_title));
        head_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        if (dataList.size() > 0) {
                            myPresenter.getVipList(dataList.get(dataList.size() - 1).getCreateTime().getTime());
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public void setListData(VipListPayBean vipListBean) {

        if (vipListBean.getDataList() != null) {
            dataList.addAll(vipListBean.getDataList());
            if (adapter == null) {
                adapter = new MemberListAdapter(MemberListActivity.this, dataList);
                listView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }

        }
        if (dataList.size() > 0) {
            empty_view.setVisibility(View.GONE);
        } else {
            empty_view.setVisibility(View.VISIBLE);
        }
    }
}
