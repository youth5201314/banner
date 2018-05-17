package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.Constants;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.IImageLoader;
import com.youth.banner.view.BannerViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.view.ViewPager.OnPageChangeListener;

public class Banner extends RelativeLayout implements OnPageChangeListener {
    public String tag = "banner";
    private Context mContext;
    /*==========indicator=========*/
    private int mIndicatorStyle = Constants.CIRCLE_INDICATOR;
    private int mIndicatorMargin = Constants.INDICATOR_MARGIN;
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private int mIndicatorGravity = -1;
    private Drawable mIndicatorSelectedDrawable;
    private Drawable mIndicatorUnselectedDrawable;
    /*=========================================*/

    private int mBannerBackgroundImage = R.drawable.no_banner;
    private int mDelayTime = Constants.DELAY_TIME;
    private int mScrollTime = Constants.SCROLL_TIME;
    private boolean isAutoPlay = Constants.IS_AUTO_PLAY;
    private boolean isScroll = Constants.IS_SCROLL;

    private int mLayoutResId = R.layout.banner;
    private int titleHeight;
    private int titleBackground;
    private int titleTextColor;
    private int titleTextSize;
    private int count = 0;
    private IImageLoader imageLoader;

    private int currentItem;
    private int lastPosition = 1;
    private int scaleType = 1;
    private List<String> titles = new ArrayList<>();
    private List imageUrls = new ArrayList();
    private List<View> imageViews = new ArrayList<>();
    private List<ImageView> indicatorImages = new ArrayList<>();

    private BannerViewPager viewPager;
    private TextView bannerTitle, numIndicatorInside, numIndicator;
    private LinearLayout indicator, indicatorInside, titleView;
    private ImageView bannerDefaultImage;
    private BannerPagerAdapter adapter;
    private OnPageChangeListener mOnPageChangeListener;
    private BannerScroller mScroller;
    private OnBannerListener listener;

    private WeakHandler handler = new WeakHandler();

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        handleTypedArray(context, attrs);
        initView(context);
        initViewPagerScroll();
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            //必须对mIndicatorWidth和mIndicatorHeight进行初始化，否则通过new BannerCopy(Context context)创建出来的
            //mIndicatorWidth和mIndicatorHeight将为0，无法显示指示器
            initDefaultValue();
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_width, IndicatorConfig.getDefaultIndicatorSize());
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_height, mIndicatorWidth);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_margin, Constants.INDICATOR_MARGIN);
        int indicatorSelectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.gray_radius);
        int indicatorUnselectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.white_radius);

        mIndicatorSelectedDrawable = ContextCompat.getDrawable(context, indicatorSelectedResId);
        mIndicatorUnselectedDrawable = ContextCompat.getDrawable(context, indicatorUnselectedResId);

        scaleType = typedArray.getInt(R.styleable.Banner_image_scale_type, scaleType);
        mDelayTime = typedArray.getInt(R.styleable.Banner_delay_time, Constants.DELAY_TIME);
        mScrollTime = typedArray.getInt(R.styleable.Banner_scroll_time, Constants.SCROLL_TIME);
        isAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, Constants.IS_AUTO_PLAY);
        titleBackground = typedArray.getColor(R.styleable.Banner_title_background, Constants.TITLE_BACKGROUND);
        titleHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_title_height, Constants.TITLE_HEIGHT);
        titleTextColor = typedArray.getColor(R.styleable.Banner_title_textcolor, Constants.TITLE_TEXT_COLOR);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.Banner_title_textsize, Constants.TITLE_TEXT_SIZE);
        mLayoutResId = typedArray.getResourceId(R.styleable.Banner_banner_layout, mLayoutResId);
        mBannerBackgroundImage = typedArray.getResourceId(R.styleable.Banner_banner_default_image, R.drawable.no_banner);
        typedArray.recycle();
    }

    private void initView(Context context) {
        imageViews.clear();
        View view = LayoutInflater.from(context).inflate(mLayoutResId, this);
        bannerDefaultImage = (ImageView) view.findViewById(R.id.bannerDefaultImage);
        viewPager = (BannerViewPager) view.findViewById(R.id.bannerViewPager);
        titleView = (LinearLayout) view.findViewById(R.id.titleView);
        indicator = (LinearLayout) view.findViewById(R.id.circleIndicator);
        indicatorInside = (LinearLayout) view.findViewById(R.id.indicatorInside);
        bannerTitle = (TextView) view.findViewById(R.id.bannerTitle);
        numIndicator = (TextView) view.findViewById(R.id.numIndicator);
        numIndicatorInside = (TextView) view.findViewById(R.id.numIndicatorInside);
        bannerDefaultImage.setImageResource(mBannerBackgroundImage);
    }

    private void initDefaultValue() {
        mIndicatorWidth = IndicatorConfig.getDefaultIndicatorSize();
        mIndicatorHeight = mIndicatorWidth;

        mIndicatorSelectedDrawable = IndicatorConfig.createIndicatorDrawable(Color.parseColor("#77000000"));
        mIndicatorUnselectedDrawable = IndicatorConfig.createIndicatorDrawable(Color.parseColor("#88ffffff"));
    }

    private void initViewPagerScroll() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new BannerScroller(viewPager.getContext());
            mScroller.setDuration(mScrollTime);
            mField.set(viewPager, mScroller);
        } catch (Exception e) {
            Log.e(tag, e.getMessage());
        }
    }

    /**
     * 设置indicator的参数
     *
     * @param config
     */
    public void setIndicatorConfig(IndicatorConfig config) {
        this.mIndicatorWidth = config.mWidth;
        this.mIndicatorHeight = config.mHeight;

        if (config.mSelectedDrawableId != 0) {
            this.mIndicatorSelectedDrawable = ContextCompat.getDrawable(mContext, config.mSelectedDrawableId);
        } else {
            this.mIndicatorSelectedDrawable = IndicatorConfig.createIndicatorDrawable(config.mSelectedColor);
        }

        if (config.mUnselectedDrawableId != 0) {
            this.mIndicatorUnselectedDrawable = ContextCompat.getDrawable(mContext, config.mUnselectedDrawableId);
        } else {
            this.mIndicatorUnselectedDrawable = IndicatorConfig.createIndicatorDrawable(config.mUnselectedColor);
        }

        if (this.mIndicatorStyle != config.mStyle) {
            this.mIndicatorStyle = config.mStyle;
            setIndicatorStyleUI();
        }

        this.mIndicatorMargin = config.mMargin;
        this.mIndicatorGravity = config.mGravity;
    }

    /**
     * 设置banner的参数
     *
     * @param config
     */
    public void setBannerConfig(BannerConfig config) {
        this.isAutoPlay = config.isAutoPlay;
        this.isScroll = config.isScroll;
        this.mDelayTime = config.mDelayTime;
        if (this.mScrollTime != config.mScrollTime) {
            this.mScrollTime = config.mScrollTime;
            initViewPagerScroll();
        }
        this.bannerDefaultImage.setImageResource(config.mBannerBackgroundImage);
        try {
            viewPager.setPageTransformer(true, config.mTransformer.newInstance());
        } catch (Exception e) {
            Log.e(tag, "Please set the PageTransformer class");
        }
        viewPager.setOffscreenPageLimit(config.mOffscreenPageLimit);
    }


    public Banner setImageLoader(IImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        return this;
    }

    public Banner setImages(List<?> imageUrls) {
        this.imageUrls = imageUrls;
        this.count = imageUrls.size();
        return this;
    }

    public Banner setImagesWithTitles(List<?> imageUrls, List<String> titles) {
        this.imageUrls = imageUrls;
        this.count = imageUrls.size();
        this.titles = titles;
        return this;
    }

    public void update(List<?> imageUrls, List<String> titles) {
        this.titles.clear();
        this.titles.addAll(titles);
        update(imageUrls);
    }

    public void update(List<?> imageUrls) {
        this.imageUrls.clear();
        this.imageViews.clear();
        this.indicatorImages.clear();
        this.imageUrls.addAll(imageUrls);
        this.count = this.imageUrls.size();
        start();
    }


    public void start() {
        setIndicatorStyleUI();
        setImageList(imageUrls);
        setData();
    }

    private void setTitleStyleUI() {
        if (titles.size() != imageUrls.size()) {
            throw new RuntimeException("[Banner] --> The number of titles and images is different");
        }
        if (titleBackground != -1) {
            titleView.setBackgroundColor(titleBackground);
        }
        if (titleHeight != -1) {
            titleView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, titleHeight));
        }
        if (titleTextColor != -1) {
            bannerTitle.setTextColor(titleTextColor);
        }
        if (titleTextSize != -1) {
            bannerTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        }
        if (titles != null && titles.size() > 0) {
            bannerTitle.setText(titles.get(0));
            bannerTitle.setVisibility(View.VISIBLE);
            titleView.setVisibility(View.VISIBLE);
        }
    }

    private void setIndicatorStyleUI() {
        int visibility = count > 1 ? View.VISIBLE : View.GONE;
        switch (mIndicatorStyle) {
            case Constants.CIRCLE_INDICATOR:
                indicator.setVisibility(visibility);
                break;
            case Constants.NUM_INDICATOR:
                numIndicator.setVisibility(visibility);
                break;
            case Constants.NUM_INDICATOR_TITLE:
                numIndicatorInside.setVisibility(visibility);
                setTitleStyleUI();
                break;
            case Constants.CIRCLE_INDICATOR_TITLE:
                indicator.setVisibility(visibility);
                setTitleStyleUI();
                break;
            case Constants.CIRCLE_INDICATOR_TITLE_INSIDE:
                indicatorInside.setVisibility(visibility);
                setTitleStyleUI();
                break;
        }
    }

    private void initImages() {
        imageViews.clear();
        switch (mIndicatorStyle) {
            case Constants.CIRCLE_INDICATOR:
            case Constants.CIRCLE_INDICATOR_TITLE:
            case Constants.CIRCLE_INDICATOR_TITLE_INSIDE:
                createIndicator();
                break;
            case Constants.NUM_INDICATOR_TITLE:
                numIndicatorInside.setText("1/" + count);
                break;
            case Constants.NUM_INDICATOR:
                numIndicator.setText("1/" + count);
                break;
        }
    }

    private void setImageList(List<?> imagesUrl) {
        if (imagesUrl == null || imagesUrl.size() <= 0) {
            bannerDefaultImage.setVisibility(VISIBLE);
            Log.e(tag, "The image data set is empty.");
            return;
        }
        bannerDefaultImage.setVisibility(GONE);
        initImages();
        for (int i = 0; i <= count + 1; i++) {
            View imageView = null;
            if (imageLoader != null) {
                imageView = imageLoader.createImageView(mContext);
            }
            if (imageView == null) {
                imageView = new ImageView(mContext);
            }
            setScaleType(imageView);
            Object url = null;
            if (i == 0) {
                url = imagesUrl.get(count - 1);
            } else if (i == count + 1) {
                url = imagesUrl.get(0);
            } else {
                url = imagesUrl.get(i - 1);
            }
            imageViews.add(imageView);
            if (imageLoader != null)
                imageLoader.displayImage(mContext, url, imageView);
            else
                Log.e(tag, "Please set images loader.");
        }
    }

    private void setScaleType(View imageView) {
        if (imageView instanceof ImageView) {
            ImageView view = ((ImageView) imageView);
            switch (scaleType) {
                case 0:
                    view.setScaleType(ScaleType.CENTER);
                    break;
                case 1:
                    view.setScaleType(ScaleType.CENTER_CROP);
                    break;
                case 2:
                    view.setScaleType(ScaleType.CENTER_INSIDE);
                    break;
                case 3:
                    view.setScaleType(ScaleType.FIT_CENTER);
                    break;
                case 4:
                    view.setScaleType(ScaleType.FIT_END);
                    break;
                case 5:
                    view.setScaleType(ScaleType.FIT_START);
                    break;
                case 6:
                    view.setScaleType(ScaleType.FIT_XY);
                    break;
                case 7:
                    view.setScaleType(ScaleType.MATRIX);
                    break;
            }

        }
    }

    private void createIndicator() {
        indicatorImages.clear();
        indicator.removeAllViews();
        indicatorInside.removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
            params.leftMargin = mIndicatorMargin;
            params.rightMargin = mIndicatorMargin;
            if (i == 0) {
                imageView.setImageDrawable(mIndicatorSelectedDrawable);
            } else {
                imageView.setImageDrawable(mIndicatorUnselectedDrawable);
            }
            indicatorImages.add(imageView);
            if (mIndicatorStyle == Constants.CIRCLE_INDICATOR ||
                    mIndicatorStyle == Constants.CIRCLE_INDICATOR_TITLE)
                indicator.addView(imageView, params);
            else if (mIndicatorStyle == Constants.CIRCLE_INDICATOR_TITLE_INSIDE)
                indicatorInside.addView(imageView, params);
        }
    }


    private void setData() {
        currentItem = 1;
        if (adapter == null) {
            adapter = new BannerPagerAdapter();
            viewPager.addOnPageChangeListener(this);
        }
        viewPager.setAdapter(adapter);
        viewPager.setFocusable(true);
        viewPager.setCurrentItem(1);
        if (mIndicatorGravity != -1)
            indicator.setGravity(mIndicatorGravity);
        if (isScroll && count > 1) {
            viewPager.setScrollable(true);
        } else {
            viewPager.setScrollable(false);
        }
        if (isAutoPlay)
            startAutoPlay();
    }


    public void startAutoPlay() {
        handler.removeCallbacks(task);
        handler.postDelayed(task, mDelayTime);
    }

    public void stopAutoPlay() {
        handler.removeCallbacks(task);
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (count > 1 && isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
//                Log.i(tag, "curr:" + currentItem + " count:" + count);
                if (currentItem == 1) {
                    viewPager.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    viewPager.setCurrentItem(currentItem);
                    handler.postDelayed(task, mDelayTime);
                }
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.i(tag, ev.getAction() + "--" + isAutoPlay);
        if (isAutoPlay) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL
                    || action == MotionEvent.ACTION_OUTSIDE) {
                startAutoPlay();
            } else if (action == MotionEvent.ACTION_DOWN) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 返回真实的位置
     *
     * @param position
     * @return 下标从0开始
     */
    public int toRealPosition(int position) {
        int realPosition = (position - 1) % count;
        if (realPosition < 0)
            realPosition += count;
        return realPosition;
    }

    class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(imageViews.get(position));
            View view = imageViews.get(position);
            if (listener != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.OnBannerClick(toRealPosition(position));
                    }
                });
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
//        Log.i(tag,"currentItem: "+currentItem);
        switch (state) {
            case 0://No operation
                if (currentItem == 0) {
                    viewPager.setCurrentItem(count, false);
                } else if (currentItem == count + 1) {
                    viewPager.setCurrentItem(1, false);
                }
                break;
            case 1://start Sliding
                if (currentItem == count + 1) {
                    viewPager.setCurrentItem(1, false);
                } else if (currentItem == 0) {
                    viewPager.setCurrentItem(count, false);
                }
                break;
            case 2://end Sliding
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrolled(toRealPosition(position), positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(toRealPosition(position));
        }
        if (mIndicatorStyle == Constants.CIRCLE_INDICATOR ||
                mIndicatorStyle == Constants.CIRCLE_INDICATOR_TITLE ||
                mIndicatorStyle == Constants.CIRCLE_INDICATOR_TITLE_INSIDE) {
//            indicatorImages.get((lastPosition - 1 + count) % count).setImageResource(mIndicatorUnselectedResId);
            indicatorImages.get((lastPosition - 1 + count) % count).setImageDrawable(mIndicatorUnselectedDrawable);
            indicatorImages.get((position - 1 + count) % count).setImageDrawable(mIndicatorSelectedDrawable);
            lastPosition = position;
        }
        if (position == 0) position = count;
        if (position > count) position = 1;
        switch (mIndicatorStyle) {
            case Constants.CIRCLE_INDICATOR:
                break;
            case Constants.NUM_INDICATOR:
                numIndicator.setText(position + "/" + count);
                break;
            case Constants.NUM_INDICATOR_TITLE:
                numIndicatorInside.setText(position + "/" + count);
                bannerTitle.setText(titles.get(position - 1));
                break;
            case Constants.CIRCLE_INDICATOR_TITLE:
                bannerTitle.setText(titles.get(position - 1));
                break;
            case Constants.CIRCLE_INDICATOR_TITLE_INSIDE:
                bannerTitle.setText(titles.get(position - 1));
                break;
        }

    }

    public Banner setOnBannerListener(OnBannerListener listener) {
        this.listener = listener;
        return this;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }

    public void releaseBanner() {
        handler.removeCallbacksAndMessages(null);
    }
}
