package com.danchexia.bikehero.api.api_destribut;


import com.danchexia.bikehero.api.CommonController;
import com.danchexia.bikehero.api.PropertiesUtils;
import com.danchexia.bikehero.main.bean.MessageBean;
import com.danchexia.bikehero.main.bean.MessageData;
import com.danchexia.bikehero.main.bean.MessageHomeBean;

import rx.Observable;
import rx.functions.Func1;
import vc.thinker.colours.client.api.MessagecontrollerApi;
import vc.thinker.colours.client.model.PageResponseOfAPIMessageBO;
import vc.thinker.colours.client.model.SingleResponseOfAPIMessageBO;

/**
 * Created by farley on 17/7/10.
 * description:
 */

public class MessageCenterController extends CommonController {
    private MessagecontrollerApi MessageApi;
    public MessageCenterController(MessagecontrollerApi MessageApi) {
        this.MessageApi = MessageApi;
    }
    //拉取系统消息
    public Observable<MessageBean> findMessage(Long lTime) {
        return MessageApi.sysListUsingGET("2",lTime)
                .map(new Func1<PageResponseOfAPIMessageBO, MessageBean>() {
                    @Override
                    public MessageBean call(PageResponseOfAPIMessageBO singleResponseOfSysSettingBO) {
                        if (singleResponseOfSysSettingBO.getSuccess()) {
                            MessageBean bean = new MessageBean();
                            bean.setContent(PropertiesUtils.copyBeanListProperties(singleResponseOfSysSettingBO.getContent(), MessageData.class));
                            return bean;
                        } else
                            return toErrorBean(singleResponseOfSysSettingBO.getError(), singleResponseOfSysSettingBO.getErrorDescription(), MessageBean.class);
                    }
                });
    }
    //拉取首页广告信息
    public Observable<MessageHomeBean> findHomeMessage(Long lTime) {
        return MessageApi.findHomeMessageUsingGET(lTime)
                .map(new Func1<SingleResponseOfAPIMessageBO, MessageHomeBean>() {
                    @Override
                    public MessageHomeBean call(SingleResponseOfAPIMessageBO singleResponseOfSysSettingBO) {
                        if (singleResponseOfSysSettingBO.getSuccess()) {
                            return  PropertiesUtils.copyBeanProperties(singleResponseOfSysSettingBO.getItem(), MessageHomeBean.class);
                        } else
                            return toErrorBean(singleResponseOfSysSettingBO.getError(), singleResponseOfSysSettingBO.getErrorDescription(), MessageHomeBean.class);
                    }
                });
    }
}
