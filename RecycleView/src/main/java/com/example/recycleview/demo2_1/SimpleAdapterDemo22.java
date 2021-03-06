package com.example.recycleview.demo2_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recycleview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mac on 2020-04-04.
 */
public class SimpleAdapterDemo22 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    private LayoutInflater inflater;
    private Context context;

    public SimpleAdapterDemo22(Context context) {
        this.context = context;
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

            case TYPE_ONE: {
                View view = inflater.inflate(R.layout.item_type_one, viewGroup, false);
                return new TypeOneViewHolder(view);
            }

            case TYPE_TWO: {
                View view = inflater.inflate(R.layout.item_type_two, viewGroup, false);
                return new TypeTwoViewHolder(view);
            }

            case TYPE_THREE: {
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
        int viewType = getItemViewType(position);
        int realPosition = position - mPosition.get(viewType);
        switch (viewType) {

            case TYPE_ONE: {
                ((TypeOneViewHolder) holder).bindHolder(list1.get(realPosition));
                break;
            }

            case TYPE_TWO: {
                ((TypeTwoViewHolder)holder).bindHolder(list2.get(realPosition));
                break;
            }

            case TYPE_THREE: {
                ((TypeThreeViewHolder)holder).bindHolder(list3.get(realPosition));
                break;
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public int getItemCount() {
        return types.size();
    }


    public void addList(List<DataModelOne> list1, List<DataModelTwo> list2, List<DataModelThree> list3) {
        addListByType(TYPE_ONE, list1);
        addListByType(TYPE_TWO, list2);
        addListByType(TYPE_THREE, list3);

        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
    }

    private List<DataModelOne> list1;
    private List<DataModelTwo> list2;
    private List<DataModelThree> list3;
    private List<Integer> types = new ArrayList<>();
    private Map<Integer, Integer> mPosition = new HashMap<>();

    private void addListByType(int type, List list) {
        mPosition.put(type, types.size());
        for (int i = 0; i < list.size(); i++) {
            types.add(type);
        }
    }

}


