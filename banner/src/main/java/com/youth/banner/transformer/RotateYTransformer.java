package com.youth.banner.transformer;

import android.view.View;

import androidx.annotation.NonNull;

public class RotateYTransformer extends BasePageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 35f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    public RotateYTransformer() {
    }

    public RotateYTransformer(float maxRotate) {
        mMaxRotate = maxRotate;
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        view.setPivotY(view.getHeight()/2);

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotationY(-1 * mMaxRotate);
            view.setPivotX(view.getWidth());
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            view.setRotationY(position * mMaxRotate);

            //[0,-1]
            if (position < 0) {
                view.setPivotX(view.getWidth() * (DEFAULT_CENTER + DEFAULT_CENTER * (-position)));
                view.setPivotX(view.getWidth());
            } else {//[1,0]
                view.setPivotX(view.getWidth() * DEFAULT_CENTER * (1 - position));
                view.setPivotX(0);
            }

            // Scale the page down (between MIN_SCALE and 1)
        } else {
            // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotationY(1 * mMaxRotate);
            view.setPivotX(0);
        }
    }
}
