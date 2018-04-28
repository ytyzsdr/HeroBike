package com.danchexia.bikehero.main.MapUtils;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;

/**
 * Created by farley on 17/3/21.
 * description:
 */

public class MyDriveRoutOverlay extends DriveRoutOverlay {
    public MyDriveRoutOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }
    @Override
    public BitmapDescriptor getStartMarker() {
//        return BitmapDescriptorFactory.fromResource(R.drawable.needle_start);
        return null;
    }

    @Override
    public BitmapDescriptor getTerminalMarker() {
        return null;
    }
}
