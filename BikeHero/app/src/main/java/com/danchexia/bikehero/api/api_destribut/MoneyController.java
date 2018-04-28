package com.danchexia.bikehero.api.api_destribut;

import com.danchexia.bikehero.api.CommonController;
import com.danchexia.bikehero.api.PropertiesUtils;
import com.danchexia.bikehero.main.bean.PayDetailBean;
import com.danchexia.bikehero.main.bean.RechartHistoryBean;
import com.danchexia.bikehero.main.bean.RechartHistoryListBean;
import com.danchexia.bikehero.main.bean.RechartTypeBean;
import com.danchexia.bikehero.main.bean.RechartTypeListBean;
import com.danchexia.bikehero.main.bean.UserAmount;

import rx.Observable;
import rx.functions.Func1;
import vc.thinker.colours.client.api.MoneycontrollerApi;
import vc.thinker.colours.client.model.ListResponseOfAPIPayAmountBO;
import vc.thinker.colours.client.model.PageResponseOfAPIUserMoneyLogBO;
import vc.thinker.colours.client.model.SingleResponseOfPayDetailsBO;
import vc.thinker.colours.client.model.SingleResponseOfdouble;

/**
 * Created by farley on 17/8/21.
 * description:
 */

public class MoneyController extends CommonController {
    private MoneycontrollerApi moneycontrollerApi;

    public MoneyController(MoneycontrollerApi moneycontrollerApi) {
        this.moneycontrollerApi = moneycontrollerApi;
    }
    //获取余额
    public Observable<UserAmount> getMoney() {
        return moneycontrollerApi.getMyBalanceUsingGET()
                .map(new Func1<SingleResponseOfdouble, UserAmount>() {
                    @Override
                    public UserAmount call(SingleResponseOfdouble simpleResponse) {
                        if (simpleResponse.getSuccess()){
                            return new UserAmount(simpleResponse.getItem());
                        }else{
                            return toErrorBean(simpleResponse.getError(),simpleResponse.getErrorDescription(),UserAmount.class);
                        }
                    }
                });
    }
    //充值
    public Observable<PayDetailBean> getRechartResult(Long money, String type) {
        return moneycontrollerApi.paymetBalanceUsingPOST(money,type)
                .map(new Func1<SingleResponseOfPayDetailsBO, PayDetailBean>() {
                    @Override
                    public PayDetailBean call(SingleResponseOfPayDetailsBO simpleResponse) {
                        if (simpleResponse.getSuccess()){
                            return PropertiesUtils.copyBeanProperties(simpleResponse.getItem(),PayDetailBean.class);
                        }else{
                            return toErrorBean(simpleResponse.getError(),simpleResponse.getErrorDescription(),PayDetailBean.class);
                        }
                    }
                });
    }
    //获取充值金额类型列表
    public Observable<RechartTypeListBean> getRechartList() {
        return moneycontrollerApi.findPayAmountListUsingGET()
                .map(new Func1<ListResponseOfAPIPayAmountBO, RechartTypeListBean>() {
                    @Override
                    public RechartTypeListBean call(ListResponseOfAPIPayAmountBO simpleResponse) {
                        if (simpleResponse.getSuccess()){
                            return new RechartTypeListBean(PropertiesUtils.copyBeanListProperties(simpleResponse.getItems(),RechartTypeBean.class));
                        }else{
                            return toErrorBean(simpleResponse.getError(),simpleResponse.getErrorDescription(),RechartTypeListBean.class);
                        }
                    }
                });
    }
    //GET /api/money/money_log_list 获取用户余额日志列表
    public Observable<RechartHistoryListBean> getRechartHistoryList(Long lTime) {
        return moneycontrollerApi.findMoneyLogListUsingGET(lTime)
                .map(new Func1<PageResponseOfAPIUserMoneyLogBO, RechartHistoryListBean>() {
                    @Override
                    public RechartHistoryListBean call(PageResponseOfAPIUserMoneyLogBO simpleResponse) {
                        if (simpleResponse.getSuccess()){
                            return new RechartHistoryListBean(PropertiesUtils.copyBeanListProperties(simpleResponse.getContent(),RechartHistoryBean.class));
                        }else{
                            return toErrorBean(simpleResponse.getError(),simpleResponse.getErrorDescription(),RechartHistoryListBean.class);
                        }
                    }
                });
    }
}
