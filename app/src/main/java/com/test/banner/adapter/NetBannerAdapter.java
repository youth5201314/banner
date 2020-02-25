package com.test.banner.adapter;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.test.banner.R;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;

public class NetBannerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public NetBannerAdapter() {
        super(R.layout.item_header, DataBean.getColors(10));
    }

    @Override
    protected void convert(BaseViewHolder helper, @Nullable String item) {
        CardView cardView = helper.findView(R.id.card_view);
        cardView.setCardBackgroundColor(Color.parseColor(item));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.e("banner_log", "onViewDetachedFromWindow:" + holder.getAdapterPosition());
        //定位你的header位置
        if (holder.getAdapterPosition()==0) {
            if (getHeaderLayoutCount() > 0) {
                //这里是获取你banner放的位置，这个根据你自己实际位置来获取，我这里header只有一个所以这么获取
                Banner banner = (Banner) getHeaderLayout().getChildAt(0);
                banner.stop();
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.e("banner_log", "onViewAttachedToWindow:" + holder.getAdapterPosition());
        if (holder.getAdapterPosition()==0) {
            if (getHeaderLayoutCount() > 0) {
                Banner banner = (Banner) getHeaderLayout().getChildAt(0);
                banner.start();
            }
        }
    }
}