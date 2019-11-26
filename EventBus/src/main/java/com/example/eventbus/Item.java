package com.example.eventbus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2019-11-26.
 */
public class Item {

    public String id;
    public String content;
    public static List<Item> ITEMS = new ArrayList<>();

    public Item(String id, String content) {
        this.id = id;
        this.content = content;
    }

    static {
        addItem(new Item("1", "Item1"));
        addItem(new Item("2", "Item2"));
        addItem(new Item("3", "Item3"));
        addItem(new Item("4", "Item4"));
        addItem(new Item("5", "Item5"));
        addItem(new Item("6", "Item6"));
    }


    private static void addItem(Item item) {
        ITEMS.add(item);
    }

    @Override
    public String toString() {
        return content;
    }

}
