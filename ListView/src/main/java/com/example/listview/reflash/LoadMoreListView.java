package com.example.listview.reflash;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.listview.R;

/**
 * Created by mac on 2020-04-06.
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    /**
     * 底部加载更多部分
     */
    private View footerView; //底部布局文件
    private int footerViewHeight; //顶部布局文件的高的
    private ProgressBar progressBar;
    private TextView textView;
    private boolean isScrollToBottom; //判断是不是滑到了底部
    private boolean isLoadingMore; ////判断是不是"加载更多"


    public LoadMoreListView(Context context) {
        super(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView(context);
        this.setOnScrollListener(this);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 初始化底部view
     *
     * @param context
     */
    private void initFooterView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footerView = inflater.inflate(R.layout.layout_footer, null);

        progressBar = footerView.findViewById(R.id.progress_bar);
        textView = footerView.findViewById(R.id.tv_load);

        measureView(footerView);
        //footerView.measure(0, 0);//设置（0，0）以便系统测量footerView的宽高

        footerViewHeight = footerView.getMeasuredHeight();
        topPadding(-footerViewHeight);
        //footerView.setPadding(0, -footerViewHeight, 0, 0);
        this.addFooterView(footerView);
    }

    /**
     * 通知父布局占用的高、宽
     *
     * @param view
     */
    private void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int height;
        int tempHeight = p.height;
        if (tempHeight > 0) {
            height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);
    }

    /**
     * 设置footer布局上边距
     *
     * @param topPadding
     */
    private void topPadding(int topPadding) {
        footerView.setPadding(footerView.getPaddingLeft(), topPadding, footerView.getPaddingRight(), footerView.getPaddingBottom());
    }

    /**
     * 监听listview滚动的状态变化，如果滑到了底部，就“加载更多..."
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        AppLogger.d("onScrollStateChanged: scrollState=" + scrollState);
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                footerView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());

                if (onRefreshListener != null) {
                    onRefreshListener.onLoadingMore();
                }
            }
        }
    }

    /**
     * 监听listview滚动的状态变化，判断当前是不是滑到了底部
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        AppLogger.d("onScroll: firstVisibleItem=" + firstVisibleItem
                + " visibleItemCount=" + visibleItemCount + " totalItemCount=" + totalItemCount);
        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }
    }

    /**
     * 为外界提供的方法，当Activity中的加载更多数据加载完后，就调用这个方法来隐藏底部的footerView
     */
    public void loadMoreComplete() {
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        isLoadingMore = false;
    }

    public void noMoreComplete() {
        loadMoreComplete();
        noMoreComplete(null);
    }

    public void noMoreComplete(String msg) {
        if (TextUtils.isEmpty(msg)) {
            msg = "没有更多！";
        }
        Snackbar snackbar = Snackbar.make(footerView, msg, Snackbar.LENGTH_SHORT);
        TextView tv = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
        tv.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        snackbar.show();
    }

    private OnRefreshListener onRefreshListener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.onRefreshListener = listener;
    }

    /**
     * 设置接口，供外界实现，监听listview的刷新和加载更多的状态
     */
    public interface OnRefreshListener {
        void onLoadingMore();//上拉加载更多
    }

}
