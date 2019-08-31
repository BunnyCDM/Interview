package com.example.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://www.jianshu.com/p/c22c008e0820
public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter adapter;

    // 图片封装为一个数组
    private int[] icons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String[] text = {"111", "222", "333"
            , "444", "555", "666"
            , "777", "888", "999"
            , "000", "123", "234"
            , "345", "456", "555"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();

        //加载适配器
        String[] form = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        adapter = new SimpleAdapter(this, data_list, R.layout.item, form, to);
        gridView.setAdapter(adapter);

        //监听item每一项
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "你点击了第" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icons[i]);
            map.put("text", text[i]);
            data_list.add(map);
        }
        return data_list;
    }
}
