package com.example.dagger.di.component;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.dagger.data.ToastUtil;
import com.example.dagger.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mac on 2017/12/5.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context context();

    ToastUtil toastUtil();

    SharedPreferences sharedPreferences();

}
