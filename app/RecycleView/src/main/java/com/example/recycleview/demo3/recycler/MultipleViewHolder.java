package com.example.recycleview.demo3.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by mac on 2020-04-10.
 * <p>
 * 方便以后处理
 * <p>
 * 自定义ViewHolder需要继承BaseViewHolder
 */
public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
