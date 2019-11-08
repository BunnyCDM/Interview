package com.example.cityselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.citypicker.CityPickerActivity;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;


/**
 * https://blog.csdn.net/meixi_android/article/details/72673501
 */
public class MainActivity extends AppCompatActivity {

    TextView textView;
    private static final int REQUEST_CODE_PICK_CITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.ssssss);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                onAddressPicker();//底部dialog地区选择器

                startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class), REQUEST_CODE_PICK_CITY);//跳页全屏地区选择器
            }
        });
    }

    private void onAddressPicker() {
        BecomePicktask task = new BecomePicktask(this);
        task.setCallback(new BecomePicktask.Callback() {
            @Override
            public void onAddressInitFailed() {
                Toast.makeText(getApplicationContext(), "数据初始化失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                textView.setText(city.getAreaName());

            }
        });
        task.execute("广东", "东莞", "东城");
    }

    //返回选中城市
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
//                if (!city.equals("全国")) {
//                    city += "市";
//                }
                textView.setText(city);
            }
        }
    }

}

