package com.example.activityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText one, two, result;
    private final static int REQUESTCODE = 1;//返回的结果码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (EditText) findViewById(R.id.Text_one);
        two = (EditText) findViewById(R.id.Text_two);
        result = (EditText) findViewById(R.id.Text_result);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取用户输入的两个值
                int a = Integer.parseInt(one.getText().toString());
                int b = Integer.parseInt(two.getText().toString());

                //意图实现activity的跳转
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                intent.putExtra("a", a);
                intent.putExtra("b", b);

                // 这种启动方式：startActivity(intent);并不能返回结果
                startActivityForResult(intent, REQUESTCODE);
            }
        });
    }


    //获取返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (resultCode == 2) {

            if (requestCode == REQUESTCODE) {
                int three = data.getIntExtra("three", 0);
                //设置结果显示框的显示数值
                result.setText(String.valueOf(three));
            }
        }

    }
}
