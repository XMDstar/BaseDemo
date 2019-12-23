package com.zcc.frame.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zcc.frame.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor zcc
 * @time ${DATE} ${TIME}
 * @class 滑动吸顶方案1   方案2：一个布局add到指定显示位置  监听滑动
 */
public class ScrollActivity extends AppCompatActivity {
    private NestedScrollView scrollView;
    private ImageView scrollIv;
    private TabLayout scrollTablayout, scrollTablayout1;
    private RecyclerView scrollRc;
    private BaseQuickAdapter adapter;
    private List<Topic> list;

    private void assignViews() {
        scrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        scrollIv = (ImageView) findViewById(R.id.scroll_iv);
        scrollTablayout = (TabLayout) findViewById(R.id.scroll_tablayout);
        scrollTablayout1 = (TabLayout) findViewById(R.id.scroll_tablayout1);
        scrollRc = (RecyclerView) findViewById(R.id.scroll_rc);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        initTab(scrollTablayout);
        initTab(scrollTablayout1);
        scrollRc.setLayoutManager(layoutManager);
        scrollRc.setHasFixedSize(true);
        scrollRc.setNestedScrollingEnabled(false);
        adapter = createAdapter();
        scrollRc.setAdapter(adapter);
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Topic topic = new Topic("图片" + i, "https://heras.igengmei.com/banner/2019/04/08/644e91d060");
            list.add(topic);
        }
        adapter.addData(list);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                if (i1 >= scrollIv.getHeight()) {
                    scrollTablayout1.setVisibility(View.VISIBLE);
                } else {
                    scrollTablayout1.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initTab(TabLayout scrollTablayout) {
        scrollTablayout.addTab(scrollTablayout.newTab().setText("其一").setIcon(R.mipmap.ic_launcher));
        scrollTablayout.addTab(scrollTablayout.newTab().setText("其二").setIcon(R.mipmap.ic_launcher));
        scrollTablayout.addTab(scrollTablayout.newTab().setText("其三").setIcon(R.mipmap.ic_launcher));
        scrollTablayout.addTab(scrollTablayout.newTab().setText("其四").setIcon(R.mipmap.ic_launcher));
        scrollTablayout.addTab(scrollTablayout.newTab().setText("其五").setIcon(R.mipmap.ic_launcher));
        scrollTablayout.addTab(scrollTablayout.newTab().setText("其六").setIcon(R.mipmap.ic_launcher));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        assignViews();
    }

    protected BaseQuickAdapter createAdapter() {
        return new BaseQuickAdapter<Topic, BaseViewHolder>(R.layout.item_album_img, null) {
            @Override
            protected void convert(final BaseViewHolder helper, final Topic item) {
                helper.setText(R.id.tv_time, item.title);
                // 加载网络图片
                Glide.with(mContext).load(item.image).into((ImageView) helper.getView(R.id.iv_photo));
            }
        };
    }

    public static class Topic {
        public String title, image;

        public Topic(String title, String image) {
            this.title = title;
            this.image = image;
        }
    }
}
