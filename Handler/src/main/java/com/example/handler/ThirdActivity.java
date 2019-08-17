package com.example.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private TextView textView;

    private HandlerThread thread;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.tv);

        thread=new HandlerThread("bunny");
        thread.start();

        handler=new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("ThirdActivity", "handleMessage: "+Thread.currentThread());
            }
        };

        handler.sendEmptyMessage(1);
    }
}
