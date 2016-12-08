package com.test.banner;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;


public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {

    private static final int NUM_ITEMS = 30;

    private String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public SampleAdapter() {
        mDataset = new String[NUM_ITEMS];
        for (int i = 0; i < NUM_ITEMS; i++) {
            mDataset[i] = String.valueOf(i);
        }
    }

    @Override
    public SampleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setTextSize(16f);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10,10,10,10);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText("This is item " + mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
