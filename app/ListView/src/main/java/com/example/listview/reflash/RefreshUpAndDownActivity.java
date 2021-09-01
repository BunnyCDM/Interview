package com.example.listview.reflash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.listview.R;
import com.example.listview.demo1.MyAdapterWithCommonViewHolder_2;
import com.example.listview.demo1.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2020-04-08.
 */
public class RefreshUpAndDownActivity extends AppCompatActivity {

    private List<Bean> mDatas;
    private RefreshUpAndDownListView mListView;
    MyAdapterWithCommonViewHolder_2 adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshupanddown);

        mListView = findViewById(R.id.mRefreshUpAndDownListView);

        mDatas = new ArrayList<>();
        Bean bean = new Bean("AndroidGet新技能Get1", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get2", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get3", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get4", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get5", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get6", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get7", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get8", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get9", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);

        adapter = new MyAdapterWithCommonViewHolder_2(this, mDatas);
        mListView.setAdapter(new MyAdapterWithCommonViewHolder_2(this, mDatas));

        mListView.setOnRefreshListener(new RefreshUpAndDownListView.OnRefreshUpAndDownListener() {
            @Override
            public void onRefresh() {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();

                        mListView.reflashComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                Bean bean = new Bean("AndroidGet新技能Get", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
                mDatas.add(bean);
                if (mDatas.size() > 10) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mListView.noMoreComplete();
                        }
                    }, 2000);

                } else {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();

                            mListView.loadMoreComplete();
                        }
                    }, 2000);
                }
            }
        });
    }

}
