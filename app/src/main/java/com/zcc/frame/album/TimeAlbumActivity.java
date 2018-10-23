package com.zcc.frame.album;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcc.frame.R;
import com.zcc.frame.album.adapter.TimeAlbumAdapter;
import com.zcc.frame.album.bean.AlbumData;
import com.zcc.frame.album.bean.MaterialBean;
import com.zcc.frame.album.utils.AlbumUtils;
import com.zcc.frame.tools.event.EventCode;
import com.zcc.frame.tools.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class TimeAlbumActivity extends Activity {
    private int REQUEST_CODE_ADD_DATA = 2;
    private int REQUEST_CODE_TAKE_PHOTO = 3;
    private int ALBUM_CHECK_MAX = 5;
    private int ALBUM_CHECK_VIDEO_MAX = 1;
    private RecyclerView feedList;
    private TextView tvDate;
    private LinearLayout mSuspensionBar;
    private List<AlbumData> photoDatas;
    private ContentResolver resolver;
    private TimeAlbumAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private AlbumUtils albumUtils;
    private int mCurrentPosition = 0;
    private int mSuspensionHeight;
    private List<MaterialBean> checkList;
    private boolean isAdd = false;
    private int videoCheckCount = 0;
    private ArrayList<MaterialBean> materialBeans;
    private List<String> checkName;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    photoDatas = (ArrayList<AlbumData>) bundle.getParcelableArrayList("materialBeans").get(0);
                    materialBeans = (ArrayList<MaterialBean>) bundle.getParcelableArrayList("materialBeans").get(1);
                    if (isAdd) {
                        photoDatas.get(0).getList().get(1).setCheck(true);
                        checkList.add(materialBeans.get(0));
                        checkName.add(materialBeans.get(0).getName());
                        isAdd = false;
                    }
                    adapter = new TimeAlbumAdapter(photoDatas, TimeAlbumActivity.this);
                    adapter.setCheckId(checkName);
                    adapter.setRefresh(false);
                    feedList.setAdapter(adapter);
                    if (photoDatas != null && photoDatas.size() > 0) {
                        tvDate.setText(photoDatas.get(mCurrentPosition).getDate());
                    }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarTextColor(true);
        setContentView(R.layout.activity_time_album);
        EventBus.getDefault().register(this);
        initData();
        getData();
    }
    private void changeStatusBarTextColor(boolean isBlack) {
        //透明状态栏  
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            }else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageReceived(MessageEvent event) {
        if (event != null && event.getCode() == EventCode.ALBUM_CHECK) {
            MaterialBean checkBean = (MaterialBean) event.getData();
            if (!checkList.contains(checkBean)) {
                checkList.add(checkBean);
            }
            //置灰
            if (checkList.size() == 9) {
                adapter.setRefresh(true);
                adapter.notifyDataSetChanged();
            }
            if (!checkName.contains(checkBean.getName())) {
                checkName.add(checkBean.getName());
            }
        }
        if (event != null && event.getCode() == EventCode.ALBUM_CHECK_NO) {
            MaterialBean checkBean = (MaterialBean) event.getData();
            if (checkList.contains(checkBean)) {
                checkList.remove(checkBean);
            }
            if (checkList.size() == 8) {
                adapter.setRefresh(false);
                adapter.notifyDataSetChanged();
            }
            if (checkName.contains(checkBean.getName())) {
                checkName.remove(checkBean.getName());
            }
        }
        if (event != null && event.getCode() == EventCode.ALBUM_SHOW_DETAIL) {
            MaterialBean checkBean = (MaterialBean) event.getData();
            if (checkBean != null && checkBean.getType() != 3) {
                int position = materialBeans.indexOf(checkBean);
                Intent intent = new Intent(this, AlbumPreviewActivity.class);
                intent.putParcelableArrayListExtra("materialBeans", (ArrayList<? extends Parcelable>) materialBeans);
                intent.putStringArrayListExtra("checkId", (ArrayList<String>) checkName);
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_CODE_ADD_DATA);
            } else {
                takePhoto();
            }

        }
    }

    private void initData() {
        feedList = (RecyclerView) findViewById(R.id.feed_list);
        tvDate = (TextView) findViewById(R.id.tv_date);
        mSuspensionBar = (LinearLayout) findViewById(R.id.suspension_bar);
        resolver = getContentResolver();
        checkList = new ArrayList<>();
        checkName = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        feedList.setLayoutManager(mLayoutManager);
        feedList.setHasFixedSize(true);

        feedList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = mSuspensionBar.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = mLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null) {
                    if (view.getTop() <= mSuspensionHeight) {
                        mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
                    } else {
                        mSuspensionBar.setY(0);
                    }
                }

                if (mCurrentPosition != mLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = mLayoutManager.findFirstVisibleItemPosition();

                    updateSuspensionBar();
                    mSuspensionBar.setY(0);
                }
            }
        });
    }

    private void updateSuspensionBar() {
        if (photoDatas != null && photoDatas.size() > 0) {
            tvDate.setText(photoDatas.get(mCurrentPosition).getDate());
        }
    }


    private void getData() {
        new Thread() {
            @Override
            public void run() {
                albumUtils = new AlbumUtils(resolver);
                ArrayList<MaterialBean> beans = albumUtils.getSortData();
                Message msg = new Message();
                msg.what = 1;
                Bundle bundle = new Bundle();
                ArrayList Alist = new ArrayList();
                Alist.add(albumUtils.getFormatData(beans));
                Alist.add(beans);
                bundle.putParcelableArrayList("materialBeans", Alist);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
            isAdd = true;
            getData();
        }
        if (requestCode == REQUEST_CODE_ADD_DATA && resultCode == Activity.RESULT_OK && data.getExtras().getBoolean("isrefresh")) {
            adapter.setCheckId(checkName);
            adapter.notifyDataSetChanged();
        }
    }
}
