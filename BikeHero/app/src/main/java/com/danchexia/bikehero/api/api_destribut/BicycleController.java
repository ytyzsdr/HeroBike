package com.danchexia.bikehero.api.api_destribut;

import com.danchexia.bikehero.api.BaseBean;
import com.danchexia.bikehero.api.CommonController;
import com.danchexia.bikehero.api.PropertiesUtils;
import com.danchexia.bikehero.main.bean.BicycleBean;
import com.danchexia.bikehero.main.bean.BicycleData;
import com.danchexia.bikehero.main.bean.NearByPark;
import com.danchexia.bikehero.main.bean.ParkDetailBean;
import com.danchexia.bikehero.main.bean.ParkListBean;

import rx.Observable;
import rx.functions.Func1;
import vc.thinker.colours.client.api.BicyclecontrollerApi;
import vc.thinker.colours.client.model.BicycleStatusVO;
import vc.thinker.colours.client.model.ListResponseOfAPISpotParkingBO;
import vc.thinker.colours.client.model.ListResponseOfBicycleBO;
import vc.thinker.colours.client.model.SimpleResponse;
import vc.thinker.colours.client.model.SingleResponseOfAPISpotParkingBO;

/**
 * Created by farley on 17/5/16.
 * description:
 */

public class BicycleController extends CommonController {
    private BicyclecontrollerApi bicyclecontrollerApi;

    public BicycleController(BicyclecontrollerApi bicyclecontrollerApi) {
        this.bicyclecontrollerApi = bicyclecontrollerApi;
    }
    public Observable<BicycleBean> getAllBike(Double var1, Double var2, Integer itr)
    {
        return bicyclecontrollerApi.findByLocationAndDistanceUsingGET(var2,var1,itr,null)
                .map(new Func1<ListResponseOfBicycleBO, BicycleBean>() {
            @Override
            public BicycleBean call(ListResponseOfBicycleBO listResponseOfBicycleBO) {
                if (listResponseOfBicycleBO.getSuccess()){
                    if (PropertiesUtils.copyBeanListProperties(listResponseOfBicycleBO.getItems(), BicycleData.class) != null) {
                        return new BicycleBean(PropertiesUtils.copyBeanListProperties(listResponseOfBicycleBO.getItems(), BicycleData.class));
                    }else {
                        return new BicycleBean();
                    }
                }else {
                    return toErrorBean(listResponseOfBicycleBO.getError(), listResponseOfBicycleBO.getErrorDescription(), BicycleBean.class);
                }
            }
        });
    }
    public Observable<ParkListBean> getParkList(Double lon, Double lat, Integer distance)
    {
        return bicyclecontrollerApi.findSpotParkingUsingGET(lon,lat,distance,null)
                .map(new Func1<ListResponseOfAPISpotParkingBO, ParkListBean>() {
                    @Override
                    public ParkListBean call(ListResponseOfAPISpotParkingBO listResponseOfBicycleBO) {
                        if (listResponseOfBicycleBO.getSuccess())
                        {
                            ParkListBean b = new ParkListBean(PropertiesUtils.copyBeanListProperties(listResponseOfBicycleBO.getItems(), ParkDetailBean.class));
                            return  b;
                        }
                        else
                            return toErrorBean(listResponseOfBicycleBO.getError(), listResponseOfBicycleBO.getErrorDescription(), ParkListBean.class);
                    }
                });
    }
    public Observable<NearByPark> getLatePark(Double lon, Double lat)
    {
        return bicyclecontrollerApi.findLatelySpotParkingUsingGET(lon,lat,null)
                .map(new Func1<SingleResponseOfAPISpotParkingBO, NearByPark>() {
                    @Override
                    public NearByPark call(SingleResponseOfAPISpotParkingBO listResponseOfBicycleBO) {
                        if (listResponseOfBicycleBO.getSuccess())
                            return PropertiesUtils.copyBeanProperties(listResponseOfBicycleBO.getItem(), NearByPark.class);
                        else
                            return toErrorBean(listResponseOfBicycleBO.getError(), listResponseOfBicycleBO.getErrorDescription(), NearByPark.class);
                    }
                });
    }
    public Observable<BaseBean> uploadLockStatus(BicycleStatusVO vo) {
        return bicyclecontrollerApi.uploadStatusUsingPOST(vo)
                .map(new Func1<SimpleResponse, BaseBean>() {
                    @Override
                    public BaseBean call(SimpleResponse simpleResponse) {
                        return toBaseBean(simpleResponse);
                    }
                });
    }
}
