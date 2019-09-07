//package com.example.viewpager.wechat.view.transformer;
//
//import android.support.annotation.NonNull;
//import android.support.v4.view.ViewPager;
//import android.view.View;
//
///**
// * Created by mac on 2019-09-07.
// */
//public class RotateTransformer implements ViewPager.PageTransformer {
//
//
//
//    private static final float MIN_SCALE = 0.75f;
//    private static final float MIN_ALPHA = 0.5f;
//
//
//    @Override
//    public void transformPage(@NonNull View view, float v) {
//
//        if (position < -1) {
//            view.setScaleX(MIN_SCALE);
//            view.setScaleY(MIN_SCALE);
//
//
//        } else if (position <= 1) {
//            if (position < 0) {
//                //左边的页面
//                float scaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
//                view.setScaleX(scaleA);
//                view.setScaleY(scaleA);
//
//                float alphaA = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position);
//                view.setAlpha(alphaA);
//            } else {
//                //右边的页面
//                float scaleB = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);
//                view.setScaleX(scaleB);
//                view.setScaleY(scaleB);
//
//                float alphaB = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - position);
//                view.setAlpha(alphaB);
//
//            }
//
//        } else {
//            view.setScaleX(MIN_SCALE);
//            view.setScaleY(MIN_SCALE);
//
//        }
//
//
//    }
//}
