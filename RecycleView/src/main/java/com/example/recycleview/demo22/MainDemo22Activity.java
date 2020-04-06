package com.example.recycleview.demo22;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.recycleview.R;

import java.util.ArrayList;
import java.util.List;


/**
 * https://blog.csdn.net/mountain_hua/article/details/81268471
 */
public class MainDemo22Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SimpleAdapterDemo22 mSimpleAdapterDemo22;
    private List<DataModel> mDatas;
    int colors[] = {android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo2);

        mDatas = new ArrayList<DataModel>();


        List<DataModelOne> list1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataModelOne dataModelOne = new DataModelOne();
            dataModelOne.avatarColor = colors[0];
            dataModelOne.name = "name:" + i;
            list1.add(dataModelOne);
        }
        List<DataModelTwo> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataModelTwo dataModelTwo = new DataModelTwo();
            dataModelTwo.avatarColor = colors[1];
            dataModelTwo.name = "name:" + i;
            dataModelTwo.content = "content:";
            list2.add(dataModelTwo);
        }
        List<DataModelThree> list3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataModelThree dataModelThree = new DataModelThree();
            dataModelThree.avatarColor = colors[2];
            dataModelThree.name = "name:" + i;
            dataModelThree.content = "content:";
            dataModelThree.contentColor=colors[2];
            list3.add(dataModelThree);
        }

        //1.通过findViewById拿到RecyclerView实例
        mRecyclerView = findViewById(R.id.mRecyclerView);
        //2.初始化适配器
        mSimpleAdapterDemo22 = new SimpleAdapterDemo22(this, mDatas);
        //3.设置适配器
        mRecyclerView.setAdapter(mSimpleAdapterDemo22);

        //4.设置RecyclerView的布局管理
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
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mSimpleAdapterDemo22.addList(list1,list2,list3);
        mSimpleAdapterDemo22.notifyDataSetChanged();
    }


}
