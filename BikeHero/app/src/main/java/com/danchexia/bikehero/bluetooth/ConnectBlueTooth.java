package com.danchexia.bikehero.bluetooth;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;

import com.orhanobut.logger.Logger;

import vc.thinker.tools.utils.LogUtils;

/**
 * Created by farley on 17/8/29.
 * description:
 */

public class ConnectBlueTooth {

    private Activity activity;
    public static boolean bluetoothIsService = false;
    private boolean bluetoothIsReceiver = false;
    public ConnectBlueTooth(Activity activity) {
        this.activity = activity;
    }
    public ConnectBlueTooth() {
    }
    //初始化蓝牙
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public  void initBlueTooth() {
        BluetoothUtils.initBluetooth(activity);
        Intent gattServiceIntent = new Intent(activity, BluetoothLeService.class);
        activity.bindService(gattServiceIntent, BluetoothUtils.mServiceConnection, activity.BIND_AUTO_CREATE);
        bluetoothIsService = true;
        registerMyReceiver();
        if (BluetoothUtils.bluetoothIsEnable()){
            BluetoothUtils.scanLeDevice(true);
        }else {
            BluetoothUtils.tipsUser(activity);
        }
    }
    public void registerMyReceiver(){
        LogUtils.d("bluetoothIsReceiver===="+bluetoothIsReceiver);
        if (!bluetoothIsReceiver) {
            activity.registerReceiver(mGattUpdateReceiver, BluetoothUtils.makeGattUpdateIntentFilter());
            bluetoothIsReceiver = true;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {//链接成功
                Logger.d("链接成功");

            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {//断开链接
                Logger.d("断开链接");
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {//发现服务
                Logger.d("发现服务");
                BluetoothUtils.getBluetoothToken();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BluetoothUtils.connectBluetoothData();
                    }
                }, 200);
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {//发来数据
                byte[] data = intent.getByteArrayExtra(BluetoothLeService.EXTRA_DATA);
                Logger.d("发来数据" + BLEUtils.showData(data));
                if (data[0] == (byte) 0x06 && data[1] == (byte) 0x02) {
                    Logger.d("获取token成功");
                    byte[] token = new byte[4];
                    if (data[0] == 0x06 && data[1] == 0x02) {
                        for (int i = 0; i < 4; i++) {
                            token[i] = data[i + 3];
                        }
                        MyGattAttributes.BLE_TOKEN = token;
                        BluetoothUtils.getLockstatus();
//                        BluetoothUtils.openBlueLock(AES.staticPaw);
                    }
                }
                if (data[0] == (byte) 0x01 && data[1] == (byte) 0x02 && data[2] == (byte) 0x01 && data[3] == (byte) 0x00) {
                    LogUtils.d("同步时间成功");
                }
                if (data[0] == (byte) 0x05 && data[1] == (byte) 0x0F && data[2] == (byte) 0x01 && data[3] == (byte) 0x00) {
                    LogUtils.d("开锁状态");
                }
                if (data[0] == (byte) 0x05 && data[1] == (byte) 0x0F && data[2] == (byte) 0x01 && data[3] == (byte) 0x01) {
                    Logger.d("关锁状态");
                    if (onLockCloseSuccessListener != null) {
                        onLockCloseSuccessListener.onCloseSuccess();
                    }
                }
                //  关锁成功
                if (data[0] == (byte) 0x05 && data[1] == (byte) 0x08 && data[2] == (byte) 0x01 && data[3] == (byte) 0x00) {
                    Logger.d("connectutils=关锁成功+onLockCloseSuccessListener"+onLockCloseSuccessListener);
                    if (onLockCloseSuccessListener != null) {
                        onLockCloseSuccessListener.onCloseSuccess();
                    }
                }
                //  锁的状态是关闭的
                if (data[0] == (byte) 0x05 && data[1] == (byte) 0x0F && data[2] == (byte) 0x01 && data[3] == (byte) 0x01) {
                    Logger.d("connectutils=关锁--状态+onLockCloseSuccessListener"+onLockCloseSuccessListener);
                    if (onLockCloseSuccessListener != null) {
                        onLockCloseSuccessListener.onCloseSuccess();
                    }
                }
                if (data[0] == (byte) 0x05 && data[1] == (byte) 0x02 && data[2] == (byte) 0x01 && data[3] == (byte) 0x00) {
                    Logger.d("开锁成功");
                    if (onLockOpenSuccessListener != null) {
                        onLockOpenSuccessListener.onSuccess();
                    }
                }
            }
        }
    };
    public void setClearLisen(){
        onLockCloseSuccessListener = null;
    }
    private OnLockOpenSuccessListener onLockOpenSuccessListener;
    public interface OnLockOpenSuccessListener{
        void onSuccess();
    }
    public void setOnLockOpenSuccessListener( OnLockOpenSuccessListener onLockOpenSuccessListener){
        this.onLockOpenSuccessListener = onLockOpenSuccessListener;
    }

    private OnLockCloseSuccessListener onLockCloseSuccessListener;
    public interface OnLockCloseSuccessListener{
        void onCloseSuccess();
    }
    public void setOnLockCloseSuccessListener( OnLockCloseSuccessListener onLockCloseSuccessListener){
        LogUtils.d("connectuti=========setonLockCloseSuccessListener="+onLockCloseSuccessListener);
        this.onLockCloseSuccessListener = onLockCloseSuccessListener;
    }
}
