package com.zcc.frame.activity;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
/**
  首先，我们需要安装这个插件：
 打开Android Studio -> Setting -> 选择：Plugins
 ->选择Browse Repositories -> 之后输入：Android Layout ID Converter
 之后 选择：install plugin 之后选择重启Android Studio

 其次，在我们的布局文件中，写入我们的控件，并添加id属性。
 之后，选择我们整个布局文件，
 单击右键。选择 Convert Android layout xml , 弹出一个对话框，然后根据自己的需要选择相应的选项，(也可以在tools里看到)
 然后点击确定。这时候，相应的代码就会自动复制在我们的粘贴板上，之后到我们相应的Activity或者Fragment中Ctrl+V即可
 *
 * */
public class ConstraintLayoutActivity extends BaseActivity{
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
    public void onClick(View view) {

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_constraint_layout);
    }

    @Override
    public void initView() {
        assignViews();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness() {

    }
}
