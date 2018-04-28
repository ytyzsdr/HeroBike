package com.danchexia.bikehero.bluetooth;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import vc.thinker.tools.utils.LogUtils;

/**
 * Created by farley on 17/8/29.
 * description:轮询锁
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public  class MyNewThread extends Thread{
    private String address;

    public MyNewThread(String address) {
        this.address = address;
    }

    @Override
    public void run() {
        super.run();
        if (!TextUtils.isEmpty(address)) {
            for (int i = 0; i < 10; i++) {
                String netAddress = address.toUpperCase().trim();
                LogUtils.w("netAddress=" + netAddress);
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
