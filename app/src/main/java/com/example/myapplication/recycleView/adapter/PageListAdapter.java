package com.example.myapplication.recycleView.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.recycleView.data.PageData;
import com.example.myapplication.R;
import com.example.myapplication.recycleView.viewholder.PageListViewHolder;

import java.util.List;

public class PageListAdapter extends RecyclerView.Adapter<PageListViewHolder> implements View.OnClickListener, View.OnLongClickListener{

    private final List<PageData> list;
    private final Context mContext;

    public PageListAdapter(List<PageData> list, Context context){
        this.list = list;
        this.mContext = context;
    }

    public void removeData(int position){
        list.remove(position);
        this.notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public PageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_page, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new PageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageListViewHolder holder, int position) {
        PageData pageData = list.get(position);
        TextView textView = holder.textView;
        textView.setText(pageData.getPageName());
        holder.itemView.setTag(position);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        PageData pageData = list.get(position);
        mContext.startActivity(new Intent(mContext, pageData.getPageClass()));
    }

    @Override
    public boolean onLongClick(View v) {
        int position = (int) v.getTag();
        AlertDialog alertDialog = new AlertDialog.Builder(mContext).setTitle("确认删除该条？").setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PageListAdapter.this.removeData(position);
            }
        }).setNegativeButton("cancel", null).create();
        alertDialog.show();
        Button btnPos = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button btnNeg = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        btnPos.setAllCaps(false);
        btnNeg.setAllCaps(false);
        btnPos.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        btnNeg.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        return true;
    }
}
