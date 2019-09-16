package com.mac.interfaces;

import android.view.View;

/**
 * Created by mac on 2019-09-12.
 */
public interface IRefreshHeader {

    int STATE_NORMAL = 0;//正常状态
    int STATE_RELEASE_TO_REFRESH = 1;//释放刷新
    int STATE_REFRESHING = 2;//刷新中
    int STATE_DONE = 3;//完成

    int TYPE_HEADER_NORMAL = 0;
    int TYPE_HEADER_MATERIAL = 1;

    void onReset();

    void onPrepare();//处于可以刷新的状态，已经过了指定距离

    void onRefreshing();//正在刷新

    void onMove(float offSet,float sunOffSet);//下拉移动

    boolean onRelease();//下拉松开

    void refreshComplete();//下拉刷新完成

    View getHeaderView();//获取HeaderView

    int getVisibleHeight();//获取Header的显示高度

    int getVisibleWidth();//获取Header的显示宽度,横向滑动时使用

    int getType();//获取Header的类型


}
