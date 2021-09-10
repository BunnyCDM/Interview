package com.asw.rxretrofit.RetrofitInterface;


import com.asw.rxretrofit.bean.HistoryToday;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by castl on 2016/5/6.
 */
public interface HistoryTodayApi {

    @GET("/todayOnhistory/queryEvent.php?")
    Observable<HistoryToday> getHistoryTodayInfo(@Query("date") String date, @Query("key") String key);

}
