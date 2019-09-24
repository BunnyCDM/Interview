package com.example.recycleview.old.bean;

/**
 * Created by mac on 2019/2/12.
 */
public class IrDeviceType {

    private String title;
    private int image;


    public IrDeviceType() {

    }

    public IrDeviceType(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
