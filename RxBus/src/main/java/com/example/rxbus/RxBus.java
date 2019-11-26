package com.example.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by mac on 2019-11-26.
 * 1、Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，要避免该问题，需要将 Subject转换为一个      SerializedSubject，上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。
 * 2、PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。
 * 3、ofType操作符只发射指定类型的数据，其内部就是filter+cast
 */
public class RxBus {

    private static volatile RxBus mInstance;
    private final Subject bus;


    public RxBus() {
        bus = new SerializedSubject(PublishSubject.create());
    }

    /**
     * 单例模式 RxBus
     *
     * @return
     */
    public static RxBus getInstance() {
        RxBus rxBus = mInstance;
        if (mInstance == null) {
            synchronized (RxBus.class) {
                rxBus = mInstance;
                if (mInstance == null) {
                    rxBus = new RxBus();
                    mInstance = rxBus;
                }
            }
        }
        return rxBus;
    }

    /**
     * 发送信息
     *
     * @param object
     */
    public void post(Object object) {
        bus.onNext(object);
    }

    /**
     * 接送信息
     *
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

//    public void post(Object object) {
//        if(hasObservers())
//            bus.onNext(object);
//    }


//    public Observable<Object> toObserverable() {
//        return bus;
//    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return bus.hasObservers();
    }

}
