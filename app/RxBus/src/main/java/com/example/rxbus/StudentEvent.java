package com.example.rxbus;

/**
 * Created by mac on 2019-11-26.
 * 创建需要发送的事件楽
 */

public class StudentEvent {

    private String id;
    private String  name;

    public StudentEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
