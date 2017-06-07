package com.wtm.lib.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.wtm.lib.android.inject.ParabolaAnimInject;
import com.wtm.lib.android.parabola.Parabola;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @Bind(R.id.img_shopCart)
    ImageView imgShopCart;
    private Parabola.Point cartPoint = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

    public void add2Cart(View v) {
        if (null == cartPoint) {
            cartPoint = getAPoint(imgShopCart);
        }
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        ParabolaAnimInject.injectAndStartAnim(this, imageView,
                getAPoint(v),
                cartPoint);
    }

    public Parabola.Point getAPoint(View v) {
        int[] locations = new int[2];
        v.getLocationInWindow(locations);
        return Parabola.createPoint(locations[0], locations[1]);
    }
}
