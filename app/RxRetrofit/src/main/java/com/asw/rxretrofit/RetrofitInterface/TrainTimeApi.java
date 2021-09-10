package com.asw.rxretrofit.RetrofitInterface;


import com.asw.rxretrofit.bean.TrainTimeByNO;
import com.asw.rxretrofit.bean.TrainTimeByStation;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by castl on 2016/5/6.
 */
public interface TrainTimeApi {


    @GET("/onebox/train/query?")
    Observable<TrainTimeByNO> getTrainNo(@Query("train") String train, @Query("key") String key);

    @GET("/onebox/train/query_ab?")
    Observable<TrainTimeByStation> getTrainStation(@Query("from") String from, @Query("to") String to, @Query("key") String key);

}
