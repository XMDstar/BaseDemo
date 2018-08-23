package com.zcc.frame.activity.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.text.TextUtils;
import android.util.Log;

import com.zcc.frame.activity.mvp.view.ILoginView;
import com.zcc.frame.common.LogManager;

public class LoginPresenterCompl  implements ILoginPresenter {
    private ILoginView iLoginView;

    public LoginPresenterCompl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void clear() {
        iLoginView.onClearText();

    }

    @Override
    public void toLogin(String name, String password) {
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)) {
            iLoginView.onLoginResult(true);
        }else{
            iLoginView.onLoginResult(false);
        }
    }
    @Override
    public void onPause() {
        LogManager.e("====================LoginPresenterCompl  onPause");
    }
}
