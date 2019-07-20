package com.example.asynchronized;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.tv);
        textView.setText("synchronized的四种用法" + "\n"
                + "https://blog.csdn.net/sinat_32588261/article/details/72880159");

    }
}
