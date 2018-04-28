package com.danchexia.bikehero.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import vc.thinker.tools.utils.LogUtils;

/**
 * Created by farley on 17/8/30.
 * description:
 */

public class BeaconService extends Service implements BeaconConsumer, RangeNotifier {

    private static final long DEFAULT_BACKGROUND_SCAN_PERIOD = 1000L;
    private static final long DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD = 1000L;

    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);

    public BeaconService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initBeacon();
        beaconManager.bind(this);
    }

    private void initBeacon() {
        beaconManager.setBackgroundScanPeriod(DEFAULT_BACKGROUND_SCAN_PERIOD);
        beaconManager.setBackgroundBetweenScanPeriod(DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (beaconManager != null)
            beaconManager.removeRangeNotifier(this);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onBeaconServiceConnect() {
        Region region = new Region("myRangingUniqueId", null, null, null);
        beaconManager.addRangeNotifier(this);
        try {
            beaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> collections, Region region) {
        List<Beacon> beacons = new ArrayList<>();
        for (Beacon beacon : collections) {
            beacons.add(beacon);
            LogUtils.w("beacon===="+beacon.toString());
        }
//        Intent intent = new Intent(MainActivity.BEACON_ACTION);
//        intent.putParcelableArrayListExtra("beacon", (ArrayList<? extends Parcelable>) beacons);//因为Beacon继承了Parcelable,
//        sendBroadcast(intent);                                                                   // 所以能通过这个方式来传递数据
    }
}
