package com.danchexia.bikehero.elebike;

import com.baidu.mapapi.model.LatLng;
import com.danchexia.bikehero.api.BasePresenter;

/**
 * Created by farley on 17/9/25.
 * description:
 */

public class EleMainPresenter extends BasePresenter<EleMainView> {
    private EleMainActivity activity;

    public EleMainPresenter(EleMainActivity activity) {
        this.activity = activity;
    }
    public void getAllEleBike(LatLng mCenterLatLng){
        activity.setAllMarkers();
    }
}
