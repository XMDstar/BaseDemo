package com.zcc.frame.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.zcc.frame.R;

/**
 * 功能: 基类 所有fragment继承此类
 * 
 * @author zcc
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
	/** 日志输出标志 **/
	protected final String TAG = getClass().getSimpleName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(bindLayout(), container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view, savedInstanceState);
		doBusiness();
		setListener(view, savedInstanceState);
	}
	/**
	 * 绑定渲染视图的布局文件
	 * 
	 * @return 布局文件资源id
	 */
	public abstract int bindLayout();

	/**
	 * 初始化视图
	 * 
	 * @param view The View returned by
	 *            {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
	 * @param savedInstanceState If non-null, this fragment is being
	 *            re-constructed from a previous saved state as given here.
	 */
	public abstract void initView(View view, Bundle savedInstanceState);

	/**
	 * 业务处理操作
	 */
	public abstract void doBusiness();

	/**
	 * 设置监听事件
	 * 
	 * * @param view The View returned by
	 * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
	 * 
	 * @param savedInstanceState If non-null, this fragment is being
	 *            re-constructed from a previous saved state as given here.
	 * 
	 */
	public abstract void setListener(View view, Bundle savedInstanceState);

	@Override
	public void onClick(View v) {

	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		getActivity().overridePendingTransition(R.anim.activity_right_in, R.anim.activity_right_out);

	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		getActivity().overridePendingTransition(R.anim.activity_right_in, R.anim.activity_right_out);
	}

}
