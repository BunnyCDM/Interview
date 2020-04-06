package com.example.recycleview.demo22;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleview.R;

/**
 * Created by mac on 2020-04-05.
 */
public class TypeThreeViewHolder extends TypeAbstracViewHolder<DataModelThree> {

    public ImageView avatar;
    public TextView name;
    public TextView content;
    public ImageView contentImage;


    public TypeThreeViewHolder(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.avatar);
        name = itemView.findViewById(R.id.name);
        content = itemView.findViewById(R.id.content);
        contentImage = itemView.findViewById(R.id.contentImage);

        itemView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void bindHolder(DataModelThree dataModel){
        avatar.setBackgroundColor(dataModel.avatarColor);
        name.setText(dataModel.name);
        content.setText(dataModel.content);
        contentImage.setBackgroundResource(dataModel.contentColor);
    }
}
