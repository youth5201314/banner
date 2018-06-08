package com.test.banner.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.test.banner.App;
import com.test.banner.R;
import com.test.banner.ui.CustomItemBanner;
import com.youth.banner.LoopLayout;
import com.youth.banner.listener.OnBannerListener;

/**
 * Created by Wanghy360 on 2018/6/8.
 */
public class CustomItemViewActivity extends AppCompatActivity implements OnBannerListener {
    private CustomItemBanner customItemBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_item_view);

        customItemBanner = (CustomItemBanner) findViewById(R.id.banner);
        customItemBanner.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, App.H / 4));
        //简单使用
        new LoopLayout.Builder(customItemBanner)
                .setItemCount(6)
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this,"position:"+position,Toast.LENGTH_SHORT).show();
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
