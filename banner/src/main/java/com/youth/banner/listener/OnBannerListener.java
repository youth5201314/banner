package com.youth.banner.listener;

public interface OnBannerListener<T> {

    /**
     * 点击事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void OnBannerClick(T data, int position);

    /**
     * banner切换事件（只在banner自动轮播时回调，返回当前展示的页面位置）
     * <P>
     *  1、为解决一些特殊的需求提供的此方法，以便区分手动滑动和自动滑动切换事件
     *  2、如不区分这些，可以调用 {@link OnPageChangeListener}
     * </p>
     * @param position 当前位置
     */
    void onBannerChanged(int position);
}
