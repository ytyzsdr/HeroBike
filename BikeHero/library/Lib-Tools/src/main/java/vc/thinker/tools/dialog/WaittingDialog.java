package vc.thinker.tools.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import vc.thinker.tools.R;
import vc.thinker.tools.utils.MyHandler;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/8/10.
 * description:
 */

public class WaittingDialog extends AlertDialog {
    private Long time = 0L;
    private boolean isBattery;
    public WaittingDialog(Context context,boolean isBattery) {
        super(context, R.style.myDialog);
        mContext = context;
        this.isBattery = isBattery;
    }

    private ImageView refresh, success;
    private Context mContext;
    private TextView myTime;
    private TextView wait_desc;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_waitting);
        setCancelable(false);
        initView();
    }

    private void initView() {
        wait_desc = (TextView) findViewById(R.id.wait_desc);
        myTime = (TextView) findViewById(R.id.myTime);
        refresh = (ImageView) findViewById(R.id.refresh);
        success = (ImageView) findViewById(R.id.success);
        RotateAnimation gearAnim = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.wait_dialog_anim);
        refresh.setVisibility(View.VISIBLE);
        refresh.startAnimation(gearAnim);
        /*myTime.setText(Utils.object2String(time)+"s");
        Message msg = new Message();
        msg.what = 4;
        myHandler = new MyHandler(time);
        myHandler.setOnChangeLisener( new MyHandler.onCountChange() {
            @Override
            public void currentCount(String count) {
                myTime.setText(Utils.object2String(count)+"s");
                Message msg1 = new Message();
                msg1.what = 4;
                myHandler.sendMessageDelayed(msg1,1000);
                if (count.contentEquals("0")){
                    dismiss();
                }
            }
        });
        myHandler.sendMessageDelayed(msg,1000);*/
        if (isBattery){
            wait_desc.setText("正在断开蓄电池连接");
        }
    }

    public void setSuccess() {
        ScaleAnimation scalAnim = (ScaleAnimation) AnimationUtils.loadAnimation(mContext, R.anim.wait_dialog_scal_bg);
        refresh.clearAnimation();
        refresh.setVisibility(View.GONE);
        success.setVisibility(View.VISIBLE);
        success.startAnimation(scalAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        },1000);
    }
    public void setSuccessNoOk() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        },1000);
    }

}
