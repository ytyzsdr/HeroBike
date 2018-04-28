package com.danchexia.bikehero.main.certifacation;

import android.text.TextUtils;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.FileController;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.colours.client.model.RealnameVO;
import vc.thinker.tools.utils.PreferencesUtils;

/**
 * Created by farley on 17/5/17.
 * description:
 */

public class CertifacationPresenter extends BasePresenter<ICertifacationView> {
    private CertifacationActivity activity;
    MemberController memberController = APIControllerFactory.getMemberApi();
    FileController fileController = APIControllerFactory.getClientFileController();
    MemberController userController = APIControllerFactory.getMemberApi();
    public CertifacationPresenter(CertifacationActivity certifacationActivity) {
        super();
        activity = certifacationActivity;
    }

    /**
     * 认证
     */
    public void iDent(RealnameVO vo){
        addSubscription(memberController.ident(vo)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<PersonalBean>() {
            @Override
            public void call(PersonalBean bean) {
                activity.hideLoading();
                if (bean.getError_code() == 0){
                    MyUtils.savaPesonData(bean);
                    activity.setBtnEnable();
                    activity.setAuthStatus(bean);
                }else{
                    showErrorNone(bean,activity);
                    activity.setBtnEnable();
                }
            }
        }));
    }
    public void addimg(final RealnameVO vo){
        activity.showLoading();
        if (activity.getImageList() != null) {
            for (int i = 0; i < activity.getImageList().size(); i++) {
                final int finalI = i;
                addSubscription(fileController.postFile(MyApplication.appContext, activity.getImageList().get(i)).
                        subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                if (!TextUtils.isEmpty(s)) {
                                    vo.getCredentialsImages().add(s);
                                }
                                if (finalI == activity.getImageList().size() -1){
                                    iDent(vo);
                                }
                            }
                        }, getErrorAction(new OnHttpListener() {
                            @Override
                            public void onResult(BaseBean bean) {
                                showErrorNone(bean, activity);
                            }
                        })));
            }

        }else{
            iDent(vo);
        }

    }
    /**
     * 获取个人资料
     */
    public void getMyData() {
        addSubscription(userController.getPersonalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PersonalBean>() {
                    @Override
                    public void call(PersonalBean personalBean) {
                        if (personalBean.getError_code() == 0) {
                            if (personalBean.getError_code() == 0){
                                MyUtils.savaPesonData(personalBean);
                                activity.setBtnEnable();
                                activity.setAuthStatus(personalBean);
                            }else{
                                showErrorNone(personalBean,activity);
                                activity.setBtnEnable();
                            }
                        } else {
                            showErrorNone(personalBean, activity);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        if (throwable != null) {
                            if (throwable.getMessage().contains("OAuthProblemException")) {
                                PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_IS_BIND", "");
                                PreferencesUtils.putString(MyApplication.appContext, "RADISHSAAS_PERSONAL_DATA", "");
                            }
                        }
                    }
                }));
    }
}
