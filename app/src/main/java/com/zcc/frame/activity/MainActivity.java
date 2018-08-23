package com.zcc.frame.activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.view.View;

import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;
import com.zcc.frame.common.LogManager;
import com.zcc.frame.databinding.ActivityMainBinding;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private int i = 0;
    private Disposable mDis;

    //模拟oom
    //    private Handler mHandler = new Handler();
    @Override
    public void onClick(View view) {
        binding.surfaceviewCanvas.reDraw();
    }

    @Override
    public void bindLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        binding.tvReset.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }, 3 * 60 * 1000);
//        finish();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                LogManager.e("MAIN", "subscribe==" + i);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
                LogManager.e("MAIN", "onNext=====" + 3);
                emitter.onNext(4);
                LogManager.e("MAIN", "onNext=====" + 4);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDis = d;
                LogManager.e("MAIN", "onSubscribe==" + i);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                i++;
                LogManager.e("MAIN", "onNext==" + i);
                if (i == 3) {
                    mDis.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogManager.e("MAIN", "onError==" + i);
            }

            @Override
            public void onComplete() {
                LogManager.e("MAIN", "onComplete==" + i);
            }
        });
    }
}
