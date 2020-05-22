package com.youth.banner.util;

import android.util.Log;

import com.youth.banner.BuildConfig;

public class LogUtils {
    public static final String TAG = "banner_log";

    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void v( String msg) {
        if (DEBUG) {
            Log.v(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }
}
