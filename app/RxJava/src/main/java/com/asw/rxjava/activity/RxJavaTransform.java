package com.asw.rxjava.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


import com.asw.rxjava.Message;
import com.asw.rxjava.MessageTask;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 2017/4/11.
 */

public class RxJavaTransform extends Activity {

    private final static String TAG = RxJavaTransform.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:Thread.currentThread().getId(): " + Thread.currentThread().getId());

//        map();

        flatMap();
    }

    private void map() {
        Observable.just(new Message("Hello"))
                .map(new Func1<Message, String>() {

                    @Override
                    public String call(Message message) {
                        Log.d(TAG, "call: Thread.currentThread().getId():" + Thread.currentThread().getId());
                        message.setMsg(message.getMsg() + "world");
                        return message.getMsg();
                    }
                })
                .map(new Func1<String, Message>() {
                    @Override
                    public Message call(String s) {
                        Message message = new Message(s + "! Welcome BeiJing ");
                        Log.d(TAG, "call: message.getMsg():" + message.getMsg());
                        return message;
                    }
                }).subscribe(new Action1<Message>() {
            @Override
            public void call(Message message) {
                Log.d(TAG, "call: message.getMsg()=" + message.getMsg());
            }
        });
    }

    private void flatMap() {
        List<MessageTask> mList = new ArrayList<>();
        mList.add(new MessageTask(0, "内容0"));
        mList.add(new MessageTask(1, "内容1"));
        mList.add(new MessageTask(2, "内容2"));
        mList.add(new MessageTask(3, "内容3"));
        mList.add(new MessageTask(4, "内容4"));

        Observable.just(mList)
                .flatMap(new Func1<List<MessageTask>, Observable<?>>() {
                    @Override
                    public Observable<?> call(List<MessageTask> messageTasks) {
                        return Observable.from(messageTasks);
                    }
                })
                .cast(MessageTask.class)
                .map(new Func1<MessageTask, String>() {

                    @Override
                    public String call(MessageTask messageTask) {
                        String str = "";
                        switch (messageTask.getTag()) {
                            case 0:
                                str += messageTask.getMsg() + " 处理内容0";
                                break;
                            case 1:
                                str += messageTask.getMsg() + " 处理内容1";
                                break;
                            case 2:
                                str += messageTask.getMsg() + " 处理内容2";
                                break;
                            case 3:
                                str += messageTask.getMsg() + " 处理内容3";
                                break;
                            case 4:
                                str += messageTask.getMsg() + " 处理内容4";
                                break;
                        }
                        Log.d(TAG, "call: str:" + str);
                        return str;
                    }
                }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: s:" + s);
            }
        });
    }


}
