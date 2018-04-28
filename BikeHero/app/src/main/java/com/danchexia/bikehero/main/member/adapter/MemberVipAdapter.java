package com.danchexia.bikehero.main.member.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.bean.MemberVipCardBean;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/8/17.
 * description:
 */

public class MemberVipAdapter extends BaseAdapter {
    private Context mContext;
    private int pos = -1;
    private List<MemberVipCardBean> arrays = new ArrayList<>();
    public MemberVipAdapter(Context context, List<MemberVipCardBean> list) {
        mContext = context;
        arrays = list;
    }
    public void setSelectedPos(int pos){
        this.pos = pos;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return arrays.size();
    }

    @Override
    public Object getItem(int position) {
        return arrays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_member_vip, null);
            holder = new ViewHolder();
            holder.vipName = (TextView) convertView.findViewById(R.id.vipName);
            holder.vipDesc = (TextView) convertView.findViewById(R.id.vipDesc);
            holder.fill = (ImageView) convertView.findViewById(R.id.fill);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        MemberVipCardBean bean = arrays.get(position);
        /*String name = bean.getCardName()+"<font color=\"#53B1FF\">"+bean.getCardAmount()+"</font>"+"元";
        holder.vipName.setText(Html.fromHtml(name));*/
        holder.vipName.setText(bean.getCardName()+"  "+bean.getCardAmount()+"元");
        holder.vipDesc.setText(Utils.object2String(bean.getCardDesc()));
        if (pos == position){
            holder.fill.setVisibility(View.VISIBLE);
        }else{
            holder.fill.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder{
        TextView vipName,vipDesc;
        ImageView fill;
    }
}
