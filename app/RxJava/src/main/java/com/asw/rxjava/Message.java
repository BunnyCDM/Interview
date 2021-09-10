package com.asw.rxjava;

import java.io.Serializable;

/**
 * Created by mac on 2017/4/11.
 */

public class Message implements Serializable {

    private final static long serialVersionUID=1l;
    private String msg;

    public Message(String msg){
        super();
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
