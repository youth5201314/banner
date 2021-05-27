package com.spring.usekotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.indicator.RoundLinesIndicator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var imageUrls = listOf(
            "https://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png",
            "https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg",
            "https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg",
            "https://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //使用默认的图片适配器
        var banner = (bannerLayout1 as Banner<String, BannerImageAdapter<String>>)
        banner.apply {
            addBannerLifecycleObserver(this@MainActivity)
            setIndicator(CircleIndicator(this@MainActivity))
            setAdapter(object : BannerImageAdapter<String>(imageUrls) {
                override fun onBindView(holder: BannerImageHolder, data: String, position: Int, size: Int) {
                    Glide.with(this@MainActivity)
                            .load(data)
                            .into(holder.imageView)
                }
            })
        }


        //使用自定义适配器，更多api方法自己尝试
        var banner2 = (bannerLayout2 as Banner<String, ImageAdapter>)
        banner2.apply {
            addBannerLifecycleObserver(this@MainActivity)
            setBannerRound(20f)
            setIndicator(RoundLinesIndicator(this@MainActivity))
            setAdapter(ImageAdapter(imageUrls))
        }
    }


}
