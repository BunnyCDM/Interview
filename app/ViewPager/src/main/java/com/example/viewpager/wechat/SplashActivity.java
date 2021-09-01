package com.example.viewpager.wechat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.viewpager.R;
import com.example.viewpager.wechat.fragment.SplashFragment;
import com.example.viewpager.wechat.fragment.TabFragment;
import com.example.viewpager.wechat.view.TabView;
import com.example.viewpager.wechat.view.transformer.ScaleTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mac on 2019-09-07.
 */
public class SplashActivity extends AppCompatActivity {

    private ViewPager mVpMain;

    private int[] mResIds = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVpMain = findViewById(R.id.vp_main);
        mVpMain.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return SplashFragment.newInstance(mResIds[i]);
            }

            @Override
            public int getCount() {
                return mResIds.length;
            }
        });


        mVpMain.setPageTransformer(true,new ScaleTransformer());

    }


}
