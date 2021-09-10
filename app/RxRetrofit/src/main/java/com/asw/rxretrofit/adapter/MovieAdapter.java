package com.asw.rxretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.asw.rxretrofit.R;
import com.asw.rxretrofit.bean.MovieBean;

import java.util.List;


/**
 * Created by castl on 2016/4/24.
 */
public class MovieAdapter extends BaseAdapter {
    private List<MovieBean.ResultBean> data;
    private LayoutInflater mInflater;

    public final class ViewHolder {
        public TextView tv_movie_title, tv_movie_week, tv_movie_weekoffice, tv_movie_alloffice;
    }

    public MovieAdapter(Context context, List<MovieBean.ResultBean> data) {
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

            convertView = mInflater.inflate(R.layout.list_movies, null);

            holder.tv_movie_title = (TextView) convertView.findViewById(R.id.tv_movie_title);
            holder.tv_movie_week = (TextView) convertView.findViewById(R.id.tv_movie_week);
            holder.tv_movie_weekoffice = (TextView) convertView.findViewById(R.id.tv_movie_weekoffice);
            holder.tv_movie_alloffice = (TextView) convertView.findViewById(R.id.tv_movie_alloffice);
            convertView.setTag(holder);

        } else {
            //通过tag找到缓存的布局
            holder = (ViewHolder) convertView.getTag();
        }

        //设置布局中控件要显示的视图
        holder.tv_movie_title.setText(data.get(position).getName());
        holder.tv_movie_week.setText(data.get(position).getWk());
        holder.tv_movie_weekoffice.setText(data.get(position).getWboxoffice());
        holder.tv_movie_alloffice.setText(data.get(position).getTboxoffice());
        return convertView;
    }
}
