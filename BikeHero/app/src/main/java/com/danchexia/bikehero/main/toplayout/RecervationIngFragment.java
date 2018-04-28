package com.danchexia.bikehero.main.toplayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.IMainView;
import com.danchexia.bikehero.main.bean.OnGoing_ReserveBO;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.utils.MyUtils;

import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.MyHandler;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/26.
 * description: 预约中
 */

public class RecervationIngFragment extends Fragment {
    private View view;
    private TextView bike_code;
    private TextView bike_location;
    private TextView bike_ring;
    private TextView time;
    private TextView bike_cancel;
    private OnGoing_ReserveBO onGoing_reserveBO;
    private MyHandler myHandler;

    public RecervationIngFragment() {
    }

    @SuppressLint("ValidFragment")
    public RecervationIngFragment(OnGoing_ReserveBO onGoing_reserveBO) {
        this.onGoing_reserveBO = onGoing_reserveBO;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recervation_ing,
                container, false);
        initView();
        return view;
    }

    private void initView() {
        bike_code = (TextView) view.findViewById(R.id.bike_code);
        bike_location = (TextView) view.findViewById(R.id.bike_location);
        bike_ring = (TextView) view.findViewById(R.id.bike_ring);
        time = (TextView) view.findViewById(R.id.time);
        bike_cancel = (TextView) view.findViewById(R.id.bike_cancel);
        bike_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelReservation();

            }
        });
        bike_ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof IMainView) {
                    ((IMainView) getActivity()).ring();
                }
            }
        });
        if (onGoing_reserveBO != null) {
            //赋值
            bike_code.setText("NO." + onGoing_reserveBO.getSysCode());
            bike_location.setText(Utils.object2String(onGoing_reserveBO.getLocationDetails()));
            final Message msg = new Message();
            msg.what = 3;
            myHandler = new MyHandler((onGoing_reserveBO.getExpireTime().getTime() - System.currentTimeMillis()) / 1000 + 6);
            myHandler.setOnChangeLisener(new MyHandler.onCountChange() {
                @Override
                public void currentCount(String count) {
                    time.setText(count);
                    Message msg1 = new Message();
                    msg1.what = 3;
                    myHandler.sendMessageDelayed(msg1, 1000);
                    LogUtils.d("count=" + count);
                    if (count.contentEquals("0:0")) {
                        myHandler.removeMessages(3);
                        if (getActivity() instanceof IMainView) {
                            ((IMainView) getActivity()).OnGoingStatus();
                        }

                    }
                }
            });
            myHandler.sendMessageDelayed(msg, 1000);
        }
    }
    /**
     * 取消预约提醒
     */
    private  void cancelReservation(){
        SystemParamsBean bean  = MyUtils.getSystemData();
        String nums = "3";
        if (bean != null){
            nums = String.valueOf(bean.getReserveNum());
        }
        StanderdDialog dialog = new StanderdDialog(getActivity(), getString(R.string.toast_14),
                getString(R.string.toast_15)+ nums +getString(R.string.toast_16), getString(R.string.toast_17),
                getString(R.string.toast_18)
                , new StanderdDialog.OnDialogClickListener() {
            @Override
            public void doAnyClick() {

            }

            @Override
            public void doMainClick() {
                if (getActivity() instanceof IMainView) {
                    ((IMainView) getActivity()).canselRecervation();
                }
            }
        });
        dialog.show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (myHandler != null) {
            myHandler.removeMessages(3);
        }
    }
}