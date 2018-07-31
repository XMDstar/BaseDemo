package com.zcc.frame.activity.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zcc.frame.R;
import com.zcc.frame.activity.MainActivity;
import com.zcc.frame.activity.mvp.presenter.LoginPresenterCompl;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends Activity implements ILoginView {

    @InjectView(R.id.button_login)
    Button buttonLogin;
    @InjectView(R.id.button_clear)
    Button buttonClear;
    @InjectView(R.id.et_name)
    EditText etName;
    @InjectView(R.id.et_password)
    EditText etPassword;
    private LoginPresenterCompl compl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        compl = new LoginPresenterCompl(this);
    }

    @OnClick(R.id.button_login)
    public void isLoginClick() {
        compl.toLogin(etName.getText().toString(), etPassword.getText().toString());
    }

    @OnClick(R.id.button_clear)
    public void isClearClick() {
        compl.clear();
    }

    @Override
    public void onClearText() {
        etName.setText("");
        etPassword.setText("");

    }

    @Override
    public void onLoginResult(boolean result) {
        if (result) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT);
        }

    }
}
