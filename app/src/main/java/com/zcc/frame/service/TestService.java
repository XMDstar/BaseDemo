package com.zcc.frame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Copyright:更美互动信息科技有限公司
 * FileName: TestService
 * Author: zcc
 * Date: 2019-10-22 10:08
 * Description: 测试生命周期
 */
public class TestService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zcc", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags,
                              int startId) {
        Log.e("zcc", "onStartCommand===="+startId+"===="+flags);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("zcc", "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("zcc", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zcc", "onDestroy");
    }
}
