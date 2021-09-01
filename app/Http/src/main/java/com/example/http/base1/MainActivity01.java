package com.example.http.base1;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.http.R;
import com.example.http.base1.api.ApiListener;
import com.example.http.base1.api.ApiUtil;

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
public class MainActivity01 extends AppCompatActivity {

    private WebView mWebView;
    private ImageView mImageView;
    private TextView mTextView;
    private SendUrlTask_Get mSendUrlTaskGet;
    private SendUrlTask_Image mSendUrlTask_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);
        mWebView = findViewById(R.id.mWebView);
        mImageView = findViewById(R.id.mImageView);
        mTextView = findViewById(R.id.mTextView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSendUrlTaskGet = new SendUrlTask_Get("http://www.baidu.com");
                mSendUrlTaskGet.execute();


                mSendUrlTask_Image = new SendUrlTask_Image("http://img3.sycdn.imooc.com/57e493a50001738705650565-140-140.jpg");
                mSendUrlTask_Image.execute();

                new TestGetApi().get(MainActivity01.this, new ApiListener() {
                    @Override
                    public void success(ApiUtil api) {
                        TestGetApi testGetApi = (TestGetApi) api;
                        String response = testGetApi.mResponse;
                        parseResponse(response);
                    }

                    @Override
                    public void failure(ApiUtil api) {

                    }
                });

                new TestPostApi("name").post(new ApiListener() {
                    @Override
                    public void success(ApiUtil api) {

                    }

                    @Override
                    public void failure(ApiUtil api) {

                    }
                });
            }
        });


        //OkGo
//        createJson_01();

//        createJson_02();

//        createJson_03();

//        createJson_04();
    }

    private void parseResponse(String response) {
        try {
//            Gson gson = new Gson();
//            QuestionInfo questionInfo = gson.fromJson(response, QuestionInfo.class)
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSendUrlTaskGet != null) {
            mSendUrlTaskGet.cancel(true);
        }

        if (mSendUrlTask_Image != null) {
            mSendUrlTask_Image.cancel(true);
        }
    }

    private class SendUrlTask_Get extends AsyncTask<Void, Void, String> {

        public String mUrl;

        public SendUrlTask_Get(String url) {
            mUrl = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //进行网络请求操作
            return HttpUtil.get(mUrl);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mWebView.loadData(s, "text/html;charset=utf-8", null);
        }
    }

    private class SendUrlTask_Post extends AsyncTask<Void, Void, String> {

        public String mUrl;

        public SendUrlTask_Post(String url) {
            mUrl = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //进行网络请求操作
            return HttpUtil.post(mUrl, null);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mWebView.loadData(s, "text/html;charset=utf-8", null);
        }
    }

    private class SendUrlTask_Image extends AsyncTask<Void, Void, Bitmap> {

        public String mUrl;

        public SendUrlTask_Image(String url) {
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            //进行网络请求操作
            return HttpUtil.loadImage(getApplication(), mUrl);
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            if (s != null) {
                mImageView.setImageBitmap(s);
            }
        }
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
