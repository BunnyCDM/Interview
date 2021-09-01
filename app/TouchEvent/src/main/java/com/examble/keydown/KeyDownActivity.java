package com.examble.keydown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.touchevent.R;



public class KeyDownActivity extends AppCompatActivity {

    private final static String TAG = "bunny";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keydown);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: ============================keyCode is "+keyCode+"，event is "+event.toString());
        return super.onKeyDown(keyCode, event);
    }
}
