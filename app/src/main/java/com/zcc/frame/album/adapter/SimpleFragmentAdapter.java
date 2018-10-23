package com.zcc.frame.album.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcc.frame.R;
import com.zcc.frame.album.bean.MaterialBean;
import com.zcc.frame.album.callback.RefreshCallBack;
import com.zcc.frame.album.view.SquareImg;
import com.zcc.frame.album.view.photoview.PhotoView;
import com.zcc.frame.common.LogManager;
import com.zcc.frame.tools.event.EventCode;
import com.zcc.frame.tools.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

/**
 * author：zcc
 * data：18/09/26
 */

public class SimpleFragmentAdapter extends PagerAdapter {
    private List<MaterialBean> images;
    private Context mContext;
    private List<String> checkId;
    private RefreshCallBack callBack;

    public SimpleFragmentAdapter(List<MaterialBean> images, Context context, List<String> checkId, RefreshCallBack callBack) {
        super();
        this.images = images;
        this.mContext = context;
        this.checkId = checkId;
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
        if (images != null) {
            return images.size();
        }
        return 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View contentView = LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_image_preview, container, false);
        // 常规图控件
        PhotoView imageView = (PhotoView) contentView.findViewById(R.id.preview_img);
        ImageView iv_play = (ImageView) contentView.findViewById(R.id.iv_play);
        CheckBox check = (CheckBox) contentView.findViewById(R.id.preview_cb);
        final MaterialBean media = images.get(position);
        if (media != null) {
            final String path = media.getPath();
            boolean isVideo = media.getType() == 1 ? false : true;
            iv_play.setVisibility(isVideo ? View.VISIBLE : View.GONE);
            Glide.with(mContext).load(new File(media.getPath())).into(imageView);
            if (checkId.size() > 0 && checkId.contains(media.getName())) {
                check.setChecked(true);
            } else {
                check.setChecked(false);
            }
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (!checkId.contains(media.getName())) {
                            checkId.add(media.getName());
                        }
                        EventBus.getDefault().post(new MessageEvent(EventCode.ALBUM_CHECK, media));
                    } else {
                        if (checkId.contains(media.getName())) {
                            checkId.remove(media.getName());
                        }
                        EventBus.getDefault().post(new MessageEvent(EventCode.ALBUM_CHECK_NO, media));
                    }
                    callBack.checkNews(checkId.size());
                }
            });
            iv_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("video_path", path);
//                    intent.putExtras(bundle);
//                    intent.setClass(mContext, PictureVideoPlayActivity.class);
//                    mContext.startActivity(intent);
                }
            });
        }
        (container).addView(contentView, 0);
        return contentView;
    }
}
