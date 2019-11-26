package com.example.eventbus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;

import de.greenrobot.event.EventBus;

/**
 * Created by mac on 2019-11-26.
 */
public class ItemDetailFragment extends Fragment {

    private TextView tvDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);//订阅事件
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        tvDetail = (TextView) rootView.findViewById(R.id.item_detail);
        view_item1 = rootView.findViewById(R.id.bolck_item1);
        view_item2 = rootView.findViewById(R.id.bolck_item2);
        return rootView;
    }

    View view_item1;
    View view_item2;

    /**
     * List点击时会发送些事件，接收到事件后更新详情
     */
    public void onEventMainThread(Item item) {
        AppLogger.d("onEventMainThread: ItemDetailFragment");
        if (item != null)
            AppLogger.d("onEventMainThread: " + item.content);
        tvDetail.setText(item.content);
        if (TextUtils.equals("1", item.id)) {
            view_item1.setVisibility(View.VISIBLE);
            view_item2.setVisibility(View.GONE);
        } else {
            view_item1.setVisibility(View.GONE);
            view_item2.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消订阅
    }

}
