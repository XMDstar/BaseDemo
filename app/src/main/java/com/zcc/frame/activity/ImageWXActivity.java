package com.zcc.frame.activity;

import android.view.View;

import com.zcc.frame.R;
import com.zcc.frame.album.view.AutoGridLayoutManager;
import com.zcc.frame.album.view.RecyclerItemDecoration;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.view.DragAdapter;
import com.zcc.frame.view.DragItemRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor zcc
 * @time ${DATE} ${TIME}
 * @class 仿微信发朋友圈图片可拖拽排序
 */


public class ImageWXActivity extends BaseActivity {
    private DragItemRecyclerView recyclerView;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_image_wx);
    }

    @Override
    public void initView() {
        List<String> list = new ArrayList<>();
        list.add("https://heras.igengmei.com/banner/2019/04/08/cd7db02fb2");
        list.add("https://heras.igengmei.com/banner/2019/04/08/644e91d060");
        list.add("https://heras.igengmei.com/banner/2019/03/18/53b4124b62");
        list.add("https://heras.igengmei.com/banner/2019/03/21/5f6a94e5a0");
        list.add("https://heras.igengmei.com/banner/2019/03/18/4a0f399f58");
        list.add("http://hera.s.igengmei.com/banner/2018/09/06/748d1f4c28");
        list.add("https://heras.igengmei.com/banner/2019/03/28/48c149c23a");
        list.add("https://heras.igengmei.com/banner/2019/03/11/ea5d38da20");
        recyclerView = (DragItemRecyclerView) findViewById(R.id.rc);
        recyclerView.setLayoutManager(new AutoGridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new RecyclerItemDecoration(4, (int) ((1.5 * this.getResources().getDisplayMetrics().density) + 0.5f), false));
        recyclerView.setAdapter(new DragAdapter().setContext(this).setList(list));
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness() {

    }
}
