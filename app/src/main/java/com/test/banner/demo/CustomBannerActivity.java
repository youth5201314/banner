package com.test.banner.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.banner.App;
import com.test.banner.R;
import com.test.banner.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.config.Constants;
import com.youth.banner.config.IndicatorConfig;

public class CustomBannerActivity extends AppCompatActivity {
    Banner banner1, banner2, banner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);
        banner3 = (Banner) findViewById(R.id.banner3);

        banner1.setImages(App.images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner2.setImages(App.images)
                .setImageLoader(new GlideImageLoader())
                .start();


        IndicatorConfig config = new IndicatorConfig.Builder()
                .style(Constants.CIRCLE_INDICATOR_TITLE_INSIDE)
                .build();
        banner3.setIndicatorConfig(config);

        banner3.setImagesWithTitles(App.images, App.titles)
                .setImageLoader(new GlideImageLoader())
                .start();
    }
}
