package com.example.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.listview.bean.Bean;
import com.example.listview.utils.CommonAdapter;
import com.example.listview.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

//http://www.cnblogs.com/allin/archive/2010/05/11/1732200.html
//ListView点击失效：http://blog.csdn.net/zhufuing/article/details/8677407
//DataSetObservable、notifyDataSetChanged分析:
//http://blog.csdn.net/chunqiuwei/article/details/39934169
//http://www.cnblogs.com/kissazi2/p/3721941.html
//http://www.cnblogs.com/nailperry/p/4668553.html
public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private ListView mListView;
    private List<Bean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
//        initData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
    }

    private void initData() {
        mDatas = new ArrayList<>();

        Bean bean = new Bean("AndroidGet新技能Get1", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get2", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
        bean = new Bean("AndroidGet新技能Get3", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        mDatas.add(bean);
//        bean = new Bean("AndroidGet新技能Get4", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
//        mDatas.add(bean);
//        bean = new Bean("AndroidGet新技能Get5", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
//        mDatas.add(bean);
//        bean = new Bean("AndroidGet新技能Get6", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
//        mDatas.add(bean);
//        bean = new Bean("AndroidGet新技能Get7", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
//        mDatas.add(bean);
//        bean = new Bean("AndroidGet新技能Get8", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
//        mDatas.add(bean);
//        bean = new Bean("AndroidGet新技能Get9", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
//        mDatas.add(bean);

        //传统方式
//        mListView.setAdapter(new MyAdapter(this,mDatas));

        //封装ViewHolder方式
//        mListView.setAdapter(new MyAdapterWithCommonViewHolder_1(this,mDatas));

        //封装Adapter方式
//        mListView.setAdapter(new MyAdapterWithCommonViewHolder_2(this,mDatas));

        //封装Adapter方式,解决抢占焦点
        mListView.setAdapter(new CommonAdapter<Bean>(MainActivity.this, mDatas, R.layout.item_listview) {
            @Override
            public void convert(ViewHolder holder, final Bean bean) {
                holder.setText(R.id.title, bean.getTitle())
                        .setText(R.id.desc, bean.getDesc())
                        .setText(R.id.time, bean.getTime())
                        .setText(R.id.phone, bean.getPhone());

                final CheckBox cb = holder.getView(R.id.id_cb);
                cb.setChecked(bean.isChecked());

                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setChecked(cb.isChecked());
                    }
                });
            }
        });


        //封装Adapter方式,解决抢占焦点
//        final List<Integer> mPos = new ArrayList<Integer>();
//        mListView.setAdapter(new CommonAdapter<Bean>(CityActivity.this, mDatas, R.layout.item_listview) {
//            @Override
//            public void convert(final ViewHolder holder, final Bean bean) {
//                holder.setText(R.id.title, bean.getTitle())
//                        .setText(R.id.desc, bean.getDesc())
//                        .setText(R.id.time, bean.getTime())
//                        .setText(R.id.phone, bean.getPhone());
//
//                final CheckBox cb = holder.getView(R.id.id_cb);
//
//                cb.setChecked(false);
//                if (mPos.contains(holder.getPosition())) {
//                    cb.setChecked(true);
//                }
//
//                cb.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (cb.isChecked()) {
//                            mPos.add(holder.getPosition());
//                        } else {
//                            mPos.remove((Integer) holder.getPosition());
//                        }
//
//                    }
//                });
//            }
//        });

    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.head:
                Log.d(TAG, "onClick: 1");
                break;
            case R.id.foot:
                Log.d(TAG, "onClick: 2");
                break;
        }

    }

    private List<Bean> getData() {
        List<Bean> data = new ArrayList<>();
        Bean bean = new Bean("AndroidGet新技能Get", "Android打造万能的ListView和GridView适配器", "2019-01-23", "10086");
        data.add(bean);
        return data;
    }
}
