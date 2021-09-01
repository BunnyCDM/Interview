package com.example.recycleview.demo3.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.recycleview.demo3.recycler.DataConverter;
import com.example.recycleview.demo3.recycler.MultipleRecyclerAdapter;


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

    String response="{\n" +
            "  \"code\": 0,\n" +
            "  \"message\": \"OK\",\n" +
            "  \"total\": 100,\n" +
            "  \"page_size\": 6,\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"goodsId\": 0,\n" +
            "      \"spanSize\": 4,\n" +
            "      \"banners\": [\n" +
            "        \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "        \"https://cdn.cnbj0.fds.api.mi-img.com/b2c-mimall-media/b9540da01aef9a00a5c640b1c98b955a.jpg\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 1,\n" +
            "      \"text\": \"明星单品\",\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 2,\n" +
            "      \"text\": \"小米5c  64GB 移动版\",\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 3,\n" +
            "      \"text\": \"米家智能摄像机\",\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 4,\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 5,\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 6,\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 7,\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 5,\n" +
            "      \"text\": \"智能生活，从这里开始\",\n" +
            "      \"spanSize\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 5,\n" +
            "      \"text\": \"测试描述5\",\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 6,\n" +
            "      \"text\": \"测试描述6\",\n" +
            "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
            "      \"spanSize\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"goodsId\": 5,\n" +
            "      \"text\": \"我又是野广告\",\n" +
            "      \"spanSize\": 4\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
