package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;

public class MainActivity extends AppCompatActivity {

    private TextView tv_Next;
    private TextView tv_OneSelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppLogger.d("onCreate[MainActivity]: ");
        setContentView(R.layout.activity_main);

        tv_Next = findViewById(R.id.tv_Next);
        String info = String.format("TaskId:%d\nCurrentActivity is %s", getTaskId(), toString());
        tv_Next.setText(info);

        tv_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, A_Activity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
            }
        });
        tv_OneSelf = findViewById(R.id.tv_OneSelf);
        tv_OneSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();//Foreground //Background
        AppLogger.d("onRestart[MainActivity]: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AppLogger.d("onNewIntent[MainActivity]: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        AppLogger.d("onStart[MainActivity]: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppLogger.d("onResume[MainActivity]: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppLogger.d("onPause[MainActivity]: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppLogger.d("onStop[MainActivity]: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppLogger.d("onDestroy[MainActivity]: ");
    }

}
