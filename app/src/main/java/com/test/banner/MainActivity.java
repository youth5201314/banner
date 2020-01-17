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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnBannerListener {
    private static final String TAG = "banner_log";
    Banner banner;
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
        banner.setData(data)
                .setViewHolderCreator(() -> new BannerViewHolder())
                .setOnBannerListener(this)
                .build();

    }

    @Override
    public void OnBannerClick(Object data, int position) {
        Toast.makeText(this, "点击" + position, Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onStart() {
        super.onStart();
        banner.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stop();
    }
}
