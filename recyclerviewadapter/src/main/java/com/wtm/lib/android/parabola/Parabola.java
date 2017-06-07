package com.wtm.lib.android.parabola;

import android.util.Log;

/**
 * Created by 王天明 on 2015/12/24 0024.
 * 抛物线
 */
public class Parabola {

    private final String TAG = "Parabola";

    public static class Point {
        public final float x;
        public final float y;

        private Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    float a, b, c;

    /**
     * 三点确定一条抛物线
     *
     * @param a
     * @param b
     * @param c
     */
    public Parabola(Point a, Point b, Point c) {
        calculate(a, b, c);
    }

    /**
     * 计算抛物线方程式 y = ax² + bx + c
     * a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2)) / (x1 * x1 * (x2 -
     * x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2))
     * b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
     * c = y1 - (x1 * x1) * a - x1 * b;
     */
    private void calculate(Point pa, Point pb, Point pc) {
        float x1 = pa.x;
        float y1 = pa.y;
        float x2 = pb.x;
        float y2 = pb.y;
        float x3 = pc.x;
        float y3 = pc.y;
        Log.d(TAG, String.format("(%f,%f)\n", x1, y1));
        Log.d(TAG, String.format("(%f,%f)\n", x2, y2));
        Log.d(TAG, String.format("(%f,%f)\n", x3, y3));
        Log.d(TAG,"计算方程式中...");
        a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
        b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
        c = y1 - (x1 * x1) * a - x1 * b;
        Log.d(TAG, String.format("方程式为: y=%fx²+%fx+%f", a, b, c));
    }

    /**
     * 通过x计算一个y值
     *
     * @param x
     * @return
     */
    public float calcY(float x) {
        return a * x * x + b * x + c;
    }

    public static Point createPoint(float x, float y) {
        return new Point(x, y);
    }
}
