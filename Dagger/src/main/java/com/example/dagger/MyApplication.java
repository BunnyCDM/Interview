package com.example.dagger;

import android.app.Application;

import com.example.dagger.di.component.DaggerMainComponent;
import com.example.dagger.di.component.MainComponent;
import com.example.dagger.di.module.MainModule;


/**
 * Created by mac on 2017/12/5.
 */

public class MyApplication extends Application {

    private static MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainComponent.builder().mainModule(new MainModule()).build();
    }

    public static MainComponent getComponent() {
        return component;
    }

}
