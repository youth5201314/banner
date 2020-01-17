package com.youth.banner.vp2;

public class Utils {

    /**
     * 获取真正的位置
     * @param position 当前位置
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
}
