package com.danchexia.bikehero.elebike;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.MapUtils.BaiduMapHelper;
import com.danchexia.bikehero.main.bottomfragment.FragmentBottom;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/9/25.
 * description:
 */

public class EleMainActivity extends MvpActivity<EleMainPresenter,EleMainView> implements EleMainView, View.OnClickListener, BaiduMap.OnMarkerClickListener, BaiduMap.OnMapClickListener, BaiduMapHelper.OnMyMapStatusChangeListener, BDLocationListener {
    private EleMainPresenter myPresenter;
    @Override
    protected EleMainPresenter CreatePresenter() {
        return myPresenter = new EleMainPresenter(this);
    }
    private ImageView head_left;
    private TextView head_title;
    private RelativeLayout head_message;
    private ImageView message_tips_icon;
    private ImageView head_right;
    private ImageView map_center;//中心大头针
    private FragmentManager fragmentManager;
    private FragmentBottom fragmentBottom;//首页下面的选项
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    private LocationClient mLocClient;// 定位相关
    private LatLng mCenterLatLng = new LatLng(0, 0);//地图屏幕中间点
    private PlanNode sNode = null;//起点
    private PlanNode eNode = null;//终点
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ele_main);
        initView();
        initBottom();
        initMap();
        initLocation();
        Utils.getScreenWithAndHeight(this);
    }

    private void initView() {
        head_message = (RelativeLayout) findViewById(R.id.head_message);
        map_center = (ImageView) findViewById(R.id.map_center);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_left = (ImageView) findViewById(R.id.head_left);
        message_tips_icon = (ImageView) findViewById(R.id.message_tips_icon);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left.setImageDrawable(getResources().getDrawable(R.drawable.map_my_bg));
        head_left.setOnClickListener(this);
        head_message.setOnClickListener(this);
        head_right.setOnClickListener(this);
    }
    private void initBottom() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentBottom = new FragmentBottom();
        transaction.replace(R.id.bottom, fragmentBottom);
        // 事务提交
        transaction.commitAllowingStateLoss();
    }
    private void initMap() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.removeViewAt(2);//去掉地图上的加减符号
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通地图
        mBaiduMap.setBuildingsEnabled(false);
        mBaiduMap.setOnMarkerClickListener(this);
        mBaiduMap.setOnMapClickListener(this);
        mBaiduMap.getUiSettings().setOverlookingGesturesEnabled(false);//禁止使用俯视视角转换
        BaiduMapHelper.initMapStatsListener(mBaiduMap);
        mSearch = RoutePlanSearch.newInstance();
        BaiduMapHelper.initSeachMap(mSearch, mBaiduMap);
        BaiduMapHelper baiduMapHelper = new BaiduMapHelper();
        baiduMapHelper.setOnMyMapStatusChangeListener(this);
    }
    private void initLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(this);
        LocationClientOption optiona = new LocationClientOption();
        optiona.setOpenGps(true); // 打开gps
        optiona.setCoorType("bd09ll"); // 设置坐标类型
        optiona.setScanSpan(1000);
        optiona.setIsNeedAddress(true);
        optiona.setNeedDeviceDirect(true);
        mLocClient.setLocOption(optiona);
        mLocClient.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_left://个人中心
                break;
            case R.id.head_right://搜索
                break;
            case R.id.head_message://消息中心
                break;
            default:
                break;
        }
    }
    //标注物点击
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
    //地图点击
    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
    //地图改变
    @Override
    public void onChange(MapStatus mapStatus) {

    }
    //路线规划
    @Override
    public void onRawed(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null || mMapView == null) {
            return;
        }
        LatLng ll = new LatLng(location.getLatitude(),
                location.getLongitude());
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
        mBaiduMap.setMyLocationData(locData);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mLocClient.stop();
        mCenterLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        sNode = PlanNode.withLocation(mCenterLatLng);
        //TODO 去获取所有电单车位置
        myPresenter.getAllEleBike(mCenterLatLng);
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
    //显示所有电单车
    public void setAllMarkers() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
