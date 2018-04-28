package com.danchexia.bikehero.main.mystroke;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.batterymain.battery.BatteryTripDetailActivity;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.mystroke.adapter.ItemStrokeAdapter;
import com.danchexia.bikehero.main.mystroke.bean.ItemStrokeBean;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/17.
 * description:
 */

public class MyStrokeActivity extends MvpActivity<MyStrokePresenter , IMyStrokeView> implements IMyStrokeView, View.OnClickListener, AdapterView.OnItemClickListener {
    private MyStrokePresenter presenter;
    private ImageView head_left, head_right;
    private TextView head_title;
    private TextView empty_txt;
    private ListView listview;
    private List<OnGoing_TripBO> dataList = new ArrayList<>();
    private ItemStrokeAdapter adapter;
    private LinearLayout empty_view;
    protected MyStrokePresenter CreatePresenter() {
        return presenter = new MyStrokePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stroke);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataList.clear();
        presenter.getMyAllStroke(null);

    }

    private void initView() {
        empty_view = (LinearLayout) findViewById(R.id.empty_view);
        head_title = (TextView) findViewById(R.id.head_title);
        empty_txt = (TextView) findViewById(R.id.empty_txt);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        listview = (ListView) findViewById(R.id.listview);
        listview.setOnItemClickListener(this);
        head_title.setText(Utils.object2String(getString(R.string.my_stroke_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        if (dataList.size() > 0) {
                            presenter.getMyAllStroke(dataList.get(dataList.size() - 1).getFinishTime().getTime());
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        if (Config.isOpenBattery){
            empty_txt.setText(getString(R.string.empty_trip_bat));
        }
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
    public void loadMore(final ItemStrokeBean bean) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                if (bean.getContent() != null) {
                    dataList.addAll(bean.getContent());
                }
                if (dataList.size() == 0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            empty_view.setVisibility(View.VISIBLE);
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (adapter == null) {
                                adapter = new ItemStrokeAdapter(MyStrokeActivity.this, dataList);
                                listview.setAdapter(adapter);
                            } else {
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OnGoing_TripBO tripBO = dataList.get(position);
        if ("battery".equals(tripBO.getTripType())){
            Intent detail = new Intent(this, BatteryTripDetailActivity.class);
            detail.putExtra("TID",tripBO.getId());
            startActivity(detail);
        }else{
            ActivityController.startStrokeDetail(this,tripBO.getId());
        }
    }
}
