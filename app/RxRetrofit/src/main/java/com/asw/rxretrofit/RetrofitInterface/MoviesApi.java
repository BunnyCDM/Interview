package com.asw.rxretrofit.RetrofitInterface;


import com.asw.rxretrofit.bean.MovieBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by castl on 2016/5/6.
 */
public interface MoviesApi {


    @GET("/boxoffice/rank?")
    Observable<MovieBean> getMovies(@Query("area") String area, @Query("key") String key);
}
