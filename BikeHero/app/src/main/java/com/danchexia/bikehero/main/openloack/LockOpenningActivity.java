package com.danchexia.bikehero.main.openloack;

import android.app.Activity;
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
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.batteryservice.BatteryChangeListen;
import com.danchexia.bikehero.batteryservice.BatteryService;
import com.danchexia.bikehero.bluetooth.AES;
import com.danchexia.bikehero.bluetooth.BLEUtils;
import com.danchexia.bikehero.bluetooth.BluetoothLeService;
import com.danchexia.bikehero.bluetooth.BluetoothUtils;
import com.danchexia.bikehero.bluetooth.MyGattAttributes;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.bean.BlueToothBean;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.service.OpenLockService;
import com.danchexia.bikehero.service.RideStatusService;

import java.util.Observable;
import java.util.Observer;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;
import vc.thinker.tools.views.OpenLockLoadingView;

/**
 * Created by farley on 17/5/16.
 * description:开锁中
 */

public class LockOpenningActivity extends MvpActivity<LockOpenPresenter, ILockOpenView> implements ILockOpenView, Observer {
    private LockOpenPresenter presenter;
    private OpenLockLoadingView openLoading;
    private TextView progressNum;//当前进度
    private String code;//锁的code
    private OpenLockService.MyBinder myBinder;
    private BatteryService.MyBinder myBatteryBinder;
    private RideStatusService.MyBinder myStatusBinder;
    private ReceiverOpenSuccess receiverOpenSuccess;
    public static final String MESSAGE_RECEIVED_ACTION = "com.danchexia.bikehero.MESSAGE_RECEIVED_ACTION";
    private boolean finishProgress = false;//控制进度条
    private boolean isBindService = false;//是否注册服务
    private boolean bluetoothIsReceiver = false;
    private boolean bluetoothIsService = false;
    private boolean isVisiableViewIt = false;
    private int openType = 0;//锁类型
    //单车的服务
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (OpenLockService.MyBinder) service;
            myBinder.startCheckStatus();
        }
    };
    private BatteryChangeListen batteryChangeListen;
    private Observable observableForSevice;
    //电池的服务
    private ServiceConnection connectionForBattery = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBatteryBinder = (BatteryService.MyBinder) service;
            myBatteryBinder.startCheckStatus();
            //返回一个MsgService对象
            batteryChangeListen = new BatteryChangeListen(((BatteryService.MyBinder) service).getService());
            observableForSevice = batteryChangeListen;
            observableForSevice.addObserver(LockOpenningActivity.this);
        }
    };
    private ServiceConnection connectionStatus = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myStatusBinder = (RideStatusService.MyBinder) service;
            myStatusBinder.startCheckStatus();
        }
    };

    @Override
    protected LockOpenPresenter CreatePresenter() {
        return presenter = new LockOpenPresenter(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_lock);
        Intent it = getIntent();
        if (it != null) {
            code = it.getStringExtra("LOCK_CODE");
            LogUtils.d("开锁码======" + code);
        }

        initView();

        initIpushReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisiableViewIt = true;
        initData();
    }

    //初始化蓝牙
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initBlueTooth() {
        BluetoothUtils.initBluetooth(this);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, BluetoothUtils.mServiceConnection, BIND_AUTO_CREATE);
        bluetoothIsService = true;
        if (!bluetoothIsReceiver) {
            registerReceiver(mGattUpdateReceiver, BluetoothUtils.makeGattUpdateIntentFilter());
            bluetoothIsReceiver = true;
        }
        if (BluetoothUtils.bluetoothIsEnable()) {
            BluetoothUtils.scanLeDevice(true);
        } else {
            BluetoothUtils.tipsUser(this);
        }
    }

    private void initIpushReceiver() {
        receiverOpenSuccess = new ReceiverOpenSuccess();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(receiverOpenSuccess, filter);
    }

    private void initView() {
        openLoading = (OpenLockLoadingView) findViewById(R.id.openLoading);
        progressNum = (TextView) findViewById(R.id.progressNum);

        openLoading.startOpen();
        openLoading.setOnResultReturnLisener(new OpenLockLoadingView.OnResultReturnLisener() {
            @Override
            public void onResult(int result) {
                if (!finishProgress) {
                    progressNum.setText(getString(R.string.lock_oping) + " " + Utils.object2String(result) + "%");
                }
            }
        });
    }

    /**
     * 开锁
     */
    private void initData() {
        if (TextUtils.isEmpty(code)) {
            ShowToast.show(this, getString(R.string.toast_1));
            finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    presenter.openLock(code, MyApplication.myLatitude, MyApplication.myLongtitude);
                    openTimeOut();
                }
            }, 500);

        }
    }

    //请求开锁成功 ----单车
    public void requestOpenLockSuccess() {
        ActivityController.startOpenService(this);
        ActivityController.bindOpenService(this, connection);
        isBindService = true;
    }

    private boolean isBatteryBindService = false;

    //请求开锁成功 ----电池
    public void requestOpenLockSuccessForBattery() {
        ActivityController.startOpenServiceForBattery(this);
        ActivityController.bindOpenServiceForBattery(this, connectionForBattery);
        isBatteryBindService = true;
    }

    //请求开锁失败
    public void requestOpenLockFailed(BlueToothBean bean) {
        ShowToast.show(this, bean.getResult());
        finish();
//        openFailed();
    }

    /**
     * 开锁成功
     */
    public void openSuccess() {
        finishProgress = true;
        LogUtils.d("开锁成功");
        openLoading.setItOver();
        progressNum.setText(getString(R.string.lock_oping_success));
        ActivityController.startRideStatusService(this);
        ActivityController.bindRideStatusService(this, connectionStatus);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                canShowFailed = false;
                finish();
            }
        }, 1000);
    }

    private void openSuccessForBattery() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    /**
     * 开锁失败
     */
    public void openFailed() {
        LogUtils.d("开锁失败");
        finishProgress = true;
        openLoading.setItOver();
        progressNum.setText(getString(R.string.lock_oping_failed));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isVisiableViewIt) {
                    failedOpen();
                }
            }
        }, 1000);
    }

    public void failedOpen() {
        if (BluetoothUtils.mBluetoothLeService != null) {
            //断开蓝牙连接
            BluetoothUtils.mBluetoothLeService.disconnect();
            BluetoothUtils.mBluetoothLeService.close();
        }
        StanderdDialog dialog = new StanderdDialog(LockOpenningActivity.this, "开锁失败", "是否重试？", "确定", "取消"
                , new StanderdDialog.OnDialogClickListener() {
            @Override
            public void doAnyClick() {
                finish();
            }

            @Override
            public void doMainClick() {
                initData();
                progressNum.setText(getString(R.string.lock_oping));
                openLoading.startOpen();
                openLoading.setOnResultReturnLisener(new OpenLockLoadingView.OnResultReturnLisener() {
                    @Override
                    public void onResult(int result) {
                        if (!finishProgress) {
                            progressNum.setText(getString(R.string.lock_oping) + " " + Utils.object2String(result) + "%");
                        }
                    }
                });
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        if (isBatteryBindService) {
            myBatteryBinder.stopCheckStatus();
            ActivityController.unbindOpenService(this, connectionForBattery);
            ActivityController.stopOpenServiceForBattery(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverOpenSuccess);
        LogUtils.d("开锁界面结束=" + isBindService);
        if (isBindService) {
            myBinder.stopCheckStatus();
            ActivityController.unbindOpenService(this, connection);
            ActivityController.stopOpenService(this);
            isBindService = false;
        }
        if (bluetoothIsReceiver) {
            unregisterReceiver(mGattUpdateReceiver);
            bluetoothIsReceiver = false;
        }
//        if (isBatteryBindService){
//            myBatteryBinder.stopCheckStatus();
//            ActivityController.unbindOpenService(this, connectionForBattery);
//            ActivityController.stopOpenServiceForBattery(this);
//        }
        if (bluetoothIsService) {
            //断开蓝牙连接
//            BluetoothUtils.mBluetoothLeService.disconnect();
//            //蓝牙注销
//            unbindService(BluetoothUtils.mServiceConnection);
//            BluetoothUtils.mBluetoothLeService = null;
        }
        if (observableForSevice != null) {
            observableForSevice.deleteObserver(this);
        }
    }

    private boolean canReceiver = true;//只接受第一个广播
    private String newPaw, newKey;

    //使用蓝牙辅助开锁
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void useBluetoothOpenLock(BlueToothBean bean) {
        if (bean.getOpenType() == 5) {//如果是必加锁4合1 那就去判断是否要展示密码
            openType = 5;
        }
        newPaw = bean.getMacPwd();
        newKey = bean.getMacSecretKey();
        AES.staticKey = BluetoothUtils.getBlueByteArray(newKey, 16);
        AES.staticPaw = BluetoothUtils.getBlueByteArray(newPaw, 6);
        LogUtils.d("key=================" + BLEUtils.showData(AES.staticKey));
        LogUtils.d("defaultMacpaw=================" + BLEUtils.showData(AES.staticPaw));
        initBlueTooth();
        BluetoothUtils.mDeviceAddress = bean.getLockMacAddress();
        if (BluetoothUtils.bluetoothIsEnable()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new MyNewThread().start();
                }
            }, 2000);

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof BatteryChangeListen) {
            BatteryChangeListen serviceBase = (BatteryChangeListen) o;
            OnGoingInfoBean onGoingInfoBean = serviceBase.getOnGoingInfoBean();
            if (onGoingInfoBean != null && onGoingInfoBean.getOnGoing_tripBO() != null) {
                if (onGoingInfoBean.getOnGoing_tripBO().getBatteryBO() != null) {
                    openSuccessForBattery();
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    class MyNewThread extends Thread {
        @Override
        public void run() {
            super.run();
            if (!TextUtils.isEmpty(BluetoothUtils.mDeviceAddress)) {
                for (int i = 0; i < 10; i++) {
                    String netAddress = BluetoothUtils.mDeviceAddress.toUpperCase().trim();
                    LogUtils.d("netAddress=" + netAddress);
                    if (BluetoothUtils.deviceList.contains(netAddress)) {
                        LogUtils.d("有了");
                        BluetoothUtils.mBluetoothLeService.connect(netAddress);
                        BluetoothUtils.scanLeDevice(false);
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothUtils.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                initData();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public class ReceiverOpenSuccess extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.d("onReceive==开锁成功");
            if (canReceiver) {
                canReceiver = false;
                openSuccess();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //什么也不做
    }

    /**
     * 开锁超时
     */
    private boolean canShowFailed = true;

    private void openTimeOut() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (canShowFailed) {
                    if (openType == 5) {
                        requestOpenLockSuccess();
                        presenter.bluetoothOpenLock(code, true);
                    } else {
                        openFailed();
                    }
                }
            }
        }, 30000);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {//链接成功
                LogUtils.d("链接成功");

            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {//断开链接
                LogUtils.d("断开链接");
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {//发现服务
                LogUtils.d("发现服务");
                BluetoothUtils.getBluetoothToken();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BluetoothUtils.connectBluetoothData();
                    }
                }, 200);
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {//发来数据
                byte[] data = intent.getByteArrayExtra(BluetoothLeService.EXTRA_DATA);
                LogUtils.d("发来数据" + BLEUtils.showData(data));
                if (data[0] == (byte) 0x06 && data[1] == (byte) 0x02) {
                    LogUtils.d("获取token成功");
                    byte[] token = new byte[4];
                    if (data[0] == 0x06 && data[1] == 0x02) {
                        for (int i = 0; i < 4; i++) {
                            token[i] = data[i + 3];
                        }
                        MyGattAttributes.BLE_TOKEN = token;

                        BluetoothUtils.getElect();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                BluetoothUtils.openBlueLock(AES.staticPaw);
                            }
                        }, 500);
                    }
                }
                if (data[0] == (byte) 0x02 && data[1] == (byte) 0x02 && data[2] == (byte) 0x01) {
                    if (data[3] == (byte) 0xff) {
                        LogUtils.d("电量获取失败");

                    } else {
                        int elect = data[3];
                        LogUtils.d("电量获取成功" + elect);
                        presenter.uploadStatus(code, elect);
                    }
                }
                if (data[0] == (byte) 0x01 && data[1] == (byte) 0x02 && data[2] == (byte) 0x01 && data[3] == (byte) 0x00) {
                    LogUtils.d("同步时间成功");
                }
                //0x05 0x02 0x01 0x00  开锁成功
                if (data[0] == (byte) 0x05 && data[1] == (byte) 0x02 && data[2] == (byte) 0x01 && data[3] == (byte) 0x00) {
                    LogUtils.d("开锁成功");
                    requestOpenLockSuccess();
                    presenter.bluetoothOpenLock(code, false);

                }
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        LogUtils.d("onpause+不见了");

        isVisiableViewIt = false;
    }


}
