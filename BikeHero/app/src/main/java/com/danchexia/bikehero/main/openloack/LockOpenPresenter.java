package com.danchexia.bikehero.main.openloack;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.BicycleController;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.bluetooth.BluetoothUtils;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.bean.BlueToothBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.colours.client.model.BicycleStatusVO;
import vc.thinker.mvp.ControllerActivity;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/5/16.
 * description:
 */

public class LockOpenPresenter extends BasePresenter<ILockOpenView> {
    private LockOpenningActivity activity;
    TripController tripController = APIControllerFactory.openLock();
    BicycleController bicycleController = APIControllerFactory.getALLPark();
    public LockOpenPresenter(LockOpenningActivity lockOpenningActivity) {
        super();
        activity = lockOpenningActivity;
    }
    /**
     * 开锁
     */
    public void openLock(String code ,Double latitude,Double longtitude){
        addSubscription(tripController.openLock(code,latitude,longtitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BlueToothBean>() {

                    @Override
                    public void call(BlueToothBean bean) {//共享类型，bicycle,battery
                        if (bean.getError_code() == 0) {
                            if ("battery".equals(bean.getShareType())){
                                activity.requestOpenLockSuccessForBattery();
                            }else if ("bicycle".equals(bean.getShareType())) {
                                activity.requestOpenLockSuccess();
                            }
                            LogUtils.d("锁类型==================="+bean.getOpenType());
                            if (bean.getOpenType() == 2 || bean.getOpenType() == 3 || bean.getOpenType() == 4 || bean.getOpenType() == 5) {
                                if ("danchexia-bijiasuo-01".equals(bean.getBluetoothCode())){
                                    BluetoothUtils.myFilter_1 = 0x01;
                                    BluetoothUtils.myFilter_2 = 0x02;
                                }
                                activity.useBluetoothOpenLock(bean);
                            }
                        }else{
                            activity.requestOpenLockFailed(bean);
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showError(bean);
                    }
                })));
    }
    //蓝牙辅助开锁
    public void bluetoothOpenLock(String code,Boolean showPaw){
        LogUtils.d("开锁成功，去请求服务器");
        addSubscription(tripController.blueToothSuccess(code,showPaw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean bean) {
                        if (bean.getError_code() == 0) {
                            LogUtils.d("服务器返回=开锁成功");
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showError(bean);
                    }
                })));
    }
    //蓝牙开锁成功 上报电量
    public void uploadStatus(String code,int elect){
        BicycleStatusVO vo = new BicycleStatusVO();
        vo.setElectricity(elect);
        vo.setSysCode(code);
        addSubscription(bicycleController.uploadLockStatus(vo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean bean) {
                        if (bean.getError_code() == 0) {
                            LogUtils.d("上报成功");
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showError(bean);
                    }
                })));
    }
    private void showError(final BaseBean bean){
//        StanderdDialog dialog = StanderdDialog.getInstatnce(activity, bean.getResult(), activity.getString(R.string.toast_2), new StanderdDialog.OnDialogClickListener() {
//            @Override
//            public void doAnyClick() {
//
//            }
//
//            @Override
//            public void doMainClick() {
//                if (bean.getResult().contains("未完成认证")){
//                    ActivityController.startRecharge(activity);
//                    activity.finish();
//                }else{
//                    activity.openFailed();
//                }
//                if (bean.getError_code() == -10 ) {
//                    PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_IS_BIND", "");//退出登录要清空token 不能传null
//                    PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA", "");//退出登录要清空个人资料不能传null
//                    ActivityController.startBindPhone(activity);
//                    ControllerActivity.finishAll();
//                }
//            }
//        });
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(false);
        StanderdDialog dialog = new StanderdDialog(activity, bean.getResult(), activity.getString(R.string.toast_2),
                new StanderdDialog.OnDialogClickListener() {
                    @Override
                    public void doAnyClick() {
                    }

                    @Override
                    public void doMainClick() {
                        if (bean.getResult().contains("未完成认证")){
                            ActivityController.startRecharge(activity);
                            activity.finish();
                        }else{
                            activity.openFailed();
                        }
                        if (bean.getError_code() == -10 ) {
                            PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_IS_BIND", "");//退出登录要清空token 不能传null
                            PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA", "");//退出登录要清空个人资料不能传null
                            ActivityController.startBindPhone(activity);
                            ControllerActivity.finishAll();
                        }
                    }
                });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }
}
