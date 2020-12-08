package com.zcc.frame.activity.ktv;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayVideoActivity extends BaseActivity {

    @Bind(R.id.sf_video)
    VideoPlayerIJK sfVideo;
    @Bind(R.id.mediacontroller_play_pause)
    ImageButton mediacontrollerPlayPause;
    @Bind(R.id.mediacontroller_time_current)
    TextView mediacontrollerTimeCurrent;
    @Bind(R.id.mediacontroller_seekbar)
    SeekBar mediacontrollerSeekbar;
    @Bind(R.id.mediacontroller_time_total)
    TextView mediacontrollerTimeTotal;
    @Bind(R.id.mediacontroller_top_back)
    ImageView mediacontrollerTopBack;
    private IjkMediaPlayer player;
    private VideoPlayerIJK ijkPlayer;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mediacontroller_play_pause:
                break;
            case R.id.mediacontroller_top_back:
                break;

        }

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_play_video);
        ButterKnife.bind(this);
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
        ijkPlayer = sfVideo;
        ijkPlayer.setListener(new VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            }

            @Override
            public void onCompletion(IMediaPlayer mp) {
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
        loadVideo("http://crk.momocdn.com/mv/DD/E6/DDE62798-C8F1-4526-ACC1-E2240328B12E20180406_h264.mp4");
    }

    public void loadVideo(String path) {
        ijkPlayer.setVideoPath(path);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness() {
    }

    @Override
    protected void onStop() {
        super.onStop();
        ijkPlayer.stop();
        IjkMediaPlayer.native_profileEnd();
    }
}
