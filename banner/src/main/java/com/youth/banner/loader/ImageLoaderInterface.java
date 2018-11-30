package com.youth.banner.loader;

import android.content.Context;
import android.widget.ImageView;

import java.io.Serializable;


public interface ImageLoaderInterface extends Serializable {

    void displayImage(Context context, Object path, ImageView imageView);

    ImageView createImageView(Context context);
}
