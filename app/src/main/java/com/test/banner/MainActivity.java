package com.test.banner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.test.banner.adapter.ImageAdapter;
import com.test.banner.adapter.ImageNetAdapter;
import com.test.banner.adapter.ImageTitleAdapter;
import com.test.banner.adapter.ImageTitleNumAdapter;
import com.test.banner.adapter.MultipleTypesAdapter;
import com.test.banner.adapter.TopLineAdapter;
import com.test.banner.bean.DataBean;
import com.test.banner.ui.ConstraintLayoutBannerActivity;
import com.test.banner.ui.RecyclerViewBannerActivity;
import com.youth.banner.Banner;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.ZoomOutPageTransformer;
import com.youth.banner.util.BannerUtils;

public class MainActivity extends AppCompatActivity implements OnBannerListener, OnPageChangeListener {
    private static final String TAG = "banner_log";
    private Banner banner,banner2;
    private SwipeRefreshLayout refresh;
    private RelativeLayout topLine;
    private CircleIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh = findViewById(R.id.swipeRefresh);
        banner = findViewById(R.id.banner);
        banner2 = findViewById(R.id.banner2);
        topLine = findViewById(R.id.topLine);
        indicator = findViewById(R.id.indicator);

        //设置适配器
        banner.setAdapter(new ImageAdapter(DataBean.getTestData()));
        //设置指示器
        banner.setIndicator(new CircleIndicator(this));
        //设置点击事件
        banner.setOnBannerListener(this);
        //添加切换监听
        banner.addOnPageChangeListener(this);
        //圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            banner.setBannerRound(BannerUtils.dp2px(5));
        }
        //添加画廊效果，可以参考我给的参数自己调试(不要和其他PageTransformer同时使用)
//        banner.setBannerGalleryEffect(25,40,0.14f);
        banner.setDelayTime(2000);
        banner.setPageTransformer(new ZoomOutPageTransformer());
        banner.setPageTransformer(new DepthPageTransformer());

        //实现1号店和淘宝头条类似的效果
        banner2.setAdapter(new TopLineAdapter(DataBean.getTestData2()));
        banner2.setOrientation(Banner.VERTICAL);
        banner2.setPageTransformer(new ZoomOutPageTransformer());

        //和下拉刷新配套使用
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟网络请求需要3秒，请求完成，设置setRefreshing 为false
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                        //给banner重新设置数据
                        banner.setDatas(DataBean.getTestData2());
                    }
                }, 3000);
            }
        });

    }

    /**
     * 点击事件、切换事件方法
     */
    @Override
    public void OnBannerClick(Object data, int position) {
        Toast.makeText(this, "点击" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.e(TAG, "onPageSelected:----" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    /**
     * 如果你需要考虑更好的体验，可以这么操作
     */
    @Override
    protected void onStart() {
        super.onStart();
        banner.start();
        banner2.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stop();
        banner2.stop();
    }

    public void changeStyle(View view) {
        indicator.setVisibility(View.GONE);
        switch (view.getId()){
            case R.id.style_image:
                refresh.setEnabled(true);
                banner.setAdapter(new ImageAdapter(DataBean.getTestData()));
                banner.setIndicator(new CircleIndicator(this));
                banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
                break;
            case R.id.style_image_title:
                refresh.setEnabled(true);
                banner.setAdapter(new ImageTitleAdapter(DataBean.getTestData()));
                banner.setIndicator(new CircleIndicator(this));
                banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
                banner.setIndicatorMargins(new IndicatorConfig.Margins(0,0,
                        BannerConfig.INDICATOR_MARGIN, (int) BannerUtils.dp2px(12)));
                break;
            case R.id.style_image_title_num:
                refresh.setEnabled(true);
                banner.setAdapter(new ImageTitleNumAdapter(DataBean.getTestData()));
                banner.removeIndicator();
                break;
            case R.id.style_multiple:
                refresh.setEnabled(true);
                indicator.setVisibility(View.VISIBLE);
                banner.setAdapter(new MultipleTypesAdapter(DataBean.getTestData()));
                banner.setCustomIndicator(indicator);
                break;
            case R.id.style_net_image:
                refresh.setEnabled(false);
                banner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));
                banner.setIndicator(new CircleIndicator(this));
                break;
            case R.id.change_indicator:
                indicator.setVisibility(View.VISIBLE);
                //推荐你在布局文件中定位好指示器的位置，如果想直接new，可以使用setIndicator方法
                banner.setCustomIndicator(indicator);
                banner.setIndicatorSpace(BannerUtils.dp2px(10));
                break;
            case R.id.rv_banner:
                startActivity(new Intent(this, RecyclerViewBannerActivity.class));
                break;
            case R.id.cl_banner:
                startActivity(new Intent(this, ConstraintLayoutBannerActivity.class));
                break;
        }
    }
}
