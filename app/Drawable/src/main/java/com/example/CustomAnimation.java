package com.example;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.baselibrary.utils.log.AppLogger;

/**
 * Created by mac on 2020-07-08.
 */
public class CustomAnimation extends Animation {

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        AppLogger.d("initialize: 获取目标控件宽高 is " + width + " ," + height);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        AppLogger.d("applyTransformation: interpolatedTime is " + interpolatedTime);

        /**
         * interpolatedTime：设置的时间
         * t：目标
         */

        //t.getMatrix().setTranslate(200, 200);
        //t.getMatrix().setTranslate(200 * interpolatedTime, 200 * interpolatedTime);
        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime * 10) * 20), 0);
        super.applyTransformation(interpolatedTime, t);
    }


}
