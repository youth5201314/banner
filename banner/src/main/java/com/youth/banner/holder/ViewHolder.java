package com.youth.banner.holder;

import android.content.Context;
import android.view.View;


public interface ViewHolder<T> {

    /**
     * 创建item view
     * @return ViewPager item 布局
     */
    View createView(Context context);

    /**
     * 绑定布局数据
     * @param item     ViewPager item
     * @param data     数据实体
     * @param position 当前位置
     * @param size     item size
     */
    void onBindView(View item, T data, int position, int size);


}
