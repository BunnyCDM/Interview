package com.asw.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mac on 2017/4/15.
 *
 * @GET("/onebox/weather/query?") Observable<Weather> getWeatherInfo(@Query("cityname") String phone,
 * @Query("key") String key);
 */

//        http://v.juhe.cn/weather/index?cityname=%E5%8C%97%E4%BA%AC&format=2&dtype=json&key=f932e05d3673dd26f518fc9d10e92ca3
//        http://op.juhe.cn/onebox/weather/query?cityname=深圳&key=19f7c5051b12a7c73b69251f59ba534f
public interface WeatherRetrofit {

    @GET("/onebox/weather/query?cityname=深圳&key=19f7c5051b12a7c73b69251f59ba534f")
    Call<ResponseBody> getWeatherResonseBody();

    //案例五：有一个键值对的情况，如果不确定时，需要使用此方法，使用@query（键名）将你的值输入:
    @GET("/onebox/weather/query?")
    Observable<Weather> getWeatherByNetWork(@Query("cityname") String cityname, @Query("key") String key);

    @GET("/onebox/weather/query?")
    Call<Weather> getWeatherByNetWork1(@Query("cityname") String cityname, @Query("key") String Key);

    @GET("/onebox/weather/query?")
    Call<ResponseBody> getWeatherByNetWork2(@Query("cityname") String cityname, @Query("key") String Key);

    @GET("/weather/index?")
    Observable<Weather> getWeather(@Query("cityname") String cityname, @Query("dtype") String dtype, @Query("format") int format, @Query("key") String key);

    @GET("/weather/index?")
    Observable<ResponseBody> getWeather1(@Query("cityname") String cityname, @Query("dtype") String dtype, @Query("format") int format, @Query("key") String key);

}
