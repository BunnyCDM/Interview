package com.example.activityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        editText= (EditText) findViewById(R.id.Text_three);
        textView= (TextView) findViewById(R.id.msg);
        button= (Button) findViewById(R.id.button2);

        // 去除传递过来的意图,并提取数据

        Intent intent = getIntent();//此处并不是创建而是直接获取一个intent对象Return the intent that started this activity.
        int a = intent.getIntExtra("a", 0); // 没有输入值默认为0
        int b = intent.getIntExtra("b", 0); // 没有输入值默认为0
        textView.setText(a + " + " + b + " = " + " ? ");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
               // 获取用户计算后的结果
               int three = Integer.parseInt(editText.getText().toString());
               intent.putExtra("three", three); //将计算的值回传回去
               //通过intent对象返回结果，必须要调用一个setResult方法，
               //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
               setResult(2, intent);

               finish(); //结束当前的activity的生命周期
            }
        });


    }
}
