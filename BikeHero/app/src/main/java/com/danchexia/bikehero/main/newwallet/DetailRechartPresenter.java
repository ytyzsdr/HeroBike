package com.danchexia.bikehero.main.newwallet;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.api.api_destribut.MoneyController;
import com.danchexia.bikehero.main.bean.RechartHistoryListBean;
import com.danchexia.bikehero.main.wallet.bean.WalletBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.mvp.MvpView;

/**
 * Created by farley on 17/8/21.
 * description:
 */

public class DetailRechartPresenter extends BasePresenter<MvpView> {
    private DetailRechartActivity activity;
    MemberController userController = APIControllerFactory.getMemberApi();
    MoneyController moneyController = APIControllerFactory.getMoneyController();
    public DetailRechartPresenter(DetailRechartActivity activity) {
        this.activity = activity;
    }

    public void getYueList(final Long time){
        addSubscription(
                moneyController.getRechartHistoryList(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RechartHistoryListBean>() {
                    @Override
                    public void call(RechartHistoryListBean rechartHistoryListBean) {
                        if (rechartHistoryListBean.getError_code() == 0){
                            activity.loadMoreAmountDetail(rechartHistoryListBean,time);
                        }
                    }
                },getErrorAction(new OnHttpListener() {
                    @Override
                    public void onResult(BaseBean bean) {
                        showErrorNone(bean,activity);
                    }
                }))
        );
    }
    /**
     * 获取我的钱包记录
     * @param time
     */
    public void getMyWallet(final Long time){
        addSubscription(userController.getMyWallet(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WalletBean>() {
                    @Override
                    public void call(WalletBean walletBean) {
                        if (walletBean.getError_code() == 0){
                            activity.loadMore(walletBean,time);
                        }else{
                            showErrorNone(walletBean,activity);
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
