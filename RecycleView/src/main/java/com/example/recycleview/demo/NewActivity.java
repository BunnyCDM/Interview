package com.example.recycleview.demo;

import com.example.recycleview.demo.base.BaseMainActivity;


public class NewActivity extends BaseMainActivity {

    @Override
    public Class<?>[] getActivitys() {
        return ACTIVITY;
    }

    @Override
    public String[] getTitles() {
        return TITLE;
    }

    private static final Class<?>[] ACTIVITY = {
            LinearLayoutActivity.class,
            EndlessLinearLayoutActivity.class,
            SwipeRefreshLayoutActivity.class,
            PartialRefreshActivity.class,
            AlphaChangeActivity.class,
            ClickLoadingFootActivity.class,
            ExpandableRecyclerViewOneActivity.class};


    private static final String[] TITLE = {
            "LinearLayoutActivity",
            "EndlessLinearLayoutActivity",
            "SwipeRefreshLayoutActivity",
            "(局部刷新)PartialRefreshActivity",
            "AlphaChangeActivity",
            "ClickLoadingFootActivity",
            "ExpandableRecyclerViewOneActivity"};


}
