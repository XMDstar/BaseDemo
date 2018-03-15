package com.zcc.frame.base;

import android.util.SparseArray;
import android.view.View;

/**
 * 公用 ViewHolder
 * 
 * @author zcc
 * @since 2017-6-24 下午12:49:25
 */
@SuppressWarnings("unchecked")
public class BaseViewHolder {
	public static <T extends View> T get(View view, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}
}
