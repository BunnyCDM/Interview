package com.asw.rxretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.asw.rxretrofit.R;
import com.asw.rxretrofit.bean.Weather;

import java.util.List;


/**
 * Created by castl on 2016/4/23.
 */
public class WeatherAdapter extends BaseAdapter {

    private List<Weather.ResultBean.DataBean.WeatherBean> data;
    private LayoutInflater mInflater;


    public WeatherAdapter(Context context, List<Weather.ResultBean.DataBean.WeatherBean> data) {
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    public final class ViewHolder {
        public TextView weather_info, date, week, moon;
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

            convertView = mInflater.inflate(R.layout.list_weather, null);

            holder.weather_info = (TextView) convertView.findViewById(R.id.tv_weather_info);
            holder.date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.week = (TextView) convertView.findViewById(R.id.tv_week);
            holder.moon = (TextView) convertView.findViewById(R.id.tv_moon);

            convertView.setTag(holder);

        } else {
            //通过tag找到缓存的布局
            holder = (ViewHolder) convertView.getTag();
        }

        //设置布局中控件要显示的视图
        holder.weather_info.setText(data.get(position).getInfo().getDay().get(1).toString());
        holder.date.setText(data.get(position).getDate());
        holder.week.setText("星期" + data.get(position).getWeek());
        holder.moon.setText("农历"+data.get(position).getNongli());
        return convertView;
    }
}
