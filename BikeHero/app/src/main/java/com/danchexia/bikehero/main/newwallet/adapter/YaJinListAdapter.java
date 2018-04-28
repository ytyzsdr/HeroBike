package com.danchexia.bikehero.main.newwallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.wallet.bean.WalletItemData;

import java.util.List;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/8/22.
 * description:
 */

public class YaJinListAdapter extends BaseAdapter {
    private Context mContext;
    private List<WalletItemData> dataList;
    public YaJinListAdapter(Context context, List<WalletItemData> dataList) {
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
            convertView = inflater.inflate(R.layout.item_wallet, null);
            holder = new ViewHolder();
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.money = (TextView) convertView.findViewById(R.id.money);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        WalletItemData data = dataList.get(position);
        holder.time.setText(Utils.stampToDate3(data.getCreateTime().getTime()));

        if (data.getType().contentEquals("1")){
            holder.type.setText(mContext.getString(R.string.wallet_list_recharge));
            holder.money.setText("-¥"+Utils.object2String(data.getAmount()));
            holder.money.setTextColor(mContext.getResources().getColor(R.color.color_deep_gray));
        }else if (data.getType().contentEquals("2") || data.getType().contentEquals("3")){
            holder.type.setText(mContext.getString(R.string.wallet_list_refound));
            holder.money.setText("+¥"+Utils.object2String(data.getAmount()));
            holder.money.setTextColor(mContext.getResources().getColor(R.color.color_tip_red));
        }

        return convertView;
    }

    class ViewHolder {
        TextView time,type,money;
    }
}