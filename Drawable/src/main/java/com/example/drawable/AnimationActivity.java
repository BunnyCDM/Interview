package com.example.drawable;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity {
    ImageView mImageViewTop, mImageViewBellow;
    Button mButton1, mButton2, mButton3, mButton4, mButton5;
    MyClickListener mMyClickListener;

    Animation mAnimation;

    Animation myAnimation_Alpha;
    Animation myAnimation_Scale;
    Animation myAnimation_Translate;
    Animation myAnimation_Rotate;

    AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mImageViewTop = (ImageView) findViewById(R.id.iv_ImageView1);
        mImageViewBellow = (ImageView) findViewById(R.id.iv_ImageView2);
        mButton1 = (Button) findViewById(R.id.btn_Button1);
        mButton2 = (Button) findViewById(R.id.btn_Button2);
        mButton3 = (Button) findViewById(R.id.btn_Button3);
        mButton4 = (Button) findViewById(R.id.btn_Button4);
        mButton5 = (Button) findViewById(R.id.btn_Button5);
    }

    private void initData() {
        mMyClickListener = new MyClickListener();
    }

    private void initEvent() {
        mImageViewBellow.setImageResource(R.drawable.animation_lis);
        mButton1.setOnClickListener(mMyClickListener);
        mButton2.setOnClickListener(mMyClickListener);
        mButton3.setOnClickListener(mMyClickListener);
        mButton4.setOnClickListener(mMyClickListener);
        mButton5.setOnClickListener(mMyClickListener);
    }

    public class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_Button1:

	/*			  mAnimation = AnimationUtils.loadAnimation( MainActivity.this,
				  R.anim.my_anim_alpha);
				  mImageViewTop.startAnimation(mAnimation);*/

                    myAnimation_Alpha = new AlphaAnimation(0.1f, 1.0f);
                    myAnimation_Alpha.setDuration(1000);
                    mImageViewTop.startAnimation(myAnimation_Alpha);
                    break;

                case R.id.btn_Button2:
			/*	mAnimation = AnimationUtils.loadAnimation(MainActivity.this,
						R.anim.my_anim_scale);
				mImageViewTop.startAnimation(mAnimation);*/

                    myAnimation_Scale = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    myAnimation_Scale.setDuration(1000);
                    mImageViewTop.startAnimation(myAnimation_Scale);
                    break;

                case R.id.btn_Button3:
                    mAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                            R.anim.my_anim_translate);
                    mImageViewTop.startAnimation(mAnimation);

                    break;

                case R.id.btn_Button4:
                    mAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                            R.anim.my_anim_rotate);
                    mImageViewTop.startAnimation(mAnimation);
                    break;

                case R.id.btn_Button5:
                    mAnimationDrawable=(AnimationDrawable) mImageViewBellow.getDrawable();
                    mAnimationDrawable.start();
                    break;
            }
        }

    }
}
