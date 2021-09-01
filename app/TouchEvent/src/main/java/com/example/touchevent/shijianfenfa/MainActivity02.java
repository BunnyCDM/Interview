package com.example.touchevent.shijianfenfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.example.touchevent.R;


/**
 * 一家人当中角色有：妈妈 爸爸 我 老婆
 * <p>
 * MainActivity：妈妈
 * ParentView：爸爸
 * ChildView：我
 */
public class MainActivity02 extends AppCompatActivity {

    private final static String TAG = "bunny";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);
    }


    /**
     * 点击屏幕首先会调用屏幕分发触摸事件
     * <p>
     * 当手指按下的时候会产生一个事件，这个事件相当于一个苹果，
     * 其实Android这个事件相当于一个icp吧？
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent[MainActivity]: 妈妈拿到一个苹果，想分享给爸爸");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //因为布局文件中既没有爸爸也没有我，所以妈妈会自己处理事件
        boolean eat = false;//false:妈妈不吃苹果，扔了；妈妈吃苹果了
        Log.d(TAG, "onTouchEvent[MainActivity]: 妈妈" + (eat ? "吃苹果了" : "没有吃苹果，扔了"));
        return eat ? true : super.onTouchEvent(event);
        //super.onTouchEvent(event);=false,默认等于false
    }


}
