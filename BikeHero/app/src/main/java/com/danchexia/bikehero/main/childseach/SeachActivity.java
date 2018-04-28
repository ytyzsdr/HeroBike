package com.danchexia.bikehero.main.childseach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.myviews.AddressEntity;
import com.danchexia.bikehero.myviews.SuggestionSearchEditText;
import com.danchexia.bikehero.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.mvp.util.XlStatusBarUtil;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/4/4.
 * description:
 */

public class SeachActivity extends Activity implements  BDLocationListener {
    private AreaAdapter mAreaAdapter = new AreaAdapter();;
    LocationClient mLocClient;
    LocationClientOption optiona;
    private String userLocationCity;//用户当前地址城市
    private String userLocationStreet;//用户当前地址街道
    private TextView selfLoaton;
    private boolean isFirstLocation = true;
//    private boolean isSeachData = false;//历史数据？搜索数据
    SuggestionSearchEditText sset;
    private List<AddressEntity> historySeach = new ArrayList<>();
    private View bottomView;//底部清除
    ListView rcv_area;
    private AddressEntity myAddressEntity;

    private ImageView head_left, head_right;
    private TextView head_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_seach);
        XlStatusBarUtil.StatusBarLightMode(this, XlStatusBarUtil.StatusBarLightMode(this));
        initView();
        initLocation();
        getHistorySeachData();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale!=1){
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
    private void initView() {
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title.setText(Utils.object2String(getString(R.string.seach_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bottomView = View.inflate(SeachActivity.this,R.layout.clearhistoryseach,null);
        selfLoaton = (TextView) findViewById(R.id.selfLoaton);
        sset = (SuggestionSearchEditText) findViewById(R.id.aa_address_search);
        sset.setOnSuggestionSearchEditTextListener(new SuggestionSearchEditText.OnSuggestionSearchEditTextListener() {
            @Override
            public void onResults(List<AddressEntity> result) {
                LogUtils.d("getFooterViewsCount="+rcv_area.getFooterViewsCount());
                if (rcv_area.getFooterViewsCount()>0) {
                    rcv_area.removeFooterView(bottomView);
                }

                mAreaAdapter.setDatas(result);
            }
        });
        bottomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StanderdDialog dialog = new StanderdDialog(SeachActivity.this, getString(R.string.seach_toast1), getString(R.string.seach_toast2),
                       getString(R.string.seach_toast3),getString(R.string.seach_toast4)
                        , new StanderdDialog.OnDialogClickListener() {
                    @Override
                    public void doAnyClick() {

                    }

                    @Override
                    public void doMainClick() {
                        historySeach.clear();
                        MyUtils.putSeachHistory(SeachActivity.this,"HISTORYSEACH",historySeach);
                        mAreaAdapter.setDatas(historySeach);
                        if (rcv_area.getFooterViewsCount()>0) {
                            rcv_area.removeFooterView(bottomView);
                        }
                    }
                });
                dialog.show();
            }
        });
        rcv_area = (ListView) findViewById(R.id.rcv_area);
        selfLoaton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myAddressEntity != null) {
                    Intent intent = new Intent();
                    intent.putExtra("address", myAddressEntity);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    /**
     * 初始化地图
     */
    private void initLocation() {
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(this);
        optiona = new LocationClientOption();
        optiona.setOpenGps(true); // 打开gps
        optiona.setCoorType("bd09ll"); // 设置坐标类型
        optiona.setScanSpan(1000);
        optiona.setIsNeedAddress(true);
        optiona.setIsNeedLocationDescribe(true);
        mLocClient.setLocOption(optiona);
        mLocClient.start();
    }

    @Override
    public void onReceiveLocation(final BDLocation location) {
        // map view 销毁后不在处理新接收的位置
        if (location == null) {
            return;
        }
        if (isFirstLocation) {
            userLocationCity = location.getCity();
            userLocationStreet = location.getStreet();
            sset.setUserCity(userLocationCity);
            sset.setUserKeyWords(userLocationStreet);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    selfLoaton.setText(location.getAddrStr());
                }
            });
            isFirstLocation = false;
            myAddressEntity = new AddressEntity();
            myAddressEntity.setName(location.getAddrStr());
            myAddressEntity.setLatitude((float) location.getLatitude());
            myAddressEntity.setLongitude((float) location.getLongitude());
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    /**
     * 获取历史数据
     * @return
     */
    public void getHistorySeachData() {
        historySeach.clear();
        historySeach.addAll(MyUtils.getSeachHistory(SeachActivity.this,"HISTORYSEACH"));
        if (historySeach != null && historySeach.size() > 0) {
            for (AddressEntity ae:historySeach){
                ae.setType(1);
            }
            LogUtils.d(historySeach.size()+"=size");
            rcv_area.addFooterView(bottomView);
            rcv_area.setAdapter(mAreaAdapter);
            mAreaAdapter.setDatas(historySeach);
        }else{
            rcv_area.setAdapter(mAreaAdapter);
        }
    }

    private class AreaAdapter extends BaseAdapter {

        /*@Override
        protected void onBindViewHolder(CommonViewHolder holder, int position, AddressEntity item) {
            setContentOfTextView(holder, R.id.add_lbs_name, item.getName());
            setContentOfTextView(holder, R.id.add_lbs_address, item.getAddress());
        }

        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CommonViewHolder commonViewHolder = new CommonViewHolder(getLayoutInflater().inflate(R.layout.layout_business_address_area_list_item, parent, false));

            final View view = commonViewHolder.getContentView();

            RelativeLayout address = (RelativeLayout) view.findViewById(R.id.address);
            if (address != null) {
                address.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddressEntity addressEntity = (AddressEntity) view.getTag();
                        selectArea(addressEntity);
                    }
                });
            }

            return commonViewHolder;
        }*/

        private void selectArea(final AddressEntity addressEntity) {
            MyUtils.putSeachHistory(SeachActivity.this,"HISTORYSEACH",historySeach);
            Intent intent = new Intent();
            intent.putExtra("address", addressEntity);
            setResult(RESULT_OK, intent);
            finish();
        }
        public void setDatas(List<AddressEntity> dataList){
            this.dataList = dataList;
            notifyDataSetChanged();
        }
        private List<AddressEntity> dataList = new ArrayList<>();

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_business_address_area_list_item, null);
                holder = new ViewHolder();
                holder.add_lbs_name = (TextView) convertView.findViewById(R.id.add_lbs_name);
                holder.add_lbs_address = (TextView) convertView.findViewById(R.id.add_lbs_address);
                holder.address = (RelativeLayout) convertView.findViewById(R.id.address);
                holder.seach_icon = (ImageView) convertView.findViewById(R.id.seach_icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final AddressEntity addressEntity = dataList.get(position);
            holder.add_lbs_name.setText(addressEntity.getName());
            holder.add_lbs_address.setText(addressEntity.getAddress());
            holder.address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!historySeach.contains(addressEntity)){
                        historySeach.add(addressEntity);
                    }
                    selectArea(addressEntity);
                }
            });
            if (addressEntity.getType() == 0){
                holder.seach_icon.setImageDrawable(SeachActivity.this.getResources().getDrawable(R.drawable.search_address_icon));
            }else{
                holder.seach_icon.setImageDrawable(SeachActivity.this.getResources().getDrawable(R.drawable.search_time_icon));
            }
            return convertView;
        }
        class ViewHolder {
            TextView add_lbs_name;
            TextView add_lbs_address;
            RelativeLayout address;
            ImageView seach_icon;
        }
    }
}
