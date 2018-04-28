package com.danchexia.bikehero.main.bottomfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.danchexia.bikehero.batterymain.BatteryMainView;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.main.IMainView;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.utils.MyUtils;

/**
 * Created by farley on 17/5/16.
 * description:
 */

public class FragmentBottom extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView mapview_location;
    private ImageView mapview_refresh;
    private ImageView mapview_help;
    private ImageView mapview_scan_lock;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_bottom,
                container, false);
        initView();
        return view;
    }

    private void initView() {
        mapview_help = (ImageView) view.findViewById(R.id.mapview_help);
        mapview_location = (ImageView) view.findViewById(R.id.mapview_location);
        mapview_refresh = (ImageView) view.findViewById(R.id.mapview_refresh);
        mapview_scan_lock = (ImageView) view.findViewById(R.id.mapview_scan_lock);
        mapview_location.setOnClickListener(this);
        mapview_scan_lock.setOnClickListener(this);
        mapview_refresh.setOnClickListener(this);
        mapview_help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mapview_location:
                if (getActivity() instanceof IMainView){
                    ((IMainView) getActivity()).restartLocation();
                }else if (getActivity() instanceof BatteryMainView){
                    ((BatteryMainView) getActivity()).reLocation();
                }
                break;
            case R.id.mapview_refresh:
                setRefreshStart();
                break;
            case R.id.mapview_scan_lock:
                if (MyUtils.checkStep(getActivity())) {//检测认证
                    ActivityController.startScan(getActivity());
                }
                break;
            case R.id.mapview_help:
                if (MyUtils.checkStep(getActivity())) {//检测认证
                    if (getActivity() instanceof IMainView){
                        ((IMainView) getActivity()).helpFeedback();
                    }else if (getActivity() instanceof BatteryMainView){
                        ((BatteryMainView) getActivity()).helpFeedback();
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置刷新动画
     */
    public void setRefreshStart(){
        if (mapview_refresh.isEnabled()) {
            if (getActivity() instanceof IMainView) {
                ((IMainView) getActivity()).refreshAll();
                mapview_refresh.setEnabled(false);
            } else if (getActivity() instanceof BatteryMainView) {
                ((BatteryMainView) getActivity()).refreshData();
                mapview_refresh.setEnabled(false);
            }
            MyUtils.setAnimotion(mapview_refresh);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mapview_refresh.setEnabled(true);
                    mapview_refresh.setVisibility(View.VISIBLE);
                }
            }, 10000);
        }
    }
    /**
     * 设置刷新和扫描隐藏
     */
    public void setMyGone(){
        mapview_scan_lock.setVisibility(View.GONE);
        mapview_refresh.setVisibility(View.GONE);
    }
    /**
     * 设置刷新和扫描隐藏
     */
    public void setMyVisiable(){
        mapview_scan_lock.setVisibility(View.VISIBLE);
        mapview_refresh.setVisibility(View.VISIBLE);
    }
}
