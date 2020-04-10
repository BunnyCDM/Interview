package com.example.recycleview.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.recycleview.R;

import java.util.ArrayList;
import java.util.List;


/**
 * https://blog.csdn.net/mountain_hua/article/details/81268471
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SimpleAdapter mSimpleAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

        //1.通过findViewById拿到RecyclerView实例
        mRecyclerView = findViewById(R.id.mRecyclerView);
        //2.设置RecyclerView的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //3.初始化适配器
        mSimpleAdapter = new SimpleAdapter(this, mDatas);
        //4.设置适配器
        mRecyclerView.setAdapter(mSimpleAdapter);

        //5.设置RecyclerView的item分割线,使用系统默认方式
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //5.设置RecyclerView的item分割线,使用自定义方式
//        mRecyclerView.addItemDecoration(new com.example.recycleview.demo1.DividerItemDecoration(this,
//                LinearLayoutManager.VERTICAL));

        //5.设置RecyclerView的item分割线,使用GridViewItem方式
        mRecyclerView.addItemDecoration(new DividerGridViewItemDecoration(this));

        //6.动画效果，以下为默认动画，git上面有相应的动画效果.连接地址如下：
        // https://github.com/gabrielemariotti/RecyclerViewItemAnimators
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //7.设置回调监听
        mSimpleAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_listview:
                //设置线性布局，以线性布局展示，可以设置横向和纵向，默认纵向
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;

            case R.id.action_gridview:
                //设置网格布局，以网格形式展示，类似GridView效果
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;

            case R.id.action_hor_gridview:
                //设置瀑布流布局
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;

            case R.id.action_staggered:
                //设置瀑布流布局
                startActivity(new Intent(MainActivity.this, StaggeredGridLayoutActivity.class));
                break;

            case R.id.action_add:
                mSimpleAdapter.addData(1);
                break;

            case R.id.action_del:
                mSimpleAdapter.delData(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
