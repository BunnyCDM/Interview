package com.example.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;

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
            //[main,5,main]
            AppLogger.d("handleMessage2: " + Thread.currentThread()
                    + "\n" + Thread.currentThread().getName());

//            try {
//                Thread.sleep(6000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            TextView textView = findViewById(R.id.tv);
//            textView.setText("handler thread!!");

            //向子线程发送消息
            Message message = new Message();
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
                //[handlerThread,5,main]
                AppLogger.d("handleMessage1: " + Thread.currentThread()
                        + "\n" + Thread.currentThread().getName());
                //向主线程发送消息
                //Message message = new Message();
                //handler.sendMessageDelayed(message, 1000);

//                try {
//                    Thread.sleep(6000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                TextView textView = findViewById(R.id.tv);
//                textView.setText("handler thread!!");
            }
        };

        //threadHandler.sendEmptyMessage(1);
        handler.sendEmptyMessage(1);

    }
}
