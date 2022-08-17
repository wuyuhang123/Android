package com.example.myapplication.recycleView.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class PageListViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public PageListViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.list_item);
    }
}
