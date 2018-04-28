package com.danchexia.bikehero.main.my.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;

import java.text.DecimalFormat;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/17.
 * description:用户的数据
 */

public class PersonalDataFragment extends Fragment{
    private View view;
    private TextView km_person,min_person,kcal_person,my_distance_utils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_data,
                container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        PersonalBean bean = MyUtils.getPersonData();
        if (bean != null) {
            Double distance = bean.getRideDistance();
            DecimalFormat df = new DecimalFormat("######0.0");
            if (distance > 1000){
                Double dis =  distance/1000;
                km_person.setText(Utils.object2String(df.format(dis)));
                my_distance_utils.setText(getString(R.string.person_km));
            }else{
                km_person.setText(Utils.object2String(df.format(distance)));
                my_distance_utils.setText(getString(R.string.person_m));
            }
            min_person.setText(Utils.object2String(bean.getRideTime()));
            Double cacories = bean.getCalorie();
            if (cacories != null) {
                kcal_person.setText(Utils.object2String(df.format(cacories)));
            }
        }
    }

    private void initView() {
        km_person = (TextView) view.findViewById(R.id.km_person);
        min_person = (TextView) view.findViewById(R.id.min_person);
        kcal_person = (TextView) view.findViewById(R.id.kcal_person);
        my_distance_utils = (TextView) view.findViewById(R.id.my_distance_utils);
    }
}
