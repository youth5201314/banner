package com.test.banner.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public ImageHolder(@NonNull View view) {
        super(view);
        this.imageView = (ImageView) view;
    }
}