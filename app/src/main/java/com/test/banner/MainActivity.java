package com.test.banner;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.holder.ViewHolder;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.vp2.Banner2;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnBannerListener {
    private static final String TAG = "banner_log";
    Banner banner;
    Banner2 banner2;
    List<Integer> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = findViewById(R.id.banner);
        data = new ArrayList<>();
        data.add(R.drawable.b1);
        data.add(R.drawable.b2);
        data.add(R.drawable.b3);
//        banner.setData(data)
//                .setViewHolderCreator(() -> new BannerViewHolder())
//                .setOnBannerListener(this)
//                .build();

        banner2 = findViewById(R.id.banner2);
        banner2.setAdapter(new BannerAdapter(data));
//        banner2.start();
        banner2.setOnBannerListener(this);
        banner2.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "onPageScrolled:" + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected:----" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageScrollStateChanged:" + state);
            }
        });
    }

    @Override
    public void OnBannerClick(Object data, int position) {
        Toast.makeText(this, "点击" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBannerChanged(int position) {
//        Log.e(TAG,"onBannerChanged:"+position);
    }


    class BannerViewHolder implements ViewHolder<Integer> {
        ImageView imageView;
        TextView title;
        TextView numIndicator;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner, null);
            imageView = view.findViewById(R.id.image);
            title = view.findViewById(R.id.bannerTitle);
            numIndicator = view.findViewById(R.id.numIndicator);
            return view;
        }

        @Override
        public void onBindView(View item, Integer data, int position, int size) {
            imageView.setImageResource(data);
            title.setText(data + "");
            numIndicator.setText((position+1)+"/"+size);
        }
    }

    class BannerAdapter extends com.youth.banner.adapter.BannerAdapter<Integer, PagerViewHolder> {

        public BannerAdapter(List<Integer> mDatas) {
            super(mDatas);
        }

        @Override
        public PagerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false);
            return new PagerViewHolder(view);
        }

        @Override
        public void onBindView(PagerViewHolder holder, Integer data, int position, int size) {
            holder.imageView.setImageResource(data);
            holder.title.setText(data + "");
            holder.numIndicator.setText((position+1)+"/"+size);
        }
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

    @Override
    protected void onStart() {
        super.onStart();
        banner.start();
        banner2.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stop();
        banner2.stop();
    }
}
