package com.zcc.frame.album.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcc.frame.R;

public class AlbumViewHolder extends RecyclerView.ViewHolder{
    public LinearLayout card;
    public RecyclerView itemDataRC;
    public TextView itemDateTv;

    public AlbumViewHolder(View itemView) {
        super(itemView);
        card = (LinearLayout) itemView.findViewById(R.id.cardView);
        itemDataRC = (RecyclerView) itemView.findViewById(R.id.item_photo);
        itemDateTv = (TextView) itemView.findViewById(R.id.item_date);
    }
}
