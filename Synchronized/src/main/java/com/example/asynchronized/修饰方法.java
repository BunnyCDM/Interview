package com.example.asynchronized;

/**
 * Created by mac on 2019/1/18.
 */

public class 修饰方法 {


    //修饰的是一个方法
    public synchronized void method1() {

    }


    //修饰的是一个代码块
    public void method2() {

        synchronized (this) {

        }

    }


    class Parent {
        public synchronized void method() {

        }
    }


    class Child extends Parent {

        public synchronized void method() {

        }

        public void method_() {
            super.method();
        }
    }


}
