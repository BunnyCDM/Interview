package com.example.viewpager.wechat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.viewpager.R;
import com.example.viewpager.wechat.fragment.TabFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mac on 2019-09-07.
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mVpMain;
    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));

    private Button mBtnWeChat;
    private Button mBtnFriend;
    private Button mBtnFind;
    private Button mBtnMine;

    private SparseArray<TabFragment> mFragments = new SparseArray<>();

    private List<Button> mTabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initViewPagerAdapter();
    }

    private void initViews() {
        mVpMain = findViewById(R.id.vp_main);
        mBtnWeChat = findViewById(R.id.btn_wechat);
        mBtnFriend = findViewById(R.id.btn_friend);
        mBtnFind = findViewById(R.id.btn_find);
        mBtnMine = findViewById(R.id.btn_mine);
        mBtnWeChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取第一个Fragment
                TabFragment tabFragment = mFragments.get(0);
                if (tabFragment != null) {
                    tabFragment.changeTitle("微信 changed！");
                }
            }
        });
        mTabs.add(mBtnWeChat);
        mTabs.add(mBtnFriend);
        mTabs.add(mBtnFind);
        mTabs.add(mBtnMine);
    }

    private void initViewPagerAdapter() {
        // TODO: 2019-09-07 android:screenOrientation="portrait"，否则使用老一套会出错

        // TODO: 2019-09-07 FragmentStatePagerAdapter:Fragment被销毁，FragmentPagerAdapter：Fragment没有被销毁
        mVpMain.setOffscreenPageLimit(mTitles.size());
        mVpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                TabFragment tabFragment = TabFragment.newInstance(mTitles.get(i));
                if (i == 0) {
                    tabFragment.setOnTitleClickListener(new TabFragment.OnTitleClickListener() {
                        @Override
                        public void onClick(String title) {
                            changeWeChatTab(title);
                        }
                    });
                }
                return tabFragment;
            }

            @Override
            public int getCount() {
                return mTitles.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TabFragment fragment = (TabFragment) super.instantiateItem(container, position);
                mFragments.put(position, fragment);
                return fragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });

        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixe) {
                AppLogger.d("onPageScrolled: position=" + position + " positionOffset=" + positionOffset
                        + " positionOffsetPixe=" + positionOffsetPixe);
                if(positionOffset>0){
                    Button left = mTabs.get(position);
                    Button right = mTabs.get(position + 1);
                    left.setText((1 - positionOffset) + "");
                    right.setText(positionOffset + "");
                }
            }

            @Override
            public void onPageSelected(int position) {
                AppLogger.d("onPageSelected: position=" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                AppLogger.d("onPageScrollStateChanged: state=" + state);
            }
        });
    }


    public void changeWeChatTab(String title) {
        mBtnWeChat.setText(title);
    }


}
