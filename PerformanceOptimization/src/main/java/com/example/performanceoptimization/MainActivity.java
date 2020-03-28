package com.example.performanceoptimization;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        calculate();
    }

    private void findView(){
        info=findViewById(R.id.mTextView);

    }
    private void calculate(){
        StringBuilder sb=new StringBuilder();

        ActivityManager am= (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int mc=am.getMemoryClass();
        int lmc=am.getLargeMemoryClass();

        sb.append("mc:"+mc+"\n");
        sb.append("lmc:"+lmc+"\n");

        info.setText(sb.toString());
    }
}
