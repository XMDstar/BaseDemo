package com.zcc.frame.activity.mvp.presenter;

import android.text.TextUtils;

import com.zcc.frame.activity.mvp.view.ILoginView;

public class LoginPresenterCompl implements ILoginPresenter{
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
}
