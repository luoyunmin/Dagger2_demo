package com.wtm.lib.android.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wtm on 2015/5/5.
 */
public abstract class WBaseAdapter<T, H extends WViewHolder> extends BaseAdapter {

    protected static final String TAG = WBaseAdapter.class.getSimpleName();

    protected final Context context;

    protected int layoutResId;

    protected final List<T> data;

    public WBaseAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public WBaseAdapter(Context context, int layoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() :data;
        this.context = context;
        this.layoutResId = layoutResId;
    }

    protected MultiItemTypeSupport<T> mMultiItemSupport;

    public WBaseAdapter(Context context, ArrayList<T> data,
                        MultiItemTypeSupport<T> multiItemSupport) {
        this.mMultiItemSupport = multiItemSupport;
        this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final H holder = getAdapterHolder(position, convertView, parent);
        T item = getItem(position);
        holder.setAssociatedObject(item);
        convert(holder, item);
        return holder.getView();
    }

    @Override
    public boolean isEnabled(int position) {
        return position < data.size();
    }

    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(data.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        data.clear();
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return data.contains(elem);
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    protected abstract void convert(H holder, T item);

    protected abstract H getAdapterHolder(int position, View convertView,
                                          ViewGroup parent);
}