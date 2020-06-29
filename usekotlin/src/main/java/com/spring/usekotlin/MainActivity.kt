package com.spring.usekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.RotateYTransformer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var imageUrls = listOf(
            "https://img.zcool.cn/community/011ad05e27a173a801216518a5c505.jpg",
            "https://img.zcool.cn/community/0148fc5e27a173a8012165184aad81.jpg",
            "https://img.zcool.cn/community/013c7d5e27a174a80121651816e521.jpg",
            "https://img.zcool.cn/community/01b8ac5e27a173a80120a895be4d85.jpg",
            "https://img.zcool.cn/community/01a85d5e27a174a80120a895111b2c.jpg",
            "https://img.zcool.cn/community/01085d5e27a174a80120a8958791c4.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter = BannerImageAdapter(imageUrls)
        banner?.let {
            it.addBannerLifecycleObserver(this)
            it.setIndicator(CircleIndicator(this))
            it.setBannerRound(20f)
            it.adapter = adapter
        }


    }


}
