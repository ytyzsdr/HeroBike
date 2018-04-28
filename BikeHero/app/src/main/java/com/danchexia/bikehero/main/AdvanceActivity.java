package com.danchexia.bikehero.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.batterymain.BatteryMainActivity;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.AdvImages;
import com.danchexia.bikehero.main.wallet.WebViewNoBaseUrl;
import com.danchexia.bikehero.utils.MyUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.MyHandler;

/**
 * Created by farley on 17/8/15.
 * description:
 */

public class AdvanceActivity extends Activity {
    private ImageView advance;
    private MyHandler myHandler;
    private MyHandler myHandler1;
    private TextView turnout;
    private float imgTime = 0;
    private boolean flag = true;
    private AdvImages advanceData;
    private int thePos = 0;
    private boolean fount = false;
    List<Bitmap> imgs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);
        advance = (ImageView) findViewById(R.id.advance);
        turnout = (TextView) findViewById(R.id.turnout);
        File file = MyUtils.fileIsHave();
        advanceData = MyUtils.getAdvanceData();
        if (advanceData == null || advanceData.getImgList().size() < 1) {
            turnMain();
            return;
        }
        int time = advanceData.getTime();
        if (time < 1) {
            turnMain();
            return;
        }
        turnout.setText("跳过 " + time);

        if (file.exists() && file.isDirectory() && file.listFiles().length > 0) {
            for (File myFile : file.listFiles()) {
                Bitmap bitmap = BitmapFactory.decodeFile(myFile.getAbsolutePath());
                imgs.add(bitmap);
            }
        } else {
            turnMain();
        }
        Message msg = new Message();
        msg.what = 2;
        myHandler = new MyHandler((long) time);
        myHandler.setOnChangeLisener(new MyHandler.onCountChange() {
            @Override
            public void currentCount(String count) {
                turnout.setText("跳过 " + count);
                Message msg1 = new Message();
                msg1.what = 2;
                myHandler.sendMessageDelayed(msg1, 1000);

                if (count.contentEquals("0")) {
                    myHandler.removeMessages(2);
                    turnMain();
                }
            }
        });
        myHandler.sendMessageDelayed(msg,1);
        LogUtils.d("===========time=========="+time);
        imgTime = (float) time / advanceData.getImgList().size();
        LogUtils.d("===========imgTime=========="+imgTime);
        turnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnMain();
            }
        });
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i=0;i<imgs.size();i++){
                thePos = i;
                if (fount) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogUtils.d("===========gettime==========");
                            advance.setImageBitmap(imgs.get(thePos));
                            advance.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent webview = new Intent(AdvanceActivity.this, WebViewNoBaseUrl.class);
                                    webview.putExtra("TITLE", "详情");
                                    webview.putExtra("VIEWURL", advanceData.getImgList().get(thePos).getLinkUrl());
                                    webview.putExtra("NEEDTOKEN", false);
                                    startActivityForResult(webview, 111);
                                    flag = false;
                                }
                            });
                        }
                    });
                }
                try {
                    LogUtils.d("sleep========"+(long) (imgTime*1000));
                    MyThread.sleep((long) (imgTime*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fount = true;
        new MyThread().start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fount = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myHandler != null) {
            myHandler.removeMessages(2);
        }
        if (myHandler1 != null) {
            myHandler1.removeMessages(4);
        }
    }

    private void turnMain() {
        if (flag) {
            Intent main;
            if (Config.isOpenBattery){
                main = new Intent(AdvanceActivity.this,BatteryMainActivity.class);
            }else{
                main = new Intent(AdvanceActivity.this,MainActivity.class);
            }
            startActivity(main);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK){
            Intent main;
            if (Config.isOpenBattery){
                main = new Intent(AdvanceActivity.this,BatteryMainActivity.class);
            }else{
                main = new Intent(AdvanceActivity.this,MainActivity.class);
            }
            startActivity(main);
            finish();
        }
    }
}
