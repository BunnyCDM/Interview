package com.example.listview.reflash.horizontal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview.R;
import com.example.listview.demo1.MyAdapterWithCommonViewHolder_2;
import com.example.listview.demo1.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2019/4/20.
 * <p>
 * 下拉刷新
 */
public class HorizontalDragModeActivity extends Activity {


    private List<Bean> mDatas;
    HorizontalDragMoreView mHorizontalDragMoreView;
    ListView mListView;
    MyAdapterWithCommonViewHolder_2 adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontaldragmore);

        mHorizontalDragMoreView = findViewById(R.id.mHorizontalDragMoreView);
        mListView = findViewById(R.id.mListView);

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

        mHorizontalDragMoreView.setLoadMoreView(new DefaultDragMoreView()).setLoadMoreCallBack(new ICallBack() {
            @Override
            public void loadMore() {
                mHorizontalDragMoreView.scrollBackToOrigin();
                Toast.makeText(HorizontalDragModeActivity.this,"loadMore",Toast.LENGTH_SHORT).show();
            }
        });


    }


}
