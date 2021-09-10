package com.asw.rxretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.asw.rxretrofit.R;
import com.asw.rxretrofit.bean.TrainTimeByStation;

import java.util.List;

/**
 * Created by castl on 2016/4/25.
 */
public class TrainAdapter extends BaseAdapter {


    private List<TrainTimeByStation.ResultBean.ListBean> data;
    private LayoutInflater mInflater;

    public final class ViewHolder {
        public TextView lv_tv_train_no,
                lv_tv_train_type,
                lv_tv_start_station_type,
                lv_tv_start_station,
                lv_tv_start_time,
                lv_tv_run_time,
                lv_tv_end_station_type,
                lv_tv_end_station,
                lv_tv_end_time;
    }

    public TrainAdapter(Context context, List<TrainTimeByStation.ResultBean.ListBean> data) {
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

            convertView = mInflater.inflate(R.layout.list_train, null);
            holder.lv_tv_train_no = (TextView) convertView.findViewById(R.id.lv_tv_train_no);
            holder.lv_tv_train_type = (TextView) convertView.findViewById(R.id.lv_tv_train_type);
            holder.lv_tv_start_station_type = (TextView) convertView.findViewById(R.id.lv_tv_start_station_type);
            holder.lv_tv_start_station = (TextView) convertView.findViewById(R.id.lv_tv_start_station);
            holder.lv_tv_start_time = (TextView) convertView.findViewById(R.id.lv_tv_start_time);
            holder.lv_tv_run_time = (TextView) convertView.findViewById(R.id.lv_tv_run_time);
            holder.lv_tv_end_station_type = (TextView) convertView.findViewById(R.id.lv_tv_end_station_type);
            holder.lv_tv_end_station = (TextView) convertView.findViewById(R.id.lv_tv_end_station);
            holder.lv_tv_end_time = (TextView) convertView.findViewById(R.id.lv_tv_end_time);
            convertView.setTag(holder);

        } else {
            //通过tag找到缓存的布局
            holder = (ViewHolder) convertView.getTag();
        }
        //设置布局中控件要显示的视图
        holder.lv_tv_train_no.setText(data.get(position).getTrain_no());
        holder.lv_tv_train_type.setText(data.get(position).getTrain_type());
        holder.lv_tv_start_station_type.setText(data.get(position).getStart_station_type());
        holder.lv_tv_start_station.setText(data.get(position).getStart_station());
        holder.lv_tv_start_time.setText(data.get(position).getStart_time());
        holder.lv_tv_run_time.setText(data.get(position).getRun_time());
        holder.lv_tv_end_station_type.setText(data.get(position).getEnd_station_type());
        holder.lv_tv_end_station.setText(data.get(position).getEnd_station());
        holder.lv_tv_end_time.setText(data.get(position).getEnd_time());
        return convertView;
    }
}
