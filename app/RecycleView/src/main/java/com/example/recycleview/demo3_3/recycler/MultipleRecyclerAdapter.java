package com.example.recycleview.demo3_3.recycler;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.recycleview.R;

import java.util.List;

/**
 * Created by mac on 2020-04-10.
 * <p>
 * 这个是为了适配多布局而诞生
 * <p>
 * 继承BaseMultiItemQuickAdapter：
 * 第一个泛型MultipleItemEntity是数据实体类型；
 * 第二个MultipleViewHolder是ViewHolder其目的是为了支持扩展ViewHolder；
 */
public class MultipleRecyclerAdapter
        extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup {

    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;

    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String name;
        final int avatarColor;
        final String content;
        final int contentColor;
        int colors[] = {android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark};
        switch (holder.getItemViewType()) {

            case ItemType.TEXT_IMAGE:
                name = entity.getField(MultipleFields.NAME);
                avatarColor = entity.getField(MultipleFields.AVATAR_COLOR);

                holder.setText(R.id.name, name);
                holder.setBackgroundRes(R.id.avatar, colors[avatarColor]);
                holder.itemView.setBackgroundColor(Color.BLACK);
                break;


            case ItemType.TEXT_IMAGE_CONTENT:
                name = entity.getField(MultipleFields.NAME);
                content = entity.getField(MultipleFields.CONTENT);
                avatarColor = entity.getField(MultipleFields.AVATAR_COLOR);

                holder.setText(R.id.name, name);
                holder.setBackgroundRes(R.id.avatar,  colors[avatarColor]);
                holder.setText(R.id.content, content);
                holder.itemView.setBackgroundColor(Color.GREEN);
                break;


            case ItemType.TEXT_IMAGE_CONTENT_IMAGE:
                name = entity.getField(MultipleFields.NAME);
                content = entity.getField(MultipleFields.CONTENT);
                avatarColor = entity.getField(MultipleFields.AVATAR_COLOR);
                contentColor = entity.getField(MultipleFields.CONTENT_COLOR);

                holder.setText(R.id.name, name);
                holder.setBackgroundRes(R.id.avatar,  colors[avatarColor]);
                holder.setText(R.id.content, content);
                holder.setBackgroundRes(R.id.contentImage,  colors[contentColor]);
                holder.itemView.setBackgroundColor(Color.GRAY);
                break;

            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        //复用item
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }

    private void init() {
        //设置不同的item布局，addItemType绑定type和layout的关系
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_type_one);
        addItemType(ItemType.TEXT_IMAGE_CONTENT, R.layout.item_type_two);
        addItemType(ItemType.TEXT_IMAGE_CONTENT_IMAGE, R.layout.item_type_three);
        setSpanSizeLookup(this);//设置宽度监听
        openLoadAnimation();//开启动画（默认为渐变效果）
        isFirstOnly(false);//动画默认只执行一次，若想多次执行动画设置为false
    }

    public void refresh(List<MultipleItemEntity> data) {
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }


    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

}
