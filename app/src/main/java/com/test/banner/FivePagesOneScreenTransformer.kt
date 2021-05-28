package com.test.banner

import android.view.View
import androidx.viewpager2.widget.ViewPager2

/**
 * 一拼五页
 *
 * @property scale Float
 * @property translationX Float
 * @constructor
 */
class FivePagesOneScreenTransformer(
        private val scale: Float = DEFAULT_MIN_SCALE,
        private val translationX: Float = -120f) : ViewPager2.PageTransformer {

    companion object {
        /**
         * 默认的中心点
         */
        private const val DEFAULT_CENTER = 0.5f

        /**
         * 默认的缩放比例
         */
        private const val DEFAULT_MIN_SCALE = 0.85f
    }

    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val pageHeight = page.height
        page.pivotX = (pageWidth / 2).toFloat()
        page.pivotY = (pageHeight / 2).toFloat()
        when {
            position < -2 -> {
                // This page is way off-screen to the left.
                page.scaleX = 0f
                page.scaleY = 0f
                page.pivotX = pageWidth.toFloat()
            }
            position < 0 -> {
                //缩放
                ((1 + position) * (1 - scale) + scale).also {
                    page.scaleX = it
                    page.scaleY = it
                }
                page.pivotX = pageWidth * (DEFAULT_CENTER + DEFAULT_CENTER * -position)
                //层级
                page.translationZ = position
            }
            position < 3 -> {
                //缩放
                ((1 - position) * (1 - scale) + scale).also {
                    page.scaleX = it
                    page.scaleY = it
                }
                page.pivotX = pageWidth * ((1 - position) * DEFAULT_CENTER)
                //层级
                page.translationZ = -position
            }
            else -> {
                page.scaleX = 0f
                page.scaleY = 0f
                page.pivotX = pageWidth.toFloat()
            }
        }
        page.translationX = translationX * position
    }
}