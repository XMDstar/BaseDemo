package com.zcc.frame.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.view.AutoplayView;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor zcc
 * @time ${DATE} ${TIME}
 * @class 实现自定义图片功能
 * 思想：绘制布局为需求图片样式 截屏
 */
public class CreateImageActivity extends BaseActivity {
    ImageView iv1, iv2, iv3;
    RelativeLayout rl;
    RecyclerView recyclerView;
    RvAdapter adapter;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_create_image);
    }

    @Override
    public void initView() {
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        rl = (RelativeLayout) findViewById(R.id.rl);
        recyclerView = (RecyclerView) findViewById(R.id.rc);
        Glide.with(this).load("https://heras.igengmei.com/banner/2019/03/18/53b4124b62").into(iv1);
        Glide.with(this).load("https://heras.igengmei.com/banner/2019/04/08/644e91d060").into(iv2);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness() {
        adapter = new RvAdapter(this);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void clickbutton(View view) {
        iv3.setImageBitmap(viewConversionBitmap(rl));
    }

    /**
     * view转bitmap
     */
    public Bitmap viewConversionBitmap(View v) {
        int w = v.getWidth();
        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);
        v.draw(c);
        Log.e("zcc", bmp.getHeight() * bmp.getRowBytes() + "");
        return bmp;
    }

    class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {
        private Context mContext;
        private List<String> list;

        public RvAdapter(Context mContext) {
            this.mContext = mContext;
            list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add("测试" + i);
            }
        }

        @Override
        public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_album_img, parent, false);
            return new RvViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RvViewHolder holder, int position) {
            holder.autoplayView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class RvViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;
            public AutoplayView autoplayView;
            public RelativeLayout relativeLayout;

            public RvViewHolder(View itemView) {
                super(itemView);
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_photo);
                textView = (TextView) itemView.findViewById(R.id.tv_time);
                autoplayView = (AutoplayView) itemView.findViewById(R.id.auto);
            }
        }
    }
}
