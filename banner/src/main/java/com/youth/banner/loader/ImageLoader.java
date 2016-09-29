package com.youth.banner.loader;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import java.io.Serializable;


public interface ImageLoader extends Serializable {

    void displayImage(Context context, Object path, ImageView imageView);

}
