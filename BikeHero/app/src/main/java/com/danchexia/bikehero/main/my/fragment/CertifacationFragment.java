package com.danchexia.bikehero.main.my.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.utils.MyUtils;

/**
 * Created by farley on 17/5/17.
 * description:
 */

@SuppressLint("ValidFragment")
public class CertifacationFragment extends Fragment {
    private View view;
    private ImageView step1, step2, step3, step4;
    private LinearLayout certifation;
    private Boolean canclick ;
    private RelativeLayout ident;
    private TextView ident_txt;
    private TextView view_self_title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_certifacation,
                container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @SuppressLint("ValidFragment")
    public CertifacationFragment(Boolean canclick) {
        this.canclick = canclick;
    }

    private void initView() {
        view_self_title = (TextView) view.findViewById(R.id.view_self_title);
        ident = (RelativeLayout) view.findViewById(R.id.ident);
        certifation = (LinearLayout) view.findViewById(R.id.certifation);
        ident_txt = (TextView) view.findViewById(R.id.ident_txt);
        step1 = (ImageView) view.findViewById(R.id.step1);
        step2 = (ImageView) view.findViewById(R.id.step2);
        step3 = (ImageView) view.findViewById(R.id.step3);
        step4 = (ImageView) view.findViewById(R.id.step4);
        final PersonalBean bean = MyUtils.getPersonData();
        if (bean != null) {
            switch (MyApplication.getIsIdenty()) {
                case 1:
                    step1.setImageDrawable(getResources().getDrawable(R.drawable.shaft_2));
                    step2.setImageDrawable(getResources().getDrawable(R.drawable.shaft_3));
                    step3.setImageDrawable(getResources().getDrawable(R.drawable.shaft_3));
                    step4.setImageDrawable(getResources().getDrawable(R.drawable.shaft_3));
                    break;
                case 2:
                    step1.setImageDrawable(getResources().getDrawable(R.drawable.shaft_1));
                    step2.setImageDrawable(getResources().getDrawable(R.drawable.shaft_2));
                    step3.setImageDrawable(getResources().getDrawable(R.drawable.shaft_3));
                    step4.setImageDrawable(getResources().getDrawable(R.drawable.shaft_3));
                    break;
                case 3:
                    step1.setImageDrawable(getResources().getDrawable(R.drawable.shaft_1));
                    step2.setImageDrawable(getResources().getDrawable(R.drawable.shaft_1));
                    step3.setImageDrawable(getResources().getDrawable(R.drawable.shaft_2));
                    step4.setImageDrawable(getResources().getDrawable(R.drawable.shaft_3));
                    break;
                default:
                    break;
            }

            certifation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (canclick) {
                        switch (bean.getAuthStep()) {
                            case 2:
                                ActivityController.startRecharge(getActivity());
                                break;
                            case 3:
                                ActivityController.startIdentid(getActivity());
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
            if (Config.isNeedIdent){
                ident.setVisibility(View.VISIBLE);
                ident_txt.setVisibility(View.VISIBLE);
            }else{
                ident.setVisibility(View.GONE);
                ident_txt.setVisibility(View.GONE);
            }
            if (!Config.isNeedIdent){
                view_self_title.setText(getString(R.string.certifacation_title_no));
            }
        }
    }
}
