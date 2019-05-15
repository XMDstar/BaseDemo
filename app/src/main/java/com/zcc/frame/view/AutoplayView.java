package com.zcc.frame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcc.frame.R;

/**
 * @anthor zcc
 * @time 2019-04-28
 * @class 视频自动播放控件
 */
public class AutoplayView extends RelativeLayout {
    private TextView plVideoTextureView;
    private int videoHeight;

    public AutoplayView(Context context) {
        super(context);
        init(context);
    }

    public AutoplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.layout_autoplay_view, this);
        plVideoTextureView = (TextView) findViewById(R.id.tv);
        plVideoTextureView.post(new Runnable() {
            @Override
            public void run() {
                videoHeight = plVideoTextureView.getHeight();
            }
        });
        videoVisible();
    }

    private void videoVisible() {
//        int[] location = new int[2];
//        view.getLocationInWindow(location);
    }

    public void setText(String str) {
        plVideoTextureView.setText(str);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            Log.e("zcc", plVideoTextureView.getText().toString() + "======" + visibility);
        } else if (visibility == GONE || visibility == INVISIBLE) {
            Log.e("zcc", plVideoTextureView.getText().toString() + "======" + visibility);
        }
    }
}
