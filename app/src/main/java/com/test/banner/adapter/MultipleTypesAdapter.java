package com.test.banner.adapter;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.test.banner.DataBean;
import com.test.banner.R;
import com.test.banner.viewholder.ImageHolder;
import com.test.banner.viewholder.ImageTitleHolder;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局,多个不同UI切换
 */
public class MultipleTypesAdapter extends BannerAdapter<DataBean, RecyclerView.ViewHolder> {

    public MultipleTypesAdapter(List<DataBean> mDatas) {
        super(mDatas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
            case 2:
                //不同的holder
                return new ImageTitleHolder(BannerUtils.getView(parent, R.layout.banner_image_title));
            case 3:
                //相同的holder，切换不同的ui
                return new ImageTitleHolder(BannerUtils.getView(parent, R.layout.banner_image_title2));
            default:
                return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getData(position).viewType;
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder, DataBean data, int position, int size) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case 1:
                ImageHolder imageHolder = (ImageHolder) holder;
                imageHolder.imageView.setImageResource(data.imageRes);
                break;
            case 2:
            case 3:
                ImageTitleHolder titleHolder = (ImageTitleHolder) holder;
                titleHolder.imageView.setImageResource(data.imageRes);
                titleHolder.title.setText(data.title);
                break;
        }
    }


}
