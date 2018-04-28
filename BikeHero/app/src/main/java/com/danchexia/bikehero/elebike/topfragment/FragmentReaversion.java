package com.danchexia.bikehero.elebike.topfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danchexia.bikehero.R;

/**
 * Created by farley on 17/9/25.
 * description:
 */

public class FragmentReaversion extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ele_reaversion,
                container, false);
        return view;
    }
}
