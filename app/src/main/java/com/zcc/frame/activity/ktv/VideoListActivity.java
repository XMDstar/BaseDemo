package com.zcc.frame.activity.ktv;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.callback.StateCallBack;
import com.zcc.frame.common.LogManager;
import com.zcc.frame.view.MeasuringListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoListActivity extends BaseActivity {

    @InjectView(R.id.lv_video)
    RecyclerView lvVideo;
    private List<String> data;
    private MyAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    int playPosition = 0;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_video_list);
        ButterKnife.inject(this);
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("http://crk.momocdn.com/mv/DD/E6/DDE62798-C8F1-4526-ACC1-E2240328B12E20180406_h264.mp4");
        }
        mLayoutManager = new LinearLayoutManager(this);
        //2 为RecyclerView创建布局管理器，这里使用的是LinearLayoutManager，表示里面的Item排列是线性排列
        lvVideo.setLayoutManager(mLayoutManager);

        adapter = new MyAdapter();
        lvVideo.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness() {

        lvVideo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                recyclerView.getBaseline();
                //direction为 -1 表示手指向下滑动（屏幕向上滑动）， 1 表示手指向上滑动（屏幕向下滑动）。
                if (mLayoutManager != null) {
                    int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();//得到显示屏内的第一个list的位置数position
                    View firstView = mLayoutManager.findViewByPosition(firstVisibleItem);

                    if (null != firstView) {
                        if (dy > 0) {


                            if (firstView.getHeight() + firstView.getTop() <= firstView.getHeight() / 3) {
                                //video stop or play second
                                if (mLayoutManager.getChildCount() < 2) {
                                    return;
                                }
                                if (playPosition == firstVisibleItem + 1) {
                                    return;
                                }
                                playPosition = firstVisibleItem + 1;
                                adapter.setPlay(playPosition);
                            } else {
                                if (playPosition == firstVisibleItem) {
                                    return;
                                }
                                playPosition = firstVisibleItem;
                                adapter.setPlay(playPosition);
                            }

                        }
                        if (dy < 0) {
                                if (firstView.getHeight() + firstView.getTop() >= firstView.getHeight() * 2 / 3) {
                                    //video stop or play second
                                    if (mLayoutManager.getChildCount() < 2) {
                                        return;
                                    }
                                    if (playPosition == firstVisibleItem) {
                                        return;
                                    }
                                    playPosition = firstVisibleItem;
                                    adapter.setPlay(playPosition);
                                } else {
                                    if (playPosition == firstVisibleItem + 1) {
                                        return;
                                    }
                                    playPosition = firstVisibleItem + 1;
                                    adapter.setPlay(playPosition);
                                }
                        }
                    }
                }
            }
        });

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private int play = 0;

        public void setPlay(int play) {
            this.play = play;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //加载布局文件
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_videolist, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, int position) {
            //将数据填充到具体的view中
            viewHolder.iv.setVisibility(View.VISIBLE);
            viewHolder.ijkPlayer.release();
            if (play == position) {
                viewHolder.ijkPlayer.setVisibility(View.VISIBLE);
                viewHolder.ijkPlayer.setVideoPath(data.get(position));
                viewHolder.iv.setVisibility(View.GONE);
                LogManager.e("======" + position);
            } else {
                viewHolder.iv.setVisibility(View.VISIBLE);
                viewHolder.ijkPlayer.setVisibility(View.GONE);
                viewHolder.ijkPlayer.release();
            }

            viewHolder.tv.setText("video" + position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView iv;
            VideoPlayerIJK ijkPlayer;

            public ViewHolder(View itemView) {
                super(itemView);
                ijkPlayer = (VideoPlayerIJK) itemView.findViewById(R.id.sf_videoview);
                tv = (TextView) itemView.findViewById(R.id.tv);
                iv = (ImageView) itemView.findViewById(R.id.iv);
            }
        }
    }

}
