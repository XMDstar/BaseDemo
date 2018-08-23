package com.zcc.frame.activity.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public interface ILoginPresenter extends LifecycleObserver{
    public void clear();
    public void toLogin(String name, String password);
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause();

}
