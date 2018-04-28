package com.danchexia.bikehero.main.bindphone;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.ShowToast;

/**
 * Created by farley on 17/5/15.
 * description:
 */

public class BindPhonePresenter extends BasePresenter<IBindPhoneView> {
    private BindPhoneActivity activity;
    private MemberController memberController  = APIControllerFactory.getMemberApiNoToken();
    private MemberController urerToken;
    public BindPhonePresenter(BindPhoneActivity bindPhoneActivity) {
        super();
        activity = bindPhoneActivity;
    }

    /**
     * 获取验证码
     * @param phoneNum
     */
    public void getAuth(String phoneNum){
        addSubscription(memberController.getAuth(phoneNum).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<BaseBean>() {
            @Override
            public void call(BaseBean bean) {
                if (bean.getError_code() != 0){
                    showErrorNone(bean,activity);
                }else{
                    activity.getAuthSuccess();
                }
            }
        },getErrorAction(new OnHttpListener() {
            @Override
            public void onResult(BaseBean bean) {
                activity.setCountClose();
                showErrorNone(bean,activity);
            }
        })));
    }

    /**
     * 登录并保存token
     * @param phone
     * @param code
     */
    public void loginService(String phone,String code){
        activity.showLoading();
        addSubscription(memberController.doLogin(phone, code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String token) {
                        activity.hideLoading();
                        PreferencesUtils.putString(activity,"RADISHSAAS_IS_BIND",token);
                        MyApplication.setIsBindPhone(true);
                        LogUtils.d("token======"+token);
                        JPushInterface.setAlias(activity, token.replace("-",""), new TagAliasCallback() {
                            @Override
                            public void gotResult(int i, String s, Set<String> set) {
                                LogUtils.d("jpush======"+s);
                            }
                        });
                        urerToken = APIControllerFactory.getMemberApi();
                        getMyData();
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        activity.hideLoading();
                        showErrorNone(bean,activity);
                    }
                })));
    }
    /**
     * 获取个人资料
     */
    public void getMyData(){
        addSubscription(urerToken.getPersonalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PersonalBean>() {
                    @Override
                    public void call(PersonalBean personalBean) {
                        ShowToast.show(activity,activity.getString(R.string.bind_toast5));
                        if (personalBean != null){
                            MyUtils.d(personalBean);
                            switch (personalBean.getAuthStep()){
                                case 2:
                                    MyApplication.setIsIdenty(2);
                                    ActivityController.startRecharge(activity);
                                    break;
                                case 3:
                                    MyApplication.setIsIdenty(3);
                                    ActivityController.startIdentid(activity);
                                    break;
                                case 4:
                                    MyApplication.setIsIdenty(4);
                                    ActivityController.startMain(activity);
                                    break;
                                default:
                                    break;
                            }

                        }else{

                        }
                        MyUtils.savaPesonData(personalBean);
                        activity.finish();
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean,activity);
                    }
                })));
    }
}
