package com.example.recycleview.demo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recycleview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2020-04-04.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<String> datas;

    private List<Integer> heights;

    public void delData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String data);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position, String data);
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int position;
        private String data;

        public MyOnClickListener(int position, String data) {
            this.position = position;
            this.data = data;

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,position,data);
        }
    }

    private class MyOnLongClickListener implements View.OnLongClickListener {
        private int position;
        private String data;

        public MyOnLongClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickListener.onItemLongClick(v, position, data);
            return true;
        }
    }


    public StaggeredAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
        heights = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            heights.add((int) (100 + Math.random() * 300));
        }
    }

    /**
     * @param viewGroup
     * @param i
     * @return 创建ViewHolder
     */
    @NonNull
    @Override
    public StaggeredAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_single_textview, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    /**
     * @param myViewHolder
     * @param position     绑定ViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull StaggeredAdapter.MyViewHolder myViewHolder, int position) {
        if (onItemClickListener != null) {
            int layoutPosition = myViewHolder.getLayoutPosition();
            myViewHolder.itemView.setOnClickListener(new MyOnClickListener(layoutPosition,datas.get(layoutPosition)));
        }
        if (onItemClickListener != null) {
            int layoutPosition = myViewHolder.getLayoutPosition();
            myViewHolder.itemView.setOnLongClickListener(new MyOnLongClickListener(layoutPosition,datas.get(layoutPosition)));
        }
        ViewGroup.LayoutParams layoutParams = myViewHolder.itemView.getLayoutParams();
        layoutParams.height = heights.get(position);
        myViewHolder.itemView.setLayoutParams(layoutParams);
        myViewHolder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}



