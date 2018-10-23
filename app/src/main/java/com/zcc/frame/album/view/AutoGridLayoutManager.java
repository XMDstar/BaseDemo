package com.zcc.frame.album.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

public class AutoGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public AutoGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

}