package com.example.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by mac on 2019-08-18.
 * 主线程与子线程通信
 */
public class FourActivity extends AppCompatActivity {

    //定义主线程Handler
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("bunny", "handleMessage2: " + Thread.currentThread().getName());
            Message message = new Message();
            //向子线程发送消息
            threadHandler.sendMessageDelayed(message, 1000);
        }
    };

    //定义子线程Handler
    private Handler threadHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HandlerThread handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();

        threadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("bunny", "handleMessage1: " + Thread.currentThread().getName());
                Message message = new Message();
                //主线程发送消息
                handler.sendMessageDelayed(message, 1000);
            }
        };


        handler.sendEmptyMessage(1);
        handler.removeMessages(1);

    }


}
