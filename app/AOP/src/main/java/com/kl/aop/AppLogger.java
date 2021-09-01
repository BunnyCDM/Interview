package com.kl.aop;

import android.util.Log;



public final class AppLogger {

    private final static String TAG = "bunny";
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int UNKNOW = 6;

    private static int LEVEL = VERBOSE;//控制log等级


    private static boolean isInited = false;

    public static void init() {
        //final boolean isDebug = AppUtils.isApkInDebug(context);
        final boolean isDebug = BuildConfig.DEBUG;
        if (isDebug) {
            LEVEL = UNKNOW;
        }

        if (!isInited) {
            isInited = true;

        } else {

        }
    }

//    private boolean isDebug() {
//        if (BuildConfig.LOG_DEBUG) { //debug模式
//            return true;
//        } else {//release模式
//            return false;
//        }
//    }

    public AppLogger setLevel(int level) {
        this.LEVEL = level;
        return this;
    }


    public static void v(String message) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG, message);
        }
    }

    public static void v(String tag, String message) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, message);
        }
    }

    public static void d(String message) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG, message);
        }
    }

    public static void d(String tag, String message) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void d(String tag,String msg, Object... args) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG, String.format(msg, args));
        }
    }

    public static void i(String message) {
        if (LEVEL <= INFO) {
            Log.i(TAG, message);
        }
    }

    public static void i(String tag, String message) {
        if (LEVEL <= INFO) {
            Log.i(tag, message);
        }
    }

    public static void w(String message) {
        if (LEVEL <= WARN) {
            Log.w(TAG, message);
        }
    }

    public static void w(String tag, String message) {
        if (LEVEL <= WARN) {
            Log.w(tag, message);
        }
    }

    public static void e(String message) {
        if (LEVEL <= ERROR) {
            Log.e(TAG, message);
        }
    }

    public static void e(String tag, String message) {
        if (LEVEL <= ERROR) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, Throwable e) {
        if (LEVEL <= ERROR) {
            Log.e(tag, "报错：",e);
        }
    }

    public static void e(String tag, String message,Throwable e) {
        if (LEVEL <= ERROR) {
            Log.e(tag, message,e);
        }
    }

}


