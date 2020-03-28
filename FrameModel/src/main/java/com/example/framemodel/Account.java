package com.example.framemodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.framemodel.BR;

/**
 * Created by mac on 2020-03-28.
 */
public class Account extends BaseObservable {

    private String name;

    private int level;


    public Account() {
    }

    public Account(String name, int level) {
        this.name = name;
        this.level = level;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }
}
