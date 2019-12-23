package com.zcc.frame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zcc.frame.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor zcc
 * @time ${DATE} ${TIME}
 * @class SmartRefreshLayout添加   BaseRecyclerViewAdapterHelper的使用
 */
public class SmartRefreshActivity extends AppCompatActivity {
    private SmartRefreshLayout srl;
    private RecyclerView rv;
    private BaseQuickAdapter adapter;
    private List<ScrollActivity.Topic> list;

    private void assignViews() {
        srl = (SmartRefreshLayout) findViewById(R.id.srl);
        rv = (RecyclerView) findViewById(R.id.rv);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        adapter = createAdapter();
        rv.setAdapter(adapter);
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ScrollActivity.Topic topic = new ScrollActivity.Topic("图片" + i, "https://heras.igengmei.com/banner/2019/04/08/644e91d060");
            list.add(topic);
        }
        adapter.addData(list);
        srl.setEnableLoadmore(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh);
        assignViews();
    }

    protected BaseQuickAdapter createAdapter() {
        return new BaseQuickAdapter<ScrollActivity.Topic, BaseViewHolder>(R.layout.item_album_img, null) {
            @Override
            protected void convert(final BaseViewHolder helper, final ScrollActivity.Topic item) {
                helper.setText(R.id.tv_time, item.title);
                // 加载网络图片
                Glide.with(mContext).load(item.image).into((ImageView) helper.getView(R.id.iv_photo));
            }
        };
    }
}
