package com.example.fragment.demo2;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2020-04-12.
 */
public class MyListFragment extends ListFragment {

    private List<String> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("item:" + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        setListAdapter(adapter);
        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), "被点击了" + list.get(position), Toast.LENGTH_SHORT).show();
    }
}
