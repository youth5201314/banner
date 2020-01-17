package com.youth.banner;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.youth.banner.holder.ViewHolder;
import com.youth.banner.holder.ViewHolderCreator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class BannerPagerAdapter<T> extends PagerAdapter {
    private List<T> mDatas;
    private ViewHolderCreator creator;
    private ViewPager mViewPager;
    private boolean mIsLoop;
    private OnBannerListener listener;
    private final int mLooperFactor = 500;

    public BannerPagerAdapter(ViewPager mViewPager, List<T> datas, ViewHolderCreator creator, boolean isLoop) {
        this.mDatas = new ArrayList<>();
        this.mDatas.addAll(datas);
        this.mViewPager = mViewPager;
        this.creator = creator;
        this.mIsLoop = isLoop;
    }

    public void setOnBannerListener(OnBannerListener listener) {
        this.listener = listener;
    }

    public int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public int getRealPosition(int position) {
        return position % getRealCount();
    }

    public int getStartPosition() {
        if (getRealCount() == 0) {
            return 0;
        }
        int currentItem = getRealCount() * mLooperFactor / 2;
        if (currentItem % getRealCount() == 0) {
            return currentItem;
        }
        while (currentItem % getRealCount() != 0) {
            currentItem++;
        }
        return currentItem;
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mIsLoop ? getRealCount() * mLooperFactor : getRealCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ViewHolder<T> holder = creator.createViewHolder();
        if (holder == null) {
            throw new NullPointerException("The createViewHolder() is not implemented");
        }
        View itemView = holder.createView(container.getContext());
        int count = getRealCount();
        if (count > 0) {
            int realPosition = getRealPosition(position);
            final T data = mDatas.get(realPosition);
            holder.onBindView(itemView, data, realPosition, count);

            if (listener != null) {
                itemView.setOnClickListener(v -> listener.OnBannerClick(data, realPosition));
            }
        }
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (!mIsLoop) return;
        int position = mViewPager.getCurrentItem();
        if (position == getCount() - 1) {
            mViewPager.setCurrentItem(0, false);
        }
    }
}