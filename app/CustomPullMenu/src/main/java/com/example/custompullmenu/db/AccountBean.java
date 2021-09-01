package com.example.custompullmenu.db;

import java.io.Serializable;

/**
 * 登录时存储数据
 */

public class AccountBean implements Serializable {

    String phone;
    String name;
    Long time;

    public AccountBean() {
    }

    public AccountBean(String phone, String name, Long time) {
        this.phone = phone;
        this.name = name;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
