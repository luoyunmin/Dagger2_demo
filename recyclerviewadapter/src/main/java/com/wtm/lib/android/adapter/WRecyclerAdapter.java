package com.wtm.lib.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 王天明 on 2015/12/21 0021.
 */
public abstract class WRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {

    private ArrayList<T> data;
    private int layoutId;
    private Context context;

    public WRecyclerAdapter(int layoutId, Context context, ArrayList<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RecyclerHolder(rootView,context);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        convert(holder, position, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected abstract void convert(RecyclerHolder holder, int position, T t);
}