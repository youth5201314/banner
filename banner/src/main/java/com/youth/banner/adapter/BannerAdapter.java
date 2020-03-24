package com.youth.banner.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.util.BannerUtils;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public abstract class BannerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements IViewHolder<T, VH> {
    protected List<T> mDatas = new ArrayList<>();
    private OnBannerListener listener;

    public BannerAdapter(List<T> datas) {
        setDatas(datas);
    }

    public void setDatas(List<T> datas) {
        mDatas.clear();
        if (datas == null) {
            datas = new ArrayList<>();
        }
        mDatas.addAll(datas);
        int count = datas.size();
        if (count > 1) {
            mDatas.add(0, datas.get(count - 1));
            mDatas.add(datas.get(0));
        }
    }

    public T getData(int position) {
        return mDatas.get(position);
    }

    @Override
    public final void onBindViewHolder(@NonNull VH holder, int position) {
        int real = BannerUtils.getRealPosition(position, getRealCount());
        onBindView(holder, mDatas.get(position), real, getRealCount());
        if (listener != null)
            holder.itemView.setOnClickListener(view -> listener.OnBannerClick(mDatas.get(position), real));
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateHolder(parent, viewType);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public int getRealCount() {
        int count = getItemCount();
        return count <= 1 ? count : count - 2;
    }

    public void setOnBannerListener(OnBannerListener listener) {
        this.listener = listener;
    }
}