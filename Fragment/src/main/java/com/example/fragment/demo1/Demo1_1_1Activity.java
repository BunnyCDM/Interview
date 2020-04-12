package com.example.fragment.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.fragment.R;

/**
 * Created by mac on 2019/2/26.
 * <p>
 * 演示v4包下的Fragment的动态使用
 * 注意：
 * 1.使用v4包下的Fragment时需要引入activity继承FragmentActivity
 * 2.获取FragmentManager对象时需要调用getSupportFragmentManager（）方法获取对象
 * 3.使用v4包下的Fragment时相关的对象也需要导入对应的v4包下
 */

public class Demo1_1_1Activity extends FragmentActivity {


    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2020-04-11  注意
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏,必须是继承Activity才有效果哈
        setContentView(R.layout.activity_demo1_1_1);
        AppLogger.d("------Demo1_1Activity------onCreate: ");

        //1.创建Fragment的管理器对象
        manager = getSupportFragmentManager();
        //2.获取Fragment的事物对象且开启事务
        transaction = manager.beginTransaction();
        //3.调用事务中相应的动态操作Fragment的方法执行
        transaction.add(R.id.layout, new MyFragment());
        //4.提交事务
        transaction.commit();
        //transaction.remove();
        //transaction.replace();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //如果现在处于停止状态，重新被用户使用
        AppLogger.d("------Demo1_1Activity------onRestart: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        //用户被看到
        AppLogger.d("------Demo1_1Activity------onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取用户焦点
        AppLogger.d("------Demo1_1Activity------onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //失去用户焦点
        AppLogger.d("------Demo1_1Activity------onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //完全被其他界面遮挡时
        AppLogger.d("------Demo1_1Activity------onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果直接点击返回
        AppLogger.d("------Demo1_1Activity------onDestroy: ");
    }

}
