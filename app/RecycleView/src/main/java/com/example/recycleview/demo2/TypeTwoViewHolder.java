package com.example.recycleview.demo2;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleview.R;

/**
 * Created by mac on 2020-04-05.
 */
public class TypeTwoViewHolder extends TypeAbstractViewHolder {

    public ImageView avatar;
    public TextView name;
    public TextView content;


    public TypeTwoViewHolder(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.avatar);
        name = itemView.findViewById(R.id.name);
        content = itemView.findViewById(R.id.content);

        itemView.setBackgroundColor(Color.GREEN);
    }

    @Override
    public void bindHolder(DataModel dataModel){
        avatar.setBackgroundResource(dataModel.avatarColor);
        name.setText(dataModel.name);
        content.setText(dataModel.content);
    }
}
