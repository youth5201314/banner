package com.youth.banner.config;

import android.support.v4.view.ViewPager;

import com.youth.banner.R;

/**
 * @Author: luqihua
 * @Time: 2018/5/17
 * @Description: BannerConfig
 */

public class BannerConfig {

    public int mBannerBackgroundImage;
    public int mDelayTime;
    public int mScrollTime;
    public Class<? extends ViewPager.PageTransformer> mTransformer;
    public int mOffscreenPageLimit;
    public boolean isAutoPlay;
    public boolean isScroll;


    private BannerConfig(Builder builder) {
        this.mBannerBackgroundImage = builder.mBannerBackgroundImage;
        this.mDelayTime = builder.mDelayTime;
        this.mScrollTime = builder.mScrollTime;
        this.isAutoPlay = builder.isAutoPlay;
        this.isScroll = builder.isScroll;
        this.mTransformer = builder.mTransformer;
        this.mOffscreenPageLimit = builder.mOffscreenPageLimit;
    }

    public static class Builder {
        int mBannerBackgroundImage = R.drawable.no_banner;
        int mDelayTime = Constants.DELAY_TIME;
        int mScrollTime = Constants.SCROLL_TIME;
        boolean isAutoPlay = Constants.IS_AUTO_PLAY;
        boolean isScroll = Constants.IS_SCROLL;
        Class<? extends ViewPager.PageTransformer> mTransformer;
        int mOffscreenPageLimit = 1;

        public Builder setBannerBackgroundImage(int bannerBackgroundImage) {
            this.mBannerBackgroundImage = bannerBackgroundImage;
            return this;
        }

        public Builder setDelayTime(int delayTime) {
            this.mDelayTime = delayTime;
            return this;
        }

        public Builder setScrollTime(int scrollTime) {
            this.mScrollTime = scrollTime;
            return this;
        }

        public Builder setAutoPlay(boolean autoPlay) {
            isAutoPlay = autoPlay;
            return this;
        }

        public Builder setScroll(boolean scroll) {
            isScroll = scroll;
            return this;
        }

        public Builder setTransformer(Class<? extends ViewPager.PageTransformer> transformer) {
            this.mTransformer = transformer;
            return this;
        }

        public Builder setOffscreenPageLimit(int limit) {
            this.mOffscreenPageLimit = limit;
            return this;
        }

        public BannerConfig build() {
            return new BannerConfig(this);
        }
    }
}
