package com.danchexia.bikehero.bluetooth;

import java.util.HashMap;

/**
 * Created by farley on 17/7/25.
 * description:GATT属性
 */

public class MyGattAttributes {
    private static HashMap<String, String> attributes = new HashMap();
    public static String WRITE_UUID = "000036f5-0000-1000-8000-00805f9b34fb";//写的uuid
    public static String READ_UUID = "000036f6-0000-1000-8000-00805f9b34fb";//读的uuid
    public static String LOCK_SERVICE = "0000fee7-0000-1000-8000-00805f9b34fb";//锁服务
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public static byte[] BLE_TOKEN = new byte[4];

    static {
        // Sample Services.
        attributes.put("0000fee7-0000-1000-8000-00805f9b34fb", "锁服务");
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information Service");
        // Sample Characteristics.
        attributes.put(READ_UUID, "ReadData UUID");
        attributes.put(WRITE_UUID, "WriteData UUID");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
