package com.example.recycleview.demo3.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.recycleview.R;
import com.example.recycleview.demo3.banner.BannerCreator;

import java.util.ArrayList;
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
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;

    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher_round)
                        .into((ImageView) holder.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher_round)
                        .into((ImageView) holder.getView(R.id.img_multiple));

                holder.setText(R.id.tv_multiple, text);
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
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
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);//轮播图
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

    @Override
    public void onItemClick(int position) {

    }
}
