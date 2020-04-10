package com.example.recycleview.demo2;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.recycleview.R;

import java.util.ArrayList;
import java.util.List;


/**
 * https://blog.csdn.net/mountain_hua/article/details/81268471
 */
public class MainDemo2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SimpleAdapterDemo2 mSimpleAdapterDemo2;
    private List<DataModel> mDatas;
    int colors[] = {android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo2);

        mDatas = new ArrayList<DataModel>();
        for (int i = 0; i < 30; i++) {
            int type = (int) ((Math.random() * 3) + 1);
            if (i <= 10) {
                type = 1;
            } else if (i > 10 && i <= 20) {
                type = 2;
            } else {
                type = 3;
            }
            DataModel dataModel = new DataModel();
            dataModel.avatarColor = colors[type - 1];
            dataModel.type = type;
            dataModel.name = "name:" + i;
            dataModel.content = "content:" + i;
            dataModel.contentColor = colors[(type + 1) % 3];
            mDatas.add(dataModel);
        }

        //1.通过findViewById拿到RecyclerView实例
        mRecyclerView = findViewById(R.id.mRecyclerView);

        //2.设置RecyclerView的布局管理
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
//                LinearLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                int type = mRecyclerView.getAdapter().getItemViewType(position);
                if (type == DataModel.TYPE_THREE) {
                    return gridLayoutManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);

        //3.初始化适配器
        mSimpleAdapterDemo2 = new SimpleAdapterDemo2(this, mDatas);
        //4.设置适配器
        mRecyclerView.setAdapter(mSimpleAdapterDemo2);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanIndex();
                outRect.top = 20;
                if (spanSize != gridLayoutManager.getSpanCount()) {
                    if (spanIndex == 1) {
                        outRect.left = 10;
                    } else {
                        outRect.right = 10;
                    }
                }
            }
        });

//        mSimpleAdapterDemo2.addList(mDatas);
//        mSimpleAdapterDemo2.notifyDataSetChanged();
    }


}
