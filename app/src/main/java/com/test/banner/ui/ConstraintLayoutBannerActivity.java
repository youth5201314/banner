package com.test.banner.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.test.banner.R;
import com.test.banner.adapter.ImageAdapter;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

public class ConstraintLayoutBannerActivity extends AppCompatActivity {
    Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_banner);
        banner = findViewById(R.id.banner);
        banner.setAdapter(new ImageAdapter(DataBean.getTestData()));
        banner.setIndicator(new CircleIndicator(this));
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
