package com.example.touchevent.shijianfenfa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by mac on 2019-09-08.
 */
public class MyViewGroup extends FrameLayout {

    public MyViewGroup(@NonNull Context context) {
        super(context);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
