package com.example.generate_aar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.generate_library.JarActivity;
import com.example.generate_library.UiTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTest(View view) {
        AppLogger.d("onClickTest: ");
        UiTest.showLog();
        startActivity(new Intent(this, JarActivity.class));
    }

}
