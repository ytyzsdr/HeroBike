package com.danchexia.bikehero.main.toplayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.main.IMainView;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;

import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.MyHandler;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/8/11.
 * description:
 */

public class FragmentParkLocation extends Fragment {
    private View view;

    @SuppressLint("ValidFragment")
    public FragmentParkLocation() {
    }

    @SuppressLint("ValidFragment")
    public FragmentParkLocation(OnGoing_TripBO bo) {
        this.bo = bo;
    }

    private OnGoing_TripBO bo;
    private TextView money;
    private TextView code;
    private TextView time;
    private TextView unLock;
    private TextView refreshText;
    private LinearLayout text_desc;
    private LinearLayout refreshLocation;
    private View viewMy;
    public static boolean isFrount = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_park_location,
                container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        isFrount = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isFrount = false;
    }

    boolean isInIt;

    private void initData() {
        money.setText(Utils.object2String(bo.getPrice()) + "元");
        code.setText(Utils.object2String(bo.getSysCode()));
        time.setText(Utils.stampToDate3(System.currentTimeMillis()));
        isInIt = PreferencesUtils.getBoolean(getActivity(), "INPARKLOCATION_BLUE", true);
        boolean isInParkForBluePark = PreferencesUtils.getBoolean(MyApplication.appContext,"isInParkForBluePark",true);//用蓝牙桩定位 是否在区域内
        if (bo.getInTheParkingLot() != null && !bo.getInTheParkingLot() || !isInIt || !isInParkForBluePark) {
            text_desc.setVisibility(View.VISIBLE);
            viewMy.setVisibility(View.VISIBLE);
            refreshText.setText("刷新位置");
        } else {
            text_desc.setVisibility(View.GONE);
            viewMy.setVisibility(View.GONE);
            refreshText.setText("结束用车");
        }


    }

    public void setMyData(OnGoing_TripBO boMyBean) {
        LogUtils.d("重新复制");
        money.setText(Utils.object2String(boMyBean.getPrice()) + "元");
        code.setText(Utils.object2String(boMyBean.getSysCode()));
        time.setText(Utils.stampToDate3(boMyBean.getLastLockLocationTime().getTime()));
        if (!boMyBean.getInTheParkingLot()) {
            text_desc.setVisibility(View.VISIBLE);
            viewMy.setVisibility(View.VISIBLE);
            refreshText.setText("刷新位置");
        } else {
            text_desc.setVisibility(View.GONE);
            viewMy.setVisibility(View.GONE);
        }
        if (myHandler != null) {
            myHandler.removeMessages(5);
            refreshText.setText("结束用车");
        }
        refreshLocation.setEnabled(true);
    }

    private MyHandler myHandler;

    private void initView() {
        viewMy = (View) view.findViewById(R.id.viewMy);
        text_desc = (LinearLayout) view.findViewById(R.id.text_desc);
        refreshLocation = (LinearLayout) view.findViewById(R.id.refreshLocation);
        money = (TextView) view.findViewById(R.id.money);
        code = (TextView) view.findViewById(R.id.code);
        time = (TextView) view.findViewById(R.id.time);
        unLock = (TextView) view.findViewById(R.id.unLock);
        refreshText = (TextView) view.findViewById(R.id.refreshText);

        unLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof IMainView) {
                    if (bo != null && bo.getBicycle() != null && bo.getBicycle().getOpenType() == 2 ||bo.getBicycle().getOpenType() == 4) {
                        ((IMainView) getActivity()).trip_unlock_forBluetooth();
                    } else {
                        ((IMainView) getActivity()).trip_unlock();
                    }
                    unLock.setSelected(true);
                    unLock.setEnabled(false);
                }
            }
        });
        refreshLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof IMainView) {
                    if (bo.getInTheParkingLot() != null && bo.getInTheParkingLot() || isInIt) {
                        if (bo != null && bo.getBicycle() != null && bo.getBicycle().getOpenType() == 2 || bo.getBicycle().getOpenType() == 4) {
                            ((IMainView) getActivity()).endMyTripForBlue();
                        } else {
                            ((IMainView) getActivity()).endMyTrip();
                        }
                        refreshLocation.setEnabled(false);
                    } else {
//                        ((IMainView) getActivity()).setStartRefreshLocation();
                        if (bo != null && bo.getBicycle() != null && bo.getBicycle().getOpenType() == 2 || bo.getBicycle().getOpenType() == 4) {
                            ((IMainView) getActivity()).endMyTripForBlue();
                        } else {
                            ((IMainView) getActivity()).endMyTrip();
                        }
                        refreshLocation.setEnabled(false);
                        unLock.setSelected(true);
                        unLock.setEnabled(false);
                        Message msg = new Message();
                        msg.what = 5;
                        myHandler = new MyHandler(30L);
                        myHandler.setOnChangeLisener(new MyHandler.onCountChange() {
                            @Override
                            public void currentCount(String count) {
                                refreshText.setText(count + "s");
                                Message msg1 = new Message();
                                msg1.what = 5;
                                myHandler.sendMessageDelayed(msg1, 1000);
                                if (count.contentEquals("0")) {
                                    myHandler.removeMessages(5);
                                    refreshText.setText(getString(R.string.park_refresh));
                                    refreshLocation.setEnabled(true);
                                    unLock.setSelected(false);
                                    unLock.setEnabled(true);
                                    time.setText(Utils.stampToDate3(System.currentTimeMillis()));
                                }
                            }
                        });
                        myHandler.sendMessageDelayed(msg, 1000);
                    }
                }
            }
        });
    }

    //开锁失败
    public void unlockFailed() {
        unLock.setSelected(false);
        unLock.setEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (myHandler != null) {
            myHandler.removeMessages(5);
            myHandler = null;
        }
    }
}
