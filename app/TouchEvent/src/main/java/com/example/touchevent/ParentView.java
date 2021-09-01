package com.example.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by mac on 2019/6/14.
 */
public class ParentView extends RelativeLayout {


    public ParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("bunny", "dispatchTouchEvent[ParentView]: 爸爸拿到了苹果，想分享给我吃");
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;//false:爸爸心里不想吃苹果；true:爸爸心里想吃苹果
        Log.d("bunny", "onInterceptTouchEvent[ParentView]: 爸爸" + (intercept ? "心里想吃苹果" : "心里不想吃苹果"));
        return intercept ? true : super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean eat = false;//false:爸爸不吃苹果；爸爸吃苹果
        Log.d("bunny", "onTouchEvent[ParentView]: 爸爸" + (eat ? "吃苹果" : "不吃苹果"));
        return eat ? true : super.onTouchEvent(event);
    }
}
