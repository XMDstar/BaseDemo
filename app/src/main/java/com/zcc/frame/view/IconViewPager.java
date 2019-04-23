package com.zcc.frame.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zcc.frame.R;

import java.util.List;

/**
 * @version 7765 美购首页重构
 * @anthor zcc
 * @time 2019/03/14
 * @class viewpager+gridview 实现多屏功能区入口
 */
public class IconViewPager extends RelativeLayout {
    private Context mContext;
    private LinearLayout lyPoint;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    public IconViewPager(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    @SuppressLint("InflateParams")
    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_icon_viewpager, null);
        lyPoint = (LinearLayout) view.findViewById(R.id.icon_ly);
        mViewPager = (ViewPager) view.findViewById(R.id.icon_vp);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.icon_rl);
        addView(view);
    }

    /**
     * 刷新数据
     *
     * @param groups 具体的圈子
     */
    public void setData(List<String> groups) {
        if (null == groups || groups.size() <= 0) return;
//        //需要重新绑定adapter刷新数据
//        mViewPagerAdapter = new WelfareHomeViewpagerAdapter(groups,mContext,mListener);
//        mViewPager.setAdapter(mViewPagerAdapter);
//        mViewPagerCount = mViewPagerAdapter.getCount();
//        initViewPagerPoints();
    }

    /**
     * 设置ViewPager高度  默认128dp
     *
     * @param id
     */
    private void setViewPagerStyle(int id) {
        if (id <= 0) return;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mViewPager.getLayoutParams();
        params.height = id;
        mViewPager.setLayoutParams(params);
    }

    private void setLinearLayoutStyle(int id) {
    }

    /**
     * 设置RelativeLayout高度
     *
     * @param id
     */
    private void setRelativeLayoutStyle(int id) {
    }
    public interface OnViewItemClickListener {
        void onGridViewItemClick(String bean);
    }
}
