package com.example.viewpager.one;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.viewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/harvic880925/article/details/38453725
 */
public class MainActivity01 extends AppCompatActivity {

    private View view1;
    private View view2;
    private View view3;

    private ViewPager viewPager;//对应的viewPage

    private List<View> views;//view数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);

        viewPager = findViewById(R.id.mViewPager);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);

        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);

        PagerAdapter pagerAdapter = new PagerAdapter() { //适配器

            @Override
            public int getCount() { //返回要滑动的View个数
                AppLogger.d("getCount: len=" + views.size());
                return views.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) { //做了两件事，第一：将视图添加到container中，第二：返回当前view
                container.addView(views.get(position));
                AppLogger.d("instantiateItem: position=" + position);
                return views.get(position);
            }

            @Override
            public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) { //对于这个函数暂不做解释，后续在做解释
                AppLogger.d("isViewFromObject: arg0=" + arg0 + " arg1=" + arg1);
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) { //从当前container中删除指定位置（position）的view
                //super.destroyItem(container, position, object);0
                AppLogger.d("destroyItem: position=" + position);
                container.removeView(views.get(position));
            }

        };

        viewPager.setAdapter(pagerAdapter);
    }
}
