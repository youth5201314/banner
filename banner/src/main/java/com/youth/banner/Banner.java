package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.Indicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.util.BannerUtils;

import java.lang.annotation.Retention;
import java.lang.ref.WeakReference;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.SOURCE;


public class Banner<T, BA extends BannerAdapter> extends FrameLayout {
    public static final String TAG = "banner_log";
    private ViewPager2 mViewPager2;
    private AutoLoopTask mLoopTask;
    private OnBannerListener listener;
    private OnPageChangeListener pageListener;
    private BA mAdapter;
    private Indicator mIndicator;

    // 是否自动播放
    private boolean mIsAutoLoop;
    // 轮播切换间隔时间
    private long mDelayTime;
    // 当前位置（默认开始为1）
    private int mCurrentPosition = 1;

    //指示器配置
    private int normalWidth;
    private int selectedWidth;
    private Drawable normalColor;
    private Drawable selectedColor;
    private int indicatorGravity;
    private int indicatorSpace;
    private int indicatorMargin;
    private int indicatorMarginLeft;
    private int indicatorMarginTop;
    private int indicatorMarginRight;
    private int indicatorMarginBottom;

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    @Retention(SOURCE)
    @IntDef({HORIZONTAL, VERTICAL})
    public @interface Orientation {
    }

    public Banner(@NonNull Context context) {
        this(context, null);
    }

    public Banner(@NonNull Context context, @NonNull AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initTypedArray(context, attrs);
    }

    private void init(@NonNull Context context) {
        mLoopTask = new AutoLoopTask(this);
        mViewPager2 = new ViewPager2(context);
        mViewPager2.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mViewPager2.setOffscreenPageLimit(3);
        mViewPager2.registerOnPageChangeCallback(new BannerOnPageChangeCallback());
        addView(mViewPager2);
    }

    private void initTypedArray(@NonNull Context context, @NonNull AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mDelayTime = a.getInt(R.styleable.Banner_delay_time, BannerConfig.DELAY_TIME);
        mIsAutoLoop = a.getBoolean(R.styleable.Banner_is_auto_loop, BannerConfig.IS_AUTO_LOOP);
        normalWidth = a.getDimensionPixelSize(R.styleable.Banner_indicator_normal_width, (int) BannerConfig.INDICATOR_NORMAL_WIDTH);
        selectedWidth = a.getDimensionPixelSize(R.styleable.Banner_indicator_selected_width, (int) BannerConfig.INDICATOR_SELECTED_WIDTH);
        normalColor = a.getDrawable(R.styleable.Banner_indicator_normal_color);
        selectedColor = a.getDrawable(R.styleable.Banner_indicator_selected_color);
        indicatorGravity = a.getInt(R.styleable.Banner_indicator_gravity, IndicatorConfig.Direction.CENTER);
        indicatorSpace = a.getDimensionPixelSize(R.styleable.Banner_indicator_space, 0);
        indicatorMargin = a.getDimensionPixelSize(R.styleable.Banner_indicator_margin, 0);
        indicatorMarginLeft = a.getDimensionPixelSize(R.styleable.Banner_indicator_marginLeft, 0);
        indicatorMarginTop = a.getDimensionPixelSize(R.styleable.Banner_indicator_marginTop, 0);
        indicatorMarginRight = a.getDimensionPixelSize(R.styleable.Banner_indicator_marginRight, 0);
        indicatorMarginBottom = a.getDimensionPixelSize(R.styleable.Banner_indicator_marginBottom, 0);
        int orientation = a.getInt(R.styleable.Banner_banner_orientation, HORIZONTAL);
        setOrientation(orientation);
        a.recycle();
    }

    private void initIndicatorAttr() {
        if (indicatorMargin != 0) {
            setIndicatorMargins(new IndicatorConfig.Margins(indicatorMargin));
        } else if (indicatorMarginLeft != 0
                || indicatorMarginTop != 0
                || indicatorMarginRight != 0
                || indicatorMarginBottom != 0) {
            setIndicatorMargins(new IndicatorConfig.Margins(
                    indicatorMarginLeft,
                    indicatorMarginTop,
                    indicatorMarginRight,
                    indicatorMarginBottom));
        }
        if (indicatorSpace > 0) {
            setIndicatorSpace(indicatorSpace);
        }
        if (indicatorGravity != IndicatorConfig.Direction.CENTER) {
            setIndicatorGravity(indicatorGravity);
        }

        int nColor = BannerUtils.getColor(getContext(), normalColor);
        if (nColor != -1) {
            setIndicatorNormalColor(nColor);
        }

        int sColor = BannerUtils.getColor(getContext(), selectedColor);
        if (sColor != -1) {
            setIndicatorSelectedColor(sColor);
        }

        if (normalWidth > 0) {
            setIndicatorNormalWidth(normalWidth);
        }
        if (selectedWidth > 0) {
            setIndicatorSelectedWidth(selectedWidth);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == MotionEvent.ACTION_UP
                || action == MotionEvent.ACTION_CANCEL
                || action == MotionEvent.ACTION_OUTSIDE) {
            if (mIsAutoLoop) start();
        } else if (action == MotionEvent.ACTION_DOWN) {
            if (mIsAutoLoop) stop();
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
            int realPosition = BannerUtils.getRealPosition(position, getRealCount());
            if (pageListener != null) {
                pageListener.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
            }
            if (mIndicator != null) {
                mIndicator.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
            }
        }


        @Override
        public void onPageSelected(int position) {
            //Log.e(TAG, "onPageSelected:" + position);
            if (filterPosition(position)) {
                mCurrentPosition = position;
                return;
            }
            mCurrentPosition = position;
            int realPosition = BannerUtils.getRealPosition(position, getRealCount());
            if (pageListener != null) {
                pageListener.onPageSelected(realPosition);
            }
            if (mIndicator != null) {
                mIndicator.onPageSelected(realPosition);
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
            if (mIndicator != null) {
                mIndicator.onPageScrollStateChanged(state);
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
            if (banner != null && banner.mIsAutoLoop) {
                int count = banner.getItemCount();
                if (count <= 1) return;
                int next = banner.getCurrentItem() % (count - 1) + 1;
                if (next == 1) {
                    banner.setCurrentItem(next, false);
                    banner.post(banner.mLoopTask);
                } else {
                    banner.setCurrentItem(next);
                    banner.postDelayed(banner.mLoopTask, banner.mDelayTime);
                    if (banner.listener != null) {
                        banner.listener.onBannerChanged(BannerUtils.getRealPosition(next, banner.getRealCount()));
                    }
                }
            }
        }
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

    private int getItemCount() {
        if (getAdapter() == null)
            return 0;
        return getAdapter().getItemCount();
    }


    private void initIndicator() {
        if (mIndicator == null) return;
        removeIndicator();
        addView(mIndicator.getIndicatorView());
        initIndicatorAttr();
        int realPosition = BannerUtils.getRealPosition(getCurrentItem(), getRealCount());
        mIndicator.onPageChanged(getRealCount(), realPosition);
    }

    public void removeIndicator() {
        if (mIndicator != null) {
            removeView(mIndicator.getIndicatorView());
        }
    }


    /**
     * **********************************************************************
     * ------------------------ 对外公开API ---------------------------------*
     * **********************************************************************
     */

    public Banner setBannerHeight(int height) {
        setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) BannerUtils.dp2px(height)));
        return this;
    }

    @NonNull
    public BA getAdapter() {
        if (mAdapter == null) {
            Log.e(TAG, getContext().getString(R.string.banner_adapter_use_error));
        }
        return mAdapter;
    }

    @NonNull
    public ViewPager2 getViewPager2() {
        return mViewPager2;
    }

    public Indicator getIndicator() {
        if (mIndicator == null) {
            Log.e(TAG, getContext().getString(R.string.indicator_null_error));
        }
        return mIndicator;
    }

    public IndicatorConfig getIndicatorConfig() {
        if (getIndicator() != null) {
            return getIndicator().getIndicatorConfig();
        }
        return null;
    }

    /**
     * 返回banner真实总数
     *
     * @return
     */
    public int getRealCount() {
        return getAdapter().getRealCount();
    }

    /**
     * 禁止手动滑动
     *
     * @param enabled true 允许，false 禁止
     * @return
     */
    public Banner setUserInputEnabled(boolean enabled) {
        getViewPager2().setUserInputEnabled(enabled);
        return this;
    }

    /**
     * {@link ViewPager2.PageTransformer}
     * 如果找不到请导入implementation "androidx.viewpager2:viewpager2:1.0.0"
     *
     * @param transformer
     */
    public Banner setPageTransformer(@Nullable ViewPager2.PageTransformer transformer) {
        getViewPager2().setPageTransformer(transformer);
        return this;
    }

    public Banner addItemDecoration(@NonNull RecyclerView.ItemDecoration decor) {
        getViewPager2().addItemDecoration(decor);
        return this;
    }

    public Banner addItemDecoration(@NonNull RecyclerView.ItemDecoration decor, int index) {
        getViewPager2().addItemDecoration(decor, index);
        return this;
    }

    /**
     * 重新设置banner数据，当然你也可以在你adapter中自己操作数据
     *
     * @param datas 数据集合，当传null或者datas没有数据时，banner会变成空白的，请做好占位UI处理
     * @return
     */
    public Banner setDatas(@NonNull List<T> datas) {
        if (getAdapter() != null) {
            getAdapter().setDatas(datas);
            int realPosition = BannerUtils.getRealPosition(getCurrentItem(), getRealCount());
            mIndicator.onPageChanged(getRealCount(), realPosition);
        }
        return this;
    }

    /**
     * 是否允许自动轮播
     *
     * @param isAutoLoop ture 允许，false 不允许
     * @return
     */
    public Banner isAutoLoop(boolean isAutoLoop) {
        this.mIsAutoLoop = isAutoLoop;
        return this;
    }

    /**
     * 设置轮播间隔时间
     *
     * @param delayTime 时间（毫秒）
     * @return
     */
    public Banner setDelayTime(long delayTime) {
        this.mDelayTime = delayTime;
        return this;
    }

    /**
     * 开始轮播
     *
     * @return
     */
    public Banner start() {
        if (mIsAutoLoop) {
            stop();
            postDelayed(mLoopTask, mDelayTime);
        }
        return this;
    }

    /**
     * 停止轮播
     *
     * @return
     */
    public Banner stop() {
        removeCallbacks(mLoopTask);
        return this;
    }

    /**
     * 设置banner的适配器
     *
     * @param adapter
     * @return
     */
    public Banner setAdapter(@NonNull BA adapter) {
        if (adapter == null) {
            throw new NullPointerException(getContext().getString(R.string.banner_adapter_null_error));
        }
        this.mAdapter = adapter;
        mViewPager2.setAdapter(adapter);
        setCurrentItem(mCurrentPosition, false);
        initIndicator();
        return this;
    }

    /**
     * 设置banner轮播方向
     *
     * @param orientation {@link Orientation}
     * @return
     */
    public Banner setOrientation(@Orientation int orientation) {
        mViewPager2.setOrientation(orientation);
        return this;
    }


    /**
     * 设置轮播指示器
     *
     * @param indicator
     */
    public Banner setIndicator(@NonNull Indicator indicator) {
        if (mIndicator == indicator) return this;
        removeIndicator();
        this.mIndicator = indicator;
        if (getAdapter() != null) {
            initIndicator();
        }
        return this;
    }

    public Banner setIndicatorSelectedColor(@ColorInt int color) {
        if (mIndicator != null) {
            mIndicator.getIndicatorConfig().setSelectedColor(color);
        }
        return this;
    }

    public Banner setIndicatorSelectedColorRes(@ColorRes int color) {
        setIndicatorSelectedColor(ContextCompat.getColor(getContext(), color));
        return this;
    }

    public Banner setIndicatorNormalColor(@ColorInt int color) {
        if (mIndicator != null) {
            mIndicator.getIndicatorConfig().setNormalColor(color);
        }
        return this;
    }

    public Banner setIndicatorNormalColorRes(@ColorRes int color) {
        setIndicatorNormalColor(ContextCompat.getColor(getContext(), color));
        return this;
    }

    public Banner setIndicatorGravity(@IndicatorConfig.Direction int gravity) {
        if (mIndicator != null) {
            mIndicator.getIndicatorConfig().setGravity(gravity);
            mIndicator.getIndicatorView().postInvalidate();
        }
        return this;
    }

    public Banner setIndicatorSpace(float indicatorSpace) {
        if (mIndicator != null) {
            mIndicator.getIndicatorConfig().setIndicatorSpace(indicatorSpace);
        }
        return this;
    }

    public Banner setIndicatorMargins(IndicatorConfig.Margins margins) {
        if (mIndicator != null) {
            mIndicator.getIndicatorConfig().setMargins(margins);
            mIndicator.getIndicatorView().requestLayout();
        }
        return this;
    }

    public Banner setIndicatorWidth(int normalWidth, int selectedWidth) {
        if (mIndicator != null) {
            mIndicator.getIndicatorConfig().setNormalWidth(normalWidth);
            mIndicator.getIndicatorConfig().setSelectedWidth(selectedWidth);
        }
        return this;
    }

    public Banner setIndicatorNormalWidth(int normalWidth) {
        if (mIndicator != null) {
            mIndicator.getIndicatorConfig().setNormalWidth(normalWidth);
        }
        return this;
    }

    public Banner setIndicatorSelectedWidth(int selectedWidth) {
        if (mIndicator != null) {
            mIndicator.getIndicatorConfig().setSelectedWidth(selectedWidth);
        }
        return this;
    }

    /**
     * 设置点击事件
     *
     * @param listener
     * @return
     */
    public Banner setOnBannerListener(@NonNull OnBannerListener listener) {
        if (getAdapter() != null) {
            getAdapter().setOnBannerListener(listener);
        }
        //为保证刚开始还没有轮播时第一个页面也调用
        listener.onBannerChanged(BannerUtils.getRealPosition(mCurrentPosition, getRealCount()));
        this.listener = listener;
        return this;
    }

    /**
     * 添加viewpager切换事件
     * <p>
     * 在viewpager2中切换事件{@link ViewPager2.OnPageChangeCallback}是一个抽象类，
     * 为了方便使用习惯这里用的是和viewpager一样的{@link ViewPager.OnPageChangeListener}接口
     * </p>
     *
     * @param pageListener
     */
    public Banner addOnPageChangeListener(@NonNull OnPageChangeListener pageListener) {
        this.pageListener = pageListener;
        return this;
    }
}
