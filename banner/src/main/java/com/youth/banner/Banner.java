package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;


public class Banner extends LoopLayout {
    private ImageLoaderInterface<View> imageLoader;
    private int scaleType = 1;
    private List<?> imagesUrls;
    private OnBannerClickListener bannerListener;//only supported in Banner
    private LoopLayout.Builder builder;

    public Banner(Context context) {
        super(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        builder = new Builder(this);
    }

    @Override
    protected void initStyleable(TypedArray typedArray) {
        super.initStyleable(typedArray);
        scaleType = typedArray.getInt(R.styleable.Banner_image_scale_type, scaleType);
    }

    public Banner isAutoPlay(boolean isAutoPlay) {
        builder.isAutoPlay(isAutoPlay);
        return this;
    }

    public Banner setImageLoader(ImageLoaderInterface imageLoader) {
        this.imageLoader = imageLoader;
        return this;
    }

    public Banner setDelayTime(int delayTime) {
        builder.setDelayTime(delayTime);
        return this;
    }

    public Banner setIndicatorGravity(int type) {
        builder.setIndicatorGravity(type);
        return this;
    }

    public LoopLayout setBannerAnimation(Class<? extends ViewPager.PageTransformer> transformer) {
        builder.setBannerAnimation(transformer);
        return this;
    }

    /**
     * Set the number of pages that should be retained to either side of the
     * current page in the view hierarchy in an idle state. Pages beyond this
     * limit will be recreated from the adapter when needed.
     *
     * @param limit How many pages will be kept offscreen in an idle state.
     * @return Banner
     */
    public LoopLayout setOffscreenPageLimit(int limit) {
        builder.setOffscreenPageLimit(limit);
        return this;
    }

    /**
     * Set a {@link ViewPager.PageTransformer} that will be called for each attached page whenever
     * the scroll position is changed. This allows the application to apply custom property
     * transformations to each page, overriding the default sliding look and feel.
     *
     * @param reverseDrawingOrder true if the supplied PageTransformer requires page views
     *                            to be drawn from last to first instead of first to last.
     * @param transformer         PageTransformer that will modify each page's animation properties
     * @return Banner
     */
    public Banner setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer) {
        builder.setPageTransformer(reverseDrawingOrder, transformer);
        return this;
    }

    public Banner setBannerTitles(List<String> titles) {
        builder.setBannerTitles(titles);
        return this;
    }

    public Banner setBannerStyle(int bannerStyle) {
        builder.setBannerStyle(bannerStyle);
        return this;
    }

    public Banner setViewPagerIsScroll(boolean isScroll) {
        builder.setViewPagerIsScroll(isScroll);
        return this;
    }


    /**
     * From now on ,we don't need to know your data source but only the count ,items can be init by overwriting {@link #getItemView(int)}.
     * So Use {@link #setItemCount(int)} instead.
     */
    public Banner setImages(List<?> imageUrls) {
        this.imagesUrls = imageUrls;
        setItemCount(imageUrls == null ? 0 : imageUrls.size());
        return this;
    }

    /**
     * @see #setImages(List)  For the same reason,use {@link #update(int, List)} instead.
     */
    public void update(List<?> imageUrls, List<String> titles) {
        this.imagesUrls = imageUrls;
        update(imageUrls == null ? 0 : imageUrls.size(), titles);
    }

    /**
     * @see #setImages(List) For the same reason, use {@link #update(int)} instead.
     */
    public void update(List<?> imageUrls) {
        this.imagesUrls = imageUrls;
        update(imageUrls == null ? 0 : imageUrls.size());
    }

    @Override
    protected View getItemView(int realPosition) {
        View imageView = null;
        if (imageLoader != null) {
            imageView = imageLoader.createImageView(context);
        }
        if (imageView == null) {
            imageView = new ImageView(context);
        }
        setScaleType(imageView);
        if (imageLoader != null)
            imageLoader.displayImage(context, imagesUrls.get(realPosition), imageView);
        else
            Log.e(tag, "Please set images loader.");
        return imageView;
    }

    private void setScaleType(View imageView) {
        if (imageView instanceof ImageView) {
            ImageView view = ((ImageView) imageView);
            switch (scaleType) {
                case 0:
                    view.setScaleType(ImageView.ScaleType.CENTER);
                    break;
                case 1:
                    view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    break;
                case 2:
                    view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
                case 3:
                    view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case 4:
                    view.setScaleType(ImageView.ScaleType.FIT_END);
                    break;
                case 5:
                    view.setScaleType(ImageView.ScaleType.FIT_START);
                    break;
                case 6:
                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                case 7:
                    view.setScaleType(ImageView.ScaleType.MATRIX);
                    break;
            }

        }
    }

    @Override
    protected void initItem(View view, final int position) {
        super.initItem(view, position);
        if (bannerListener != null) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(tag, "你正在使用旧版点击事件接口，下标是从1开始，" +
                            "为了体验请更换为setOnBannerListener，下标从0开始计算");
                    bannerListener.OnBannerClick(position);
                }
            });
        }
    }

    @Deprecated
    public Banner setOnBannerClickListener(OnBannerClickListener listener) {
        this.bannerListener = listener;
        return this;
    }

}