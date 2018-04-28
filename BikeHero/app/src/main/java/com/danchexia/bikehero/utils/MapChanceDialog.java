package com.danchexia.bikehero.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danchexia.bikehero.R;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/5.
 * description:
 */

public class MapChanceDialog extends Dialog {
    private Context mContext;
    private OnDialogClickListener lisener;
    private TextView baiduMap;
    private TextView gaodeMap;
    private TextView cancel;


    public interface OnDialogClickListener{
        void onChooce(int mapChoose);
    }

    //只有主操作按钮 标题也没有
    public MapChanceDialog(Context context , OnDialogClickListener listener) {
        super(context, R.style.ActionTheme);
        mContext = context;
        this.lisener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_map_chance);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        initView();
    }

    private void initView() {
        baiduMap = (TextView) findViewById(R.id.baiduMap);
        gaodeMap = (TextView) findViewById(R.id.gaodeMap);
        cancel = (TextView) findViewById(R.id.cancel);
        if (Utils.isBaiduMapInstalled()){
            baiduMap.setVisibility(View.VISIBLE);
        }
        if (Utils.isGdMapInstalled()){
            gaodeMap.setVisibility(View.VISIBLE);
        }
        baiduMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisener.onChooce(0);
                dismiss();
            }
        });
        gaodeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisener.onChooce(1);
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}