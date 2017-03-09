package com.test.banner;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class SampleAdapter extends BaseAdapter {

    private String[] mDataSet;
    private Context context;

    public SampleAdapter(Context context,String[] dataSet) {
        this.mDataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDataSet.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.text_item, null);
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(mDataSet[position]);
        if (position % 2 == 0) {
            textView.setBackgroundColor(Color.parseColor("#f5f5f5"));
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }

}
