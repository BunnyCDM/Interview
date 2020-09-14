package com.example.http.base1.api;

/**
 * Created by mac on 2020-08-06.
 */
public interface ApiListener {
    /**
     * 发送成功监听
     *
     * @param api
     */
    void success(ApiUtil api);

    /**
     * 发送失败监听
     *
     * @param api
     */
    void failure(ApiUtil api);
}

