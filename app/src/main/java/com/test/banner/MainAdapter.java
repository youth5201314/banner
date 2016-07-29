package com.test.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter{
    private Context context;
    private String[] des;

    public MainAdapter(Context context, String[] des) {
        this.context = context;
        this.des=des;
    }

    @Override
    public int getCount() {
        return des.length;
    }

    @Override
    public Object getItem(int position) {
        return des[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView=new TextView(context);
        textView.setText(des[position]);
        textView.setPadding(20,20,20,20);
        return textView;
    }
}
