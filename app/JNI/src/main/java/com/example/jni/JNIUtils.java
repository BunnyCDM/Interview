package com.example.jni;

/**
 * Created by mac on 2019-11-27.
 */
public class JNIUtils {

    //加载native-jni
    static {
        System.loadLibrary("native-lib");//加载native-lib jniLibs
    }

    //java调C的方法都需要用native声明且方法名必须和C的方法名一样
    public native String stringFromJNI();

}
