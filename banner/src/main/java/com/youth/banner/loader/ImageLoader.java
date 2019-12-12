package com.youth.banner.loader;

import android.content.Context;
import android.widget.ImageView;


public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public ImageView createImageView(Context context, Object path) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }

}
