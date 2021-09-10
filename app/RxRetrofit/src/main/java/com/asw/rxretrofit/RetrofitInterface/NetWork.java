package com.asw.rxretrofit.RetrofitInterface;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by castl on 2016/5/6.
 */
public class NetWork {

    private static WeatherApi weatherApi;

    private static TrainTimeApi trainApi;

    private static MoviesApi moviesApi;

    private static IdiomApi idiomApi;

    private static HistoryTodayApi historytodatApi;

    private static ConstellationApi constellatonApi;



    public static WeatherApi getWeatherApi() {
        if (weatherApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://op.juhe.cn")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherApi = retrofit.create(WeatherApi.class);
        }
        return weatherApi;
    }

    public static TrainTimeApi getTrainApi() {
        if (trainApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://op.juhe.cn")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            trainApi = retrofit.create(TrainTimeApi.class);
        }
        return trainApi;
    }

    public static MoviesApi getMoviesApi() {
        if (moviesApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://v.juhe.cn")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            moviesApi = retrofit.create(MoviesApi.class);
        }
        return moviesApi;
    }

    public static IdiomApi getIdiomApi() {
        if (idiomApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://v.juhe.cn")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            idiomApi = retrofit.create(IdiomApi.class);
        }
        return idiomApi;
    }

    public static HistoryTodayApi getHistorytodatApi() {
        if (historytodatApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://v.juhe.cn")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            historytodatApi = retrofit.create(HistoryTodayApi.class);
        }
        return historytodatApi;
    }

    public static ConstellationApi getConstellationApi() {
        if (constellatonApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://web.juhe.cn:8080")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            constellatonApi = retrofit.create(ConstellationApi.class);
        }
        return constellatonApi;
    }


}
