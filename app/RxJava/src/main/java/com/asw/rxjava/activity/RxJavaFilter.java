package com.asw.rxjava.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by mac on 2017/4/11.
 * 使用Operators
 * map 和 flatMap
 */

public class RxJavaFilter extends Activity {

    private final static String TAG = RxJavaFilter.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        filter();

        lift();

    }

    private void filter(){ //过滤只保留偶数
        Observable.just(1, 2, 3, 4)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.v(TAG, "data=" + integer);
                    }
                });
    }

    private void lift() {
//伪代码
//将事件序列中的 Integer 对象转换为 String 对象
        Observable.Operator operator = new Observable.Operator() {


            @Override
            public Subscriber call(Object o) { //final Subscriber subscriber
                final Subscriber subscriber= (Subscriber) o;
                return new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        String string = "" + integer;  //转换操作
                        subscriber.onNext(string);
                    }

                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }
                };
            }

        };

        // TODO: 2018/9/12 start
        //伪代码
// lift方法将Int类型转换成String类型
        Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {//Subscriber subscriber
                Subscriber subscriber= (Subscriber) o;
                subscriber.onNext(1); //Int类型
                subscriber.onCompleted();
            }

})
         .lift(operator)  // Int --> String

        .subscribe(new Subscriber() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error!");
            }

            @Override
            public void onNext(Object o) { //String类型,String s
                String s= (String) o;
                Log.d(TAG, "Item: " + s);
            }
        });


        // TODO: 2018/9/12 end

    }

}

