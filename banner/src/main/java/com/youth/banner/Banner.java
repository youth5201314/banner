package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.youth.banner.adapter.BannerPagerAdapter;
import com.youth.banner.holder.ViewHolderCreator;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Banner<T> extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "banner";
    private BannerViewPager mViewPager;
    private BannerPagerAdapter mAdapter;
    private List<T> mDatas;
    private boolean mIsAutoPlay ;// 是否自动播放
    private int mCurrentPosition = 0;//当前位置
    private int mDelayTime;// Banner 轮播切换间隔时间
    private int mDurationTime;// Banner 轮播滑动执行时间
    private BannerScroller mBannerScroller;//控制ViewPager滑动速度的Scroller
    private boolean mIsLoop = true;// 是否支持无限轮播
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private boolean mViewPagerIsScroll = true;//是否允许手动滑动viewpager
    private int mCount = 0;
    private int mStartPosition = 0;
    private int mPlaceholderImage;//占位图
    private ViewHolderCreator mCreator;


    public Banner(@NonNull Context context) {
        this(context, null);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleTypedArray(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.banner_view_pager, this, true);
        mViewPager = view.findViewById(R.id.bannerViewPager);
        mViewPager.setOffscreenPageLimit(3);
        initViewPagerScroll();
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mDelayTime = typedArray.getInt(R.styleable.Banner_delay_time, BannerConfig.TIME);
        mDurationTime = typedArray.getInt(R.styleable.Banner_duration_time, BannerConfig.DURATION);
        mIsAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, BannerConfig.IS_AUTO_PLAY);
        mPlaceholderImage = typedArray.getResourceId(R.styleable.Banner_placeholder_mage, R.drawable.no_banner);
        typedArray.recycle();
    }


    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mBannerScroller = new BannerScroller(getContext());
            mScroller.set(mViewPager, mBannerScroller);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private final Runnable mLoopRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsAutoPlay) {
                mCurrentPosition = mViewPager.getCurrentItem();
                mCurrentPosition++;
                if (mCurrentPosition == mAdapter.getCount() - 1) {
                    mCurrentPosition = 0;
                    mViewPager.setCurrentItem(mCurrentPosition, false);
                    postDelayed(this, mDelayTime);
                } else {
                    mViewPager.setCurrentItem(mCurrentPosition);
                    postDelayed(this, mDelayTime);
                }
            } else {
                postDelayed(this, mDelayTime);
            }
        }
    };


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!mIsLoop) {
            return super.dispatchTouchEvent(ev);
        }
        switch (ev.getAction()) {
            // 按住Banner的时候，停止自动轮播
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_DOWN:
                stop();
                break;
            case MotionEvent.ACTION_UP:
                start();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 重新设置点击事件，保证position是真实位置
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrolled(mAdapter.getRealPosition(position), positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        // 重新设置点击事件，保证position是真实位置
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(mAdapter.getRealPosition(position));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                mIsAutoPlay = false;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                mIsAutoPlay = true;
                break;
        }
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    /**
     * **********************************************************************
     *                          对外公开API                                   *
     * **********************************************************************
     */

    /**
     * 设置数据
     *
     * @param datas
     * @return
     */
    public Banner setData(List<T> datas) {
        if (datas == null) {
            throw new NullPointerException("Data cannot be null");
        }
        mDatas = new ArrayList<>();
        mDatas.addAll(datas);
        mCount = mDatas.size();
        return this;
    }

    /**
     * 设置ViewHolder创建器
     *
     * @param creator
     * @return
     */
    public Banner setViewHolderCreator(ViewHolderCreator creator) {
        if (creator == null) {
            throw new NullPointerException("ViewHolderCreator cannot be null");
        }
        this.mCreator = creator;
        return this;
    }

    /**
     * 完成banner构建（最后调用）
     *
     * @return
     */
    public Banner build() {
        mBannerScroller.setDuration(mDurationTime);

        mAdapter = new BannerPagerAdapter(mViewPager, mDatas, mCreator, mIsLoop);
        mViewPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        int currentItem = mIsLoop ? mAdapter.getStartPosition() : 0;
        mViewPager.setCurrentItem(currentItem);

        mViewPager.clearOnPageChangeListeners();
        mViewPager.addOnPageChangeListener(this);

        if (mViewPagerIsScroll && mCount > 1) {
            mViewPager.setScrollable(true);
        } else {
            mViewPager.setScrollable(false);
        }
        if (mIsAutoPlay) {
            start();
        }
        return this;
    }

    /**
     * 开始轮播
     */
    public void start() {
        if (mAdapter == null) {
            return;
        }
        if (mIsLoop) {
            stop();
            postDelayed(mLoopRunnable, mDelayTime);
        }
    }

    /**
     * 停止轮播
     */
    public void stop() {
        removeCallbacks(mLoopRunnable);
    }

    /**
     * 设置是否可以轮播
     *
     * @param isLoop
     */
    public Banner isLoop(boolean isLoop) {
        this.mIsLoop = isLoop;
        return this;
    }

    /**
     * 设置BannerView 的切换时间间隔
     *
     * @param delayTime
     */
    public Banner setDelayTime(int delayTime) {
        this.mDelayTime = delayTime;
        return this;
    }

    /**
     * 是否自动轮播
     *
     * @param isAutoPlay
     * @return
     */
    public Banner isAutoPlay(boolean isAutoPlay) {
        this.mIsAutoPlay = isAutoPlay;
        return this;
    }

    /**
     * 设置ViewPager是否手动滑动
     *
     * @param isScroll
     * @return
     */
    public Banner setViewPagerIsScroll(boolean isScroll) {
        this.mViewPagerIsScroll = isScroll;
        return this;
    }


    /**
     * 获取BannerViewPager对象,以便调用viewpager的api
     *
     * @return {@link BannerViewPager}
     */
    public BannerViewPager getViewPager() {
        return mViewPager;
    }


    /**
     * 设置ViewPager切换的速度
     *
     * @param duration 切换动画时间
     */
    public void setDuration(int duration) {
        this.mDurationTime=duration;
    }

    /**
     * 设置开始轮播的位置
     *
     * @param position
     * @return
     */
    public Banner setStartPosition(int position) {
        this.mStartPosition = position;
        return this;
    }


    /**
     * 设置点击事件
     *
     * @param listener
     * @return
     */
    public Banner setOnBannerListener(OnBannerListener listener) {
        if (mAdapter != null) {
            mAdapter.setOnBannerListener(listener);
        }
        return this;
    }


    public void addPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }
}
