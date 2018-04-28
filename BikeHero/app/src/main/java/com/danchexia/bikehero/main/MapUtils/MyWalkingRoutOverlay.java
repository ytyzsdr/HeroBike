package com.danchexia.bikehero.main.MapUtils;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.danchexia.bikehero.R;

/**
 * Created by farley on 17/5/26.
 * description:
 */

public class MyWalkingRoutOverlay extends WalkingRoutOverlay {
    public MyWalkingRoutOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }
    @Override
    public BitmapDescriptor getStartMarker() {
        return BitmapDescriptorFactory.fromResource(R.drawable.needle);
    }

    @Override
    public BitmapDescriptor getTerminalMarker() {
        return null;
    }
}
