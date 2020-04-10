package com.test.banner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.test.banner.R;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.util.BannerUtils;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==R.layout.item) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        }else{
            return new MyBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).cardView.setBackgroundColor(Color.parseColor(DataBean.getRandColor()));
        }else if (holder instanceof MyBannerViewHolder){
            Banner banner=((MyBannerViewHolder) holder).banner;
            banner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));
            banner.setBannerRound(BannerUtils.dp2px(5));
            banner.setIndicator(new RoundLinesIndicator(context));
            banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(15));
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return R.layout.item;
        }else{
            return R.layout.banner;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    //banner 内部已实现
//    @Override
//    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
//        super.onViewDetachedFromWindow(holder);
//        Log.e("banner_log", "onViewDetachedFromWindow:" + holder.getAdapterPosition());
//        //定位你的位置
//        if (holder.getAdapterPosition()%2!=0) {
//            if (holder instanceof MyBannerViewHolder) {
//                ((MyBannerViewHolder) holder).banner.stop();
//            }
//        }
//    }
//
//    @Override
//    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        Log.e("banner_log", "onViewAttachedToWindow:" + holder.getAdapterPosition());
//        if (holder.getAdapterPosition()%2!=0) {
//            if (holder instanceof MyBannerViewHolder) {
//                ((MyBannerViewHolder) holder).banner.start();
//            }
//        }
//    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    class MyBannerViewHolder extends RecyclerView.ViewHolder {
        public Banner banner;

        public MyBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
}