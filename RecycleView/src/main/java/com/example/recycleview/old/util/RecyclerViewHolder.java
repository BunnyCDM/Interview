package com.example.recycleview.old.util;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mac on 2019/2/12.
 * <p>
 * 封装RecyclerView.ViewHolder
 */
public final class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<View>();
        mConvertView = itemView;
    }

    public static RecyclerViewHolder get(ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId
                , parent, false);
        return new RecyclerViewHolder(view);
    }


    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过viewId获取控件ID
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);

        }
        return (T) view;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public RecyclerViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    /**
     * 设置Image的值
     *
     * @param viewId
     * @param resId
     * @return
     */
    public RecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

}
