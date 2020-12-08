package com.zcc.frame.activity.ktv;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.common.LogManager;
import com.zcc.frame.tools.Toaster;
import com.zcc.frame.tools.event.EventCode;
import com.zcc.frame.tools.event.MessageEvent;
import com.zcc.frame.tools.event.annotation.BindEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@BindEventBus
public class VideoListActivity extends BaseActivity {

    @Bind(R.id.lv_video)
    RecyclerView lvVideo;
    private List<String> data;
    private MyAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    int playPosition = 0;
    VideoPlayerIJK play;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);
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
        EventBus.getDefault().post(new MessageEvent(1, "succeed"));
//        lvVideo.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                recyclerView.getBaseline();
//                //direction为 -1 表示手指向下滑动（屏幕向上滑动）， 1 表示手指向上滑动（屏幕向下滑动）。
//                if (mLayoutManager != null) {
//                    int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();//得到显示屏内的第一个list的位置数position
//                    View firstView = mLayoutManager.findViewByPosition(firstVisibleItem);
////                    View view=mLayoutManager.getChildAt(firstVisibleItem);
//
//                    if (null != firstView) {
//                        if (dy > 0) {
//                            if (firstView.getHeight() + firstView.getTop() <= firstView.getHeight() / 3) {
//                                //video stop or play second
//                                if (mLayoutManager.getChildCount() < 2) {
//                                    return;
//                                }
//                                if (playPosition == firstVisibleItem + 1) {
//                                    return;
//                                }
//                                playPosition = firstVisibleItem + 1;
//                                adapter.setPlay(playPosition);
//                                //adapter.notifyDataSetChanged();
//                                adapter.notifyItemChanged(playPosition);
//                            } else {
//                                if (playPosition == firstVisibleItem) {
//                                    return;
//                                }
//                                playPosition = firstVisibleItem;
////                                adapter.setPlay(playPosition);
//                                View view=mLayoutManager.findViewByPosition(playPosition);
//                                FrameLayout fl= (FrameLayout) view.findViewById(R.id.fl);
//
//                                play=new VideoPlayerIJK(VideoListActivity.this);
//                                play.setVideoPath(data.get(0));
//                                fl.addView(play);
//
//                            }
//
//                        }
//                        if (dy < 0) {
//                                if (firstView.getHeight() + firstView.getTop() >= firstView.getHeight() * 2 / 3) {
//                                    //video stop or play second
//                                    if (mLayoutManager.getChildCount() < 2) {
//                                        return;
//                                    }
//                                    if (playPosition == firstVisibleItem) {
//                                        return;
//                                    }
//                                    playPosition = firstVisibleItem;
//                                    adapter.setPlay(playPosition);
//                                } else {
//                                    if (playPosition == firstVisibleItem + 1) {
//                                        return;
//                                    }
//                                    playPosition = firstVisibleItem + 1;
//                                    adapter.setPlay(playPosition);
//                                }
//                        }
//                    }
//                }
//            }
//        });

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
//            viewHolder.iv.setVisibility(View.VISIBLE);
////            viewHolder.ijkPlayer.release();
//            if (play == position) {
//                viewHolder.ijkPlayer.setVisibility(View.VISIBLE);
//                viewHolder.ijkPlayer.setVideoPath(data.get(position));
//                viewHolder.iv.setVisibility(View.GONE);
//                LogManager.e("======" + position);
//            } else {
//                viewHolder.iv.setVisibility(View.VISIBLE);
//                viewHolder.ijkPlayer.setVisibility(View.GONE);
//                viewHolder.ijkPlayer.release();
//            }

//            viewHolder.tv.setText("video" + position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView iv;
//            VideoPlayerIJK ijkPlayer;

            public ViewHolder(View itemView) {
                super(itemView);
//                ijkPlayer = (VideoPlayerIJK) itemView.findViewById(R.id.sf_videoview);
//                tv = (TextView) itemView.findViewById(R.id.tv);
//                iv = (ImageView) itemView.findViewById(R.id.iv);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageReceived(MessageEvent event) {
        if (event != null && event.getCode() == EventCode.loginSucceed) {
            Toaster.show("onMessageReceived======" + event.getData());
            LogManager.e("onMessageReceived======+event.getData()");
        }
    }

}
