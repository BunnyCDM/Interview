package com.example.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by mac on 2020-03-28.
 */
public class Employee extends BaseObservable {

    private String mLastName;
    private String mFirstName;

    private boolean mIsFired=false;

    public Employee(String lastName, String firstName) {
        this.mLastName = lastName;
        this.mFirstName = firstName;
    }


    @Bindable
    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public boolean isIsFired() {
        return mIsFired;
    }

    public void setIsFired(boolean mIsFired) {
        this.mIsFired = mIsFired;
        notifyChange();
    }
}
