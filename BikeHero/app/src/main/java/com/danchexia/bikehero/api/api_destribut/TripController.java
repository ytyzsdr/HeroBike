package com.danchexia.bikehero.api.api_destribut;

import com.danchexia.bikehero.main.bean.BicycleData;
import com.danchexia.bikehero.main.bean.BlueToothBean;
import com.danchexia.bikehero.pay.bean.PayDetails;
import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.CommonController;
import com.danchexia.bikehero.api.PropertiesUtils;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.main.bean.OnGoing_ReserveBO;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.main.bean.TripCyclingPointDataM;
import com.danchexia.bikehero.main.mystroke.bean.ItemStrokeBean;
import com.danchexia.bikehero.service.bean.UpPointData;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import vc.thinker.colours.client.api.TripcontrollerApi;
import vc.thinker.colours.client.model.PageResponseOfTripBO;
import vc.thinker.colours.client.model.Point;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfAPIShareObject;
import vc.thinker.colours.client.model.SingleResponseOfBicycleBO;
import vc.thinker.colours.client.model.SingleResponseOfOngoingInfoBO;
import vc.thinker.colours.client.model.SingleResponseOfPayDetailsBO;
import vc.thinker.colours.client.model.SingleResponseOfTripBO;
import vc.thinker.colours.client.model.TripPointBo;
import vc.thinker.colours.client.model.TripPointVO;

/**
 * Created by farley on 17/5/23.
 * description:
 */

public class TripController extends CommonController {
    private TripcontrollerApi tripcontrollerApi;

    public TripController(TripcontrollerApi tripcontrollerApi) {
        this.tripcontrollerApi = tripcontrollerApi;
    }

    /**
     * 开锁
     * @param code
     * @param latitude
     * @param longtitude
     * @return
     */
    public Observable<BlueToothBean> openLock(String code, Double latitude, Double longtitude) {
        return tripcontrollerApi.unlockUsingPOST(code,longtitude,latitude,null)
                .map(new Func1<SingleResponseOfAPIShareObject, BlueToothBean>() {
                    @Override
                    public BlueToothBean call(SingleResponseOfAPIShareObject simpleResponse) {
                        if (simpleResponse.getSuccess()) {
                            return PropertiesUtils.copyBeanProperties(simpleResponse.getItem(),BlueToothBean.class);
                        }
                        else {
                            return toErrorBean(simpleResponse.getError(), simpleResponse.getErrorDescription(), BlueToothBean.class);
                        }
                    }
                });
    }
    /**
     * 开锁 -------------->蓝牙
     * @param code
     * @return
     */
    public Observable<BaseBean> blueToothSuccess(String code,Boolean showPaw) {
        return tripcontrollerApi.confirmUnlockUsingPOST(code,showPaw)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 预约
     * @param code
     * @return
     */
    public Observable<BaseBean> reserve(String code) {
        return tripcontrollerApi.reserveUsingPOST(code)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 用户当前的状态
     * @return
     */
    public Observable<OnGoingInfoBean> onGoingInfo() {
        return tripcontrollerApi.findOngoingInfoUsingGET("GCJ02")
                .map(new Func1<SingleResponseOfOngoingInfoBO, OnGoingInfoBean>() {
                    @Override
                    public OnGoingInfoBean call(SingleResponseOfOngoingInfoBO singleResponseOfOngoingInfoBO) {
                        if (singleResponseOfOngoingInfoBO.getSuccess()) {
                            OnGoing_ReserveBO beanReserveBO = PropertiesUtils.copyBeanProperties(singleResponseOfOngoingInfoBO.getItem().getReserve(), OnGoing_ReserveBO.class);
                            OnGoing_TripBO beanTripBO = PropertiesUtils.copyBeanProperties(singleResponseOfOngoingInfoBO.getItem().getTrip(), OnGoing_TripBO.class);
                            return new OnGoingInfoBean(beanReserveBO,beanTripBO);
                        }
                        else
                            return toErrorBean(singleResponseOfOngoingInfoBO.getError(), singleResponseOfOngoingInfoBO.getErrorDescription(), OnGoingInfoBean.class);
                    }
                });
    }

    /**
     * 寻车龄
     * @return
     */
    public Observable<BaseBean> ring() {
        return tripcontrollerApi.beepUsingPOST()
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 取消预约
     * @return
     */
    public Observable<BaseBean> canselRecervation() {
        return tripcontrollerApi.cancelReserveUsingPOST()
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 骑行结束支付
     * @return
     */
    public Observable<PayDetails> ridePayNo(Long tid, String paymentMark) {
        return tripcontrollerApi.paymetUsingPOSTNo(tid,paymentMark)
                .map(new Func1<SingleResponseOfPayDetailsBO, PayDetails>() {
                    @Override
                    public PayDetails call(SingleResponseOfPayDetailsBO singleResponseOfPayDetailsBO) {
                        if (singleResponseOfPayDetailsBO.getSuccess()){
                            return PropertiesUtils.copyBeanProperties(singleResponseOfPayDetailsBO.getItem(),PayDetails.class);
                        }else{
                            return toErrorBean(singleResponseOfPayDetailsBO.getError(),singleResponseOfPayDetailsBO.getErrorDescription(),PayDetails.class);
                        }
                    }
                });
    }

    /**
     * 骑行结束支付
     * @return
     */
    public Observable<PayDetails> ridePay(Long tid, Long cid, String paymentMark) {
        return tripcontrollerApi.paymetUsingPOST(tid,cid,paymentMark)
                .map(new Func1<SingleResponseOfPayDetailsBO, PayDetails>() {
                    @Override
                    public PayDetails call(SingleResponseOfPayDetailsBO singleResponseOfPayDetailsBO) {
                        if (singleResponseOfPayDetailsBO.getSuccess()){
                            return PropertiesUtils.copyBeanProperties(singleResponseOfPayDetailsBO.getItem(),PayDetails.class);
                        }else{
                            return toErrorBean(singleResponseOfPayDetailsBO.getError(),singleResponseOfPayDetailsBO.getErrorDescription(),PayDetails.class);
                        }
                    }
                });
    }
    /**
     * 未支付的行程
     * @return
     */
    public Observable<OnGoing_TripBO> getNotPayTrip(){
        return tripcontrollerApi.findNotPayTripUsingGET().map(new Func1<SingleResponseOfTripBO, OnGoing_TripBO>() {
            @Override
            public OnGoing_TripBO call(SingleResponseOfTripBO singleResponseOfTripBO) {
                if (singleResponseOfTripBO.getSuccess()){
                    OnGoing_TripBO bean = null;
                    if (singleResponseOfTripBO.getItem() != null) {
                        bean = PropertiesUtils.copyBeanProperties(singleResponseOfTripBO.getItem(), OnGoing_TripBO.class);
                        return bean;
                    }else{
                        return bean;
                    }
                }else{
                    return toErrorBean(singleResponseOfTripBO.getError(), singleResponseOfTripBO.getErrorDescription(), OnGoing_TripBO.class);
                }
            }
        });
    }
    /**
     * 上报行程轨迹位置位置
     * @return
     */
    public Observable<BaseBean> upload_track_loc(List<UpPointData> list) {
        TripPointBo bo = new TripPointBo();
        bo.setClientType("2");
        List<TripPointVO> listParams = new ArrayList<>();
        for (int i = 0;i < list.size();i++){
            UpPointData data = list.get(i);
            TripPointVO vo = new TripPointVO();
            vo.setDateTime(data.getDateTime());
            Point mPoint = new Point();
            mPoint.setX(data.getmPoint().getX());
            mPoint.setY(data.getmPoint().getY());
            vo.setPoint(mPoint);
            listParams.add(vo);
        }

        bo.setPointList(listParams);
        return tripcontrollerApi.uploadTrackLocUsingPOST(bo)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 获取用户所有的行程数据
     * @return
     */
    public Observable<ItemStrokeBean> getMyAllStroke(Long time) {
        return tripcontrollerApi.findTripListUsingPOST(time)
                .map(new Func1<PageResponseOfTripBO, ItemStrokeBean>() {
                    @Override
                    public ItemStrokeBean call(PageResponseOfTripBO pageResponseOfTripBO) {
                        if (pageResponseOfTripBO.getSuccess()) {
                            List<OnGoing_TripBO> listBean = PropertiesUtils.copyBeanListProperties(pageResponseOfTripBO.getContent(), OnGoing_TripBO.class);
                            ItemStrokeBean itemStrokeBean = new ItemStrokeBean();
                            itemStrokeBean.setContent(listBean);
                            return itemStrokeBean;
                        }
                        else
                            return toErrorBean(pageResponseOfTripBO.getError(), pageResponseOfTripBO.getErrorDescription(), ItemStrokeBean.class);
                    }
                });
    }
    /**
     * 获取行程详情
     * @return
     */
    public Observable<OnGoing_TripBO> profileUsing(Long time) {
        return tripcontrollerApi.profileUsingGET(time,null)
                .map(new Func1<SingleResponseOfTripBO, OnGoing_TripBO>() {
                    @Override
                    public OnGoing_TripBO call(SingleResponseOfTripBO singleResponseOfTripBO) {
                        if (singleResponseOfTripBO.getSuccess()){
                            List<TripCyclingPointDataM> tripCyclingPointDataMs = PropertiesUtils.mappingApiToListBean(singleResponseOfTripBO.getItem().getCyclingPoints(),TripCyclingPointDataM.class);
                            OnGoing_TripBO bean = PropertiesUtils.copyBeanProperties(singleResponseOfTripBO.getItem(),OnGoing_TripBO.class);
                            bean.setCyclingPoints(tripCyclingPointDataMs);
                            return bean;
                        }else{
                            return toErrorBean(singleResponseOfTripBO.getError(), singleResponseOfTripBO.getErrorDescription(), OnGoing_TripBO.class);
                        }
                    }
                });
    }
    /**
     * 行程车重新定位锁
     * @return
     */
    public Observable<BaseBean> refreshLocation() {
        return tripcontrollerApi.locationUsingPOST()
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return toBaseBean(simpleResponse);
                    }
                });
    }
    /**
     * 行程中开锁
     * @return
     */
    public Observable<BicycleData> tripUnlock() {
        return tripcontrollerApi.tripUnlockUsingPOST()
                .map(new Func1<SingleResponseOfBicycleBO, BicycleData>() {
                    @Override
                    public BicycleData call(SingleResponseOfBicycleBO singleResponseOfTripBO) {
                        if (singleResponseOfTripBO.getSuccess()){
                            return PropertiesUtils.copyBeanProperties(singleResponseOfTripBO.getItem(),BicycleData.class);
                        }else{
                            return toErrorBean(singleResponseOfTripBO.getError(), singleResponseOfTripBO.getErrorDescription(), BicycleData.class);
                        }
                    }
                });
    }
    /**
     * 结束行车
     * @return
     */
    public Observable<OnGoing_TripBO> endTrip(Double lon,Double lat,String endType) {
        return tripcontrollerApi.endTripUsingPOST(lon,lat,endType,null)
                .map(new Func1<SingleResponseOfTripBO, OnGoing_TripBO>() {
                    @Override
                    public OnGoing_TripBO call(SingleResponseOfTripBO singleResponseOfTripBO) {
                       if (singleResponseOfTripBO.getSuccess()){
                           return PropertiesUtils.copyBeanProperties(singleResponseOfTripBO.getItem(),OnGoing_TripBO.class);
                       }else{
                           return toErrorBean(singleResponseOfTripBO.getError(),singleResponseOfTripBO.getErrorDescription(),OnGoing_TripBO.class);
                       }
                    }
                });
    }
}
