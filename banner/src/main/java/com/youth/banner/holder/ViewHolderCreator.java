package com.youth.banner.holder;

public interface ViewHolderCreator<VH extends ViewHolder> {

    /**
     * 创建ViewHolder
     */
    VH createViewHolder();
}
