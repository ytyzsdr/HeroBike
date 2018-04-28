package com.danchexia.bikehero.api.api_destribut;

import com.danchexia.bikehero.api.CommonController;
import com.danchexia.bikehero.api.PropertiesUtils;
import com.danchexia.bikehero.main.bean.AdvImages;
import com.danchexia.bikehero.main.bean.InvateAndShateBean;
import com.danchexia.bikehero.main.bean.SystemParamsBean;

import rx.Observable;
import rx.functions.Func1;
import vc.thinker.colours.client.api.AppparamcontrollerApi;
import vc.thinker.colours.client.model.SingleResponseOfImgBo;
import vc.thinker.colours.client.model.SingleResponseOfShareSetBO;
import vc.thinker.colours.client.model.SingleResponseOfSysSettingBO;

/**
 * Created by farley on 17/5/23.
 * description:
 */

public class AppParamController extends CommonController {
    private AppparamcontrollerApi appparamcontrollerApi;

    public AppParamController(AppparamcontrollerApi appparamcontrollerApi) {
        this.appparamcontrollerApi = appparamcontrollerApi;
    }

    /**
     * 获取系统配置
     *
     * @return
     */
    public Observable<SystemParamsBean> getSystemParams() {
        return appparamcontrollerApi.querySysSetUsingGET()
                .map(new Func1<SingleResponseOfSysSettingBO, SystemParamsBean>() {
                    @Override
                    public SystemParamsBean call(SingleResponseOfSysSettingBO singleResponseOfSysSettingBO) {
                        if (singleResponseOfSysSettingBO.getSuccess()) {
                            SystemParamsBean bean;
                            bean = PropertiesUtils.copyBeanProperties(singleResponseOfSysSettingBO.getItem(), SystemParamsBean.class);
                            return bean;
                        } else
                            return toErrorBean(singleResponseOfSysSettingBO.getError(), singleResponseOfSysSettingBO.getErrorDescription(), SystemParamsBean.class);
                    }
                });

    }

    /**
     * 获取邀请 和 分享的配置信息
     *
     * @return
     */
    public Observable<InvateAndShateBean> getInvateAndShareParams() {
        return appparamcontrollerApi.queryAppShareUsingGET()
                .map(new Func1<SingleResponseOfShareSetBO, InvateAndShateBean>() {
                    @Override
                    public InvateAndShateBean call(SingleResponseOfShareSetBO singleResponseOfShareSetBO) {
                        if (singleResponseOfShareSetBO.getSuccess()) {
                            return PropertiesUtils.copyBeanProperties(singleResponseOfShareSetBO.getItem(), InvateAndShateBean.class);
                        } else {
                            return toErrorBean(singleResponseOfShareSetBO.getError(), singleResponseOfShareSetBO.getErrorDescription(), InvateAndShateBean.class);
                        }
                    }
                });
    }

    /**
     * 获取广告图片
     *
     * @return
     */
    public Observable<AdvImages> getAdvImages() {
        return appparamcontrollerApi.queryInitImgUsingGET()
                .map(new Func1<SingleResponseOfImgBo, AdvImages>() {
                    @Override
                    public AdvImages call(SingleResponseOfImgBo singleResponseOfShareSetBO) {
                        if (singleResponseOfShareSetBO.getSuccess()) {
                            return PropertiesUtils.copyBeanProperties(singleResponseOfShareSetBO.getItem(), AdvImages.class);
                        } else {
                            return toErrorBean(singleResponseOfShareSetBO.getError(), singleResponseOfShareSetBO.getErrorDescription(), AdvImages.class);
                        }
                    }
                });
    }
}
