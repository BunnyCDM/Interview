package com.example.http.base1.api;

import android.os.Environment;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.http.base1.response.OkHttpCallback;
import com.example.http.base3.request.RequestParams;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by mac on 2020-08-06.
 * okhttp
 */
public class OkHttpUtil {

    private static OkHttpClient mOkHttpClient = null;

    private enum REQUEST_TYPE {
        POST, PUT, DELETE
    }

    public static void init() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient()
                    .newBuilder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .writeTimeout(5000, TimeUnit.MILLISECONDS);
            mOkHttpClient = clientBuilder.build();
        }
    }

    public static void get(String url, OkHttpCallback okHttpCallback) {
        Call call = null;
        try {
            HashMap<String, String> commonHap = ApiCommonParams.fetchCommonsParams();
            url = getFinalUrl(url, commonHap);
            Request request = new Request.Builder().url(url).build();
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallback);
        } catch (Exception e) {
            e.printStackTrace();
            okHttpCallback.onFailure(call, new IOException("get", e));
        }
    }

    public static String getFinalUrl(String url, HashMap<String, String> urlParamsMap) {
        if (urlParamsMap != null) {
            String paramString = getUrlAppendStr(urlParamsMap);
            url += url.contains("?") ? "&" : "?";
            url += paramString;
        }
        return url;
    }

    private static String getUrlAppendStr(HashMap<String, String> urlParamsMap) {
        StringBuilder result = new StringBuilder();
        for (HashMap.Entry<String, String> entry : urlParamsMap.entrySet()) {
            try {
                if (result.length() > 0) {
                    result.append("&");
                }
                result.append(entry.getKey());
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (Throwable e) {
                return "";
            }
        }
        return result.toString();
    }

    private static RequestBody createEncodingBuilderBody(HashMap<String, String> bodyMap) {
        FormBody.Builder builder = new FormBody.Builder();

        for (HashMap.Entry<String, String> entry : bodyMap.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    public static void post(String url, OkHttpCallback okHttpCallback, HashMap<String, String> bodyMap) {
        Call call = null;
        try {
            RequestBody body = createEncodingBuilderBody(bodyMap);
            Request request = new Request.Builder().url(url).post(body).build();
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallback);
        } catch (Throwable e) {
            e.printStackTrace();
            okHttpCallback.onFailure(call, new IOException("get", e));
        }
    }


    public static void put(String url, OkHttpCallback okHttpCallback, HashMap<String, String> bodyMap) {
        Call call = null;
        try {
            RequestBody body = createEncodingBuilderBody(bodyMap);
            Request request = new Request.Builder().put(body).url(url).build();

            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallback);
        } catch (Throwable e) {
            e.printStackTrace();
            okHttpCallback.onFailure(call, new IOException("put", e));
        }
    }


    public static void delete(String url, OkHttpCallback okHttpCallback, HashMap<String, String> bodyMap) {
        Call call = null;
        try {
            RequestBody body = createEncodingBuilderBody(bodyMap);
            Request request = new Request.Builder().delete(body).url(url).build();

            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallback);
        } catch (Throwable e) {
            e.printStackTrace();
            okHttpCallback.onFailure(call, new IOException("delete", e));
        }
    }


    public static void sendRequestWithBody(String url, OkHttpCallback okHttpCallback, HashMap<String, String> bodyMap,
                                           REQUEST_TYPE type) {

        Call call = null;
        try {
            RequestBody body = createEncodingBuilderBody(bodyMap);
            Request request = null;
            if (type == REQUEST_TYPE.POST) {
                request = new Request.Builder().post(body).url(url).build();
            } else if (type == REQUEST_TYPE.PUT) {
                request = new Request.Builder().put(body).url(url).build();
            } else if (type == REQUEST_TYPE.DELETE) {
                request = new Request.Builder().delete(body).url(url).build();
            }
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallback);
        } catch (Throwable e) {
            e.printStackTrace();
            okHttpCallback.onFailure(call, new IOException("get", e));
        }


    }

    //POST请求-普通参数请求(表单方式提交)
//            OkHttpClient.Builder clientBuilder = new OkHttpClient()
//                    .newBuilder()
//                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
//                    .readTimeout(5000, TimeUnit.MILLISECONDS)
//                    .writeTimeout(5000, TimeUnit.MILLISECONDS);
//            OkHttpClient okHttpClient=clientBuilder.build();
//
//            FormBody.Builder builder = new FormBody.Builder();
//            builder.add("name", mNickName);
//            builder.add("certId", "");
//            builder.add("sex", "");
//            builder.add("nickname", mNickName);
//            builder.add("logo", "");
//            builder.add("merCode", "");
//
//            RequestBody body =builder.build();
//
//            Request request = new Request.Builder()
//            .header("CARDLAN_AUTH",AppConstants.Variable.loginRspBean.getToken())
//            .url("http://47.107.120.118:8046/bang/user/modifyAppUserInfo")
//            .post(body)
//            .build();
//
//            Call call =okHttpClient.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    AppLogger.d( "onFailure: ");
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    AppLogger.d( "onResponse: "+response.toString());
//                }
//            });


    //POST请求-提交Json(json方式提交)
//            OkHttpClient.Builder clientBuilder = new OkHttpClient()
//                    .newBuilder()
//                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
//                    .readTimeout(5000, TimeUnit.MILLISECONDS)
//                    .writeTimeout(5000, TimeUnit.MILLISECONDS);
//            OkHttpClient okHttpClient=clientBuilder.build();
//
//            ModifyUserInfoReq req=new ModifyUserInfoReq();
//            req.setName(mNickName);
//            req.setCertId("");
//            req.setSex("");
//            req.setNickname(mNickName);
//            req.setLogo("");
//            req.setMerCode("");
//            Gson gson = new Gson();
//            //使用Gson将对象转换为json字符串
//            String json = gson.toJson(req);
//
//            //MediaType  设置Content-Type 标头中包含的媒体类型值
//            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//            RequestBody body = FormBody.create(mediaType, json);
//
//            Request request = new Request.Builder()
//            .header("CARDLAN_AUTH",AppConstants.Variable.loginRspBean.getToken())
//            .url("http://47.107.120.118:8046/bang/user/modifyAppUserInfo")
//            .post(body)
//            .build();
//
//            Call call =okHttpClient.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    AppLogger.d( "onFailure: ");
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    AppLogger.d( "onResponse: "+response.toString());
//                }
//            });


    //POST请求-提交file
//    OkHttpClient.Builder clientBuilder = new OkHttpClient()
//            .newBuilder()
//            .connectTimeout(5000, TimeUnit.MILLISECONDS)
//            .readTimeout(5000, TimeUnit.MILLISECONDS)
//            .writeTimeout(5000, TimeUnit.MILLISECONDS);
//    OkHttpClient okHttpClient=clientBuilder.build();
//
//    File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
//        if (!file.exists()) {
//        AppLogger.e("file is not exit!");
//        return;
//    }
//
//    RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//    Request.Builder builder = new Request.Builder();
//    Request request = builder.post(requestBody).url(BASE_URL + "postFile").build();
//    execute(request);


    //POST请求-提交文件(文件方式提交)
//    OkHttpClient.Builder clientBuilder = new OkHttpClient()
//            .newBuilder()
//            .connectTimeout(5000, TimeUnit.MILLISECONDS)
//            .readTimeout(5000, TimeUnit.MILLISECONDS)
//            .writeTimeout(5000, TimeUnit.MILLISECONDS);
//    OkHttpClient okHttpClient=clientBuilder.build();
//
//    MultipartBody.Builder requestBody = new MultipartBody.Builder();
//        requestBody.setType(MultipartBody.FORM);
//
//    RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//        requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + name + "\""),body);
//
//    RequestBody body_ = RequestBody.create(null, string);
//        requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + name + "\""),body_);
//
//
//    Request request =new Request.Builder().url(url).post(requestBody.build()).build();
//    Call call =okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//            AppLogger.d( "onFailure: ");
//        }
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            AppLogger.d( "onResponse: "+response.toString());
//        }
//    });

    // TODO: 2020-08-06 可以对照CommonRequest中的post请求对参数的封装以及cookie添加
    // TODO: 2020-08-06 可以对比MainActivity01、MainActivity02中下载图片，若图片过大或者大小不清楚，要记得压缩
    // TODO: 2020-08-06 可以参考MainActivity02中上传下载进度处理

}
