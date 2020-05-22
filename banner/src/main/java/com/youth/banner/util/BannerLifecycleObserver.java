package com.youth.banner.util;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public interface BannerLifecycleObserver extends LifecycleObserver {

    void onStop(LifecycleOwner owner);

    void onStart(LifecycleOwner owner);

    void onDestroy(LifecycleOwner owner);
}
