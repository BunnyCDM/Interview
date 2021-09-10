package com.asw.rxretrofit.RetrofitInterface;


import com.asw.rxretrofit.bean.Constellation;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by castl on 2016/5/6.
 */
public interface ConstellationApi {

    @GET("/constellation/getAll")
    Observable<Constellation> getConstellation(@Query("consName") String consName, @Query("type") String type, @Query("key") String key);
}
