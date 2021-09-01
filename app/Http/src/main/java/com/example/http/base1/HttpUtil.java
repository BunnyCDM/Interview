package com.example.http.base1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mac on 2020-08-05.
 * URLConnection
 * HttpURLConnection
 */
public class HttpUtil {

    private static String dealResponseResult(InputStream stream) throws IOException {
        //StringBuilder非线程安全的
        //StringBuffer对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的
        StringBuffer buffer = new StringBuffer();
        //String中的对象是不可变的，也就可以理解为常量，显然线程安全
        String str;

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        while ((str = reader.readLine()) != null) {
            buffer.append(str);
        }
        return buffer.toString();
    }


    /**
     * 返回这个url对应的网址内容
     *
     * @param sendUrl
     * @return
     */
    public static String get(String sendUrl) {

        try {
            URL url = new URL(sendUrl);

            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                int response = conn.getResponseCode();//获得服务器是否成功处理该请求
                if (response == HttpURLConnection.HTTP_OK) {

                    InputStream stream = conn.getInputStream();
                    return dealResponseResult(stream);

                } else {
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String post(String sendUrl, String params) {

        try {
            URL url = new URL(sendUrl);

            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("POST");

                //设置请求体的类型是文本类型
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                //设置请求体的长度
                conn.setRequestProperty("Content-Length", String.valueOf(params.getBytes().length));

                //获得输入流，向服务器写入数据
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(params.getBytes());

                int response = conn.getResponseCode();//获得服务器是否成功处理该请求
                if (response == HttpURLConnection.HTTP_OK) {

                    InputStream stream = conn.getInputStream();
                    return dealResponseResult(stream);

                } else {
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;

    }


    public static String put(String sendUrl, String params) {

        try {
            URL url = new URL(sendUrl);

            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("PUT");


                //默认请求体类型，application/x-www-form-urlencoded
                //请求体的长度，自动计算

                //获得输入流，向服务器写入数据
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(params.getBytes());

                int response = conn.getResponseCode();//获得服务器是否成功处理该请求
                if (response == HttpURLConnection.HTTP_OK) {

                    InputStream stream = conn.getInputStream();
                    return dealResponseResult(stream);

                } else {
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String delete(String sendUrl, String params) {

        try {
            URL url = new URL(sendUrl);

            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("DELETE");


                //默认请求体类型，application/x-www-form-urlencoded
                //请求体的长度，自动计算

                //获得输入流，向服务器写入数据
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(params.getBytes());

                int response = conn.getResponseCode();//获得服务器是否成功处理该请求
                if (response == HttpURLConnection.HTTP_OK) {

                    InputStream stream = conn.getInputStream();
                    return dealResponseResult(stream);

                } else {
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 返回这个url对应的的图片
     *
     * @param sendUrl
     * @return
     */
    public static Bitmap loadImage(Context context, String sendUrl) {

        try {
            URL url = new URL(sendUrl);

            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");

                InputStream stream = conn.getInputStream();

                FileOutputStream outputStream = null;
                String fileName = String.valueOf(System.currentTimeMillis());
                File fileDownload = null;
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //File parent = Environment.getExternalStorageDirectory();

                    //创建文件路径
                    File parent = new File(context.getExternalFilesDir(null).getPath() + "/");
                    if (!parent.exists()) {
                        parent.mkdir();
                    }

                    fileDownload = new File(parent, fileName);
                    outputStream = new FileOutputStream(fileDownload);
                }


                byte[] bytes = new byte[2 * 1024];
                int lens = 0;
                if (outputStream != null) {

                    while ((lens = stream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, lens);
                    }

                    return BitmapFactory.decodeFile(fileDownload.getAbsolutePath());
                } else {
                    return null;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;

    }


}
