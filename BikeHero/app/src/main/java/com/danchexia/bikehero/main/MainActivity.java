package com.danchexia.bikehero.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
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
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.danchexia.bikehero.main.member.RechartMemberActivity;
import com.orhanobut.logger.Logger;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.bluetooth.AES;
import com.danchexia.bikehero.bluetooth.BluetoothLeService;
import com.danchexia.bikehero.bluetooth.BluetoothUtils;
import com.danchexia.bikehero.bluetooth.ConnectBlueTooth;
import com.danchexia.bikehero.bluetooth.MyNewThread;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.jpush.ReceiverBean;
import com.danchexia.bikehero.jpush.myobserveable.JiPushGetData;
import com.danchexia.bikehero.main.MapUtils.BaiduMapHelper;
import com.danchexia.bikehero.batterymain.battery.BatteryIntroductionFragment;
import com.danchexia.bikehero.main.bean.BatteryBean;
import com.danchexia.bikehero.main.bean.BicycleBean;
import com.danchexia.bikehero.main.bean.BicycleData;
import com.danchexia.bikehero.main.bean.NearByPark;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.main.bean.OnGoing_ReserveBO;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.bean.ParkDetailBean;
import com.danchexia.bikehero.main.bean.ParkListBean;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.bottomfragment.FragmentBottom;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.feedback.FeedBackActivity;
import com.danchexia.bikehero.main.personal.PersonalActivity;
import com.danchexia.bikehero.main.toplayout.AskFeedbackFragment;
import com.danchexia.bikehero.main.toplayout.FragmentParkLocation;
import com.danchexia.bikehero.main.toplayout.RecervationIngFragment;
import com.danchexia.bikehero.main.toplayout.ReversationFragment;
import com.danchexia.bikehero.main.toplayout.UseingFragment;
import com.danchexia.bikehero.myviews.AddressEntity;
import com.danchexia.bikehero.service.BlueToothThread;
import com.danchexia.bikehero.service.RideStatusService;
import com.danchexia.bikehero.service.ServiceBase;
import com.danchexia.bikehero.utils.DialogUtils;
import com.danchexia.bikehero.utils.MyUtils;
import com.umeng.analytics.MobclickAgent;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import vc.thinker.mvp.ControllerActivity;
import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

public class MainActivity extends MvpActivity<MainPresenter, IMainView> implements IMainView, View.OnClickListener,
        BDLocationListener, BaiduMapHelper.OnMyMapStatusChangeListener,
        BaiduMap.OnMarkerClickListener, BaiduMap.OnMapClickListener, Observer, BeaconConsumer, RangeNotifier {
    private MainPresenter presenter;
    private ImageView head_left, head_right;//标题栏
    private ImageView map_center;//中心店
    private TextView head_title;//标题
    private LinearLayout layoutOpenMember;
    private TextView tvOpenMember;
    private MapView mMapView = null;
    private FragmentManager fragmentManager;
    private FragmentBottom fragmentBottom;//首页下面的选项
    private ReversationFragment fragmentRecervation;//预约
    private BatteryIntroductionFragment batteryIntroductionFragment;//预约  ------电池
    private RecervationIngFragment fragmentRecervationIng;//预约中   取消预约
    private UseingFragment usingFragment;//骑行中
    private BaiduMap mBaiduMap;

    private LocationClient mLocClient;// 定位相关
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    private PlanNode sNode = null;//起点
    private PlanNode eNode = null;//终点
    private boolean isShowRecervation = false;//是否显示预约
    private String sysCode = null;//选中车辆的code
    private RideStatusService.MyBinder myBinder;
    private boolean canClick = true;//默认可以点击
    private BicycleBean myBicycleBean;//所有车辆
    private String selectedBikeAddress;
    private boolean canLocationController = true;
    private boolean returnFromLocation = false;//选择定位回来后调用
    BitmapDescriptor mCurrentMarker = null;
    private MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
    private static final int accuracyCircleFillColor = 0x00000000;
    private static final int accuracyCircleStrokeColor = 0x00000000;
    private Boolean isReversatinging = false;//是否在预约中
    private OnGoing_TripBO onUsingTripData;//骑行中的数据
    private FragmentParkLocation fragmentParkLocation;//定点停车
    private Observable observable;
    private Observable observableForSevice;
    private ImageView message_tips_icon;
    private ImageView mMessage_icon;
    private boolean canPullAllBike = true;//默认去加载所有车辆
    //监听数据
    private ReceiverUpdata receiverUpdata;
    public static final String MESSAGE_RECEIVED_ACTION = "com.danchexia.bikehero.MESSAGE_RECEIVED_ACTION_USING_COLORS";
    private RideStatusService rideStatusService;
    private ServiceBase serviceBase;
    private StanderdDialog dialog;//打开蓝牙的提醒
    private int tripOpenType = -1;//行程中的蓝牙锁类型
    private boolean rideServiceConnection = false;//服务是否开启
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (RideStatusService.MyBinder) service;
            myBinder.startCheckStatus();
            //返回一个MsgService对象
            rideStatusService = ((RideStatusService.MyBinder) service).getService();
            serviceBase = new ServiceBase(rideStatusService);
            observableForSevice = serviceBase;
            observableForSevice.addObserver(MainActivity.this);
        }
    };

    @Override
    protected MainPresenter CreatePresenter() {
        return presenter = new MainPresenter(this);
    }

    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSIONS = 1;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);
        initView();
        initBottom();
        initMap();
        initLocation();
        initReceiver();
        setMyObserverable();//关于极光的
        Utils.getScreenWithAndHeight(this);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                PgyUpdateManager.register(MainActivity.this, Config.APPID, new UpdateManagerListener() {
//                    @Override
//                    public void onUpdateAvailable() {
//                        // TODO Auto-generated method stub
//                        Toast.makeText(MainActivity.this, "有新的版本", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNoUpdateAvailable() {
//                        // TODO Auto-generated method stub
////                        Toast.makeText(MainActivity.this, "已经是最新的版本", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }, 1000);
//

//        new LogUpToKiBaNA(this,"Hello kibana","error",217).start();
        MyUtils.canUpdata(this,false);//控制是否强制更新

    }

    public void setMyObserverable() {
        JiPushGetData jiPushGetData = MyApplication.getJiPushGetData();
        this.observable = jiPushGetData;
        this.observable.addObserver(this);
    }


    private boolean blueLockIsOpen = true;//蓝牙锁状态
    private boolean havefeedback = false;//有问题反馈

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof JiPushGetData) {
            JiPushGetData jiPushGetData = (JiPushGetData) o;
            display(jiPushGetData.getReceiverBean());
        }
        if (o instanceof ServiceBase) {
            ServiceBase serviceBase = (ServiceBase) o;
            OnGoingInfoBean onGoingInfoBean = serviceBase.getOnGoingInfoBean();
            MyUtils.d(onGoingInfoBean);
            tripStopType = onGoingInfoBean.getOnGoing_tripBO().getStopType();
            if (onGoingInfoBean.getOnGoing_tripBO().getDoingFeedbacks() != null && onGoingInfoBean.getOnGoing_tripBO().getDoingFeedbacks().size() > 0) {
                connectBlueTooth.setClearLisen();//关锁后就不监听了
                havefeedback = true;
                return;
            }else{
                havefeedback = false;
            }
            LogUtils.w("收到订阅的service消息");

            if (onGoingInfoBean != null && onGoingInfoBean.getOnGoing_tripBO() != null && onGoingInfoBean.getOnGoing_tripBO().getBicycle() != null) {
                int openType = onGoingInfoBean.getOnGoing_tripBO().getBicycle().getOpenType();
                if (openType == 2 || openType == 4 || openType == 5) {
                    final BlueToothThread myBlueToothThread = new BlueToothThread(this);
                    myBlueToothThread.setOnCloseListener(new BlueToothThread.OnCloseListener() {
                        @Override
                        public void onClose() {
                            LogUtils.d("==============endMyTripForBlue==247");
                            if (!havefeedback) {
                                endMyTripForBlue();
                            }
                        }

                        @Override
                        public void show(final BluetoothAdapter mBluetoothAdapter) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (dialog == null) {
                                        dialog = new StanderdDialog(MainActivity.this, "无法连接蓝牙, 请打开您的手机蓝牙后重试!", "知道了",
                                                new StanderdDialog.OnDialogClickListener() {
                                                    @Override
                                                    public void doAnyClick() {
                                                    }

                                                    @Override
                                                    public void doMainClick() {
                                                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                                        startActivityForResult(enableBtIntent, 403);
                                                        dialog.dismiss();
                                                    }
                                                });
                                    } else {
                                        if (!dialog.isShowing()) {
                                            dialog.show();
                                        }
                                    }
                                }
                            });

                        }
                    });
                    LogUtils.w("blueLockIsOpen===============" + blueLockIsOpen);
                    if (blueLockIsOpen) {
                        LogUtils.w("scanBluetoothStopSign====" + scanBluetoothStopSign);
                        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                        BluetoothAdapter MyAdapterBlue = bluetoothManager.getAdapter();
                        Logger.d("MyAdapterBlue.isEnabled()====" + MyAdapterBlue.isEnabled());
                        if (!MyAdapterBlue.isEnabled()) {
                            BlueConnectAgain();
                            scanBluetoothStopSign = false;//如果过程中蓝牙断开了
                        }else {
                            if (!scanBluetoothStopSign) {
                                Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
                                bindService(gattServiceIntent, BluetoothUtils.mServiceConnection, BIND_AUTO_CREATE);
                                connectBlueTooth.registerMyReceiver();
                                Logger.d("BluetoothUtils.isConnectBluetooth()=" + BluetoothUtils.isConnectBluetooth());
                                Logger.d("BluetoothLeService.mConnectionState=" + BluetoothLeService.mConnectionState);
                                if (BluetoothUtils.isConnectBluetooth() && BluetoothLeService.mConnectionState == 2) {
                                    connectBlueTooth.setOnLockCloseSuccessListener(new ConnectBlueTooth.OnLockCloseSuccessListener() {
                                        @Override
                                        public void onCloseSuccess() {
                                            endMyTripForBlue();
                                            LogUtils.w("=====关锁成功了====");
                                            connectBlueTooth.setClearLisen();//关锁后就不监听了
                                        }
                                    });
                                    BluetoothUtils.getLockstatus();
                                } else {
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        LogUtils.d("===========adadadadad========start");
//                                        myBlueToothThread.start();
//                                        scanBluetoothStopSign = true;
//                                    }
//                                }, 2000);
                                    trip_unlock_forBluetooth();
                                    scanBluetoothStopSign = true;
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private boolean scanBluetoothStopSign = false;//是否去扫描设备是否停运

    //如果获取到自定义消息是10002 则去支付
    private void display(ReceiverBean receiverBean) {
//        logout:"99999";
//        订单创建:"10001"
//        订单结束"10002"
        if (receiverBean.getMsgType().equals("10002") || receiverBean.getMsgType().equals("10001")) {
//            message_tips_icon.setVisibility(View.VISIBLE);
        }
        if (receiverBean.getMsgType().equals("808")) {
            if (receiverBean.isClicked()) {
                presenter.getMyData();//获取我的个人数据
            }
        }
    }

    private void initReceiver() {
        receiverUpdata = new ReceiverUpdata();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(receiverUpdata, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        LogUtils.d("A onStart");
        presenter.getSystemParams();//获取系统配置
        presenter.getInvateAndShareParams();//获取系统配置关于分享的
        presenter.getMyData();//获取我的个人数据
        initAskFragment(false);
        if (!TextUtils.isEmpty(PreferencesUtils.getString(this, "RADISHSAAS_IS_BIND"))) {
            isStartEdOver = true;
            presenter.onGiongInfo();//定位结束以后去请求当前用户的状态
            presenter.getHomeMessage();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("A onResume");
        mMapView.onResume();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
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

    //定位
    private void initLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        /*mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.rotate_triangle);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode,
                true, mCurrentMarker, accuracyCircleFillColor, accuracyCircleStrokeColor));*/
        MyLocationConfiguration configuration = new MyLocationConfiguration(mCurrentMode,
                false, null, accuracyCircleFillColor, accuracyCircleStrokeColor);
        mBaiduMap.setMyLocationConfigeration(configuration);
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
        mMessage_icon = (ImageView) findViewById(R.id.message_icon);
        map_center = (ImageView) findViewById(R.id.map_center);
        message_tips_icon = (ImageView) findViewById(R.id.message_tips_icon);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        head_title = (TextView) findViewById(R.id.head_title);
        layoutOpenMember = (LinearLayout) findViewById(R.id.layout_open_member);
        tvOpenMember = (TextView) findViewById(R.id.tv_open_member);
        head_left.setOnClickListener(this);
        head_right.setOnClickListener(this);
        mMessage_icon.setOnClickListener(this);
        tvOpenMember.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String token = PreferencesUtils.getString(MyApplication.appContext, "RADISHSAAS_IS_BIND");
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

            case R.id.message_icon:
                if (TextUtils.isEmpty(token)) {
                    ActivityController.startBindPhone(this);
                } else {
                    ActivityController.startMessage(this);
                    message_tips_icon.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.tv_open_member://开通会员
                if (TextUtils.isEmpty(token)){
                    ActivityController.startBindPhone(this);
                }else {
                    Intent guide = new Intent(this, RechartMemberActivity.class);
                    guide.putExtra("ISVIP",false);
                    guide.putExtra("REMAINDATE","");
                    guide.putExtra("REMAINDATELONG","");
                    startActivityForResult(guide,204);
                }
                break;
            default:
                break;
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

    //预约
    private void initReservation(boolean isShow, int distance, String time, String location) {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            fragmentRecervation = new ReversationFragment(distance, time, location);
            transaction.replace(R.id.top_layout, fragmentRecervation);
        } else {
            if (fragmentRecervation != null) {
                transaction.remove(fragmentRecervation);
            }
        }
        // 事务提交
        transaction.commitAllowingStateLoss();
    }

    //预约 ---------电池
    private void initBattery(boolean isShow, BatteryBean clickedBean, int distance) {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            batteryIntroductionFragment = new BatteryIntroductionFragment(clickedBean, distance);
            transaction.replace(R.id.top_layout, batteryIntroductionFragment);
        } else {
            if (batteryIntroductionFragment != null) {
                transaction.remove(batteryIntroductionFragment);
            }
        }
        // 事务提交
        transaction.commitAllowingStateLoss();
    }

    //预约中 -----取消预约
    private void initReservationIng(boolean isShow, OnGoing_ReserveBO onGoing_reserveBO) {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            isReversatinging = true;
            fragmentRecervationIng = new RecervationIngFragment(onGoing_reserveBO);
            transaction.replace(R.id.top_layout, fragmentRecervationIng);
            isShowRecervation = true;
        } else {
            isReversatinging = false;
            if (fragmentRecervationIng != null) {
                transaction.remove(fragmentRecervationIng);
                isShowRecervation = false;
            }
        }
        // 事务提交
        transaction.commitAllowingStateLoss();
    }

    //骑行中
    private void initUsingFragment(boolean isShow, OnGoing_TripBO onGoing_tripBO) {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            if (usingFragment == null || !usingFragment.isFrount) {//如果是显示中的 就不要去重复添加了
                onUsingTripData = onGoing_tripBO;
                usingFragment = new UseingFragment(onGoing_tripBO);
                transaction.replace(R.id.top_layout, usingFragment);
            }
        } else {
            if (usingFragment != null) {
                transaction.remove(usingFragment);
            }
        }
        // 事务提交
        transaction.commitAllowingStateLoss();
    }

    //定点停车
    private void initParkFragment(boolean isShow, OnGoing_TripBO bo) {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            onUsingTripData = bo;
            if (myBinder != null) {
                myBinder.stopCheckStatus();
                scanBluetoothStopSign = false;//如果过程中蓝牙断开了
                blueLockIsOpen = true;
            }
            fragmentParkLocation = new FragmentParkLocation(bo);
            transaction.replace(R.id.top_layout, fragmentParkLocation);
        } else {
            if (fragmentParkLocation != null) {
                transaction.remove(fragmentParkLocation);
            }
        }
        // 事务提交
        transaction.commitAllowingStateLoss();
    }

    AskFeedbackFragment askFeedbackFragment;

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
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        unregisterReceiver(receiverUpdata);
        observable.deleteObserver(this);
        if (observableForSevice != null) {
            observableForSevice.deleteObserver(this);
        }
        LogUtils.d("A被干掉了");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        LogUtils.d("A被onPause");
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
        if (canLocationController) {
            canLocationController = false;
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);//设为中心点
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            MyApplication.myLatitude = location.getLatitude();//每次定位都保存下自己的位置
            MyApplication.myLongtitude = location.getLongitude();
            LogUtils.d("553canPullAllBike===" + canPullAllBike);
            if (canPullAllBike) {
                presenter.getAllBicycleData(location.getLatitude(), location.getLongitude(), 3000);//获取当前位置的3000米范围内的车辆
            }
            mCenterLatLng = new LatLng(MyApplication.myLatitude, MyApplication.myLongtitude);
            sNode = PlanNode.withLocation(mCenterLatLng);

        }
        MyApplication.myLatitude = location.getLatitude();//每次定位都保存下自己的位置
        MyApplication.myLongtitude = location.getLongitude();
        if (getUseLocation) {
            getUseLocation = false;
            LogUtils.d("=========================generalEndTrip===628");
            generalEndTrip(true, "NOBLUE");
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @Override
    public void restartLocation() {
        canLocationController = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.d("onActivityResult=requestCode=" + requestCode);
        //搜索返回的数据
        if (requestCode == 1001) {
            LogUtils.d("搜索返回的数据" + data);
            if (data != null) {
                AddressEntity addressEntity = data.getParcelableExtra("address");
                LogUtils.d("addressEntity.getLatitude(),=" + addressEntity.getLatitude());
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
        if (requestCode == 403) {//蓝牙打开回调
            if (resultCode == Activity.RESULT_OK) {
                scanBluetoothStopSign = false;//如果过程中蓝牙断开了
            }
        }
        if (requestCode == 303) {
            mBaiduMap.clear();
            fragmentBottom.setRefreshStart();
        }
        if (requestCode == BluetoothUtils.REQUEST_ENABLE_BT) {
//            seachBluePark();
        }
        if (requestCode == 203) {//去设置功率 返回
            BatteryBean batteryBean = MyUtils.getBatteryData();
            int distance = PreferencesUtils.getInt(MyApplication.appContext, "BATTERY_DISTANCE", 0);
            if (batteryBean != null) {
                initBattery(true, batteryBean, distance);
            }
        }
        if (requestCode == 408) {//行程中问题反馈 返回
            if (resultCode == RESULT_OK) {
                endMyTripForBlue();
            }
        }
        if (requestCode == 204){
            if (requestCode == RESULT_OK){
                presenter.getMyData();
            }
        }
    }

    /**
     * 请求所有车辆后 返回的车辆信息
     *
     * @param bicycleBean
     */
    private List<Marker> batteryOverlayList = new ArrayList<>();

    public void addMarkers(BicycleBean bicycleBean) {
        myBicycleBean = bicycleBean;
        removeAllBatteryMarkers();
        for (BicycleData data : bicycleBean.getListBike()) {
            batteryOverlayList.add((Marker) BaiduMapHelper.setMarkers(data, mBaiduMap));
        }
    }

    private void removeAllBatteryMarkers() {
        for (Marker marker : batteryOverlayList) {
            LogUtils.d("开始删除");
            marker.remove();
        }
        batteryOverlayList.clear();
    }

    private LatLng mCenterLatLng = new LatLng(0, 0);
    private long isFreshing = 0;//就第一次点击的时间

    /**
     * 地图状态监听
     *
     * @param mapStatus
     */
    @Override
    public void onChange(MapStatus mapStatus) {
        long secondTime = System.currentTimeMillis();
        if (secondTime - isFreshing > 500) {
            if ((int) DistanceUtil.getDistance(mapStatus.target, mCenterLatLng) > 250) {
                mCenterLatLng = mapStatus.target;
                if (!isShowRecervation) {//有状态的时候 不去刷新
                    sNode = PlanNode.withLocation(mCenterLatLng);

                    fragmentBottom.setRefreshStart();
                } else {
                    if (isStopType) {
                        if (parkIsClick) {
                            mBaiduMap.clear();
                            presenter.getAllParkList(mCenterLatLng.longitude, mCenterLatLng.latitude, 3000);
                        }
                    }
                }
            }
            isFreshing = secondTime;//更新firstTime
        }
    }

    /**
     * 路线画完以后调用
     *
     * @param walkingRouteResult
     */
    private String endAddress = " ";

    @Override
    public void onRawed(WalkingRouteResult walkingRouteResult) {
        int distance = walkingRouteResult.getRouteLines().get(0).getDistance();
        String time = String.valueOf(Utils.wait2wei((double) walkingRouteResult.getRouteLines().get(0).getDuration() / 60));
        if (isStopType) {
            BaiduMapHelper.setInfoWindow(mBaiduMap, eNode.getLocation(), distance, endAddress);
        }
        if (!isReversatinging && canClick) {
            if (mapMarkClickType.equals("bicycle")) {
                initReservation(true, distance, time, selectedBikeAddress);
            }
            if (mapMarkClickType.equals("battery")) {
                initBattery(true, clickedBean, distance);
            }
        } else {
            if (mapMarkClickType.equals("bicycle")) {
                initReservation(false, distance, time, selectedBikeAddress);
            }
            if (mapMarkClickType.equals("battery")) {
                initBattery(false, clickedBean, distance);
            }
        }
    }

    private String mapMarkClickType = "null";
    private BatteryBean clickedBean;//点击选中的那个图标 电池

    @Override
    public boolean onMarkerClick(Marker marker) {
        mapMarkClickType = "null";
        clickedBean = null;
        if (canClick) {
            sysCode = marker.getTitle();//获取选中车辆的code
            if (!TextUtils.isEmpty(sysCode)) {
                if (myBicycleBean != null) {
                    for (BicycleData data : myBicycleBean.getListBike()) {
                        if (data.getSysCode().contentEquals(sysCode)) {
                            mapMarkClickType = "bicycle";
                            selectedBikeAddress = data.getLocationDetails();
                            map_center.setVisibility(View.GONE);//画线路图的时候隐藏掉
                            LatLng mCenterLatLng = marker.getPosition();
                            eNode = PlanNode.withLocation(mCenterLatLng);
                            isShowRecervation = true;//画完线路后会显示预约界面，因此赋值true 不要让onChange运行
                            mSearch.walkingSearch(new WalkingRoutePlanOption().from(sNode).to(eNode));

                        }
                    }
                }

            }
        } else {
            sysCode = marker.getTitle();
            if (!TextUtils.isEmpty(sysCode)) {
                if (sysCode.contains("park")) {
                    for (ParkDetailBean data : parkListBean.getDataList()) {
                        if (("park" + data.getId()).contentEquals(sysCode)) {
                            endAddress = data.getLocationAddress();
                            map_center.setVisibility(View.GONE);//画线路图的时候隐藏掉
                            LatLng mCenterLatLng = marker.getPosition();
                            eNode = PlanNode.withLocation(mCenterLatLng);
                            isShowRecervation = true;//画完线路后会显示预约界面，因此赋值true 不要让onChange运行
                            mSearch.walkingSearch(new WalkingRoutePlanOption().from(sNode).to(eNode));
                            parkIsClick = false;
                        }
                    }
                }

            }
        }
        return false;

    }

    private boolean parkIsClick = true;

    @Override
    public void onMapClick(LatLng latLng) {
        if (canClick) {
            LogUtils.d("=============地图点击======清除====================");
            mBaiduMap.clear();
            isReversatinging = false;
            map_center.setVisibility(View.VISIBLE);//取消预约的时候显示
            initReservation(false, 0, null, null);
            restartLocation();
            if (canPullAllBike) {
                presenter.getAllBicycleData(mCenterLatLng.latitude, mCenterLatLng.longitude, 3000);//获取当前位置的3000米范围内的车辆
            }
        } else {
            parkIsClick = true;
        }
    }

    //控制地图是否可以点击
    public void setMapAndMarkerClick(boolean canClick) {
        this.canClick = canClick;
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    /**
     * 预约
     */
    @Override
    public void recervationBike() {
        presenter.reserve(sysCode);
    }

    @Override
    public void refreshAll() {
        if (canPullAllBike) {
            presenter.getAllBicycleData(mCenterLatLng.latitude, mCenterLatLng.longitude, 3000);//获取当前位置的3000米范围内的车辆
        }
    }

    /**
     * 寻车
     */
    @Override
    public void ring() {
        presenter.ring();
    }

    /**
     * 取消预约
     */
    @Override
    public void canselRecervation() {
        presenter.canselRecervation();
        mBaiduMap.clear();
        restartLocation();//取消预约时重新定位
    }

    /**
     * 获取当前状态
     */
    @Override
    public void OnGoingStatus() {
        presenter.onGiongInfo();
        mBaiduMap.clear();
        restartLocation();//取消预约时重新定位
    }

    @Override
    public void helpFeedback() {
        LogUtils.d("tripMyStatus================" + tripMyStatus);
        if (tripMyStatus == MyUtils.RIDE_STATUS_3) {
            if (BluetoothUtils.mBluetoothLeService != null) {
                BluetoothUtils.mBluetoothLeService.disconnect();
                BluetoothUtils.mBluetoothLeService.close();
            }
            Intent question = new Intent(MainActivity.this, FeedBackActivity.class);
            question.putExtra("TYPE", "2");
            question.putExtra("SYSCODE", onUsingTripData.getSysCode());
            question.putExtra("TRIPID", onUsingTripData.getId());
            question.putExtra("TRIPOPENTYPE", tripOpenType);
            question.putExtra("PARKTYPE", onUsingTripData.getStopType());
            startActivityForResult(question, 408);
        } else {
            ActivityController.startFeedBack(MainActivity.this, "1", "", -1L);
        }

    }

    @Override
    public void refreshLocation() {
//        presenter.refreshLocation();
        LogUtils.d("=========================generalEndTrip===916");
        generalEndTrip(true, "NOBLUE");
    }

    private ConnectBlueTooth connectBlueTooth = new ConnectBlueTooth(this);

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void trip_unlock_forBluetooth() {
        String address = "";
        address = PreferencesUtils.getString(MyApplication.appContext, Config.TRIPINGBLUETOOTHMACADDRESS);
        OnGoing_TripBO data = MyUtils.getTripData();
        MyUtils.d(data);
        String newPaw = data.getBicycle().getMacPwd();
        String newKey = data.getBicycle().getMacSecretKey();

        if ("danchexia-bijiasuo-01".equals(data.getBicycle().getBluetoothCode())){
            BluetoothUtils.myFilter_1 = 0x01;
            BluetoothUtils.myFilter_2 = 0x02;
        }
        AES.staticKey = BluetoothUtils.getBlueByteArray(newKey, 16);
        AES.staticPaw = BluetoothUtils.getBlueByteArray(newPaw, 6);
        address = address.toUpperCase();
        if (BluetoothUtils.isConnectBluetooth() && BluetoothLeService.mConnectionState == 2) {
            BluetoothUtils.openBlueLock(AES.staticPaw);
        } else {
            connectBlueTooth.initBlueTooth();

            if (!TextUtils.isEmpty(address)) {
                if (BluetoothUtils.bluetoothIsEnable()) {
                    final String finalAddress = address;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new MyNewThread(finalAddress).start();
                        }
                    }, 2000);
                }
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!blueLockIsOpen) {
                        setUnlockFailed();
                    }
                }
            }, 30000);
        }
        connectBlueTooth.setOnLockOpenSuccessListener(new ConnectBlueTooth.OnLockOpenSuccessListener() {
            @Override
            public void onSuccess() {
                if (ConnectBlueTooth.bluetoothIsService && BluetoothUtils.mBluetoothLeService != null) {
                    //断开蓝牙连接
//                    BluetoothUtils.mBluetoothLeService.disconnect();
//                    //蓝牙注销
//                    unbindService(BluetoothUtils.mServiceConnection);
//                    BluetoothUtils.mBluetoothLeService = null;
                    presenter.onGiongInfo();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LogUtils.d("blueLockIsOpen===844===" + blueLockIsOpen);
                            blueLockIsOpen = true;//用户由于某些原因 再次打开了锁
                            scanBluetoothStopSign = false;//锁再次打开之后，我就去重新查询锁的状态
                        }
                    }, 5000);

                }
            }
        });
        connectBlueTooth.setOnLockCloseSuccessListener(new ConnectBlueTooth.OnLockCloseSuccessListener() {
            @Override
            public void onCloseSuccess() {
                endMyTripForBlue();
                LogUtils.w("=====关锁成功了====");
                connectBlueTooth.setClearLisen();//关锁后就不监听了
            }
        });
    }

    @Override
    public void trip_unlock() {
        presenter.tripUnlock();
    }

    @Override
    public void endMyTrip() {
        LogUtils.d("=========================generalEndTrip===990");
        generalEndTrip(true, "NOBLUE");
    }

    @Override
    public void endMyTripForBlue() {
//        presenter.endTripForBlue(true);
        LogUtils.d("=========================generalEndTrip===997");
        generalEndTrip(true, "BLUE");

    }

    @Override
    public void startToPerson() {//去设置功率
        Intent modify = new Intent(MainActivity.this, PersonalActivity.class);
        startActivityForResult(modify, 203);
    }

    @Override
    public void go_find() {//暂且弃用

    }

    @Override
    public void openMemberTop() {
        if (MyUtils.getPersonData() != null && MyUtils.getPersonData().getVIP()){
            layoutOpenMember.setVisibility(View.GONE);
        }else {
            layoutOpenMember.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 预约成功后 隐藏这个预约功能模块
     */
    public void setRecervationGone() {
        initReservation(false, 0, null, null);
    }

    private int tripMyStatus = 0;//当前行程的状态
    /**
     * 获取用户当前的状态
     *
     * @param onGoingInfoBean
     */
    private Boolean isStopType = false;//默认不加载停车围栏
    private String tripStopType = "arbitrarily";// 停车方式：任意：arbitrarily  定点停车 fixed_point

    public void setMyStatus(OnGoingInfoBean onGoingInfoBean) {
        if (onGoingInfoBean.getOnGoing_reserveBO() != null) {
            OnGoing_ReserveBO onGoing_reserveBO = onGoingInfoBean.getOnGoing_reserveBO();
            canPullAllBike = false;//控制不去请求所有车辆
            isStopType = false;
            if (onGoing_reserveBO.getStatus() == 1) {//预约中
                map_center.setVisibility(View.GONE);//预约中 隐藏
                initReservationIng(true, onGoing_reserveBO);
                LatLng mCenterLatLng = new LatLng(onGoing_reserveBO.getLocation().getY(), onGoing_reserveBO.getLocation().getX());
                eNode = PlanNode.withLocation(mCenterLatLng);
                isShowRecervation = true;//画完线路后会显示预约界面，因此赋值true 不要让onChange运行
                mSearch.walkingSearch(new WalkingRoutePlanOption().from(sNode).to(eNode));
                setMapAndMarkerClick(false);
            } else if (onGoing_reserveBO.getStatus() == 2) {//已取消
                initReservationIng(false, null);
            }
        } else if (onGoingInfoBean.getOnGoing_tripBO() != null &&
                onGoingInfoBean.getOnGoing_tripBO().getStatus() == MyUtils.RIDE_STATUS_4) {//如果有未支付的行程 就走支付流程去
            tripMyStatus = onGoingInfoBean.getOnGoing_tripBO().getStatus();
            canPullAllBike = false;//控制不去请求所有车辆
            initUsingFragment(false, null);//显示用车中
            isStopType = false;
            if (!isNoTripPay) {
                if (MyApplication.toPayStroke) {
                    MyApplication.toPayStroke = false;
                    if (isStartEdOver) {
                        isStartEdOver = false;
                        LogUtils.d("startRideOver====1031");
                        ActivityController.startRideOver(MainActivity.this, 0L);
                    }
                } else {
                    if (isStartEdOver) {
                        isStartEdOver = false;
                        strokePay();
                    }
                }
            } else {
                isNoTripPay = false;
            }

        } else if (onGoingInfoBean.getOnGoing_tripBO() != null &&
                onGoingInfoBean.getOnGoing_tripBO().getStatus() == MyUtils.RIDE_STATUS_3) {//在骑行中，上报位置
            tripMyStatus = onGoingInfoBean.getOnGoing_tripBO().getStatus();
            OnGoing_TripBO data = onGoingInfoBean.getOnGoing_tripBO();
            canPullAllBike = false;//控制不去请求所有车辆
            Config.HAVETRIPING = true;//有进行中的行程
            PreferencesUtils.putLong(MyApplication.appContext, Config.USERTRIPID, data.getId());
            ActivityController.startRideStatusService(this);
            rideServiceConnection = ActivityController.bindRideStatusService(this, connection);
            if (myBinder != null) {
                myBinder.startCheckStatus();
            }
            if (data.getDoingFeedbacks() != null && data.getDoingFeedbacks().size() > 0) {
                initAskFragment(true);
                mBaiduMap.clear();
                return;
            } else {
                initAskFragment(false);
            }
            tripStopType = onGoingInfoBean.getOnGoing_tripBO().getStopType();
            if (onGoingInfoBean.getOnGoing_tripBO().getStopType().contains("fixed_point")) {
                isStopType = true;
            } else {
                isStopType = false;
            }
            tripOpenType = data.getBicycle().getOpenType();
            if (data.getBicycle().getOpenType() == 2 || data.getBicycle().getOpenType() == 4) {
                String newPaw = data.getBicycle().getMacPwd();
                String newKey = data.getBicycle().getMacSecretKey();
                AES.staticKey = BluetoothUtils.getBlueByteArray(newKey, 16);
                AES.staticPaw = BluetoothUtils.getBlueByteArray(newPaw, 6);
                BluetoothUtils.mDeviceAddress = data.getBicycle().getLockMacAddress();

            }
            if (data.getStopType().contains("fixed_point")) {
                if (data.getBicycle().getOpenType() == 2 || data.getBicycle().getOpenType() == 4){
                    initParkFragment(false, null);
                    initUsingFragment(true, data);//显示用车中
                    mBaiduMap.clear();
                    presenter.getAllParkList(mCenterLatLng.longitude, mCenterLatLng.latitude, 3000);
                    isShowRecervation = true;//骑车中，因此赋值true 不要让onChange运行
                    setMapAndMarkerClick(false);
                    fragmentBottom.setMyGone();
                    if (rideStatusService != null) {
                        rideStatusService.setMyParkBoolean(true);
                    }
                    return;
                }
                if (data.getLockOnoff()) {
                    initParkFragment(false, null);
                    initUsingFragment(true, data);//显示用车中
                    mBaiduMap.clear();
                    presenter.getAllParkList(mCenterLatLng.longitude, mCenterLatLng.latitude, 3000);
                    isShowRecervation = true;//骑车中，因此赋值true 不要让onChange运行
                    setMapAndMarkerClick(false);
                    fragmentBottom.setMyGone();
                    if (rideStatusService != null) {
                        rideStatusService.setMyParkBoolean(true);
                    }
                } else {
                    if (data.getAutoEnd()) {
                        LogUtils.d("=========================park===1113");
                        initParkFragment(true, data);
                    } else {//超过时间以后 直接跳转停车失败界面
                        LogUtils.d("=========================park===1127");
                        parkIsNotIn(data);
                    }
                }
            } else {
                initParkFragment(false, null);
                initUsingFragment(true, onGoingInfoBean.getOnGoing_tripBO());//显示用车中
                if (isStopType) {
                    mBaiduMap.clear();
                    presenter.getAllParkList(mCenterLatLng.longitude, mCenterLatLng.latitude, 3000);
                }
                setMapAndMarkerClick(false);
                fragmentBottom.setMyGone();
                isShowRecervation = true;//骑车中，因此赋值true 不要让onChange运行
                map_center.setVisibility(View.GONE);//骑行中 隐藏
                mBaiduMap.clear();

            }

        } else {
            tripMyStatus = 0;
            isStopType = false;
//            canLocationController = true;
            setMapAndMarkerClick(true);
            map_center.setVisibility(View.VISIBLE);//取消预约的时候显示
            initReservationIng(false, null);//预约中，取消显示
            initReservation(false, 0, null, null);//预约，取消显示
            initUsingFragment(false, null);
            initParkFragment(false, null);
            fragmentBottom.setMyVisiable();
            isShowRecervation = false;
            canPullAllBike = true;
            presenter.getAllBicycleData(MyApplication.myLatitude, MyApplication.myLongtitude, 3000);
            Long myTripId = PreferencesUtils.getLong(MyApplication.appContext, Config.USERTRIPID, 0);
            if (myTripId != 0) {
                if (isStartEdOver) {
                    isStartEdOver = false;
                    LogUtils.d("startRideOver====1124");
                    ActivityController.startRideOver(MainActivity.this, myTripId);
                }
            }
        }
    }

    private long firstTime = 0;//就第一次点击的时间

    @Override
    public void onBackPressed() {
        //按两次退出
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ShowToast.show(this, getString(R.string.toast_22));
            firstTime = secondTime;//更新firstTime
        } else {
            MobclickAgent.onKillProcess(this);
            ControllerActivity.finishAll();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * 行程支付
     */
    private void strokePay() {
        StanderdDialog dialog = new StanderdDialog(MainActivity.this, getString(R.string.toast_23),
                getString(R.string.toast_24), getString(R.string.toast_25), getString(R.string.toast_26)
                , new StanderdDialog.OnDialogClickListener() {
            @Override
            public void doAnyClick() {

            }

            @Override
            public void doMainClick() {
                ActivityController.startRideOver(MainActivity.this, 0L);
            }
        });
        dialog.show();
    }
    /**
     * 蓝牙重连
     */
    private StanderdDialog buleDialog;
    private void BlueConnectAgain() {
        if (buleDialog == null) {
            buleDialog = new StanderdDialog(MainActivity.this, getString(R.string.toast_23),
                    "为保证您的正常使用，请打开蓝牙后继续", "确定", getString(R.string.toast_26)
                    , new StanderdDialog.OnDialogClickListener() {
                @Override
                public void doAnyClick() {

                }

                @Override
                public void doMainClick() {
                    connectBlueTooth.initBlueTooth();
                }
            });
        }
        if (!buleDialog.isShowing()) {
            buleDialog.show();
        }
    }
    private ParkListBean parkListBean;

    //需要停车围栏的时候 会调用这个方法
    public void addParkMarkers(ParkListBean bean) {
        if (bean.getDataList() == null) {
            return;
        }
        parkListBean = bean;
        if (BaiduMapHelper.strokeOverlayList.size() > 0) {
            for (int i = 0; i < BaiduMapHelper.strokeOverlayList.size(); i++) {
                BaiduMapHelper.strokeOverlayList.get(i).remove();
            }
        }
        for (int i = 0; i < bean.getDataList().size(); i++) {
            if (bean.getDataList().get(i).getType() == null) {
                return;
            }
            if (bean.getDataList().get(i).getType().contains("circular")) {//定点类型 circular：圆，polygon：多边形
                BaiduMapHelper.setParkCircle(bean.getDataList().get(i), mBaiduMap);
            } else if (bean.getDataList().get(i).getType().contains("polygon")) {
                BaiduMapHelper.setParkStroke(bean.getDataList().get(i), mBaiduMap);
            }
        }
    }

    //在定点停车的时候 重新开锁失败
    public void setUnlockFailed() {
        fragmentParkLocation.unlockFailed();
    }

    //在定点停车的时候 重新定位请求成功后 去轮询状态接口
    private boolean getUseLocation = false;

    public void setStartRefreshLocation() {
        getUseLocation = true;
    }

    private boolean serviceFirst = true;

    //结束行程成功
    public void setEndTripSuccess(OnGoing_TripBO bean) {
        OnGoingInfoBean onGoingInfoBean = new OnGoingInfoBean(null, bean);
//        setMyStatus(onGoingInfoBean);
        DialogUtils.successWaitDialog();
        initParkFragment(false, null);
        initUsingFragment(false, null);
        canLocationController = true;
        mBaiduMap.clear();
        if (isStartEdOver) {
            isStartEdOver = false;
            Long myTripId = -1L;
            if (bean.getBicycle() != null && bean.getBicycle().getOpenType() != 1) {
                myTripId = PreferencesUtils.getLong(MyApplication.appContext, Config.USERTRIPID, 0);
            }
            LogUtils.d("startRideOver====1222");
            ActivityController.startRideOver(MainActivity.this, myTripId);
        }
    }

    //纯蓝牙锁关锁成功
    public void setEndTripBlueSuccess(OnGoing_TripBO bean) {
//        Long myTripId = 0l;
//        if (!Config.isNeadToPay) {
//            myTripId = PreferencesUtils.getLong(MyApplication.appContext, Config.USERTRIPID, 0);
//        }
//        PersonalBean personalBean = MyUtils.getPersonData();
//        if (personalBean.getVIP() != null && personalBean.getVIP()) {
//            myTripId = PreferencesUtils.getLong(MyApplication.appContext, Config.USERTRIPID, 0);
//        }
//        LogUtils.d("myTripId=========1141========" + myTripId);
        if (isStartEdOver) {
            isStartEdOver = false;
            ActivityController.startRideOver(MainActivity.this, 0l);
        }
        blueLockIsOpen = true;//纯蓝牙关锁成功了  给恢复初始值
        scanBluetoothStopSign = false;//纯蓝牙关锁成功了，给恢复初始值
    }

    private static boolean isStartEdOver = true;
    private boolean isNoTripPay = false;

    //结束行程失败
    public void setEndTripFailed() {
        LogUtils.w("非蓝牙结束行程失败");
        isNoTripPay = true;
        presenter.onGiongInfo();
        DialogUtils.successWaitDialogNoOk();
    }

    //开锁成功
    public void setUnLocakSuccess() {
        presenter.onGiongInfoForTriping();
        ShowToast.show(this, "请求成功");
    }
    private int onGiongInfoForTripingNums = 0;
    public void setForTripingMyStatus(OnGoingInfoBean onGoingInfoBean) {
        if (onGoingInfoBean.getOnGoing_tripBO() != null){
            OnGoing_TripBO tripBo = onGoingInfoBean.getOnGoing_tripBO();
            if (tripBo.getStopType().contains("fixed_point")) {
                if (tripBo.getLockOnoff()) {
                    ActivityController.startRideStatusService(this);
                    rideServiceConnection = ActivityController.bindRideStatusService(this, connection);
                    if (myBinder != null) {
                        myBinder.startCheckStatus();
                    }
                    onGiongInfoForTripingNums = 0;
                    initParkFragment(false, null);
                    initUsingFragment(true, tripBo);//显示用车中
                    mBaiduMap.clear();
                    presenter.getAllParkList(mCenterLatLng.longitude, mCenterLatLng.latitude, 3000);
                    isShowRecervation = true;//骑车中，因此赋值true 不要让onChange运行
                    setMapAndMarkerClick(false);
                    fragmentBottom.setMyGone();
                    if (rideStatusService != null) {
                        rideStatusService.setMyParkBoolean(true);
                    }
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (onGiongInfoForTripingNums < 6) {
                                presenter.onGiongInfoForTriping();
                                onGiongInfoForTripingNums ++;
                            }else{
                                onGiongInfoForTripingNums = 0;
                            }
                        }
                    },1000);
                }
            }
        }
    }

    public void setSystemData(final SystemParamsBean systemParamsBean) {
        if (systemParamsBean != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    head_title.setText(Utils.object2String(systemParamsBean.getAppName()));
                }
            });
        }
    }


    //不在区域内的时候   纯蓝牙
    public void notInParkContent() {
        blueLockIsOpen = false;//锁已经关了 只是不再区域内
        if (!fragmentParkLocation.isFrount) {
            OnGoing_TripBO data = MyUtils.getTripData();
            if (data != null) {
                initUsingFragment(false, null);
                LogUtils.d("=========================park===1347");
                initParkFragment(true, data);
            }
        }
    }

    public class ReceiverUpdata extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                Boolean notrip = intent.getBooleanExtra("NOTRIP", false);
                Boolean lockIsClosed = intent.getBooleanExtra("LockIsClosed", false);
                Boolean parkfailed = intent.getBooleanExtra("PARKFAILED", false);
                Boolean lockIsOpened = intent.getBooleanExtra("LOCKISOPENED", false);
                Boolean needToPay = intent.getBooleanExtra("NEEDTOPAY", false);
                int openType = intent.getIntExtra("OpenType", 1);
                Boolean showorhideaskfragment = intent.getBooleanExtra("SHOWORHIDEASKFRAGMENT", true);
                int tripstatus = intent.getIntExtra("TRIPSTATUS", 0);

                if (!showorhideaskfragment) {
                    initAskFragment(showorhideaskfragment);
                }
                if (needToPay) {
                    if (serviceFirst) {
                        if (isStartEdOver) {
                            isStartEdOver = false;
                            LogUtils.d("startRideOver====1308");
                            ActivityController.startRideOver(MainActivity.this, 0L);
                        }
                    } else {
                        serviceFirst = true;
                    }
                }
                if (lockIsOpened) {
                    isNoTripPay = true;
                    presenter.onGiongInfo();
                }
                if (notrip) {
                    tripIsNull();
                }
                if (lockIsClosed) {
                    LogUtils.d("锁关了");
                    int turnTime = intent.getIntExtra("TURNTIME", 0);
                    DialogUtils.waittingDialogUtils(MainActivity.this);
                    LogUtils.d("=========================generalEndTrip===1395");
                    generalEndTrip(false, "NOBLUE");
                    rideStatusService.setMyParkBoolean(false);
                }
                if (parkfailed) {
                    Boolean isinpark = intent.getBooleanExtra("ISINPARK", false);
                    Double myPrice = intent.getDoubleExtra("myPrice", 0);
                    String sysCode = intent.getStringExtra("sysCode");
                    Long refreshTime = intent.getLongExtra("refreshTime", 0);

                    final OnGoing_TripBO bo = new OnGoing_TripBO();
                    BicycleData bicyData = new BicycleData();
                    bicyData.setOpenType(openType);
                    bo.setBicycle(bicyData);
                    bo.setPrice(myPrice);
                    bo.setSysCode(sysCode);
                    bo.setLastLockLocationTime(new Date(refreshTime));
                    bo.setInTheParkingLot(isinpark);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            parkIsNotIn(bo);
                        }
                    }, 4000);

                }
            }
        }
    }

    //行程为空的时候
    private void tripIsNull() {
        canLocationController = true;
        setMapAndMarkerClick(true);
        map_center.setVisibility(View.VISIBLE);//取消预约的时候显示
        initReservationIng(false, null);//预约中，取消显示
        initReservation(false, 0, null, null);//预约，取消显示
        initUsingFragment(false, null);//用车中，取消显示
        fragmentBottom.setMyVisiable();
        isShowRecervation = false;
        LogUtils.d("轮训服务是否开启："+rideServiceConnection);
        if (rideServiceConnection){
            ActivityController.unbindRideStatusService(MainActivity.this, connection);
            rideServiceConnection = false;
        }
        ActivityController.stopRideStatusService(MainActivity.this);
        mBaiduMap.clear();
        Long myTripId = PreferencesUtils.getLong(MyApplication.appContext, Config.USERTRIPID, 0);
        LogUtils.d("收到行程为空的广播====" + myTripId);
        if (myTripId != 0) {
            if (isStartEdOver) {
                isStartEdOver = false;
                LogUtils.d("startRideOver====1370");
                ActivityController.startRideOver(MainActivity.this, myTripId);
            }
        }
    }

    //  定点停车的时候
    private void parkIsNotIn(OnGoing_TripBO intent) {

        if (fragmentParkLocation != null && fragmentParkLocation.isFrount) {
            if (intent.getInTheParkingLot()) {
                LogUtils.d("=========================park===1459");
                initParkFragment(true, intent);
                rideStatusService.setMyParkBoolean(false);
            }
        } else {
            LogUtils.d("=========================park===1464");
            initParkFragment(true, intent);
            initUsingFragment(false, null);
        }
    }

    private boolean isToShow = false;//控制显示状态
    private String requestType;//请求结束行程的状态

    //统一管理结束行程的调用停车方式：任意：arbitrarily  定点停车 fixed_point
    private void generalEndTrip(boolean show, String type) {
        requestType = type;
        if (tripStopType.equals("arbitrarily")) {
            if ("BLUE".equals(requestType)) {
                presenter.endTripForBlue(isToShow, "gprs");
            } else {
                presenter.endTrip(isToShow, "gprs");
            }
        } else if (tripStopType.equals("fixed_point")) {
            presenter.getNearByPark();
            isToShow = show;
        }
    }

    //根据返回的车类型去做判断 结束类型 gprs,bluetooth
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void setNearParkStatus(NearByPark nearByPark) {
        if (nearByPark == null) {
            if ("BLUE".equals(requestType)) {
                presenter.endTripForBlue(isToShow, "gprs");
            } else {
                presenter.endTrip(isToShow, "gprs");
            }
            return;
        }
        if ("bluetooth".equals(nearByPark.getSpotType())) {
            LogUtils.w("走其他流程去");
            this.nearByPark = nearByPark;
            seachBluePark();
        } else {
            if ("BLUE".equals(requestType)) {
                presenter.endTripForBlue(isToShow, "gprs");
            } else {
                presenter.endTrip(isToShow, "gprs");
            }
            LogUtils.w("走原流程去");
        }
    }

    private NearByPark nearByPark;//蓝牙桩的信息

    //去搜周边的蓝牙装
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void seachBluePark() {
        BluetoothUtils.initBluetooth(this);
        if (BluetoothUtils.bluetoothIsEnable()) {
            beaconManager = BeaconManager.getInstanceForApplication(this);
            beaconManager.setBackgroundScanPeriod(DEFAULT_BACKGROUND_SCAN_PERIOD);
            beaconManager.setBackgroundBetweenScanPeriod(DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD);
            beaconManager.bind(this);
        } else {
            BluetoothUtils.tipsUser(this);
        }

    }

    private BeaconManager beaconManager;
    private static final long DEFAULT_BACKGROUND_SCAN_PERIOD = 100L;
    private static final long DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD = 100L;
    private Region myRegion;

    @Override
    public void onBeaconServiceConnect() {//FD A5 06 93 A4 E2 4F B1 AF CF C6 EB 07 64 78 25  ,27 11 4C B9 myRangingUniqueId
        if (nearByPark != null) {
            LogUtils.w("====设置过滤====" + nearByPark.getUuid() + ";" + nearByPark.getMajor() + ";" + nearByPark.getMinor());
            myRegion = new Region(nearByPark.getUuid(),
                    null,
                    Identifier.parse(nearByPark.getMajor()), Identifier.parse(nearByPark.getMinor()));
            beaconManager.addRangeNotifier(this);
            try {
                beaconManager.startRangingBeaconsInRegion(myRegion);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private int blueTimeSeach = 0;//搜索次数

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> collections, Region region) {
        LogUtils.w("搜索====" + region.toString() + ";requestType=" + requestType);
        if (collections.size() > 0) {
            if ("BLUE".equals(requestType)) {
                presenter.endTripForBlue(isToShow, "bluetooth");
            } else {
                presenter.endTrip(isToShow, "bluetooth");
            }
            for (Beacon b : collections) {
                LogUtils.w("搜索到蓝牙桩了，关闭行程====" + b.toString());
            }
            PreferencesUtils.putBoolean(MyApplication.appContext, "isInParkForBluePark", true);
            closeBeacon();
        } else {
            LogUtils.w("没有搜索到蓝牙桩，关闭行程====" + blueTimeSeach);
            if (blueTimeSeach > 5) {
                PreferencesUtils.putBoolean(MyApplication.appContext, "isInParkForBluePark", false);
                closeBeacon();
                if ("BLUE".equals(requestType)) {
                    notInParkContent();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setEndTripFailed();
                        }
                    });

                }
                blueTimeSeach = 0;
            } else {
                blueTimeSeach += 1;
            }

        }
    }

    //关闭 beacon扫描
    private void closeBeacon() {
        if (beaconManager != null) {
            try {
                beaconManager.stopRangingBeaconsInRegion(myRegion);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            beaconManager.removeRangeNotifier(MainActivity.this);
            beaconManager.unbind(MainActivity.this);
            beaconManager = null;
        }
    }
}
