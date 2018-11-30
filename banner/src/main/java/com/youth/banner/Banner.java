package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

/**
 * 图片Banner
 */
public class Banner extends LoopLayout {
    private int scaleType;
    private BannerAdapter bannerAdapter;

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
        bannerAdapter = new BannerAdapter(scaleType);
        setAdapter(bannerAdapter);
    }

    @Override
    protected void initStyleable(TypedArray typedArray) {
        super.initStyleable(typedArray);
        scaleType = typedArray.getInt(R.styleable.Banner_image_scale_type, 1);
    }

    public void setImageLoader(ImageLoaderInterface imageLoader) {
        bannerAdapter.setImageLoader(imageLoader);
    }


    public void setImages(List<?> imageUrls) {
        bannerAdapter.setImagesUrls(imageUrls);
    }

    public void setTitles(List<String> titles) {
        bannerAdapter.setTitles(titles);
    }

    public void setScaleType(int scaleType) {
        bannerAdapter.setScaleType(scaleType);
    }

    /**
     * @see #setImages(List).
     */
    public void update(List<?> imageUrls) {
        setImages(imageUrls);
        bannerAdapter.notifyDataSetChanged();
    }

    public void start() {
        bannerAdapter.notifyDataSetChanged();
    }
}