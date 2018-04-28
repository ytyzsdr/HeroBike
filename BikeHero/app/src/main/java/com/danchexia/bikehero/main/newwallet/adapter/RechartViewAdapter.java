package com.danchexia.bikehero.main.newwallet.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.bean.RechartTypeBean;

import java.util.List;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/8/21.
 * description:
 */

public class RechartViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<RechartTypeBean> dataList;
    private int selected = 0;
    public RechartViewAdapter(Context context, List<RechartTypeBean> dataList) {
        mContext = context;
        this.dataList = dataList;
    }
    public void setMySelected(int pos){
        selected = pos;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_my_rechart, null);
            holder = new ViewHolder();
            holder.item_all = (LinearLayout) convertView.findViewById(R.id.item_all);
            holder.needPay = (TextView) convertView.findViewById(R.id.needPay);
            holder.offerMoney = (TextView) convertView.findViewById(R.id.offerMoney);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        RechartTypeBean bean = dataList.get(position);
        holder.needPay.setText(Utils.object2String(bean.getPayAmount())+"元");
        String descStr = bean.getRemark();
        if (TextUtils.isEmpty(descStr)){
            holder.offerMoney.setVisibility(View.GONE);
        }else {
            holder.offerMoney.setText(descStr);
        }
        if (position == selected){
            holder.item_all.setSelected(true);
            holder.needPay.setSelected(true);
        }else{
            holder.item_all.setSelected(false);
            holder.needPay.setSelected(false);
        }
        return convertView;
    }

    class ViewHolder {
        TextView needPay,offerMoney;
        LinearLayout item_all;
    }
}
