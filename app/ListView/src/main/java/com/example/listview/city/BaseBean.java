package com.example.listview.city;

import java.io.Serializable;

public class BaseBean implements Serializable {

    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
