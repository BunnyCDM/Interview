package com.example.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RelativeLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_relative_layout);

        dynamic();

    }

    private RelativeLayout root;
    private TextView tv;

    private void dynamic() {
        root = new RelativeLayout(this);
        setContentView(root);

        tv = new TextView(this);

        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv.setText("RelativeLayoutActivity");
        root.addView(tv,params);
    }

}
