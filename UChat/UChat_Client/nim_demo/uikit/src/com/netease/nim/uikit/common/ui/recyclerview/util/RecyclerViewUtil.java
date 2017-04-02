package com.netease.nim.uikit.common.ui.recyclerview.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

/**
 * Created by AllStar on 2017/3/8.
 */

public class RecyclerViewUtil {

    public static void changeItemAnimation(RecyclerView recyclerView, boolean isOpen) {
        // 关闭viewholder动画效果。解决viewholder闪烁问题
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(isOpen);
        }
    }
}
