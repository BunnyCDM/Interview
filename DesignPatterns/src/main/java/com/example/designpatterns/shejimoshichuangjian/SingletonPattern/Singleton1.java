package com.example.designpatterns.shejimoshichuangjian.SingletonPattern;

/**
 * Created by mac on 2019/1/21.
 * <p>
 * 饿汉式单例类.在类初始化时，已经自行实例化
 */

public class Singleton1 {

    private Singleton1() {

    }

    private static final Singleton1 single = new Singleton1();


    //静态工厂方法
    public static Singleton1 getInstance() {
        return single;
    }

}
