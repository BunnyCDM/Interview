package com.example.listview.reflash;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.listview.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mac on 2020-04-08.
 */
public class RefreshUpAndDownListView extends ListView implements AbsListView.OnScrollListener {


    public RefreshUpAndDownListView(Context context) {
        super(context);
    }

    public RefreshUpAndDownListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView(context);
        initFooterView(context);
        this.setOnScrollListener(this);
    }

    public RefreshUpAndDownListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RefreshUpAndDownListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private View headerView;
    private int headerViewHeight;

    private void initHeaderView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        headerView = inflater.inflate(R.layout.layout_header, null);

        measureHeaderView(headerView);

        headerViewHeight = headerView.getMeasuredHeight();
        topPaddingHeaderView(-headerViewHeight);

        this.addHeaderView(headerView);//添加都布局
    }

    private void measureHeaderView(View view) {
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

    private void topPaddingHeaderView(int topPadding) {
        headerView.setPadding(headerView.getPaddingLeft(), topPadding, headerView.getPaddingRight(), headerView.getPaddingBottom());
    }

    int scrollState;//当前滚动状态
    int firstVisibleItem; //当前页面第一个可见的item
    boolean isRemark;//标记，当前是在listview最顶端按下
    int startY;//按下时的Y值

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (firstVisibleItem == 0) {
                    isRemark = true;
                    startY = (int) ev.getY();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;

            case MotionEvent.ACTION_UP:
                if (state == RELESH) {
                    state = RELESHNG;
                    //加载数据
                    reflashViewByStatus();

                    if(listener!=null){
                        listener.onRefresh();
                    }

                } else if (state == PULL) {
                    state = NONE;
                    isRemark = false;
                    reflashViewByStatus();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    int state;//当前的状态
    final int NONE = 0;//正常状态
    final int PULL = 1;//提示下拉状态
    final int RELESH = 2;//提示释放状态
    final int RELESHNG = 3;//刷新状态

    //判断移动过程中的判断
    private void onMove(MotionEvent ev) {
        if (!isRemark) {
            return;
        }

        int tempY = (int) ev.getY();
        int space = tempY - startY;

        int topPadding = space - headerViewHeight;
        switch (state) {
            case NONE:
                if (space > 0) {
                    state = PULL;
                    reflashViewByStatus();
                }
                break;

            case PULL:
                topPaddingHeaderView(topPadding);
                if (space > headerViewHeight + 30
                        && scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    state = RELESH;
                    reflashViewByStatus();
                }
                break;

            case RELESH:
                topPaddingHeaderView(topPadding);
                if (space < headerViewHeight + 30) {
                    state = PULL;
                    reflashViewByStatus();
                } else if (space <= 0) {
                    state = NONE;
                    isRemark = false;
                    reflashViewByStatus();
                }
                break;

            case RELESHNG:

                break;
        }

    }

    //根据当前状态显示布局
    private void reflashViewByStatus() {
        TextView tip = (TextView) headerView.findViewById(R.id.tip);
        ImageView arrow = (ImageView) headerView.findViewById(R.id.arrow);
        ProgressBar progress = (ProgressBar) headerView.findViewById(R.id.progress);


        RotateAnimation animation = new RotateAnimation(0, 180, RotateAnimation.RELATIVE_TO_SELF
                , 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        RotateAnimation animation1 = new RotateAnimation(180, 0, RotateAnimation.RELATIVE_TO_SELF
                , 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation1.setDuration(500);
        animation1.setFillAfter(true);
        switch (state) {

            case NONE:
                arrow.clearAnimation();
                topPaddingHeaderView(-headerViewHeight);
                break;

            case PULL:
                arrow.setVisibility(VISIBLE);
                progress.setVisibility(GONE);
                tip.setText("下拉可以刷新");
                arrow.clearAnimation();
                arrow.setAnimation(animation1);
                break;

            case RELESH:
                tip.setText("松开可以刷新");
                arrow.setVisibility(VISIBLE);
                progress.setVisibility(GONE);
                arrow.clearAnimation();
                arrow.setAnimation(animation);
                break;

            case RELESHNG:
                topPaddingHeaderView(50);
                tip.setText("正在刷新");
                arrow.setVisibility(GONE);
                progress.setVisibility(VISIBLE);
                arrow.clearAnimation();
                break;

        }
    }


    //获取完数据
    public void reflashComplete() {
        state = NONE;
        isRemark = false;
        reflashViewByStatus();

        TextView lastT = (TextView) headerView.findViewById(R.id.lastupdate_time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        lastT.setText(time);
    }

    OnRefreshUpAndDownListener listener;

    public void setOnRefreshListener(OnRefreshUpAndDownListener listener) {
        this.listener = listener;
    }

    public interface OnRefreshUpAndDownListener {
        public void onRefresh();

        public void onLoadMore();
    }

    // TODO: 2020-04-08 以下是footview内容

    private View footerView;
    private int footerViewHeight;
    private ProgressBar progressBar;
    private TextView textView;
    private boolean isScrollToBottom; //判断是不是滑到了底部
    private boolean isLoadingMore; ////判断是不是"加载更多"

    private void initFooterView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footerView = inflater.inflate(R.layout.layout_footer, null);

        progressBar = footerView.findViewById(R.id.progress_bar);
        textView = footerView.findViewById(R.id.tv_load);

        measureFooterView(footerView);
        //footerView.measure(0, 0);//设置（0，0）以便系统测量footerView的宽高

        footerViewHeight = footerView.getMeasuredHeight();
        topPaddingFooterView(-footerViewHeight);
        //footerView.setPadding(0, -footerViewHeight, 0, 0);
        this.addFooterView(footerView);
    }

    private void measureFooterView(View view) {
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

    private void topPaddingFooterView(int topPadding) {
        footerView.setPadding(footerView.getPaddingLeft(), topPadding, footerView.getPaddingRight(), footerView.getPaddingBottom());
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.scrollState = scrollState;
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                footerView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());

                if (listener != null) {
                    listener.onLoadMore();
                }
            }
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }
    }

    public void loadMoreComplete() {
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        isLoadingMore = false;
    }

    public void noMoreComplete() {
        loadMoreComplete();
        noMoreComplete(null);
    }

    public void noMoreComplete(String msg) {
//        footerView.setPadding(0, 0, 0, 0);
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
}
