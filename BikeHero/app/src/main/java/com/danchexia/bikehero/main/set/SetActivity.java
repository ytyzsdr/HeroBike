package com.danchexia.bikehero.main.set;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.set.adapter.SetAdapter;
import com.danchexia.bikehero.main.set.bean.SetBean;
import com.danchexia.bikehero.main.set.bean.SetData;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.clearcache.ClearCacheUtils;
import vc.thinker.tools.clearcache.DataCleanManager;
import vc.thinker.tools.utils.Utils;
import vc.thinker.tools.views.NewTextView;

/**
 * Created by farley on 17/5/18.
 * description:
 */

public class SetActivity extends MvpActivity<SetPresenter, ISetView> implements ISetView, View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView head_left, head_right;
    private TextView head_title;
    //    private RelativeLayout about_us,protocol,chrge,clear;
    private RelativeLayout clear;
    private TextView cache;
    private NewTextView appcode;
    private SetPresenter presenter;
    private ListView listView;
    private SetAdapter adapter;
    private SetBean setBean;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initView();
    }

    @Override
    protected SetPresenter CreatePresenter() {
        return presenter = new SetPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getGuideList(2);//彩虹单车的
//        presenter.getGuideList(9);//小E
    }

    private void initView() {
//        about_us = (RelativeLayout) findViewById(R.id.about_us);
//        protocol = (RelativeLayout) findViewById(R.id.protocol);
        //clear = (RelativeLayout) findViewById(R.id.clear);
        logout = (TextView) findViewById(R.id.logout);
//        chrge = (RelativeLayout) findViewById(R.id.chrge);
        //cache = (TextView) findViewById(R.id.cache);
        appcode = (NewTextView) findViewById(R.id.appcode);
        appcode.setTextIsSelectable(true);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        listView = (ListView) findViewById(R.id.listview);

        //把清除缓存作为脚布局添加到listview中
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View footView = inflater.inflate(R.layout.footview_set,null);
        cache = (TextView) footView.findViewById(R.id.cache);
        clear = (RelativeLayout) footView.findViewById(R.id.clear);
        listView.addFooterView(footView);

        head_title.setText(Utils.object2String(getString(R.string.set_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
//        about_us.setOnClickListener(this);
//        protocol.setOnClickListener(this);
//        chrge.setOnClickListener(this);
        clear.setOnClickListener(this);
        logout.setOnClickListener(this);
        try {
            cache.setText(Utils.object2String(DataCleanManager.getTotalCacheSize(this)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(this);


        appcode.setText(Utils.getAppMsg(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left:
                finish();
                break;
            case R.id.logout:
                presenter.logout();
                break;
           /* case R.id.about_us:
                ShowToast.show(this,"关于我们");
                break;
            case R.id.protocol:
                ShowToast.show(this,"用户协议");
                break;
            case R.id.chrge:
                ActivityController.startWebView(this,"押金说明","api/guide_detail_app?id");
                break;*/
            case R.id.clear:
                ClearCacheUtils.clearCache(this, new ClearCacheUtils.OnClearListener() {
                    @Override
                    public void clear() {
                        cache.setText("0k");
                    }
                });
                break;
            default:
                break;
        }
    }

    public void setData(SetBean bean) {
        setBean = bean;
        adapter = new SetAdapter(this, bean.getDatas());
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityController.startWebView(this, setBean.getDatas().get(position).getTitle(), "api/guide_detail_app?id=" + setBean.getDatas().get(position).getId(), true);
    }
}
