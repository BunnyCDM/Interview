package com.example.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.dagger.data.User;
import com.example.dagger.di.UserQualifier;
import com.example.dagger.di.module.MainModule;

import javax.inject.Inject;
import javax.inject.Named;


// TODO: 2020-11-23 测试一
//public class MainActivity extends AppCompatActivity {
//
//    @Inject
//    User user;//这里不能申请为private
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        AppLogger.e("onCreate: user（1）=" + user);
//        DaggerMainComponent.builder().build().inject(this);
//        AppLogger.e("onCreate: user（2）=" + user);
//    }
//
//}


// TODO: 2020-11-23 测试二
//public class MainActivity extends AppCompatActivity {
//
//    @Inject
//    User user;//这里不能申请为private
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        AppLogger.e("onCreate: user（1）=" + user);
//        DaggerMainComponent.builder().build().inject(this);
//        AppLogger.e("onCreate: user（2）=" + user);
//        DaggerMainComponent.builder().mainModule(new MainModule("chen")).build().inject(this);
//        AppLogger.e("onCreate: user（3）=" + user);
//        DaggerMainComponent.builder().mainModule(new MainModule("chen")).build().provideUser();
//        AppLogger.e("onCreate: user（4）=" + user);
//    }
//
//}

// TODO: 2020-11-23 测试三
//public class MainActivity extends AppCompatActivity {
//
//    @Inject
//    User user;//这里不能申请为private
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        AppLogger.e("onCreate: user（1）=" + user);
//        MyApplication.getComponent().inject(this);
//
//        AppLogger.e("onCreate: user（2）=" + user);
//
//        MyApplication.getComponent().provideUser();
//        user.setName("chen");
//        AppLogger.e("onCreate: " + user.getName());
//
//    }
//
//
//    public void btn_jump(View view) {
//        startActivity(new Intent(this, AActivity.class));
//    }
//
//}


// TODO: 2020-11-23 测试四
public class MainActivity extends AppCompatActivity {

    //@Named("a")
    @UserQualifier("a")
    @Inject
    User user;//这里不能申请为private

    //@Named("b")
    @UserQualifier("b")
    @Inject
    User user_;//这里不能申请为private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppLogger.e("onCreate: user（1）=" + user+"-"+user_);
        MyApplication.getComponent().inject(this);

        AppLogger.e("onCreate: user（2）=" + user+"-"+user_);
        user.setName("chen");
        AppLogger.e("onCreate: " + user.getName()+"-"+user_.getName());

    }


    public void btn_jump(View view) {
        startActivity(new Intent(this, AActivity.class));
    }

}