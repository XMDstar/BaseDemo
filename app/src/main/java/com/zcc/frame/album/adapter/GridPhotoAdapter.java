package com.zcc.frame.album.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.zcc.frame.R;
import com.zcc.frame.album.bean.MaterialBean;
import com.zcc.frame.album.utils.AlbumUtils;
import com.zcc.frame.common.LogManager;
import com.zcc.frame.tools.event.EventCode;
import com.zcc.frame.tools.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

public class GridPhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {
    private List<MaterialBean> materialBeans;
    private Context mContext;
    private List<String> checkId;
    private boolean isRefresh = false;

    public GridPhotoAdapter(List<MaterialBean> materialBeans, Context mContext, List<String> checkId) {
        this.materialBeans = materialBeans;
        this.mContext = mContext;
        this.checkId = checkId;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_album_img, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int position) {
        holder.cbPhoto.setVisibility(View.VISIBLE);
        final MaterialBean bean = materialBeans.get(position);
        if (bean.getType() == 2) {
            holder.img.setImageBitmap(AlbumUtils.getThumbnail(bean.getPath()));
            holder.tvTime.setText(bean.getFormatDuration());
        } else if (bean.getType() == 3) {
            holder.img.setImageResource(R.mipmap.album_camera_icom);
            holder.tvTime.setText("");
            holder.cbPhoto.setVisibility(View.GONE);
        } else {
            Glide.with(mContext).load(new File(bean.getPath())).into(holder.img);
            holder.tvTime.setText("");
        }
        holder.cbPhoto.setOnCheckedChangeListener(null);
        if (checkId.size() > 0 && checkId.contains(bean.getName())) {
            holder.cbPhoto.setChecked(true);
        } else {
            if (isRefresh) {
                holder.cbPhoto.setVisibility(View.GONE);
            }else{
                holder.cbPhoto.setChecked(false);
            }
        }
        holder.cbPhoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkId.add(bean.getName());
                    EventBus.getDefault().post(new MessageEvent(EventCode.ALBUM_CHECK, bean));
                } else {
                    checkId.remove(bean.getName());
                    EventBus.getDefault().post(new MessageEvent(EventCode.ALBUM_CHECK_NO, bean));
                }
            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(EventCode.ALBUM_SHOW_DETAIL, bean));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (materialBeans != null) {
            return materialBeans.size();
        } else return 0;
    }
}
