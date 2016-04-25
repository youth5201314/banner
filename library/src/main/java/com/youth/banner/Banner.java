package com.youth.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Banner extends FrameLayout {
    private int count;
    private List<ImageView> imageViews;
    private Context context;
    private ViewPager viewPager;
    private boolean isAutoPlay;
    private int currentItem;
    private int delayTime=2000;
    private LinearLayout indicator;
    private List<ImageView> indicatorImages;
    private Handler handler = new Handler();
    private OnBannerClickListener listener;

    public Banner(Context context) {
        this(context, null);
    }
    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        imageViews = new ArrayList<ImageView>();
        indicatorImages = new ArrayList<ImageView>();
    }

    public void setImages(Object[] images) {
        initView();
        initImage(images);
        showTime();
    }
    private void initImage(Object[] imagesUrl) {
        count = imagesUrl.length;
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8,8);
            params.leftMargin = 4;
            params.rightMargin = 4;
            imageView.setBackgroundResource(R.drawable.white_radius);
            indicator.addView(imageView, params);
            indicatorImages.add(imageView);
        }
        for (int i = 0; i <= count + 1; i++) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.FIT_XY);
            if (i == 0) {
                Glide.with(context).load(imagesUrl[count - 1]).into(iv);
            } else if (i == count + 1) {
                Glide.with(context).load(imagesUrl[0]).into(iv);
            } else {
                Glide.with(context).load(imagesUrl[i - 1]).into(iv);
            }
            imageViews.add(iv);
        }
    }
    private void initView() {
        imageViews.clear();
        View view = LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        indicator = (LinearLayout) view.findViewById(R.id.indicator);
        indicator.removeAllViews();
    }
    
    private void showTime() {
        viewPager.setAdapter(new BannerPagerAdapter());
        viewPager.setFocusable(true);
        viewPager.setCurrentItem(1);
        currentItem = 1;
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        startAutoPlay();
    }

    private void startAutoPlay() {
        isAutoPlay = true;
        handler.postDelayed(task, delayTime);
    }

    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
                if (currentItem == 1) {
                    viewPager.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    viewPager.setCurrentItem(currentItem);
                    handler.postDelayed(task, delayTime);
                }
            } else {
                handler.postDelayed(task, delayTime);
            }
        }
    };

    class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(imageViews.get(position));
            final ImageView view=imageViews.get(position);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.OnBannerClick(v,position);
                    }
                }
            });
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:
                    isAutoPlay = false;
                    break;
                case 2:
                    isAutoPlay = true;
                    break;
                case 0:
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(count, false);
                    } else if (viewPager.getCurrentItem() == count + 1) {
                        viewPager.setCurrentItem(1, false);
                    }
                    currentItem = viewPager.getCurrentItem();
                    isAutoPlay = true;
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < indicatorImages.size(); i++) {
                if (i == arg0 - 1) {
                    indicatorImages.get(i).setImageResource(R.drawable.gray_radius);
                } else {
                    indicatorImages.get(i).setImageResource(R.drawable.white_radius);
                }
            }
        }

    }

    public OnBannerClickListener getOnBannerClickListener() {
        return listener;
    }

    public void setOnBannerClickListener(OnBannerClickListener listener) {
        this.listener = listener;
    }

    public interface OnBannerClickListener {
        void OnBannerClick(View view, int position);
    }
}
