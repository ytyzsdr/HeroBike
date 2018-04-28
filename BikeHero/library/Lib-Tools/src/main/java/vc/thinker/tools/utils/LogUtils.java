package vc.thinker.tools.utils;

import android.util.Log;

import vc.thinker.tools.BuildConfig;

/**
 * Created by farley on 17/3/3.
 * description:日志打印封装，可以根据自己的实际情况再另行添加其他方法
 */

public class LogUtils {
    public static boolean isEnable = true;
    public static void d(String msg){
        if (isEnable) {
            Log.d("farley", msg);
        }
    }
    public static void i(String Tag,String msg){
        if (isEnable) {
            Log.i("farley", msg);
        }
    }
    public static void e(String Tag,String msg){
        if (isEnable) {
            Log.e("farley", msg);
        }
    }
    public static void w(String msg){
        if (isEnable) {
            Log.e("farley", msg);
        }
    }
}
