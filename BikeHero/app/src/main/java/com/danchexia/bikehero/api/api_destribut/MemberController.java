package com.danchexia.bikehero.api.api_destribut;

import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.CommonController;
import com.danchexia.bikehero.api.PropertiesUtils;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.MemberVipCardBean;
import com.danchexia.bikehero.main.bean.VipListBean;
import com.danchexia.bikehero.main.bean.VipListPayBean;
import com.danchexia.bikehero.main.bean.VipPayBean;
import com.danchexia.bikehero.main.bindphone.bean.AuthCredentials;
import com.danchexia.bikehero.main.feedback.bean.FeedBackUpLoadBean;
import com.danchexia.bikehero.main.feedback.bean.FeedbackTypeListBean;
import com.danchexia.bikehero.main.feedback.bean.FeedbackTypeListData;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.main.myoffer.bean.MyOfferBean;
import com.danchexia.bikehero.main.myoffer.bean.MyOfferData;
import com.danchexia.bikehero.main.recharge.bean.ChergeBean;
import com.danchexia.bikehero.main.set.bean.SetBean;
import com.danchexia.bikehero.main.set.bean.SetData;
import com.danchexia.bikehero.main.wallet.bean.WalletBean;
import com.danchexia.bikehero.main.wallet.bean.WalletItemData;
import com.danchexia.bikehero.pay.bean.PayDetails;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import javax.security.auth.login.LoginException;

import rx.Observable;
import rx.functions.Func1;
import vc.thinker.colours.client.ApiClient;
import vc.thinker.colours.client.api.MembercontrollerApi;
import vc.thinker.colours.client.model.FeedbackVO;
import vc.thinker.colours.client.model.ListResponseOfAPIMembershipCardBO;
import vc.thinker.colours.client.model.ListResponseOfFeedbackTypeBO;
import vc.thinker.colours.client.model.ListResponseOfUserGuideBO;
import vc.thinker.colours.client.model.PageResponseOfAPIVipPayLogBO;
import vc.thinker.colours.client.model.PageResponseOfUserCouponBO;
import vc.thinker.colours.client.model.PageResponseOfUserDepositLogBO;
import vc.thinker.colours.client.model.RealnameVO;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfMemberBO;
import vc.thinker.colours.client.model.SingleResponseOfPayDetailsBO;
import vc.thinker.colours.client.model.SingleResponseOfdouble;

/**
 * Created by farley on 17/8/17.
 * description:
 */

public class MemberController extends CommonController {
    private MembercontrollerApi membercontrollerApi;

    public MemberController(MembercontrollerApi membercontrollerApi) {
        this.membercontrollerApi = membercontrollerApi;
    }

    //获取验证码
    public Observable<BaseBean> getAuth(String phoneNum) {
        return membercontrollerApi.boundModileVerifycodeUsingGET(phoneNum)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return toBaseBean(simpleResponse);
                    }
                });
    }

    /**
     * 登录
     *
     * @param mobile
     * @param validCode
     * @return
     */

    public Observable<String> doLogin(String mobile, String validCode) {
        return Observable.just(new AuthCredentials(mobile, validCode)).flatMap(new Func1<AuthCredentials, Observable<String>>() {
            @Override
            public Observable<String> call(AuthCredentials credentials) {
                try {
                    OAuthClientRequest request = OAuthClientRequest
                            .tokenLocation(ApiClient.tokenUrl)
                            .setClientId(Config.appClientId)
                            .setClientSecret(Config.appClientSecret)
                            .setUsername(credentials.getUsername())
                            .setPassword(credentials.getPassword())
                            .setGrantType(GrantType.PASSWORD)
                            .buildBodyMessage();
                    request.addHeader("api-agent",ApiClient.userAgent);
                    OAuthClient client = new OAuthClient(new URLConnectionClient());

                    OAuthJSONAccessTokenResponse accessTokenResponse = client.accessToken(request);
                    client.shutdown();
                    return Observable.just(accessTokenResponse.getAccessToken());
                } catch (OAuthSystemException e) {
                    e.printStackTrace();
                } catch (OAuthProblemException e) {
                    e.printStackTrace();
                    return Observable.error(new LoginException(e.getDescription()));
                }

                return Observable.error(new LoginException("登录失败"));
            }
        });
    }

    /**
     * 获取押金金额
     *
     * @return
     */
    public Observable<ChergeBean> getCherge() {
        return membercontrollerApi.getDepositUsingGET()
                .map(new Func1<SingleResponseOfdouble, ChergeBean>() {
                    @Override
                    public ChergeBean call(SingleResponseOfdouble singleResponseOfdouble) {
                        if (singleResponseOfdouble.getSuccess()) {
                            ChergeBean bean = new ChergeBean(singleResponseOfdouble.getItem());
                            return bean;
                        } else {
                            return toErrorBean(singleResponseOfdouble.getError(), singleResponseOfdouble.getErrorDescription(), ChergeBean.class);
                        }

                    }
                });
    }

    /**
     * 充值押金
     *
     * @param payType
     * @return
     */
    public Observable<PayDetails> buyVip(Long cardId,String payType) {
        return membercontrollerApi.paymetVIPUsingPOST(cardId,payType)
                .map(new Func1<SingleResponseOfPayDetailsBO, PayDetails>() {
                    @Override
                    public PayDetails call(SingleResponseOfPayDetailsBO singleResponseOfWeiXinPaymetBO) {
                        return copyObjectByGson(singleResponseOfWeiXinPaymetBO.getItem(), PayDetails.class);
                    }
                });
    }
    /**
     * 充值押金
     *
     * @param payType
     * @return
     */
    public Observable<PayDetails> recharge(String payType) {
        return membercontrollerApi.paymetUsingPOST(payType)
                .map(new Func1<SingleResponseOfPayDetailsBO, PayDetails>() {
                    @Override
                    public PayDetails call(SingleResponseOfPayDetailsBO singleResponseOfWeiXinPaymetBO) {
                        return copyObjectByGson(singleResponseOfWeiXinPaymetBO.getItem(), PayDetails.class);
                    }
                });
    }

    /**
     * 获取个人信息
     *
     * @return
     */
    public Observable<PersonalBean> getPersonalData() {
        return membercontrollerApi.getMyProfileUsingGET()
                .map(new Func1<SingleResponseOfMemberBO, PersonalBean>() {
                    @Override
                    public PersonalBean call(SingleResponseOfMemberBO singleResponseOfMemberBO) {
                        if (singleResponseOfMemberBO.getSuccess()) {
                            PersonalBean bean ;
                            bean = PropertiesUtils.copyBeanProperties(singleResponseOfMemberBO.getItem(), PersonalBean.class);
                            return bean;
                        }
                        else
                            return toErrorBean(singleResponseOfMemberBO.getError(), singleResponseOfMemberBO.getErrorDescription(), PersonalBean.class);
                    }
                });
    }
    /**
     * 获取邀请好友之后获得的金额
     *
     * @return
     */
    public Observable<BaseBean> getInvateMoney() {
        return membercontrollerApi.queryInviteAmountUsingGET()
                .map(new Func1<SingleResponseOfdouble, BaseBean>() {
                    @Override
                    public BaseBean call(SingleResponseOfdouble singleResponseOfMemberBO) {
                        if (singleResponseOfMemberBO.getSuccess()){
                            return new BaseBean(singleResponseOfMemberBO.getItem().toString(),0);
                        }else{
                            return new BaseBean(singleResponseOfMemberBO.getErrorDescription(),-37);
                        }
                    }
                });
    }

    /**
     * 获取h5页面
     * @param type
     * @return
     */
    public Observable<SetBean> getGuideList(Integer type){
        return membercontrollerApi.userGuideListUsingGET(type)
                .map(new Func1<ListResponseOfUserGuideBO, SetBean>() {
                    @Override
                    public SetBean call(ListResponseOfUserGuideBO listResponseOfUserGuideBO) {
                        if (listResponseOfUserGuideBO.getSuccess()){
                            return new SetBean(PropertiesUtils.mappingApiToListBean(listResponseOfUserGuideBO.getItems(), SetData.class));
                        }else{
                            return toErrorBean(listResponseOfUserGuideBO.getError(),listResponseOfUserGuideBO.getErrorDescription(),SetBean.class);
                        }
                    }
                });
    }


    /**
     * 获取我的优惠券
     * @return
     */
    public Observable<MyOfferBean> getMyOffer(){
        return membercontrollerApi.findTripListUsingPOST()
                .map(new Func1<PageResponseOfUserCouponBO, MyOfferBean>() {
                    @Override
                    public MyOfferBean call(PageResponseOfUserCouponBO pageResponseOfUserCouponBO) {
                        if (pageResponseOfUserCouponBO.getSuccess()){
                            return new MyOfferBean(PropertiesUtils.mappingApiToListBean(pageResponseOfUserCouponBO.getContent(), MyOfferData.class));
                        }else{
                            return toErrorBean(pageResponseOfUserCouponBO.getError(),pageResponseOfUserCouponBO.getErrorDescription(),MyOfferBean.class);
                        }
                    }
                });
    }
    /**
     * 获取我的钱包
     * @return
     */
    public Observable<WalletBean> getMyWallet(Long time){
        return membercontrollerApi.depositLogUsingGET(time)
                .map(new Func1<PageResponseOfUserDepositLogBO, WalletBean>() {
                    @Override
                    public WalletBean call(PageResponseOfUserDepositLogBO pageResponseOfUserDepositLogBO) {
                        if (pageResponseOfUserDepositLogBO.getSuccess()){
                            return new WalletBean(PropertiesUtils.mappingApiToListBean(pageResponseOfUserDepositLogBO.getContent(), WalletItemData.class));
                        }else{
                            return toErrorBean(pageResponseOfUserDepositLogBO.getError(),pageResponseOfUserDepositLogBO.getErrorDescription(),WalletBean.class);
                        }
                    }
                });
    }
    /**
     * 认证
     * @return
     */
    public Observable<PersonalBean> ident(RealnameVO var1){
        return membercontrollerApi.realnameUsingPOST(var1)
                .map(new Func1<SingleResponseOfMemberBO, PersonalBean>() {
                    @Override
                    public PersonalBean call(SingleResponseOfMemberBO simpleResponse) {
                        if (simpleResponse.getSuccess()){
                            return PropertiesUtils.copyBeanProperties(simpleResponse.getItem(),PersonalBean.class);
                        }else{
                            return toErrorBean(simpleResponse.getError(),simpleResponse.getErrorDescription(),PersonalBean.class);
                        }
                    }
                });
    }
    /**
     * 填写邀请码
     * @return
     */
    public Observable<BaseBean> boundInavate(String code){
        return membercontrollerApi.boundInviteCodeUsingPOST(code)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return  toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 退押金
     * @return
     */
    public Observable<BaseBean> repounDisposit(){
        return membercontrollerApi.refundDepositUsingPOST()
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return  toBaseBean(simpleResponse);
                    }
                });
    }

    /**
     * 获取问题反馈问题列表
     * @param type 反馈类型分类，1 一般问题 2 行程中 3 已完成用户
     * @return
     */
    public Observable<FeedbackTypeListBean> getFeedBackTypeList(String type){
        return membercontrollerApi.findFeedbackTypeListUsingGET(type)
                .map(new Func1<ListResponseOfFeedbackTypeBO, FeedbackTypeListBean>() {
                    @Override
                    public FeedbackTypeListBean call(ListResponseOfFeedbackTypeBO listResponseOfFeedbackTypeBO) {
                        if (listResponseOfFeedbackTypeBO.getSuccess()){
                            return new FeedbackTypeListBean(PropertiesUtils.copyBeanListProperties(listResponseOfFeedbackTypeBO.getItems(), FeedbackTypeListData.class));
                        }else{
                            return toErrorBean(listResponseOfFeedbackTypeBO.getError(),listResponseOfFeedbackTypeBO.getErrorDescription(),FeedbackTypeListBean.class);
                        }
                    }
                });
    }
    /**
     * 问题反馈
     * @return
     */
    public Observable<BaseBean> feedback(FeedBackUpLoadBean bean){
        FeedbackVO feedbackVO = new FeedbackVO();
        feedbackVO.setFeedDesc(bean.getFeedDesc());
        feedbackVO.setImgUrl1(bean.getImgUrl1());
        feedbackVO.setImgUrl2(bean.getImgUrl2());
        feedbackVO.setImgUrl3(bean.getImgUrl3());
        feedbackVO.setImgUrl4(bean.getImgUrl4());
        feedbackVO.setSysCode(bean.getSysCode());
        feedbackVO.setTripId(bean.getTripId());
        feedbackVO.setTypeId(bean.getTypeId());
        feedbackVO.setBluetoothConnection(bean.getBluetoothConnection());
        feedbackVO.setLockOnoff(bean.getLockOnoff());
        return membercontrollerApi.feedbackUsingPOST(feedbackVO)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return  toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 修改头像
     * @return
     */
    public Observable<BaseBean> saveUserHead(String imgUrl){
        return membercontrollerApi.modifyUserHeadUsingGET(imgUrl)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return  toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 修改功率
     * @return
     */
    public Observable<BaseBean> fixPower(Integer power){
        return membercontrollerApi.modifyMotorPowerUsingGET(power)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return  toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 修改昵称
     * @return
     */
    public Observable<BaseBean> saveUserNickname(String name){
        return membercontrollerApi.modifyUserNicknameUsingGET(name)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return  toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 获取会员列表
     * @return
     */
    public Observable<VipListBean> getVipList(){
        return membercontrollerApi.findMembershipCardListUsingGET()
                .map(new Func1<ListResponseOfAPIMembershipCardBO, VipListBean>() {
                    @Override
                    public VipListBean call(ListResponseOfAPIMembershipCardBO listResponseOfAPIMembershipCardBO) {
                        if (listResponseOfAPIMembershipCardBO.getSuccess()){
                            return new VipListBean(PropertiesUtils.copyBeanListProperties(listResponseOfAPIMembershipCardBO.getItems(), MemberVipCardBean.class));
                        }else{
                            return toErrorBean(listResponseOfAPIMembershipCardBO.getError(),listResponseOfAPIMembershipCardBO.getErrorDescription(),VipListBean.class);
                        }
                    }
                });
    }
    /**
     * 获取会员明细
     * @return
     */
    public Observable<VipListPayBean> getVipListM(Long lgtime){
        return membercontrollerApi.findVipPayListUsingGET(lgtime)
                .map(new Func1<PageResponseOfAPIVipPayLogBO, VipListPayBean>() {
                    @Override
                    public VipListPayBean call(PageResponseOfAPIVipPayLogBO listResponseOfAPIMembershipCardBO) {
                        if (listResponseOfAPIMembershipCardBO.getSuccess()){
                            return new VipListPayBean(PropertiesUtils.copyBeanListProperties(listResponseOfAPIMembershipCardBO.getContent(), VipPayBean.class));
                        }else{
                            return toErrorBean(listResponseOfAPIMembershipCardBO.getError(),listResponseOfAPIMembershipCardBO.getErrorDescription(),VipListPayBean.class);
                        }
                    }
                });
    }
}

