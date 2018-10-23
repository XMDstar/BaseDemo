package com.zcc.frame.album;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zcc.frame.R;
import com.zcc.frame.album.adapter.SimpleFragmentAdapter;
import com.zcc.frame.album.bean.AlbumData;
import com.zcc.frame.album.bean.MaterialBean;
import com.zcc.frame.album.callback.RefreshCallBack;
import com.zcc.frame.album.view.PreviewViewPager;

import java.util.ArrayList;
import java.util.List;

public class AlbumPreviewActivity extends Activity implements View.OnClickListener,RefreshCallBack{
    private PreviewViewPager viewPager;
    private TextView back,next;
    private int position;
    private boolean isRefresh=false;
    private List<String> checkId;
    private ArrayList<MaterialBean> materialBeans;
    private SimpleFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏  
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_album_preview);
        initView();
    }

    private void initView() {
        position = getIntent().getIntExtra("position", 0);
        materialBeans = getIntent().getParcelableArrayListExtra("materialBeans");
        checkId = getIntent().getStringArrayListExtra("checkId");
        viewPager = (PreviewViewPager) findViewById(R.id.vp_preview);
        back = (TextView) findViewById(R.id.tv_back);
        next= (TextView) findViewById(R.id.tv_next);
        back.setOnClickListener(this);
        next.setOnClickListener(this);
        next.setText("下一步("+checkId.size()+")");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int i) {
                position = i;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        adapter = new SimpleFragmentAdapter(materialBeans, this, checkId,this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_next:
                Intent intent = new Intent();
                intent.putExtra("isrefresh", isRefresh);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    public void checkNews(int size) {
        next.setText("下一步("+size+")");
        isRefresh=true;
    }
}
