package com.example.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LinearLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dynamic();
    }

    /**
     * 使用布局文件
     */
    private void unDynamic() {
        setContentView(R.layout.activity_linear_layout);
    }

    /**
     * 代码控制布局
     */

    private LinearLayout root;
    private Button mBtn;

    private void dynamic() {
        root = new LinearLayout(this);
        //设置方向
//        root.setOrientation(LinearLayout.VERTICAL);

        //父集容器设置
//        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//        root.setLayoutParams(params1);
        setContentView(root);

        mBtn = new Button(this);
        mBtn.setText("Remove Me");
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //移除子对象
                root.removeView(v);
            }
        });

        //父集容器子对象设置
//        LinearLayout.LayoutParams params2
//                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        mBtn.setLayoutParams(params2);

        //添加子对象一
//        root.addView(mBtn);

        //添加子对象二
//        root.addView(mBtn,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams params
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置比重
        //params.weight = 1;
        //添加子对象三
        root.addView(mBtn, params);

    }

}
