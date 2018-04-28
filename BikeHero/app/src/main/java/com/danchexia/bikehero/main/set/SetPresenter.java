package com.danchexia.bikehero.main.set;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.api.api_destribut.UserController;
import com.danchexia.bikehero.main.set.bean.SetBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by farley on 17/5/24.
 * description:
 */

public class SetPresenter extends BasePresenter<ISetView>{
    private SetActivity activity;
    private UserController userController = APIControllerFactory.getUserApi();
    private MemberController memberController = APIControllerFactory.getMemberApi();
    public SetPresenter(SetActivity setActivity) {
        super();
        activity = setActivity;
    }

    /**
     * 获取h5
     * @param type
     */
    public void getGuideList(Integer type){
        addSubscription(memberController.getGuideList(type)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<SetBean>() {
            @Override
            public void call(SetBean setBean) {
                if (setBean.getError_code() == 0){
                    activity.setData(setBean);
                }else{
                    showErrorNone(setBean,activity);
                }
            }
        },getErrorAction(new OnHttpListener() {
            @Override
            public void onResult(BaseBean bean) {
                showErrorNone(bean,activity);
            }
        })));
    }

    /**
     * 退出登录
     */
    public void logout(){
        addSubscription(userController.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean bean) {
                        if (bean.getError_code() == 0){
                            showErrorLogin(bean,activity);
                        }else{
                            showErrorNone(bean,activity);
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean,activity);
                    }
                })));
    }
}
