package com.test.banner.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.test.banner.R;
import com.test.banner.adapter.ImageAdapter;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConstraintLayoutBannerActivity extends AppCompatActivity {
    private static final String TAG = "banner_log";
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_banner);
        ButterKnife.bind(this);
        banner.setAdapter(new ImageAdapter(DataBean.getTestData()));
        banner.setIndicator(new CircleIndicator(this));
        banner.isAutoLoop(false);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banner.destroy();
    }
}
