package com.example.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.baselibrary.utils.log.AppLogger;

/**
 * 默认整个应用是通过ActivityThread创建的，
 * 在ActivityThread中会默认创建一个线程main，
 * 在创建main过程中也会创建一个looper，也会创建一个message
 */
public class SecondActivity extends AppCompatActivity {


    private MyThread thread;

    private class MyThread extends Thread {

        public Handler handler;
        public Looper looper;

        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            AppLogger.d("run1: " + Thread.currentThread().getName());
            //以下handler就创建成功了，并和当前线程关联
            Looper.prepare();//创建Looper
            looper = Looper.myLooper();
            handler = new Handler() { //默认情况下根据当前线程获取Handler对象
                @Override
                public void handleMessage(Message msg) {
                    //Thread[Thread-2,5,main]
                    AppLogger.d("handleMessage:2 " + Thread.currentThread());
                }
            };
            Looper.loop();//循环处理消息
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new MyThread();
        thread.start();

        test1();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AppLogger.d("handleMessage:1 " + Thread.currentThread());
        }
    };

    private void test1() {
        try {
            Thread.sleep(500);
            thread.handler.sendEmptyMessage(1);//发送消息，会在子线程中处理该消息
            handler.sendEmptyMessage(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void test2() {
        // TODO: 2019-08-18  会报空指针bug，因为thread.looper存在还没创建成功，如何避免在thirdactivity中
        Handler handler_ = new Handler(thread.looper) {
            @Override
            public void handleMessage(Message msg) {
                AppLogger.d("handleMessage:3" + Thread.currentThread());
            }
        };
        handler_.sendEmptyMessage(1);
    }

}
