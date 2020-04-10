package com.example.recycleview.demo2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recycleview.R;

import java.util.List;

/**
 * Created by mac on 2020-04-04.
 */
public class SimpleAdapterDemo2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<DataModel> datas;

    public SimpleAdapterDemo2(Context context, List<DataModel> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * @param viewGroup
     * @param viewType
     * @return 创建ViewHolder
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {

            case DataModel.TYPE_ONE: {
                View view = inflater.inflate(R.layout.item_type_one, viewGroup, false);
                return new TypeOneViewHolder(view);
            }

            case DataModel.TYPE_TWO: {
                View view = inflater.inflate(R.layout.item_type_two, viewGroup, false);
                return new TypeTwoViewHolder(view);
            }

            case DataModel.TYPE_THREE: {
                View view = inflater.inflate(R.layout.item_type_three, viewGroup, false);
                return new TypeThreeViewHolder(view);
            }

        }

        return null;
    }

    /**
     * @param holder
     * @param position 绑定ViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        //方式一
//        int viewType = getItemViewType(position);
//        switch (viewType) {
//            case DataModel.TYPE_ONE:
//                ((TypeOneViewHolder)holder).bindHolder(datas.get(position));
//                break;
//
//            case DataModel.TYPE_TWO:
//                ((TypeTwoViewHolder)holder).bindHolder(datas.get(position));
//                break;
//
//            case DataModel.TYPE_THREE:
//                ((TypeThreeViewHolder)holder).bindHolder(datas.get(position));
//                break;
//        }
        //方式二
        ((TypeAbstractViewHolder) holder).bindHolder(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).type;
    }

    public void addList(List<DataModel> list) {
        datas.addAll(list);
    }

}


