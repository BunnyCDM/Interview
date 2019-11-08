package com.example.cityselector;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by meixi
 * 邮箱：13164716840@163.com
 * 日期：2018/12/3 08
 */
public class FastJsonUtils {


    /**
     * 将json字符串转化为对象集合
     *
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> List<T> getObjectsList(String jsonStr, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonStr, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}


