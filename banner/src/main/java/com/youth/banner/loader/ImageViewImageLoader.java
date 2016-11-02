package com.youth.banner.loader;

import android.content.Context;
import android.widget.ImageView;


public abstract class ImageViewImageLoader implements ImageLoader<ImageView> {

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }

}
