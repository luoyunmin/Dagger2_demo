package com.wtm.lib.android.adapter.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by wtm on 2015/5/5.
 */
public class WViewHolder {

    private SparseArray<View> holderViews;

    private View convertView;

    private Context context;

    private int position;

    public int layoutId;

    private ViewGroup parent;


    private Object associatedObject;

    private WViewHolder(Context context, ViewGroup parent,
                        int layoutId, int position) {

        this.holderViews = new SparseArray<>();
        this.context = context;
        this.position = position;
        this.parent = parent;
        this.layoutId = layoutId;

        convertView = LayoutInflater.from(context)
                .inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    public static WViewHolder obtain(Context context, View convertView, ViewGroup parent, int layoutId) {
        return obtain(context, convertView, parent, layoutId, -1);
    }

    static WViewHolder obtain(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new WViewHolder(context, parent, layoutId, position);
        }
        WViewHolder existingHelper = (WViewHolder) convertView
                .getTag();

        if (existingHelper.layoutId != layoutId) {
            return new WViewHolder(context, parent, layoutId, position);
        }
        existingHelper.position = position;
        return existingHelper;
    }

    public <V extends View> V getView(int id) {
        View view = holderViews.get(id);
        if (view == null) {
            view = convertView.findViewById(id);
            holderViews.put(id, view);
        }
        return (V) view;
    }


    public WViewHolder setText(int viewId, String value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }


    public WViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public WViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public WViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public WViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public WViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(context.getResources().getColor(textColorRes));
        return this;
    }

    public WViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public WViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public WViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public WViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 给textView添加一个超链接
     *
     * @param viewId
     * @return
     */
    public WViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public WViewHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    public WViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public WViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public WViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public WViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * 设置评分
     *
     * @param viewId
     * @param rating
     * @return
     */
    public WViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * 设置评分
     *
     * @param viewId
     * @param rating
     * @param max
     * @return
     */
    public WViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public WViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public WViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public WViewHolder setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        if (view instanceof Checkable) {
            Checkable checkableView = (Checkable)view;
            checkableView.setChecked(checked);
        }
        return this;
    }

    public WViewHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    public WViewHolder setOnClickListener(int viewId,
                                          View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public WViewHolder setOnTouchListener(int viewId,
                                          View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public WViewHolder setOnLongClickListener(int viewId,
                                              View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public View getView() {
        return convertView;
    }

    public int getPosition() {
        if (position == -1)
            throw new IllegalStateException(
                    "Use WViewHolder constructor "
                            + "with position if you need to retrieve the position.");
        return position;
    }


    public Object getAssociatedObject() {
        return associatedObject;
    }

    public void setAssociatedObject(Object associatedObject) {
        this.associatedObject = associatedObject;
    }

}