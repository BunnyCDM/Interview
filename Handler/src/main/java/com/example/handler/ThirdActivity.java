package com.example.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private TextView textView;


    //使用HandlerThread会避免SecondActivity中空指针问题，同时已解决多线程并发的问题
    private HandlerThread thread;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        textView.setText("handler thread");

        thread = new HandlerThread("handler thread");
        thread.start();

        handler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("bunny", "handleMessage: " + Thread.currentThread());
            }
        };

        handler.sendEmptyMessage(1);
    }
}
