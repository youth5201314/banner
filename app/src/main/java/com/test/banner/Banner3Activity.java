package com.test.banner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

public class Banner3Activity extends AppCompatActivity {
    private Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner3);
        getSupportActionBar().setTitle(getIntent().getStringExtra("des"));
        String[] images2= getResources().getStringArray(R.array.url2);
        banner = (Banner) findViewById(R.id.banner3);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(images2);
    }

}
