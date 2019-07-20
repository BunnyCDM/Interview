package com.example.recycleview;

import android.content.Context;

import com.example.recycleview.R;
import com.example.recycleview.bean.IrDeviceType;
import com.example.recycleview.util.CommonRecyclerViewAdapter;
import com.example.recycleview.util.RecyclerViewHolder;

import java.util.List;


/**
 * Created by mac on 2019/2/16.
 */
public class MyAdapterWithCommonRecyclerViewHolder extends CommonRecyclerViewAdapter<IrDeviceType> {


    public MyAdapterWithCommonRecyclerViewHolder(Context context, List<IrDeviceType> mDatas) {
        super(context, mDatas, R.layout.item_irdevicetype);
    }

    @Override
    public void convert(RecyclerViewHolder holder, IrDeviceType irDeviceType) {
        holder.setText(R.id.title, irDeviceType.getTitle())
                .setImageResource(R.id.iv_title, irDeviceType.getImage());

    }

}
