package com.test.banner.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.banner.App;
import com.test.banner.R;
import com.youth.banner.LoopAdapter;
import com.youth.banner.LoopLayout;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
 * Created by Wanghy360 on 2018/6/8.
 */
public class CustomItemViewActivity extends AppCompatActivity implements OnBannerListener {
    private LoopLayout customItemBanner;
    private LoopAdapter loopAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_item_view);

        customItemBanner = (LoopLayout) findViewById(R.id.banner);
        customItemBanner.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, App.H / 4));
        loopAdapter = new LoopAdapter() {
            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public View getItemView(Context context, int realPosition) {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_custom_item_view, customItemBanner, false);
                TextView tvPosition = (TextView) view.findViewById(R.id.tv_position);
                ImageView ivImage = (ImageView) view.findViewById(R.id.iv_image);

                tvPosition.setText(getResources().getString(R.string.no_position, realPosition));
                if (realPosition == 0) {
                    ivImage.setImageResource(R.mipmap.a);
                } else if (realPosition == 1) {
                    ivImage.setImageResource(R.mipmap.b);
                } else if (realPosition == 2) {
                    ivImage.setImageResource(R.mipmap.b1);
                } else if (realPosition == 3) {
                    ivImage.setImageResource(R.mipmap.b2);
                } else if (realPosition == 4) {
                    ivImage.setImageResource(R.mipmap.c);
                } else {
                    ivImage.setImageResource(R.mipmap.d);
                }
                return view;
            }

            @Override
            public List<String> getTitles() {
                return null;
            }
        };
        customItemBanner.setAdapter(loopAdapter);
        customItemBanner.setOnBannerListener(this);
        loopAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "position:" + position, Toast.LENGTH_SHORT).show();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        customItemBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        customItemBanner.stopAutoPlay();
    }
}
