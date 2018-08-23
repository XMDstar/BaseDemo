package com.zcc.frame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.zcc.frame.callback.StateCallBack;
import com.zcc.frame.common.LogManager;

public class MeasuringListview extends ListView implements AbsListView.OnScrollListener {
    private StateCallBack callBack;
    private int positon = 0;
    private boolean isPlay = true;

    public MeasuringListview(Context context) {
        super(context);
        setOnScrollListener(this);
    }

    public MeasuringListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(this);
    }

    public MeasuringListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnScrollListener(this);
    }

    public void setCallBack(StateCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE://停止滚动
                LogManager.e("ListView滚动状态---->停止滚动");
                /**在这里执行，视频的自动播放与停止*/
                if (callBack != null) {
                    callBack.pLayItemPosition(positon);
                    isPlay = true;
                }
                break;
            case SCROLL_STATE_TOUCH_SCROLL://拖动
                LogManager.e("ListView滚动状态---->拖动");
                //callBack.stopItemPosition(positon);
                isPlay = false;
                break;
            case SCROLL_STATE_FLING://惯性滑动
                isPlay = false;
                LogManager.e("ListView滚动状态---->惯性滑动");
                break;
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View firstView = view.getChildAt(firstVisibleItem);
        if (null != firstView) {
            if (firstView.getHeight() + firstView.getTop() <= firstView.getHeight() / 3) {
                //video stop or play second
                if (visibleItemCount < 2) {
                    return;
                }
                positon = firstVisibleItem + 1;
            } else {
                if (positon == firstVisibleItem) {
                    return;
                }
                positon = firstVisibleItem;
            }
            LogManager.e("play position==" + positon);
        }
    }
}
