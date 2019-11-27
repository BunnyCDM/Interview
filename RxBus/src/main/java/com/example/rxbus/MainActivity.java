package com.example.rxbus;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;
import com.hwangjr.rxbus.annotation.Subscribe;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * RxBus并不是一个库，而是一种模式。
 * 相信大多数开发者都使用过EventBus，作为事件总线通信库，
 * 如果你的项目已经加入RxJava和EventBus，不妨用RxBus代替EventBus，以减少库的依赖
 */
public class MainActivity extends AppCompatActivity {

    private Subscription mSubscription;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxBusObservers1();
        rxBusObservers2();
    }

    public void do_send(View view) {
        RxBus.getInstance().post(new StudentEvent("007", "小明"));//发送事件
    }

    private void rxBusObservers1() {
        /**
         * mSubscription是Sbscription的对象，我们这里把RxBus.getInstance().toObserverable(StudentEvent.class)赋值给rxSbscription以方便生命周期结束时取消订阅事件
         */
        mSubscription = RxBus.getInstance().toObservable(StudentEvent.class)//接送事件
                .subscribe(new Action1<StudentEvent>() {
                    @Override
                    public void call(StudentEvent studentEvent) {
                        AppLogger.d("call: " + "id:" + studentEvent.getId() + "  name:" + studentEvent.getName());
                    }
                });
    }

    private void rxBusObservers2() {
        Subscription subscription = RxBus.getInstance()
                .toObservable(StudentEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if (o instanceof StudentEvent) {
                            AppLogger.d("onNext: o:" + ((StudentEvent) o).getId() + ((StudentEvent) o).getName());
                        }

                    }
                });
        addSubscription(subscription);
    }

    private void addSubscription(Subscription subscription) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed()) {//取消订阅,以免内存泄漏
            mSubscription.unsubscribe();
        }
        mCompositeSubscription.unsubscribe();
    }
}
