package vc.thinker.tools.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import vc.thinker.tools.R;

/**
 * Created by farley on 17/3/3.
 * description:toast封装
 */

public class ShowToast {
//    public static void show(Context m,String msg){
//        Toast.makeText(m,msg,Toast.LENGTH_SHORT).show();
//    }
    public static void show(Context context, String message) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast, null);
        //初始化布局控件
        TextView myToast = (TextView) toastRoot.findViewById(R.id.myToast);
        //为控件设置属性
        myToast.setText(message);
        //Toast的初始化
        Toast toastStart = new Toast(context);
        //获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, (height/4)*3);
        toastStart.setDuration(Toast.LENGTH_LONG);
        toastStart.setView(toastRoot);
        toastStart.show();
    }
}
