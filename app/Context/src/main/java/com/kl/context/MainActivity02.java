package com.kl.context;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);

        final TextView mTextView = findViewById(R.id.mTextView);
        final EditText mEditText = findViewById(R.id.mEditText);
        mTextView.setText("共享数据是：" +((App) getApplicationContext()).getTextData());

        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App) getApplicationContext()).setTextData(mEditText.getText().toString());
            }
        });
    }

}
