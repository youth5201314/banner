package com.test.banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] des = {"默认模式", "显示圆形指示器", "显示数字指示器",
            "显示数字指示器和标题","显示圆形指示器和标题（垂直显示）", "显示圆形指示器和标题（水平显示）",
            "设置指示器位置", "自定义指示器样式","高级api自定义调用"};
    Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //最简单的用法，获取控件或者new一个控件，再把设置进去就行了
        String[] images= getResources().getStringArray(R.array.url);
        banner = (Banner) findViewById(R.id.banner);
        banner.setDelayTime(1000);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(images);

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new MainAdapter(this, des));
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == des.length - 1) {
            startActivity(new Intent(this, BannerSeniorActivity.class)
                    .putExtra("des", des[position]));
        } else if (position == des.length - 2) {
            startActivity(new Intent(this, BannerCustomActivity.class)
                    .putExtra("des", des[position]));
        } else {
            startActivity(new Intent(this, BannerActivity.class)
                    .putExtra("position", position)
                    .putExtra("des", des[position]));
        }
    }

    //如果你需要考虑更好的体验，可以这么操作(可避免在切换页面时白屏问题)
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("--","onStart");
        banner.isAutoPlay(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("--","onStop");
        banner.isAutoPlay(false);
    }
}
