package com.test.banner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.youth.banner.Banner;


public class MainActivity extends Activity {
    private Banner banner;
    String[] images= new String[] {
            "http://image.zcool.com.cn/56/35/1303967876491.jpg",
            "http://image.zcool.com.cn/59/54/m_1303967870670.jpg",
            "http://image.zcool.com.cn/47/19/1280115949992.jpg",
            "http://image.zcool.com.cn/59/11/m_1303967844788.jpg" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (Banner) findViewById(R.id.banner);
        banner.setImages(images);//可以选择设置图片网址，或者资源文件
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {

            }
        });

    }

}
