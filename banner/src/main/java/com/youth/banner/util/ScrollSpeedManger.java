package com.youth.banner.util;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.youth.banner.Banner;

import java.lang.reflect.Field;


/**
 * 改变LinearLayoutManager的切换速度
 */
public class ScrollSpeedManger extends LinearLayoutManager {
    private Banner banner;

    public ScrollSpeedManger(Banner banner, LinearLayoutManager linearLayoutManager) {
        super(banner.getContext(), linearLayoutManager.getOrientation(), false);
        this.banner = banner;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            protected int calculateTimeForDeceleration(int dx) {
                return banner.getScrollTime();
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public static void reflectLayoutManager(Banner banner) {
        if (banner.getScrollTime() < 100) return;
        try {
            ViewPager2 viewPager2 = banner.getViewPager2();
            RecyclerView recyclerView = (RecyclerView) viewPager2.getChildAt(0);
            recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            ScrollSpeedManger speedManger = new ScrollSpeedManger(banner, (LinearLayoutManager) recyclerView.getLayoutManager());
            recyclerView.setLayoutManager(speedManger);


            Field LayoutMangerField = ViewPager2.class.getDeclaredField("mLayoutManager");
            LayoutMangerField.setAccessible(true);
            LayoutMangerField.set(viewPager2, speedManger);

            Field pageTransformerAdapterField = ViewPager2.class.getDeclaredField("mPageTransformerAdapter");
            pageTransformerAdapterField.setAccessible(true);
            Object mPageTransformerAdapter = pageTransformerAdapterField.get(viewPager2);
            if (mPageTransformerAdapter != null) {
                Class<?> aClass = mPageTransformerAdapter.getClass();
                Field layoutManager = aClass.getDeclaredField("mLayoutManager");
                layoutManager.setAccessible(true);
                layoutManager.set(mPageTransformerAdapter, speedManger);
            }
            Field scrollEventAdapterField = ViewPager2.class.getDeclaredField("mScrollEventAdapter");
            scrollEventAdapterField.setAccessible(true);
            Object mScrollEventAdapter = scrollEventAdapterField.get(viewPager2);
            if (mScrollEventAdapter != null) {
                Class<?> aClass = mScrollEventAdapter.getClass();
                Field layoutManager = aClass.getDeclaredField("mLayoutManager");
                layoutManager.setAccessible(true);
                layoutManager.set(mScrollEventAdapter, speedManger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
