package com.wtm.lib.android.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wtm on 2015/5/18.
 * BaseAdapter的替代者
 */
public abstract class WAdapter<T> extends WBaseAdapter<T, WViewHolder> {


    public WAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public WAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    public WAdapter(Context context, ArrayList data, MultiItemTypeSupport multiItemSupport) {
        super(context, data, multiItemSupport);
    }


    @Override
    protected WViewHolder getAdapterHolder(int position, View convertView, ViewGroup parent) {
        if (mMultiItemSupport != null) {
            return WViewHolder.obtain(
                    context,
                    convertView,
                    parent,
                    mMultiItemSupport.getLayoutId(position, data.get(position)),
                    position);
        } else {
            return WViewHolder.obtain(context, convertView, parent, layoutResId, position);
        }
    }
}
