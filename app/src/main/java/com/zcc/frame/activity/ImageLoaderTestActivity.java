package com.zcc.frame.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.tools.ImageLoaderUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ImageLoaderTestActivity extends BaseActivity {


    @InjectView(R.id.iv)
    ImageView imageView;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_image_loader_test);
    }

    @Override
    public void initView() {
//        ImageLoaderUtil.init(this).displayRoundRadius("http://hera.s.igengmei.com/2018/04/09/adc2b8896f",imageView);
//        ImageLoaderUtil.getInstance(this).displayImage(imageView,"http://hera.s.igengmei.com/2018/04/09/adc2b8896f");
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
