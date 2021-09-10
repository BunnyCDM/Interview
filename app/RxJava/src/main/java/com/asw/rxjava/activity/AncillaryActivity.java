package com.asw.rxjava.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.asw.rxjava.Message;
import com.asw.rxjava.MessageTask;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by mac on 2017/12/19.
 * <p>
 * 辅助操作
 */

public class AncillaryActivity extends Activity {

    private final static String TAG = AncillaryActivity.class.getSimpleName();
    private int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        subscribeOn();
//
//        test2();
//
//        test3();
//
//        test4();

        compose();
    }

    private void subscribeOn() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "call: Thread.currentThread().getId():" + Thread.currentThread().getId());
                Log.d(TAG, "call: i % 3 :" + i % 3);
                if (i % 3 == 0) {
                    subscriber.onNext("MultiThreading");
                } else if (i % 3 == 1) {
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable("进入异常"));
                }
                i++;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: Thread.currentThread().getId():" + Thread.currentThread().getId());
                Log.d(TAG, "onCompleted: 完成！！");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: 失败！！");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: s=" + s);
            }
        });
    }

    private void observeOn() {

    }

    Observable.Transformer schedulersTransformer(){
        return new Observable.Transformer(){

            @Override
            public Object call(Object observable) {
                return ((Observable) observable)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //消除重复代码
    private void compose() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "call: Thread.currentThread().getId():" + Thread.currentThread().getId());
                Log.d(TAG, "call: i % 3 :" + i % 3);
                if (i % 3 == 0) {
                    subscriber.onNext("MultiThreading");
                } else if (i % 3 == 1) {
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable("进入异常"));
                }
                i++;
            }
        })
                .compose(schedulersTransformer())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Thread.currentThread().getId():" + Thread.currentThread().getId());
                        Log.d(TAG, "onCompleted: 完成！！");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: 失败！！");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: s=" + s);
                    }
                });


    }


    /**
     * 使用Action1来执行回调
     */
    private void test2() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "call:Thread.currentThread().getId():" + Thread.currentThread().getId());
                if (i % 3 == 0) {
                    subscriber.onNext("MultiThreading");
                } else if (i % 3 == 1) {
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable("进入异常"));
                }
                i++;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                               @Override
                               public void call(String s) {
                                   Log.d(TAG, "call: Thread.currentThread().getId():" + Thread.currentThread().getId());
                                   Log.d(TAG, "call: s=" + s);
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   Log.d(TAG, "call: 失败！！！");
                               }
                           }, new Action0() {
                               @Override
                               public void call() {
                                   Log.d(TAG, "call: 完成！！！！");
                               }
                           }
                );
    }

    /**
     * 使用操作符map，操作符中的线程跟Observable是一样的,在这里都是异步
     */
    private void test3() {
        Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                Log.d(TAG, "call: Thread.currentThread().getId():" + Thread.currentThread().getId());
                if (i % 3 == 0) {
                    subscriber.onNext(new Message("Hello RxAndroid!!!!!"));
                } else if ((i % 3 == 1)) {
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(null);
                }
                i++;
            }
        }).map(new Func1<Message, Message>() {

            @Override
            public Message call(Message arg0) {
                Log.d(TAG, "call: Thread.currentThread().getId():" + Thread.currentThread().getId());
                return new Message(arg0.getMsg() + "Hello Winson!!!");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Message>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: Thread.currentThread().getId():" + Thread.currentThread().getId());
                Log.d(TAG, "onCompleted: 完成！！！！");
            }

            @Override
            public void onError(Throwable arg0) {
                Log.d(TAG, "onError: Thread.currentThread().getId():" + Thread.currentThread().getId());
                Log.d(TAG, "onError: 失败！！！!");
            }

            @Override
            public void onNext(Message message) {
                Log.d(TAG, "onNext: Thread.currentThread().getId():" + Thread.currentThread().getId());
                Log.d(TAG, "onNext: message.getMsg()=" + message.getMsg());
            }
        });
    }

    /**
     * onNext回调拓展
     */
    private void test4() {
        Observable.create(new Observable.OnSubscribe<MessageTask>() {
            @Override
            public void call(Subscriber<? super MessageTask> subscriber) {
                Log.d(TAG, "call: Thread.currentThread().getId():" + Thread.currentThread().getId());
                try {
                    subscriber.onNext(new MessageTask(0, "开始执行任务。。。。。"));
                    Thread.sleep(1000);
                    subscriber.onNext(new MessageTask(1, "正在执行任务。。。。"));
                    Thread.sleep(1000);
                    subscriber.onNext(new MessageTask(2, "还在执行任务。。。。"));
                    Thread.sleep(1000);
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<MessageTask>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: Thread.currentThread().getId():" + Thread.currentThread().getId());
                Log.d(TAG, "onCompleted: 已经完成了！！！！");
            }

            @Override
            public void onError(Throwable arg0) {
                Log.d(TAG, "onError: Thread.currentThread().getId():" + Thread.currentThread().getId());
                Log.d(TAG, "onError: 可惜失败了！！！");
            }

            @Override
            public void onNext(MessageTask arg0) {
                Log.d(TAG, "onNext: Thread.currentThread().getId():" + Thread.currentThread().getId());
                switch (arg0.getTag()) {
                    case 0:
                        Log.d(TAG, "onNext: arg0.getMsg() 我要准备");
                        break;
                    case 1:
                        Log.d(TAG, "onNext: arg0.getMsg() 已经开始了呀");
                        break;
                    case 2:
                        Log.d(TAG, "onNext: arg0.getMsg() 还要多久呀");
                        break;

                    default:
                        break;
                }
            }
        });
    }

}
