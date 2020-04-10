package com.youth.banner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;


public class CircleIndicator extends BaseIndicator {
    private float mNormalRadius;
    private float mSelectedRadius;
    private float maxRadius;

    public CircleIndicator(Context context) {
        this(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mNormalRadius = config.getNormalWidth() / 2;
        mSelectedRadius = config.getSelectedWidth() / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = config.getIndicatorSize();
        if (count <= 1) return;

        mNormalRadius = config.getNormalWidth() / 2;
        mSelectedRadius = config.getSelectedWidth() / 2;
        //考虑当 选中和默认 的大小不一样的情况
        maxRadius = Math.max(mSelectedRadius, mNormalRadius);
        //间距*（总数-1）+最大的半径（考虑有时候选中时会变大的情况）+默认半径*（总数-1）
        int width = (int) ((count - 1) * config.getIndicatorSpace() + 2 * (maxRadius + mNormalRadius * (count - 1)));
        int height = (int) (2 * maxRadius);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = config.getIndicatorSize();
        if (count <= 1) return;
        mPaint.setColor(config.getNormalColor());
        for (int i = 0; i < count; i++) {
            float x = maxRadius + (config.getNormalWidth() + config.getIndicatorSpace()) * i;
            canvas.drawCircle(x, maxRadius, mNormalRadius, mPaint);
        }
        mPaint.setColor(config.getSelectedColor());
        float x = maxRadius + (config.getNormalWidth() + config.getIndicatorSpace()) * config.getCurrentPosition();
        canvas.drawCircle(x , maxRadius, mSelectedRadius, mPaint);

    }

}
