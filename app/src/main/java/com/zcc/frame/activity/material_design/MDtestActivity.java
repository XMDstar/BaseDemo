package com.zcc.frame.activity.material_design;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 此类主要使用Material Design中CoordinatorLayout，FloatingActionButton、TextInputEditText、Snackbar、TextInputLayout
 * CardView控件而写的一些基本使用类
 */
public class MDtestActivity extends BaseActivity {


    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.text_layout)
    TextInputLayout textLayout;
    @Bind(R.id.et_password)
    TextInputEditText etPassword;
    @Bind(R.id.fab_button)
    FloatingActionButton fabButton;
    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_button:
                Snackbar.make(coordinatorLayout, "This is ok!", Snackbar.LENGTH_LONG).setAction("NavigationView", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MDtestActivity.this, "That is ok!", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_mdtest);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {
        fabButton.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
