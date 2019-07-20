package com.example.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Created by mac on 2019/6/14.
 */
public class ChildView extends View implements OnTouchListener {


    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnTouchListener(this);
    }

    /**
     * 我
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean eat=false;//false:我不吃苹果；我吃苹果
        Log.d("bunny", "onTouchEvent: 我"+(eat?"吃苹果":"不吃苹果"));
        return eat? true : super.onTouchEvent(event);
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
        boolean eat=false;//false:老婆不吃苹果；老婆吃苹果
        Log.d("bunny", "onTouch: 老婆"+(eat?"吃苹果":"不吃苹果"));
        return eat;
    }


}
