package com.asw.rxjava.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.asw.rxjava.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 2017/4/11.
 *  * 异步联网
 */

public class RxJavaError extends Activity{

    private final static String TAG=RxJavaError.class.getSimpleName();
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    TextView mTextView1;
    TextView mTextView2;
    TextView mTextView3;
    private MyClickListener mMyClickListener;
    private List<String> mList;
    public static final String HOST = "http://www.weather.com.cn/data/sk/"; // 这是天气网站

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_combining);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        mButton1= (Button) findViewById(R.id.btn1_rx2);
        mButton2= (Button) findViewById(R.id.btn2_rx2);
        mButton3= (Button) findViewById(R.id.btn3_rx2);
        mButton4= (Button) findViewById(R.id.btn4_rx2);
        mTextView1= (TextView) findViewById(R.id.tvs_rx2);
        mTextView2= (TextView) findViewById(R.id.tve_rx2);
        mTextView3= (TextView) findViewById(R.id.tvc_rx2);
    }

    private void initData() {
        mMyClickListener=new MyClickListener();
    }

    private void initEvent() {
        mButton1.setOnClickListener(mMyClickListener);
        mButton2.setOnClickListener(mMyClickListener);
        mButton3.setOnClickListener(mMyClickListener);
        mButton4.setOnClickListener(mMyClickListener);
    }

    private class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn1_rx2:
                    Log.d(TAG, "onClick: 1");
                    test1();
                    break;
                case R.id.btn2_rx2:
                    Log.d(TAG, "onClick: 2");
                    test2();
                    break;
                case R.id.btn3_rx2:
                    Log.d(TAG, "onClick: 3");
                    break;
                case R.id.btn4_rx2:
                    Log.d(TAG, "onClick: 4");
                    break;
            }
        }
    }

    private void test1() {
        Observable.just("101010100.html").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                Log.d(TAG, "call: result:"+HOST+s);
                return HOST + s;
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                Log.d(TAG, "call: s:"+s);
                return doNetTask(s);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: s:"+s);
                /**
                 * {
                 "weatherinfo": {
                 "city": "北京",
                 "cityid": "101010100",
                 "temp": "18",
                 "WD": "东南风",
                 "WS": "1级",
                 "SD": "17%",
                 "WSE": "1",
                 "time": "17:05",
                 "isRadar": "1",
                 "Radar": "JC_RADAR_AZ9010_JB",
                 "njd": "暂无实况",
                 "qy": "1011",
                 "rain": "0"
                 }
                 }
                 */
            }
        });
    }

    private void test2() {
        mList = new ArrayList<String>();
        mList.add("101030100.html");
        mList.add("101040100.html");
        mList.add("101050101.html");
        mList.add("101060101.html");
        mList.add("101070101.html");

        Observable.just(mList).flatMap(new Func1<List<String>, Observable<?>>() {
            @Override
            public Observable<?> call(List<String> strings) {
                Log.d(TAG, "call: Observable.from(strings):"+Observable.from(strings));
                return Observable.from(strings);
            }
        }).cast(String.class).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                Log.d(TAG, "call: s:"+s);
                return doNetTask(HOST + s); // 取出想要的字段，这里我就不取出来了
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: s:"+s);
            }
        });
    }


    private String doNetTask(String url) {
        try {
            URL u = new URL(url);//将字符串地址封装成URL对象，目前为了使用uRL对象打开网络链接
            try {
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();//打开网络链接，并强制转化成http协议的链接对象HttpURLConnection
                StringBuffer sb = null;
                conn.setRequestMethod("GET");
                conn.connect();//建立链接
                int recode = conn.getResponseCode();//获取服务端响应码，如果等于200，则服务器响应成功，否则响应失败
                if (recode == HttpURLConnection.HTTP_OK) {//服务端响应请求成功
                    InputStream in = conn.getInputStream();//获取输入流（字节流），读取服务端返回的数据
                    InputStreamReader inr = new InputStreamReader(in);//将字节流转换成字符流
                    BufferedReader br = new BufferedReader(inr);//将低级流封装成高级流
                    String str = null;
                    sb = new StringBuffer();
                    while ((str = br.readLine()) != null) {//执行读取服务端返回的数据，判断如果不为null，则将读到的一行数据保存到stringbuffer中
                        sb.append(str);
                        sb.append(System.getProperty("line.separator"));//每行添加一个换行符
                    }
                    br.close();//关闭输入流
                    if (sb.toString().length() == 0) {
                        return null;//获取数据为空
                    }
                    return sb.toString().substring(0, sb.toString().length() - System.getProperty("line.separator").length());
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;

    }

//    private String doNetTask(String s) {
//        HttpClient client = new DefaultHttpClient();
//
//        HttpGet get = new HttpGet(s);
//        String result;
//        try {
//            HttpResponse response = client.execute(get);
//
//            if (200 == response.getStatusLine().getStatusCode()) {
//                result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
//            } else {
//                result = "状态行非200";
//            }
//
//        } catch (Exception e1) {
//            result = e1.getStackTrace().toString();
//        }
//        return result;
//    }
}
