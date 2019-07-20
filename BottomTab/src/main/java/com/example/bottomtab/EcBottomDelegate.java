package com.example.bottomtab;

import android.graphics.Color;

import com.example.bottomtab.bottom.BaseBottomDelegate;
import com.example.bottomtab.bottom.BottomItemDelegate;
import com.example.bottomtab.bottom.BottomTabBean;
import com.example.bottomtab.bottom.ItemBuilder;
import com.example.bottomtab.main.DiscoverDelegate;
import com.example.bottomtab.main.IndexDelegate;
import com.example.bottomtab.main.PersonalDelegate;
import com.example.bottomtab.main.ShopCartDelegate;
import com.example.bottomtab.main.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by mac on 2019/2/25.
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

//    @Override
//    public Object setLayout() {
//        return R.layout.delegate_bottom;
//    }
//
//    @Override
//    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//
//    }

}
