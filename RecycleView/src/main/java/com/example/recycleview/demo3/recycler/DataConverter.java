package com.example.recycleview.demo3.recycler;

import java.util.ArrayList;

/**
 * Created by mac on 2020-04-10.
 * <p>
 * 数据转化器，一个基类
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public void clearData() {
        ENTITIES.clear();
    }

}