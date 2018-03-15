package com.zcc.frame.activity;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;

    @Override
    public void onClick(View view) {
        binding.surfaceviewCanvas.reDraw();
    }

    @Override
    public void bindLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        binding.tvReset.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }
}
