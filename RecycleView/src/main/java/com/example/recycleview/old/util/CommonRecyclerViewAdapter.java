package com.example.recycleview.old.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by mac on 2019/2/12.
 * <p>
 * 封装RecyclerViewAdapter
 */
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int mLayoutId;

    public CommonRecyclerViewAdapter(Context context, List<T> mDatas, int layoutId) {
        this.mContext = context;
        this.mDatas = mDatas;
        this.mLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(context);
    }

    public void resetData(List<T> mDatas) {
        mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 插入一条数据
     *
     * @param index 下标
     * @param t     数据
     */
    public void addItem(int index, T t) {
        mDatas.add(index, t);
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
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder = RecyclerViewHolder.get(parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new MyOnClickListener(position, mDatas.get(position)));
        }

        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new MyOnLongClickListener(position, mDatas.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(RecyclerViewHolder holder, T t);

    public void onItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void onItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;

    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public class MyOnClickListener implements View.OnClickListener {
        private int position;
        private Object data;

        public MyOnClickListener(int position, Object data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, position, data);
        }
    }

    public class MyOnLongClickListener implements View.OnLongClickListener {

        private int position;
        private Object data;

        public MyOnLongClickListener(int position, Object data) {
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
        void onItemClick(View view, int position, Object data);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position, Object data);
    }
}

