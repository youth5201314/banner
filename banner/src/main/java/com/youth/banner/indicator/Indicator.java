package com.youth.banner.indicator;

import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnPageChangeListener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}
