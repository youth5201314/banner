package com.test.banner.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.test.banner.R;
import com.test.banner.adapter.ImageAdapter;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TVActivity extends AppCompatActivity {

    private static final String TAG = "banner_log";
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_v);
        ButterKnife.bind(this);
        banner.setAdapter(new ImageAdapter(DataBean.getTestData()));
        banner.setIndicator(new CircleIndicator(this));
        banner.isAutoLoop(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int count = banner.getItemCount();
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                Log.d(TAG, "向左");
                int prev = (banner.getCurrentItem() - 1) % count;
                if (prev == 0) {
                    prev = banner.getRealCount();
                } else if (prev == count - 1) {
                    prev = 1;
                }
                banner.setCurrentItem(prev, false);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                Log.d(TAG, "向右");
                int next = (banner.getCurrentItem() + 1) % count;
                if (next == 0) {
                    next = banner.getRealCount();
                } else if (next == count - 1) {
                    next = 1;
                }
                banner.setCurrentItem(next, false);
                break;
        }
        //如果没有设置指示器，就不用执行下面两行
        int real = BannerUtils.getRealPosition(banner.isInfiniteLoop(), banner.getCurrentItem(), banner.getRealCount());
        banner.getIndicator().onPageSelected(real);
        return super.onKeyDown(keyCode, event);
    }

}
