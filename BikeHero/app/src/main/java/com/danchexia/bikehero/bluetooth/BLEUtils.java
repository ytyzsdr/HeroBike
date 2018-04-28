package com.danchexia.bikehero.bluetooth;

/**
 * Created by farley on 17/7/25.
 * description:BLE工具
 */

public class BLEUtils {
    //两个byte -->int
    public static  int byteToInt(byte b, byte c) {//计算总包长，两个字节表示的
        short s = 0;
        int ret;
        short s0 = (short) (c & 0xff);// 最低位
        short s1 = (short) (b & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        ret = s;
        return ret;
    }
    // int -->两个byte
    public static byte[] int2byte(int res) {
        byte[] targets = new byte[2];
        targets[1] = (byte) (res & 0xff);// 最低位
        targets[0] = (byte) ((res >> 8) & 0xff);// 次低位
        return targets;
    }
    //16进制字符串 -->byte[ ]
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }
    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }
    //byte[ ] -->16进制字符串
    public static String byte2hex(byte [] buffer){
        StringBuilder stringBuilder = new StringBuilder("");
        if (buffer == null || buffer.length <= 0) {
            return null;
        }
        for (int i = 0; i < buffer.length; i++) {
            int v = buffer[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
    //补齐16位
    public static byte[] add2Max(byte[] request){
        if (request.length == 16){
            return request;
        }
        byte[] result = new byte[16];
        for (int i=0;i < 16;i++){
            if (i < request.length){
                result[i] = request[i];
            }else {
                result[i] = (byte) 0x00;
            }
        }
        return result;
    }
    //long 2 byte
    public static byte[] longtobyte(long in) {
        byte[] a = new byte[4];
        a[3] = (byte) (0xff & in);
        a[2] = (byte) ((0xff00 & in) >> 8);
        a[1] = (byte) ((0xff0000 & in) >> 16);
        a[0] = (byte) ((0xff000000 & in) >> 24);
        return a;
    }
    //String转byte
    public static byte[] string2byte(String name){
        byte[] srtbyte = name.getBytes();
        return srtbyte;
    }
    public static String showData(byte[] data) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : data) {
            String b1 = String.format("%02X", b);
            stringBuffer.append(" " + b1);
        }
        return stringBuffer.toString();
    }
}
