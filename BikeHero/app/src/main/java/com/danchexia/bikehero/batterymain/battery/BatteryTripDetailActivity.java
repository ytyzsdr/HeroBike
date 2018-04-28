package com.danchexia.bikehero.batterymain.battery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.InvateAndShateBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vc.thinker.colours.client.ApiClient;
import vc.thinker.mvp.MvpActivity;
import vc.thinker.mvp.MvpView;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/7/3.
 * description:
 */

public class BatteryTripDetailActivity extends MvpActivity<BatteryDetailPresenter, MvpView> implements MvpView, View.OnClickListener, BaiduMap.SnapshotReadyCallback, BDLocationListener {
    private ImageView  head_right;
    private TextView ride_distance_unit;//骑行距离
    private TextView kcal;//消耗卡路里
    private TextView ride_time;//骑行时长
    private LinearLayout share_ll;
//        private Long tid;
    private InvateAndShateBean invateAndShare;
    private ImageView sceen_img;
    List<LatLng> latLngPolygon = new ArrayList<LatLng>();
    private TextView money;//花费
    private ImageView qrCode;
    private String shareTile = "我的行程";
    private String shareContent = "我的行程";
    private ImageView head_left;
    private TextView head_title;
    private TextView nickName;
    private TextView ride_distance;
    private TextView saving_g;
    private TextView borrowTime;
    private TextView borrowAdd;
    private TextView pullTime;
    private TextView pullAdd;
    private TextView syscode;
    private BatteryDetailPresenter myPresenter;
    private CircleImageView icon_my;
    private TextView share;
    private RelativeLayout shareQrCode;
    private BaiduMap mBaiduMap;
    private MapView mMapView = null;
    private Long orderCode ;
    private LocationClient mLocClient;// 定位相关
    private TextView payMoney;
    @Override
    protected BatteryDetailPresenter CreatePresenter() {
        return myPresenter = new BatteryDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battery_detail_trip);
        Intent it = getIntent();
        orderCode = it.getLongExtra("TID",0);
        initView();
        initMap();
        myPresenter.getDetailData(orderCode);
        getShareParams();
        initLocation();
    }
    //定位
    private void initLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        /*mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.rotate_triangle);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode,
                true, mCurrentMarker, accuracyCircleFillColor, accuracyCircleStrokeColor));*/
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
    private void getShareParams() {
        invateAndShare = MyUtils.getInvateAndShareData();
    }
    private void initMap() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.removeViewAt(2);//去掉地图上的加减符号
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通地图
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (share != null) {
            share.setVisibility(View.VISIBLE);
            shareQrCode.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        sceen_img.setVisibility(View.GONE);
    }
    private void initView() {
        payMoney = (TextView) findViewById(R.id.payMoney);
        shareQrCode = (RelativeLayout) findViewById(R.id.shareQrCode);
        qrCode = (ImageView) findViewById(R.id.qrCode);
        icon_my = (CircleImageView) findViewById(R.id.icon_my);
        share_ll = (LinearLayout) findViewById(R.id.share_ll);
        sceen_img = (ImageView) findViewById(R.id.sceen_img);
        nickName = (TextView) findViewById(R.id.nickName);
        money = (TextView) findViewById(R.id.money);
        ride_distance = (TextView) findViewById(R.id.ride_distance);
        ride_distance_unit = (TextView) findViewById(R.id.ride_distance_unit);
        saving_g = (TextView) findViewById(R.id.saving_g);
        kcal = (TextView) findViewById(R.id.kcal);
        ride_time = (TextView) findViewById(R.id.ride_time);
        syscode = (TextView) findViewById(R.id.syscode);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        share = (TextView) findViewById(R.id.share);
        head_title.setText(Utils.object2String(getString(R.string.stroke_detail_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        share.setOnClickListener(this);
        Bitmap bitLogo = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        Bitmap bitmap = CodeUtils.createImage("http://a.app.qq.com/o/simple.jsp?pkgname=com.danchexia.bikehero", 200, 200, bitLogo);
        qrCode.setImageBitmap(bitmap);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_title = (TextView) findViewById(R.id.head_title);
        borrowTime = (TextView) findViewById(R.id.borrowTime);
        share = (TextView) findViewById(R.id.share);
        shareQrCode = (RelativeLayout) findViewById(R.id.shareQrCode);
        borrowAdd = (TextView) findViewById(R.id.borrowAdd);
        syscode = (TextView) findViewById(R.id.syscode);
        pullTime = (TextView) findViewById(R.id.pullTime);
        pullAdd = (TextView) findViewById(R.id.pullAdd);
        ride_distance = (TextView) findViewById(R.id.ride_distance);
        saving_g = (TextView) findViewById(R.id.saving_g);
        nickName = (TextView) findViewById(R.id.nickName);
        icon_my = (CircleImageView) findViewById(R.id.icon_my);
        head_title.setText(getString(R.string.battery_detail_title));
        head_left.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_left:
                finish();
                break;
            case R.id.share:
                mBaiduMap.snapshot(this);
                break;
            default:
                break;
        }
    }
//    public void initData(CurrentStatusBean bean){
    public void initData(OnGoing_TripBO bean){
        PersonalBean beanPersonal = MyUtils.getPersonData();
        if (beanPersonal != null){
            nickName.setText(beanPersonal.getNickname());
            if (!TextUtils.isEmpty(beanPersonal.getHeadImgPath())){
                Glide.with(this).load(beanPersonal.getHeadImgPath()).into(icon_my);
            }
        }

        if (bean.getDistance() != null && bean.getDistance() > 1000){
            ride_distance_unit.setText(getString(R.string.battery_ride_distance_km));
        }else{
            ride_distance_unit.setText(getString(R.string.battery_ride_distance_m));
        }
        ride_distance.setText(Utils.object2String(bean.getDistance()));
        saving_g.setText(Utils.object2String(bean.getRideTime()));
        borrowTime.setText(getString(R.string.battery_detail_borrow_time)+Utils.stampToDate3(bean.getBeginTime().getTime()));
        borrowAdd.setText(getString(R.string.battery_detail_borrow_add)+Utils.object2String(bean.getBeginLocationDetails()));
        pullTime.setText(getString(R.string.battery_detail_return_time)+Utils.stampToDate3(bean.getFinishTime().getTime()));
        pullAdd.setText(getString(R.string.battery_detail_return_add)+Utils.object2String(bean.getEndLocationDetails()));
        syscode.setText(getString(R.string.battery_detail_sysCode)+orderCode);
        payMoney.setText("使用共花费"+Utils.object2String(bean.getPrice())+"元");
        if (Config.isNeadToPay) {
            payMoney.setVisibility(View.VISIBLE);
        }else{
            payMoney.setVisibility(View.GONE);
        }

        LatLng pointStart = new LatLng(bean.getBeginLcationLat(),bean.getBeginLcationLon());
        LatLng pointEnd = new LatLng(bean.getEndLcationLat(),bean.getEndLcationLon());

        setMarkers(pointStart,true);
        setMarkers(pointEnd,false);
    }
    @Override
    public void onSnapshotReady(Bitmap bitmap) {
        sceen_img.setVisibility(View.VISIBLE);
        sceen_img.setImageBitmap(bitmap);
//        String imgurl = Utils.getViewImg(share_ll);截图
        if (invateAndShare != null) {
            shareTile = invateAndShare.getShareTitle();
            shareContent = invateAndShare.getShareContent();
        }
        // shareTile, ApiClient.baseUrl + "share/um_order_share?orderCode=" + tid, shareContent, share_ll,
        MyUtils.myShareDialog(true,this, new MyUtils.ShareCancelListener() {
            @Override
            public void cancel() {
                sceen_img.setVisibility(View.GONE);
            }

            @Override
            public void mySelect(int pos) {
                if (pos == 0) {
                    MyUtils.shareQzone(BatteryTripDetailActivity.this, shareTile, ApiClient.baseUrl + "share/trip_share?id=" + orderCode, shareContent);
                } else if (pos == 1) {
                    MyUtils.shareQQ(BatteryTripDetailActivity.this, shareTile, ApiClient.baseUrl + "share/trip_share?id=" + orderCode, shareContent);
                } else if (pos == 2) {
                    share.setVisibility(View.GONE);
                    shareQrCode.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MyUtils.showMoment(BatteryTripDetailActivity.this, share_ll);
                        }
                    },1000);

                } else if (pos == 3) {
                    MyUtils.showWechat(BatteryTripDetailActivity.this, shareTile, ApiClient.baseUrl + "share/trip_share?id=" + orderCode, shareContent);
                }
            }
        });

    }
    private void setMarkers(LatLng point,boolean usingAtMaps){
        BitmapDescriptor bitmap ;
        if (usingAtMaps){
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.starting_point);
        }else{
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.end_point);
        }
        MarkerOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap)
                .zIndex(0).period(10);
        mBaiduMap.addOverlay(option);
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
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mLocClient.stop();
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
