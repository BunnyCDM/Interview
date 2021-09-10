package com.asw.rxjava.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;


import com.asw.rxjava.R;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 2017/4/11.
 */

public class RxJavaCombining extends Activity {

    private final static String TAG = RxJavaCombining.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combining);

        init();

//        fromCallable();

    }

    private void fromCallable() {
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                Log.d(TAG, "call: 开始");
                CountDownLatch countDownLatch1 = new CountDownLatch(1);
                handler.post(new TestLeftRightChannelRun(countDownLatch1));
                countDownLatch1.await();
                Log.d(TAG, "call: 结束");

                return true;
            }
        }).compose(new Observable.Transformer<Object, Object>() {
            @Override
            public Observable<Object> call(Observable<Object> objectObservable) {
                Log.d(TAG, "call: 55");
                return objectObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.d(TAG, "call: 66");

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: 77");
            }
        });
    }


    private HandlerThread handlerThread;
    private Handler handler;

    private void init() {
        handlerThread = new HandlerThread("devTest");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    public class TestLeftRightChannelRun implements Runnable {

        private CountDownLatch countDownLatch;

        public TestLeftRightChannelRun(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            try {
                Log.d(TAG, "run: 1111");
                Thread.sleep(2000);
                Log.d(TAG, "run: 22222");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "run: 3333");
            countDownLatch.countDown();
        }

    }

}
