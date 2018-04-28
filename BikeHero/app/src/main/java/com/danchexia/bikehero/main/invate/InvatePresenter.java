package com.danchexia.bikehero.main.invate;

import android.content.Intent;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.main.MainActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.tools.utils.ShowToast;


/**
 * Created by farley on 17/5/17.
 * description:
 */

public class InvatePresenter extends BasePresenter<IInvateView> {
    private InvateActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    public InvatePresenter(InvateActivity invateActivity) {
        super();
        activity = invateActivity;
    }

    /**
     * 填写邀请码
     * @param code
     */
    public void boundInvate(String code){
        addSubscription(userController.boundInavate(code)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<BaseBean>() {
            @Override
            public void call(BaseBean bean) {
                if (bean.getError_code() == 0){
                    ShowToast.show(activity,activity.getString(R.string.certifacation_toast3));
                    Intent main = new Intent(activity,MainActivity.class);
                    activity.startActivity(main);
                    activity.finish();
                }else{
                    showErrorNone(bean,activity);
                }
            }
        }));
    }
}
