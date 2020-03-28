package com.example.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Android中的Http通信
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.mTextView);

        createJson_01();

//        createJson_02();

//        createJson_03();

        createJson_04();
    }

    private void createJson_01() {

        //对象转换方式
    }

    private void createJson_02() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "http在安卓中的应用");
            jsonObject.put("author", "Nick");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("http基础");
            jsonArray.put("http数据解析");
            jsonObject.put("content", jsonArray);

            mTextView.setText(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void createJson_03() {
        String jsonStr="{\"name\": \"http在安卓中的应用\",\"author\": \"Nick\"}";

        mTextView.setText(jsonStr);
    }

    private void createJson_04() {
        String name="http在安卓中的应用";
        String author="Nick";
        String jsonStr="{\"name\": "+"\""+name+"\""+"}";

        mTextView.setText(jsonStr);
    }
}
