package com.example.recycleview.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.recycleview.R;

import java.util.ArrayList;
import java.util.List;


public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StaggeredAdapter mStaggeredAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mStaggeredAdapter = new StaggeredAdapter(this, mDatas);
        mRecyclerView.setAdapter(mStaggeredAdapter);

        //设置RecyclerView的布局管理
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        mStaggeredAdapter.setOnItemClickListener(new StaggeredAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Toast.makeText(StaggeredGridLayoutActivity.this,"onItemClick"+position,Toast.LENGTH_SHORT).show();
            }
        });

        mStaggeredAdapter.setOnItemLongClickListener(new StaggeredAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position, String data) {
                mStaggeredAdapter.delData(position);
            }
        });


    }
}
