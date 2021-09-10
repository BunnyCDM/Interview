package com.asw.rxretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.asw.rxretrofit.R;
import com.asw.rxretrofit.bean.HistoryToday;

import java.util.List;


/**
 * Created by castl on 2016/4/24.
 */
public class HistoryAdapter extends BaseAdapter {


    private List<HistoryToday.ResultBean> data;
    private LayoutInflater mInflater;

    public final class ViewHolder {
        public TextView history_day, history_date, history_title;
    }

    public HistoryAdapter(Context context, List<HistoryToday.ResultBean> data) {
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //判断是否存在缓存
        if (convertView == null) {
            holder = new ViewHolder();

            //通过 LayoutInflater实例化布局

            convertView = mInflater.inflate(R.layout.list_history, null);

            holder.history_day = (TextView) convertView.findViewById(R.id.tv_history_day);
            holder.history_date = (TextView) convertView.findViewById(R.id.tv_history_date);
            holder.history_title = (TextView) convertView.findViewById(R.id.tv_history_title);
            convertView.setTag(holder);

        } else {
            //通过tag找到缓存的布局
            holder = (ViewHolder) convertView.getTag();
        }

        //设置布局中控件要显示的视图
        holder.history_day.setText(data.get(position).getDay());
        holder.history_date.setText("历史上今日：" + data.get(position).getDate());
        holder.history_title.setText(data.get(position).getTitle());
        return convertView;
    }
}
