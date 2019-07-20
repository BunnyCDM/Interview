package com.example.guidepage.app;

import android.app.Application;

/**
 * Created by mac on 2019/2/25.
 */

public class GuidePageApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).configure();
    }

}
