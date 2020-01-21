package com.test.banner.itemdecoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MarginItemDecoration extends RecyclerView.ItemDecoration {
    private int marginPx;

    public MarginItemDecoration(int marginPx) {
        this.marginPx = marginPx;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        LinearLayoutManager linearLayoutManager = requireLinearLayoutManager(parent);
        if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            outRect.top = marginPx;
            outRect.bottom = marginPx;
        } else {
            outRect.left = marginPx;
            outRect.right = marginPx;
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
