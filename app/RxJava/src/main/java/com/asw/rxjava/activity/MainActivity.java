package com.asw.rxjava.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.asw.rxjava.R;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CountDownlatchTest();
    }

    public void btn_create(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaCreate.class));
    }

    public void btn_transform(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaTransform.class));
    }

    public void btn_filter(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaFilter.class));
    }

    public void btn_combining(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaCombining.class));
    }

    public void btn_error(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaError.class));
    }

    public void btn_ancillary(View view) {
        startActivity(new Intent(MainActivity.this, AncillaryActivity.class));
    }

    /**
     * 摘自：https://www.jianshu.com/p/1ec1009ebab7
     * 有1个driver和5个worker，需要满足以下两点要求：
     * 1.当driver完成了全部的工作之后才允许worker们开始工作
     * 2.当所有的worker都完成了自己的工作之后，driver主线程才能结束
     */
    private void CountDownlatchTest() {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        Log.d(TAG, "CountDownlatchTest: Driver is doing something...");
        Log.d(TAG, "CountDownlatchTest: Driver is Finished, start all workers ...");
        startSignal.countDown(); // Driver执行完毕，发出开始信号，使所有的worker线程开始执行

        try {
            doneSignal.await(); // 等待所有的worker线程执行结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Finished. ");
    }


    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await(); // 等待Driver线程执行完毕，获得开始信号
                Log.d(TAG, "run: Working now ...");
                doneSignal.countDown(); // 当前worker执行完毕，释放一个完成信号
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
