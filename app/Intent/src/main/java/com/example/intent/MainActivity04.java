package com.example.intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.baselibrary.utils.log.AppLogger;

public class MainActivity04 extends AppCompatActivity {

    public static final String ACTION = "com.example.intent.intent.action.MainActivity02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main04);


        Uri uri = getIntent().getData();
        AppLogger.d("onCreate: " + uri);
    }
}
