package com.danchexia.bikehero.batterymain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.batterymain.battery.BatteryUsingFragment;
import com.danchexia.bikehero.batteryservice.BatteryChangeListen;
import com.danchexia.bikehero.batteryservice.BatteryService;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.jpush.ReceiverBean;
import com.danchexia.bikehero.jpush.myobserveable.JiPushGetData;
import com.danchexia.bikehero.main.MapUtils.BaiduMapHelper;
import com.danchexia.bikehero.batterymain.battery.BatteryIntroductionFragment;
import com.danchexia.bikehero.main.bean.BatteryBean;
import com.danchexia.bikehero.main.bean.BatteryListBean;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.bottomfragment.FragmentBottom;
import com.danchexia.bikehero.main.personal.PersonalActivity;
import com.danchexia.bikehero.main.toplayout.AskFeedbackFragment;
import com.danchexia.bikehero.myviews.AddressEntity;
import com.danchexia.bikehero.utils.DialogUtils;
import com.danchexia.bikehero.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/9/5.
 * description:
 */

public class BatteryMainActivity extends MvpActivity<BatteryMainPresenter, BatteryMainView> implements BatteryMainView,
        View.OnClickListener, BaiduMap.OnMarkerClickListener, BaiduMap.OnMapClickListener,
        BaiduMapHelper.OnMyMapStatusChangeListener, BDLocationListener ,Observer {
    private BatteryMainPresenter myPresenter;

    @Override
    protected BatteryMainPresenter CreatePresenter() {
        return myPresenter = new BatteryMainPresenter(this);
    }

    private ImageView head_left, head_right;//标题栏
    private ImageView map_center;//中心店
    private TextView head_title;//标题
    private MapView mMapView = null;
    private RelativeLayout head_message;
    private ImageView message_tips_icon;
    private FragmentManager fragmentManager;
    private FragmentBottom fragmentBottom;//首页下面的选项
    private BaiduMap mBaiduMap;
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    private LocationClient mLocClient;// 定位相关
    private BatteryListBean mBatteryListBean;
    private PlanNode sNode = null;//起点
    private PlanNode eNode = null;//终点
    private LatLng mCenterLatLng = new LatLng(0, 0);//地图屏幕中间点
    private BatteryService.MyBinder myBinder;
    private BatteryService rideStatusService;
    private BatteryChangeListen serviceBase;
    private Observable observableForSevice;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (BatteryService.MyBinder) service;
            myBinder.startCheckStatus();
            //返回一个MsgService对象
            rideStatusService = ((BatteryService.MyBinder) service).getService();
            serviceBase = new BatteryChangeListen(rideStatusService);
            observableForSevice = serviceBase;
            observableForSevice.addObserver(BatteryMainActivity.this);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_battery);
        initView();
        initBottom();
        initMap();
        initLocation();
        myPresenter.getInvateAndShareParams();
        setMyObserverable();
        Utils.getScreenWithAndHeight(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PgyUpdateManager.register(BatteryMainActivity.this, Config.APPID, new UpdateManagerListener() {
                    @Override
                    public void onUpdateAvailable() {
                        // TODO Auto-generated method stub
                        Toast.makeText(BatteryMainActivity.this, "有新的版本", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                        // TODO Auto-generated method stub
//                        Toast.makeText(MainActivity.this, "已经是最新的版本", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        },1000);
    }
    private Observable observable;
    public void setMyObserverable() {
        JiPushGetData jiPushGetData = MyApplication.getJiPushGetData();
        this.observable = jiPushGetData;
        this.observable.addObserver(this);
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

    private void initView() {
        map_center = (ImageView) findViewById(R.id.map_center);
        message_tips_icon = (ImageView) findViewById(R.id.message_tips_icon);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title = (TextView) findViewById(R.id.head_title);
        head_message = (RelativeLayout) findViewById(R.id.head_message);
        head_left.setImageDrawable(getResources().getDrawable(R.drawable.map_my_bg));
        head_right.setImageDrawable(getResources().getDrawable(R.drawable.nav_seach_bg));
        head_left.setOnClickListener(this);
        head_right.setOnClickListener(this);
        head_message.setOnClickListener(this);
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null) {
            head_title.setText(Utils.object2String(systemParamsBean.getAppName()));
        }
    }
    private void initBottom() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentBottom = new FragmentBottom();
        transaction.replace(R.id.bottom, fragmentBottom);
        // 事务提交
        transaction.commitAllowingStateLoss();
    }
    //初始化地图
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
    @Override
    public void onClick(View v) {
        String token = PreferencesUtils.getString(MyApplication.appContext,"RADISHSAAS_IS_BIND");
            switch (v.getId()) {

                case R.id.head_left:
                    if (TextUtils.isEmpty(token)) {
                        ActivityController.startBindPhone(this);
                    } else {
                        ActivityController.startMy(this);
                    }
                    break;
                case R.id.head_right:
                    ActivityController.startSeach(this);
                    break;
                case R.id.head_message:
                    if (TextUtils.isEmpty(token)) {
                        ActivityController.startBindPhone(this);
                    } else {
                        ActivityController.startMessage(this);
                        message_tips_icon.setVisibility(View.INVISIBLE);
                    }
                    break;
                default:
                    break;
            }
        }
    private String sysCode = null;//选中车辆的code
    private BatteryBean clickedBean;//点击选中的那个图标 电池
    @Override
    public boolean onMarkerClick(Marker marker) {
        clickedBean = null;
        sysCode = marker.getTitle();//获取选中车辆的code
        if (!TextUtils.isEmpty(sysCode)) {
            if (Config.isOpenBattery && mBatteryListBean != null) {//当有电池的时候 给予点击事件
                for (BatteryBean data : mBatteryListBean.getDataList()) {
                    if (data.getSysCode().contentEquals(sysCode)) {
                        map_center.setVisibility(View.GONE);//画线路图的时候隐藏掉
                        LatLng mCenterLatLng = marker.getPosition();
                        eNode = PlanNode.withLocation(mCenterLatLng);
                        clickedBean = data;
                        mSearch.walkingSearch(new WalkingRoutePlanOption().from(sNode).to(eNode));
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (isShowFragmentReservcation){
            mBaiduMap.clear();
            map_center.setVisibility(View.VISIBLE);//取消的时候大头针再显示出来
            initBattery(false,null,0);
            myPresenter.getAllBattery(mCenterLatLng.longitude,mCenterLatLng.latitude,3000);
        }
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
    private long isFreshing = 0;//就第一次点击的时间
    @Override
    public void onChange(MapStatus mapStatus) {
        long secondTime = System.currentTimeMillis();
        if (secondTime - isFreshing > 500) {
            if ((int) DistanceUtil.getDistance(mapStatus.target, mCenterLatLng) > 250) {
                mCenterLatLng = mapStatus.target;
                if (!isShowFragmentReservcation && !isShowFragmentUsing){
                    mBaiduMap.clear();
                    sNode = PlanNode.withLocation(mCenterLatLng);
                    fragmentBottom.setRefreshStart();
                    myPresenter.getAllBattery(mCenterLatLng.longitude,mCenterLatLng.latitude,3000);
                }
            }
            isFreshing = secondTime;//更新firstTime
        }
    }
    private BatteryIntroductionFragment batteryIntroductionFragment;//预约  ------电池
    private boolean isShowFragmentUsing = false;//使用中的fragment
    private boolean isShowFragmentReservcation = false;//预约的fragment
    //预约 ---------电池
    private void initBattery(boolean isShow, BatteryBean clickedBean, int distance) {
        isShowFragmentReservcation = isShow;
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            batteryIntroductionFragment = new BatteryIntroductionFragment(clickedBean,distance);
            transaction.replace(R.id.top_layout, batteryIntroductionFragment);
        } else {
            if (batteryIntroductionFragment != null) {
                transaction.remove(batteryIntroductionFragment);
            }
        }
        // 事务提交
        transaction.commitAllowingStateLoss();
    }
    private BatteryUsingFragment batteryUsingFragment;
    //使用中 ---------电池
    private void initBatteryUsing(boolean isShow) {
        isShowFragmentUsing = isShow;
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            batteryUsingFragment = new BatteryUsingFragment();
            transaction.replace(R.id.top_layout, batteryUsingFragment);
        } else {
            if (batteryUsingFragment != null) {
                transaction.remove(batteryUsingFragment);
            }
        }
        // 事务提交
        transaction.commitAllowingStateLoss();
    }
    private  AskFeedbackFragment askFeedbackFragment;
    //问题反馈
    private void initAskFragment(boolean isShow) {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            askFeedbackFragment = new AskFeedbackFragment();
            transaction.replace(R.id.top_layout, askFeedbackFragment);
        } else {
            if (askFeedbackFragment != null) {
                transaction.remove(askFeedbackFragment);
            }
        }
        // 事务提交
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onRawed(WalkingRouteResult walkingRouteResult) {
        int distance = walkingRouteResult.getRouteLines().get(0).getDistance();
        initBattery(true,clickedBean,distance);
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null || mMapView == null) {
            return;
        }
        LatLng ll = new LatLng(location.getLatitude(),
                location.getLongitude());
        MyApplication.myLongtitude = location.getLongitude();
        MyApplication.myLatitude = location.getLatitude();
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
        myPresenter.getAllBattery(location.getLongitude(),location.getLatitude(),3000);//获取所有蓄电池   每次重新定位以后都去重新获取一次
    }

    @Override
    public void reLocation() {
        if (mLocClient != null){
            mLocClient.start();
        }
    }

    @Override
    public void refreshData() {//刷新
        mBaiduMap.clear();
        initBattery(false,null,0);
        initBatteryUsing(false);
        removeAllBatteryMarkers();
        myPresenter.getAllBattery(MyApplication.myLongtitude,MyApplication.myLatitude,3000);//获取所有蓄电池   每次重新定位以后都去重新获取一次
    }

    @Override
    public void startToPerson() {//去设置功率
        Intent modify = new Intent(BatteryMainActivity.this, PersonalActivity.class);
        startActivityForResult(modify,203);
    }

    private boolean isFragmentRefresh = false;
    @Override
    public void setGetAllBatteryBol() {
        isFragmentRefresh = true;
    }

    private String tripingSysCode;//行程中 电池的code

    @Override
    public void helpFeedback() {

        Long myTripId = PreferencesUtils.getLong(MyApplication.appContext, Config.USERTRIPID, 0);
        if (myTripId > 0){
            ActivityController.startFeedBack(BatteryMainActivity.this, "2", tripingSysCode, myTripId);
        }else{
            ActivityController.startFeedBack(BatteryMainActivity.this, "1", "", -1L);
        }

    }

    @Override
    public void endTripForBattery() {
        myPresenter.endTrip();
    }
    //结束成功
    public void setEndTripSuccess(OnGoing_TripBO bean) {
        DialogUtils.waittingDialogUtilsForBattery(this);
        requestFailedTimeOut();//一分钟后没有反馈如此处理
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //搜索返回的数据
        if (requestCode == 1001) {
            if (data != null) {
                mBaiduMap.clear();
                initBattery(false,null,0);//搜索回来以后 直接把预约界面给隐藏
                AddressEntity addressEntity = data.getParcelableExtra("address");
                LatLng cenpt = new LatLng(addressEntity.getLatitude(), addressEntity.getLongitude());
                MapStatus mMapStatus = new MapStatus.Builder()
                        .target(cenpt)
                        .zoom(18)
                        .build();
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                //改变地图状态
                mBaiduMap.setMapStatus(mMapStatusUpdate);
            }
        }
        if (requestCode == 203) {//去设置功率 返回
            BatteryBean batteryBean = MyUtils.getBatteryData();
            int distance = PreferencesUtils.getInt(MyApplication.appContext, "BATTERY_DISTANCE", 0);
            if (batteryBean != null){
                initBattery(true,batteryBean,distance);
            }
        }
    }
    private List<Marker> batteryOverlayList = new ArrayList<>();
    //显示所有电池的位置
    public void setBatteryMarks(BatteryListBean batteryListBean) {
        removeAllBatteryMarkers();
        if (batteryListBean.getDataList() != null){
            mBatteryListBean = batteryListBean;
            batteryOverlayList.clear();
            for (BatteryBean ben:batteryListBean.getDataList()){
                batteryOverlayList.add((Marker) BaiduMapHelper.setBatteryMarkers(ben,mBaiduMap));
            }
        }
    }

    private void removeAllBatteryMarkers(){
        for (Marker marker:batteryOverlayList){
            marker.remove();
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        myPresenter.getHomeMessage();
        if (isFragmentRefresh){
            isFragmentRefresh = false;
            initBattery(false,null,0);
            mBaiduMap.clear();
            myPresenter.getAllBattery(MyApplication.myLongtitude,MyApplication.myLatitude,3000);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initAskFragment(false);
        if (!TextUtils.isEmpty(PreferencesUtils.getString(this, "RADISHSAAS_IS_BIND"))) {
            myPresenter.onGiongInfo();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        if (isBatteryBindService){
            myBinder.stopCheckStatus();
            ActivityController.unbindOpenService(this, connection);
            ActivityController.stopOpenServiceForBattery(this);
        }
        if(observableForSevice != null) {
            observableForSevice.deleteObserver(this);
        }
        if (observable != null){
            observable.deleteObserver(this);
        }
    }
    private boolean isBatteryBindService = false;
    //更新监听
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof JiPushGetData) {
            JiPushGetData jiPushGetData = (JiPushGetData) o;
            display(jiPushGetData.getReceiverBean());
        }
        if (o instanceof BatteryChangeListen) {
            BatteryChangeListen serviceBase = (BatteryChangeListen) o;
            final OnGoingInfoBean onGoingInfoBean = serviceBase.getOnGoingInfoBean();
            tripingSysCode = "";
            if (onGoingInfoBean != null && onGoingInfoBean.getOnGoing_tripBO() != null){
                OnGoing_TripBO tripBo = onGoingInfoBean.getOnGoing_tripBO();
                LogUtils.d("status==="+tripBo.getStatus());
                switch (tripBo.getStatus()){
                    case 40://
                        initAskFragment(false);
                        DialogUtils.successWaitDialog();
                        if (isBatteryBindService){
                            myBinder.stopCheckStatus();
                            ActivityController.unbindOpenService(this, connection);
                            ActivityController.stopOpenServiceForBattery(this);
                        }
                        ActivityController.startRideOver(BatteryMainActivity.this, 0L);
                        break;
                    case 30://
                        if (tripBo.getBatteryBO() != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (batteryUsingFragment != null) {
                                        batteryUsingFragment.setData(onGoingInfoBean);
                                    }
                                }
                            });
                        }
                        tripingSysCode = tripBo.getSysCode();//获取电池的code
                        if (tripBo.getDoingFeedbacks() != null && tripBo.getDoingFeedbacks().size() > 0) {
                            initAskFragment(true);
                            return;
                        } else {
                            initAskFragment(false);
                        }
                        break;
                    default:
                        break;
                }
            }else{

                fragmentBottom.setMyVisiable();
                initAskFragment(false);
                if (isBatteryBindService){
                    myBinder.stopCheckStatus();
                    ActivityController.unbindOpenService(this, connection);
                    ActivityController.stopOpenServiceForBattery(this);
                }
                DialogUtils.successWaitDialog();
                initBatteryUsing(false);
                final Long myTripId = PreferencesUtils.getLong(MyApplication.appContext, Config.USERTRIPID, 0);
                if (myTripId != null){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ActivityController.startRideOver(BatteryMainActivity.this, myTripId);
                        }
                    },1500);

                }
            }
        }
    }
    //如果获取到自定义消息是10002 则去支付
    private void display(ReceiverBean receiverBean) {
//        logout:"99999";
//        订单创建:"10001"
//        订单结束"10002"
        if (receiverBean.getMsgType().equals("10002") || receiverBean.getMsgType().equals("10001")) {
//            message_tips_icon.setVisibility(View.VISIBLE);
        }
        if (receiverBean.getMsgType().equals("99999") ){
            LogUtils.d("异地登录了");
            PreferencesUtils.putString(this,"RADISHSAAS_IS_BIND","");
        }
    }
    private void requestFailedTimeOut(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DialogUtils.successWaitDialogNoOk();//如果一分钟后没有数据返回就dismiss掉
            }
        },60000);
    }
    //查询当前行程状态
    public void setMyStatus(OnGoingInfoBean onGoingInfoBean) {
        tripingSysCode = "";
    /** 状态  10:开锁中 20: 开锁失败 30:行驶中 40:未支付 50:已支付 */
        if (onGoingInfoBean != null && onGoingInfoBean.getOnGoing_tripBO() != null){
            OnGoing_TripBO tripBo = onGoingInfoBean.getOnGoing_tripBO();
            switch (tripBo.getStatus()){
                case 40://
                    strokePay();
                    break;
                case 30://
                    ActivityController.startOpenServiceForBattery(this);
                    ActivityController.bindOpenServiceForBattery(this, connection);
                    isBatteryBindService = true;
                    if (tripBo.getDoingFeedbacks() != null && tripBo.getDoingFeedbacks().size() > 0) {
                        initAskFragment(true);
                        return;
                    } else {
                        initAskFragment(false);
                    }
                    if (!batteryUsingFragment.isUsingFound) {//如果不是在现实中 就去显示
                        initBatteryUsing(true);//显示使用中
                    }
                    mBaiduMap.clear();
                    fragmentBottom.setMyGone();
                    tripingSysCode = tripBo.getSysCode();
                    PreferencesUtils.putLong(MyApplication.appContext,Config.USERTRIPID,tripBo.getId());
                    break;

                default:
                    break;
            }

        }else{
            initBatteryUsing(false);
            fragmentBottom.setMyVisiable();
        }
    }
    /**
     * 行程支付
     */
    private void strokePay() {
        DialogUtils.successWaitDialog();
        StanderdDialog dialog = new StanderdDialog(BatteryMainActivity.this, getString(R.string.toast_23),
                getString(R.string.toast_24), getString(R.string.toast_25), getString(R.string.toast_26)
                , new StanderdDialog.OnDialogClickListener() {

            @Override
            public void doAnyClick() {

            }

            @Override
            public void doMainClick() {
                ActivityController.startRideOver(BatteryMainActivity.this, 0L);
            }

        });
        dialog.show();
    }

}
