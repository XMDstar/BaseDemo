package com.zcc.frame.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zcc.frame.R;
import com.zcc.frame.album.view.SquareImg;

import java.util.Collections;
import java.util.List;

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.DragViewHolder> implements DragItemRecyclerView.ItemTouchHelperAdapter {
    private List<String> list;
    private Context mContext;

    public DragAdapter() {
    }

    public DragAdapter setList(List<String> list) {
        this.list = list;
        return this;
    }

    public List<String> getList() {
        return list;
    }

    public DragAdapter setContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    @Override
    public DragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_album_img, parent, false);
        return new DragViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DragViewHolder holder, final int position) {
        holder.cbPhoto.setVisibility(View.GONE);
        if (list != null && !TextUtils.isEmpty(list.get(position))) {
            Glide.with(mContext).load(list.get(position)).into(holder.img);
        }
        holder.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //移除数据
                list.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        else return 0;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public class DragViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout card;
        public SquareImg img;
        public TextView tvTime;
        public CheckBox cbPhoto;

        public DragViewHolder(View itemView) {
            super(itemView);
            card = (RelativeLayout) itemView.findViewById(R.id.rl_photo);
            img = (SquareImg) itemView.findViewById(R.id.iv_photo);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            cbPhoto = (CheckBox) itemView.findViewById(R.id.cb_photo);
        }
    }
}
