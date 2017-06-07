package fzone.shboka.com.dagger2_demo.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fzone.shboka.com.dagger2_demo.R;
import fzone.shboka.com.dagger2_demo.base.BaseActivity;
import fzone.shboka.com.dagger2_demo.domain.Shop;
import fzone.shboka.com.dagger2_demo.view.transform.GlideCircleTransform;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.Holder> {

    private BaseActivity activity;
    private ArrayList<Shop> shops;

    private AdapterView.OnItemClickListener onItemClickListener;

    public DataListAdapter(BaseActivity activity, ArrayList<Shop> shops) {
        this.activity = activity;
        this.shops = shops;
    }

    @Override
    public DataListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(DataListAdapter.Holder holder, final int position) {
        final Shop shop = shops.get(position);
        Glide.with(activity)
                .load(shop.getImgUrl())
                .error(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(activity))
                .into(holder.imageView);
        holder.textView.setText(shop.getName());
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(null, v, position, v.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            imageView = (ImageView) itemView.findViewById(R.id.imgView);
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
