package com.test.banner.transformer;

import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


/**
 * 左右条目缩小漏出效果
 */
public class MultiplePagerScaleInTransformer implements ViewPager2.PageTransformer {
    private int marginPx;
    private float scale;

    public MultiplePagerScaleInTransformer(@Px int marginPx, float scale) {
        this.marginPx = marginPx;
        this.scale = scale;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        ViewPager2 viewPager = requireViewPager(page);
        float offset = position * marginPx;
        if (viewPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                page.setTranslationX(offset);
            } else {
                page.setTranslationX(-offset);
            }
            page.setScaleY(1 - (scale * Math.abs(position)));
        } else {
            page.setTranslationY(-offset);
            page.setScaleX(1 - (scale * Math.abs(position)));
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
