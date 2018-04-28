package com.danchexia.bikehero.main.myinvate;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by farley on 17/5/18.
 * description:
 */

public class MyInvatePresenter extends BasePresenter<IMyInvateView> {
    private MyInvateActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    public MyInvatePresenter(MyInvateActivity myInvateActivity) {
        super();
        activity = myInvateActivity;
    }

    /**
     * 获取邀请好友之后自己能获得多少钱
     */
    public void getInvateMoney(){
        activity.showLoading();
        addSubscription(userController.getInvateMoney()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<BaseBean>() {
            @Override
            public void call(BaseBean bean) {
                activity.hideLoading();
                if (bean.getError_code() == 0){
                    activity.setInvateMoney(bean);
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
