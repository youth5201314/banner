package com.spring.usekotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var imageUrls = listOf(
        "https://img.zcool.cn/community/017ed36019103411013e3991abd875.jpg@1280w_1l_2o_100sh.jpg",
        "https://img.zcool.cn/community/01f5076019103711013f792886cff4.jpg@1280w_1l_2o_100sh.jpg",
        "https://img.zcool.cn/community/01f5076019103711013f792886cff4.jpg@1280w_1l_2o_100sh.jpg"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val adapter = BannerImageAdapter(imageUrls)
//        banner?.let {
//            it.addBannerLifecycleObserver(this)
//            it.setIndicator(CircleIndicator(this))
//            it.setBannerRound(20f)
//            it.adapter = adapter
//        }


        val mAdapterKt = BannerImageAdapter(mutableListOf())
        bannerKt.apply {
            addBannerLifecycleObserver(this@MainActivity)
            indicator = CircleIndicator(this@MainActivity)
            setBannerRound(20f)
            setAdapterKt(mAdapterKt)
        }
        mAdapterKt.setDatas(imageUrls)

    }


}
