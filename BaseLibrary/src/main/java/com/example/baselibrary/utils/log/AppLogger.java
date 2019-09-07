package com.example.baselibrary.utils.log;

import android.util.Log;

/**
 * Created by mac on 2019-09-01.
 * 日志管理相关工具类，注意打包正式release版本，最好关闭日志打印或提高等级，否则过多的日志输出会影响app性能
 */
public class AppLogger {

    private final static String TAG = "bunny";
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int UNKNOW = 6;

    private static int LEVEL = VERBOSE;//控制log等级

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

}



