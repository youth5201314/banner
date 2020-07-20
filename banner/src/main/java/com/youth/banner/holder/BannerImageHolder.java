package com.youth.banner.holder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public
class BannerImageHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public BannerImageHolder(@NonNull View view) {
        super(view);
        this.imageView = (ImageView) view;
    }
}
