package com.example.fragment.demo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.fragment.R;

/**
 * Created by mac on 2019/2/26.
 * <p>
 * 演示Fragment向Activity传值
 */

public class Demo2_1Activity extends AppCompatActivity implements ResourceFragment.MyListener {

    private TextView tv_show;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2_1);
        AppLogger.d("------Demo2_1Activity------onCreate: ");
        tv_show = findViewById(R.id.tv_show);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //如果现在处于停止状态，重新被用户使用
        AppLogger.d("------Demo2_1Activity------onRestart: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        //用户被看到
        AppLogger.d("------Demo2_1Activity------onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取用户焦点
        AppLogger.d("------Demo2_1Activity------onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //失去用户焦点
        AppLogger.d("------Demo2_1Activity------onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //完全被其他界面遮挡时
        AppLogger.d("------Demo2_1Activity------onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果直接点击返回
        AppLogger.d("------Demo2_1Activity------onDestroy: ");
    }

    @Override
    public void sendMessage(String str) {
        if (!TextUtils.isEmpty(str)) {
            tv_show.setText(str);
        }
    }
}
