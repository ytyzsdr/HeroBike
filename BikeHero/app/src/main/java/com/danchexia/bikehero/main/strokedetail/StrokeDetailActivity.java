package com.danchexia.bikehero.main.strokedetail;

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

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.bumptech.glide.Glide;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.InvateAndShateBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.bean.TripCyclingPointDataM;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vc.thinker.colours.client.ApiClient;
import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/18.
 * description:
 */

public class StrokeDetailActivity extends MvpActivity<StrokeDetailPresenter, IStrokeDetailView> implements IStrokeDetailView, View.OnClickListener, BaiduMap.SnapshotReadyCallback {
    private StrokeDetailPresenter presenter;
    private ImageView head_left ;
    private TextView head_right;
    private TextView head_title;
    private TextView share;
    private TextView ride_distance;//骑行距离
    private TextView ride_distance_unit;//骑行距离
    private TextView saving_g;//节约碳排g
    private TextView kcal;//消耗卡路里
    private TextView ride_time;//骑行时长
    private TextView syscode;//自行车编号
    private TextView nickName;//昵称
    private LinearLayout share_ll;
    private Long tid;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private InvateAndShateBean invateAndShare;
    private ImageView sceen_img;
    private CircleImageView icon_my;
    List<LatLng> latLngPolygon = new ArrayList<LatLng>();
    private TextView money;//花费
    private RelativeLayout shareQrCode;
    private ImageView qrCode;
    private String shareTile = "我的行程";
    private String shareContent = "我的行程";
    @Override
    protected StrokeDetailPresenter CreatePresenter() {
        return presenter = new StrokeDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroke_detail);
        tid = getIntent().getLongExtra("TID", -1L);
        initView();
        initMap();
        getShareParams();
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
        presenter.profileUsing(tid);
        if (Config.isNeadToShare && share != null) {
            share.setVisibility(View.VISIBLE);
            shareQrCode.setVisibility(View.GONE);
        }
    }

    private void initView() {
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
        head_right = (TextView) findViewById(R.id.head_right);
        head_right.setOnClickListener(this);
        share = (TextView) findViewById(R.id.share);
        head_title.setText(Utils.object2String(getString(R.string.stroke_detail_title)));
        head_left.setOnClickListener(this);
        share.setOnClickListener(this);
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null && !TextUtils.isEmpty(systemParamsBean.getAppDownloadUrl())) {
            String imgUrl = systemParamsBean.getAppDownloadUrl();
            Bitmap bitLogo = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
            Bitmap bitmap = CodeUtils.createImage(imgUrl, 200, 200, bitLogo);
            qrCode.setImageBitmap(bitmap);
        }
        if (!Config.isNeadToShare){
            share.setVisibility(View.GONE);
        }
        if (!Config.isNeadToPay){
            money.setVisibility(View.GONE);
        }
    }

    public void initData(OnGoing_TripBO onGoing_tripBO) {
        DecimalFormat df = new DecimalFormat("######0.0");
        if (onGoing_tripBO != null) {
            PersonalBean b = MyUtils.getPersonData();
            if (!TextUtils.isEmpty(b.getHeadImgPath())){
                Glide.with(this).load(b.getHeadImgPath()).into(icon_my);
            }
            nickName.setText(Utils.object2String(b.getNickname()));
            Integer distance = onGoing_tripBO.getDistance();
            if (distance > 1000) {
                ride_distance.setText(Utils.object2String(df.format((float) distance / 1000)));
            } else {
                ride_distance.setText(Utils.object2String(distance));
                ride_distance_unit.setText(getString(R.string.person_m));
            }
            saving_g.setText(Utils.object2String(onGoing_tripBO.getCarbon()));
            kcal.setText(Utils.object2String(onGoing_tripBO.getCalorie()));
            ride_time.setText(Utils.object2String(onGoing_tripBO.getRideTime()));
            syscode.setText(getString(R.string.stroke_detail_bike_code) + Utils.object2String(onGoing_tripBO.getSysCode()));
            money.setText(getString(R.string.stroke_detail_totoal_money) + Utils.object2String(onGoing_tripBO.getPrice()) +
                    getString(R.string.stroke_detail_totoal_money_unit));
           /* //画图
            WalkingRouteLine line = new WalkingRouteLine();

            List<WalkingRouteLine.WalkingStep> stepList = new ArrayList<>();
            for (TripCyclingPointDataM data:onGoing_tripBO.getCyclingPoints()){
                LatLng myLatlng = new LatLng(data.getPointLat(),data.getPointLon());
                RouteNode routeNode = new RouteNode();
                routeNode.setLocation(myLatlng);
                WalkingRouteLine.WalkingStep step = new WalkingRouteLine.WalkingStep();
                step.setEntrance(routeNode);
                step.setExit(routeNode);
                stepList.add(step);
            }
            line.setSteps(stepList);
            final MyWalkingRoutOverlay overlay = new MyWalkingRoutOverlay(mBaiduMap);
            //设置路线数据
            overlay.setData(line);
            overlay.addToMap();  //将所有overlay添加到地图中
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    overlay.zoomToSpan();//缩放地图
                }
            },1000);*/
            drawStart(onGoing_tripBO);
        }
    }

    Double minLat, maxLat;
    Double minLon, maxLon;
    Double latstart, lngstart;//起点
    Double latstop, lngstop;//终点
    LatLng leftPoint, roghtPoint, topPoint, bottomPoint;

    public void drawStart(OnGoing_TripBO bean) {
        latLngPolygon.clear();
        //向latLngPolygon中添加获取到的所有坐标点
        for (int jj = 0; jj < bean.getCyclingPoints().size(); jj++) {
            TripCyclingPointDataM data = bean.getCyclingPoints().get(jj);
            double lattemp = data.getPointLat();
            double lngtemp = data.getPointLon();
            LatLng pt1 = new LatLng(lattemp, lngtemp);
            if (jj == 0) {//起点
                latstart = data.getPointLat();
                lngstart = data.getPointLon();
                minLat = lattemp;
                maxLat = lattemp;
                minLon = lngtemp;
                maxLon = lngtemp;
            }
            if (jj == (bean.getCyclingPoints().size() - 1)) {//终点
                latstop = data.getPointLat();
                lngstop = data.getPointLon();
            }
            latLngPolygon.add(pt1);

            if (lattemp < minLat) {
                minLat = lattemp;
                bottomPoint = new LatLng(lattemp, lngtemp);
            }

            if (lattemp > maxLat) {
                maxLat = lattemp;
                topPoint = new LatLng(lattemp, lngtemp);
            }

            if (lngtemp < minLon) {
                minLon = lngtemp;
                leftPoint = new LatLng(lattemp, lngtemp);
            }

            if (lngtemp > maxLon) {
                maxLon = lngtemp;
                roghtPoint = new LatLng(lattemp, lngtemp);
            }

        }
        // 计算两点之间的距离，重新设定缩放值，让全部marker显示在屏幕中。
        int distance1 = (int) DistanceUtil.getDistance(topPoint, bottomPoint);
        int distance2 = (int) DistanceUtil.getDistance(leftPoint, roghtPoint);
        LogUtils.d("distance1=" + distance1);
        LogUtils.d("distance2=" + distance2);
        int distance;
        if (distance1 > distance2) {
            distance = distance1;
        } else {
            distance = distance2;
        }
        LogUtils.d("distance=" + distance);
        LatLng point = new LatLng((maxLat + minLat) / 2, (maxLon + minLon) / 2);// 中点
        //地图缩放等级
        int zoomLevel[] = {2000000, 1000000, 500000, 200000, 100000, 50000,
                25000, 20000, 10000, 5000, 2000, 1000, 500, 100, 50, 20, 0};
        int i;
        LogUtils.d("distance=" + distance);
        for (i = 0; i < 17; i++) {
            if (zoomLevel[i] < (distance / 2)) {
                break;
            }
        }
        //根据起点和终点的坐标点计算出距离来对比缩放等级获取最佳的缩放值，用来得到最佳的显示折线图的缩放等级
        float zoom = i + 4;
        if (zoom > 15) {
            zoom = 15;
        }
        LogUtils.d("zoom=" + zoom);
        // 设置当前位置显示在地图中心
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(point, zoom);// 设置缩放比例
        mBaiduMap.animateMapStatus(u);
        /**
         * 创建自定义overlay
         */
        // 起点位置
        LatLng geoPoint = new LatLng(latstart - 0.00004, lngstart);
        // 构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.starting_point);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(geoPoint)
                .icon(bitmap).zIndex(8).draggable(true);

        // 终点位置
        LatLng geoPoint1 = new LatLng(latstop - 0.00004, lngstop);
        // 构建Marker图标
        BitmapDescriptor bitmap1 = BitmapDescriptorFactory
                .fromResource(R.drawable.end_point);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option1 = new MarkerOptions().position(geoPoint1)
                .icon(bitmap1).zIndex(8).draggable(true);
        // 在地图上添加Marker，并显示

        List<OverlayOptions> overlay = new ArrayList<OverlayOptions>();
        overlay.add(option);
        overlay.add(option1);
        mBaiduMap.addOverlays(overlay);

        //开始绘制
        drawMyRoute(latLngPolygon);
    }

    /**
     * 根据数据绘制轨迹
     *
     * @param points2
     */
    protected void drawMyRoute(List<LatLng> points2) {
        if (points2.size() < 2) {
            points2.addAll(points2);
        }
        OverlayOptions options = new PolylineOptions().color(0xFF0090FF)
                .width(10).points(points2);
        mBaiduMap.addOverlay(options);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left:
                finish();
                break;
            case R.id.share:

                break;
            case R.id.head_right:
                mBaiduMap.snapshot(this);
                break;
            default:
                break;
        }
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
        // shareTile, ApiClient.baseUrl + "share/trip_share?id=" + tid, shareContent, share_ll,
        MyUtils.myShareDialog(true,this, new MyUtils.ShareCancelListener() {
            @Override
            public void cancel() {
                sceen_img.setVisibility(View.GONE);
            }

            @Override
            public void mySelect(int pos) {
                if (pos == 0) {
                    MyUtils.shareQzone(StrokeDetailActivity.this, shareTile, ApiClient.baseUrl + "share/trip_share?id=" + tid, shareContent);
                } else if (pos == 1) {
                    MyUtils.shareQQ(StrokeDetailActivity.this, shareTile, ApiClient.baseUrl + "share/trip_share?id=" + tid, shareContent);
                } else if (pos == 2) {
                    share.setVisibility(View.GONE);
                    shareQrCode.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MyUtils.showMoment(StrokeDetailActivity.this, share_ll);
                        }
                    },1000);

                } else if (pos == 3) {
                    MyUtils.showWechat(StrokeDetailActivity.this, shareTile, ApiClient.baseUrl + "share/trip_share?id=" + tid, shareContent);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sceen_img.setVisibility(View.GONE);

    }
}
