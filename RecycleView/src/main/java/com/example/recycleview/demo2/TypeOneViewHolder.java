package com.example.recycleview.demo2;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleview.R;

/**
 * Created by mac on 2020-04-05.
 */
public class TypeOneViewHolder extends TypeAbstracViewHolder {

    public ImageView avatar;
    public TextView name;

    public TypeOneViewHolder(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.avatar);
        name = itemView.findViewById(R.id.name);

        itemView.setBackgroundColor(Color.BLACK);
    }

    @Override
    public void bindHolder(DataModel dataModel) {
        avatar.setBackgroundResource(dataModel.avatarColor);
        name.setText(dataModel.name);
    }

}
