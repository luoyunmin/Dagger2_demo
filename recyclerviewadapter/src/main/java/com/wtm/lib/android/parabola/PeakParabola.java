package com.wtm.lib.android.parabola;

/**
 * Created by 王天明 on 2015/12/24 0024.
 * 抛物线顶点试
 */
public class PeakParabola {

    private final String TAG = "Parabola";

    public static class Point {
        public final float x;
        public final float y;

        private Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    float a, h, k;

    /**
     * 顶点和随意一点确定方程式
     * <p>
     * y = a(x-h)²+k
     * (h,k)为顶点坐标
     */
    public PeakParabola(Point peak, Point any) {
        calculate(peak, any);
    }

    /**
     * 计算抛物线方程式 y = a(x-h)²+k
     * a = (y-k)/(x-h)²
     */
    private void calculate(Point peak, Point any) {
        h = peak.x;
        k = peak.y;
        a = (float) ((any.y - k) / Math.pow(any.x - h, 2));
    }

    /**
     * 通过x计算一个y值
     *
     * @param x
     * @return
     */
    public float calcY(float x) {
        return a * (x - h) * (x - h) + k;
    }

    public static Point createPoint(float x, float y) {
        return new Point(x, y);
    }
}
