package com.netease.nim.uikit.common.ui.recyclerview.holder;

import android.support.v7.widget.RecyclerView;

/**
 * Created by AllStar on 2017/3/6.
 */

public abstract class RecyclerViewHolder<T extends RecyclerView.Adapter, V extends BaseViewHolder, K> {
    final private T adapter;

    public RecyclerViewHolder(T adapter) {
        this.adapter = adapter;
    }

    public T getAdapter() {
        return adapter;
    }

    public abstract void convert(V holder, K data, int position, boolean isScrolling);
}
