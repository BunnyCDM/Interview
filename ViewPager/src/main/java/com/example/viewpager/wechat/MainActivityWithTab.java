package com.example.viewpager.wechat;

import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.example.viewpager.wechat.view.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mac on 2019-09-07.
 */
public class MainActivityWithTab extends AppCompatActivity {

    ViewPager mVpMain;
    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));

    private TabView mTabWeChat;
    private TabView mTabFriend;
    private TabView mTabFind;
    private TabView mTabMine;

    private SparseArray<TabFragment> mFragmets = new SparseArray<>();

    private List<TabView> mTabs = new ArrayList<>();

    private static final String BUNDLE_KEY_POS = "bundle_key_pos";
    private int mCurTabPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainwthtab);

        if (savedInstanceState != null) {
            mCurTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS, 0);
        }

        initViews();

        initViewPagerAdapter();

        initEvents();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_KEY_POS, mVpMain.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        mVpMain = findViewById(R.id.vp_main);
        mTabWeChat = findViewById(R.id.tab_wechat);
        mTabFriend = findViewById(R.id.tab_friend);
        mTabFind = findViewById(R.id.tab_find);
        mTabMine = findViewById(R.id.tab_mine);

        mTabWeChat.setIconAndText(R.mipmap.weixin, R.mipmap.weixin_select, "微信");
        mTabFriend.setIconAndText(R.mipmap.tongxunlu, R.mipmap.tongxunlu_select, "通讯录");
        mTabFind.setIconAndText(R.mipmap.faxian, R.mipmap.faxian_select, "发现");
        mTabMine.setIconAndText(R.mipmap.wo, R.mipmap.wo_select, "我");

        mTabs.add(mTabWeChat);
        mTabs.add(mTabFriend);
        mTabs.add(mTabFind);
        mTabs.add(mTabMine);

        setCurrentTab(mCurTabPos);//默认选中第一个
    }

    private void initEvents() {
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            final int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVpMain.setCurrentItem(finalI, false);
                    setCurrentTab(finalI);
                }
            });
        }
    }

    private void setCurrentTab(int pos) {
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            if (i == pos) {
                tabView.setProgress(1);
            } else {
                tabView.setProgress(0);
            }
        }
    }

    private void initViewPagerAdapter() {
        // TODO: 2019-09-07 android:screenOrientation="portrait"，否则使用老一套会出错

        // TODO: 2019-09-07 FragmentStatePagerAdapter:Fragment被销毁，FragmentPagerAdapter：Fragment没有被销毁
        mVpMain.setOffscreenPageLimit(mTitles.size());
        mVpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                TabFragment tabFragment = TabFragment.newInstance(mTitles.get(i));
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
                mFragmets.put(position, fragment);
                return fragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragmets.remove(position);
                super.destroyItem(container, position, object);
            }
        });

        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixe) {
                AppLogger.d("onPageScrolled: position=" + position + " positionOffset=" + positionOffset + " positionOffsetPixe=" + positionOffsetPixe);
                if (positionOffset > 0) {
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position + 1);
                    left.setProgress((1 - positionOffset));
                    right.setProgress(positionOffset);
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


}
