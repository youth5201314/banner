package com.youth.banner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

public class RectangleIndicator extends BaseIndicator {

    public RectangleIndicator(Context context) {
        this(context, null);
    }

    public RectangleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectangleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = config.getIndicatorSize();
        if (count <= 1) return;
        //间距*（总数-1）+默认宽度*总数
        int width = (int) ((count - 1) * config.getIndicatorSpace() + config.getNormalWidth() * count);
        setMeasuredDimension(width, config.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = config.getIndicatorSize();
        if (count <= 1) return;
        float left = 0;
        for (int i = 0; i < count; i++) {
            mPaint.setColor(config.getCurrentPosition() == i ? config.getSelectedColor() : config.getNormalColor());
            RectF rectF = new RectF(left, 0, left + config.getNormalWidth(), config.getHeight());
            left = (i+1) * (config.getNormalWidth() + config.getIndicatorSpace());
            canvas.drawRoundRect(rectF, config.getRadius(), config.getRadius(), mPaint);
        }
    }
}
