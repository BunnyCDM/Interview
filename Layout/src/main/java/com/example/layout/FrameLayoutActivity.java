package com.example.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FrameLayoutActivity extends AppCompatActivity {


    ImageView mImageView1;
    ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        //可以使用RelativeLayout完全代替FrameLayout，只不过FrameLayout轻量级
        //在不需要调整子元素位置的话，可以使用FrameLayout

        mImageView1 = findViewById(R.id.mImageView1);
        mImageView2 = findViewById(R.id.mImageView2);

        findViewById(R.id.mFrameLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageView1.getVisibility() == View.VISIBLE) {
                    showB();
                } else {
                    showA();
                }

            }
        });

        showA();
    }

    private void showA() {
        mImageView1.setVisibility(View.VISIBLE);
        mImageView2.setVisibility(View.INVISIBLE);
    }

    private void showB() {
        mImageView1.setVisibility(View.INVISIBLE);
        mImageView2.setVisibility(View.VISIBLE);
    }
}
