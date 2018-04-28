package com.danchexia.bikehero.batterymain.triplist;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.TripController;
import com.danchexia.bikehero.main.mystroke.bean.ItemStrokeBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by farley on 17/9/6.
 * description:
 */

public class MyTripListPresenter  extends BasePresenter<IMyTripListView> {
    private MyTripLIstActivity activity;
    TripController tripController = APIControllerFactory.getAllStroke();
    public MyTripListPresenter(MyTripLIstActivity myStrokeActivity) {
        super();
        activity = myStrokeActivity;
    }
    //获取我所有的行程
    public void getMyAllStroke(Long time){
        activity.showLoading();
        addSubscription(tripController.getMyAllStroke(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ItemStrokeBean>() {
                    @Override
                    public void call(ItemStrokeBean itemStrokeBean) {
                        activity.hideLoading();
                        if (itemStrokeBean.getError_code() == 0){
                            activity.loadMore(itemStrokeBean);
                        }else{
                            showErrorLogin(itemStrokeBean,activity);
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