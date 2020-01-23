package com.youth.banner.config;

import com.youth.banner.util.BannerUtils;

public class BannerConfig {
    public static final boolean IS_AUTO_LOOP = true;
    public static final int DELAY_TIME = 3000;
    public static final int DURATION = 800;
    public static final int INDICATOR_NORMAL_COLOR = 0x88ffffff;
    public static final int INDICATOR_SELECTED_COLOR = 0x88000000;
    public static final float INDICATOR_NORMAL_WIDTH = BannerUtils.dp2px(6);
    public static final float INDICATOR_SELECTED_WIDTH = BannerUtils.dp2px(8);
    public static final float INDICATOR_SPACE = BannerUtils.dp2px(6);
    public static final int INDICATOR_MARGIN = (int) BannerUtils.dp2px(5);

}
