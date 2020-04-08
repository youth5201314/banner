package com.youth.banner.config;

import com.youth.banner.util.BannerUtils;

public class BannerConfig {
    public static final boolean IS_AUTO_LOOP = true;
    public static final boolean IS_INFINITE_LOOP = true;
    public static final int LOOP_TIME = 3000;
    public static final int SCROLL_TIME = 600;
    public static final int INDICATOR_NORMAL_COLOR = 0x88ffffff;
    public static final int INDICATOR_SELECTED_COLOR = 0x88000000;
    public static final int INDICATOR_NORMAL_WIDTH = (int) BannerUtils.dp2px(5);
    public static final int INDICATOR_SELECTED_WIDTH = (int) BannerUtils.dp2px(7);
    public static final int INDICATOR_SPACE = (int) BannerUtils.dp2px(5);
    public static final int INDICATOR_MARGIN = (int) BannerUtils.dp2px(5);

    public static final int INDICATOR_HEIGHT = (int) BannerUtils.dp2px(3);
    public static final int INDICATOR_RADIUS = (int) BannerUtils.dp2px(3);

}
