package com.asw.rxjava.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


import com.asw.rxjava.Message;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by mac on 2017/4/11.
 * <p>
 * 创建操作
 */

public class RxJavaCreate extends Activity {

    private final static String TAG = RxJavaCreate.class.getSimpleName();

    Subscriber<String> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

//        create();

//        just();

//        from();

//        publishSubject();

//        behaviorSubject();
    }

    /**
     * 创建观察者（observer/subscriber）,除Observer接口外，RxJava 还内置了一个实现了 Observer 的抽象类：Subscriber implements Observer
     * Observer和Subscriber具有相同的角色，而且Observer在subscribe()过程中最终会被转换成Subscriber对象，因此，后面的描述将用Subscriber来代替Observer，这样更加严谨
     */
    private void create() {

        //步骤一：创建observable（被观察者），并为它定义事件触发规则
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "call: 开始放声大哭搜救");
                if (!subscriber.isUnsubscribed()) {//检查观察者状态
                    subscriber.onNext("Hello RxJava");
                    subscriber.onCompleted();
                }
            }
        });

        //步骤二：创建observer（观察者）
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };


        //步骤二：创建subscriber（观察者）
        subscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: s:" + s);
            }
        };

        //步骤三：订阅
        observable.subscribe(subscriber);

    }

    private void just() {

        //简化
        Observable.just("Hello RxJava").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: 普通简化=" + s);
            }
        });

        //简化
        Observable.just("Hello RxJava").subscribe(s -> Log.d(TAG, "call: lambda简化：" + s));

        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: onCompleteAction");
            }
        };

        Action1<Message> onNextAction = new Action1<Message>() {
            @Override
            public void call(Message message) {
                Log.d(TAG, "call: onNextAction message.getMsg()=" + message.getMsg());
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: onErrorAction");
            }
        };

        Observable observable1 = Observable.just(new Message("Hello World"));
        observable1.subscribe(onNextAction, onErrorAction, onCompleteAction);

        Observable observable2 = Observable.just(new Message("世界和平"));
        observable2.subscribe(onNextAction);

        Observable.just(new Message("简单写法！！！！！不错哦！！")).subscribe(new Action1<Message>() {
            @Override
            public void call(Message message) {
                Log.d(TAG, "call: message.getMsg()=" + message.getMsg());
            }
        });

        Observable.just(new Message("Hello World!fuck!!")).subscribe(new Subscriber<Message>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Message message) {
                Log.d(TAG, "onNext: message.getMsg()=" + message.getMsg());
            }
        });
    }

    private void from(){
        String[] items={"just1","just2","just3","just4"};
        Observable.from(items).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: s="+s);
            }
        });
    }

    private void publishSubject(){
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: "+"-----" + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: onError "+throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: onComplete");
            }
        });
        subject.onNext(2);
        //subject.onError(new Throwable("自定义的一个错误！！！"));
        //subject.onCompleted();
        subject.onNext(3);
        subject.onNext(4);
        subject.onCompleted();
    }

    private void behaviorSubject(){
        BehaviorSubject<Integer> s = BehaviorSubject.create(555);
        s.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: "+"1----" + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: onError");
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: onComplete1");
            }
        });
        Log.d(TAG, "behaviorSubject: ------1------");
        s.onNext(1);
        Log.d(TAG, "behaviorSubject: ------2------");
        s.onNext(2);
        Log.d(TAG, "behaviorSubject: ------3------");
        s.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: "+"2----" + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: onError");
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: onComplete2");
            }
        });
        Log.d(TAG, "behaviorSubject: ------4------");
        s.onNext(3);
        Log.d(TAG, "behaviorSubject: ------5------");
        s.onNext(4);
        Log.d(TAG, "behaviorSubject: ------6------");
        s.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: "+"3----" + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: onError");
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: onComplete3");
            }
        });
        Log.d(TAG, "behaviorSubject: 开始onCompleted");
        s.onCompleted();
    }

//    private void observableEmitter(){
//        //创建被观察者
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                //调用观察者的回调
//                emitter.onNext("我是");
//                emitter.onNext("RxJava");
//                emitter.onNext("简单示例");
//                emitter.onError(new Throwable("出错了"));
//                emitter.onComplete();
//            }
//        });
//
//        //创建观察者
//        Observer<String> observer = new Observer<String>() {
//
//            @Override
//            public void onCompleted() {
//                Log.e(TAG,"onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG,e.getMessage());
//            }
//
//
//            @Override
//            public void onNext(String s) {
//                Log.d(TAG,s);
//            }
//        };
//
//        //注册，将观察者和被观察者关联，将会触发OnSubscribe.call方法
//        observable.subscribe(observer);
//
//    }


    /**
     * 因为在 subscribe() 之后， Observable 会持有 Subscriber 的引用，这个引用如果不能及时被释放，将有内存泄露的风险。
     * 所以最好保持一个原则：要在不再使用的时候尽快在合适的地方（例如 onPause() onStop() 等方法中）调用 unsubscribe() 来解除引用关系，以避免内存泄露的发生
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }

    }
}
