package com.asw.rxretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.asw.rxretrofit.R;
import com.asw.rxretrofit.jvhe.ConstellationActivity;
import com.asw.rxretrofit.jvhe.HistoryTodayActivity;
import com.asw.rxretrofit.jvhe.IdiomActivity;
import com.asw.rxretrofit.jvhe.MovieActivity;
import com.asw.rxretrofit.jvhe.TrainTimeActivity;
import com.asw.rxretrofit.jvhe.WeatherActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_idiom;
    private LinearLayout ll_history;
    private LinearLayout ll_train;
    private LinearLayout ll_movie;
    private LinearLayout ll_constellation;
    private LinearLayout ll_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ll_idiom = (LinearLayout) findViewById(R.id.ll_idiom);
        ll_history = (LinearLayout) findViewById(R.id.ll_history);
        ll_train = (LinearLayout) findViewById(R.id.ll_train);
        ll_movie = (LinearLayout) findViewById(R.id.ll_movie);
        ll_constellation = (LinearLayout) findViewById(R.id.ll_constellation);
        ll_weather = (LinearLayout) findViewById(R.id.ll_weather);

        ll_idiom.setOnClickListener(this);
        ll_history.setOnClickListener(this);
        ll_train.setOnClickListener(this);
        ll_movie.setOnClickListener(this);
        ll_constellation.setOnClickListener(this);
        ll_weather.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_weather:
                startActivity(new Intent(this, WeatherActivity.class));
                break;
            case R.id.ll_idiom:
                startActivity(new Intent(this, IdiomActivity.class));
                break;
            case R.id.ll_train:
                startActivity(new Intent(this, TrainTimeActivity.class));
                break;
            case R.id.ll_history:
                startActivity(new Intent(this, HistoryTodayActivity.class));
                break;
            case R.id.ll_movie:
                startActivity(new Intent(this, MovieActivity.class));
                break;
            case R.id.ll_constellation:
                startActivity(new Intent(this, ConstellationActivity.class));
                break;
        }
    }
}
