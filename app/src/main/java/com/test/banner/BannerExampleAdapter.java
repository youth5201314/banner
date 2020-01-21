package com.test.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局，随你发挥
 */
public class BannerExampleAdapter extends BannerAdapter<DataBean, BannerExampleAdapter.PagerViewHolder> {

    public BannerExampleAdapter(List<DataBean> mDatas) {
        //设置数据，也可以调用banner提供的方法
        super(mDatas);
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public PagerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        //注意布局文件，item布局文件要设置为match_parent，这个是viewpager2强制要求的
        //或者调用BannerUtils.getView(parent,R.layout.banner);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false);
        return new PagerViewHolder(view);
    }

    //绑定数据
    @Override
    public void onBindView(PagerViewHolder holder, DataBean data, int position, int size) {
        holder.imageView.setImageResource(data.imageRes);
        holder.title.setText(data.title);
        //可以在布局文件中自己实现指示器，亦可以使用banner提供的方法自定义指示器，目前样式较少，后面补充
        holder.numIndicator.setText((position + 1) + "/" + size);
    }


    class PagerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView numIndicator;

        public PagerViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            title = view.findViewById(R.id.bannerTitle);
            numIndicator = view.findViewById(R.id.numIndicator);
        }
    }

}
