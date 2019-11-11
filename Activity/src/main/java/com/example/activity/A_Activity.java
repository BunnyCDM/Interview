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
public class A_Activity extends AppCompatActivity {


    private TextView tv_Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppLogger.d("onCreate[A_Activity]: ");

        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("id", -1);
            Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
        }

        tv_Next = findViewById(R.id.tv_Next);
        tv_Next = findViewById(R.id.tv_Next);
        String info = String.format("TaskId:%d\nCurrentActivity is %s", getTaskId(), toString());
        tv_Next.setText(info);

        tv_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A_Activity.this, B_Activity.class);
                City city = new City("深圳", "shenzhen", "15007190899");
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
        AppLogger.d("onRestart[A_Activity]: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppLogger.d("onStart[A_Activity]: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AppLogger.d("onNewIntent[A_Activity]: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppLogger.d("onResume[A_Activity]: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppLogger.d("onPause[A_Activity]: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppLogger.d("onStop[A_Activity]: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppLogger.d("onDestroy[A_Activity]: ");
    }
}
