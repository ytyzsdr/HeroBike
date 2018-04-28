package com.danchexia.bikehero.main.MapUtils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.main.bean.BatteryBean;
import com.danchexia.bikehero.main.bean.BicycleData;
import com.danchexia.bikehero.main.bean.ParkDetailBean;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.colours.client.model.Point;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/26.
 * description:地图工具类
 */

public class BaiduMapHelper {
    public static List<OverlayOptions> optionsList = new ArrayList<>();//地图上的所有点
    public static List<Overlay> overlayList = new ArrayList<>();//地图上的所有点
    public static MyWalkingRoutOverlay myWalkingRoutOverLay = null;

    /**
     * 构建MarkerOption，用于在地图上添加Marker
     */
    public static Overlay setMarkers(BicycleData data, BaiduMap mBaiduMap) {
        if(data == null || data.getPoint() == null){
            return null;
        }
        LatLng point = new LatLng(data.getPoint().getY(), data.getPoint().getX());
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.bike_position);
        MarkerOptions option = new MarkerOptions()
                .title(data.getSysCode())
                .position(point)
                .icon(bitmap)
                .zIndex(0).period(10);
//        option.animateType(MarkerOptions.MarkerAnimateType.grow);生长动画
//        optionsList.add(option);
        return mBaiduMap.addOverlay(option);
    }
    /**
     * 构建MarkerOption，用于在地图上添加Marker   电池的
     */
    public static Overlay setBatteryMarkers(BatteryBean data, BaiduMap mBaiduMap) {
        if(data == null || data.getPoint() == null){
            return null;
        }
        LatLng point = new LatLng(data.getPoint().getY(), data.getPoint().getX());
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.battery_position);
        MarkerOptions option = new MarkerOptions()
                .title(data.getSysCode())
                .position(point)
                .anchor(0.5f,0.9f)
                .icon(bitmap)
                .zIndex(9).period(10);
       return mBaiduMap.addOverlay(option);
    }
    /**
     * 构建停车图标 //多边形
     */
    public static List<Overlay> strokeOverlayList = new ArrayList<>();
    public static void setParkStroke(ParkDetailBean data, BaiduMap mBaiduMap) {
        //定义多边形的五个顶点
        List<LatLng> pts = new ArrayList<LatLng>();
        Double xToatl = 0d,yTotal = 0d;
        for(int i = 0;i < data.getPoints().size();i++){
            Point vo = data.getPoints().get(i);
            LatLng pt = new LatLng(vo.getY(), vo.getX());
            pts.add(pt);
            xToatl += vo.getX();
            yTotal += vo.getY();
        }
        //构建用户绘制多边形的Option对象
        OverlayOptions polygonOption = new PolygonOptions()
                .points(pts)
                .stroke(new Stroke(1, 0xFFFFBB4A))
                .fillColor(0x2DFFBB4A);
        //在地图上添加多边形Option，用于显示
        Overlay myOverlay = mBaiduMap.addOverlay(polygonOption);
        strokeOverlayList.add(myOverlay);
        LatLng centerPoint = new LatLng(yTotal/data.getPoints().size(), xToatl/data.getPoints().size());
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.parking_icon);
        MarkerOptions option = new MarkerOptions()
                .title("park"+data.getId())
                .position(centerPoint)
                .icon(bitmap)
                .anchor(0.5f,0.5f)
                .zIndex(0).period(10);
        Overlay myPark = mBaiduMap.addOverlay(option);
        strokeOverlayList.add(myPark);
    }
    /**
     * 构建停车图标 //圆
     */
    public static void setParkCircle(ParkDetailBean data, BaiduMap mBaiduMap) {
        //定义多边形的五个顶点
        LatLng pt1 = new LatLng(data.getLocationLat(), data.getLocationLon());
        //构建用户绘制多边形的Option对象
        OverlayOptions polygonOption = new CircleOptions()
                .center(pt1)
                .radius(data.getDistance())
                .stroke(new Stroke(1, 0xFFFFBB4A))
                .fillColor(0x2DFFBB4A);
        //在地图上添加多边形Option，用于显示
        Overlay myOverlay =  mBaiduMap.addOverlay(polygonOption);
        strokeOverlayList.add(myOverlay);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.parking_icon);
        MarkerOptions option = new MarkerOptions()
                .title("park"+data.getId())
                .position(pt1)
                .icon(bitmap)
                .anchor(0.5f,0.5f)
                .zIndex(0).period(10);
        Overlay myPark = mBaiduMap.addOverlay(option);
        strokeOverlayList.add(myPark);
    }

    private static OnMyMapStatusChangeListener onMyMapStatusChangeListener;

    public interface OnMyMapStatusChangeListener {
        abstract void onChange(MapStatus mapStatus);

        abstract void onRawed(WalkingRouteResult walkingRouteResult);
    }

    public void setOnMyMapStatusChangeListener(OnMyMapStatusChangeListener onMyMapStatusChangeListener) {
        this.onMyMapStatusChangeListener = onMyMapStatusChangeListener;
    }

    /**
     * 地图状态变化的监听
     *
     * @param mBaiduMap
     * @return
     */
    public static void initMapStatsListener(BaiduMap mBaiduMap) {
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                onMyMapStatusChangeListener.onChange(mapStatus);
            }
        });
    }

    /**
     * 画线 线路
     */
    public static void initSeachMap(RoutePlanSearch mSearch, final BaiduMap mBaiduMap) {
        mSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                }
                if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                    // result.getSuggestAddrInfo()
                    return;
                }
                if (myWalkingRoutOverLay != null) {//清楚上次的线路图
                    myWalkingRoutOverLay.removeFromMap();
                }
                if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    MyWalkingRoutOverlay overlay = new MyWalkingRoutOverlay(mBaiduMap);
                    //设置路线数据
                    overlay.setData(walkingRouteResult.getRouteLines().get(0));
                    overlay.addToMap();  //将所有overlay添加到地图中
                    overlay.zoomToSpan();//缩放地图
                    myWalkingRoutOverLay = overlay;
                    onMyMapStatusChangeListener.onRawed(walkingRouteResult);
                }
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        });
    }
    public static void setInfoWindow(BaiduMap mBaiduMap, LatLng latLng, int dis, String time) {
        LayoutInflater inflater = (LayoutInflater) MyApplication.appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.info_window_map, null);
        TextView distance = (TextView) view.findViewById(R.id.distance);
        TextView walkTime = (TextView) view.findViewById(R.id.walkTime);
        if (dis > 1000){
            distance.setText(Utils.object2String((float)dis/1000) + "千米");
        }else {
            distance.setText(Utils.object2String(dis) + "米");
        }

        walkTime.setText(Utils.object2String(time));
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(view, latLng, -50);
        //显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
    }
    //计算两个坐标点之间的直线距离
    public static Double getMinDistance(Double lon1,Double lat1,Double lon2,Double lat2){
        if (lon1 == null || lon2 ==null || lat1 == null || lat2 == null){
            return 100d;
        }
        Double lon = Math.abs(lon1 - lon2);
        Double lat = Math.abs(lat1 - lat2);
        Double dis = Math.sqrt(lon*lon + lat*lat);
        return dis;
    }
}
