package com.youth.banner;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.adapter.BannerAdapter;

/**
 * <pre>
 *     @desc: 通过去掉泛型暂时解决 kotlin 中使用出现 Nothing 的问题
 * </pre>
 *
 * @author imtianx
 * @email imtianx@gmail.com
 * @date 2021/5/24 08:09 PM
 * @see Banner
 */
public class BannerKt<T> extends Banner<T, BannerAdapter<T, RecyclerView.ViewHolder>> {
    public BannerKt(Context context) {
        super(context);
    }

    public BannerKt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerKt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 去掉泛型避免 kotlin 中出现 Nothing 错误
    public BannerKt<T> setAdapterKt(BannerAdapter adapter) {
        setAdapter(adapter);
        return this;
    }

    public BannerKt<T> setAdapterKt(BannerAdapter adapter, boolean isInfiniteLoop) {
        setAdapter(adapter, isInfiniteLoop);
        return this;
    }
}
