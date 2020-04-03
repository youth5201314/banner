package com.youth.banner.config;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class IndicatorConfig {

    private int indicatorSize;
    private int currentPosition;
    private int gravity = Direction.CENTER;
    private int indicatorSpace = BannerConfig.INDICATOR_SPACE;
    private int normalWidth = BannerConfig.INDICATOR_NORMAL_WIDTH;
    private int selectedWidth = BannerConfig.INDICATOR_SELECTED_WIDTH;
    @ColorInt
    private int normalColor = BannerConfig.INDICATOR_NORMAL_COLOR;
    @ColorInt
    private int selectedColor = BannerConfig.INDICATOR_SELECTED_COLOR;

    private int radius = BannerConfig.INDICATOR_RADIUS;
    private int height = BannerConfig.INDICATOR_HEIGHT;

    private Margins margins;

    //是将指示器添加到banner上
    private boolean attachToBanner = true;

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
            this(marginSize, marginSize, marginSize, marginSize);
        }

        public Margins(int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
            this.leftMargin = leftMargin;
            this.topMargin = topMargin;
            this.rightMargin = rightMargin;
            this.bottomMargin = bottomMargin;
        }
    }

    public Margins getMargins() {
        if (margins == null) {
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
        this.indicatorSize = indicatorSize;
        return this;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public IndicatorConfig setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public IndicatorConfig setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        return this;
    }

    public float getIndicatorSpace() {
        return indicatorSpace;
    }

    public IndicatorConfig setIndicatorSpace(int indicatorSpace) {
        this.indicatorSpace = indicatorSpace;
        return this;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public IndicatorConfig setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        return this;
    }

    public float getNormalWidth() {
        return normalWidth;
    }

    public IndicatorConfig setNormalWidth(int normalWidth) {
        this.normalWidth = normalWidth;
        return this;
    }

    public float getSelectedWidth() {
        return selectedWidth;
    }

    public IndicatorConfig setSelectedWidth(int selectedWidth) {
        this.selectedWidth = selectedWidth;
        return this;
    }

    public int getGravity() {
        return gravity;
    }

    public IndicatorConfig setGravity(@Direction int gravity) {
        this.gravity = gravity;
        return this;
    }

    public boolean isAttachToBanner() {
        return attachToBanner;
    }

    public IndicatorConfig setAttachToBanner(boolean attachToBanner) {
        this.attachToBanner = attachToBanner;
        return this;
    }

    public int getRadius() {
        return radius;
    }

    public IndicatorConfig setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public IndicatorConfig setHeight(int height) {
        this.height = height;
        return this;
    }
}
