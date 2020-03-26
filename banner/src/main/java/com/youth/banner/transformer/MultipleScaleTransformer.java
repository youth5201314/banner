package com.youth.banner.transformer;

import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * 实现画廊效果，配合addItemDecoration一起使用，单独使用无效
 */
public class MultipleScaleTransformer implements ViewPager2.PageTransformer {
    private int mMarginPx;
    private float mScale;

    public MultipleScaleTransformer(@Px int marginPx, float scale) {
        mMarginPx = marginPx;
        mScale = scale;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        ViewPager2 viewPager = requireViewPager(page);
        if (viewPager==null) return;
        float offset = position * mMarginPx;
        float scaleFactor = 1 - (mScale * Math.abs(position));
        if (viewPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                page.setTranslationX(offset);
            } else {
                page.setTranslationX(-offset);
            }
            page.setScaleY(scaleFactor);
        } else {
            page.setTranslationY(-offset);
            page.setScaleY(scaleFactor);
        }
    }

    private ViewPager2 requireViewPager(@NonNull View page) {
        ViewParent parent = page.getParent();
        ViewParent parentParent = parent.getParent();

        if (parent instanceof RecyclerView && parentParent instanceof ViewPager2) {
            //判断是否设置了间距，否则单独使用无效
            if (((RecyclerView) parent).getItemDecorationCount()>0) {
                return (ViewPager2) parentParent;
            }else {
                return null;
            }
        }

        throw new IllegalStateException(
                "Expected the page view to be managed by a ViewPager2 instance.");
    }
}
