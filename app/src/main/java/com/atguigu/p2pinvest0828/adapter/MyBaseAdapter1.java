package com.atguigu.p2pinvest0828.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by shkstart on 2016/12/5 0005.
 */
public abstract class MyBaseAdapter1<T> extends BaseAdapter {

    public List<T> list;
    //通过构造器初始化集合数据
    public MyBaseAdapter1(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = myGetView(position,convertView,parent);
        return view;
    }

    public abstract View myGetView(int position, View convertView, ViewGroup parent);
}
