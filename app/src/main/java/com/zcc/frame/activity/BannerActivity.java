package com.zcc.frame.activity;

import android.os.Bundle;
import android.os.HandlerThread;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcc.frame.R;

import java.util.ArrayList;
import java.util.List;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * 仿淘宝直播banner轮播
 */
public class BannerActivity extends AppCompatActivity {
    ViewPager viewPager;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        PagerContainer container = (PagerContainer) findViewById(R.id.pager_container);
        HandlerThread thread = new HandlerThread("banner");
        thread.start();
        viewPager = container.getViewPager();
        list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add("http://hera.s.igengmei.com/2018/04/09/adc2b8896f");
        }
        VPAdapter vpAdapter = new VPAdapter();
        viewPager.setAdapter(vpAdapter);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(15);
        new CoverFlow.Builder()
                .with(viewPager)
                .scale(0.3f)
                .pagerMargin(-30)
                .spaceSize(0f)
                .build();
    }

    private class VPAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(BannerActivity.this).inflate(R.layout.item_cover, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
            Glide.with(BannerActivity.this).load(list.get(position)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }

}