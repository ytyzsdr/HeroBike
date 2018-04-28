package com.danchexia.bikehero.batterymain.battery;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.batterymain.BatteryMainView;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.bean.BatteryBean;
import com.danchexia.bikehero.utils.MapChanceDialog;
import com.danchexia.bikehero.utils.MyUtils;

import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.PreferencesUtils;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/8/24.
 * description:
 */

public class BatteryIntroductionFragment extends Fragment {
    public BatteryIntroductionFragment() {
    }

    @SuppressLint("ValidFragment")
    public BatteryIntroductionFragment(BatteryBean bean, int distance) {
        this.bean = bean;
        this.distance = distance;
    }

    private BatteryBean bean;
    private int distance;
    private View view;
    private TextView batteryType;
    private TextView batteryAddress;
    private TextView batteryGo;
    private TextView b_distance;
    private TextView b_life;
    private TextView b_price;
    private TextView b_list;
    private TextView b_remain;
    private TextView priceUnit;
    private LinearLayout noData;
    private LinearLayout haveData;
    private RelativeLayout go_nav;
    private LinearLayout closeTrip;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_battery_introduction,
                container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData(bean);
    }

    private void initData(BatteryBean bean) {
        if (bean != null) {
            b_distance.setText((float) distance / 1000 + "");
            b_remain.setText(Utils.object2String(bean.getElectricity()));
            batteryType.setText(Utils.object2String(bean.getTypeName()));
            batteryAddress.setText(Utils.object2String(bean.getLocationDetails()));
            b_price.setText(Utils.object2String(bean.getPrice()));
            priceUnit.setText("元/" + bean.getPriceMinute() + "分钟");
            b_life.setText(Utils.object2String(bean.getRechargeMileage()));
            if (bean.getRechargeMileage() == null) {
                noData.setVisibility(View.VISIBLE);
                haveData.setVisibility(View.GONE);
            } else {
                haveData.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
            }
        }
    }

    private void initView() {
        closeTrip = (LinearLayout) view.findViewById(R.id.closeTrip);
        go_nav = (RelativeLayout) view.findViewById(R.id.go_nav);
        haveData = (LinearLayout) view.findViewById(R.id.haveData);
        noData = (LinearLayout) view.findViewById(R.id.noData);
        priceUnit = (TextView) view.findViewById(R.id.priceUnit);
        batteryType = (TextView) view.findViewById(R.id.batteryType);
        batteryAddress = (TextView) view.findViewById(R.id.batteryAddress);
        batteryGo = (TextView) view.findViewById(R.id.batteryGo);
        b_distance = (TextView) view.findViewById(R.id.b_distance);
        b_remain = (TextView) view.findViewById(R.id.b_remain);
        b_life = (TextView) view.findViewById(R.id.b_life);
        b_price = (TextView) view.findViewById(R.id.b_price);
        b_list = (TextView) view.findViewById(R.id.b_list);
        b_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StanderdDialog.getInstatnce(getActivity(), bean.getTypeDesc(), "知道了", new StanderdDialog.OnDialogClickListener() {
//                    @Override
//                    public void doAnyClick() {
//
//                    }
//
//                    @Override
//                    public void doMainClick() {
//                    }
//                }).show();
                StanderdDialog dialog = new StanderdDialog(getActivity(), bean.getTypeDesc(), "知道了",
                        new StanderdDialog.OnDialogClickListener() {
                            @Override
                            public void doAnyClick() {
                            }

                            @Override
                            public void doMainClick() {
                            }
                        });
                dialog.show();
            }
        });
        noData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int step = MyApplication.getIsIdenty();
                if (step == 0) {
                    ActivityController.startBindPhone(getActivity());
                    if (getActivity() instanceof BatteryMainView) {
                        ((BatteryMainView) getActivity()).setGetAllBatteryBol();
                    }
                } else {
                    if (getActivity() instanceof BatteryMainView) {
                        MyUtils.savaBatteryData(bean);
                        PreferencesUtils.putInt(MyApplication.appContext, "BATTERY_DISTANCE", distance);
                        ((BatteryMainView) getActivity()).startToPerson();
                    }
                }

            }
        });
        go_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getPoint() != null) {
                    if (Utils.isBaiduMapInstalled() || Utils.isGdMapInstalled()) {
                        MapChanceDialog dialog = new MapChanceDialog(getActivity(), new MapChanceDialog.OnDialogClickListener() {
                            @Override
                            public void onChooce(int mapChoose) {
                                double[] startLocation = new double[2];
                                startLocation[0] = bean.getPoint().getX();
                                startLocation[1] = bean.getPoint().getY();
                                if (mapChoose == 0) {
                                    Utils.openBaiduMap(getActivity(), "电池", startLocation);
                                } else if (mapChoose == 1) {
                                    startLocation = Utils.bdToGaoDe(startLocation[0], startLocation[1]);
                                    Utils.openGaoDeMap(getActivity(), "电池", startLocation);
                                }
                            }
                        });
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(false);
                    } else {
                        ShowToast.show(getActivity(), "您还没有安装任何地图应用");
                    }
                }
            }
        });
    }


}
