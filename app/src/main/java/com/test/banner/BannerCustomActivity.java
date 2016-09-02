package com.test.banner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

public class BannerCustomActivity extends AppCompatActivity {
    private Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_custom);
        getSupportActionBar().setTitle(getIntent().getStringExtra("des"));
        String[] images2= getResources().getStringArray(R.array.url2);
        banner = (Banner) findViewById(R.id.banner3);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(images2);
    }

}
