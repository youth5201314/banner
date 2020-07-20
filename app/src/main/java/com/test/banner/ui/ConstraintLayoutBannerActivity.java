package com.test.banner.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.test.banner.R;
import com.test.banner.adapter.ImageAdapter;
import com.test.banner.adapter.ImageNetAdapter;
import com.test.banner.adapter.ImageTitleAdapter;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

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
        banner.setAdapter(new ImageTitleAdapter(DataBean.getTestData()));
        banner.setIndicator(new CircleIndicator(this));
        banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
        banner.setIndicatorMargins(new IndicatorConfig.Margins(0, 0,
                BannerConfig.INDICATOR_MARGIN, (int) BannerUtils.dp2px(12)));
        banner.addBannerLifecycleObserver(this);
    }

}
