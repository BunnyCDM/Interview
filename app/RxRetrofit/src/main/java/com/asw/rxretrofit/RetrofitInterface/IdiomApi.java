package com.asw.rxretrofit.RetrofitInterface;


import com.asw.rxretrofit.bean.IdiomDic;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by castl on 2016/5/6.
 */
public interface IdiomApi {

    @GET("/chengyu/query?")
    Observable<IdiomDic> getIdiomInfo(@Query("word") String word, @Query("key") String key);
}
