package com.example.http.base1;

import android.app.Application;
import android.content.Context;

import com.example.http.base1.api.OkHttpUtil;

/**
 * Created by mac on 2020-08-06.
 */
public class AppApplication extends Application {


    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtil.init();
        mContext = this;
    }
}
