package com.example.recycleview.demo3;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.recycleview.R;
import com.example.recycleview.demo3.recycler.BaseDecoration;
import com.example.recycleview.demo3.refresh.RefreshHandler;



public class MainDemo3Activity extends AppCompatActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private RefreshHandler mRefreshHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo3);

        //1.通过findViewById拿到RecyclerView实例
        mRefreshLayout = findViewById(R.id.srl_index);
        mRecyclerView = findViewById(R.id.rv_index);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshHandler.firstPage();
            }
        });

        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);

        //2.设置RecyclerView的布局管理
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(this, R.color.common_blue), 5));


        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());


    }


}
