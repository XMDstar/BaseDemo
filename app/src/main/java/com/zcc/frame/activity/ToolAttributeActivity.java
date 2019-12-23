package com.zcc.frame.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.fragment.TestFragment;
import com.zcc.frame.service.TestService;
import com.zcc.frame.tools.Toaster;

public class ToolAttributeActivity extends BaseActivity {
    private FrameLayout fl;
    private Intent intent;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    @Override
    public void onClick(View v) {

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_tool_attribute);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness() {
        ATest aTest = new ATest(new ATest.BTest() {
            @Override
            public boolean toDo() {
                return false;
            }
        });
    }

    public void clickbutton(View view) {
        Toaster.show("click");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zcc", "1=======onCreate");
        fl = findViewById(R.id.fl);
        TestFragment fragment = new TestFragment();
        Log.e("zcc", "1=======fragment");
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl, fragment);
        transaction.commit();
        intent = new Intent(this, TestService.class);

    }
ServiceConnection connection=new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
};
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zcc", "1=======onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        startService(intent);
//        startForegroundService(intent);
        bindService(intent, connection, Context.BIND_AUTO_CREATE); // 开启服务
        Log.e("zcc", "1=======onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("zcc", "1=======onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zcc", "1=======onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        stopService(intent);
        unbindService(connection);
        Log.e("zcc", "1=======onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zcc", "1=======onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.e("zcc", "1=======onConfigurationChanged");
    }
}

class ATest {
    public ATest(BTest bTest) {
    }

    public interface BTest {
        boolean toDo();
    }
}
