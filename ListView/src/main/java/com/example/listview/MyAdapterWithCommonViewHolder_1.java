package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.listview.bean.Bean;
import com.example.listview.utils.ViewHolder;

import java.util.List;

/**
 * Created by mac on 2019/1/23.
 */

public class MyAdapterWithCommonViewHolder_1 extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Bean> mDatas;
    private Context mContext;


    public MyAdapterWithCommonViewHolder_1(Context context, List<Bean> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, R.layout.item_listview, position);

        Bean bean = mDatas.get(position);

        //TextView title = holder.getView(R.id.title);
        //title.setText(bean.getTitle());

        ((TextView) holder.getView(R.id.title)).setText(bean.getTitle());
        ((TextView) holder.getView(R.id.desc)).setText(bean.getDesc());
        ((TextView) holder.getView(R.id.time)).setText(bean.getTime());
        ((TextView) holder.getView(R.id.phone)).setText(bean.getPhone());

        return holder.getConvertView();
    }


}
