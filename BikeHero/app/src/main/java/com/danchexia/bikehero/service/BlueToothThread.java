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

import java.util.ArrayList;
import java.util.List;

import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/8/29.
 * description:
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BlueToothThread extends Thread {
    private Activity mContext;
    public BlueToothThread(Activity context) {
        mContext = context;
    }

    @Override
    public void run() {
        super.run();
        initBluetooth(mContext);
        if (mBluetoothAdapter.isEnabled()){
            myScan(true);
        }else {
            tipUser();
        }
    }
    private  boolean mScanning;//标记是否还在扫描中
    private  BluetoothAdapter mBluetoothAdapter;
    public static final int REQUEST_ENABLE_BT = 1;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initBluetooth(Context context) {
        // 检查当前手机是否支持ble 蓝牙,如果不支持退出程序
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(context, "对不起，您的设备不支持BLE", Toast.LENGTH_SHORT).show();
        }
        // 初始化 Bluetooth adapter, 通过蓝牙管理器得到一个参考蓝牙适配器(API必须在以上android4.3或以上和版本)
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        // 检查设备上是否支持蓝牙
        if (mBluetoothAdapter == null) {
            Toast.makeText(context, "对不起，您的设备不支持BLE", Toast.LENGTH_SHORT).show();
        }
    }
    public static List<String> deviceList = new ArrayList<>();
    private void myScan(boolean enable){
        if (enable) {
            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
            deviceList.clear();
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }
    // Device scan callback.
    private  BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, final byte[] scanRecord) {
            for (int i = 0; i < scanRecord.length; i++) {
                if (scanRecord[i] == BluetoothUtils.myFilter_1 && scanRecord[i + 1] == BluetoothUtils.myFilter_2) {
                    if (!deviceList.contains(device.getAddress())) {
                        String netMac = PreferencesUtils.getString(MyApplication.appContext, Config.TRIPINGBLUETOOTHMACADDRESS);
                        if (!TextUtils.isEmpty(netMac)){
                            netMac = netMac.toUpperCase();
                            if (netMac.equals(device.getAddress())){
                                if (scanRecord[15] == (byte)0x01){//如果锁关了 那么就去结束行程
                                    if (onCloseListener != null){
                                        onCloseListener.onClose();
                                        myScan(false);
                                        return;
                                    }
                                }
                            }
                        }

                    }
                }
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
        void show(BluetoothAdapter mBluetoothAdapter);
    }
    private OnCloseListener onCloseListener;
    public void setOnCloseListener(OnCloseListener onCloseListener){
        this.onCloseListener = onCloseListener;
    }
}
