package com.zcc.frame.base;

import android.app.Activity;
import android.app.Application;

import com.zcc.frame.tools.Toaster;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by zcc on 2017/6/5.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance = null;
    /***
     * 寄存整个应用Activity
     **/
    private final Stack<WeakReference<Activity>> activitys = new Stack<WeakReference<Activity>>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Toaster.init(instance);

    }

    public static BaseApplication getInstance() {
        return instance;
    }
    /******************************************* Application中存放的Activity操作（压栈/出栈）API（开始） *****************************************/

    /**
     * 将Activity压入Application栈
     *
     * @param task 将要压入栈的Activity对象
     */
    public void pushTask(WeakReference<Activity> task) {
        activitys.push(task);
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        activitys.remove(task);
    }

    /**
     * 移除全部（用于整个应用退出）
     */
    public void removeAll() {
        // finish所有的Activity
        for (WeakReference<Activity> task : activitys) {
            if (!task.get().isFinishing()) {
                task.get().finish();
            }
        }
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    public void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex)
            activitys.remove(taskIndex);
    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            if (!activitys.get(i).get().isFinishing()) {
                activitys.get(i).get().finish();
            }
        }
    }
    /******************************************* Application中存放的Activity操作（压栈/出栈）API（结束） *****************************************/
}
