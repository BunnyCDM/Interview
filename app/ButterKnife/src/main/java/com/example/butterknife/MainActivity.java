package com.example.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * https://www.bilibili.com/video/BV1vU4y1h7wx?p=3
 *
 * https://www.jianshu.com/p/9bee8e143410
 *
 * https://blog.csdn.net/zyw0101/article/details/80399225
 */
//@InjectLayout(R.layout.activity_main)
public class MainActivity extends Activity {


    @BindView(R.id.tv_content)
    TextView tvContent;


    @BindView(R.id.btn_content)
//    @InjectView(R.id.btn_content)
    Button btnContent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


//        InjectManager.inject(this);
//        btnContent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "btn_content1", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @com.example.butterknife.OnClick(R.id.btn_content)
    public void leoOnClick() {
        Toast.makeText(this, "btn_content2", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_content)
    public void onClick() {
        Toast.makeText(this, "btn_content", Toast.LENGTH_SHORT).show();
    }


}
