package com.youth.banner;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

/**
 * Created by Wanghy360 on 2018/11/30.
 */
public class BannerAdapter extends LoopAdapter {
    private int scaleType;
    private List<?> imagesUrls;
    private List<String> titles;
    private ImageLoaderInterface imageLoader;

    public BannerAdapter(int scaleType) {
        this(scaleType, null, null);
    }

    public BannerAdapter(int scaleType, List<?> imagesUrls, List<String> titles) {
        this.scaleType = scaleType;
        this.imagesUrls = imagesUrls;
        this.titles = titles;
    }

    public void setImagesUrls(List<?> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public void setImageLoader(ImageLoaderInterface imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return imagesUrls == null ? 0 : imagesUrls.size();
    }

    @Override
    public View getItemView(Context context, int realPosition) {
        ImageView imageView = null;
        if (imageLoader != null) {
            imageView = imageLoader.createImageView(context);
        }
        if (imageView == null) {
            imageView = new ImageView(context);
        }
        setScaleType(imageView);
        if (imageLoader != null)
            imageLoader.displayImage(context, imagesUrls.get(realPosition), imageView);
        else
            Log.e("BannerAdapter", "Please set images loader.");
        return imageView;
    }

    @Override
    public List<String> getTitles() {
        return titles;
    }

    private void setScaleType(View imageView) {
        if (imageView instanceof ImageView) {
            ImageView view = ((ImageView) imageView);
            switch (scaleType) {
                case 0:
                    view.setScaleType(ImageView.ScaleType.CENTER);
                    break;
                case 1:
                    view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    break;
                case 2:
                    view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
                case 3:
                    view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case 4:
                    view.setScaleType(ImageView.ScaleType.FIT_END);
                    break;
                case 5:
                    view.setScaleType(ImageView.ScaleType.FIT_START);
                    break;
                case 6:
                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                case 7:
                    view.setScaleType(ImageView.ScaleType.MATRIX);
                    break;
            }
        }
    }
}
