package com.example.dagger.di.component;


import com.example.dagger.AActivity;
import com.example.dagger.MainActivity;
import com.example.dagger.data.User;
import com.example.dagger.di.module.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mac on 2017/12/5.
 * <p>
 * 作用是把两个标注了Inject联系起来
 * 注意：不能写返回相同类型的方法，也就是不能再写一个方法名不同，但是都返回User的方法，否则会编译不通过
 */

//@Component
//@Component(modules = MainModule.class)

@Singleton
@Component(modules = MainModule.class)

public interface MainComponent {

    void inject(MainActivity mainActivity); //传入参数是MainActivity是因为我们要注入的地方就是MainActivity

    void inject(AActivity aActivity);

    User provideUser();//这里不能传入参数，否则报错

}


