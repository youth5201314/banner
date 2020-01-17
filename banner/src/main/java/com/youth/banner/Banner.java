package com.youth.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.BannerFragmentAdapter;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;

import java.lang.ref.WeakReference;


public class Banner<T> extends FrameLayout {
    private static final String TAG = "banner_log";
    private ViewPager2 mViewPager2;
    private boolean mIsLoop = true;// 是否支持无限轮播
    private boolean mIsAutoPlay = false;// 是否自动播放
    private long mDelayTime = 3000;// Banner 轮播切换间隔时间
    private AutoLoopTask mLoopTask;
    private int mCurrentPosition = 1;//当前位置（默认开始为1）
    private int mPlaceholderImage;//占位图
    private OnBannerListener listener;
    private OnPageChangeListener pageListener;

    public Banner(@NonNull Context context) {
        this(context, null);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        mViewPager2 = new ViewPager2(context);
        mViewPager2.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mViewPager2.setOffscreenPageLimit(3);
        mViewPager2.registerOnPageChangeCallback(new BannerOnPageChangeCallback());
        addView(mViewPager2);
        mLoopTask = new AutoLoopTask(this);
    }

    private int getRealCount() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter instanceof BannerAdapter) {
            return ((BannerAdapter) adapter).getRealCount();
        }
        if (adapter instanceof BannerFragmentAdapter) {
            return ((BannerFragmentAdapter) adapter).getRealCount();
        }
        return 0;
    }

    private int getItemCount() {
        return getAdapter().getItemCount();
    }

    private void setCurrentItem(int position) {
        setCurrentItem(position, true);
    }

    private void setCurrentItem(int position, boolean smoothScroll) {
        mViewPager2.setCurrentItem(position, smoothScroll);
    }

    private int getCurrentItem() {
        return mViewPager2.getCurrentItem();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!mIsLoop) {
            return super.dispatchTouchEvent(ev);
        }
        int action = ev.getActionMasked();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_OUTSIDE) {
            if (mIsAutoPlay) start();
        } else if (action == MotionEvent.ACTION_DOWN) {
            if (mIsAutoPlay) stop();
        }
        return super.dispatchTouchEvent(ev);
    }

    class BannerOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {

        private boolean filterPosition(int position) {
            if ((position == 1 && mCurrentPosition == getItemCount() - 1)
                    || (position == getRealCount() && mCurrentPosition == 0)) {
                return true;
            }
            return false;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (filterPosition(position)) return;
            if (pageListener != null) {
                pageListener.onPageScrolled(Utils.getRealPosition(position, getRealCount()), positionOffset, positionOffsetPixels);
            }
        }


        @Override
        public void onPageSelected(int position) {
//            Log.e(TAG, "onPageSelected:" + position);
            if (filterPosition(position)) {
                mCurrentPosition = position;
                return;
            }
            mCurrentPosition = position;
            if (pageListener != null) {
                pageListener.onPageSelected(Utils.getRealPosition(position, getRealCount()));
            }
        }


        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                //正在拖动中
                case ViewPager2.SCROLL_STATE_DRAGGING:
                    if (mCurrentPosition == 0) {
                        setCurrentItem(getRealCount(), false);
                    } else if (mCurrentPosition == getItemCount() - 1) {
                        setCurrentItem(1, false);
                    }
                    break;
            }
            if (pageListener != null) {
                pageListener.onPageScrollStateChanged(state);
            }
        }

    }

    static class AutoLoopTask implements Runnable {
        private final WeakReference<Banner> reference;

        AutoLoopTask(Banner banner) {
            this.reference = new WeakReference<>(banner);
        }

        @Override
        public void run() {
            Banner banner = reference.get();
            if (banner != null && banner.mIsAutoPlay) {
                int count = banner.getItemCount();
                if (count<=1) return;
                int next = banner.getCurrentItem() % (count - 1) + 1;
                if (next == 1) {
                    banner.setCurrentItem(next, false);
                    banner.post(banner.mLoopTask);
                } else {
                    banner.setCurrentItem(next);
                    banner.postDelayed(banner.mLoopTask, banner.mDelayTime);
                    if (banner.listener != null) {
                        banner.listener.onBannerChanged(Utils.getRealPosition(next, banner.getRealCount()));
                    }
                }
            }
        }
    }


    /**
     * **********************************************************************
     * ------------------------ 对外公开API ---------------------------------*
     * **********************************************************************
     */


    public void start() {
        if (mIsAutoPlay) {
            postDelayed(mLoopTask, mDelayTime);
        }
    }

    public void stop() {
        removeCallbacks(mLoopTask);
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        if (adapter instanceof BannerAdapter || adapter instanceof BannerFragmentAdapter) {
            mViewPager2.setAdapter(adapter);
            setCurrentItem(mCurrentPosition, false);
        }
    }

    @Nullable
    public RecyclerView.Adapter getAdapter() {
        return mViewPager2.getAdapter();
    }


    public void setOrientation(@ViewPager2.Orientation int orientation) {
        mViewPager2.setOrientation(orientation);
    }


    @NonNull
    public ViewPager2 getViewPager2() {
        return mViewPager2;
    }

    /**
     * 设置点击事件
     *
     * @param listener
     * @return
     */
    public Banner setOnBannerListener(OnBannerListener listener) {
        this.listener = listener;
        //为保证刚开始还没有轮播时第一个页面也调用
        listener.onBannerChanged(Utils.getRealPosition(mCurrentPosition, getRealCount()));
        return this;
    }

    /**
     * 添加viewpager切换事件
     * <p>
     * viewpager2中OnPageChangeCallback是一个抽象类，
     * 为了方便使用习惯这里用的是和viewpager一样的OnPageChangeListener接口
     *
     * @param pageListener
     */
    public Banner addOnPageChangeListener(OnPageChangeListener pageListener) {
        this.pageListener = pageListener;
        return this;
    }
}
