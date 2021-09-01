package com.example.designpatterns.shejimoshichuangjian.SingletonPattern;

/**
 * Created by mac on 2019/1/21.
 * <p>
 * 懒汉式单例类，在第一次调用的时候实例化自己
 */

public class Singleton {

    private static volatile Singleton single = null;

    //把构造函数设置为private，避免类在外部被实例化
    private Singleton() {

    }

    //静态工厂方法，这个没考虑线程安全问题，线程不安全
    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }

    //在getInstance方法上加同步
    public static synchronized Singleton getInstance1() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }

    //双重检查锁定
    public static Singleton getInstance2() {
        if (single == null) {
            synchronized (Singleton.class) {
                if (single == null) {
                    single = new Singleton();
                }
            }
        }
        return single;
    }

    //静态内部类,这种比上面1、2都好一些，既实现了线程安全，又避免了同步带来的性能影响
    private static class LazyHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance3() {
        return LazyHolder.INSTANCE;
    }

}
