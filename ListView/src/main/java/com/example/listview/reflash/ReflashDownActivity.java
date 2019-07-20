package com.example.listview.reflash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.listview.MyAdapterWithCommonViewHolder_2;
import com.example.listview.R;
import com.example.listview.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2019/4/20.
 * <p>
 * 下拉刷新
 */
public class ReflashDownActivity extends Activity implements ReFlashListView.IReflashListener {


    private List<Bean> mDatas;
    ReFlashListView mListView;
    MyAdapterWithCommonViewHolder_2 adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflashown);

        mListView = (ReFlashListView) findViewById(R.id.mReFlashListView);


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

        mListView.setInterface(this);
    }

    @Override
    public void onReflash() {

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();

                mListView.reflashComplete();
            }
        },2000);

    }
}
