package com.example.dagger.di.component;


import com.example.dagger.AActivity;
import com.example.dagger.MainActivity;
import com.example.dagger.data.User;
import com.example.dagger.di.UserQualifier;
import com.example.dagger.di.UserScope;
import com.example.dagger.di.module.MainModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mac on 2017/12/5.
 * <p>
 * 就是一个组件，用来完成注入
 * 作用是把两个标注了Inject联系起来
 * 注意：不能写返回相同类型的方法，也就是不能再写一个方法名不同，但是都返回User的方法，否则会编译不通过
 */

// TODO: 2020-11-23 测试一
//@Component
//public interface MainComponent {
//    void inject(MainActivity mainActivity); //传入参数是MainActivity是因为我们要注入的地方就是MainActivity，不能出现多太
//}


// TODO: 2020-11-23 测试二
//@Singleton //全局的单例模式
//@Component(modules = MainModule.class)
//
//public interface MainComponent {
//    void inject(MainActivity mainActivity); //传入参数是MainActivity是因为我们要注入的地方就是MainActivity，不能出现多太
//
//    void inject(AActivity aActivity);
//
//    User provideUser();//这里不能传入参数，否则报错
//}


// TODO: 2020-11-23 测试三
//@Component
//@Component(modules = MainModule.class)

@Singleton
//@UserScope //Scope代表着作用域，也就是作用的范围
@Component(modules = MainModule.class)

public interface MainComponent {

    void inject(MainActivity mainActivity); //传入参数是MainActivity是因为我们要注入的地方就是MainActivity，不能出现多太

    void inject(AActivity aActivity);

    User provideUser();//这里不能传入参数，否则报错

}

// TODO: 2020-11-23 测试四



