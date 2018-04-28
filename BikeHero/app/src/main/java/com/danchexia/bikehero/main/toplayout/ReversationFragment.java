package com.danchexia.bikehero.main.toplayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.main.IMainView;
import com.danchexia.bikehero.utils.MyUtils;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/26.
 * description:预约用车
 */

public class ReversationFragment extends Fragment {
    private View view;
    private Button reservation_now;
    private TextView km_reservation, min_reservation, location;
    private String  time, address;
    private int distance = 0;

    public ReversationFragment() {
    }

    @SuppressLint("ValidFragment")
    public ReversationFragment(int km, String min, String location) {
        this.distance = km;
        this.time = min;
        this.address = location;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reservation,
                container, false);
        initView();
        return view;
    }

    private void initView() {
        reservation_now = (Button) view.findViewById(R.id.reservation_now);
        location = (TextView) view.findViewById(R.id.location);
        km_reservation = (TextView) view.findViewById(R.id.km_reservation);
        min_reservation = (TextView) view.findViewById(R.id.min_reservation);

        if (!TextUtils.isEmpty(time)) {
            km_reservation.setText(Utils.object2String((float)distance/1000));
            min_reservation.setText(Utils.object2String(time));
            location.setText(Utils.object2String(address));
        }
        int step = MyApplication.getIsIdenty();
        if (step == 0) {
            reservation_now.setText(getString(R.string.reversation_no_ident));
        }

        reservation_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtils.checkStep(getActivity())) {//检测登录
                    if (getActivity() instanceof IMainView) {
                        ((IMainView) getActivity()).recervationBike();
                    }
                }
            }
        });
    }

}
