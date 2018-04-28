package com.danchexia.bikehero.main.mystroke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.bean.OnGoing_TripBO;

import java.util.List;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/17.
 * description:
 */

public class ItemStrokeAdapter extends BaseAdapter {
    private Context mContext;
    private List<OnGoing_TripBO> dataList;
    public ItemStrokeAdapter(Context context, List<OnGoing_TripBO> dataList) {
        mContext = context;
        this.dataList = dataList;
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
            convertView = inflater.inflate(R.layout.item_my_stroke, null);
            holder = new ViewHolder();
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.code = (TextView) convertView.findViewById(R.id.code);
            holder.money = (TextView) convertView.findViewById(R.id.money);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        OnGoing_TripBO data = dataList.get(position);
        holder.time.setText(Utils.stampToDate5(data.getCreateTime().getTime()));
        holder.code.setText("NO."+Utils.object2String(data.getSysCode()));
        if (Config.isNeadToPay) {
            holder.money.setText(mContext.getString(R.string.my_stroke_money) + Utils.object2String(data.getPayAmout())
                    + mContext.getString(R.string.my_stroke_money_unit));
        }else{
//            if ("battery".equals(data.getTripType())){
//                holder.money.setText(mContext.getString(R.string.my_battery_money) + Utils.object2String(data.getRideTime())
//                        + "min");
//            }else {
                holder.money.setText(mContext.getString(R.string.my_stroke_time) + Utils.object2String(data.getRideTime())
                        + "min");
//            }
        }

        return convertView;
    }

    class ViewHolder {
        TextView time,code,money;
    }
}
