package com.danchexia.bikehero.main.personal;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by farley on 17/6/2.
 * description:
 */

public class FixUserNamePresenter extends BasePresenter<IPersonalView> {
    private FixUserNameActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    public FixUserNamePresenter(FixUserNameActivity fixUserNameActivity) {
        super();
        activity = fixUserNameActivity;
    }
    /**
     * 上传昵称
     * @param name
     */
    public void upNicName(String name){
        activity.showLoading();
        addSubscription(userController.saveUserNickname(name)
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
