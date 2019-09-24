package com.example.recycleview.old;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleview.R;
import com.example.recycleview.old.bean.IrDeviceType;

import java.util.List;

/**
 * Created by mac on 2019/1/21.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<IrDeviceType> mDatas;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public MyRecyclerViewAdapter(List<IrDeviceType> mDatas) {
        this.mDatas = mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 插入一条数据
     *
     * @param index        下标
     * @param irDeviceType 数据
     */
    public void addItem(int index, IrDeviceType irDeviceType) {
        mDatas.add(index, irDeviceType);
        notifyItemInserted(index);
    }

    /**
     * 删除一条数据
     *
     * @param index 下标
     */
    public void deleteItem(int index) {
        mDatas.remove(index);
        notifyItemRemoved(index);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_irdevicetype,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position).getTitle());
        holder.iv.setImageResource(mDatas.get(position).getImage());
        int adapterPosition = holder.getAdapterPosition();
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new MyOnClickListener(position, mDatas.get(adapterPosition).getTitle()));
        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new MyOnLongClickListener(position, mDatas.get(adapterPosition).getTitle()));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.title);
            iv = (ImageView) itemView.findViewById(R.id.iv_title);
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int position;
        private String data;

        public MyOnClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, position, data);
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
        public boolean onLongClick(View view) {
            onItemLongClickListener.onItemLongClick(view, position, data);
            return true;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String data);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position, String data);
    }


}

