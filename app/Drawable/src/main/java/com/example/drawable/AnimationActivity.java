package com.example.drawable;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.CustomAnimation;
import com.example.baselibrary.utils.log.AppLogger;

/**
 * View 动画
 */
public class AnimationActivity extends AppCompatActivity {
    ImageView mImageViewTop, mImageViewBellow;
    Button mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7;
    MyClickListener mMyClickListener;

    Animation mAnimation;

    AnimationSet mAnimationSet;

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
        mButton6 = (Button) findViewById(R.id.btn_Button6);
        mButton7 = (Button) findViewById(R.id.btn_Button7);
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
        mButton6.setOnClickListener(mMyClickListener);
        mButton7.setOnClickListener(mMyClickListener);
    }

    public class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_Button1: {

                    /**
                     * 代码设置
                     */
//                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
//                    alphaAnimation.setDuration(1000);
//                    mImageViewTop.startAnimation(alphaAnimation);

                    /**
                     * xml设置
                     */
                    mAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                            R.anim.my_anim_alpha);
                    mImageViewTop.startAnimation(mAnimation);
                    break;
                }
                case R.id.btn_Button2: {

                    /**
                     * 代码设置
                     */
//                    Animation scaleAnimation = new ScaleAnimation(0.0f, 1.0f,
//                            0.0f, 1.0f,
//                            Animation.RELATIVE_TO_SELF, 0.5f,
//                            Animation.RELATIVE_TO_SELF, 0.5f);
//                    scaleAnimation.setDuration(1000);
//                    mImageViewTop.startAnimation(scaleAnimation);

                    /**
                     * xml设置
                     */
                    mAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                            R.anim.my_anim_scale);
                    mImageViewTop.startAnimation(mAnimation);
                    break;
                }


                case R.id.btn_Button3: {
                    /**
                     * 代码设置
                     */
//                    Animation translateAnimation = new TranslateAnimation(0, 100,
//                            0, 100);
//                    translateAnimation.setDuration(1000);
//                    mImageViewBellow.startAnimation(translateAnimation);
                    /**
                     * xml设置
                     */
                    mAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                            R.anim.my_anim_translate);
                    mImageViewTop.startAnimation(mAnimation);

                    break;
                }

                case R.id.btn_Button4: {
                    /**
                     * 代码设置
                     */
                    //Animation rotateAnimation = new RotateAnimation(0, 360);
                    //Animation rotateAnimation = new RotateAnimation(0, 360,100,50);
//                    Animation rotateAnimation = new RotateAnimation(0, 360,
//                            Animation.RELATIVE_TO_SELF, 0.5f,
//                            Animation.RELATIVE_TO_SELF, 0.5f);
//                    rotateAnimation.setDuration(1000);
//                    mImageViewTop.startAnimation(rotateAnimation);

                    /**
                     * xml设置
                     */
                    mAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                            R.anim.my_anim_rotate);
                    mImageViewTop.startAnimation(mAnimation);
                    break;
                }

                case R.id.btn_Button6: {
                    /**
                     * 代码设置
                     */
//                    mAnimationSet=new AnimationSet(true);
//                    mAnimationSet.setDuration(1000);
//
//                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
//                    alphaAnimation.setDuration(1000);
//                    mAnimationSet.addAnimation(alphaAnimation);
//
//                    Animation translateAnimation = new TranslateAnimation(0, 100,
//                            0, 100);
//                    translateAnimation.setDuration(1000);
//                    mAnimationSet.addAnimation(translateAnimation);
//
//                    mImageViewTop.startAnimation(mAnimationSet);

                    /**
                     * xml设置
                     */

                    mAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                            R.anim.anim_set);
                    mAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            AppLogger.d("onAnimationStart: ");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            AppLogger.d("onAnimationEnd: ");
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            AppLogger.d("onAnimationRepeat: ");
                        }
                    });
                    mImageViewTop.startAnimation(mAnimation);
                    break;

                }

                case R.id.btn_Button7: {
                    CustomAnimation customAnimation = new CustomAnimation();
                    customAnimation.setDuration(1000);
                    mImageViewTop.startAnimation(customAnimation);
                    break;
                }

                case R.id.btn_Button5:
                    mAnimationDrawable = (AnimationDrawable) mImageViewBellow.getDrawable();
                    mAnimationDrawable.start();
                    break;
            }
        }

    }
}
