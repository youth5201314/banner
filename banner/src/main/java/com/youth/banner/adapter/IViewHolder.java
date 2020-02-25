package com.youth.banner.adapter;

import android.view.ViewGroup;

public interface IViewHolder<T, VH> {

    /**
     * 创建ViewHolder
     *
     * @return XViewHolder
     */
    VH onCreateHolder(ViewGroup parent, int viewType);

    /**
     * 绑定布局数据
     *
     * @param holder   XViewHolder
     * @param data     数据实体
     * @param position 当前位置
     * @param size     总数
     */
    void onBindView(VH holder, T data, int position, int size);

}
