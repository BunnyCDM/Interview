package com.example.fragment.lifecycle;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fragment.R;

/**
 * Created by mac on 2019/2/26.
 * <p>
 * 演示Fragment的生命周期
 */

public class LifeCycleActivity extends Activity implements View.OnClickListener {

    private TextView tv_hotspot, tv_topline;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public void onClick(View v) {
        transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.hotspot:
                transaction.replace(R.id.contentlayout, new HotspotFragment());
                break;
            case R.id.topline:
                transaction.replace(R.id.contentlayout, new TopLineFragment());
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.d("tag", "------LifeCycleActivity------onCreate: ");

        tv_hotspot = (TextView) findViewById(R.id.hotspot);
        tv_topline = (TextView) findViewById(R.id.topline);

        tv_hotspot.setOnClickListener(this);
        tv_topline.setOnClickListener(this);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.contentlayout, new HotspotFragment());
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //用户被看到
        Log.d("tag", "------LifeCycleActivity------onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取用户焦点
        Log.d("tag", "------LifeCycleActivity------onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //失去用户焦点
        Log.d("tag", "------LifeCycleActivity------onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //完全被其他界面遮挡时
        Log.d("tag", "------LifeCycleActivity------onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //如果现在处于停止状态，重新被用户使用
        Log.d("tag", "------LifeCycleActivity------onRestart: ");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果直接点击返回
        Log.d("tag", "------LifeCycleActivity------onDestroy: ");
    }

}
