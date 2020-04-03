package com.youth.banner.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public abstract class BannerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements IViewHolder<T, VH> {
    protected List<T> mDatas = new ArrayList<>();
    private OnBannerListener mOnBannerListener;
    private VH mViewHolder;
    private int increaseCount = 2;

    public BannerAdapter(List<T> datas) {
        setDatas(datas);
    }

    public void setDatas(List<T> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        mDatas = datas;
    }

    public T getData(int position) {
        return mDatas.get(position);
    }

    @Override
    public final void onBindViewHolder(@NonNull VH holder, int position) {
        mViewHolder = holder;
        int real = getRealPosition(position);
        onBindView(holder, mDatas.get(real), real, getRealCount());
        if (mOnBannerListener != null)
            holder.itemView.setOnClickListener(view -> mOnBannerListener.OnBannerClick(mDatas.get(real), real));
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateHolder(parent, viewType);
    }

    @Override
    public int getItemCount() {
        return getRealCount() > 1 ? getRealCount() + increaseCount : getRealCount();
    }

    public int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public int getRealPosition(int position) {
        return BannerUtils.getRealPosition(increaseCount == 2, position, getRealCount());
    }

    public void setOnBannerListener(OnBannerListener listener) {
        this.mOnBannerListener = listener;
    }

    public VH getViewHolder() {
        return mViewHolder;
    }

    public void setIncreaseCount(int increaseCount) {
        this.increaseCount = increaseCount;
    }
}