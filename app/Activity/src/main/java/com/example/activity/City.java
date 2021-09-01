package com.example.activity;

import java.io.Serializable;

/**
 * Created by mac on 2019-11-09.
 */
public class City implements Serializable {

    private String name;
    private String pinyin;
    private String lalong;

    public City() {
    }

    public City(String name, String pinyin, String lalong) {
        this.name = name;
        this.pinyin = pinyin;
        this.lalong = lalong;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setLalong(String lalong) {
        this.lalong = lalong;
    }

    public String getName() {
        return this.name;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public String getLalong() {
        return this.lalong;
    }


}
