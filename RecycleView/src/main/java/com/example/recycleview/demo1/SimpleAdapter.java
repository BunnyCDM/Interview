package com.example.recycleview.demo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recycleview.R;

import java.util.List;

/**
 * Created by mac on 2020-04-04.
 */
public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<String> datas;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public SimpleAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * @param viewGroup
     * @param i
     * @return 创建ViewHolder
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_single_textview, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    /**
     * @param myViewHolder
     * @param position     绑定ViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        myViewHolder.tv.setText(datas.get(position));

        if (onItemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    onItemClickListener.onItemClick(myViewHolder.itemView, layoutPosition);
                }
            });


            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(myViewHolder.itemView, layoutPosition);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    /**
     * 插入一条数据
     *
     * @param position:下标
     */
    public void addData(int position) {
        datas.add(position, "insert");
        notifyItemInserted(position);
    }


    /**
     * 删除一条数据
     *
     * @param position:下标
     */
    public void delData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }
}


class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }
}
