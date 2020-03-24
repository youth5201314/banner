package com.test.banner.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.test.banner.R;
import com.test.banner.adapter.ImageAdapter;
import com.test.banner.adapter.NetBannerAdapter;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

public class RecyclerViewBannerActivity extends AppCompatActivity {
    Banner banner;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_banner);
        recyclerView=findViewById(R.id.net_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        banner = (Banner) LayoutInflater.from(this).inflate(R.layout.banner,null);
        banner.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, (int) BannerUtils.dp2px(200)));
        banner.setAdapter(new ImageAdapter(DataBean.getTestData2()));
        banner.setIndicator(new CircleIndicator(this));

        NetBannerAdapter adapter = new NetBannerAdapter();
        adapter.addHeaderView(banner);
        recyclerView.setAdapter(adapter);
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
