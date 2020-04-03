package com.youth.banner.indicator;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.youth.banner.config.IndicatorConfig;

public class BaseIndicator extends View implements Indicator {
    protected IndicatorConfig config;
    protected Paint mPaint;
    protected float positionOffset;

    public BaseIndicator(Context context) {
        this(context, null);
    }

    public BaseIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config = new IndicatorConfig();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(config.getNormalColor());
    }

    @NonNull
    @Override
    public View getIndicatorView() {
        if (config.isAttachToBanner()) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            switch (config.getGravity()) {
                case IndicatorConfig.Direction.LEFT:
                    layoutParams.gravity = Gravity.BOTTOM | Gravity.START;
                    break;
                case IndicatorConfig.Direction.CENTER:
                    layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                    break;
                case IndicatorConfig.Direction.RIGHT:
                    layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
                    break;
            }
            layoutParams.leftMargin = config.getMargins().leftMargin;
            layoutParams.rightMargin = config.getMargins().rightMargin;
            layoutParams.topMargin = config.getMargins().topMargin;
            layoutParams.bottomMargin = config.getMargins().bottomMargin;
            setLayoutParams(layoutParams);
        }
        return this;
    }

    @Override
    public IndicatorConfig getIndicatorConfig() {
        return config;
    }

    @Override
    public void onPageChanged(int count, int currentPosition) {
        config.setIndicatorSize(count);
        config.setCurrentPosition(currentPosition);
        requestLayout();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.positionOffset=positionOffset;
    }

    @Override
    public void onPageSelected(int position) {
        config.setCurrentPosition(position);
        postInvalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
