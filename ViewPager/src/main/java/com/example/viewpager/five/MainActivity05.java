package com.example.viewpager.five;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.viewpager.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity05 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main05);

        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());

        FragAdapter adapter=new FragAdapter(getSupportFragmentManager(),fragments);

        ViewPager viewPager=findViewById(R.id.mViewPager);
        viewPager.setAdapter(adapter);



    }
}
