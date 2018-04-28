package com.danchexia.bikehero.main.fixpower;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.mvp.MvpView;

/**
 * Created by farley on 17/9/5.
 * description:
 */

public class FixPowerPresenter extends BasePresenter<MvpView> {
    private FixPowerActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    public FixPowerPresenter(FixPowerActivity activity) {
        this.activity = activity;
    }
    /**
     * 修改功率
     * @param power
     */
    public void fixPower(Integer power){
        activity.showLoading();
        addSubscription(userController.fixPower(power)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean bean) {
                        activity.hideLoading();
                        if (bean.getError_code() == 0){
                            activity.setSuccessDo();
                        }else{
                            showErrorNone(bean,activity);
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        activity.hideLoading();
                        showErrorNone(bean,activity);
                    }
                })));
    }
}
