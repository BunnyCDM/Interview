package com.example.eventbus;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baselibrary.utils.log.AppLogger;

//import de.greenrobot.event.EventBus;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by mac on 2019-11-26.
 */
public class ItemListFragment extends ListFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);//订阅事件
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //开启线程加载列表
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //Thread.sleep(1000);//模拟延迟
                    EventBus.getDefault().post(new Event.ItemListEvent(Item.ITEMS));//发布事件，在后台线程发的事件
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        AppLogger.d("onListItemClick: " + position);
        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }


    /**
     * 代表这个方法会在UI线程执行
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Event.ItemListEvent event) {
        AppLogger.d("onEventMainThread: ItemListFragment");
        setListAdapter(new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, event.getItems()));
        EventBus.getDefault().post(getListView().getItemAtPosition(0));
    }

    public void onEvent() {
    }

    public void onEventBackgroundThread() {
    }

    public void onEventAsync() {
    }

    /**
     * 代表这个方法会在当前发布事件的线程执行
     */
    public void onEventPostThread() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消订阅
    }

}
