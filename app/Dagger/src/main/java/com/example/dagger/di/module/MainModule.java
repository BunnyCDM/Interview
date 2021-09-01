package com.example.dagger.di.module;


import com.example.dagger.data.User;
import com.example.dagger.di.UserQualifier;
import com.example.dagger.di.UserScope;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 2017/12/5.
 * <p>
 * 用来提供对象
 * 类似一个简单工厂，里面存放着各种构造方法
 */


//@Module
//public class MainModule {
//
//    private String name;
//
//    public MainModule() {
//
//    }
//
//    public MainModule(String name) {
//        this.name = name;
//    }
//
//    @Singleton //全局的单例模式
////    @UserScope //Scope代表着作用域，也就是作用的范围
//    @Provides
//    User provideUser() {
//        return new User(name);
//    }
//
//
//    @Singleton
//    @Provides
//    User provideUser_() {
//        return new User(name);
//    }
//
//}

// TODO: 2020-11-23 测试四
@Module
public class MainModule {

    private String name;

    public MainModule() {

    }

    public MainModule(String name) {
        this.name = name;
    }

    @Provides
    //@Named("a")
    @UserQualifier("a")
    User provideUser() {
        return new User(name);
    }


    @Provides
    //@Named("b")
    @UserQualifier("b")
    User provideUser_() {
        return new User(name);
    }

}
