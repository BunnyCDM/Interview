package com.example.dagger.di.module;


import com.example.dagger.data.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 2017/12/5.
 * <p>
 * 类似一个简单工厂，里面存放着各种构造方法
 */

@Module
public class MainModule {

    private String name;

    public MainModule() {

    }

    public MainModule(String name) {
        this.name = name;
    }

    @Singleton
    @Provides
    User provideUser() {
        return new User(name);
    }

}
