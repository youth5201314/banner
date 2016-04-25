package com.test.banner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.youth.banner.Banner;


public class MainActivity extends Activity {
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (Banner) findViewById(R.id.banner);
        banner.setImages(new String[] {
         "http://img04.muzhiwan.com/2015/06/16/upload_557fd293326f5.jpg",
         "http://img03.muzhiwan.com/2015/06/05/upload_557165f4850cf.png",
         "http://img02.muzhiwan.com/2015/06/11/upload_557903dc0f165.jpg",
         "http://img04.muzhiwan.com/2015/06/05/upload_5571659957d90.png",
         "http://img03.muzhiwan.com/2015/06/16/upload_557fd2a8da7a3.jpg" });
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {

            }
        });

    }

}
