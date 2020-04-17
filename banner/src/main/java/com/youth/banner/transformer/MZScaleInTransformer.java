package com.youth.banner.transformer;

import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class MZScaleInTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinScale = DEFAULT_MIN_SCALE;

    public MZScaleInTransformer() {
    }

    public MZScaleInTransformer(float minScale) {
        this.mMinScale = minScale;
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        ViewPager2 viewPager = requireViewPager(view);
        float paddingLeft = viewPager.getPaddingLeft();
        float paddingRight = viewPager.getPaddingRight();
        float width = viewPager.getMeasuredWidth();
        float offsetPosition = paddingLeft / (width - paddingLeft - paddingRight);
        float currentPos = position - offsetPosition;
        float reduceX = 0;
        float itemWidth = view.getWidth();
        //由于左右边的缩小而减小的x的大小的一半
        reduceX = (1.0f - mMinScale) * itemWidth / 2.0f;
        if (currentPos <= -1.0f) {
            view.setTranslationX(reduceX);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
        } else if (currentPos <= 1.0) {
            float scale = (1.0f - mMinScale) * Math.abs(1.0f - Math.abs(currentPos));
            float translationX = currentPos * -reduceX;
            if (currentPos <= -0.5) {//两个view中间的临界，这时两个view在同一层，左侧View需要往X轴正方向移动覆盖的值()
                view.setTranslationX(translationX + Math.abs(Math.abs(currentPos) - 0.5f) / 0.5f);
            } else if (currentPos <= 0.0f) {
                view.setTranslationX(translationX);
            } else if (currentPos >= 0.5) {//两个view中间的临界，这时两个view在同一层
                view.setTranslationX(translationX - Math.abs(Math.abs(currentPos) - 0.5f) / 0.5f);
            } else {
                view.setTranslationX(translationX);
            }
            view.setScaleX(scale + mMinScale);
            view.setScaleY(scale + mMinScale);
        } else {
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
            view.setTranslationX(-reduceX);
        }

    }

    private ViewPager2 requireViewPager(@NonNull View page) {
        ViewParent parent = page.getParent();
        ViewParent parentParent = parent.getParent();

        if (parent instanceof RecyclerView && parentParent instanceof ViewPager2) {
            return (ViewPager2) parentParent;
        }

        throw new IllegalStateException(
                "Expected the page view to be managed by a ViewPager2 instance.");
    }
}
