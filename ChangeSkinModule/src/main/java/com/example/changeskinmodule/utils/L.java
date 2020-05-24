package com.example.changeskinmodule.utils;

import android.util.Log;

/**
 * Created by zhy on 15/9/23.
 */
public class L
{
    private static final String TAG = "bunny";
    private static boolean debug = false;

    public static void e(String msg)
    {
        if (debug)
            Log.e(TAG, msg);
    }

}
