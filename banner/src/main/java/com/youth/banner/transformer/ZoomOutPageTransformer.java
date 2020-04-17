package com.youth.banner.transformer;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;


public class ZoomOutPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinScale = DEFAULT_MIN_SCALE;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    public ZoomOutPageTransformer() {
    }

    public ZoomOutPageTransformer(float minScale,float minAlpha ) {
        this.mMinScale = minScale;
        this.mMinAlpha = minAlpha;
    }

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0f);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(mMinScale, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(mMinAlpha +
                    (scaleFactor - mMinScale) /
                            (1 - mMinScale) * (1 - mMinAlpha));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0f);
        }
    }
}
