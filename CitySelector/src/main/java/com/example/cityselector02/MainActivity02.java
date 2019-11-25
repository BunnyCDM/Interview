package com.example.cityselector02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.cityselector.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity02 extends AppCompatActivity {

    private Context context = MainActivity02.this;

    private ListView listView;
    private List<City> mlist = new ArrayList<City>();
    private ItemBeanAdapter mAdapter;
    private ClearEditText clearedit;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            mAdapter.updateListView(mlist);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                CityDBHelper dbHelper = new CityDBHelper(context);
                mlist = dbHelper.getCityDB().getAllCity();
                mHandler.sendEmptyMessage(0);
            }
        }).start();

        mAdapter = new ItemBeanAdapter(context, mlist);
        listView.setAdapter(mAdapter);
    }

    private void initView() {
        clearedit = (ClearEditText) findViewById(R.id.clear_edit_text);
        listView = (ListView) findViewById(R.id.list);
    }

}
