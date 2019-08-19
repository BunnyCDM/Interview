package com.example.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {


    private MyThread thread;

    private class MyThread extends Thread {

        public Handler handler;
        public Looper looper;

        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            Looper.prepare();//创建Looper
            looper = Looper.myLooper();
            handler = new Handler() { //默认情况下根据当前线程获取Handler对象
                @Override
                public void handleMessage(Message msg) {
                    Log.d("bunny", "handleMessage:2 " + Thread.currentThread());
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

//        try {
//            Thread.sleep(500);
//            thread.handler.sendEmptyMessage(1);//发送消息，会在子线程中处理该消息
//            handler.sendEmptyMessage(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        // TODO: 2019-08-18  会报空指针bug，因为thread.looper存在还没创建成功
        Handler handler_ = new Handler(thread.looper) {
            @Override
            public void handleMessage(Message msg) {
                Log.d("bunny", "handleMessage:3" + Thread.currentThread());
            }
        };
        handler_.sendEmptyMessage(1);

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("bunny", "handleMessage:1 " + Thread.currentThread());
        }
    };

}
