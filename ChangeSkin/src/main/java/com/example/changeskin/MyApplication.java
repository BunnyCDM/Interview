package com.example.changeskin;

import android.app.Application;

import com.example.changeskinmodule.SkinManager;

/**
 * Created by mac on 2020-05-03.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
