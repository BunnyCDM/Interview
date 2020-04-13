package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baselibrary.utils.log.AppLogger;

/**
 * Created by mac on 2019-09-24.
 */
public class B_Activity extends AppCompatActivity {

    private TextView tv_Next;
    private TextView tv_OneSelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppLogger.d("onCreate[B_Activity]: ");

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                City city = (City) bundle.getSerializable("city");
                String name = city.getName();
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            }
        }

        tv_Next = findViewById(R.id.tv_Next);
        String info = String.format("TaskId:%d\nCurrentActivity is %s", getTaskId(), toString());
        tv_Next.setText(info);

        tv_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(B_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tv_OneSelf = findViewById(R.id.tv_OneSelf);
        tv_OneSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(B_Activity.this, B_Activity.class);
                City city = new City("合肥", "hefei", "15007190899");
                Bundle bundle = new Bundle();
                bundle.putSerializable("city", city);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppLogger.d("onRestart[B_Activity]: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AppLogger.d("onNewIntent[B_Activity]: ");
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                City city = (City) bundle.getSerializable("city");
                String name = city.getName();
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppLogger.d("onStart[B_Activity]: ");
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
