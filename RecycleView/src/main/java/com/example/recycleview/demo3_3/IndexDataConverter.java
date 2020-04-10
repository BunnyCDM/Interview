package com.example.recycleview.demo3_3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.recycleview.demo3_3.recycler.DataConverter;
import com.example.recycleview.demo3_3.recycler.ItemType;
import com.example.recycleview.demo3_3.recycler.MultipleFields;
import com.example.recycleview.demo3_3.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by mac on 2020-04-10.
 */
public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {

            try {
                final JSONObject data = dataArray.getJSONObject(i);

                int type = data.getInteger("type");//id
                final int spanSize = data.getInteger("spanSize");//id
                final String name = data.getString("name");//text
                final int avatarColor = data.getInteger("avatarColor");//spanSize

                String content = "郭德纲";
                int contentColor = 0;
                if (data.containsKey("content")) {
                    content = data.getString("content");//imageUrl
                }

                if (data.containsKey("contentColor")) {
                    contentColor = data.getInteger("contentColor");//null
                }

                //final JSONArray banners = data.getJSONArray("banners");

//            final ArrayList<String> bannerImages = new ArrayList<>();
//            int type = 0;
//            if (imageUrl == null && text != null) {
//                type = ItemType.TEXT;
//            } else if (imageUrl != null && text == null) {
//                type = ItemType.IMAGE;
//            } else if (imageUrl != null) {
//                type = ItemType.TEXT_IMAGE;
//            } else if (banners != null) {
//                type = ItemType.BANNER;
//                //Banner的初始化
//                final int bannerSize = banners.size();
//                for (int j = 0; j < bannerSize; j++) {
//                    final String banner = banners.getString(j);
//                    bannerImages.add(banner);
//                }
//            }

                if (type == 1) {
                    type = ItemType.TEXT_IMAGE;
                } else if (type == 2) {
                    type = ItemType.TEXT_IMAGE_CONTENT;
                } else {
                    type = ItemType.TEXT_IMAGE_CONTENT_IMAGE;

                }
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setField(MultipleFields.ITEM_TYPE, type)
                        .setField(MultipleFields.SPAN_SIZE, spanSize)
//                    .setField(MultipleFields.ID, id)
//                    .setField(MultipleFields.TEXT, text)
                        .setField(MultipleFields.NAME, name)
//                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                        .setField(MultipleFields.AVATAR_COLOR, avatarColor)
//                    .setField(MultipleFields.BANNERS, bannerImages)
                        .setField(MultipleFields.CONTENT, content)
                        .setField(MultipleFields.CONTENT_COLOR, contentColor)
                        .build();

                ENTITIES.add(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return ENTITIES;
    }

}

