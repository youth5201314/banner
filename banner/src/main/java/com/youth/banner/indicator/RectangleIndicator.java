package com.youth.banner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.youth.banner.config.BannerConfig;

/**
 * 矩形（条形）指示器
 * 1、可以设置选中和默认的宽度、指示器的圆角
 * 2、如果需要正方形将圆角设置为0，可将宽度和高度设置为一样
 * 3、如果不想选中时变长，可将选中的宽度和默认宽度设置为一样
 */
public class RectangleIndicator extends BaseIndicator {
    RectF rectF;

    public RectangleIndicator(Context context) {
        this(context, null);
    }

    public RectangleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectangleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = config.getIndicatorSize();
        if (count <= 1) {
            return;
        }
        //间距*（总数-1）+默认宽度*（总数-1）+选中宽度
        int space = config.getIndicatorSpace() * (count - 1);
        int normal = config.getNormalWidth() * (count - 1);
        setMeasuredDimension(space + normal + config.getSelectedWidth(), config.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = config.getIndicatorSize();
        if (count <= 1) {
            return;
        }
        float left = 0;
        for (int i = 0; i < count; i++) {
            mPaint.setColor(config.getCurrentPosition() == i ? config.getSelectedColor() : config.getNormalColor());
            int indicatorWidth = config.getCurrentPosition() == i ? config.getSelectedWidth() : config.getNormalWidth();
            rectF.set(left, 0, left + indicatorWidth, config.getHeight());
            left += indicatorWidth + config.getIndicatorSpace();
            canvas.drawRoundRect(rectF, config.getRadius(), config.getRadius(), mPaint);
        }
    }
}
