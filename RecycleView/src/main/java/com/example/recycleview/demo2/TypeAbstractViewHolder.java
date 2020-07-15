package com.example.recycleview.demo2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mac on 2020-04-05.
 */
public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder {


    public TypeAbstractViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(DataModel dataModel);

    // TODO: 2020-07-13 与上面的却别的是，下面这种可以由子类决定是否需要继承该方法，上面那种是强制实现
//    public  void bindHolder(DataModel dataModel){
//
//    }


}
