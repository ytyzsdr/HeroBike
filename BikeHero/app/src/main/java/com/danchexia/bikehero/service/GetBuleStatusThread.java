package com.danchexia.bikehero.service;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.widget.Toast;

import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.bluetooth.BluetoothUtils;
import com.danchexia.bikehero.config.Config;

import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/11/14.
 * description:
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class GetBuleStatusThread extends Thread {
    private Activity mContext;
    public GetBuleStatusThread(Activity context) {
        mContext = context;
    }
    private static Boolean isTimeOut = true;//默认会超时的
    @Override
    public void run() {
        super.run();
        isTimeOut = true;
        initBluetooth(mContext);
        if (mBluetoothAdapter.isEnabled()){
            myScan(true);
        }else {
            tipUser();
        }
    }
    private static BluetoothAdapter mBluetoothAdapter;
    public static final int REQUEST_ENABLE_BT = 1;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initBluetooth(Context context) {
        // 检查当前手机是否支持ble 蓝牙,如果不支持退出程序
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(context, "对不起，您的设备不支持BLE", Toast.LENGTH_SHORT).show();
            if (onCloseListener != null){
                onCloseListener.onException();
            }
        }
        // 初始化 Bluetooth adapter, 通过蓝牙管理器得到一个参考蓝牙适配器(API必须在以上android4.3或以上和版本)
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
//        mBluetoothAdapter.disable();
//        mBluetoothAdapter.enable();
        // 检查设备上是否支持蓝牙
        if (mBluetoothAdapter == null) {
            Toast.makeText(context, "对不起，您的设备不支持BLE", Toast.LENGTH_SHORT).show();
            if (onCloseListener != null){
                onCloseListener.onException();
            }
        }
    }
    private static void myScan(boolean enable){
        if (enable) {
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }
    private static int seachTimes = 0;
    // Device scan callback.
    private static BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, final byte[] scanRecord) {
            for (int i = 0; i < scanRecord.length; i++) {
                if (scanRecord[i] == BluetoothUtils.myFilter_1 && scanRecord[i + 1] == BluetoothUtils.myFilter_2) {
                        String netMac = PreferencesUtils.getString(MyApplication.appContext, Config.TRIPINGBLUETOOTHMACADDRESS);

                        if (!TextUtils.isEmpty(netMac)){
                            netMac = netMac.toUpperCase();
                            LogUtils.d("sssssss=========netMac===="+netMac);
                            LogUtils.d("sssssss==========device==="+device.getAddress());
                            if (netMac.equals(device.getAddress())){
                                isTimeOut = false;//只要能搜到 不管搜到的是开还是关 都不再说超时的事了
                                seachTimes = 0;
                                if (scanRecord[15] == (byte)0x01){//如果锁关了 那么就去结束行程
                                    if (onCloseListener != null){
                                        onCloseListener.onClose();
                                        myScan(false);
                                        return;
                                    }
                                }else{
                                    if (onCloseListener != null){
                                        onCloseListener.onopen();
                                        myScan(false);
                                        return;
                                    }
                                }
                            }
                        }
                }
            }
            LogUtils.d("sssssss==========seachTimes==="+seachTimes);
            if (seachTimes == 200) {
                mBluetoothAdapter.stopLeScan(mLeScanCallback);
                if (onCloseListener != null && isTimeOut) {
                    onCloseListener.onTimeOut();
                    seachTimes = 0;
                }
            }else{
                seachTimes++;
            }
        }
    };

    private void tipUser() {
        if (!mBluetoothAdapter.isEnabled()) {
            if (onCloseListener != null){
                onCloseListener.show(mBluetoothAdapter);
            }
            // 为了确保设备上蓝牙能使用, 如果当前蓝牙设备没启用,弹出对话框向用户要求授予权限来启用
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            mContext.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }
    }
    public interface OnCloseListener{
        void onClose();
        void onopen();
        void onException();
        void onTimeOut();
        void show(BluetoothAdapter mBluetoothAdapter);
    }
    private static OnCloseListener onCloseListener;
    public void setOnCloseListener(OnCloseListener onCloseListener){
        this.onCloseListener = onCloseListener;
    }
}
