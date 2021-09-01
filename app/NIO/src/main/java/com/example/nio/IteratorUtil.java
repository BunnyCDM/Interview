package com.example.nio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by mac on 2019-08-17.
 */
public class IteratorUtil {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("张三1");
        list.add("张三2");
        list.add("张三3");
        list.add("张三4");

        List<String> linkList = new LinkedList<>();
        linkList.add("link1");
        linkList.add("link2");
        linkList.add("link3");
        linkList.add("link4");

        Set<String> set = new HashSet<>();
        set.add("set1");
        set.add("set2");
        set.add("set3");
        set.add("set4");

        //使用迭代器遍历ArrayList集合
        Iterator<String> listIt = list.iterator();
        while (listIt.hasNext()) {
            System.out.println(listIt.next());
            listIt.remove();
        }
        //使用迭代器遍历Set集合
        Iterator<String> setIt = set.iterator();
        while (setIt.hasNext()) {
            System.out.println(setIt.next());
        }
        //使用迭代器遍历LinkedList集合
        Iterator<String> linkIt = linkList.iterator();
        while (linkIt.hasNext()) {
            System.out.println(linkIt.next());
        }
    }
}

