package com.example.dagger.data;

import javax.inject.Inject;

/**
 * Created by mac on 2017/12/5.
 */

// TODO: 2020-11-23 测试一
//public class User {
//
//    private String name;
//
//    @Inject
//    public User(Child child) {
//
//    }
//
//    public User(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//}

// TODO: 2020-11-23 测试二
//public class User {
//
//    private String name;
//
//    public User(Child child) {
//
//    }
//
//    public User(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//}

// TODO: 2020-11-23 测试四
public class User {

    private String name;

    @Inject
    public User() {
    }

    public User(Child child) {

    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
