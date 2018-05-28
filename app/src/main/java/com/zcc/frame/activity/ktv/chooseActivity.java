package com.zcc.frame.activity.ktv;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.tools.Toaster;

public class ChooseActivity extends BaseActivity {
    private TextView mTvName;
    private Button mBtnGuest;
    private Button mBtnHost;
    private EditText mEtName;

    private void assignViews() {
        mTvName = (TextView) findViewById(R.id.tv_name);
        mBtnGuest = (Button) findViewById(R.id.btn_guest);
        mBtnHost = (Button) findViewById(R.id.btn_host);
        mEtName = (EditText) findViewById(R.id.et_name);
    }


    @Override
    public void onClick(View view) {
        if (mEtName.getText().toString() == null || mEtName.getText().toString().equals("")) {
            Toaster.show("请先输入房间名！");
            return;
        }
        Intent intent = new Intent(ChooseActivity.this, KtvActivity.class);
        intent.putExtra("title", mEtName.getText().toString());
        switch (view.getId()) {
            case R.id.btn_guest:
                intent.putExtra("type", 2);
                break;
            case R.id.btn_host:
                intent.putExtra("type", 1);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_choose);
    }

    @Override
    public void initView() {
        assignViews();
    }

    @Override
    public void setListener() {
        mBtnGuest.setOnClickListener(this);
        mBtnHost.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }
}
