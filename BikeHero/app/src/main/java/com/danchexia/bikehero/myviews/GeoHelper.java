package com.danchexia.bikehero.myviews;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.List;

/**
 * Created by huan on 2016/3/3.
 */
public class GeoHelper implements OnGetGeoCoderResultListener {
    private OnReverseGeoListener mOnReverseGeoListener;

    private GeoCoder mSearch;

    public interface OnReverseGeoListener {
        void onGetResult(double longitude, double latitude, String poi, String district, String address);
    }

    public GeoHelper() {
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
    }

    public void setOnReverseGeoListener(OnReverseGeoListener listener) {
        mOnReverseGeoListener = listener;
    }

    public void reverseGeo(double latitude, double longitude) {
        reverseGeo(latitude, longitude, mOnReverseGeoListener);
    }

    public void reverseGeo(double latitude, double longitude, OnReverseGeoListener listener) {
        setOnReverseGeoListener(listener);

        LatLng ptCenter = new LatLng(latitude, longitude);
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter));
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if(mOnReverseGeoListener != null) {
            String poi = "";

            List<PoiInfo> poiInfoList = reverseGeoCodeResult.getPoiList();
            if(poiInfoList != null && poiInfoList.size() > 0) {
                poi = poiInfoList.get(0).name;
            }

            ReverseGeoCodeResult.AddressComponent addressComponent = reverseGeoCodeResult.getAddressDetail();
            LatLng latLng = reverseGeoCodeResult.getLocation();
            if(latLng != null && addressComponent != null) {
                mOnReverseGeoListener.onGetResult(latLng.longitude, latLng.latitude, poi, addressComponent.district, reverseGeoCodeResult.getAddress());
            }
        }
    }
}
