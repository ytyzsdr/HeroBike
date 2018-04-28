package com.danchexia.bikehero.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.api_destribut.AppParamController;
import com.danchexia.bikehero.batterymain.BatteryMainActivity;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.utils.MyUtils;

import java.io.File;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.tools.utils.Utils;


/**
 * Created by farley on 17/6/2.
 * description:
 */

public class WelcaomActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (Utils.isNetOk(this)) {
            getSystem();
        }
    }
    AppParamController appParamController = APIControllerFactory.getSystemParams();
    private void getSystem(){
        appParamController.getSystemParams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SystemParamsBean>() {
                    @Override
                    public void call(final SystemParamsBean systemParamsBean) {
                        if (systemParamsBean.getError_code() == 0) {
                            MyUtils.savaSystemData(systemParamsBean);
                            File file = MyUtils.fileIsHave();
                            if (file.exists() && file.isDirectory() && file.listFiles().length > 0) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent main = new Intent(WelcaomActivity.this, AdvanceActivity.class);
                                        startActivity(main);
                                        finish();
                                    }
                                }, 2000);
                            } else {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent main;
                                        if (systemParamsBean.getOpenBattery() != null && systemParamsBean.getOpenBattery()) {
                                            main = new Intent(WelcaomActivity.this, BatteryMainActivity.class);
                                        } else {
                                            main = new Intent(WelcaomActivity.this, MainActivity.class);
                                        }
                                        startActivity(main);
                                        finish();
                                    }
                                }, 2000);
                            }
                        }
                    }
                });

    }
}
