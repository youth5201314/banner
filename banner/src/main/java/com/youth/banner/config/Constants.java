package com.youth.banner.config;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {
    /**
     * indicator style
     */
    public static final int NOT_INDICATOR = 0;
    public static final int CIRCLE_INDICATOR = 1;
    public static final int NUM_INDICATOR = 2;
    public static final int NUM_INDICATOR_TITLE = 3;
    public static final int CIRCLE_INDICATOR_TITLE = 4;
    public static final int CIRCLE_INDICATOR_TITLE_INSIDE = 5;

    @IntDef({NOT_INDICATOR, CIRCLE_INDICATOR, NUM_INDICATOR, NUM_INDICATOR_TITLE
            , CIRCLE_INDICATOR_TITLE, CIRCLE_INDICATOR_TITLE_INSIDE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IndicatorStyle {
    }

    /**
     * indicator gravity
     */
    public static final int LEFT = 5;
    public static final int CENTER = 6;
    public static final int RIGHT = 7;


    @IntDef({LEFT, CENTER, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IndicatorGravity {
    }

    /**
     * banner
     */
    public static final int INDICATOR_MARGIN = 5;
    public static final int DELAY_TIME = 2000;
    public static final int SCROLL_TIME = 800;
    public static final boolean IS_AUTO_PLAY = true;
    public static final boolean IS_SCROLL = true;

    /**
     * title style
     */
    public static final int TITLE_BACKGROUND = -1;
    public static final int TITLE_HEIGHT = -1;
    public static final int TITLE_TEXT_COLOR = -1;
    public static final int TITLE_TEXT_SIZE = -1;

}
