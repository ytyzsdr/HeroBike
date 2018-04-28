package com.danchexia.bikehero.main.userguide;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.main.set.bean.SetBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by farley on 17/5/18.
 * description:
 */

public class UserGuidePresenter extends BasePresenter<IUserGuideView> {
    private UserGuideActivity activity;
    private MemberController userController = APIControllerFactory.getMemberApi();
    public UserGuidePresenter(UserGuideActivity userGuideActivity) {
        super();
        activity = userGuideActivity;
    }
    /**
     * 获取h5
     * @param type
     */
    public void getGuideList(Integer type){
        addSubscription(userController.getGuideList(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SetBean>() {
                    @Override
                    public void call(SetBean setBean) {
                        if (setBean.getError_code() == 0){
                            activity.loadData(setBean);
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
}
