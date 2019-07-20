package com.example.actionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Activity不显示TitleBar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置Activity为全屏模式
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //以上两种防止都会造成无法获取ActionBar，当然不想显示ActionBar可以在样式中设置
        setContentView(R.layout.activity_main);

        //使用传统设置
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("测试");

        //使用自定义设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.actionbar_title);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_stop:
                Log.d(TAG, "onOptionsItemSelected: stop");
                break;

            case R.id.menu_start:
                Log.d(TAG, "onOptionsItemSelected: start");
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
