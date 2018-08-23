package com.zcc.frame.activity.ktv;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayVideoActivity extends BaseActivity {

    @InjectView(R.id.sf_video)
    VideoPlayerIJK sfVideo;
    @InjectView(R.id.mediacontroller_play_pause)
    ImageButton mediacontrollerPlayPause;
    @InjectView(R.id.mediacontroller_time_current)
    TextView mediacontrollerTimeCurrent;
    @InjectView(R.id.mediacontroller_seekbar)
    SeekBar mediacontrollerSeekbar;
    @InjectView(R.id.mediacontroller_time_total)
    TextView mediacontrollerTimeTotal;
    @InjectView(R.id.mediacontroller_top_back)
    ImageView mediacontrollerTopBack;
    private IjkMediaPlayer player;
    private VideoPlayerIJK ijkPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        ButterKnife.inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mediacontroller_play_pause:
                break;
            case R.id.mediacontroller_top_back:
                break;

        }

    }

    @Override
    public void bindLayout() {

    }

    @Override
    public void initView() {
        //加载so文件
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        player = new IjkMediaPlayer();
        ijkPlayer=sfVideo;
        //ijkPlayer.s
        ijkPlayer.setListener(new VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            }

            @Override
            public void onCompletion(IMediaPlayer mp) {
                mp.seekTo(0);
                mp.start();
            }

            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer mp) {
                mp.start();
            }

            @Override
            public void onSeekComplete(IMediaPlayer mp) {

            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
                //获取到视频的宽和高
            }
        });

    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness() {

    }
}