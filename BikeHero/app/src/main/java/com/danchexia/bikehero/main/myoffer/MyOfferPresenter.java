package com.danchexia.bikehero.main.myoffer;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.main.myoffer.bean.MyOfferBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by farley on 17/5/22.
 * description:
 */

public class MyOfferPresenter extends BasePresenter<IMyOfferView> {
    private MyOfferActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    public MyOfferPresenter(MyOfferActivity myOfferActivity) {
        super();
        activity = myOfferActivity;
    }

    /**
     * 获取我的优惠券
     */
    public void getMyOffer(){
        activity.showLoading();
        addSubscription(userController.getMyOffer()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<MyOfferBean>() {
            @Override
            public void call(MyOfferBean myOfferBean) {
                activity.hideLoading();
                if (myOfferBean.getError_code() == 0){
                    activity.setData(myOfferBean);
                }else{
                    showErrorNone(myOfferBean,activity);
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
