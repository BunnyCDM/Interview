package com.example.custompullmenu.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.custompullmenu.R;
import com.example.custompullmenu.db.AccountDao;
import com.example.custompullmenu.db.AccountBean;
import com.example.custompullmenu.LoginActivity;

import java.util.ArrayList;


/**
 * Created by louliang on 2018/05/02.
 */

public class OptionsAdapter extends BaseAdapter {
    private ArrayList<AccountBean> list = new ArrayList<AccountBean>();
    private LoginActivity activity = null;
    AccountDao dao;

    public OptionsAdapter(Activity activity, ArrayList<AccountBean> list, AccountDao accountDao) {
        this.activity = (LoginActivity) activity;
        this.dao = accountDao;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //下拉项布局
            convertView = LayoutInflater.from(activity).inflate(R.layout.option_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.item_text);
            holder.nickName = (TextView) convertView.findViewById(R.id.nick);
            holder.imageView = (ImageView) convertView.findViewById(R.id.delImage);
            holder.rl_item = (RelativeLayout) convertView.findViewById(R.id.rl_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final AccountBean accountBean = list.get(position);
        holder.textView.setText(accountBean.getPhone());
        holder.nickName.setText(accountBean.getName());

        //为下拉框选项删除图标部分设置事件，最终效果是点击将该选项删除
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(accountBean);
                dao.delete(accountBean.getPhone());
                activity.setVivi();
                notifyDataSetChanged();
            }
        });
        notifyDataSetChanged();
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        TextView nickName;
        ImageView imageView;
        RelativeLayout rl_item;
    }
}

