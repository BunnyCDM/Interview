package com.example.touchevent.shijianfenfa;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mac on 2019-09-08.
 */
public class MyView  extends View implements View.OnTouchListener {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("bunny", "dispatchTouchEvent[ChildView]: ");
        return super.dispatchTouchEvent(event);
    }

    /**
     * 我
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean eat = false;//false:我不吃苹果；true:我吃苹果
        Log.d("bunny", "onTouchEvent[ChildView]: 我" + (eat ? "吃苹果" : "不吃苹果"));
        return eat ? true : super.onTouchEvent(event);
    }

    /**
     * 老婆
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean eat = false;//false:老婆不吃苹果；true:老婆吃苹果
        Log.d("bunny", "onTouch[ChildView]: 老婆" + (eat ? "吃苹果" : "不吃苹果"));
        return eat;
    }
}
