package com.youth.banner.vp2;

import android.view.View;

import androidx.annotation.NonNull;

public interface Indicator {
    @NonNull
    View getIndicatorView();

    void onChanged(int itemCount, int currentPosition);

    void onPageSelected(int position);

    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    void onPageScrollStateChanged(int state);
}
