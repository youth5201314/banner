package com.youth.banner.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.R;
import com.youth.banner.holder.IViewHolder;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public abstract class BannerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH>
        implements IViewHolder<T, VH> {
    protected List<T> mDatas = new ArrayList<>();
    private OnBannerListener<T> mOnBannerListener;
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
        holder.itemView.setTag(R.id.banner_data_key, mDatas.get(real));
        holder.itemView.setTag(R.id.banner_pos_key, real);
        onBindView(holder, mDatas.get(real), real, getRealCount());
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = onCreateHolder(parent, viewType);
        vh.itemView.setOnClickListener(v -> {
            if (mOnBannerListener != null) {
                mOnBannerListener.OnBannerClick((T) vh.itemView.getTag(R.id.banner_data_key),
                        (Integer) vh.itemView.getTag(R.id.banner_pos_key));
            }
        });
        return vh;
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

    public void setOnBannerListener(OnBannerListener<T> listener) {
        this.mOnBannerListener = listener;
    }

    public VH getViewHolder() {
        return mViewHolder;
    }

    public void setIncreaseCount(int increaseCount) {
        this.increaseCount = increaseCount;
    }
}