package com.test.banner;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.banner.common.BaseRecyclerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, OnBannerClickListener {
    static final int REFRESH_COMPLETE = 0X1112;
    SwipeRefreshLayout mSwipeLayout;
    RecyclerView recyclerView;
    Banner banner;
    String[] images, titles;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    images = getResources().getStringArray(R.array.url4);
                    List list = Arrays.asList(images);
                    List arrayList = new ArrayList(list);
                    banner.update(arrayList);
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images = getResources().getStringArray(R.array.url);
        titles = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(images);
        List arrayList = new ArrayList(list);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeLayout.setOnRefreshListener(this);

        recyclerView= (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<>(new SampleAdapter());

        /**
         * 将banner添加到recyclerView头部
         */
        View header= LayoutInflater.from(this).inflate(R.layout.header,null);

        banner = (Banner) header.findViewById(R.id.banner);
        //如果你不需要用xml的属性，那么也可以直接创建对象来实现
//        banner=new Banner(this);

        //设置轮播图宽度和高度，建议最好按照图片的比例设置，效果更好
        banner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getScreenH(this)/3));
        adapter.addHeader(banner);
        recyclerView.setAdapter(adapter);

        //简单使用
//        banner.setImages(arrayList).setImageLoader(new FrescoImageLoader()).start();

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(arrayList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ZoomOut);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置点击事件
        banner.setOnBannerClickListener(this);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    @Override
    public void OnBannerClick(int position, Object data) {
        Toast.makeText(getApplicationContext(), "点击：" + position, Toast.LENGTH_SHORT).show();
        Log.e("--",position+"");
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }
    public int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


}
