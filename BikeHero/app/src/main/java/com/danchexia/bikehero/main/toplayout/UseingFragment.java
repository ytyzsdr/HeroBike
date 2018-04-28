package com.danchexia.bikehero.main.toplayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.IMainView;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;
import com.danchexia.bikehero.utils.MyUtils;

import java.text.DecimalFormat;

import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/30.
 * description:用车中
 */

public class UseingFragment extends Fragment{
    private View view;
    private OnGoing_TripBO onGoing_tripBO;
    private TextView km_person;//骑行距离
    private TextView min_person;//骑行时长
    private TextView kcal_person;//消耗卡路里
    private TextView rev_money;//预计诶用
    private TextView use_car_code;//正在使用的车辆
    private TextView ride_destance_unit;//骑行里程的单位
    private TextView closeLockNoData;//关锁未结束行程
    private ReceiverMyUpdata receiverUpdata;
    public static  Boolean isFrount = false;
    private TextView yuji;
    private LinearLayout showPaw;//显示密码
    private TextView txt_paw1,txt_paw2,txt_paw3,txt_paw4;
    public static final String MY_MESSAGE_RECEIVED_ACTION = "com.thinker.radishsaas.colors.MESSAGE_RECEIVED_ACTION_USING";
    private boolean mShowpaw=false;
    private String mLockpaw;

    @SuppressLint("ValidFragment")
    public UseingFragment() {
    }

    @SuppressLint("ValidFragment")
    public UseingFragment(OnGoing_TripBO onGoing_tripBO) {
        this.onGoing_tripBO = onGoing_tripBO;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_useing,
                container, false);

        return view;
    }
    private void initView() {
        txt_paw4 = (TextView) view.findViewById(R.id.txt_paw4);
        txt_paw3 = (TextView) view.findViewById(R.id.txt_paw3);
        txt_paw2 = (TextView) view.findViewById(R.id.txt_paw2);
        txt_paw1 = (TextView) view.findViewById(R.id.txt_paw1);
        showPaw = (LinearLayout) view.findViewById(R.id.showPaw);
        yuji = (TextView) view.findViewById(R.id.yuji);
        closeLockNoData = (TextView) view.findViewById(R.id.closeLockNoData);
        ride_destance_unit = (TextView) view.findViewById(R.id.ride_destance_unit);
        km_person = (TextView) view.findViewById(R.id.km_person);
        min_person = (TextView) view.findViewById(R.id.min_person);
        kcal_person = (TextView) view.findViewById(R.id.kcal_person);
        rev_money = (TextView) view.findViewById(R.id.rev_money);
        use_car_code = (TextView) view.findViewById(R.id.use_car_code);
        closeLockNoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof IMainView){
                    ((IMainView) getActivity()).helpFeedback();
                }
//                ActivityController.startFeedBack(getActivity(),"2",onGoing_tripBO.getSysCode(),onGoing_tripBO.getId());
            }
        });
        initData();
        initReceiver();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initReceiver() {
        if (receiverUpdata != null){
            receiverUpdata = null;
        }
        receiverUpdata = new ReceiverMyUpdata();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MY_MESSAGE_RECEIVED_ACTION);
        getActivity().registerReceiver(receiverUpdata, filter);
    }
    private void initData(){
        if (onGoing_tripBO != null){
            if (onGoing_tripBO.getDistance() > 1000){
                Double distance = Double.valueOf(onGoing_tripBO.getDistance())/1000;
                DecimalFormat df = new DecimalFormat("######0.0");
                km_person.setText(Utils.object2String(df.format(distance)));
                ride_destance_unit.setText(getString(R.string.person_km));
            }else{
                km_person.setText(Utils.object2String(onGoing_tripBO.getDistance()));
                ride_destance_unit.setText(getString(R.string.person_m));
            }
            LogUtils.d("时长==========="+Utils.object2String(onGoing_tripBO.getRideTime()));
            min_person.setText(Utils.object2String(onGoing_tripBO.getRideTime()));
            kcal_person.setText(Utils.object2String(onGoing_tripBO.getCalorie()));
            if (Config.isNeadToPay) {
                if (MyUtils.getPersonData().getVIP()){
//                    会员
                    rev_money.setText(Utils.object2String(onGoing_tripBO.getVipPrice()) + "元");
                }else {
//                    非会员
                    rev_money.setText(Utils.object2String(onGoing_tripBO.getPrice()) + "元");
                }
            }else{
                rev_money.setVisibility(View.GONE);
                yuji.setVisibility(View.GONE);
            }
            use_car_code.setText(Utils.object2String(onGoing_tripBO.getSysCode()));
            mShowpaw = onGoing_tripBO.getShowPwd();
            mLockpaw = onGoing_tripBO.getUnlockPwd();
            if (mShowpaw){
                showPaw.setVisibility(View.VISIBLE);
                if (mLockpaw.toCharArray().length > 3) {
                    txt_paw1.setText(mLockpaw.charAt(0)+"");
                    txt_paw2.setText(mLockpaw.charAt(1)+"");
                    txt_paw3.setText(mLockpaw.charAt(2)+"");
                    txt_paw4.setText(mLockpaw.charAt(3)+"");
                }
            }else{
                showPaw.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(receiverUpdata);
    }



    public class ReceiverMyUpdata extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            DecimalFormat df = new DecimalFormat("######0.0");
            if (intent != null){
                LogUtils.d("接收广播");
                Integer distance = intent.getIntExtra("DISTANCE",0);
                Long time = intent.getLongExtra("TIME",0);
                Integer calories = intent.getIntExtra("KCAL",0);
                if (distance > 1000){
                    Double dis = Double.valueOf(distance)/1000;
                    km_person.setText(Utils.object2String(df.format(dis)));
                    ride_destance_unit.setText(getString(R.string.person_km));
                }else{
                    km_person.setText(Utils.object2String(distance));
                    ride_destance_unit.setText(getString(R.string.person_m));
                }
                String rideTime = String.valueOf(((System.currentTimeMillis() - time)/1000/60));
                LogUtils.d("onReceive时长==========="+Utils.object2String(rideTime));
                min_person.setText(Utils.object2String(rideTime));
                kcal_person.setText(Utils.object2String(calories));
                if (MyUtils.getPersonData().getVIP()){
                    Double price = intent.getDoubleExtra("VIPMONEY",0);
                    rev_money.setText(Utils.object2String(price)+getString(R.string.toast_19));
                }else {
                    Double price = intent.getDoubleExtra("MONEY",0);
                    rev_money.setText(Utils.object2String(price)+getString(R.string.toast_19));
                }

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isFrount = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isFrount = false ;
    }
}
