package com.example.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;

public class ThirdActivity extends AppCompatActivity {

    private TextView textView;


    //使用HandlerThread会避免SecondActivity中空指针问题，同时已解决多线程并发的问题
    private HandlerThread handlerThread;
    private Handler threadHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        textView.setText("handler thread");

        //[handler thread,5,main]
        handlerThread = new HandlerThread("handler thread");
        handlerThread.start();

        threadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                AppLogger.d("handleMessage: " + Thread.currentThread()
                        +"\n"+Thread.currentThread().getName());
                textView.setText("handler thread!!");
            }
        };

        threadHandler.sendEmptyMessage(1);
    }
}
