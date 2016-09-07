package com.test.banner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

public class BannerSeniorActivity extends AppCompatActivity implements View.OnClickListener {
    private Banner banner;
    private Button refresh,stop,start;
    boolean flag=true;
    String[] images,images2,titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_senior);
        getSupportActionBar().setTitle(getIntent().getStringExtra("des"));
        images= getResources().getStringArray(R.array.url);
        images2= getResources().getStringArray(R.array.url2);
        titles= getResources().getStringArray(R.array.title);
        refresh= (Button) findViewById(R.id.refresh);
        start= (Button) findViewById(R.id.start);
        stop= (Button) findViewById(R.id.stop);
        banner = (Banner) findViewById(R.id.banner2);
        refresh.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        
        
        //显示圆形指示器和标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置标题列表
        banner.setBannerTitle(titles);
        //设置轮播间隔时间 在布局文件中设置了5秒
//        banner.setDelayTime(3000);
        //设置动画
        banner.setBannerAnimation(Transformer.CubeOut);
        /**
         * 可以选择设置图片网址或者资源文件，默认用Glide加载
         * 如果你想设置默认图片就在xml里设置default_image
         * banner.setImages(images);
         */
        //如果你想用自己项目的图片加载,那么----->自定义图片加载框架
        banner.setImages(images, new OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                /**
                 * 这里你可以根据框架灵活设置
                 */
                Glide.with(getApplicationContext())
                        .load(url)
                        .into(view);
            }
        });
        //设置点击事件
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getApplicationContext(),"你点击了："+position,Toast.LENGTH_LONG).show();
            }
        });
        //开放了viewpager的滑动事件
        //banner.setOnPageChangeListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.refresh:
                if(flag) {
                    flag=false;
                    banner.setImages(images2);
                }else{
                    flag=true;
                    banner.setImages(images);
                }
                break;
            case R.id.start:
                banner.isAutoPlay(true);
                break;
            case R.id.stop:
                banner.isAutoPlay(false);
                break;
        }
    }
    
    //如果你需要考虑更好的体验，可以这么操作
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
