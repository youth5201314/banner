package com.youth.banner;

import android.content.Context;
import android.view.View;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Wanghy360 on 2018/11/30.
 */
public abstract class LoopAdapter {
    private LoopObservable observable;

    public LoopAdapter() {
        observable = new LoopObservable();
    }

    public void notifyDataSetChanged() {
        observable.setChanged();
        observable.notifyObservers();
        observable.clearChanged();
    }

    void registerObserver(Observer observer) {
        observable.addObserver(observer);
    }

    void deleteObservers() {
        observable.clearChanged();
        observable.deleteObservers();
    }

    /**
     * 数量
     */
    public abstract int getCount();

    /**
     * item view
     */
    public abstract View getItemView(Context context, int realPosition);

    /**
     * 标题列表
     */
    public abstract List<String> getTitles();

    private static class LoopObservable extends Observable {
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        protected synchronized void clearChanged() {
            super.clearChanged();
        }
    }
}
