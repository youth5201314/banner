package com.test.banner.adapter;

import android.view.ViewGroup;

import com.test.banner.bean.DataBean;
import com.test.banner.R;
import com.test.banner.viewholder.ImageTitleHolder;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局，图片+标题
 */

public class ImageTitleAdapter extends BannerAdapter<DataBean, ImageTitleHolder> {

    public ImageTitleAdapter(List<DataBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(BannerUtils.getView(parent,R.layout.banner_image_title));
    }

    @Override
    public void onBindView(ImageTitleHolder holder, DataBean data, int position, int size) {
        holder.imageView.setImageResource(data.imageRes);
        holder.title.setText(data.title);
    }



}
