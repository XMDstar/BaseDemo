package com.zcc.frame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zcc.frame.R;
import com.zcc.frame.http.Api;
import com.zcc.frame.http.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxjavaRetrofitActivity extends AppCompatActivity {
    @Bind(R.id.rc)
    public RecyclerView rc;
    private List<ScrollActivity.Topic> list;
    private BaseQuickAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_retrofit);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ScrollActivity.Topic topic = new ScrollActivity.Topic("图片" + i, "https://heras.igengmei.com/banner/2019/04/08/644e91d060");
            list.add(topic);
        }
        adapter = createAdapter();
        adapter.addData(list);
        ApiClient.retrofit().create(Api.class).loadDataByRetrofitRxJava("02").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ScrollActivity.Topic>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(ScrollActivity.Topic topic) {

            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(RxjavaRetrofitActivity.this, "模拟数据", Toast.LENGTH_LONG).show();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RxjavaRetrofitActivity.this);
                rc.setLayoutManager(linearLayoutManager);
                rc.setAdapter(adapter);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    protected BaseQuickAdapter createAdapter() {
        return new BaseQuickAdapter<ScrollActivity.Topic, BaseViewHolder>(R.layout.item_album_img, null) {
            @Override
            protected void convert(final BaseViewHolder helper, final ScrollActivity.Topic item) {
                helper.setText(R.id.tv_time, item.title);
                // 加载网络图片
                Glide.with(mContext).load(item.image).into((ImageView) helper.getView(R.id.iv_photo));
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
