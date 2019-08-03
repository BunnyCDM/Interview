package com.example.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("SecondActivity", "handleMessage:1 " + Thread.currentThread());
        }
    };


    class MyThread extends Thread {

        public Handler handler;
        public Looper looper;

        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            Looper.prepare();
            looper = Looper.myLooper();
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.d("SecondActivity", "handleMessage:2 " + Thread.currentThread());
                }
            };
            Looper.loop();
        }
    }


    private MyThread thread;
    private Handler handler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new MyThread();
        thread.start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        handler.sendEmptyMessage(1);


        handler2 = new Handler(thread.looper) {
            @Override
            public void handleMessage(Message msg) {
                Log.d("SecondActivity", "handleMessage:3" + Thread.currentThread());
            }
        };

        handler2.sendEmptyMessage(1);

    }
}
