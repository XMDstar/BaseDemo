package com.zcc.frame.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcc.frame.R;

import java.lang.ref.WeakReference;

/**
 * Activity基类
 * Created by zcc on 2017/6/4.
 */

public abstract class BaseActivity extends FragmentActivity implements IBaseActivity, OnClickListener {
    /***
     * 整个应用Applicaiton
     **/
    private BaseApplication mApplication = null;
    /**
     * 当前Activity的弱引用，防止内存泄露
     **/
    private WeakReference<Activity> context = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG = getClass().getSimpleName();
    protected InputMethodManager iMManager;
    /**
     * 标题容器
     */
    protected RelativeLayout rl_title_bar_container;
    /**
     * 横线
     */
    protected View iv_title_bar_btom_div;
    /**
     * 返回键
     */
    protected TextView tv_title_bar_left;
    /**
     * 标题
     */
    protected TextView tv_title_bar_title;
    /**
     * 小标题
     */
    protected TextView tv_title_bar_little_titile;
    /**
     * 中间内容
     */
    protected LinearLayout ll_base_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout();
        // 将当前Activity压入栈
        mApplication = BaseApplication.getInstance();
        context = new WeakReference<Activity>(this);
        mApplication.pushTask(context);
        initBaseView();
        initView();
        setListener();
        doBusiness();
    }

    private void initBaseView() {
        rl_title_bar_container = (RelativeLayout) findViewById(R.id.rl_title_bar_container);
        iv_title_bar_btom_div = (View) findViewById(R.id.iv_title_bar_btom_div);
        tv_title_bar_title = (TextView) findViewById(R.id.tv_title_bar_title);
        tv_title_bar_left = (TextView) findViewById(R.id.tv_title_bar_left);
        tv_title_bar_little_titile = (TextView) findViewById(R.id.tv_title_bar_little_titile);
        ll_base_container = (LinearLayout) findViewById(R.id.ll_base_container);
    }

    /**
     * 设置内容View
     *
     * @param layoutId
     */
    protected void setContainerView(int layoutId) {
        // 把布局添加到ll_base_container 返回的View是ll_base_container
        LayoutInflater.from(getActivity()).inflate(layoutId, ll_base_container, true);
    }

    /**
     * 设置页面标题
     *
     * @param titleString 标题内容
     */
    protected void setTitle(String titleString) {
        if (tv_title_bar_title != null)
            tv_title_bar_title.setText(titleString);
    }

    /**
     * 设置页面标题颜色
     *
     * @param colorId
     */
    protected void setTitleTextColor(int colorId) {
        if (tv_title_bar_title != null)
            tv_title_bar_title.setTextColor(colorId);
    }

    /**
     * 设置返回页面图标
     */
    protected void setGoBackImg() {
        Drawable drawableLeft = getResources().getDrawable(R.mipmap.icon_back);
        drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight()); //设置边界
        if (tv_title_bar_left != null)
            tv_title_bar_left.setCompoundDrawables(drawableLeft, null, null, null);
    }

    /**
     * 设置返回按钮点击事件——finish()
     */
    protected void setGoBack() {
        if (tv_title_bar_left != null)
            tv_title_bar_left.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    hideSoftInputFromWindow();
                    getActivity().finish();
                }
            });
    }

    /**
     * 设置小标题
     */
    protected void setLittleTitile(String text) {
        if (tv_title_bar_little_titile != null) {
            tv_title_bar_little_titile.setText(TextUtils.isEmpty(text) ? "" : text);
            tv_title_bar_little_titile.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        }

    }

    /**
     * 设置小标题
     */
    protected void setLittleTitileColor(int colorId) {
        if (tv_title_bar_little_titile != null) {
            tv_title_bar_little_titile.setTextColor(colorId);
        }

    }

    /**
     * 设置标题分割线隐藏
     */
    protected void setTitleContainerBackgroundGone() {
        iv_title_bar_btom_div.setVisibility(View.GONE);
    }

    /**
     * 隐藏输入法软键盘
     */
    protected void hideSoftInputFromWindow() {
        Activity act = getActivity();
        if (iMManager == null) {
            iMManager = (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);
        }

        if (iMManager != null) {
            View currentFocus = act.getCurrentFocus();
            if (currentFocus != null) {
                iMManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 获取当前Activity
     *
     * @return
     */
    protected Activity getActivity() {
        if (null != context)
            return context.get();
        else
            return null;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_right_in, R.anim.activity_right_out);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_right_in, R.anim.activity_right_out);
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftInputFromWindow();
        overridePendingTransition(R.anim.activity_left_in, R.anim.activity_left_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication.removeTask(context);
    }
}
