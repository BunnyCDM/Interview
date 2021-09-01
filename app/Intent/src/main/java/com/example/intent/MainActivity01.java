package com.example.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);

        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示使用
//                startActivity(new Intent(MainActivity01.this, MainActivity02.class));

                //隐示使用，注意其它应用也可以打开，如果intent-filter设置的信息一样，他会提示让是选择打开那一个
//                startActivity(new Intent(MainActivity02.ACTION));

                //指定协议Activity对应AndroidManifest.xml设置的 <data android:scheme="app"/>
                startActivity(new Intent(MainActivity02.ACTION, Uri.parse("app://")));
            }
        });
    }
}
