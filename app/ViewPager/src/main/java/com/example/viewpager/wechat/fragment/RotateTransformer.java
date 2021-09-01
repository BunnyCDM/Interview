package com.example.viewpager.wechat.fragment;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;

/**
 * Created by mac on 2020-07-11.
 */
public class RotateTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.5f;
    private static final float MAX_ROATE = 15;

    @Override
    public void transformPage(@NonNull View view, float position) {
        AppLogger.d("transformPage: tag=" + view.getTag() + " position=" + position);


        if (position < -1) {
            view.setRotation(-MAX_ROATE);
            view.setPivotY(view.getHeight());
            view.setPivotX(view.getWidth());

        } else if (position <= 1) {
            if (position < 0) {
                //左边的页面
                view.setPivotY(view.getHeight());

                view.setPivotX(0.5f * view.getWidth() + 0.5f * (-position) * view.getWidth());

                view.setRotation(MAX_ROATE * position);

            } else {
                //右边的页面
                view.setPivotY(view.getHeight());

                view.setPivotX(0.5f * view.getWidth() * (1 - position));

                view.setRotation(MAX_ROATE * position);
            }

        } else {
            view.setRotation(MAX_ROATE);
            view.setPivotY(view.getHeight());
            view.setPivotX(0);
        }


    }


}
