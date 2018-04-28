package com.danchexia.bikehero.bluetooth;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by farley on 17/7/27.
 * description:
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class SendDataUtils {
    /*public static void syncTime(BluetoothGattService mBluetoothLeService){
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString("0000fee7-0000-1000-8000-00805f9b34fb"));
        characteristic = mnotyGattService.getCharacteristic(UUID.fromString(MyGattAttributes.WRITE_UUID));
        //同步时间  0x01 0x01 0x04
        byte[] openLock = {(byte) 0x01, (byte) 0x01, (byte) 0x04,
                (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x23,
                (byte) 0x30, (byte) 0x54, (byte) 0x23,
                (byte) 0x34
        };
        for (int i = 0;i < 4 ;i++){
            openLock[i+3] = BLEUtils.longtobyte(System.currentTimeMillis()/1000)[i];
        }
        for (int i = 0;i < 4 ;i++){
            openLock[i+7] = MyGattAttributes.BLE_TOKEN[i];
        }
        Log.d("farley", "同步时间加密之前=" + BLEUtils.byte2hex(openLock));
        openLock = AES.encrypt(openLock);
        Log.d("farley", "同步时间加密之后=" + BLEUtils.byte2hex(openLock));
        //将指令放置进特征中
        characteristic.setValue(openLock);
        //开始写数据
        mBluetoothLeService.writeCharacteristic(characteristic);
    }*/
}
