package com.example.recycleview.demo2_1;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleview.R;

/**
 * Created by mac on 2020-04-05.
 */
public class TypeOneViewHolder extends TypeAbstracViewHolder<DataModelOne> {

    public ImageView avatar;
    public TextView name;

    public TypeOneViewHolder(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.avatar);
        name = itemView.findViewById(R.id.name);

        itemView.setBackgroundColor(Color.BLACK);
    }

    @Override
    public void bindHolder(DataModelOne dataModel) {
        avatar.setBackgroundResource(dataModel.avatarColor);
        name.setText(dataModel.name);
    }

}
