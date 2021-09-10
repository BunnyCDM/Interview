package com.asw.rxjava;

import java.io.Serializable;

/**
 * Created by mac on 2017/4/11.
 */

public class MessageTask implements Serializable {
    private final static long serialVersionUID=1l;
    private int tag;
    private String msg;

    public MessageTask(int tag, String msg) {
        super();
        this.tag = tag;
        this.msg = msg;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
