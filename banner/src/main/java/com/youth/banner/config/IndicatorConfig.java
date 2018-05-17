package com.youth.banner.config;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IdRes;
import android.view.Gravity;

/**
 * @Author: luqihua
 * @Time: 2018/5/17
 * @Description: IndicatorConfig
 */

public class IndicatorConfig {
    @Constants.IndicatorStyle
    public int mStyle;

    @Constants.IndicatorGravity
    public int mGravity;

    public int mMargin;
    public int mWidth;
    public int mHeight;

    public int mSelectedDrawableId;
    public int mUnselectedDrawableId;

    public int mSelectedColor;
    public int mUnselectedColor;

    private IndicatorConfig(Builder builder) {
        this.mStyle = builder.mStyle;
        this.mGravity = parseGravity(builder.mGravity);
        this.mMargin = builder.mMargin;
        this.mWidth = builder.mWidth == 0 ? getDefaultIndicatorSize() : builder.mWidth;
        this.mHeight = builder.mHeight == 0 ? mWidth : builder.mHeight;
        this.mSelectedColor = builder.mSelectedColor;
        this.mUnselectedColor = builder.mUnselectedColor;
        this.mSelectedDrawableId = builder.mSelectedDrawableId;
        this.mUnselectedDrawableId = builder.mUnselectedDrawableId;
    }

    /**
     * 返回一个默认的指示器尺寸
     * @return
     */
    public static int getDefaultIndicatorSize() {
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        return screenWidth / 80;
    }

    /**
     * 根据颜色创建一个圆形drawable
     * @param color
     * @return
     */
    public static Drawable createIndicatorDrawable(int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(color);
        return drawable;
    }


    public static int parseGravity(int gravity) {
        int value = Gravity.CENTER;
        switch (gravity) {
            case Constants.LEFT:
                value = Gravity.START | Gravity.CENTER_VERTICAL;
                break;
            case Constants.CENTER:
                value = Gravity.CENTER;
                break;
            case Constants.RIGHT:
                value = Gravity.END | Gravity.CENTER_VERTICAL;
                break;
        }
        return value;
    }

    public static class Builder {
        int mStyle = Constants.CIRCLE_INDICATOR;
        int mGravity = Constants.CENTER;
        int mMargin = Constants.INDICATOR_MARGIN;
        int mWidth;
        int mHeight;
        int mSelectedColor = Color.parseColor("#77000000");
        int mUnselectedColor = Color.parseColor("#88ffffff");
        int mSelectedDrawableId;
        int mUnselectedDrawableId;

        public Builder style(@Constants.IndicatorStyle int style) {
            this.mStyle = style;
            return this;
        }

        public Builder gravity(@Constants.IndicatorGravity int gravity) {
            this.mGravity = gravity;
            return this;
        }

        public Builder margin(int margin) {
            this.mMargin = margin;
            return this;
        }

        public Builder width(int width) {
            this.mWidth = width;
            return this;
        }

        public Builder height(int height) {
            this.mHeight = height;
            return this;
        }

        public Builder selectedColor(int color) {
            this.mSelectedColor = color;
            return this;
        }

        public Builder unselectedColor(int color) {
            this.mUnselectedColor = color;
            return this;
        }

        public Builder selectedDrawableId(@IdRes int resId) {
            this.mSelectedDrawableId = resId;
            return this;
        }

        public Builder unselectedDrawableId(@IdRes int resId) {
            this.mUnselectedDrawableId = resId;
            return this;
        }

        public IndicatorConfig build() {
            return new IndicatorConfig(this);
        }
    }
}
