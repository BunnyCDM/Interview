package com.example.cityselector02;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cityselector.R;

public class ItemBeanAdapter extends BaseAdapter {

    private Context context;
    private List<City> data;

    public ItemBeanAdapter(Context context, List<City> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int arg0) {
        return data.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Log.i("Tiger", "getView position = " + position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.select_city_item, null);

            holder = new ViewHolder();
            holder.group_title = (TextView) convertView.findViewById(R.id.group_title);
            holder.city_title = (TextView) convertView.findViewById(R.id.city_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        City city = data.get(position);

        final String cityname = city.getCity();
        final String avater = city.getFirstPY();

        holder.city_title.setText(cityname);

        if (getPostionForSection(getSectionForPostion(position)) == position) {
            Log.i("Tiger", "getView ok position = " + position);
            holder.group_title.setVisibility(View.VISIBLE);
            holder.group_title.setText(avater);
        } else {
            holder.group_title.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void updateListView(List<City> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    /*根据当前位置获取这个位置的首字母的ASCII*/
    protected int getSectionForPostion(int position) {
        return data.get(position).getFirstPY().charAt(0);
    }

    /*根据当前的传入的字母的ASCII来找到第一个出现该字母的位置*/
    protected int getPostionForSection(int section) {
        //对列表进行循环扫描，判断每一个位置的ASCII，第一个出现的就返回
        for (int i = 0; i < getCount(); i++) {
            String sortStr = data.get(i).getFirstPY();
            char firstChar = sortStr.toUpperCase().charAt(0);

            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    static class ViewHolder {
        TextView group_title;
        TextView city_title;
    }

}
