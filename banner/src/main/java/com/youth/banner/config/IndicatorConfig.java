package com.youth.banner.config;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class IndicatorConfig {

    private int indicatorSize;
    private int currentPosition;
    private int gravity = Direction.CENTER;
    private float indicatorSpace = BannerConfig.INDICATOR_SPACE;
    private float normalWidth = BannerConfig.INDICATOR_NORMAL_WIDTH;
    private float selectedWidth = BannerConfig.INDICATOR_SELECTED_WIDTH;
    @ColorInt
    private int normalColor = BannerConfig.INDICATOR_NORMAL_COLOR;
    @ColorInt
    private int selectedColor = BannerConfig.INDICATOR_SELECTED_COLOR;

    private Margins margins;

    //是否包含指示器
    private boolean includeIndicator = true;

    @IntDef({Direction.LEFT, Direction.CENTER, Direction.RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
        int LEFT = 0;
        int CENTER = 1;
        int RIGHT = 2;
    }

    public static class Margins {
        public int leftMargin;
        public int topMargin;
        public int rightMargin;
        public int bottomMargin;

        public Margins() {
            this(BannerConfig.INDICATOR_MARGIN);
        }

        public Margins(int marginSize) {
            this(marginSize,marginSize,marginSize,marginSize);
        }

        public Margins(int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
            this.leftMargin = leftMargin;
            this.topMargin = topMargin;
            this.rightMargin = rightMargin;
            this.bottomMargin = bottomMargin;
        }
    }

    public Margins getMargins() {
        if (margins==null){
            setMargins(new Margins());
        }
        return margins;
    }

    public IndicatorConfig setMargins(Margins margins) {
        this.margins = margins;
        return this;
    }

    public int getIndicatorSize() {
        return indicatorSize;
    }

    public IndicatorConfig setIndicatorSize(int indicatorSize) {
        if (this.indicatorSize!=indicatorSize) {
            this.indicatorSize = indicatorSize;
        }
        return this;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public IndicatorConfig setNormalColor(int normalColor) {
        if (this.normalColor != normalColor) {
            this.normalColor = normalColor;
        }
        return this;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public IndicatorConfig setSelectedColor(int selectedColor) {
        if (this.selectedColor != selectedColor) {
            this.selectedColor = selectedColor;
        }
        return this;
    }

    public float getIndicatorSpace() {
        return indicatorSpace;
    }

    public IndicatorConfig setIndicatorSpace(float indicatorSpace) {
        if (this.indicatorSpace != indicatorSpace) {
            this.indicatorSpace = indicatorSpace;
        }
        return this;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public IndicatorConfig setCurrentPosition(int currentPosition) {
        if (this.currentPosition != currentPosition) {
            this.currentPosition = currentPosition;
        }
        return this;
    }

    public float getNormalWidth() {
        return normalWidth;
    }

    public IndicatorConfig setNormalWidth(float normalWidth) {
        if (this.normalWidth != normalWidth) {
            this.normalWidth = normalWidth;
        }
        return this;
    }

    public float getSelectedWidth() {
        return selectedWidth;
    }

    public IndicatorConfig setSelectedWidth(float selectedWidth) {
        if (this.selectedWidth != selectedWidth) {
            this.selectedWidth = selectedWidth;
        }
        return this;
    }

    public int getGravity() {
        return gravity;
    }

    public IndicatorConfig setGravity(@Direction int gravity) {
        if (this.gravity != gravity) {
            this.gravity = gravity;
        }
        return this;
    }

    public boolean isIncludeIndicator() {
        return includeIndicator;
    }

    public void setIncludeIndicator(boolean includeIndicator) {
        this.includeIndicator = includeIndicator;
    }
}
