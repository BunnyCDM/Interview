package com.example.bottomtab.app;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by mac on 2019/2/26.
 */

public class BottomTabApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .configure();


    }
}
