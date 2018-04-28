package com.danchexia.bikehero.api.api_destribut;

import com.danchexia.bikehero.api.CommonController;
import com.danchexia.bikehero.api.PropertiesUtils;
import com.danchexia.bikehero.main.bean.BatteryBean;
import com.danchexia.bikehero.main.bean.BatteryListBean;

import rx.Observable;
import rx.functions.Func1;
import vc.thinker.colours.client.api.BatterycontrollerApi;
import vc.thinker.colours.client.model.ListResponseOfAPIBatteryBO;

/**
 * Created by farley on 17/8/26.
 * description:
 */

public class BatteryController extends CommonController {
    private BatterycontrollerApi batterycontrollerApi;

    public BatteryController(BatterycontrollerApi batterycontrollerApi) {
        this.batterycontrollerApi = batterycontrollerApi;
    }
    //根据位置查询附近的所有电池
    public Observable<BatteryListBean> getAllbattery(Double lon, Double lat, int distance){
        return batterycontrollerApi.findByLocationAndDistanceUsingGET(lon,lat,distance,null)
                .map(new Func1<ListResponseOfAPIBatteryBO, BatteryListBean>() {
                    @Override
                    public BatteryListBean call(ListResponseOfAPIBatteryBO listResponseOfAPIBatteryBO) {
                        if (listResponseOfAPIBatteryBO.getSuccess()){
                            return new BatteryListBean( PropertiesUtils.copyBeanListProperties(listResponseOfAPIBatteryBO.getItems(), BatteryBean.class));
                        }else{
                            return toErrorBean(listResponseOfAPIBatteryBO.getError(),listResponseOfAPIBatteryBO.getErrorDescription(),BatteryListBean.class);
                        }
                    }
                });
    }
}
