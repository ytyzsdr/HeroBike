package com.danchexia.bikehero.main.feedback.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danchexia.bikehero.R;
import com.danchexia.bikehero.main.feedback.bean.FeedbackTypeListData;

import java.util.List;

import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/22.
 * description:
 */

public class QuestionAdapter extends BaseAdapter {
    private Context mContext;
    private List<FeedbackTypeListData> strList;
    private int seleted = -1;
    private OnMySeletcedListener onMySeletcedListener;
    private int resultPosition =-1;//选中更多问题的脚标
    public QuestionAdapter(Context context,List<FeedbackTypeListData> strList) {
        mContext = context;
        this.strList = strList;
    }

    @Override
    public int getCount() {
        if (strList.size() > 5){
            return 6;
        }else{
            return strList.size();
        }

    }

    @Override
    public Object getItem(int position) {
        return strList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setSelected(int posSelect){
        seleted = posSelect;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_question,null);
            holder = new ViewHolder();
            holder.questionName = (TextView) convertView.findViewById(R.id.questionName);
            holder.ll_bg = (LinearLayout) convertView.findViewById(R.id.ll_bg);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.questionName.setText(Utils.object2String(strList.get(position).getTypeName()));
        if (seleted == position){
            holder.ll_bg.setSelected(true);
            holder.questionName.setSelected(true);
        }else{
            holder.ll_bg.setSelected(false);
            holder.questionName.setSelected(false);
        }
        holder.ll_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMySeletcedListener.onSelected(position);
            }
        });
        //如果返回的脚标不等于-1且是最后一个
        if(resultPosition != -1 && position == getCount()-1){
            holder.questionName.setText(strList.get(resultPosition).getTypeName());
        }
        return convertView;
    }
    class ViewHolder{
        TextView questionName;
        LinearLayout ll_bg;
    }
    public void setOnMySelectedListener(OnMySeletcedListener onMySeletcedListener){
        this.onMySeletcedListener = onMySeletcedListener;
    }
    public interface OnMySeletcedListener{
        void onSelected(int position);
    }

    public void setResultPosition(int resultPosition){
        this.resultPosition =resultPosition;
    }
}
