package com.danchexia.bikehero.generalphotoreview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.danchexia.bikehero.R;

import uk.co.senab.photoview.PhotoView;


/**
 * Created by farley on 17/4/11.
 * description:
 */

public class FragmentPhotoView extends Fragment implements View.OnClickListener {

    public FragmentPhotoView() {
    }

    @SuppressLint("ValidFragment")
    public FragmentPhotoView(String imgStr) {
        this.imgStr = imgStr;
    }

    private View view;
    private String imgStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_photo_review,
                container, false);
        return view;
    }
    private PhotoView photo_view;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photo_view = (PhotoView) view.findViewById(R.id.photo_view);
        Glide.with(getActivity()).load(imgStr).into(photo_view);

    }

    @Override
    public void onClick(View v) {

    }

}
