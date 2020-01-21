package com.youth.banner.util;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public class BannerUtils {

    /**
     * 获取真正的位置
     *
     * @param position  当前位置
     * @param realCount 真实数量
     * @return
     */
    public static int getRealPosition(int position, int realCount) {
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
}
