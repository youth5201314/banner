package com.test.banner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.test.banner.transformer.DepthPageTransformer;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.Banner;
import com.youth.banner.util.BannerUtils;

public class MainActivity extends AppCompatActivity implements OnBannerListener, OnPageChangeListener {
    private static final String TAG = "banner_log";
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = findViewById(R.id.banner);
        //一行代码调用实现，默认配置
        banner.setAdapter(new BannerExampleAdapter(DataBean.getTestData()));
        banner.setIndicator(new CircleIndicator(this));
        banner.setIndicatorSelectedColorRes(R.color.main_color);
        banner.setIndicatorNormalColorRes(R.color.textColor);
        banner.setIndicatorGravity(IndicatorConfig.Direction.LEFT);
        banner.setIndicatorSpace(BannerUtils.dp2px(20));
//        banner.setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(20)));
        banner.setIndicatorWidth(10,20);
//        banner.addItemDecoration(new MarginItemDecoration((int) BannerUtils.dp2px(50)));
        banner.setPageTransformer(new DepthPageTransformer());
        banner.setOnBannerListener(this);
        banner.addOnPageChangeListener(this);


//        banner.setAdapter(new BannerExampleAdapter(DataBean.getTestData()))
//                .setOrientation(Banner.VERTICAL)
//                .setIndicator(new CircleIndicator(this))
//                .setUserInputEnabled(false);
    }

    /**
     * 点击事件、切换事件方法
     */
    @Override
    public void OnBannerClick(Object data, int position) {
        Toast.makeText(this, "点击" + position, Toast.LENGTH_SHORT).show();
        banner.setDatas(DataBean.getTestData2());
    }

    @Override
    public void onBannerChanged(int position) {
        Log.e(TAG,"onBannerChanged:"+position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e(TAG, "onPageScrolled:" + position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.e(TAG, "onPageSelected:----" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e(TAG, "onPageScrollStateChanged:" + state);
    }


    /**
     * 如果你需要考虑更好的体验，可以这么操作
     */
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
