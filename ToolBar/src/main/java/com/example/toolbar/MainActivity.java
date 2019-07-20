package com.example.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        topBar.setOnClickListener(new TopBar.topbarClickListener() {
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
