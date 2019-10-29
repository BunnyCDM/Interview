package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.baselibrary.utils.log.AppLogger;

/**
 * Created by mac on 2019-09-24.
 */
public class B_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppLogger.d("onCreate[B_Activity]: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppLogger.d("onStart[B_Activity]: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AppLogger.d("onNewIntent[B_Activity]: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppLogger.d("onRestart[B_Activity]: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppLogger.d("onResume[B_Activity]: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppLogger.d("onPause[B_Activity]: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppLogger.d("onStop[B_Activity]: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppLogger.d("onDestroy[B_Activity]: ");
    }
}
