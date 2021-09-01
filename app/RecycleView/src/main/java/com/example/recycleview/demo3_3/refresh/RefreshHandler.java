package com.example.recycleview.demo3_3.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.recycleview.demo3_3.recycler.DataConverter;
import com.example.recycleview.demo3_3.recycler.MultipleRecyclerAdapter;


/**
 * Created by mac on 2020-04-10.
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    //简单工厂模式
    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }

    public void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求,把下面一行代码放到网络请求回调的success中
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage() {
        //BEAN.setDelayed(1000);
        final JSONObject object = JSON.parseObject(response);
        BEAN.setTotal(object.getInteger("total")).setPageSize(object.getInteger("page_size"));
        //设置Adapter
        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
        //滑动最后一个Item的时候回调onLoadMoreListener方法
        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
        RECYCLERVIEW.setAdapter(mAdapter);
        BEAN.addIndex();
    }

    //分页功能
    private void paging() {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();//当前页码数

        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            //数据加载完毕
            mAdapter.loadMoreEnd(true);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    CONVERTER.clearData();
                    //成功获取更多数据
                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                    //累加数量
                    BEAN.setCurrentCount(mAdapter.getData().size());
                    //加载完成
                    mAdapter.loadMoreComplete();
                    BEAN.addIndex();
                }
            }, 1000);
        }
    }


    @Override
    public void onRefresh() {
        refresh();
    }


    @Override
    public void onLoadMoreRequested() {
        paging();
    }

    String response = "{\n" +
            "  \"code\": 0,\n" +
            "  \"message\": \"OK\",\n" +
            "  \"total\": 36,\n" +
            "  \"page_size\": 2,\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"type\":1,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 0,\n" +
            "      \"name\": \"name:1\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":1,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 0,\n" +
            "      \"name\": \"name:2\"\n" +
            "    },\n" +
            "    \n" +
            "    {\n" +
            "      \"type\":1,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 0,\n" +
            "      \"name\": \"name:3\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":1,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 0,\n" +
            "      \"name\": \"name:4\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":1,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 0,\n" +
            "      \"name\": \"name:5\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":1,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 0,\n" +
            "      \"name\": \"name:6\"\n" +
            "    },\n" +
            "    \n" +
            "    {\n" +
            "      \"type\":2,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 1,\n" +
            "      \"name\": \"name:1\",\n" +
            "      \"content\": \"content:1\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":2,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 1,\n" +
            "      \"name\": \"name:2\",\n" +
            "      \"content\": \"content:2\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":2,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 1,\n" +
            "      \"name\": \"name:3\",\n" +
            "      \"content\": \"content:3\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":2,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 1,\n" +
            "      \"name\": \"name:4\",\n" +
            "      \"content\": \"content:4\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":2,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 1,\n" +
            "      \"name\": \"name:5\",\n" +
            "      \"content\": \"content:5\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":2,\n" +
            "      \"spanSize\":1,\n" +
            "      \"avatarColor\": 1,\n" +
            "      \"name\": \"name:6\",\n" +
            "      \"content\": \"content:6\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":3,\n" +
            "      \"spanSize\":2,\n" +
            "      \"avatarColor\": 2,\n" +
            "      \"name\": \"name:1\",\n" +
            "      \"content\": \"content:1\",\n" +
            "      \"contentColor\": 2\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":3,\n" +
            "      \"spanSize\":2,\n" +
            "      \"avatarColor\": 2,\n" +
            "      \"name\": \"name:2\",\n" +
            "      \"content\": \"content:2\",\n" +
            "      \"contentColor\": 2\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":3,\n" +
            "      \"spanSize\":2,\n" +
            "      \"avatarColor\": 2,\n" +
            "      \"name\": \"name:3\",\n" +
            "      \"content\": \"content:3\",\n" +
            "      \"contentColor\": 2\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":3,\n" +
            "      \"spanSize\":2,\n" +
            "      \"avatarColor\": 2,\n" +
            "      \"name\": \"name:4\",\n" +
            "      \"content\": \"content:4\",\n" +
            "      \"contentColor\": 2\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":3,\n" +
            "      \"spanSize\":2,\n" +
            "      \"avatarColor\": 2,\n" +
            "      \"name\": \"name:5\",\n" +
            "      \"content\": \"content:5\",\n" +
            "      \"contentColor\": 2\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"type\":3,\n" +
            "      \"spanSize\":2,\n" +
            "      \"avatarColor\": 2,\n" +
            "      \"name\": \"name:6\",\n" +
            "      \"content\": \"content:6\",\n" +
            "      \"contentColor\": 2\n" +
            "    }\n" +
            "\n" +
            "  ]\n" +
            "}";
}
