package com.kl.aop;

import android.Manifest;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kl.aop.reflection.ResourcesUtil;
import com.kl.aop.reflection.ToastUtils;


/**
 * https://www.bilibili.com/video/BV1vU4y1h7wx?p=7
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private TextView tv;

    private String temp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.text);
        View bt1 = findViewById(R.id.button1);
        View bt2 = findViewById(R.id.button2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        Log.d("fsdfs", "onCreate: ");


        //方法调用
//        requestLocation();
    }

    @Override
    public void onClick(View v) {
//        initData();
        switch (v.getId()) {
            case R.id.button1:
                Log.d("fsdfs", "onCreate: ");
//                ToastUtils.show(temp);
                break;
            case R.id.button2:
                tv.setText(temp);
                break;
        }
    }

    private void initData() {
        String test1 = ResourcesUtil.getString(R.string.test1);
        String test2 = ResourcesUtil.getString(R.string.test2);
        if(temp.equals(test1)){
            temp = test2;
        }else{
            temp = test1;
        }
    }


//    @PermissionNeed(value = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, requestCode = 11)
//    private void requestLocation() {
//        //方法执行
//        Log.d("bunny", "requestLocation: 请求权限授权成功");
//    }


}
