package com.zcc.frame.album.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcc.frame.R;
import com.zcc.frame.album.bean.AlbumData;
import com.zcc.frame.album.view.AutoGridLayoutManager;
import com.zcc.frame.album.view.RecyclerItemDecoration;

import java.util.List;

public class TimeAlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private List<String> checkId;
    private boolean isRefresh = false;
    private AutoGridLayoutManager gridLayoutManager;
    private List<AlbumData> photos;
    private Context mContext;
    private RecyclerView.RecycledViewPool viewPool;
    private GridPhotoAdapter adapter;

    public TimeAlbumAdapter(List<AlbumData> photos, Context mContext) {
        this.photos = photos;
        this.mContext = mContext;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    public void setCheckId(List<String> checkId) {
        this.checkId = checkId;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo, parent, false);
        AlbumViewHolder holder = new AlbumViewHolder(view);
        holder.itemDataRC.setRecycledViewPool(viewPool);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.itemDateTv.setText(photos.get(position).getDate());
        gridLayoutManager = new AutoGridLayoutManager(mContext, 4);
        gridLayoutManager.setScrollEnabled(false);
        holder.itemDataRC.setLayoutManager(gridLayoutManager);
        holder.itemDataRC.addItemDecoration(new RecyclerItemDecoration(4, (int) ((1.5*mContext.getResources().getDisplayMetrics().density)+0.5f),false));
        adapter = new GridPhotoAdapter(photos.get(position).getList(), mContext, checkId);
        adapter.setRefresh(isRefresh);
        holder.itemDataRC.setAdapter(adapter);

    }


    @Override
    public int getItemCount() {
        if (photos != null) {
            return photos.size();
        } else return 0;
    }

}