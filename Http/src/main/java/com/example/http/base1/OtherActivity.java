package com.example.http.base1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.http.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Android中的Http通信，cardproject1-master所在Github文件下
 * <p>
 * 随着Okhttp等框架的横空出世，最原生的网络请求HttpURLConnection已经退出历史舞台
 * <p>
 * URLConnection和HttpURLConnection使用的都是java.net中的类，属于标准的java接口
 * HttpURLConnection继承自URLConnection,差别在与HttpURLConnection仅仅针对Http连接，https用HttpsURLConnection
 * <p>
 * OkGo基于Http协议，封装了OkHttp的网络请求框架，支持自定义缓存，支持批量断点下载管理和批量上传管理功能
 * <p>
 * okhttp-OkGo的使用，完美支持RxJava
 */
public class OtherActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        mTextView = findViewById(R.id.mTextView);


        //URLConnection
        //HttpURLConnection

        //OkGo okhttp3、
        //okhttp
        //okio
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
        String jsonStr = "{\"name\": \"http在安卓中的应用\",\"author\": \"Nick\"}";

        mTextView.setText(jsonStr);
    }

    private void createJson_04() {
        String name = "http在安卓中的应用";
        String author = "Nick";
        String jsonStr = "{\"name\": " + "\"" + name + "\"" + "}";

        mTextView.setText(jsonStr);
    }
}
