package com.example.dagger.di.module;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.dagger.MyApplication;
import com.example.dagger.data.ToastUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 2017/12/5.
 * 用来提供对象
 */

@Module
public class AppModule {

    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Singleton
    @Provides
    ToastUtil privideToastUtil(Context context) {
        return new ToastUtil(context);
    }

    @Singleton
    @Provides
    SharedPreferences provideSP(Context context) {
        return context.getSharedPreferences("config", Context.MODE_PRIVATE);
    }


}
