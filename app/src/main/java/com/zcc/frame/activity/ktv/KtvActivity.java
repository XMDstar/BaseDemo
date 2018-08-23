package com.zcc.frame.activity.ktv;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.common.LogManager;
import com.zcc.frame.databinding.ActivityKtvBinding;
import com.zcc.frame.tools.Toaster;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class KtvActivity extends BaseActivity {
    ActivityKtvBinding binding;
    private IjkMediaPlayer player;
    private VideoPlayerIJK ijkPlayer;
    private int type = 0;
    private String title;
    private RtcEngine rtcEngine=null;
    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
            LogManager.e("uid====================================="+uid);
        }

        @Override
        public void onLeaveChannel(RtcStats stats) {
            super.onLeaveChannel(stats);
            LogManager.e("=====================================leave");
        }

        @Override
        public void onUserJoined(int uid, int elapsed) {
            super.onUserJoined(uid, elapsed);
            LogManager.e("=====================================join user");
        }

        @Override
        public void onUserOffline(int uid, int reason) {
            super.onUserOffline(uid, reason);
            if (uid == 2333) {
                LogManager.e("=====================================leave user is host");
                rtcEngine.leaveChannel();
                RtcEngine.destroy();
                rtcEngine = null;
                Toaster.show("房间已解散");
                finish();
            }else{
                LogManager.e("=====================================leave user");
            }
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up:
                LogManager.e("=====================================join user up");
               int a= rtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
                LogManager.e("用户角色====================================="+a);
                break;
            case R.id.btn_down:
                LogManager.e("=====================================join user down");
               int b= rtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
                LogManager.e("用户角色====================================="+b);
                break;
            case R.id.btn_leave:
                rtcEngine.leaveChannel();
                RtcEngine.destroy();
                rtcEngine = null;
                finish();
                break;
        }
    }

    @Override
    public void bindLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ktv);
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
        ijkPlayer=binding.sfVideoview;
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
    public void loadVideo(String path) {
        ijkPlayer.setVideoPath(path);
    }
    @Override
    public void setListener() {
        binding.btnUp.setOnClickListener(this);
        binding.btnDown.setOnClickListener(this);
        binding.btnLeave.setOnClickListener(this);

    }

    @Override
    public void doBusiness(){
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
        LogManager.e("title====================================="+title);
        LogManager.e("type====================================="+type);
        if (type == 1) {
            //创建 RtcEngine 对象
            try {
                rtcEngine = RtcEngine.create(this,getString(R.string.ktv_app_id), mRtcEventHandler);
            } catch (Exception e) {
                LogManager.e(Log.getStackTraceString(e));
                throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
            }
            //设置频道模式为直播
            int a=rtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
            LogManager.e("频道模式====================================="+a);
            //设置用户角色为主播
            int b=rtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
            LogManager.e("用户角色====================================="+b);
            //创建并加入频道
            int c= rtcEngine.joinChannel(null, title, null, 2333);
            LogManager.e("加入频道====================================="+c);
        } else if (type == 2) {
            binding.lyGuestTools.setVisibility(View.VISIBLE);
            //创建 RtcEngine 对象
            try {
                rtcEngine = RtcEngine.create(this,getString(R.string.ktv_app_id), mRtcEventHandler);
            } catch (Exception e) {
                LogManager.e(Log.getStackTraceString(e));
                throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
            }
            //设置频道模式为直播
            rtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
            //设置用户角色为房客
            rtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
            //创建并加入频道
            rtcEngine.joinChannel(null, title, null, 0);
        } else {

        }
       loadVideo("http://crk.momocdn.com/mv/DD/E6/DDE62798-C8F1-4526-ACC1-E2240328B12E20180406_h264.mp4");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ijkPlayer.stop();
        IjkMediaPlayer.native_profileEnd();
    }
}
