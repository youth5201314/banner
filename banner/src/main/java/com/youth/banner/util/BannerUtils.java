package com.youth.banner.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.youth.banner.Banner;
import com.youth.banner.R;
import com.youth.banner.config.BannerConfig;

public class BannerUtils {

    /**
     * 获取真正的位置
     *
     * @param isIncrease 首尾是否有增加
     * @param position  当前位置
     * @param realCount 真实数量
     * @return
     */
    public static int getRealPosition(boolean isIncrease, int position, int realCount) {
        if (!isIncrease) {
            return position;
        }
        int realPosition;
        if (position == 0) {
            realPosition = realCount - 1;
        } else if (position == realCount + 1) {
            realPosition = 0;
        } else {
            realPosition = position - 1;
        }
        return realPosition;
    }

    /**
     * 将布局文件转成view，这里为了适配viewpager2中高宽必须为match_parent
     *
     * @param parent
     * @param layoutId
     * @return
     */
    public static View getView(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        //这里判断高度和宽带是否都是match_parent
        if (params.height != -1 || params.width != -1) {
            params.height = -1;
            params.width = -1;
            view.setLayoutParams(params);
        }
        return view;
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 设置view圆角
     *
     * @param radius
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setBannerRound(View view,float radius) {
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
            }
        });
        view.setClipToOutline(true);
    }
}
