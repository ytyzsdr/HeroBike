package com.danchexia.bikehero.main.my;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by farley on 17/5/16.
 * description:
 */

public class MyPresenter extends BasePresenter<IMyView> {
    private MyActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    public MyPresenter(MyActivity myActivity) {
        super();
        activity = myActivity;
    }
    /**
     * 获取个人资料
     */
    public void getMyData(){
        addSubscription(userController.getPersonalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PersonalBean>() {
                    @Override
                    public void call(PersonalBean personalBean) {
                        if (personalBean.getError_code() == 0){
                            MyUtils.savaPesonData(personalBean);
                            if (personalBean != null && !personalBean.getAuthStatus().contentEquals("2")){
                                switch (personalBean.getAuthStep()){
                                    case 2:
                                        MyApplication.setIsIdenty(2);
                                        break;
                                    case 3:
                                        MyApplication.setIsIdenty(3);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            activity.initData(personalBean);
                        }else{
                            showErrorNone(personalBean,activity);
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
