package com.mac.interfaces;

import android.view.View;

/**
 * Created by mac on 2019-09-12.
 */
public interface ILoadMoreFooter {


    enum State {
        Normal,//正常
        ManualLoadMore,//手动点击加载
        NoMore,//加载到最低了
        Loading,//加载中
        NetWorkError//网络异常
    }

    void onReset();//状态回调，回复初始设置

    void onLoading();//状态回调，加载中

    void onComplete();//状态回调，加载完成

    void onNoMore();//状态回调，已全部加载完成

    View getFootView();//加载更多的View

    void setNetworkErrorViewClickListener(OnNetWorkErrorListener listener);

    void setOnClickLoadMoreListener(OnLoadMoreListener listener);


}
