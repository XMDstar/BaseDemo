package com.zcc.frame.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewSwitcher;

import com.zcc.frame.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zcc on 2017/11/27.
 */

public class WelfareSwitcherView extends ViewSwitcher implements View.OnClickListener {
    private ArrayList<String> reArrayList = new ArrayList<String>();
    private int resIndex = 0, newPosition = 0;
    private final int UPDATE_TEXTSWITCHER = 1;

    public WelfareSwitcherView(Context context) {
        super(context);
        init();
    }

    public WelfareSwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setOnClickListener(this);
        this.setInAnimation(getContext(), R.anim.home_banner_enter_bottom);
        this.setOutAnimation(getContext(), R.anim.home_banner_leave_top);
    }

    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            Message msg = new Message();
            msg.what = UPDATE_TEXTSWITCHER;
            handler.sendMessage(msg);
        }
    };
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXTSWITCHER:
                    updateTextSwitcher();
                    break;
                default:
                    break;
            }

        }
    };

    /**
     * 需要传递的资源
     *
     * @param reArrayList
     */
    public void getResource(ArrayList<String> reArrayList) {
        this.reArrayList = reArrayList;
        if (reArrayList.size() == 1) {
        } else {
            Timer timer = new Timer();
            timer.schedule(timerTask, 1, 7000);
        }
    }

    public void updateTextSwitcher() {
        if (this.reArrayList != null && this.reArrayList.size() > 0) {
            newPosition = resIndex;
            if (resIndex > this.reArrayList.size() - 1) {
                resIndex = 0;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (newPosition) {
        }
    }
}
