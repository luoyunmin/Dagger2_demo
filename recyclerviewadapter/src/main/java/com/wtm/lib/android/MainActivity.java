package com.wtm.lib.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;
import com.wtm.lib.android.adapter.RecyclerHolder;
import com.wtm.lib.android.adapter.WRecyclerAdapter;
import com.wtm.lib.android.data.ImgUrl;
import com.wtm.lib.android.inject.ParabolaAnimInject;
import com.wtm.lib.android.parabola.Parabola;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.img_shopCart)
    ImageView imgShopCart;

    private Parabola.Point cartPoint = null;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < ImgUrl.urls.length; i++) {
            persons.add(new Person("item" + i, ImgUrl.urls[i]));
        }
        recyclerView.setAdapter(new WRecyclerAdapter<Person>(R.layout.item_list, this, persons) {
            @Override
            protected void convert(RecyclerHolder holder, final int position, Person person) {
                holder.setText(R.id.tv, person.getName())
                        .setImageUrl(R.id.imgView, person.getImgUrl());
                holder.setOnClickListener(R.id.btn_addCart, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setImageResource(R.mipmap.ic_launcher);
                        imageView.setMaxWidth(100);
                        imageView.setMaxHeight(100);
                        if (null == cartPoint) {
                            cartPoint = getAPoint(imgShopCart);
                        }
                        if (position % 2 == 0) {
                            ParabolaAnimInject.injectAndStartAnim(MainActivity.this, imageView,
                                    getAPoint(v),
                                    cartPoint);
                        } else {
                            ObjectAnimator anim = ParabolaAnimInject.injectGetAnim(MainActivity.this, imageView,
                                    getAPoint(v),
                                    cartPoint);
                            anim.setDuration(2000);
                            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    float fraction = animation.getAnimatedFraction();
                                    if (fraction > 1) {
                                        return;
                                    }
                                    //fraction为动画执行的百分比
                                    ViewCompat.setRotationY(imageView, fraction * 1000);
                                }
                            });
                            anim.start();
                        }
                    }
                });
            }
        });
    }

    public Parabola.Point getAPoint(View v) {
        int[] locations = new int[2];
        v.getLocationInWindow(locations);
        return Parabola.createPoint(locations[0], locations[1]);
    }

    @OnClick(R.id.img_shopCart)
    public void jumpTest() {
        startActivity(new Intent(this, TestActivity.class));
    }
}