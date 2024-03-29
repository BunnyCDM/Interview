package com.example.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.white)     //状态栏颜色，不写默认透明色
//                .navigationBarColor(R.color.colorAccent) //导航栏颜色，不写默认黑色
//                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
//                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .init();

//        method_1();

//        method_2();

        method_3();

    }


    /**
     * xml中的配置，包含自定义标题样式
     */
    private void method_1() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + v.toString());
            }
        });
        //加载该menu文件
        toolbar.inflateMenu(R.menu.main);
    }

    /**
     * 代码中设置
     */
    private void method_2() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("代码中设置标题");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击左边返回按钮监听事件
                Log.d(TAG, "onClick: ");
            }
        });
    }

    /**
     * 全部自定义
     */
    private void method_3() {
        TopBar topBar = (TopBar) findViewById(R.id.toolbar);
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 方法实现
         * 属性实现
         */
        topBar.setLetIsVisible(false);
        topBar.setRightIsVisible(false);
        topBar.setTitleIsVisible(false);
        topBar.setTopbarIsVisible(false);

    }

}
