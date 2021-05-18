package com.test.banner.ui;

import android.os.Bundle;
import android.util.Log;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.test.banner.R;
import com.test.banner.adapter.MultipleTypesAdapter;
import com.test.banner.bean.DataBean;
import com.test.banner.indicator.NumIndicator;
import com.test.banner.viewholder.VideoHolder;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnPageChangeListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仿淘宝商品详情，banner第一个放视频,然后首尾不能自己滑动，加上自定义数字指示器
 */
public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.banner)
    Banner banner;
    StandardGSYVideoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        banner.addBannerLifecycleObserver(this)
                .setAdapter(new MultipleTypesAdapter(this, DataBean.getTestDataVideo()))
                .setIndicator(new NumIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                .addOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        stopVideo(position);
                    }

                    @Override
                    public void onPageSelected(int position) {
                        Log.e("--","position:"+position);
                        stopVideo(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
    }

    private void stopVideo(int position) {
        if (player == null) {
            RecyclerView.ViewHolder viewHolder = banner.getAdapter().getViewHolder();
            if (viewHolder instanceof VideoHolder) {
                VideoHolder holder = (VideoHolder) viewHolder;
                player = holder.player;
                if (position != 0) {
                    player.onVideoPause();
                }
            }
        }else {
            if (position != 0) {
                player.onVideoPause();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null)
            player.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null)
            player.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        //释放所有
        if (player != null)
            player.setVideoAllCallBack(null);
        super.onBackPressed();
    }

}
