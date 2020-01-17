package com.youth.banner.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.youth.banner.vp2.IViewHolder;
import com.youth.banner.vp2.Utils;

import java.util.List;

public abstract class BannerFragmentAdapter extends FragmentStateAdapter implements IViewHolder {

    public abstract int getRealCount();

    public BannerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public BannerFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public BannerFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public final Fragment createFragment(int position) {
        return createFragment(Utils.getRealPosition(position, getRealCount()));
    }

    @Override
    public final int getItemCount() {
        return getRealCount() > 1 ? getRealCount() + 2 : getRealCount();
    }

    @Override
    public final long getItemId(int position) {
        return Utils.getRealPosition(position, getRealCount());
    }

    @Override
    public final void onBindViewHolder(@NonNull FragmentViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, Utils.getRealPosition(position, getRealCount()), payloads);
    }

}
