package com.youth.banner.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.IViewHolder;
import com.youth.banner.Utils;

import java.util.ArrayList;
import java.util.List;


public abstract class BannerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements IViewHolder<T, VH> {
    private List<T> mDatas ;
    private List<T> mOriginalDatas ;
    private boolean mIsLoop;
    private int mCount;
    public BannerAdapter(List<T> datas) {
        this.mOriginalDatas=datas;
        mCount=getRealCount();
        mDatas = new ArrayList<>();
        mDatas.addAll(datas);
        if (mCount>1) {
            mDatas.add(0, datas.get(mCount - 1));
            mDatas.add(datas.get(0));
        }
    }

    @Override
    public final void onBindViewHolder(@NonNull VH holder, int position) {
        int real = Utils.getRealPosition(position, getRealCount());
        onBindView(holder, mDatas.get(position), real, getRealCount());
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateHolder(parent, viewType);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public int getRealCount() {
        return mOriginalDatas == null ? 0 : mOriginalDatas.size();
    }


}