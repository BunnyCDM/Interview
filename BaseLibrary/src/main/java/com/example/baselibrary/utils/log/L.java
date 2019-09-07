package com.example.baselibrary.utils.log;

import android.util.Log;

import com.example.baselibrary.BuildConfig;

/**
 * Created by mac on 2019-09-07.
 */
public class L {

    private final static String TAG = "bunny";

    private static boolean sDebug = BuildConfig.DEBUG;

    public static void d(String msg, Object... args) {

        if (!sDebug) {
            return;
        }
        Log.d(TAG, String.format(msg, args));
    }

}
