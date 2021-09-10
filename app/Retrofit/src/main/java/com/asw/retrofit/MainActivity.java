package com.asw.retrofit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

//        initRetrofit();

        initRetrofit1();
//        initRetrofit5();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                //如果服务器返回的是json格式的数据 设置GsonConverterFactory后 实现对对象的转化
//                //相当于对服务器返回的json字符串直接进行解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherRetrofit weatherRetrofit = retrofit.create(WeatherRetrofit.class);
        weatherRetrofit.getWeather("深圳", "json", 1, "f932e05d3673dd26f518fc9d10e92ca3")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Weather>() {
                    @Override
                    public void call(Weather weather) {
                        Log.d(TAG, "call: weather:" + weather);
                    }
                });
//
//        weatherRetrofit.getWeather1("深圳", "json",1,"f932e05d3673dd26f518fc9d10e92ca3")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//               .subscribe(new Action1<ResponseBody>() {
//                   @Override
//                   public void call(ResponseBody responseBody) {
//                       try {
//                           Log.d(TAG, "call: "+responseBody.string());
//                       } catch (IOException e) {
//                           e.printStackTrace();
//                       }
//                   }
//               });
    }

    /**
     * 普通get请求
     */
    private void initRetrofit1() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://op.juhe.cn").build();//创建retrofit的实例
        WeatherRetrofit weatherRetrofit = retrofit.create(WeatherRetrofit.class);//根据接口的字节码文件对象获取接口对象
        Call<ResponseBody> call = weatherRetrofit.getWeatherResonseBody();//调用接口中的方法
        call.enqueue(new Callback<ResponseBody>() {//调用call中的请求方法 enqueue（）异步请求 excute（）同步请求
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {//成功的获取相应
                    Log.d(TAG, "onResponse: ");
//                    String result=response.body().toString();
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onResponse: result:" + result);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: ");

            }
        });
    }

    /**
     * /get请求 通过@query注解动态传递参数的值
     */
    private void initRetrofit5() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://op.juhe.cn")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //如果服务器返回的是json格式的数据 设置GsonConverterFactory后 实现对对象的转化
                //相当于对服务器返回的json字符串直接进行解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();//创建retrofit的实例
        WeatherRetrofit weatherRetrofit = retrofit.create(WeatherRetrofit.class);//根据接口的字节码文件对象获取接口对象

//        Observable observable = weatherRetrofit.getWeatherByNetWork("深圳", "19f7c5051b12a7c73b69251f59ba534f");//调用接口中的方法
//        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() {
//            @Override
//            public void call(Object o) {
//                Weather w = (Weather) o;
//                Log.d(TAG, "call: w:" + w.getResult().getData().getRealtime().getWeather().getInfo());
//            }
//        });

//        Call<Weather> call=weatherRetrofit.getWeatherByNetWork1("深圳", "19f7c5051b12a7c73b69251f59ba534f");
//        call.enqueue(new Callback<Weather>() {
//            @Override
//            public void onResponse(Call<Weather> call, Response<Weather> response) {
//                Log.d(TAG, "onResponse: ");
//                if(response.isSuccessful()){
//                    Weather weather=response.body();
//                    Log.d(TAG, "onResponse: "+weather.getResult().getData().getRealtime().getWeather().getInfo());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Weather> call, Throwable t) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });


        Call<ResponseBody> call = weatherRetrofit.getWeatherByNetWork2("深圳", "19f7c5051b12a7c73b69251f59ba534f");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: ");
                if (response.isSuccessful()) {
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onResponse: result:" + result);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }


    public void initRetrofit01() {//普通get请求

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).build();//创建retrofit的实例

        INewsBiz iNewsBiz = retrofit.create(INewsBiz.class); //根据接口的字节码文件对象获取接口对象

        Call<ResponseBody> call = iNewsBiz.getResonseBody();//调用接口中的方法

        call.enqueue(new Callback<ResponseBody>() {   //调用call中的请求方法  enqueue()异步请求   execute()同步请求
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {//成功的获取响应
                    try {
                        String result = response.body().string();//获取响应对象中的字符串
                        Log.d(TAG, "onResponse: result:" + result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: t.getMessage()" + t.getMessage());
            }
        });
    }

    public void initRetrofit02() {//普通的get请求 返回的数据为String类型
//        Retrofit retrofit=new Retrofit
//                .Builder()
//                .baseUrl(Constant.BASE_URL)
//                //设置返回数据的适配器  get请求后可以返回String字符串类型的数据
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//        INewsBiz iNewsBiz=retrofit.create(INewsBiz.class);
//        Call<String> call=iNewsBiz.getJsonString();
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if(response.isSuccessful()){
//                    String result=response.body().toString();//获取响应对象中的字符串
//                    Log.d(TAG, "onResponse: result:"+result);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
    }

    public void initRetrofit03() {//普通的get请求 返回的数据为list集合
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.TEABASE_URL)
                //如果服务器返回的是json格式的数据 设置GsonConverterFactory后 实现对对象的转化
                //相当于对服务器返回的json字符串直接进行解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INewsBiz iNewsBiz = retrofit.create(INewsBiz.class);
        Call<List<Tea>> call = iNewsBiz.getTeas();
        call.enqueue(new Callback<List<Tea>>() {
            @Override
            public void onResponse(Call<List<Tea>> call, Response<List<Tea>> response) {
                if (response.isSuccessful()) {
                    List<Tea> list = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (Tea t : list) {
                        sb.append(t.getProduct_cat_name());
                    }
                    Log.d(TAG, "onResponse: sb.toString():" + sb.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Tea>> call, Throwable t) {

            }
        });

    }

    public void initRetrofit04() {//get请求 通过占位符指定path路径
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INewsBiz iNewsBiz = retrofit.create(INewsBiz.class);
        Call<NewsInfo> call = iNewsBiz.getNewsInfo("GetFeeds");
        call.enqueue(new Callback<NewsInfo>() {
            @Override
            public void onResponse(Call<NewsInfo> call, Response<NewsInfo> response) {
                if (response.isSuccessful()) {
                    List<NewsInfo.ParamzBean.FeedsBean> news = response.
                            body().getParamz().getFeeds();
                    StringBuffer sb = new StringBuffer();
                    for (NewsInfo.ParamzBean.FeedsBean feed : news) {
                        sb.append(feed.getData().getSubject());
                    }
                    Log.d(TAG, "onResponse: sb.toString():" + sb.toString());
                }
            }

            @Override
            public void onFailure(Call<NewsInfo> call, Throwable t) {

            }
        });
    }

    public void initRetrofit05() {//get请求 通过@query注解动态传递参数的值
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.TEABASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INewsBiz iNewsBiz = retrofit.create(INewsBiz.class);
        Call<List<Tea>> call = iNewsBiz.getTeaByNetWork(161);
        call.enqueue(new Callback<List<Tea>>() {
            @Override
            public void onResponse(Call<List<Tea>> call, Response<List<Tea>> response) {
                if (response.isSuccessful()) {
                    List<Tea> list = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (Tea t : list) {
                        sb.append(t.getProduct_cat_name());
                    }
                    Log.d(TAG, "onResponse: sb.toString():" + sb.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Tea>> call, Throwable t) {

            }
        });
    }

    public void initRetrofit06() {//get请求 请求通过@queryMap注解动态的传递多个参数
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INewsBiz iNewsBiz = retrofit.create(INewsBiz.class);
        //column=0&PageSize=20&pageIndex=1&val=100511D3BE5301280E0992C73A9DEC41"
        Map<String, String> map = new HashMap<>();
        map.put("column", "0");
        map.put("PageSize", "10");
        map.put("pageIndex", "1");
        map.put("val", "100511D3BE5301280E0992C73A9DEC41");
        Call<NewsInfo> call = iNewsBiz.getInfoByNetWork(map);
        call.enqueue(new Callback<NewsInfo>() {
            @Override
            public void onResponse(Call<NewsInfo> call, Response<NewsInfo> response) {
                if (response.isSuccessful()) {
                    List<NewsInfo.ParamzBean.FeedsBean> news = response.
                            body().getParamz().getFeeds();
                    StringBuffer sb = new StringBuffer();
                    for (NewsInfo.ParamzBean.FeedsBean feed : news) {
                        sb.append(feed.getData().getSubject());
                    }
                    Log.d(TAG, "onResponse: sb.toString():" + sb.toString());
                }
            }

            @Override
            public void onFailure(Call<NewsInfo> call, Throwable t) {

            }
        });
    }

    public void initRetrofit07() {//下载网络图片
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.IMAGE_URL).build();
        INewsBiz iNewsBiz = retrofit.create(INewsBiz.class);
        Call<ResponseBody> call = iNewsBiz.downLoadImage();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
//                    String str=response.body().string();
//                    byte[] buff=response.body().bytes();

                    InputStream in = response.body().byteStream();
                    Bitmap bm = BitmapFactory.decodeStream(in);
                    Log.d(TAG, "onResponse: bm:" + bm);
//                    iv.setImageBitmap(bm);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void initRetrofit08() {// 采用同步的访问方式
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).build();
        final INewsBiz iNewsBiz = retrofit.create(INewsBiz.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<ResponseBody> call = iNewsBiz.getResonseBody();
                try {
                    //采用同步的形式请求数据
                    Response<ResponseBody> response = call.execute();
                    String str = response.body().string();
                    //将获取的数据通过消息发送到主线程
                    Message message = Message.obtain();
                    message.obj = str;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String string = (String) msg.obj;
            Log.d(TAG, "handleMessage: string: " + string);
        }
    };

}
