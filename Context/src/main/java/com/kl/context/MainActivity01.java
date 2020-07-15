package com.kl.context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;

public class MainActivity01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);


        final TextView mTextView = findViewById(R.id.mTextView);
        final EditText mEditText = findViewById(R.id.mEditText);
        mTextView.setText("共享数据是：" +((App) getApplicationContext()).getTextData());

        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App) getApplicationContext()).setTextData(mEditText.getText().toString());
                mTextView.setText("共享数据是：" + mEditText.getText().toString());
            }
        });

    }

    /**
     * context:可以作为全局信息的桥梁
     */
    private void context() {
        TextView textView = new TextView(this);
        //textView.setText("hello android");
        AppLogger.d("onCreate: " + getResources().getText(R.string.app_name));
        textView.setText(R.string.app_name);
        setContentView(textView);

//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.mipmap.ic_launcher);
//        setContentView(imageView);

    }
}
