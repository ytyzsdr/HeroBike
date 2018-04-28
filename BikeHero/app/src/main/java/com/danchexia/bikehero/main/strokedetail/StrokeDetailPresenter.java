package com.danchexia.bikehero.main.strokedetail;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by farley on 17/5/18.
 * description:
 */

public class StrokeDetailPresenter extends BasePresenter<IStrokeDetailView> {
    private StrokeDetailActivity activity;
    TripController tripController = APIControllerFactory.getAllStroke();
    public StrokeDetailPresenter(StrokeDetailActivity strokeDetailActivity) {
        super();
        activity = strokeDetailActivity;
    }
    //获取行程详情tid
    public void profileUsing(Long tid){
        addSubscription(tripController.profileUsing(tid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OnGoing_TripBO>() {
                    @Override
                    public void call(OnGoing_TripBO onGoing_tripBO) {
                        if (onGoing_tripBO.getError_code() == 0){
                            activity.initData(onGoing_tripBO);
                        }else{
                            showErrorNone(onGoing_tripBO, activity);
                        }
                    }
                }, getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean, activity);
                    }
                })));
    }
}
