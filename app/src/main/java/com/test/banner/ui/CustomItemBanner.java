package com.test.banner.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.banner.R;
import com.youth.banner.LoopLayout;

/**
 * Created by Wanghy360 on 2018/6/8.
 */
public class CustomItemBanner extends LoopLayout {
    public CustomItemBanner(@NonNull Context context) {
        super(context);
    }

    public CustomItemBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomItemBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomItemBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected View getItemView(int realPosition) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custom_item_view, this, false);
        TextView tvPosition = (TextView) view.findViewById(R.id.tv_position);
        ImageView ivImage = (ImageView) view.findViewById(R.id.iv_image);

        tvPosition.setText(getResources().getString(R.string.no_position, realPosition));
        if (realPosition == 0) {
            ivImage.setImageResource(R.mipmap.a);
        } else if (realPosition == 1) {
            ivImage.setImageResource(R.mipmap.b);
        } else if (realPosition == 2) {
            ivImage.setImageResource(R.mipmap.b1);
        } else if (realPosition == 3) {
            ivImage.setImageResource(R.mipmap.b2);
        } else if (realPosition == 4) {
            ivImage.setImageResource(R.mipmap.c);
        } else {
            ivImage.setImageResource(R.mipmap.d);
        }
        return view;
    }
}
