package com.zcc.frame.base;

/**
 * Activity接口
 * 
 */
public interface IBaseActivity {

	/**
	 * 绑定渲染视图的布局文件
	 * 
	 * @return 布局文件资源id
	 */
	int bindLayout();

	/**
	 * 初始化视图
	 */
	void initView();

	/**
	 * 设置事件监听
	 */
	void setListener();

	/**
	 * 业务处理操作（onCreate方法中调用）
	 */
	void doBusiness();

}
