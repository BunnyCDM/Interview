package com.example.listview.demo1;

import android.content.Context;

import com.example.listview.R;
import com.example.listview.demo1.bean.Bean;
import com.example.listview.demo1.utils.CommonAdapter;
import com.example.listview.demo1.utils.ViewHolder;

import java.util.List;

/**
 * Created by mac on 2019/1/23.
 */

public class MyAdapterWithCommonViewHolder_2 extends CommonAdapter<Bean> {


    public MyAdapterWithCommonViewHolder_2(Context context, List<Bean> datas) {
        super(context, datas, R.layout.item_listview);
    }


    @Override
    public void convert(ViewHolder holder, Bean bean) {

        //链式编程
        holder.setText(R.id.title, bean.getTitle())
                .setText(R.id.desc, bean.getDesc())
                .setText(R.id.time, bean.getTime())
                .setText(R.id.phone, bean.getPhone());
    }


}
