package com.danchexia.bikehero.main.personal;

import android.graphics.Bitmap;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.FileController;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.app.MyApplication;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.tools.utils.ShowToast;

/**
 * Created by farley on 17/5/17.
 * description:
 */

public class PersonalPresenter extends BasePresenter<IPersonalView> {
    private PersonalActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    FileController fileController = APIControllerFactory.getClientFileController();
    public PersonalPresenter(PersonalActivity personalActivity) {
        super();
        activity = personalActivity;
    }

    /**
     * 上传头像
     * @param imgUrl
     */
    public void upIcon(Bitmap imgUrl){
        if (imgUrl != null){
            addSubscription(fileController.postFile(MyApplication.appContext, imgUrl).
                    subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            addSubscription(userController.saveUserHead(s)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<BaseBean>() {
                                        @Override
                                        public void call(BaseBean bean) {
                                            if (bean.getError_code() == 0){
                                                ShowToast.show(activity,"修改成功");
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
                    }, getErrorAction(new OnHttpListener() {
                        @Override
                        public void onResult(BaseBean bean) {
                            showErrorNone(bean,activity);
                        }
                    })));
        }else{
            ShowToast.show(activity,"上传失败");
        }

    }

}

