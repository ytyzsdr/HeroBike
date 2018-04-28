package vc.thinker.tools.utils;

import android.os.Handler;
import android.os.Message;

import java.util.Date;

/**
 * Created by farley on 17/4/24.
 * description:
 */

public class MyHandler extends Handler {
    private onCountChange onChange;
    private Date startTime;
    private Long maxCount;//倒计时
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 1:
                onChange.currentCount(Utils.l2lTotalTimes(startTime.getTime()+1000,System.currentTimeMillis()));
                break;
            case 2:
                maxCount = maxCount -1;
                onChange.currentCount(String.valueOf(maxCount));
                break;
            case 3:
                maxCount = maxCount -1;
                onChange.currentCount(Utils.timeShutDown(maxCount));
                break;
            case 4:
                maxCount = maxCount -1;
                onChange.currentCount(String.valueOf(maxCount));
                break;
            case 5:
                maxCount = maxCount -1;
                onChange.currentCount(String.valueOf(maxCount));
                break;
            default:
                break;
        }
    }

    public MyHandler(Date starttime) {

        this.startTime = starttime;
    }

    public interface onCountChange{
        void currentCount(String count);
    }
    public void setOnChangeLisener(onCountChange onCountChange){
        onChange = onCountChange;
    }

    public MyHandler(Long count) {

        this.maxCount = count;
    }
}