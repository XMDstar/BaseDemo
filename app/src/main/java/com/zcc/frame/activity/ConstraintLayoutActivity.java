package com.zcc.frame.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcc.frame.R;

public class ConstraintLayoutActivity extends AppCompatActivity {
    private ConstraintLayout mActivityConstraintLayout;
    private ImageView mImageView;
    private Button mButton2;
    private Button mButton3;
    private TextView mTextView2;

    private void assignViews() {
        mActivityConstraintLayout = (ConstraintLayout) findViewById(R.id.activity_constraint_layout);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mTextView2 = (TextView) findViewById(R.id.textView2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
    }
}
