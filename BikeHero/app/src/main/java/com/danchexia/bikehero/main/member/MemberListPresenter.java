package com.danchexia.bikehero.main.member;

import com.danchexia.bikehero.api.APIControllerFactory;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.BasePresenter;
import com.danchexia.bikehero.api.OnHttpListener;
import com.danchexia.bikehero.api.api_destribut.MemberController;
import com.danchexia.bikehero.main.bean.VipListPayBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vc.thinker.mvp.MvpView;

/**
 * Created by farley on 17/8/18.
 * description:
 */

public class MemberListPresenter extends BasePresenter<MvpView> {
    private MemberListActivity activity;
    private MemberController memberController = APIControllerFactory.getMemberApi();
    public MemberListPresenter(MemberListActivity activity) {
        this.activity = activity;
    }
    public void getVipList(Long lgtime){
        addSubscription(memberController.getVipListM(lgtime)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<VipListPayBean>() {
            @Override
            public void call(VipListPayBean vipListBean) {
                if (vipListBean.getError_code() == 0){
                    activity.setListData(vipListBean);
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
