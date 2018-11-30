package com.test.banner.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.banner.App;
import com.test.banner.R;
import com.test.banner.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

public class CustomBannerActivity extends AppCompatActivity {
    Banner banner1, banner2, banner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);
        banner3 = (Banner) findViewById(R.id.banner3);

        banner1.setImages(App.images);
        banner1.setImageLoader(new GlideImageLoader());
        banner1.start();

        banner2.setImages(App.images);
        banner2.setImageLoader(new GlideImageLoader());
        banner2.start();

        banner3.setImages(App.images);
        banner3.setTitles(App.titles);
        banner3.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner3.setImageLoader(new GlideImageLoader());
        banner3.start();
    }
}
