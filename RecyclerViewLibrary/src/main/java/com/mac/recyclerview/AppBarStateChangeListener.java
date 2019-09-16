package com.mac.recyclerview;

import android.support.design.widget.AppBarLayout;

/**
 * Created by mac on 2019-09-14.
 */
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {


    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);

    public enum State {
        EXPANDED, //扩大
        COLLAPSED, //崩溃
        IDLE //闲置
    }

    private State mCurrentState = State.IDLE;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

}
