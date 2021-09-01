package com.example.multidex;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by mac on 2019/3/4.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);//初始化

    }

}
