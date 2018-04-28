package com.danchexia.bikehero.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import vc.thinker.tools.utils.LogUtils;

/**
 * Created by farley on 17/7/27.
 * description:
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothUtils {
    public static byte myFilter_1 = 0x11;
    public static byte myFilter_2 = 0x11;
    // 10秒后停止查找搜索.
    private static final long SCAN_PERIOD = 10000;
    private static boolean mScanning;//标记是否还在扫描中
    private static BluetoothAdapter mBluetoothAdapter;
    public static final int REQUEST_ENABLE_BT = 1;
    public static String mDeviceAddress;//要链接的mac
    public static BluetoothLeService mBluetoothLeService;
    public static List<String> deviceList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void scanLeDevice(boolean enable) {
        LogUtils.d("搜索属性=======" + enable);
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
            deviceList.clear();
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }

    //初始化蓝牙    1
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void initBluetooth(Context context) {
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

    //提醒用户打开蓝牙
    public static void tipsUser(Activity activity) {
        if (!bluetoothIsEnable()) {
            // 为了确保设备上蓝牙能使用, 如果当前蓝牙设备没启用,弹出对话框向用户要求授予权限来启用
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    public static boolean bluetoothIsEnable() {
        return mBluetoothAdapter.isEnabled();
    }

    //用户的选择
    public static void onActivityResultFun(int requestCode, int resultCode) {
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            return;
        }
    }

    // Device scan callback.
    private static BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, final byte[] scanRecord) {
            LogUtils.d("myFilter_1=======" + myFilter_1+";"+myFilter_2);
            LogUtils.d("搜索结果=======" + device.getName() + ";" + device.getAddress() + BLEUtils.showData(scanRecord));
            for (int i = 0; i < scanRecord.length; i++) {
                if (scanRecord[i] == myFilter_1 && scanRecord[i + 1] == myFilter_2) {
                    if (!deviceList.contains(device.getAddress())) {
                        LogUtils.d("device=" + device.getAddress() + ";showData=" + BLEUtils.showData(scanRecord));
                        deviceList.add(device.getAddress());
//                        if (onResultChangeListener != null) {
//                            onResultChangeListener.onChange(scanRecord);
//                        }
                    }
                }
            }

        }
    };

    public static boolean isConnectBluetooth() {
        if (mBluetoothLeService == null) {
            return false;
        }
        BluetoothGattService mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString(MyGattAttributes.LOCK_SERVICE));
        if (mnotyGattService != null) {
            return true;
        } else {
            return false;
        }
    }

    // Code to manage Service lifecycle.管理服务生命周期的代码。
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
            }
            // Automatically connects to the device upon successful start-up initialization.成功启动初始化后自动连接到设备。
//            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void getBluetoothToken() {
        BluetoothGattService mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString(MyGattAttributes.LOCK_SERVICE));
        BluetoothGattCharacteristic characteristic = mnotyGattService.getCharacteristic(UUID.fromString(MyGattAttributes.READ_UUID));
        int charaProp = characteristic.getProperties();
        if (characteristic.getUuid().toString().contains(MyGattAttributes.READ_UUID) && (charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
            Log.e("farley", "gattCharacteristic的属性为:  可通知");
            mBluetoothLeService.setCharacteristicNotification(characteristic, true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void connectBluetoothData() {
        BluetoothGattService mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString(MyGattAttributes.LOCK_SERVICE));
        BluetoothGattCharacteristic characteristic = mnotyGattService.getCharacteristic(UUID.fromString(MyGattAttributes.WRITE_UUID));
        int charaProp = characteristic.getProperties();
        if (characteristic.getUuid().toString().contains(MyGattAttributes.WRITE_UUID) && (charaProp | BluetoothGattCharacteristic.PROPERTY_WRITE) > 0) {
            Log.e("farley", "gattCharacteristic的属性为:  可写");
            //获取令牌
            byte[] thisString = {(byte) 0x06, (byte) 0x01, (byte) 0x01, (byte) 0x01,
                    (byte) 0x2C, (byte) 0xBC, (byte) 0x62, (byte) 0x58,
                    (byte) 0x96, (byte) 0x67, (byte) 0x42, (byte) 0x92,
                    (byte) 0x01, (byte) 0x33, (byte) 0x31, (byte) 0x41};
            Log.d("farley", "获取令牌加密之前=" + BLEUtils.byte2hex(thisString));
            thisString = AES.encrypt(thisString);
            Log.d("farley", "获取令牌加密之后=" + BLEUtils.byte2hex(thisString));
            //将指令放置进特征中
            characteristic.setValue(thisString);
            //开始写数据
            mBluetoothLeService.writeCharacteristic(characteristic);
        }
    }

    public static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    //获取锁状态
    public static void getLockstatus() {
        BluetoothGattCharacteristic characteristic;
        LogUtils.e("farley", "mBluetoothLeService=" + mBluetoothLeService);
        BluetoothGattService mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString(MyGattAttributes.LOCK_SERVICE));
        LogUtils.e("farley", "mnotyGattService=" + mnotyGattService);
        characteristic = mnotyGattService.getCharacteristic(UUID.fromString(MyGattAttributes.WRITE_UUID));
        //开锁  0x05 0x01 0x06 psw[6]
        byte[] openLock = {(byte) 0x05, (byte) 0x0E, (byte) 0x01, (byte) 0x01};
        Log.d("farley", "addtoken之前=" + BLEUtils.byte2hex(openLock));
        openLock = addToken(openLock);
        Log.d("farley", "获取状态加密之前=" + BLEUtils.byte2hex(openLock));
        openLock = AES.encrypt(openLock);
        Log.d("farley", "获取状态加密之后=" + BLEUtils.byte2hex(openLock));
        //将指令放置进特征中
        characteristic.setValue(openLock);
        //开始写数据
        mBluetoothLeService.writeCharacteristic(characteristic);
    }

    public static void syncTime() {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString(MyGattAttributes.LOCK_SERVICE));
        characteristic = mnotyGattService.getCharacteristic(UUID.fromString(MyGattAttributes.WRITE_UUID));
        //同步时间  0x01 0x01 0x04
        byte[] snyctime = {(byte) 0x01, (byte) 0x01, (byte) 0x04,
                (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x23,
                (byte) 0x30, (byte) 0x54, (byte) 0x23,
                (byte) 0x34
        };
        for (int i = 0; i < 4; i++) {
            snyctime[i + 3] = BLEUtils.longtobyte(System.currentTimeMillis() / 1000)[i];
        }
        for (int i = 0; i < 4; i++) {
            snyctime[i + 7] = MyGattAttributes.BLE_TOKEN[i];
        }
        Log.d("farley", "同步时间加密之前=" + BLEUtils.byte2hex(snyctime));
        snyctime = AES.encrypt(snyctime);
        Log.d("farley", "同步时间加密之后=" + BLEUtils.byte2hex(snyctime));
        //将指令放置进特征中
        characteristic.setValue(snyctime);
        //开始写数据
        mBluetoothLeService.writeCharacteristic(characteristic);
    }

    //开锁
    public static void openBlueLock(byte[] password) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString(MyGattAttributes.LOCK_SERVICE));
        characteristic = mnotyGattService.getCharacteristic(UUID.fromString(MyGattAttributes.WRITE_UUID));
        //开锁  0x05 0x01 0x06 psw[6]
        byte[] openLock = {(byte) 0x05, (byte) 0x01, (byte) 0x06,
                password[0], password[1], password[2],
                password[3], password[4], password[5]
        };
        Log.d("farley", "addtoken之前=" + BLEUtils.byte2hex(openLock));
        openLock = addToken(openLock);
        Log.d("farley", "开锁加密之前=" + BLEUtils.byte2hex(openLock));
        openLock = AES.encrypt(openLock);
        Log.d("farley", "开锁加密之后=" + BLEUtils.byte2hex(openLock));
        //将指令放置进特征中
        characteristic.setValue(openLock);
        //开始写数据
        mBluetoothLeService.writeCharacteristic(characteristic);
    }

    //获取电量
    public static void getElect() {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString(MyGattAttributes.LOCK_SERVICE));
        characteristic = mnotyGattService.getCharacteristic(UUID.fromString(MyGattAttributes.WRITE_UUID));
        byte[] openLock = {(byte) 0x02, (byte) 0x01, (byte) 0x01,
                (byte) 0x01, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x23,
                (byte) 0x30, (byte) 0x54, (byte) 0x23,
                (byte) 0x34
        };
        for (int i = 0; i < 4; i++) {
            openLock[i + 4] = MyGattAttributes.BLE_TOKEN[i];
        }
        Log.d("farley", "检测电量之前=" + BLEUtils.byte2hex(openLock));
        openLock = AES.encrypt(openLock);
        Log.d("farley", "检测电量加密之后=" + BLEUtils.byte2hex(openLock));
        //将指令放置进特征中
        characteristic.setValue(openLock);
        //开始写数据
        mBluetoothLeService.writeCharacteristic(characteristic);
    }

    //添加token
    private static byte[] addToken(byte[] type) {
        byte[] result = new byte[16];
        if (type == null) {
            return null;
        }
        if (type.length > 16) {
            return type;
        }
        for (int i = 0; i < type.length; i++) {
            result[i] = type[i];
        }
        for (int i = type.length; i < type.length + 4; i++) {
            result[i] = MyGattAttributes.BLE_TOKEN[i - type.length];
        }
        return result;
    }

    public static byte[] getBlueByteArray(String param, int len) {
        byte[] result = new byte[len];
        String[] key = param.split(":");
        for (int i = 0; i < key.length; i++) {
            try {
                result[i] =  Integer.valueOf(key[i],16).byteValue();
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }

    public static void disConnect(BluetoothDevice device) {
        mBluetoothLeService.disconnect(device);
    }

    private static OnResultChangeListener onResultChangeListener;

    public interface OnResultChangeListener {
        void onChange(byte[] scanRecord);
    }

    public void setOnResultChangeListener(OnResultChangeListener onResultChangeListener) {
        this.onResultChangeListener = onResultChangeListener;
    }
}
