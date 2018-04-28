package vc.thinker.tools.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vc.thinker.tools.R;
import vc.thinker.tools.dialog.StanderdDialog;

/**
 * Created by farley on 17/3/13.
 * description:工具类
 */

public class Utils {
    /**
     * 获取设备信息
     *
     * @param context
     * @return
     */
    public static String getAppMsg(Context context) {
        String appVersion = "";
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            appVersion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String model = "Android/" + Build.MODEL + "/"
                + Build.VERSION.SDK_INT + "/"
                + Build.VERSION.RELEASE + "/" + appVersion;
        ;
        return model;
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getReversion(Context context) {
        String appVersion = "";
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            appVersion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String model = "Product Model: " + Build.MODEL + ","
                + Build.VERSION.SDK_INT + ","
                + Build.VERSION.RELEASE + "/" + appVersion;
        LogUtils.d("appVersion=" + appVersion);
        return appVersion;
    }
    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isNetOk(Context context){
        if (isNetworkAvailable(context)){
            return true;
        }else{
            Toast.makeText(context,"当前网络不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    /**
     * 将时间转换为时间戳
     */
    public static Long dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        Long ts = date.getTime();
        return ts;
    }

    /**
     * 将时间戳转换为时间MM月dd日 HH:mm
     */
    public static String stampToDate(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        res = simpleDateFormat.format(new Date(s));
        LogUtils.d("res=" + res);
        return res;
    }

    /**
     * 将时间戳转换为时间yyyy.MM.dd HH:mm:ss
     */
    public static String stampToDate2(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        res = simpleDateFormat.format(new Date(s));
        LogUtils.d("res=" + res);
        return res;
    }

    /**
     * 将时间戳转换为时间yyyy-MM-dd HH:mm:ss
     */
    public static String stampToDate3(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        res = simpleDateFormat.format(new Date(s));
        LogUtils.d("res=" + res);
        return res;
    }

    /**
     * 将时间戳转换为时间yyyy-MM-dd
     */
    public static String stampToDate4(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        res = simpleDateFormat.format(new Date(s));
        LogUtils.d("res=" + res);
        return res;
    }

    /**
     * 将时间戳转换为时间yyyy-MM-dd HH:mm
     */
    public static String stampToDate5(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        res = simpleDateFormat.format(new Date(s));
        LogUtils.d("res=" + res);
        return res;
    }

    /**
     * 匹配手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^1[34578]\\d{9}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 计算前后时间的差值
     */
    public static String l2lTotalTime(Long start, Long end) {
        String result = "";
        long temp = (end - start) / 1000;
        long days = temp / (60 * 60 * 24);
        long hours = (temp % (60 * 60 * 24)) / (60 * 60);
        long minutes = (temp % (60 * 60)) / 60;
        if (days > 0) {
            result = days + "天" + hours + "小时" + minutes + "分钟";
        } else if (hours > 0) {
            result = hours + "小时" + minutes + "分钟";
        } else if (minutes > 0) {
            result = minutes + "分钟";
        }
        return result;
    }
    /**
     * long 2 00:00
     */
    public static String timeShutDown(Long time) {
        String result = "";
        long minutes = time / 60;
        long seconds = time % 60;
        result = minutes+":"+seconds;
        return result;
    }

    /**
     * 计算前后时间的差值精确打牌秒至
     */
    public static String l2lTotalTimes(Long start, Long end) {
        String result = "";
        long temp = (end - start) / 1000;
        long days = temp / (60 * 60 * 24);
        long hours = (temp % (60 * 60 * 24)) / (60 * 60);
        long minutes = (temp % (60 * 60)) / 60;
        long seconds = (temp % (60 * 60)) % 60;
        if (days > 0) {
            result = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (hours > 0) {
            result = hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (minutes > 0) {
            result = minutes + "分钟" + seconds + "秒";
        } else if (seconds > 0) {
            result = seconds + "秒";
        }
        return result;
    }

    /**
     * 秒转换时间
     */
    public static String secondsToTime(Long time) {
        String result = "";
        long days = time / (60 * 60 * 24);
        long hours = (time % (60 * 60 * 24)) / (60 * 60);
        long minutes = (time % (60 * 60)) / 60;
        if (days > 0) {
            result = days + "天" + hours + "小时" + minutes + "分钟";
        } else if (hours > 0) {
            result = hours + "小时" + minutes + "分钟";
        } else if (minutes >= 0) {
            result = minutes + "分钟";
        }
        return result;
    }

    /**
     * 获取当前日期
     */
    public static String getThisDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String result = "";
        result = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        return result;
    }

    /**
     * 获取当前时间戳
     */
    public static Long getThisTime() {
        Long time = System.currentTimeMillis();
        return time;
    }

    /**
     * 地图应用是否安装
     *
     * @return
     */
    public static boolean isGdMapInstalled() {
        return isInstallPackage("com.autonavi.minimap");
    }

    public static boolean isBaiduMapInstalled() {
        return isInstallPackage("com.baidu.BaiduMap");
    }

    private static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 获取打开百度地图应用uri [http://lbsyun.baidu.com/index.php?title=uri/api/android]
     *
     * @param originLat
     * @param originLon
     * @param desLat
     * @param desLon
     * @return
     */
    public static String getBaiduMapUri(String originLat, String originLon, String originName, String desLat, String desLon, String destination, String region, String src) {
        String uri = "intent://map/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving&region=%7$s&src=%8$s#Intent;" +
                "scheme=bdapp;package=com.baidu.BaiduMap;end";

        return String.format(uri, originLat, originLon, originName, desLat, desLon, destination, region, src);
    }

    /**
     * 获取打开高德地图应用uri
     */
    public static String getGdMapUri(String appName, String slat, String slon, String sname, String dlat, String dlon, String dname) {
        String uri = "androidamap://route?sourceApplication=%1$s&slat=%2$s&slon=%3$s&sname=%4$s&dlat=%5$s&dlon=%6$s&dname=%7$s&dev=0&m=0&t=2";
        return String.format(uri, appName, slat, slon, sname, dlat, dlon, dname);
    }


    /**
     * 网页版百度地图 有经纬度
     *
     * @param originLat
     * @param originLon
     * @param originName  ->注：必填
     * @param desLat
     * @param desLon
     * @param destination
     * @param region      : 当给定region时，认为起点和终点都在同一城市，除非单独给定起点或终点的城市。-->注：必填，不填不会显示导航路线
     * @param appName
     * @return
     */
    public static String getWebBaiduMapUri(String originLat, String originLon, String originName, String desLat, String desLon, String destination, String region, String appName) {
        String uri = "http://api.map.baidu.com/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving&region=%7$s&output=html" +
                "&src=%8$s";
        return String.format(uri, originLat, originLon, originName, desLat, desLon, destination, region, appName);
    }


    /**
     * 百度地图定位经纬度转高德经纬度
     *
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public static double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }

    /**
     * 高德地图定位经纬度转百度经纬度
     *
     * @param gd_lon
     * @param gd_lat
     * @return
     */
    public static double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
        double[] bd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gd_lon, y = gd_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
        bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
        return bd_lat_lon;
    }

    public static void openGaoDeMap(Context context, String parkName, double[] location) {
        try {
            Intent intent = Intent.getIntent("androidamap://viewMap?sourceApplication=智能车库&poiname=" + parkName + "&lat=" + location[0] + "&lon=" + location[1] + "&dev=0");
            context.startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void openBaiduMap(Context context, String parkName, double[] location) {
        Intent i1 = new Intent();

// 驾车导航

        i1.setData(Uri.parse("baidumap://map/navi?location=" + location[1] + "," + location[0]));

        context.startActivity(i1);
    }


    /**
     * 根据需求获取对应的图片，此举是为了节省流量
     *
     * @param url
     * @param size
     * @return
     */
    public static String trunImgTypr(String url, String size) {
        return url + "_" + size;
    }


    /**
     * string转bitmap
     *
     * @return
     */
    public static Bitmap getBitmapFromFile(File dst, int width, int height) {
        if (null != dst && dst.exists()) {
            BitmapFactory.Options opts = null;
            if (width > 0 && height > 0) {
                opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(dst.getPath(), opts);
                // 计算图片缩放比例
                final int minSideLength = Math.min(width, height);
                opts.inSampleSize = computeSampleSize(opts, minSideLength,
                        width * height);
                opts.inJustDecodeBounds = false;
                opts.inInputShareable = true;
                opts.inPurgeable = true;
            }
            try {
                return BitmapFactory.decodeFile(dst.getPath(), opts);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
                .floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }


    /**
     * 获取屏幕宽高
     *
     * @param context
     */
    public static int screenWidth;
    public static int screenHeight;
    public static int dpWith;
    public static int densityDpi;

    public static void getScreenWithAndHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);

        //窗口的宽度
        screenWidth = dm.widthPixels;

        //窗口高度
        screenHeight = dm.heightPixels;
        densityDpi = dm.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        dpWith = screenWidth / (densityDpi / 160);
    }

    /**
     * 为了防止null 转为String
     */
    public static String object2String(Object str) {
        String result = " ";
        result = String.valueOf(str);
        return result;
    }

    /**
     * 验证手机号码
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1]\\d{10}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 验证手机号码
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((17[0-9])|(13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$";
        return check(cellphone, regex);
    }
    static boolean flag = false;
    public static boolean check(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    /**
     * 验证身份证号码的正确性
     *
     */
    public static boolean checkIdCard(String cardid){
        String ls_id = cardid;
        if(ls_id.length() != 18)
        {
            return false;
        }
        char[] l_id = ls_id.toCharArray();
        int l_jyw = 0;
        int[] wi = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
        char[] ai= new char[]{'1','0','X','9','8','7','6','5','4','3','2'};
        for(int i =0 ; i < 17; i++)
        {
            if(l_id[i] < '0' || l_id[i] > '9')
            {
                return false;
            }
            l_jyw += (l_id[i] -'0')*wi[i];
        }
        l_jyw = l_jyw % 11;
        if(ai[l_jyw] != l_id[17])
        {
            return false;
        }
        return true;
    }

    /**
     * string 同意转换bitmap
     * @param imgArray
     * @return
     */
    public static List<Bitmap> getImageList(List<String> imgArray)
    {
        List<Bitmap> bitmaps = new ArrayList<>();
        if (imgArray.size() > 0)
        {
            for (String url :imgArray) {
                File file  = new File(url);
                Bitmap bitmap = getBitmapFromFile(file,1000,1000);
                LogUtils.d("bitmap="+bitmap);
                if (bitmap != null) {
                    bitmaps.add(bitmap);
                }
            }
            return bitmaps;
        }
        return  null;
    }
    /**
     * 打电话
     * @param phone
     */
    public static  void callPhone(final Context mContext, final String phone) {
        if (TextUtils.isEmpty(phone)){
            ShowToast.show(mContext,"号码错误，请退出重试。");
        }else {
            StanderdDialog dialog = new StanderdDialog(mContext, "拨打电话", phone, "确定", "取消"
                    , new StanderdDialog.OnDialogClickListener() {
                @Override
                public void doAnyClick() {

                }

                @Override
                public void doMainClick() {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
            dialog.show();
        }
    }

    /**
     * bitmap 保存本地
     * @param bmp
     * @return
     */
    public static void saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "share");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "share.jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取指定view 的截图
     */
    public static String getViewImg(View view){
        String imgStr = null;
        view.setDrawingCacheEnabled(true);
        Bitmap tBitmap = view.getDrawingCache();
        // 拷贝图片，否则在setDrawingCacheEnabled(false)以后该图片会被释放掉
        tBitmap = tBitmap.createBitmap(tBitmap);
        view.setDrawingCacheEnabled(false);
        if (tBitmap != null) {
            saveImage(tBitmap);
            File appDir = new File(Environment.getExternalStorageDirectory()+ "/share/"+"share.jpg");
            if (appDir.exists() ){
                imgStr = appDir.getPath();
            }
            return imgStr;
        }else{
            return "";
        }
    }

    /**
     * 保留1位小数
     * @param obj
     * @return
     */
    public static String wait2wei(Double obj){
        DecimalFormat df = new DecimalFormat("######0.0");
        return df.format(obj);
    }
    /**
     * 判断服务是否运行
     *
     * @param context
     * @param clazz
     * @return
     */
    public static boolean isRunging(Context context,
                                    Class<? extends Service> clazz) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> list = am.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo info : list) {
            ComponentName service = info.service;
            String className = service.getClassName();
            if (className.equals(clazz.getName())) {
                return true;
            }
        }

        return false;
    }

}