package com.wtm.lib.android.inject;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.wtm.lib.android.parabola.Parabola;

/**
 * Created by 王天明 on 2015/12/24 0024.
 * 抛物线anim注入器
 */
public class ParabolaAnimInject {

    private static final int ANIMPARENTLAYOUTID = Integer.MAX_VALUE;
    private enum Orientation {
        LEFT_TOP,
        RIGHT_TOP
    }

    public static void injectAndStartAnim(Activity activity, View animView, Parabola.Point pa, Parabola.Point pc) {
        injectGetAnim(activity, animView, pa, pc).start();
    }

    public static ObjectAnimator injectGetAnim(Activity activity, View animView, Parabola.Point pa, Parabola.Point pc) {
        FrameLayout linearLayout = injectAnimLayout(activity);
        linearLayout.addView(animView);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = (int) pa.x;
        lp.topMargin = (int) pa.y;
        animView.setLayoutParams(lp);
        return injectAnim(animView, pa, generateCenterPoint(pa, pc), pc);
    }

    /**
     *根据起点和终点 计算一个基准点
     * @param pa 起点
     * @param pc 终点
     * @return 基准点
     */
    private static Parabola.Point generateCenterPoint(Parabola.Point pa, Parabola.Point pc) {
        Parabola.Point result = null;
        if (pa.x == pc.x && pa.y == pc.y) {
            throw new RuntimeException("startPoint equals endPoint !!error!");
        }
        //right-top
        //起点位于终点的右上方
        if (pa.y > pc.y && pa.x > pc.x) {
            float centerX = (pa.x - pc.x) / 2, centerY;
            if (pa.y < 450) {
                centerY = 300;
            } else {
                centerY = (int) (pa.y * .6f);
            }
            result =  Parabola.createPoint(Math.abs(centerX), centerY);
        }


        return result;
    }

    private static FrameLayout injectAnimLayout(Activity activity) {
        FrameLayout linearLayout = (FrameLayout) activity.findViewById(ANIMPARENTLAYOUTID);
        if (linearLayout == null) {
            ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
            linearLayout = new FrameLayout(activity);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            linearLayout.setLayoutParams(lp);
            linearLayout.setId(ANIMPARENTLAYOUTID);
            linearLayout.setBackgroundResource(android.R.color.transparent);
            rootView.addView(linearLayout);
        }
        return linearLayout;
    }

    private static ObjectAnimator injectAnim(final View animView, Parabola.Point pa, Parabola.Point pb, Parabola.Point pc) {
        Parabola parabola = new Parabola(pa, pb, pc);
        int count = (int) Math.abs(pa.x - pc.x);

        Keyframe[] keyframes_x = new Keyframe[count];
        Keyframe[] keyframes_y = new Keyframe[count];
        final float keyStep = 1f / (float) count;
        float key = 0;
        for (int i = 0; i < count; i++) {
            float x = pa.x - i;
            float y = Math.abs(parabola.calcY(x));
            //设置的是变化的值
            keyframes_x[i] = Keyframe.ofFloat(key, -i);
            keyframes_y[i] = Keyframe.ofFloat(key, y - pa.y);
            key += keyStep;
        }
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("translationX", keyframes_x);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe("translationY", keyframes_y);
        ObjectAnimator yxBouncer = ObjectAnimator.ofPropertyValuesHolder(animView, pvhY, pvhX).setDuration(1000);
        yxBouncer.setInterpolator(new AccelerateDecelerateInterpolator());
        yxBouncer.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animView.setVisibility(View.GONE);
                ((ViewGroup) animView.getParent()).removeView(animView);
            }
        });
        return yxBouncer;
    }
}
