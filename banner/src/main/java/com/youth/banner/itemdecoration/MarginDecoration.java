package com.youth.banner.itemdecoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.util.BannerUtils;


public class MarginDecoration extends RecyclerView.ItemDecoration {
    private int mMarginPx;

    public MarginDecoration(@Px int margin) {
        mMarginPx = margin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        LinearLayoutManager linearLayoutManager = requireLinearLayoutManager(parent);
        if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            outRect.top = mMarginPx;
            outRect.bottom = mMarginPx;
        } else {
            outRect.left = mMarginPx;
            outRect.right = mMarginPx;
        }
    }

    private LinearLayoutManager requireLinearLayoutManager(@NonNull RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return (LinearLayoutManager) layoutManager;
        }
        throw new IllegalStateException("The layoutManager must be LinearLayoutManager");
    }
}
