package com.mac.util;

import android.support.v7.widget.RecyclerView;

import com.mac.recyclerview.LRecyclerViewAdapter;

/**
 * @author Lzx
 * @created 2016/9/30 10:36
 * <p>
 * RecyclerView设置Header/Footer所用到的工具类
 */
public class RecyclerViewUtils {

    /**
     * 请使用本方法替代RecyclerView.ViewHolder的getLayoutPosition()方法
     *
     * @param recyclerView
     * @param holder
     * @return
     */
    public static int getLayoutPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof LRecyclerViewAdapter) {

            int headerViewCounter = ((LRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getLayoutPosition() - (headerViewCounter + 1);
            }
        }

        return holder.getLayoutPosition();
    }

}
