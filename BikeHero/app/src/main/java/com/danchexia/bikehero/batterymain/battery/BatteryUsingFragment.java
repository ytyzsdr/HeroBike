package com.danchexia.bikehero.batterymain.battery;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.batterymain.BatteryMainView;
import com.danchexia.bikehero.main.bean.BatteryBean;
import com.danchexia.bikehero.main.bean.OnGoingInfoBean;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/8/24.
 * description:
 */

public class BatteryUsingFragment extends Fragment {
    public BatteryUsingFragment() {
    }
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_battery_using,
                container, false);
        return view;
    }
    private TextView closeQusetion;
    private TextView useTime;
    private TextView b_remain;
    private TextView b_life;
    private TextView price;
    private TextView stopTrip;
    private LinearLayout closeTrip;
    public  static boolean isUsingFound = false;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        closeTrip = (LinearLayout) view.findViewById(R.id.closeTrip);
        closeQusetion = (TextView) view.findViewById(R.id.closeQusetion);
        useTime = (TextView) view.findViewById(R.id.useTime);
        b_remain = (TextView) view.findViewById(R.id.b_remain);
        b_life = (TextView) view.findViewById(R.id.b_life);
        price = (TextView) view.findViewById(R.id.price);
        stopTrip = (TextView) view.findViewById(R.id.stopTrip);
        closeTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof BatteryMainView){
                    ((BatteryMainView)getActivity()).endTripForBattery();
                }
            }
        });
        closeQusetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof BatteryMainView){
                    ((BatteryMainView)getActivity()).helpFeedback();
                }
            }
        });
    }
    public void setData(OnGoingInfoBean onGoingInfoBean){
        OnGoing_TripBO tripBo = onGoingInfoBean.getOnGoing_tripBO();
        String rideTime = String.valueOf(((System.currentTimeMillis() - tripBo.getBeginTime().getTime())/1000/60));
        BatteryBean batteryBean = tripBo.getBatteryBO();
        useTime.setText(rideTime);
        b_remain.setText(Utils.object2String(batteryBean.getElectricity()));
        b_life.setText(Utils.object2String(batteryBean.getRechargeMileage()));
        price.setText(Utils.object2String(batteryBean.getPrice()));
    }

    @Override
    public void onResume() {
        super.onResume();
        isUsingFound = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isUsingFound = false;
    }
}
