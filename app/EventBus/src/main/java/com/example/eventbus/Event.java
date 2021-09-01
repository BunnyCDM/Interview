package com.example.eventbus;

import java.util.List;

/**
 * Created by mac on 2019-11-26.
 */
public class Event {


    /**
     * 列表加载事件
     */
    public static class ItemListEvent {
        private List<Item> items;

        public ItemListEvent(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }

    }

}
