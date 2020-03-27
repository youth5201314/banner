package com.youth.banner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.youth.banner.util.BannerUtils;

public class RoundLinesIndicator extends BaseIndicator {

    public RoundLinesIndicator(Context context) {
        this(context, null);
    }

    public RoundLinesIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundLinesIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = config.getIndicatorSize();
        if (count <= 1) return;
        setMeasuredDimension((int) (config.getSelectedWidth() * count), config.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = config.getIndicatorSize();
        if (count <= 1) return;

        mPaint.setColor(config.getNormalColor());
        RectF oval = new RectF(0, 0, canvas.getWidth(), config.getHeight());
        canvas.drawRoundRect(oval, config.getRadius(), config.getRadius(), mPaint);

        mPaint.setColor(config.getSelectedColor());
        int left = (int) (config.getCurrentPosition() * config.getSelectedWidth());
        RectF rectF = new RectF(left, 0, left + config.getSelectedWidth(), config.getHeight());
        canvas.drawRoundRect(rectF, config.getRadius(), config.getRadius(), mPaint);
    }
}
