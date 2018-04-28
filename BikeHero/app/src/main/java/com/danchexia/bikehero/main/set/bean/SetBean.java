package com.danchexia.bikehero.main.set.bean;

import com.danchexia.bikehero.api.BaseBean;

import java.util.List;

/**
 * Created by farley on 17/5/24.
 * description:
 */

public class SetBean extends BaseBean {
    List<SetData> datas;

    public SetBean(List<SetData> datas) {
        this.datas = datas;
    }

    public List<SetData> getDatas() {
        return datas;
    }

    public void setDatas(List<SetData> datas) {
        this.datas = datas;
    }
}
