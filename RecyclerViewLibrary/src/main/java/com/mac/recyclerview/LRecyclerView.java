package com.mac.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mac.interfaces.ILoadMoreFooter;
import com.mac.interfaces.IRefreshHeader;
import com.mac.interfaces.OnLoadMoreListener;
import com.mac.interfaces.OnNetWorkErrorListener;
import com.mac.interfaces.OnRefreshListener;
import com.mac.view.ArrowRefreshHeader;
import com.mac.view.LoadingFooter;

/**
 * Created by mac on 2019-09-12.
 */
public class LRecyclerView extends RecyclerView {

    private int mTouchSlop;
    private boolean mPullRefreshEnabled = true;
    private boolean mLoadMoreEnabled = true;
    private boolean isRegisterDataObserver;
    private IRefreshHeader mIRefreshHeader;
    private ILoadMoreFooter mILoadMoreFooter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private View mFootView;
    private View mEmptyView;
    private final RecyclerView.AdapterDataObserver mDataObserver = new DataObserver();
    private int mPageSize = 10; //一次网络请求默认数量
    private float startY;
    private float startX;
    private boolean mIsVpDragger;
    private float mLastY = -1;
    private int mActivePointerId;
    private float sumOffSet;
    private static final float DRAG_RATE = 2.0f;
    private boolean mRefreshing = false;//是否正在下拉刷新
    private boolean mLoadingData = false;//是否正在加载数据
    private AppBarStateChangeListener.State appbarState = AppBarStateChangeListener.State.EXPANDED;
    private OnRefreshListener mRefreshListener;
    private OnLoadMoreListener mLoadMoreListener;
    private LScrollListener mLScrollListener;


    /**
     * 当前RecyclerView类型
     */
    protected LayoutManagerType layoutManagerType;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisibleItemPosition;

    /**
     * 最后一个的位置
     */
    private int[] lastPositions;

    /**
     * X轴移动的实际距离（最左侧为0）
     */
    private int mScrolledXDistance = 0;

    /**
     * Y轴移动的实际距离（最顶部为0）
     */
    private int mScrolledYDistance = 0;

    /**
     * 是否需要监听控制
     */
    private boolean mIsScrollDown = true;

    /**
     * 触发在上下滑动监听器的容差距离
     */
    private static final int HIDE_THRESHOLD = 20;

    /**
     * 滑动的距离
     */
    private int mDistance = 0;

    /**
     * 当前滑动的状态
     */
    private int currentScrollState = 0;

    private boolean isNoMore = false;
    private boolean isCritical = false;

    public LRecyclerView(Context context) {
        super(context);
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * set view when no content item
     *
     * @param emptyView visiable view when items is empty
     */
    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
        mDataObserver.onChanged();
    }

    /**
     * @param pageSize 一页加载的数量
     */
    public void refreshComplete(int pageSize) {
        this.mPageSize = pageSize;
        if (mRefreshing) {
            isNoMore = false;
            mRefreshing = false;
            mIRefreshHeader.refreshComplete();

            if (mLRecyclerViewAdapter.getInnerAdapter().getItemCount() < pageSize) {
                mFootView.setVisibility(GONE);
                mLRecyclerViewAdapter.removeFooterView();
            } else {
                if (mLRecyclerViewAdapter.getFooterViewsCount() == 0) {
                    mLRecyclerViewAdapter.addFooterView(mFootView);
                }
            }
        } else if (mLoadingData) {
            mLoadingData = false;
            mILoadMoreFooter.onComplete();
        }
        //visibleItemCount 10 lastVisibleItemPosition 9 totalItemCount 11 isNoMore false mRefreshing false
        //处理特殊情况 最后一行显示出来了加载更多的view的一部分
        if (mLRecyclerViewAdapter.getInnerAdapter().getItemCount() == mPageSize) {
            isCritical = true;
        } else {
            isCritical = false;
        }
    }

    /**
     * @param pageSize 一页加载的数量
     * @param total    总数
     */
    public void refreshComplete(int pageSize, int total) {
        this.mPageSize = pageSize;
        if (mRefreshing) {
            mRefreshing = false;
            mIRefreshHeader.refreshComplete();

            if (mLRecyclerViewAdapter.getInnerAdapter().getItemCount() < pageSize) {
                mFootView.setVisibility(GONE);
                mLRecyclerViewAdapter.removeFooterView();
            } else {
                if (mLRecyclerViewAdapter.getFooterViewsCount() == 0) {
                    mLRecyclerViewAdapter.addFooterView(mFootView);
                }
            }
        } else if (mLoadingData) {
            mLoadingData = false;
            mILoadMoreFooter.onComplete();
        }
        if (pageSize < total) {
            isNoMore = false;
        }
        //处理特殊情况 最后一行显示出来了加载更多的view的一部分
        if (mLRecyclerViewAdapter.getInnerAdapter().getItemCount() == mPageSize) {
            isCritical = true;
        } else {
            isCritical = false;
        }
    }

    /**
     * 此方法主要是为了满足数据不满一屏幕或者数据小于pageSize的情况下，是否显示footview
     * 在分页情况下使用refreshComplete(int pageSize, int total, boolean false)就相当于refreshComplete(int pageSize, int total)
     *
     * @param pageSize       一页加载的数量
     * @param total          总数
     * @param isShowFootView 是否需要显示footview（前提条件是：getItemCount() < pageSize）
     */
    public void refreshComplete(int pageSize, int total, boolean isShowFootView) {
        this.mPageSize = pageSize;
        if (mRefreshing) {
            mRefreshing = false;
            mIRefreshHeader.refreshComplete();
            if (isShowFootView) {
                mFootView.setVisibility(VISIBLE);
            } else {
                if (mLRecyclerViewAdapter.getInnerAdapter().getItemCount() < pageSize) {
                    mFootView.setVisibility(GONE);
                    mLRecyclerViewAdapter.removeFooterView();
                } else {
                    if (mLRecyclerViewAdapter.getFooterViewsCount() == 0) {
                        mLRecyclerViewAdapter.addFooterView(mFootView);
                    }
                }
            }
        } else if (mLoadingData) {
            mLoadingData = false;
            mILoadMoreFooter.onComplete();
        }
        if (pageSize < total) {
            isNoMore = false;
        }
        //处理特殊情况 最后一行显示出来了加载更多的view的一部分
        if (mLRecyclerViewAdapter.getInnerAdapter().getItemCount() == mPageSize) {
            isCritical = true;
        } else {
            isCritical = false;
        }
    }

    /**
     * 设置是否已加载全部
     *
     * @param noMore
     */
    public void setNoMore(boolean noMore) {
        mLoadingData = false;
        isNoMore = noMore;
        if (isNoMore) {
            mILoadMoreFooter.onNoMore();
            mFootView.setVisibility(VISIBLE);
        } else {
            mILoadMoreFooter.onComplete();
        }
    }

    public void setPullRefreshEnabled(boolean enabled) {
        mPullRefreshEnabled = enabled;
    }

    /**
     * 到底加载是否可用
     */
    public void setLoadMoreEnabled(boolean enabled) {
        if (mLRecyclerViewAdapter == null) {
            throw new NullPointerException("LRecyclerViewAdapter cannot be null, please make sure the variable mLRecyclerViewAdapter have been initialized.");
        }
        mLoadMoreEnabled = enabled;
        if (!enabled) {
            mLRecyclerViewAdapter.removeFooterView();
        }
    }

    public void setRefreshProgressStyle(int style) {
        if (mIRefreshHeader instanceof ArrowRefreshHeader) {
            ((ArrowRefreshHeader) mIRefreshHeader).setProgressStyle(style);
        }
    }

    public void setArrowImageView(int resId) {
        if (mIRefreshHeader instanceof ArrowRefreshHeader) {
            ((ArrowRefreshHeader) mIRefreshHeader).setArrowImageView(resId);
        }
    }

    public void setLoadingMoreProgressStyle(int style) {
        if (mILoadMoreFooter instanceof LoadingFooter) {
            ((LoadingFooter) mILoadMoreFooter).setProgressStyle(style);
        }

    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mLoadMoreListener = listener;
    }

    public void setOnNetWorkErrorListener(final OnNetWorkErrorListener listener) {
        mILoadMoreFooter.setNetworkErrorViewClickListener(listener);
    }

    /**
     * 请使用自定义LoadingFooter的方式实现
     *
     * @param loading
     * @param noMore
     * @param noNetWork
     */
    @Deprecated
    public void setFooterViewHint(String loading, String noMore, String noNetWork) {
        if (mILoadMoreFooter instanceof LoadingFooter) {
            LoadingFooter loadingFooter = ((LoadingFooter) mILoadMoreFooter);
            loadingFooter.setLoadingHint(loading);
            loadingFooter.setNoMoreHint(noMore);
            loadingFooter.setNoNetWorkHint(noNetWork);
        }
    }

    /**
     * 本方法不再推荐使用，请使用自定义LoadingFooter的方式实现
     * 设置Footer文字颜色
     *
     * @param indicatorColor
     * @param hintColor
     * @param backgroundColor
     */
    @Deprecated
    public void setFooterViewColor(int indicatorColor, int hintColor, int backgroundColor) {
        if (mILoadMoreFooter instanceof LoadingFooter) {
            LoadingFooter loadingFooter = ((LoadingFooter) mILoadMoreFooter);
            loadingFooter.setIndicatorColor(ContextCompat.getColor(getContext(), indicatorColor));
            loadingFooter.setHintTextColor(hintColor);
            loadingFooter.setViewBackgroundColor(backgroundColor);
        }
    }

    /**
     * 设置颜色
     *
     * @param indicatorColor  Only call the method setRefreshProgressStyle(int style) to take effect
     * @param hintColor
     * @param backgroundColor
     */
    public void setHeaderViewColor(int indicatorColor, int hintColor, int backgroundColor) {
        if (mIRefreshHeader instanceof ArrowRefreshHeader) {
            ArrowRefreshHeader arrowRefreshHeader = ((ArrowRefreshHeader) mIRefreshHeader);
            arrowRefreshHeader.setIndicatorColor(ContextCompat.getColor(getContext(), indicatorColor));
            arrowRefreshHeader.setHintTextColor(hintColor);
            arrowRefreshHeader.setViewBackgroundColor(backgroundColor);
        }

    }

    public void refresh() {
        if (mIRefreshHeader.getVisibleHeight() > 0 || mRefreshing) {// if RefreshHeader is Refreshing, return
            return;
        }
        if (mPullRefreshEnabled && mRefreshListener != null) {
            mIRefreshHeader.onRefreshing();
            int offSet = mIRefreshHeader.getHeaderView().getMeasuredHeight();
            mIRefreshHeader.onMove(offSet, offSet);
            mRefreshing = true;

            mFootView.setVisibility(GONE);
            mRefreshListener.onRefresh();
        }
    }

    public void forceToRefresh() {
        if (mLoadingData) {
            return;
        }
        refresh();
    }

    private void init() {
        mTouchSlop = ViewConfiguration.get(getContext().getApplicationContext()).getScaledTouchSlop();
        if (mPullRefreshEnabled) {
            setRefreshHeader(new ArrowRefreshHeader(getContext().getApplicationContext()));
        }

        if (mLoadMoreEnabled) {
            setLoadMoreFooter(new LoadingFooter(getContext().getApplicationContext()), false);
        }
    }

    /**
     * 设置自定义的RefreshHeader
     * 注意：setRefreshHeader方法必须在setAdapter方法之前调用才能生效
     */
    private void setRefreshHeader(IRefreshHeader refreshHeader) {
        if (isRegisterDataObserver) {
            throw new RuntimeException("setRefreshHeader must been invoked before setting the adapter.");
        }
        this.mIRefreshHeader = refreshHeader;
    }

    /**
     * 设置自定义的footerview
     *
     * @param loadMoreFooter
     * @param isCustom       是否自定义footview
     */
    private void setLoadMoreFooter(ILoadMoreFooter loadMoreFooter, boolean isCustom) {
        this.mILoadMoreFooter = loadMoreFooter;
        if (isCustom) {
            if (null != mLRecyclerViewAdapter && mLRecyclerViewAdapter.getFooterViewsCount() > 0) {
                mLRecyclerViewAdapter.removeFooterView();
            }
        }
        mFootView = loadMoreFooter.getFootView();
        mFootView.setVisibility(VISIBLE);

        //wxm:mFootView inflate的时候没有以RecyclerView为parent，所以要设置LayoutParams
        ViewGroup.LayoutParams layoutParams = mFootView.getLayoutParams();
        if (layoutParams != null) {
            mFootView.setLayoutParams(new LayoutParams(layoutParams));
        } else {
            mFootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }

        if (isCustom) {
            if (mLoadMoreEnabled && mLRecyclerViewAdapter.getFooterViewsCount() == 0) {
                mLRecyclerViewAdapter.addFooterView(mFootView);
            }
        }
    }


    @Override
    public void setAdapter(Adapter adapter) {
        //super.setAdapter(adapter);
        if (mLRecyclerViewAdapter != null && mDataObserver != null && isRegisterDataObserver) {
            mLRecyclerViewAdapter.getInnerAdapter().unregisterAdapterDataObserver(mDataObserver);
        }

        mLRecyclerViewAdapter = (LRecyclerViewAdapter) adapter;
        super.setAdapter(mLRecyclerViewAdapter);

        mLRecyclerViewAdapter.getInnerAdapter().registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
        isRegisterDataObserver = true;

        mLRecyclerViewAdapter.setRefreshHeader(mIRefreshHeader);

        //fix bug: https://github.com/jdsjlzx/LRecyclerView/issues/115
        if (mLoadMoreEnabled && mLRecyclerViewAdapter.getFooterViewsCount() == 0) {
            mLRecyclerViewAdapter.addFooterView(mFootView);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mLRecyclerViewAdapter != null && mDataObserver != null && isRegisterDataObserver) {
            mLRecyclerViewAdapter.getInnerAdapter().unregisterAdapterDataObserver(mDataObserver);
            isRegisterDataObserver = false;
        }
    }

    /**
     * 解决嵌套RecyclerView滑动冲突问题
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 记录手指按下的位置
                startY = ev.getY();
                startX = ev.getX();
                // 初始化标记
                mIsVpDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果viewpager正在拖拽中，那么不拦截它的事件，直接return false；
                if (mIsVpDragger) {
                    return false;
                }

                // 获取当前手指位置
                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                // 如果X轴位移大于Y轴位移，那么将事件交给viewPager处理。
                if (distanceX > mTouchSlop && distanceX > distanceY) {
                    mIsVpDragger = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 初始化标记
                mIsVpDragger = false;
                break;
        }
        // 如果是Y轴位移大于X轴，事件交给swipeRefreshLayout处理。
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getY();
            mActivePointerId = ev.getPointerId(0);
            sumOffSet = 0;
        }
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getY();
                mActivePointerId = ev.getPointerId(0);
                sumOffSet = 0;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                final int index = ev.getActionIndex();
                mActivePointerId = ev.getPointerId(index);
                mLastY = (int) ev.getY(index);
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex == -1) {
                    pointerIndex = 0;
                    mActivePointerId = ev.getPointerId(pointerIndex);
                }
                final int moveY = (int) ev.getY(pointerIndex);
                final float deltaY = (moveY - mLastY) / DRAG_RATE;
                mLastY = moveY;
                sumOffSet += deltaY;
                if (isOnTop() && mPullRefreshEnabled && !mRefreshing && (appbarState == AppBarStateChangeListener.State.EXPANDED)) {
                    if (mIRefreshHeader.getType() == IRefreshHeader.TYPE_HEADER_NORMAL) {
                        mIRefreshHeader.onMove(deltaY, sumOffSet);
                    } else if (mIRefreshHeader.getType() == IRefreshHeader.TYPE_HEADER_MATERIAL) {
                        if (deltaY > 0 && !canScrollVertically(-1) || deltaY < 0 && !canScrollVertically(1)) { //判断无法下拉和无法上拉（item过少的情况）
                            overScrollBy(0, (int) -deltaY, 0, 0, 0, 0, 0, (int) sumOffSet, true);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastY = -1; // reset
                mActivePointerId = -1;
                if (isOnTop() && mPullRefreshEnabled && !mRefreshing/*&& appbarState == AppBarStateChangeListener.State.EXPANDED*/) {
                    if (mIRefreshHeader != null && mIRefreshHeader.onRelease()) {
                        if (mRefreshListener != null) {
                            mRefreshing = true;
                            mFootView.setVisibility(GONE);
                            mRefreshListener.onRefresh();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isOnTop() {
        return mPullRefreshEnabled && (mIRefreshHeader.getHeaderView().getParent() != null);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                   int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (deltaY != 0 && isTouchEvent) {
            mIRefreshHeader.onMove(deltaY, sumOffSet);
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        int firstVisibleItemPosition = 0;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();

        if (layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LayoutManagerType.LinearLayout;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = LayoutManagerType.GridLayout;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = LayoutManagerType.StaggeredGridLayout;
            } else {
                throw new RuntimeException(
                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (layoutManagerType) {
            case LinearLayout:
                firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GridLayout:
                firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case StaggeredGridLayout:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(lastPositions);
                firstVisibleItemPosition = findMax(lastPositions);
                break;
            default:
                break;
        }

        // 根据类型来计算出第一个可见的item的位置，由此判断是否触发到底部的监听器
        // 计算并判断当前是向上滑动还是向下滑动
        calculateScrollUpOrDown(firstVisibleItemPosition, dy);
        // 移动距离超过一定的范围，我们监听就没有啥实际的意义了
        mScrolledXDistance += dx;
        mScrolledYDistance += dy;
        mScrolledXDistance = (mScrolledXDistance < 0) ? 0 : mScrolledXDistance;
        mScrolledYDistance = (mScrolledYDistance < 0) ? 0 : mScrolledYDistance;
        if (mIsScrollDown && (dy == 0)) {
            mScrolledYDistance = 0;
        }
        //Be careful in here
        if (null != mLScrollListener) {
            mLScrollListener.onScrolled(mScrolledXDistance, mScrolledYDistance);
        }

        if (mLoadMoreListener != null && mLoadMoreEnabled) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            if (visibleItemCount > 0
                    && lastVisibleItemPosition >= totalItemCount - 1
                    && (isCritical ? totalItemCount >= visibleItemCount : totalItemCount > visibleItemCount)
                    && !isNoMore
                    && !mRefreshing) {

                mFootView.setVisibility(View.VISIBLE);
                if (!mLoadingData) {
                    mLoadingData = true;
                    mILoadMoreFooter.onLoading();
                    mLoadMoreListener.onLoadMore();
                }

            }

        }
        if (isOnTop() && dy > 0 && mIRefreshHeader.getType() == IRefreshHeader.TYPE_HEADER_MATERIAL && !mRefreshing && (appbarState
                == AppBarStateChangeListener.State.EXPANDED)) {
            mIRefreshHeader.onMove(dy, mScrolledYDistance);
        }
    }

    /**
     * 计算当前是向上滑动还是向下滑动
     */
    private void calculateScrollUpOrDown(int firstVisibleItemPosition, int dy) {
        if (null != mLScrollListener) {
            if (firstVisibleItemPosition == 0) {
                if (!mIsScrollDown) {
                    mIsScrollDown = true;
                    mLScrollListener.onScrollDown();
                }
            } else {
                if (mDistance > HIDE_THRESHOLD && mIsScrollDown) {
                    mIsScrollDown = false;
                    mLScrollListener.onScrollUp();
                    mDistance = 0;
                } else if (mDistance < -HIDE_THRESHOLD && !mIsScrollDown) {
                    mIsScrollDown = true;
                    mLScrollListener.onScrollDown();
                    mDistance = 0;
                }
            }
        }

        if ((mIsScrollDown && dy > 0) || (!mIsScrollDown && dy < 0)) {
            mDistance += dy;
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        currentScrollState = state;

        if (mLScrollListener != null) {
            mLScrollListener.onScrollStateChanged(state);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //解决LRecyclerView与CollapsingToolbarLayout滑动冲突的问题
        AppBarLayout appBarLayout = null;
        ViewParent p = getParent();
        while (p != null) {
            if (p instanceof CoordinatorLayout) {
                break;
            }
            p = p.getParent();
        }
        if (p instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) p;
            final int childCount = coordinatorLayout.getChildCount();
            for (int i = childCount - 1; i >= 0; i--) {
                final View child = coordinatorLayout.getChildAt(i);
                if (child instanceof AppBarLayout) {
                    appBarLayout = (AppBarLayout) child;
                    break;
                }
            }
            if (appBarLayout != null) {
                appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
                    @Override
                    public void onStateChanged(AppBarLayout appBarLayout, State state) {
                        appbarState = state;
                    }
                });
            }
        }
    }

    public enum LayoutManagerType {
        LinearLayout,
        StaggeredGridLayout,
        GridLayout
    }

    public void setLScrollListener(LScrollListener listener) {
        mLScrollListener = listener;
    }

    public interface LScrollListener {

        void onScrollUp();//scroll down to up

        void onScrollDown();//scroll from up to down

        void onScrolled(int distanceX, int distanceY);// moving state,you can get the move distance

        void onScrollStateChanged(int state);
    }

    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter instanceof LRecyclerViewAdapter) {
                LRecyclerViewAdapter lRecyclerViewAdapter = (LRecyclerViewAdapter) adapter;
                if (lRecyclerViewAdapter.getInnerAdapter() != null && mEmptyView != null) {
                    int count = lRecyclerViewAdapter.getInnerAdapter().getItemCount();
                    if (count == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                        LRecyclerView.this.setVisibility(View.GONE);
                    } else {
                        mEmptyView.setVisibility(View.GONE);
                        LRecyclerView.this.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                if (adapter != null && mEmptyView != null) {
                    if (adapter.getItemCount() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                        LRecyclerView.this.setVisibility(View.GONE);
                    } else {
                        mEmptyView.setVisibility(View.GONE);
                        LRecyclerView.this.setVisibility(View.VISIBLE);
                    }
                }
            }

            if (mLRecyclerViewAdapter != null) {
                mLRecyclerViewAdapter.notifyDataSetChanged();
                if (mLRecyclerViewAdapter.getInnerAdapter().getItemCount() < mPageSize) {
                    mFootView.setVisibility(GONE);
                }
            }

        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mLRecyclerViewAdapter.notifyItemRangeChanged(positionStart + mLRecyclerViewAdapter.getHeaderViewsCount() + 1, itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mLRecyclerViewAdapter.notifyItemRangeInserted(positionStart + mLRecyclerViewAdapter.getHeaderViewsCount() + 1, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mLRecyclerViewAdapter.notifyItemRangeRemoved(positionStart + mLRecyclerViewAdapter.getHeaderViewsCount() + 1, itemCount);
            if (mLRecyclerViewAdapter.getInnerAdapter().getItemCount() < mPageSize) {
                mFootView.setVisibility(GONE);
            }

        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            int headerViewsCountCount = mLRecyclerViewAdapter.getHeaderViewsCount();
            mLRecyclerViewAdapter.notifyItemRangeChanged(fromPosition + headerViewsCountCount + 1, toPosition + headerViewsCountCount + 1 + itemCount);
        }

    }

}
